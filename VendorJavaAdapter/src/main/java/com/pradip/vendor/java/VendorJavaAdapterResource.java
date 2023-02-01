/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015, 2016. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.pradip.vendor.java;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.OAuthSecurity;

@Api(value = "Sample Adapter Resource")
@Path("/VendorResource")
public class VendorJavaAdapterResource {
	/*
	 * For more info on JAX-RS see
	 * https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
	 */

	// Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(VendorJavaAdapterResource.class.getName());

	// Inject the MFP configuration API:
	@javax.ws.rs.core.Context
	ConfigurationAPI configApi;

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/VendorJavaAdapter/resource"
	 */
    
	/*
	@ApiOperation(value = "Returns 'Hello from resource'", notes = "A basic example of a resource returning a constant string.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Hello message returned") })
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResourceData() {
		// log message to server log
		logger.info("Logging info message...");

		return "Hello from resource";
	}

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/VendorJavaAdapter/resource/greet?name={name}"
	 *

	@ApiOperation(value = "Query Parameter Example", notes = "Example of passing query parameters to a resource. Returns a greeting containing the name that was passed in the query parameter.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Greeting message returned") })
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/greet")
	public String helloUser(
			@ApiParam(value = "Name of the person to greet", required = true) @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/VendorJavaAdapter/resource/{path}/"
	 *

	@ApiOperation(value = "Multiple Parameter Types Example", notes = "Example of passing parameters using 3 different methods: path parameters, headers, and form parameters. A JSON object containing all the received parameters is returned.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A JSON object containing all the received parameters returned.") })
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{path}")
	public Map<String, String> enterInfo(
			@ApiParam(value = "The value to be passed as a path parameter", required = true) @PathParam("path") String path,
			@ApiParam(value = "The value to be passed as a header", required = true) @HeaderParam("Header") String header,
			@ApiParam(value = "The value to be passed as a form parameter", required = true) @FormParam("form") String form) {
		Map<String, String> result = new HashMap<String, String>();

		result.put("path", path);
		result.put("header", header);
		result.put("form", form);

		return result;
	}

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/VendorJavaAdapter/resource/prop"
	 *

	@ApiOperation(value = "Configuration Example", notes = "Example usage of the configuration API. A property name is read from the query parameter, and the value corresponding to that property name is returned.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Property value returned."),
			@ApiResponse(code = 404, message = "Property value not found.") })
	@GET
	@Path("/prop")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getPropertyValue(
			@ApiParam(value = "The name of the property to lookup", required = true) @QueryParam("propertyName") String propertyName) {
		// Get the value of the property:
		String value = configApi.getPropertyValue(propertyName);
		if (value != null) {
			// return the value:
			return Response
					.ok("The value of " + propertyName + " is: " + value)
					.build();
		} else {
			return Response.status(Status.NOT_FOUND)
					.entity("No value for " + propertyName + ".").build();
		}

	}

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/VendorJavaAdapter/resource/unprotected"
	 */

	/*
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/unprotected")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Map<String, String> unprotected(
			@ApiParam(value = "First Param", required = true) @QueryParam("param1") String param1) throws ClassNotFoundException, SQLException, NamingException {
		
		
		PreparedStatement pstm = null;
		ResultSet rs;
		
		Map<String, String> result = new HashMap<String, String>();
		
		try {
			
			//Class.forName("com.ibm.db2.jcc.DB2Driver");
			//con = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			Context initContext = (Context) new InitialContext();
			
			DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			Connection conn = ds.getConnection();
			
			

			//String fetchStatement = "SELECT * from PCOMMON.VENDOR_PORTAL fetch first 20 rows only";
			
			//String fetchStatement = "SELECT B.P_ID,P_PO_NUMBER,P_PO_TEXT,P_MIR7,P_VENDOR_NAME,P_IV_AMOUNT,P_PAYMENT_STATUS,P_PAYMENT_DOC_NO,P_REF1_PID, P_PAY_ORDER_STATUS," +
				//	"P_PAY_ORDER_CREATED_BY,P_CREATION_DATE,P_PURCHASE_ORG,P_PLANT,P_VENDOR_CODE,P_NAME,P_BANK_ACK,P_PAY_AMOUNT,P_MODE_OF_PAY,P_PAYMENT_RUN_DATE,P_BANK_ACC_NO," +
					//"P_REFERENCE FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION  A,PCOMMON.P_FILE_INSTANCES_MASTER B,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER " +
					//"C  where A.P_ID=B.P_ID and  " +
					//"P_VENDOR_NAME like ? " +
					//"AND (P_VENDOR_CODE=? OR P_PAY_VENDOR_CODE=?) " +
					//"AND A.P_PLANT=C.P_SSCCODE order by  P_CREATION_DATE  DESC";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, "%" + param1 + "%");
			pstm.setString(2, "0001074600");
			pstm.setString(3, "0001074600");
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				result.put("path", "inside");
				result.put("header", "inside");
				result.put("form", param1);
				

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		} 
		
	

		//result.put("path", "mypath");
		//result.put("header", "myheader");
		//result.put("form", param1);

		return result;
		
	
		
	}
	*/
	//Private method to get employee details	
	private Employee getDetails(String emp_num) throws NamingException{
		
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		Employee emp=new Employee();
		
		try {
			
			
			 conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
		    //conn = ds.getConnection();
			
			

			String fetchStatement = "SELECT UPPER(FIRST_NAME||' '||LAST_NAME) as EMP_NAME, UPPER(DESIGNATION) as DESIGNATION  from PCOMMON.P_EMPLOYEE_MASTER WHERE EMP_NO=?";
			
						
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, emp_num);
			
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				emp.setEmpname(rs.getString(1));
				emp.setDesignation(rs.getString(2));
				

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		} 
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
			
