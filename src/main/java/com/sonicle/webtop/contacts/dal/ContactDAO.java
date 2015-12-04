/*
 * webtop-contacts is a WebTop Service developed by Sonicle S.r.l.
 * Copyright (C) 2014 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle@sonicle.com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * "Powered by Sonicle WebTop" logo. If the display of the logo is not reasonably
 * feasible for technical reasons, the Appropriate Legal Notices must display
 * the words "Powered by Sonicle WebTop".
 */
package com.sonicle.webtop.contacts.dal;

import com.sonicle.commons.db.RSUtils;
import com.sonicle.commons.db.StatementUtils;
import com.sonicle.webtop.contacts.bol.OContact;
import com.sonicle.webtop.contacts.bol.VContact;
import static com.sonicle.webtop.contacts.jooq.Sequences.SEQ_CONTACTS;
import static com.sonicle.webtop.contacts.jooq.Tables.CATEGORIES;
import static com.sonicle.webtop.contacts.jooq.tables.Contacts.CONTACTS;
import com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord;
import com.sonicle.webtop.core.dal.BaseDAO;
import com.sonicle.webtop.core.dal.DAOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jooq.DSLContext;
import org.jooq.Field;
import static org.jooq.impl.DSL.field;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

/**
 *
 * @author malbinola
 */
public class ContactDAO extends BaseDAO {
	private final static ContactDAO INSTANCE = new ContactDAO();
	public static ContactDAO getInstance() {
		return INSTANCE;
	}
	
	public static Field<String> CUSTOMERS_DESCRIPTION = field("public.customers.description", String.class);
	
	public Long getSequence(Connection con) throws DAOException {
		DSLContext dsl = getDSL(con);
		Long nextID = dsl.nextval(SEQ_CONTACTS);
		return nextID;
	}
	
