/* 
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
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle[at]sonicle[dot]com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2014 Sonicle S.r.l.".
 */
package com.sonicle.webtop.contacts.io.input;

import com.sonicle.webtop.contacts.io.ContactInput;
import com.sonicle.webtop.contacts.io.VCardInput;
import com.sonicle.webtop.core.io.BeanHandler;
import com.sonicle.webtop.core.io.input.FileReaderException;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.LogEntries;
import com.sonicle.webtop.core.util.LogEntry;
import com.sonicle.webtop.core.util.MessageLogEntry;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author malbinola
 */
public class ContactVCardFileReader implements ContactFileReader {
	
	@Override
	public void readContacts(File file, BeanHandler beanHandler) throws IOException, FileReaderException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			readContacts(fis, beanHandler);
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}
	
	public void readContacts(InputStream is, BeanHandler beanHandler) throws IOException, FileReaderException {
		// See https://tools.ietf.org/html/rfc6350
		// See http://www.w3.org/TR/vcard-rdf/
		
		final VCardInput input = new VCardInput();
		List<VCard> vcs = Ezvcard.parse(is).all();
		for (VCard vc : vcs) {
			LogEntries log = new LogEntries();
			ContactReadResult result = null;
			try {
				LogEntries vclog = new LogEntries();
				result = readVCard(input, vclog, vc);
				if (!vclog.isEmpty()) {
					log.addMaster(new MessageLogEntry(LogEntry.Level.WARN, "VCARD [{0}]", vc.getUid()));
					log.addAll(vclog);
				}
			} catch(Throwable t) {
				log.addMaster(new MessageLogEntry(LogEntry.Level.ERROR, "VCARD [{0}]. Reason: {1}", vc.getUid(), t.getMessage()));
			} finally {
				try {
					beanHandler.handle(result, log);
				} catch(Exception ex) {
					throw new FileReaderException(ex);
				}
			}
		}
	}
	
	public ContactReadResult readVCard(VCardInput input, LogEntries log, VCard vCard) throws WTException {
		ContactInput ci = input.fromVCard(vCard, log);
		return new ContactReadResult(ci.contact, ci.picture);
	}
}