		return emp;
		
		
	}
	
	//Private method to get last execution of the payment portal schedular
	// DATE: 10.02.2020 SUSANTA/SATYAWAN GUPTA
		private String getLastUpdate() throws NamingException{
			
			String result="";
			PreparedStatement pstm = null;
			ResultSet rs=null;
			Connection conn=null;
			
			
			try {
				
				
				 conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
				String fetchStatement = "SELECT DAY(MAX(P_RUNDATE))||'-'||MONTH(MAX(P_RUNDATE))||'-'||YEAR(MAX(P_RUNDATE))||' '||CHAR(TIME(MAX(P_RUNDATE)),USA) as LASTUPDATEDATE   from PCOMMON.PVP_INVOICE_DETAIL";
				
							
				pstm = conn.prepareStatement(fetchStatement);
				
				
								
				rs = pstm.executeQuery();
				
				while (rs.next()) {
					
					result=rs.getString(1);
				

				}
			
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				result="N/A";
				
			} 
			finally {
				if (conn != null | pstm != null || rs != null) {
					try {
						conn.close();
						pstm.close();
						rs.close();
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					conn = null;
					pstm = null;
					rs = null;
				}
			}
				
			return result;
			
			
		}
		
	
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@POST
	@Path("/adsLogin")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Map<String, String> adsLogin(
			@ApiParam(value = "First Param", required = true) @QueryParam("username") String username,
			@ApiParam(value = "Second Param", required = true) @QueryParam("password") String password) {
		

		//String[] employee_array = new String[]{"009697","009394","008681","004246","004191","009391","007447","006920","008229","007035","001878","002691","012658"};

        // Convert String Array to List
       // List<String> list = Arrays.asList(employee_array);
        
        
		Map<String, String> result = new HashMap<String, String>();
		
		ActiveDirectoryAuthentication auth=new ActiveDirectoryAuthentication("ntpc.orgn");
		
/*		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM,yyyy hh:mm");*/ 
		
		try{
			
			boolean response=auth.authenticate(username, password);
			
			if(response){
				
				// Code required to disable/enable specific NTPC Users
				/*
				if(list.contains(username)){
					
					
					Employee employee=this.getDetails(username);
					
					result.put("success", "true");
					result.put("username", username);
					result.put("role", "NTPC");
					result.put("name", employee.getEmpname());
					result.put("designation", employee.getDesignation());
					result.put("lastUpdatedTime", formatter.format(date));
					
					
				}else{
					
					result.put("success", "false");
					result.put("username", username);
					result.put("role", "N/A");
					result.put("name", "N/A");
					result.put("designation", "N/A");
					result.put("lastUpdatedTime", "N/A");
					
				}
				*/
				//For NTPC Employee Case
				
				if(!username.equalsIgnoreCase("ntpc_doe")){
					
					Employee employee=this.getDetails(username);
					
					result.put("success", "true");
					result.put("username", username);
					result.put("role", "NTPC");
					result.put("name", employee.getEmpname());
					result.put("designation", employee.getDesignation());
					//result.put("lastUpdatedTime", formatter.format(date));
					result.put("lastUpdatedTime", this.getLastUpdate());
					
				}
				else{
					
					result.put("success", "true");
					result.put("username", username);
					result.put("role", "DOE");
					result.put("name", "Dept. of Expenditure");
					result.put("designation", "MoF,GOI");
					//result.put("lastUpdatedTime", formatter.format(date));
					result.put("lastUpdatedTime", this.getLastUpdate());
					
					
				}
				
				
			}else{
				
				result.put("success", "false");
				result.put("username", username);
				result.put("role", "N/A/A");
				result.put("name", "N/A/A");
				result.put("designation", "N/A/A");
				result.put("lastUpdatedTime", "N/A/A");
				
			}
			
		}
		catch(Exception ex){
			
			result.put("success", "false");
			result.put("username", username);
			result.put("role", "N/A");
			result.put("name", "N/A");
			result.put("designation", "N/A");
			result.put("lastUpdatedTime", "N/A");
			
		}
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@POST
	@Path("/vendorRegister")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Map<String, String> vendorRegister(
			@ApiParam(value = "First Param", required = true) @QueryParam("vcode") String vcode,
			@ApiParam(value = "Second Param", required = true) @QueryParam("pan") String pan,
			@ApiParam(value = "Third Param", required = true) @QueryParam("email") String email,
			@ApiParam(value = "Fourth Param", required = true) @QueryParam("mobile") String mobile,
			@ApiParam(value = "Fifth Param", required = true) @QueryParam("address") String address) {
		

		Map<String, String> result = new HashMap<String, String>();
		
					
		result.put("success", "true");
		result.put("vcode", vcode);
			
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/vendorSearchByName")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<Vendor> vendorSearchByName(
			@ApiParam(value = "First Param", required = true) @QueryParam("vname") String vname) throws ClassNotFoundException {
		
		
		System.out.println("Inside Vendor Search");
		
		List<Vendor> result=new ArrayList<Vendor>();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			
			String fetchStatement = "SELECT DISTINCT A.P_VENDOR_CODE,B.VNAME,B.ADDRESS,B.CITY FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION A, PCOMMON.VENDOR_PORTAL B "
					+ " WHERE RIGHT(A.P_VENDOR_CODE,7)= RIGHT(B.VCODE,7)AND UPPER(P_VENDOR_NAME) LIKE UPPER(?) FETCH FIRST 50 ROWS ONLY";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, "%" + vname + "%");
			
			rs = pstm.executeQuery();
		
			while (rs.next()) {
					
				Vendor vend=new Vendor();
				
				vend.setCode(rs.getString(1));
				vend.setName(rs.getString(2));
				vend.setAddress(rs.getString(3));
				vend.setCity(rs.getString(4));
				
				System.out.println("Searched Vendor" + vend.getName());
				
				result.add(vend);
			}
		
		
			}catch (SQLException ex) {
				System.out.println(ex.getMessage());
				
			}
			finally {
				if (conn != null | pstm != null || rs != null) {
					try {
						conn.close();
						pstm.close();
						rs.close();
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					conn = null;
					pstm = null;
					rs = null;
				}
			}
		
		return result;
	}
		
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/searchQueryByPoOrInvoice")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<SearchResult> searchQueryByPoOrInvoice(
			@ApiParam(value = "First Param", required = true) @QueryParam("vcode") String vcode,
			@ApiParam(value = "Second Param", required = true) @QueryParam("queryParam") String queryParam) throws ClassNotFoundException {
		

		List<SearchResult> result=new ArrayList<SearchResult>();
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			//Connection conn = ds.getConnection();
	
			
			String fetchStatement = "SELECT P_PO_NUMBER,P_PO_TEXT,CASE WHEN P_REFERENCE is NULL THEN '-' WHEN P_REFERENCE IS NOT NULL THEN P_REFERENCE END AS P_INVOICE_NO,"
					+ " P_IV_AMOUNT,P_PAYMENT_STATUS,P_NAME,CASE WHEN P_BANK_ACK is NULL THEN '-' WHEN P_BANK_ACK IS NOT NULL THEN P_BANK_ACK END AS P_BANK_ACK,CASE WHEN P_PAY_AMOUNT "
					+ " is NULL THEN '-' WHEN P_PAY_AMOUNT IS NOT NULL THEN P_PAY_AMOUNT END AS P_PAY_AMOUNT,CASE WHEN P_MODE_OF_PAY is NULL THEN '-' WHEN P_MODE_OF_PAY IS NOT NULL "
					+ " THEN P_MODE_OF_PAY END AS P_MODE_OF_PAY, CASE WHEN P_PAYMENT_RUN_DATE  Is Null THEN '1900-01-01' WHEN P_PAYMENT_RUN_DATE IS NOT NULL THEN P_PAYMENT_RUN_DATE "
					+ " END AS P_PAYMENT_RUN_DATE,CASE WHEN P_BANK_ACC_NO is NULL THEN '-' WHEN P_BANK_ACC_NO IS NOT NULL THEN P_BANK_ACC_NO END AS P_BANK_ACC_NO "
					+ " FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION  A,PCOMMON.P_FILE_INSTANCES_MASTER B,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER C,PCOMMON.P_USER_ACTION_MASTER D "
					+ " where A.P_ID=B.P_ID and A.P_ID=D.P_ID"
					+ " and  (UPPER(P_PO_NUMBER) like ? or UPPER(P_REFERENCE) like ? or P_DOCUMENT_REFERENCE_NO like ?) AND (P_VENDOR_CODE=? OR P_PAY_VENDOR_CODE=?) "
							+ " AND D.P_ACTION_ON>='2019-09-01' AND A.P_PLANT=C.P_SSCCODE AND D.P_ACTION in ('Closed','Approve') "
							+ " order by  P_CREATION_DATE  DESC";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, "%" + queryParam.toUpperCase() + "%");
			pstm.setString(2, "%" + queryParam.toUpperCase() + "%");
			pstm.setString(3, "%" + queryParam.toUpperCase() + "%");
			pstm.setString(4, vcode);
			pstm.setString(5, vcode);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				
				SearchResult temp=new SearchResult();
				
				temp.setPo_number(rs.getString(1));
				temp.setWork_desc(rs.getString(2));
				temp.setInvoice_number(rs.getString(3));
				
				if(!isNullOrEmpty(rs.getString(4))){
					temp.setInvoice_amount(rs.getString(4).trim());
				}else{
					temp.setInvoice_amount("N/A");
				}
				
				
				temp.setPayment_status(rs.getString(5));
				temp.setPlant_name(rs.getString(6));
				temp.setUtr_no(rs.getString(7));
				
				if(!isNullOrEmpty(rs.getString(8))){
					
					temp.setAmount_paid(rs.getString(8).trim());
				}else{
					temp.setAmount_paid("N/A");
				}
				
				
				temp.setPay_mode(rs.getString(9));
				temp.setPay_date(rs.getString(10).substring(0, 10));
				temp.setBank_account(rs.getString(11));
				
				result.add(temp);

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
		
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/searchPaymentByVendorCode")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<SearchResult> searchPaymentByVendorCode(
			@ApiParam(value = "First Param", required = true) @QueryParam("vcode") String vcode) throws ClassNotFoundException {
		

		List<SearchResult> result=new ArrayList<SearchResult>();
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			//Connection conn = ds.getConnection();
	
			
			String fetchStatement = "SELECT P_PO_NUMBER,P_PO_TEXT,CASE WHEN P_REFERENCE is NULL THEN '-' WHEN P_REFERENCE IS NOT NULL THEN P_REFERENCE END AS P_INVOICE_NO,"
					+ " P_IV_AMOUNT,P_PAYMENT_STATUS,P_NAME,CASE WHEN P_BANK_ACK is NULL THEN '-' WHEN P_BANK_ACK IS NOT NULL THEN P_BANK_ACK END AS P_BANK_ACK,CASE WHEN P_PAY_AMOUNT "
					+ " is NULL THEN '-' WHEN P_PAY_AMOUNT IS NOT NULL THEN P_PAY_AMOUNT END AS P_PAY_AMOUNT,CASE WHEN P_MODE_OF_PAY is NULL THEN '-' WHEN P_MODE_OF_PAY IS NOT NULL "
					+ " THEN P_MODE_OF_PAY END AS P_MODE_OF_PAY, CASE WHEN P_PAYMENT_RUN_DATE  Is Null THEN '1900-01-01' WHEN P_PAYMENT_RUN_DATE IS NOT NULL THEN P_PAYMENT_RUN_DATE "
					+ " END AS P_PAYMENT_RUN_DATE,CASE WHEN P_BANK_ACC_NO is NULL THEN '-' WHEN P_BANK_ACC_NO IS NOT NULL THEN P_BANK_ACC_NO END AS P_BANK_ACC_NO "
					+ " FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION  A,PCOMMON.P_FILE_INSTANCES_MASTER B,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER C,PCOMMON.P_USER_ACTION_MASTER D "
					+ " where A.P_ID=B.P_ID and A.P_ID=D.P_ID"
					+ " AND (P_VENDOR_CODE=? OR P_PAY_VENDOR_CODE=?) "
							+ " AND D.P_ACTION_ON>='2019-09-01' AND A.P_PLANT=C.P_SSCCODE AND D.P_ACTION in ('Closed','Approve') "
							+ " order by  P_CREATION_DATE  DESC";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			
			pstm.setString(1, vcode);
			pstm.setString(2, vcode);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				
				SearchResult temp=new SearchResult();
				
				temp.setPo_number(rs.getString(1));
				temp.setWork_desc(rs.getString(2));
				temp.setInvoice_number(rs.getString(3));
				
				if(!isNullOrEmpty(rs.getString(4))){
					temp.setInvoice_amount(rs.getString(4).trim());
				}else{
					temp.setInvoice_amount("N/A");
				}
				
				
				temp.setPayment_status(rs.getString(5));
				temp.setPlant_name(rs.getString(6));
				temp.setUtr_no(rs.getString(7));
				
				if(!isNullOrEmpty(rs.getString(8))){
					
					temp.setAmount_paid(rs.getString(8).trim());
				}else{
					temp.setAmount_paid("N/A");
				}
				
				
				temp.setPay_mode(rs.getString(9));
				temp.setPay_date(rs.getString(10).substring(0, 10));
				temp.setBank_account(rs.getString(11));
				
				result.add(temp);

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
		
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/searchQueryByUTR")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<SearchResult> searchQueryByUTR(
			
			@ApiParam(value = "First Param", required = true) @QueryParam("queryParam") String queryParam) throws ClassNotFoundException {
		

		List<SearchResult> result=new ArrayList<SearchResult>();
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			//Connection conn = ds.getConnection();
	
			
			String fetchStatement = "SELECT P_PO_NUMBER,P_PO_TEXT,"
					+ " CASE WHEN P_REFERENCE is NULL THEN '-' WHEN P_REFERENCE IS NOT NULL THEN P_REFERENCE END AS P_INVOICE_NO, "
					+ " P_VENDOR_CODE,P_VENDOR_NAME,P_IV_AMOUNT,P_PAYMENT_STATUS, "
					+ " P_NAME,CASE WHEN P_BANK_ACK is NULL THEN '-' WHEN P_BANK_ACK IS NOT NULL THEN P_BANK_ACK END AS P_BANK_ACK,CASE WHEN P_PAY_AMOUNT is NULL THEN '-' WHEN P_PAY_AMOUNT IS NOT NULL THEN P_PAY_AMOUNT END "
					+ " AS P_PAY_AMOUNT,CASE WHEN P_MODE_OF_PAY is NULL THEN '-' WHEN P_MODE_OF_PAY IS NOT NULL THEN P_MODE_OF_PAY END AS P_MODE_OF_PAY, CASE WHEN P_PAYMENT_RUN_DATE  Is Null THEN '1900-01-01' WHEN P_PAYMENT_RUN_DATE"
					+ " IS NOT NULL THEN P_PAYMENT_RUN_DATE END AS P_PAYMENT_RUN_DATE,CASE WHEN P_BANK_ACC_NO is NULL THEN '-' WHEN P_BANK_ACC_NO IS NOT NULL THEN P_BANK_ACC_NO END AS P_BANK_ACC_NO "
					+ " FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION  A,PCOMMON.P_FILE_INSTANCES_MASTER B,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER C,PCOMMON.P_USER_ACTION_MASTER D "
					+ " where A.P_ID=B.P_ID and A.P_ID=D.P_ID and  UPPER(P_BANK_ACK) = ?   AND D.P_ACTION_ON>='2019-09-01' AND A.P_PLANT=C.P_SSCCODE AND D.P_ACTION in ('Closed','Approve') order by  P_CREATION_DATE  DESC ";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, queryParam.toUpperCase());
			
			
			
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				
				SearchResult temp=new SearchResult();
				
				temp.setPo_number(rs.getString(1));
				temp.setWork_desc(rs.getString(2));
				temp.setInvoice_number(rs.getString(3));
				temp.setVendor_code(rs.getString(4));
				temp.setVendor_name(rs.getString(5));
				if(!isNullOrEmpty(rs.getString(6))){
					temp.setInvoice_amount(rs.getString(6).trim());
				}else{
					temp.setInvoice_amount("N/A");
				}
				
				
				temp.setPayment_status(rs.getString(7));
				temp.setPlant_name(rs.getString(8));
				temp.setUtr_no(rs.getString(9));
				
				if(!isNullOrEmpty(rs.getString(10))){
					
					temp.setAmount_paid(rs.getString(10).trim());
				}else{
					temp.setAmount_paid("N/A");
				}
				
				
				temp.setPay_mode(rs.getString(11));
				temp.setPay_date(rs.getString(12).substring(0, 10));
				temp.setBank_account(rs.getString(13));
				
				result.add(temp);

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
		
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/vendorPaymentStatusForChart")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<ChartData> vendorPaymentStatusForChart(
			
			@ApiParam(value = "First Param", required = true) @QueryParam("queryParam") String queryParam) throws ClassNotFoundException {
		

		List<ChartData> result=new ArrayList<ChartData>();
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			//Connection conn = ds.getConnection();
	
			
			String fetchStatement = "SELECT  'TOTAL' AS STAT,ROUND((SUM(P_IV_AMOUNT)/100000),2) AS AMOUNT FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION where P_VENDOR_CODE=? "
					+ " UNION "
					+ " SELECT 'PAID' AS STAT,ROUND((SUM(P_IV_AMOUNT)/100000),2) AS AMOUNT FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION where P_VENDOR_CODE=? and P_PAYMENT_STATUS IN('Processed','In Progress-Cash & Bank') "
					+ " UNION "
					+ " SELECT 'PENDING' AS STAT,ROUND((SUM(P_IV_AMOUNT)/100000),2) AS AMOUNT FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION where P_VENDOR_CODE=? and P_PAYMENT_STATUS NOT IN('Processed','In Progress-Cash & Bank') "
					+ " ORDER BY STAT ";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, queryParam );
			pstm.setString(2, queryParam );
			pstm.setString(3, queryParam );
			
			
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				
				ChartData temp=new ChartData();
				
				temp.setPay_Status(rs.getString(1));
				temp.setAmount(rs.getString(2));
				
				result.add(temp);

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
		
		
	
		return result;
		
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/vendorLastFivePoDetail")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public List<PODetail> vendorLastFivePoDetail(
			
			@ApiParam(value = "First Param", required = true) @QueryParam("queryParam") String queryParam) throws ClassNotFoundException {
		

		List<PODetail> result=new ArrayList<PODetail>();
		
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Connection conn=null;
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://ecmprddb.ntpc.co.in:50000/PCOMMON", "db2inst1","ProddbPR");
			
			//Context initContext = (Context) new InitialContext();
			//DataSource ds = (DataSource) initContext.lookup("jdbc/PcommonProd");
			//Connection conn = ds.getConnection();
	
			
			String fetchStatement = " SELECT distinct P_PO_NUMBER,P_PO_TEXT,P_VENDOR_NAME,P_NAME,P_CREATION_DATE "
					+ " FROM PCOMMON.P_PAYMENT_INVOICE_VERIFICATION  A,PCOMMON.P_FINANCE_SHARED_SERVICE_CENTER C "
					+ " where P_VENDOR_CODE=? AND A.P_PLANT=C.P_SSCCODE order by  P_CREATION_DATE  DESC FETCH FIRST 5 ROWS ONLY ";
			
			pstm = conn.prepareStatement(fetchStatement);
			
			pstm.setString(1, queryParam );
		
			
			
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				
				PODetail temp=new PODetail();
				
				temp.setPo_Number(rs.getString(1));
				temp.setPo_Text(rs.getString(2));
				temp.setVendor_Name(rs.getString(3));
				temp.setPlant_Name(rs.getString(4));
				temp.setPo_Date(rs.getString(5));
				result.add(temp);

			}
		
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		finally {
			if (conn != null | pstm != null || rs != null) {
				try {
					conn.close();
					pstm.close();
					rs.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				conn = null;
				pstm = null;
				rs = null;
			}
		}
		
		
	
		return result;
		
		
	}
	private static boolean isNullOrEmpty(String str) {
		//StringUtils.is
		
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}
	

}
