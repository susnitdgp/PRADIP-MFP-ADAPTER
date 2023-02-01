/*
 *  Licensed Materials - Property of IBM
 *  5725-I43 (C) Copyright IBM Corp. 2011, 2016. All Rights Reserved.
 *  US Government Users Restricted Rights - Use, duplication or
 *  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/**
 * @param tag: a topic such as MobileFirst_Platform, Bluemix, Cordova.
 * @returns json list of items.
 */
function ssoLogin(userid,password) {
    
	MFP.Logger.info(userid);
    
	var request = {"userid":userid,"password":password};

    var options = {
        method: 'post',       
        path: 'PradipLoginIbm/login/LoginService/getToken',      
        headers: {
        	'Content-Type' : 'plain'
        },         
        body : {
	        'contentType' : 'plain',
	        'content' : request
        }
    };
    
    var response={};
    response= MFP.Server.invokeHttp(options);
    
    return response;
    
}