	public List<VContact> viewByCategoryQuery(Connection con, int categoryId, String query) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select(
				CONTACTS.CONTACT_ID,
				CONTACTS.CATEGORY_ID,
				CONTACTS.LIST_ID,
				CONTACTS.SEARCHFIELD,
				CONTACTS.TITLE,
				CONTACTS.FIRSTNAME,
				CONTACTS.LASTNAME,
				CONTACTS.NICKNAME,
				CONTACTS.COMPANY,
				CONTACTS.FUNCTION,
				CONTACTS.CADDRESS,
				CONTACTS.CCITY,
				CONTACTS.CTELEPHONE,
				CONTACTS.CMOBILE,
				CONTACTS.CEMAIL,
				CONTACTS.HTELEPHONE,
				CONTACTS.HEMAIL,
				CONTACTS.HBIRTHDAY
			)
			.select(
				CUSTOMERS_DESCRIPTION.as("company_as_customer"),
				CATEGORIES.DOMAIN_ID.as("category_domain_id"),
				CATEGORIES.USER_ID.as("category_user_id")
			)
			.from(CONTACTS)
			.join(CATEGORIES).on(CONTACTS.CATEGORY_ID.equal(CATEGORIES.CATEGORY_ID))
			.leftOuterJoin("public.customers").on("contacts.contacts.company = public.customers.customer_id")
			.where(
				CONTACTS.CATEGORY_ID.equal(categoryId)
				.and(
					CONTACTS.STATUS.equal("N")
					.or(CONTACTS.STATUS.equal("M"))
				)
				.and(
					CONTACTS.CEMAIL.like(query)
					.or(CONTACTS.COMPANY.like(query))
					.or(CONTACTS.SEARCHFIELD.like(query))
				)
			)
			.orderBy(
				CONTACTS.LASTNAME.asc(),
				CONTACTS.FIRSTNAME.asc(),
				CONTACTS.COMPANY.asc()
			)
			.fetchInto(VContact.class);
	}
	
	public OContact selectById(Connection con, int contactId) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.select()
			.from(CONTACTS)
			.where(CONTACTS.CONTACT_ID.equal(contactId))
			.fetchOneInto(OContact.class);
	}
	
	public int insert(Connection con, OContact item) throws DAOException {
		DSLContext dsl = getDSL(con);
		ContactsRecord record = dsl.newRecord(CONTACTS, item);
		return dsl
			.insertInto(CONTACTS)
			.set(record)
			.execute();
	}
	
	public int update(Connection con, OContact item) throws DAOException {
		DSLContext dsl = getDSL(con);
		ContactsRecord record = dsl.newRecord(CONTACTS, item);
		return dsl
			.update(CONTACTS)
			.set(record)
			.where(
					CONTACTS.CONTACT_ID.equal(item.getContactId())
			)
			.execute();
	}
	
	public int updateRevision(Connection con, int contactId, RevisionInfo updateInfo) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(CONTACTS)
			.set(CONTACTS.LAST_MODIFIED, updateInfo.lastModified)
			.set(CONTACTS.UPDATE_DEVICE, updateInfo.lastDevice)
			.set(CONTACTS.UPDATE_USER, updateInfo.lastUser)
			.where(
				CONTACTS.CONTACT_ID.equal(contactId)
			)
			.execute();
	}
	
	public int updateStatus(Connection con, int contactId, String status, RevisionInfo updateInfo) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(CONTACTS)
			.set(CONTACTS.STATUS, status)
			.set(CONTACTS.LAST_MODIFIED, updateInfo.lastModified)
			.set(CONTACTS.UPDATE_DEVICE, updateInfo.lastDevice)
			.set(CONTACTS.UPDATE_USER, updateInfo.lastUser)
			.where(
				CONTACTS.CONTACT_ID.equal(contactId)
			)
			.execute();
	}
	
	public int logicDeleteById(Connection con, int contactId, RevisionInfo updateInfo) throws DAOException {
		DSLContext dsl = getDSL(con);
		return dsl
			.update(CONTACTS)
			.set(CONTACTS.STATUS, OContact.STATUS_DELETED)
			.set(CONTACTS.LAST_MODIFIED, updateInfo.lastModified)
			.set(CONTACTS.UPDATE_DEVICE, updateInfo.lastDevice)
			.set(CONTACTS.UPDATE_USER, updateInfo.lastUser)
			.where(
				CONTACTS.CONTACT_ID.equal(contactId)
			)
			.execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public byte[] readPhoto(Connection con, int contactId) throws IOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DSLContext dsl = getDSL(con);
			String sql = dsl
				.select(
					CONTACTS.PHOTO
				)
				.from(CONTACTS)
				.where(
						CONTACTS.CONTACT_ID.equal(contactId)
				)
				.getSQL();
			
			stmt = con.prepareStatement(sql);
			StatementUtils.setInt(stmt, 1, contactId);
			rs = stmt.executeQuery();
			if(!rs.next()) return null;
			
			LargeObjectManager lom = (con.unwrap(org.postgresql.PGConnection.class)).getLargeObjectAPI();
			LargeObject lo = lom.open(rs.getInt(1), LargeObjectManager.READ);
			byte buf[] = new byte[lo.size()];
			lo.read(buf, 0, lo.size());
			lo.close();
			return buf;
			
			//return (rs.next()) ? IOUtils.toByteArray(rs.getBinaryStream(1)) : null;
			
		} catch(SQLException ex) {
			throw new DAOException("Unable to read bytes", ex);
		} finally {
			RSUtils.closeQuietly(rs);
			StatementUtils.closeQuietly(stmt);
		}
	}
	
	
	/*
	public int writePhoto(Connection con, int contactId, byte[] bytes) throws IOException {
		PreparedStatement stmt = null;
		
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			String sql = "UPDATE contacts SET "
					+ "photo = ? "
					+ "WHERE (contact_id = ?)";
			
			stmt = con.prepareStatement(sql);
			StatementUtils.setBinaryStream(stmt, 1, bais, bytes.length);
			StatementUtils.setInt(stmt, 2, contactId);
			return stmt.executeUpdate();
			
		} catch(SQLException ex) {
			throw new DAOException("Unable to write bytes", ex);
		} finally {
			StatementUtils.closeQuietly(stmt);
		}
	}
	*/
}