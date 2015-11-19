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
Ext.define('Sonicle.webtop.contacts.view.Contact', {
	extend: 'WT.sdk.ModelView',
	requires: [
		'Ext.ux.form.trigger.Clear',
		'Sonicle.form.Separator',
		'Sonicle.form.field.IconComboBox',
		'Sonicle.form.field.Image',
		'Sonicle.webtop.core.store.Gender',
		'Sonicle.webtop.contacts.model.CategoryLkp'
	],
	
	dockableConfig: {
		title: '{contact.tit}',
		iconCls: 'wtcon-icon-contact-xs',
		width: 650,
		height: 500
	},
	confirm: 'yn',
	autoToolbar: false,
	model: 'Sonicle.webtop.contacts.model.Contact',
	/*
	viewModel: {
		formulas: {
			isDefault: WTF.checkboxBind('record', 'isDefault'),
			sync: WTF.checkboxBind('record', 'sync')
		}
	},
	*/
	
	profileId: null,
	
	initComponent: function() {
		var me = this, main, work, more, home, other, notes;
		Ext.apply(me, {
			tbar: [
				me.addAction('saveClose', {
					text: WT.res('act-saveClose.lbl'),
					iconCls: 'wt-icon-saveClose-xs',
					handler: function() {
						me.saveEvent();
					}
				}),
				'-',
				'->',
				WTF.localCombo('id', 'desc', {
					reference: 'fldowner',
					store: {
						autoLoad: true,
						model: 'WT.model.Simple',
						proxy: WTF.proxy(me.mys.ID, 'LookupCategoryRoots', 'roots')
					},
					fieldLabel: me.mys.res('contact.fld-owner.lbl'),
					labelWidth: 75,
					listeners: {
						select: function(s, rec) {
							//me.updateCalendarFilters();
							//me.updateActivityParams(true);
						}
					},
					value: me.profileId
				})
			]
		});
		me.callParent(arguments);
		
		main = Ext.create({
			itemId: 'main',
			xtype: 'panel',
			layout: 'column',
			title: me.mys.res('contact.main.tit'),
			bodyPadding: 5,
			defaults: {
				xtype: 'container',
				layout: 'anchor',
				modelValidation: true
			},
			items: [{
				defaults: {
					labelWidth: 120,
					width: 400
				},
				items: [Ext.create(
					WTF.lookupCombo('categoryId', 'name', {
						xtype: 'soiconcombo',
						bind: '{record.categoryId}',
						store: {
							autoLoad: true,
							model: 'Sonicle.webtop.contacts.model.CategoryLkp',
							proxy: WTF.proxy(me.mys.ID, 'LookupCategoryFolders', 'categorys')
						},
						iconClsField: 'colorCls',
						fieldLabel: me.mys.res('contact.fld-category.lbl')
					})
				), {
					xtype: 'textfield',
					bind: '{record.title}',
					fieldLabel: me.mys.res('contact.fld-title.lbl')
				}, {
					xtype: 'textfield',
					reference: 'fldfirstname',
					bind: '{record.firstName}',
					fieldLabel: me.mys.res('contact.fld-firstName.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.lastName}',
					fieldLabel: me.mys.res('contact.fld-lastName.lbl')
				}, Ext.create(
					WTF.remoteCombo('id', 'desc', {
						bind: '{record.company}',
						forceSelection: false,
						autoLoadOnValue: true,
						store: {
							model: 'WT.model.Simple',
							proxy: WTF.proxy(WT.ID, 'LookupCustomers', 'customers')
						},
						triggers: {
							clear: WTF.clearTrigger()
						},
						fieldLabel: me.mys.res('contact.fld-company.lbl')
					})
				), {
					xtype: 'textfield',
					bind: '{record.function}',
					fieldLabel: me.mys.res('contact.fld-function.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.department}',
					fieldLabel: me.mys.res('contact.fld-department.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.workEmail}',
					fieldLabel: me.mys.res('contact.fld-workEmail.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.workMobile}',
					fieldLabel: me.mys.res('contact.fld-workMobile.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.workTelephone}',
					fieldLabel: me.mys.res('contact.fld-workTelephone.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.homeEmail}',
					fieldLabel: me.mys.res('contact.fld-homeEmail.lbl')
				}, {
					xtype: 'textfield',
					bind: '{record.homeTelephone}',
					fieldLabel: me.mys.res('contact.fld-homeTelephone.lbl')
				}]
			}, {
				margin: '20 0 0 30',
				items: [{
					xtype: 'soimagefield',
					reference: 'fldphoto',
					bind: '{record.photo}',
					imageWidth: 150,
					imageHeight: 150,
					geometry: 'circle',
					imageUrl: WTF.processBinUrl(me.mys.ID, 'GetContactPhoto'),
					blankImageUrl: WTF.resourceUrl(me.mys.ID, 'contact-placeholder.png'),
					clearTriggerCls: 'wtcon-trash-trigger',
					uploadTriggerCls: 'wtcon-add-trigger',
					uploaderConfig: WTF.uploader(me.mys.ID, 'ContactPhoto', {
						mimeTypes: [
							{title: 'Image files', extensions: 'jpeg,jpg,png'}
						]
					}),
					listeners: {
						uploadstarted: function(up) {
							me.wait();
						},
						uploadcomplete: function(up) {
							me.unwait();
						},
						uploaderror: function(up) {
							me.unwait();
						},
						fileuploaded: function(up, file) {
							me.getModel().set('photo', file.uploadId);
						}
					}
				}]
			}]
		});
		
		work = Ext.create({
			itemId: 'work',
			xtype: 'panel',
			layout: 'anchor',
			modelValidation: true,
			title: me.mys.res('contact.work.tit'),
			bodyPadding: 5,
			defaults: {
				labelWidth: 120,
				width: 400
			},
			items: [{
				xtype: 'textfield',
				bind: '{record.workEmail}',
				fieldLabel: me.mys.res('contact.fld-email.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workAddress}',
				fieldLabel: me.mys.res('contact.fld-address.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workPostalCode}',
				fieldLabel: me.mys.res('contact.fld-postalCode.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workCity}',
				fieldLabel: me.mys.res('contact.fld-city.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workState}',
				fieldLabel: me.mys.res('contact.fld-state.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workCountry}',
				fieldLabel: me.mys.res('contact.fld-country.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workTelephone}',
				fieldLabel: me.mys.res('contact.fld-telephone.lbl')	
			}, {
				xtype: 'textfield',
				bind: '{record.workTelephone2}',
				fieldLabel: me.mys.res('contact.fld-telephone2.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workMobile}',
				fieldLabel: me.mys.res('contact.fld-mobile.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workFax}',
				fieldLabel: me.mys.res('contact.fld-fax.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workPager}',
				fieldLabel: me.mys.res('contact.fld-pager.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.workInstantMsg}',
				fieldLabel: me.mys.res('contact.fld-instantMsg.lbl')
			}]
		});
		
		home = Ext.create({
			itemId: 'home',
			xtype: 'panel',
			layout: 'anchor',
			modelValidation: true,
			title: me.mys.res('contact.home.tit'),
			bodyPadding: 5,
			defaults: {
				labelWidth: 120,
				width: 400
			},
			items: [{
				xtype: 'textfield',
				bind: '{record.homeEmail}',
				fieldLabel: me.mys.res('contact.fld-email.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeAddress}',
				fieldLabel: me.mys.res('contact.fld-address.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homePostalCode}',
				fieldLabel: me.mys.res('contact.fld-postalCode.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeCity}',
				fieldLabel: me.mys.res('contact.fld-city.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeState}',
				fieldLabel: me.mys.res('contact.fld-state.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeCountry}',
				fieldLabel: me.mys.res('contact.fld-country.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeTelephone}',
				fieldLabel: me.mys.res('contact.fld-telephone.lbl')	
			}, {
				xtype: 'textfield',
				bind: '{record.homeMobile}',
				fieldLabel: me.mys.res('contact.fld-mobile.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeFax}',
				fieldLabel: me.mys.res('contact.fld-fax.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homePager}',
				fieldLabel: me.mys.res('contact.fld-pager.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.homeInstantMsg}',
				fieldLabel: me.mys.res('contact.fld-instantMsg.lbl')
			}]
		});
		
		other = Ext.create({
			itemId: 'other',
			xtype: 'panel',
			layout: 'anchor',
			modelValidation: true,
			title: me.mys.res('contact.other.tit'),
			bodyPadding: 5,
			defaults: {
				labelWidth: 120,
				width: 400
			},
			items: [{
				xtype: 'textfield',
				bind: '{record.otherEmail}',
				fieldLabel: me.mys.res('contact.fld-email.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherAddress}',
				fieldLabel: me.mys.res('contact.fld-address.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherPostalCode}',
				fieldLabel: me.mys.res('contact.fld-postalCode.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherCity}',
				fieldLabel: me.mys.res('contact.fld-city.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherState}',
				fieldLabel: me.mys.res('contact.fld-state.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherCountry}',
				fieldLabel: me.mys.res('contact.fld-country.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.otherInstantMsg}',
				fieldLabel: me.mys.res('contact.fld-instantMsg.lbl')
			}]
		});
		
		more = Ext.create({
			itemId: 'more',
			xtype: 'panel',
			layout: 'anchor',
			modelValidation: true,
			title: me.mys.res('contact.more.tit'),
			bodyPadding: 5,
			defaults: {
				labelWidth: 120,
				width: 400
			},
			items: [{
				xtype: 'textfield',
				bind: '{record.nickname}',
				fieldLabel: me.mys.res('contact.fld-nickname.lbl')
			}, Ext.create(
				WTF.remoteCombo('id', 'desc', {
					bind: '{record.gender}',
					autoLoadOnValue: true,
					store: Ext.create('Sonicle.webtop.core.store.Gender', {
						autoLoad: true
					}),
					triggers: {
						clear: WTF.clearTrigger()
					},
					fieldLabel: me.mys.res('contact.fld-gender.lbl')
				})
			), {
				xtype: 'textfield',
				bind: '{record.manager}',
				fieldLabel: me.mys.res('contact.fld-manager.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.assistant}',
				fieldLabel: me.mys.res('contact.fld-assistant.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.assistantTelephone}',
				fieldLabel: me.mys.res('contact.fld-assistantTelephone.lbl')
			}, {
				xtype: 'textfield',
				bind: '{record.url}',
				fieldLabel: me.mys.res('contact.fld-url.lbl')
			}, {
				xtype: 'formseparator'
			}, {
				xtype: 'textfield',
				bind: '{record.partner}',
				fieldLabel: me.mys.res('contact.fld-partner.lbl')
			}, {
				xtype: 'datefield',
				bind: '{record.birthday}',
				fieldLabel: me.mys.res('contact.fld-birthday.lbl')
			}, {
				xtype: 'datefield',
				bind: '{record.anniversary}',
				fieldLabel: me.mys.res('contact.fld-anniversary.lbl')
			}]
		});
		
		notes = Ext.create({
			itemId: 'notes',
			xtype: 'panel',
			layout: 'fit',
			modelValidation: true,
			title: me.mys.res('contact.notes.tit'),
			bodyPadding: 5,
			items: [{
				xtype: 'textarea',
				bind: '{record.notes}'
			}]
		});
		
		me.add({
			region: 'center',
			xtype: 'tabpanel',
			defaults: {
				
			},
			items: [main, work, home, other, more, notes]
		});
		
		me.on('viewload', me.onViewLoad);
	},
	
	onViewLoad: function(s, success) {
		if(!success) return;
		var me = this,
				//model = me.getModel(),
				owner = me.lref('fldowner');
		
		if(me.isMode(me.MODE_NEW)) {
			owner.setDisabled(false);
		} else if(me.isMode(me.MODE_EDIT)) {
			owner.setDisabled(true);
		}
		
		me.lref('fldfirstname').focus(true);
	}
});