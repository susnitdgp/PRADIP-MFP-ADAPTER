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
/*
function getFeed(tag) {
	var input = {
	    method : 'get',
	    returnedContentType : 'xml',
	    path : getPath(tag)
	};

	return MFP.Server.invokeHttp(input);
}
*/
/**
 * Helper function to build the URL path.
 
function getPath(tag){
    if(tag === undefined || tag === ''){
        return 'feed.xml';
    } else {
        return 'blog/atom/' + tag + '.xml';
    }
}

/**
 * @returns ok
 */
function Alerts(param1,param2,param3) {
	
	//var response= "Hello from unprotected resource with value = "+ param1 + "-" +param2;
	
	//return {result : response};
	
	//MFP.Logger.info(userid);
    
    //var before_decode = '009697' + ':' + 'Basant@2019';
    
    //var after_decode = window.btoa(before_decode);
    //var auth_string = "Basic "+after_decode;

	
	
    var options = {
        'method': 'GET',  
        'returnedContentType' : 'json',
        'path': '/objectserver/restapi/alerts/status?filter='+param1+'&collist='+param2+'&orderby='+param3, 
        'headers' : {
        	//'withCredentials':'true',
	       'Authorization' : 'Basic cm9vdDo='
	       
        }
    };
      
    MFP.Logger.info(JSON.stringify(options));

    var response={};
    response= MFP.Server.invokeHttp(options);
    
    return response;
    
   
	
}



function Status() {
	
	//var response= "Hello from unprotected resource with value = "+ param1 + "-" +param2;
	
	//return {result : response};
	
	//MFP.Logger.info(userid);
    
    //var before_decode = '009697' + ':' + 'Basant@2019';
    
    //var after_decode = window.btoa(before_decode);
    //var auth_string = "Basic "+after_decode;

	
	var response={'result':'Sample Response'};
	
    return response;
    
   
	
}



/*
function testRestProcessList() {
    
	MFP.Logger.info(userid);
     
    var options = {
            'method': 'get',  
            'returnedContentType' : 'json',
            'path': 'sample.json'         
           
        };
    
    //MFP.Logger.info(JSON.stringify(input));

    return MFP.Server.invokeHttp(options);
   
   
}
function getExposedProcessList(userid,password) {
    
	MFP.Logger.info(userid);
    
    //var before_decode = '009697' + ':' + 'Basant@2019';
    
    //var after_decode = window.btoa(before_decode);
    //var auth_string = "Basic "+after_decode;

    var options = {
        'method': 'GET',  
        'returnedContentType' : 'json',
        'path': '/rest/bpm/wle/v1/exposed/process?accept=application/x-javascript', 
        'headers' : {
        	//'withCredentials':'true',
	       'Authorization' : 'Basic MDA5Njk3OkJhc2FudEAyMDE5'
	       
        }
    };
      
    MFP.Logger.info(JSON.stringify(options));

    var response={};
    response= MFP.Server.invokeHttp(options);
    return response.data.exposedItemsList;
 
 
}

*/



