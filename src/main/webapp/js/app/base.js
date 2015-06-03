/**
 * Configuration
 */
var limit = 10;
var languageDefault = "pt-BR";

/**
 * Setup
 */
var baseUrl = './';
var loginUrl = baseUrl + 'authentication/login/';
var logoutUrl = baseUrl + 'authentication/logout/';

/**
 * Basic Functions
 */

function string(val) {
	return "" + val + "";
}

window.alert = function(message) {
	$(document.createElement('div')).attr({
		title : 'Warning',
		'class' : 'alert'
	}).html(message).dialog({
		buttons : {
			OK : function() {
				$(this).remove();
			}
		},
		close : function() {
			$(this).remove();
		},
		draggable : true,
		modal : true,
		resizable : false
	});
};

window.confirm = function(message, callback) {
	"use strict"
	$(document.createElement('div')).attr({
		title : 'Warning',
		'class' : 'alert'
	}).html(message).dialog({
		width : 'auto',
		modal : true,
		resizable : false,
		buttons : {
			"Cancelar" : function() {
				$(this).remove();
				return false;
			},
			"Ok" : function() {
				//chamando função callback
				if(callback && (typeof callback === "function")){
					callback();
				} 
				$(this).remove();
				return true;
			}
		}
	});
};