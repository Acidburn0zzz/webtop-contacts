/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.contacts.jooq.tables.pojos;

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
public class Categories implements java.io.Serializable {

	private static final long serialVersionUID = 2047349173;

	private java.lang.Integer categoryId;
	private java.lang.String  domainId;
	private java.lang.String  userId;
	private java.lang.Boolean builtIn;
	private java.lang.String  name;
	private java.lang.String  description;
	private java.lang.String  color;
	private java.lang.String  sync;
	private java.lang.Boolean isDefault;

	public Categories() {}

	public Categories(
		java.lang.Integer categoryId,
		java.lang.String  domainId,
		java.lang.String  userId,
		java.lang.Boolean builtIn,
		java.lang.String  name,
		java.lang.String  description,
		java.lang.String  color,
		java.lang.String  sync,
		java.lang.Boolean isDefault
	) {
		this.categoryId = categoryId;
		this.domainId = domainId;
		this.userId = userId;
		this.builtIn = builtIn;
		this.name = name;
		this.description = description;
		this.color = color;
		this.sync = sync;
		this.isDefault = isDefault;
	}

	public java.lang.Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(java.lang.Integer categoryId) {
		this.categoryId = categoryId;
	}

	public java.lang.String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(java.lang.String domainId) {
		this.domainId = domainId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.Boolean getBuiltIn() {
		return this.builtIn;
	}

	public void setBuiltIn(java.lang.Boolean builtIn) {
		this.builtIn = builtIn;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getColor() {
		return this.color;
	}

	public void setColor(java.lang.String color) {
		this.color = color;
	}

	public java.lang.String getSync() {
		return this.sync;
	}

	public void setSync(java.lang.String sync) {
		this.sync = sync;
	}

	public java.lang.Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(java.lang.Boolean isDefault) {
		this.isDefault = isDefault;
	}
}