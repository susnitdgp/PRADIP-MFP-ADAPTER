/*
 *  Licensed Materials - Property of IBM
 *  5725-I43 (C) Copyright IBM Corp. 2011, 2016. All Rights Reserved.
 *  US Government Users Restricted Rights - Use, duplication or
 *  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/************************************************************************
 * Implementation code for procedure - 'procedure1'
 *
 *
 * @return - invocationResult
 */
 
var loginStatement = "SELECT VCODE, PAN_NO, VNAME, CITY from PCOMMON.VENDOR_PORTAL WHERE VCODE = ? AND PAN_NO= ?";
function VendorLogin(param1,param2) {
	return MFP.Server.invokeSQLStatement({
		preparedStatement : loginStatement,
		parameters : [param1,param2]
	});
}



var fetchQuerySSC="select distinct A.P_SSCCODE,P_NAME FROM PCOMMON.P_FINANCE_MASTER_PAY_ORDER  A,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER B " +
		"WHERE A.P_SSCCODE NOT IN ('1000','1001','1040','1051','1070')  AND A.P_SSCCODE=B.P_SSCCODE";
function fetchSSCList() {
	
	return MFP.Server.invokeSQLStatement({
		preparedStatement : fetchQuerySSC
		
	});
	
}


var fetchQueryPlant="select distinct A.P_BACODE ,P_NAME FROM PCOMMON.P_FINANCE_MASTER_PAY_ORDER  A,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER B " +
		"WHERE A.P_SSCCODE NOT IN ('1000','1001','1040','1051','1070')  AND A.P_BACODE=B.P_SSCCODE AND A.P_SSCCODE= ? ";
function fetchPlantList(param1) {

	return MFP.Server.invokeSQLStatement({
		preparedStatement : fetchQueryPlant,
		parameters : [param1]
	
	});

}

//if ntpc   pass NTPC and SSC pass ssccode 
var fetchNtpcDataSql="SELECT PVP_ENTITY_NO, PVP_ENTITY, PVP_ENTITY_SME, PVP_ENTITY_CAP_OPX,PVP_TOTAL_RS,  " +
		"PVP_DELAY_7_RS,PVP_DELAY_15_RS, PVP_DELAY_15PLUS_RS FROM PCOMMON.PVP_AGGREGATE_DETAIL " +
		"where PVP_ENTITY_NO = ? AND PVP_SUBDIV IS NULL";
function fetchNtpcAndSSCData(param1) {

	return MFP.Server.invokeSQLStatement({
		preparedStatement : fetchNtpcDataSql,
		parameters : [param1]

	});

}


//if ntpc   pass NTPC and SSC pass ssccode 
var fetchPlantDataSql="SELECT PVP_ENTITY_NO, PVP_ENTITY, PVP_ENTITY_SME, PVP_ENTITY_CAP_OPX,PVP_TOTAL_RS,  " +
		"PVP_DELAY_7_RS,PVP_DELAY_15_RS, PVP_DELAY_15PLUS_RS FROM PCOMMON.PVP_AGGREGATE_DETAIL " +
		"where PVP_ENTITY_NO = ? AND PVP_SUBDIV = ?";
function fetchPlantData(param1,param2) {

	return MFP.Server.invokeSQLStatement({
		preparedStatement : fetchPlantDataSql,
		parameters : [param1,param2]

	});

}


var feedbackStatement = "INSERT INTO PCOMMON.VENDOR_FEEDBACK VALUES(?,?,?,?,?,?,CURRENT TIMESTAMP)";
function feedbackMethod(param1,param2,param3,param4,param5,param6) {
	return MFP.Server.invokeSQLStatement({
		preparedStatement : feedbackStatement,
		parameters : [param1,param2,param3,param4,param5,param6]
	});
}


//Invoke stored SQL procedure and return invocation result
function registerMobileDevice(IN_VCODE,IN_DEVICE_ID,IN_DEVICE_NAME,IN_FCM_TOKEN){
  // To run a SQL stored procedure, use the `MFP.Server.invokeSQLStoredProcedure` method
  return MFP.Server.invokeSQLStoredProcedure({
    procedure : "PCOMMON.PROC_REGISTER_MOBILE_DEVICE",
    parameters : [IN_VCODE,IN_DEVICE_ID,IN_DEVICE_NAME,IN_FCM_TOKEN,2,"SUCCESS"]
  });
}



/************************************************************************
 * Implementation code for procedure - 'procedure2'
 *
 *
 * @return - invocationResult
 */
 
var scopeStatement = "SELECT VCODE, PAN_NO from PCOMMON.VENDOR_PORTAL WHERE VCODE = ? AND PAN_NO= ?";
function scopeMethod(param1,param2) {
	return MFP.Server.invokeSQLStatement({
		preparedStatement : scopeStatement,
		parameters : [param1,param2]
	});
}




