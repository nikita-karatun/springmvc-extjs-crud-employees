Ext.onReady(function()
{
	Ext.define('TestTask.model.Employee',
	{
		extend : 'Ext.data.Model',
		fields : [
		{
			name : 'id',
			type : 'int'
		},
		{
			name : 'firstName',
			type : 'string'
		},
		{
			name : 'lastName',
			type : 'string'
		},
		{
			name : 'secondName',
			type : 'string'
		},
		{
			name : 'age',
			type : 'int'
		},
		{
			name : 'experience',
			type : 'string'
		},
		{
			name : 'description',
			type : 'string'
		} ]
	});
	
	var employeeStoreProxy = {
			type : 'ajax',
			url : '/SpringMVC-ExtJS-CRUD/api/employee/loadEmployees',
			reader :
			{
				type : 'json',
				root : 'employees'
			}
		};

	Ext.define('TestTask.store.Employees',
	{
		extend : 'Ext.data.Store',
		storeId : 'employeeStore',
		model : 'TestTask.model.Employee',
		fields : [ 'id', 'firstName', 'lastName', 'secondName', 'age', 'experience', 'description' ],
		proxy : employeeStoreProxy,
		autoLoad : true
	});

	var headers =
	{
		firstName : 'First Name',
		lastName : 'Last Name',
		secondName : 'Second Name',
		age : 'Age',
		experience : 'Experience',
		description : 'Description'
	};

	var defaultColumnWidth = 110;

	var confirmEmployeeDelete = function(rs)
	{
		Ext.Msg.confirm('Remove Employee', 'Are you sure<br/>you want to delete?', function(button)
		{
			if (button == 'yes')
			{
				var employee = rs[0].getData();
				Ext.Ajax.request(
				{
					url : '/SpringMVC-ExtJS-CRUD/api/employee/delete',
					method : 'POST',
					jsonData : employee,
					success : function(response)
					{
						var grid = Ext.ComponentQuery.query('employeeslist')[0];
						grid.getStore().load();
					}
				});
			}
		});
	};

	var deleteEmployee = function(v, m, r)
	{
		var id = Ext.id();
		Ext.defer(function()
		{
			Ext.widget('image',
			{
				renderTo : id,
				name : 'delete',
				src : 'resources/images/book_delete.png',
				listeners :
				{
					afterrender : function(me)
					{
						me.getEl().on('click', function()
						{
							var grid = Ext.ComponentQuery.query('employeeslist')[0];
							if (grid)
							{
								var sm = grid.getSelectionModel();
								var rs = sm.getSelection();
								if (!rs.length)
								{
									Ext.Msg.alert('Info', 'No Employee Selected');
									return;
								}
								confirmEmployeeDelete(rs);
							}
						});
					}
				}
			});
		}, 50);
		return Ext.String.format('<div id="{0}"></div>', id);
	};

	Ext.define('TestTask.view.EmployeesList',
	{
		extend : 'Ext.grid.Panel',
		alias : 'widget.employeeslist',
		title : 'Employees List',
		store : 'Employees',
		initComponent : function()
		{
			this.tbar = [
			{
				text : 'Add Employee',
				action : 'add'
			},
			{
				text : 'Search',
				action : 'search'
			},
			{
				text : 'Refresh',
				action : 'refresh'
			}];
			this.columns = [
			{
				header : headers.firstName,
				dataIndex : 'firstName',
				flex : 1
			},
			{
				header : headers.lastName,
				dataIndex : 'lastName'
			},
			{
				header : headers.secondName,
				dataIndex : 'secondName',
				width : defaultColumnWidth
			},
			{
				header : headers.age,
				dataIndex : 'age',
				width : defaultColumnWidth
			},
			{
				header : headers.experience,
				dataIndex : 'experience',
				width : defaultColumnWidth
			},
			{
				header : headers.description,
				dataIndex : 'description',
				width : defaultColumnWidth
			},
			{
				header : 'Delete employee',
				width : defaultColumnWidth,
				renderer : deleteEmployee
			} ];
			this.callParent(arguments);
		}
	});

	Ext.define('TestTask.view.EmployeesForm',
	{
		extend : 'Ext.window.Window',
		alias : 'widget.employeesform',
		title : 'Add Employee',
		width : 350,
		layout : 'fit',
		resizable : false,
		closeAction : 'hide',
		modal : true,
		config :
		{
			recordIndex : 0,
			action : ''
		},
		items : [
		{
			xtype : 'form',
			layout : 'anchor',
			bodyStyle :
			{
				background : 'none',
				padding : '10px',
				border : '0'
			},
			defaults :
			{
				xtype : 'textfield',
				anchor : '100%'
			},
			items : [
			{
				name : 'id',
				hidden : true
			},
			{
				name : 'firstName',
				fieldLabel : headers.firstName
			},
			{
				name : 'lastName',
				fieldLabel : headers.lastName
			},
			{
				name : 'secondName',
				fieldLabel : headers.secondName
			},
			{
				name : 'age',
				fieldLabel : headers.age,
				regex : /^\d{1,3}$/
			},
			{
				name : 'experience',
				fieldLabel : headers.experience
			},
			{
				name : 'description',
				fieldLabel : headers.description
			} ]
		} ],
		buttons : [
		{
			text : 'OK',
			action : 'add'
		},
		{
			text : 'Reset',
			handler : function()
			{
				this.up('window').down('form').getForm().reset();
			}
		},
		{
			text : 'Cancel',
			handler : function()
			{
				this.up('window').close();
			}
		} ]
	});

	var reloadStoreWithFilter = function(store, filter)
	{
		var cProxy =
		{
			type : 'ajax',
			url : '/SpringMVC-ExtJS-CRUD/api/employee/loadEmployeesWithFilter',
			extraParams :
			{
				employee : filter
			},
			reader :
			{
				type : 'json',
				root : 'employees'
			}
		};
		store.setProxy(cProxy);
		store.load();
	};

	Ext.define('TestTask.controller.Employees',
	{
		extend : 'Ext.app.Controller',
		stores : [ 'Employees' ],
		views : [ 'EmployeesList', 'EmployeesForm' ],
		refs : [
		{
			ref : 'formWindow',
			xtype : 'employeesform',
			selector : 'employeesform',
			autoCreate : true
		} ],
		init : function()
		{
			this.control(
			{
				'employeeslist > toolbar > button[action=add]' :
				{
					click : this.showAddForm
				},
				'employeeslist > toolbar > button[action=search]' :
				{
					click : this.showSearchForm
				},
				'employeeslist > toolbar > button[action=refresh]' :
				{
					click : this.refresh
				},
				'employeeslist' :
				{
					itemdblclick : this.onRowdblclick
				},
				'employeesform button[action=add]' :
				{
					click : this.doAddEmployee
				}
			});
		},
		onRowdblclick : function(me, record, item, index)
		{
			var win = this.getFormWindow();
			win.setTitle('Edit Employee');
			win.setAction('edit');
			win.setRecordIndex(index);
			win.down('form').getForm().setValues(record.getData());
			win.show();
		},
		showAddForm : function()
		{
			var win = this.getFormWindow();
			win.setTitle('Add Employee');
			win.setAction('add');
			win.down('form').getForm().reset();
			win.show();
		},
		refresh : function()
		{
			var store = this.getEmployeesStore();
			store.setProxy(employeeStoreProxy);
			store.load();
		},
		doAddEmployee : function()
		{
			var win = this.getFormWindow();

			if (win.down('form').getForm().isValid())
			{
				var values = win.down('form').getValues();
				var store = this.getEmployeesStore();
				var action = win.getAction();
				console.log(action);
				if (action == 'search')
				{
					reloadStoreWithFilter(store, values);
				} else
				{
					var url = '';
					if (action == 'edit')
					{
						url = '/SpringMVC-ExtJS-CRUD/api/employee/updateEmployee';
					} else
					{
						url = '/SpringMVC-ExtJS-CRUD/api/employee/save';
					}
					console.log(values.id == '');
					var employee = values;
					if (employee.id == '')
						employee.id = -1;
					Ext.Ajax.request(
					{
						url : url,
						method : 'POST',
						jsonData : employee,
						success : function(response)
						{
							store.load();
						}
					});
				}
				win.close();
			} else
			{
				Ext.Msg.alert('Error', 'Form validation failed');
			}
		},
		showSearchForm : function()
		{
			var win = this.getFormWindow();
			win.setTitle('Search Employee');
			win.setAction('search');
			win.down('form').getForm().reset();
			win.show();
		}
	});

	Ext.application(
	{
		name : 'TestTask',
		controllers : [ 'Employees' ],
		launch : function()
		{
			Ext.widget('employeeslist',
			{
				width : 860,
				height : 400,
				renderTo : 'output'
			});
		}
	});
});