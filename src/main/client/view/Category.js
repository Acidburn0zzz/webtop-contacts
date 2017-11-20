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
Ext.define('Sonicle.webtop.contacts.view.Category', {
	extend: 'WTA.sdk.ModelView',
	requires: [
		'Sonicle.form.field.Palette',
		'Sonicle.form.RadioGroup',
		'Sonicle.webtop.contacts.store.Provider',
		'Sonicle.webtop.contacts.store.Sync'
	],
	
	dockableConfig: {
		title: '{category.tit}',
		iconCls: 'wtcon-icon-category-xs',
		width: 380,
		height: 340
	},
	fieldTitle: 'name',
	modelName: 'Sonicle.webtop.contacts.model.Category',
	
	constructor: function(cfg) {
		var me = this;
		me.callParent([cfg]);
		
		WTU.applyFormulas(me.getVM(), {
			foIsRemote: WTF.foGetFn('record', 'provider', function(v) {
				return Sonicle.webtop.contacts.view.Category.isRemote(v);
			}),
			isDefault: WTF.checkboxBind('record', 'isDefault')
		});
	},
	
	initComponent: function() {
		var me = this;
		me.callParent(arguments);
		
		me.add({
			region: 'center',
			xtype: 'wttabpanel',
			items: [{
				xtype: 'wtform',
				title: me.mys.res('category.main.tit'),
				modelValidation: true,
				defaults: {
					labelWidth: 110
				},
				items: [
					WTF.lookupCombo('id', 'desc', {
						bind: '{record.provider}',
						disabled: true,
						store: Ext.create('Sonicle.webtop.contacts.store.Provider', {
							autoLoad: true
						}),
						fieldLabel: me.mys.res('category.fld-provider.lbl'),
						width: 250
					}),
					{
						xtype: 'textfield',
						reference: 'fldname',
						bind: '{record.name}',
						fieldLabel: me.mys.res('category.fld-name.lbl'),
						anchor: '100%'
					}, {
						xtype: 'checkbox',
						bind: {
							value: '{isDefault}',
							disabled: '{foIsRemote}'
						},
						hideEmptyLabel: false,
						boxLabel: me.mys.res('category.fld-default.lbl')
					}, {
						xtype: 'textareafield',
						bind: '{record.description}',
						fieldLabel: me.mys.res('category.fld-description.lbl'),
						anchor: '100%'
					}, {
						xtype: 'sopalettefield',
						bind: '{record.color}',
						colors: WT.getColorPalette(),
						fieldLabel: me.mys.res('category.fld-color.lbl'),
						width: 210
					},
					WTF.lookupCombo('id', 'desc', {
						reference: 'fldsync',
						bind: '{record.sync}',
						store: Ext.create('Sonicle.webtop.contacts.store.Sync', {
							autoLoad: true
						}),
						fieldLabel: me.mys.res('category.fld-sync.lbl'),
						width: 250
					})
					
				]
			}, {
				xtype: 'wtform',
				title: me.mys.res('category.remote.tit'),
				bind: {
					disabled: '{!foIsRemote}'
				},
				modelValidation: true,
				defaults: {
					labelWidth: 90
				},
				items: [{
					xtype: 'textfield',
					bind: '{record.remoteUrl}',
					selectOnFocus: true,
					fieldLabel: me.mys.res('category.fld-remoteUrl.lbl'),
					anchor: '100%'
				}, {
					xtype: 'sofakeinput' // Disable Chrome autofill
				}, {
					xtype: 'sofakeinput', // Disable Chrome autofill
					type: 'password'
				}, {
					xtype: 'textfield',
					bind: '{record.remoteUsername}',
					plugins: 'sonoautocomplete',
					fieldLabel: me.mys.res('category.fld-remoteUsername.lbl'),
					width: 280
				}, {
					xtype: 'textfield',
					bind: '{record.remotePassword}',
					inputType: 'password',
					plugins: 'sonoautocomplete',
					fieldLabel: me.mys.res('category.fld-remotePassword.lbl'),
					width: 280
				}],
				tbar: ['->', {
					xtype: 'splitbutton',
					tooltip: me.mys.res('category.btn-syncnow.tip'),
					iconCls: 'wt-icon-refresh-xs',
					handler: function() {
						me.syncRemoteCategoryUI(me.getModel().get('categoryId'), false);
					},
					menu: {
						items: [{
							itemId: 'partial',
							text: me.mys.res('category.btn-syncnow.partial.lbl')
						}, {
							itemId: 'full',
							text: me.mys.res('category.btn-syncnow.full.lbl')
						}],
						listeners: {
							click: function(s, itm) {
								me.syncRemoteCategoryUI(me.getModel().get('categoryId'), itm.getItemId() === 'full');
							}
						}
					}
				}]
			}]
		});
		me.on('viewload', me.onViewLoad);
	},
	
	onViewLoad: function(s, success) {
		if(!success) return;
		var me = this;
		me.updateSyncFilters();
		me.lref('fldname').focus(true);
	},
	
	updateSyncFilters: function() {
		var me = this,
				isRem = me.self.isRemote(me.getModel().get('provider')),
				sto = me.lref('fldsync').getStore();
		sto.clearFilter();
		sto.addFilter([{
			filterFn: function(rec) {
				return (isRem && (rec.getId() === 'W')) ? false : true;
			}
		}]);
	},
	
	syncRemoteCategoryUI: function(categoryId, full) {
		var me = this;
		WT.confirm(me.mys.res('category.confirm.remotesync'), function(bid) {
			if (bid === 'yes') {
				me.mys.syncRemoteCategory(categoryId, full, {
					callback: function(success, json) {
						if (success) {
							me.closeView(true);
						} else {
							WT.error(json.message);
						}
					}
				});
			}
		}, this);
	},
	
	statics: {
		isRemote: function(provider) {
			return provider === 'carddav';
		}
	}
});
