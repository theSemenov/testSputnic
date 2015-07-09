/**
 * 
 */
var host = 'http://localhost';
var port = '8080';
var rest = 'humanmanager/rest/actions';
var actionListAdr = host + ':' + port + '/' + rest + '/' + 'showall';
var actionAddAdr = host + ':' + port + '/' + rest + '/' + 'save';
var actionUpdateAdr = host + ':' + port + '/' + rest + '/' + 'update';
var actionRemoveAdr = host + ':' + port + '/' + rest + '/' + 'remove';
var grid;
var bAdd;
var bEdit;
var bRemove;
var bRefresh;
var bAction;
var bСhancelAction;
var action;
var editId;

jQuery('document').ready(function() {
	init()
});

function init() {
	grid = jQuery('#jqGrid');
	bAdd = jQuery('#addBtn');
	bEdit = jQuery('#editBtn');
	bRemove = jQuery('#removeBtn');
	bRefresh = jQuery('#refreshBtn');
	bAction = jQuery('#actionBtn');
	bСhancelAction = jQuery('#chancelActionBtn');
	jQuery.datepicker.setDefaults( $.datepicker.regional['ru'] );
	jQuery('#bdField').datepicker();
	jQuery('#form').hide();
	var col = [ {
		label : '#',
		name : 'id',
		width : 75,
		sorttype : 'number',
		sortable : 'true',
		key : true
	}, {
		label : 'Фамилия',
		name : 'sname',
		width : 75,
		sorttype : 'text',
		sortable : 'true'
	}, {
		label : 'Имя',
		name : 'fname',
		width : 150,
		sorttype : 'text',
		sortable : 'true'
	}, {
		label : 'Отчество',
		name : 'mname',
		width : 150,
		sorttype : 'number',
		sortable : 'true'
	}, {
		label : 'Дата рождения',
		name : 'birthDate',
		width : 150,
		sorttype : 'date',
		sortable : 'true',
		formatter : 'date',
		formatoptions : {
			srcformat : 'm.d.Y',
			newformat : 'm.d.Y'
		},
	} ];

	grid.jqGrid({
		url : actionListAdr,
		mtype : 'GET',
		datatype : 'json',
		colModel : col,
		viewrecords : true,
		height : 'auto',
		autowidth : true,
		shrinkToFit : true,
	});

	registerAddListner();
	registerUpdateListner();
	registerRemoveListner();
	registerRefreshListner();
	registerActionListner();
	registerChancelActionListner();
}
function registerActionListner() {
	bAction.on('click', function() {
		if (action == 'save') {
			url = actionAddAdr;
			var data = getData();
		} else if (action == 'update') {
			url = actionUpdateAdr;
			var data = getData(editId);
		} else {
			return;
		}
		jQuery.ajax({
			url : url,
			cache : true,
			type : 'POST',
			crossDomain : false,
			Accept : 'application/json',
			contentType : 'application/json',
			/*beforeSend: function (request)
	        {
	                request.setRequestHeader("X-Requested-With", 'XMLHttpRequest');
	        },*/
			headers : {
				'Access-Control-Request-Headers' : 'x-requested-with'
			},
			data : data,
			success : function(ret) {
				grid.trigger('reloadGrid');
				bAction.removeClass( 'pure-button-primary' ).addClass('button-success');
				jQuery('#form').hide('slow');
				jQuery('#mes').html('Ok');
				jQuery('#mes').css('color', 'rgb(28, 184, 65)');
				jQuery('#mes').show('fast');
			},
			error : function(ret) {
				bAction.removeClass( 'pure-button-primary button-success' ).addClass('button-error');
				jQuery('#mes').html(ret.responseText);
				jQuery('#mes').css('color', 'rgb(202, 60, 60)');
				jQuery('#mes').show('slow');
			}
		});
	})
}
function registerChancelActionListner(){
	bСhancelAction.on('click', function() {
		action = '';
		jQuery('#form').hide('fast');
		jQuery('#actionName').html('');
		jQuery('#actionBtn').html('');
		jQuery('#fnameField').val('');
		jQuery('#snameField').val('');
		jQuery('#mnameField').val('');
		jQuery('#bdField').val('');
	});
}
function registerRefreshListner() {
	bRefresh.on('click', function() {
		grid.trigger('reloadGrid');
		
	});
}
function registerAddListner() {
	bAdd.on('click', function() {
		action = 'save';
		bAction.removeClass('button-success button-error').addClass('pure-button-primary');
		jQuery('#mes').html("");
		jQuery('#mes').hide('show');
		jQuery('#form').hide('slow');
		jQuery('#actionName').html('Создать человека');
		jQuery('#actionName').css('color', 'rgb(28, 184, 65)');
		jQuery('#actionBtn').html('сохранить');
		jQuery('#fnameField').val('');
		jQuery('#snameField').val('');
		jQuery('#mnameField').val('');
		jQuery('#bdField').val('');
		jQuery('#form').show('slow');
	});
}

function registerUpdateListner() {
	bEdit.on('click', function() {
		action = 'update';
		bAction.removeClass('button-success button-error').addClass('pure-button-primary');
		jQuery('#form').hide('slow');
		jQuery('#mes').html("");
		jQuery('#mes').hide('show');
		jQuery('#actionName').html('Редактировать человека');
		jQuery('#actionName').css('color', 'rgb(223, 117, 20)');
		jQuery('#actionBtn').html('обновить');
		var rowid = grid.getGridParam('selrow');
		if(rowid != null) {
			 var rowData = grid.jqGrid ('getRowData', rowid);
			jQuery('#fnameField').val(rowData.fname);
			jQuery('#snameField').val(rowData.sname);
			jQuery('#mnameField').val(rowData.mname);
			jQuery('#bdField').val(rowData.birthDate);
			jQuery('#form').show('slow');
			editId = rowid;
		} else {
			alert('Выбирите запись для редактирования');
		}
	});
}

function registerRemoveListner() {
	bRemove.on('click', function() {
		var rowid = grid.getGridParam('selrow');
		if (rowid != null) {
			removeAction(rowid);
		}else {
			alert('Выбирите запись для редактирования');
		}
	});

}

function removeAction(id) {
	var url = actionRemoveAdr + '/' + id;
	jQuery.ajax({
		url : url,
		crossDomain : true,
		type : 'GET',
		headers : {
			'Access-Control-Request-Headers' : 'x-requested-with'
		}
	}).done(function(data) {
		grid.trigger('reloadGrid');
	});
}

function getData(id) {
	var data = {
		id : id,
		fname : jQuery('#fnameField').val(),
		sname : jQuery('#snameField').val(),
		mname : jQuery('#mnameField').val(),
		birthDate : jQuery('#bdField').val()
	}
	return JSON.stringify(data);
}
