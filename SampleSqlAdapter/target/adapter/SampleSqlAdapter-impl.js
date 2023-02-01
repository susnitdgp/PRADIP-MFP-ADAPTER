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
 
var procedure1Statement = "SELECT * FROM PCOMMON.P_EMPLOYEE_MASTER WHERE EMP_NO = ?";
function getEmployeeDetails(param) {
	return MFP.Server.invokeSQLStatement({
		preparedStatement : procedure1Statement,
		parameters : [param]
	});
}

var sqlStatement2= "SELECT * FROM PCOMMON.P_PROCESS_INSTANCES_MASTER WHERE P_CURRENT_OWNER = ? AND P_FILE_STATUS <> ? AND P_FILE_STATUS <> ?";
function getPendingTaskDetails(param1){
	var param2='Closed';
	var param3='Discarded';
	return MFP.Server.invokeSQLStatement({
		preparedStatement : sqlStatement2,
		parameters : [param1,param2,param3]
	});
}

var sqlStatement3="SELECT * FROM PCOMMON.P_USER_ACTION_MASTER WHERE P_ID = ? ";
function getFileActionHistory(param){
	return MFP.Server.invokeSQLStatement({
		preparedStatement : sqlStatement3,
		parameters : [param]
	});
	
}


/************************************************************************
 * Implementation code for procedure - 'procedure2'
 *
 *
 * @return - invocationResult
 */
 
function procedure2(param) {
	return MFP.Server.invokeSQLStoredProcedure({
		procedure : "storedProcedure2",
		parameters : [param]
	});
}

/************************************************************************
 * Implementation code for procedure - 'unprotected'
 *
 *
 * @return - invocationResult
 */
function unprotected(param) {
	return {result : "Hello from unprotected resource"};
}


