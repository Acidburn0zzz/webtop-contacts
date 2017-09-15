/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.contacts.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Contacts extends org.jooq.impl.TableImpl<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord> {

	private static final long serialVersionUID = -1494853329;

	/**
	 * The reference instance of <code>contacts.contacts</code>
	 */
	public static final com.sonicle.webtop.contacts.jooq.tables.Contacts CONTACTS = new com.sonicle.webtop.contacts.jooq.tables.Contacts();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord> getRecordType() {
		return com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord.class;
	}

	/**
	 * The column <code>contacts.contacts.contact_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.Integer> CONTACT_ID = createField("contact_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>contacts.contacts.category_id</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.Integer> CATEGORY_ID = createField("category_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>contacts.contacts.revision_status</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> REVISION_STATUS = createField("revision_status", org.jooq.impl.SQLDataType.VARCHAR.length(1), this, "");

	/**
	 * The column <code>contacts.contacts.revision_timestamp</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, org.joda.time.DateTime> REVISION_TIMESTAMP = createField("revision_timestamp", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new com.sonicle.webtop.core.jooq.DateTimeConverter());

	/**
	 * The column <code>contacts.contacts.public_uid</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> PUBLIC_UID = createField("public_uid", org.jooq.impl.SQLDataType.VARCHAR.length(36), this, "");

	/**
	 * The column <code>contacts.contacts.is_list</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.Boolean> IS_LIST = createField("is_list", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * The column <code>contacts.contacts.searchfield</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> SEARCHFIELD = createField("searchfield", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>contacts.contacts.title</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.firstname</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> FIRSTNAME = createField("firstname", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

	/**
	 * The column <code>contacts.contacts.lastname</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> LASTNAME = createField("lastname", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

	/**
	 * The column <code>contacts.contacts.nickname</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> NICKNAME = createField("nickname", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

	/**
	 * The column <code>contacts.contacts.gender</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> GENDER = createField("gender", org.jooq.impl.SQLDataType.VARCHAR.length(6), this, "");

	/**
	 * The column <code>contacts.contacts.company</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> COMPANY = createField("company", org.jooq.impl.SQLDataType.VARCHAR.length(60), this, "");

	/**
	 * The column <code>contacts.contacts.function</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> FUNCTION = createField("function", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_address</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_ADDRESS = createField("work_address", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>contacts.contacts.work_city</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_CITY = createField("work_city", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_state</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_STATE = createField("work_state", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.work_postalcode</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_POSTALCODE = createField("work_postalcode", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

	/**
	 * The column <code>contacts.contacts.work_country</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_COUNTRY = createField("work_country", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.work_telephone</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_TELEPHONE = createField("work_telephone", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_telephone2</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_TELEPHONE2 = createField("work_telephone2", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_fax</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_FAX = createField("work_fax", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_mobile</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_MOBILE = createField("work_mobile", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_pager</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_PAGER = createField("work_pager", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.work_email</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_EMAIL = createField("work_email", org.jooq.impl.SQLDataType.VARCHAR.length(320), this, "");

	/**
	 * The column <code>contacts.contacts.work_im</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> WORK_IM = createField("work_im", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.assistant</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> ASSISTANT = createField("assistant", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.assistant_telephone</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> ASSISTANT_TELEPHONE = createField("assistant_telephone", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.department</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> DEPARTMENT = createField("department", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.manager</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> MANAGER = createField("manager", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.home_address</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_ADDRESS = createField("home_address", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>contacts.contacts.home_city</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_CITY = createField("home_city", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_state</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_STATE = createField("home_state", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.home_postalcode</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_POSTALCODE = createField("home_postalcode", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

	/**
	 * The column <code>contacts.contacts.home_country</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_COUNTRY = createField("home_country", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.home_telephone</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_TELEPHONE = createField("home_telephone", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_telephone2</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_TELEPHONE2 = createField("home_telephone2", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_fax</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_FAX = createField("home_fax", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_mobile</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_MOBILE = createField("home_mobile", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_pager</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_PAGER = createField("home_pager", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.home_email</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_EMAIL = createField("home_email", org.jooq.impl.SQLDataType.VARCHAR.length(320), this, "");

	/**
	 * The column <code>contacts.contacts.home_im</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HOME_IM = createField("home_im", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.partner</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> PARTNER = createField("partner", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.birthday</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, org.joda.time.LocalDate> BIRTHDAY = createField("birthday", org.jooq.impl.SQLDataType.DATE, this, "", new com.sonicle.webtop.core.jooq.LocalDateConverter());

	/**
	 * The column <code>contacts.contacts.anniversary</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, org.joda.time.LocalDate> ANNIVERSARY = createField("anniversary", org.jooq.impl.SQLDataType.DATE, this, "", new com.sonicle.webtop.core.jooq.LocalDateConverter());

	/**
	 * The column <code>contacts.contacts.other_address</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_ADDRESS = createField("other_address", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>contacts.contacts.other_city</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_CITY = createField("other_city", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>contacts.contacts.other_state</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_STATE = createField("other_state", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.other_postalcode</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_POSTALCODE = createField("other_postalcode", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

	/**
	 * The column <code>contacts.contacts.other_country</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_COUNTRY = createField("other_country", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "");

	/**
	 * The column <code>contacts.contacts.other_email</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_EMAIL = createField("other_email", org.jooq.impl.SQLDataType.VARCHAR.length(320), this, "");

	/**
	 * The column <code>contacts.contacts.other_im</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> OTHER_IM = createField("other_im", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>contacts.contacts.url</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * The column <code>contacts.contacts.notes</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> NOTES = createField("notes", org.jooq.impl.SQLDataType.VARCHAR.length(2000), this, "");

	/**
	 * The column <code>contacts.contacts.revision_sequence</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.Integer> REVISION_SEQUENCE = createField("revision_sequence", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>contacts.contacts.href</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> HREF = createField("href", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * The column <code>contacts.contacts.etag</code>.
	 */
	public final org.jooq.TableField<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord, java.lang.String> ETAG = createField("etag", org.jooq.impl.SQLDataType.VARCHAR.length(2048), this, "");

	/**
	 * Create a <code>contacts.contacts</code> table reference
	 */
	public Contacts() {
		this("contacts", null);
	}

	/**
	 * Create an aliased <code>contacts.contacts</code> table reference
	 */
	public Contacts(java.lang.String alias) {
		this(alias, com.sonicle.webtop.contacts.jooq.tables.Contacts.CONTACTS);
	}

	private Contacts(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Contacts(java.lang.String alias, org.jooq.Table<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.sonicle.webtop.contacts.jooq.Contacts.CONTACTS, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord> getPrimaryKey() {
		return com.sonicle.webtop.contacts.jooq.Keys.CONTACTS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.contacts.jooq.tables.records.ContactsRecord>>asList(com.sonicle.webtop.contacts.jooq.Keys.CONTACTS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.sonicle.webtop.contacts.jooq.tables.Contacts as(java.lang.String alias) {
		return new com.sonicle.webtop.contacts.jooq.tables.Contacts(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.sonicle.webtop.contacts.jooq.tables.Contacts rename(java.lang.String name) {
		return new com.sonicle.webtop.contacts.jooq.tables.Contacts(name, null);
	}
}
