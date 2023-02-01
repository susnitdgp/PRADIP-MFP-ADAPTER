/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015, 2016. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.ntpc.pradip.mobility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.OAuthSecurity;

@Api(value = "Sample Adapter Resource")
@Path("/resource")
public class MobilitySMSAdapterResource {
	/*
	 * For more info on JAX-RS see
	 * https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
	 */

	// Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(MobilitySMSAdapterResource.class.getName());

	// Inject the MFP configuration API:
	@Context
	ConfigurationAPI configApi;

	

	/*
	 * Path for method:
	 * "<server address>/mfp/api/adapters/MobilitySMSAdapter/resource/terms"
	 */

	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/terms")
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(enabled = false)
	public String terms() {
		
		
		String  terms_string = "It was the best of times, it was the worst of times,\n "
		         + "it was the age of wisdom, it was the age of foolishness,\n "
		         + "it was the epoch of belief, it was the epoch of incredulity,\n "
		         + "it was the season of Light, it was the season of Darkness,\n "
		         + "it was the spring of hope, it was the winter of despair,\n "
		         + "we had everything before us, we had nothing before us \n "
		         + "it was the epoch of belief, it was the epoch of incredulity,\n "
		         + "it was the season of Light, it was the season of Darkness,\n "
		         + "it was the spring of hope, it was the winter of despair,\n "
		         + "we had everything before us, we had nothing before us \n "
		 		 + "it was the epoch of belief, it was the epoch of incredulity,\n "
		 		 + "it was the season of Light, it was the season of Darkness,\n "
		 		 + "it was the spring of hope, it was the winter of despair,\n "
		 		 + "we had everything before us, we had nothing before us";
		
		return terms_string;
		
				
	}
	
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/termsAccept")
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(enabled = false)
	public String termsUpdate(@ApiParam(value = "First Param", required = false) @QueryParam("empnum") String empnum) throws ClassNotFoundException, SQLException {
		
		
		DBUtil db=new DBUtil();
		db.insertTerms(empnum);
		
		return "SUCCESS";
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/termsVerify")
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(enabled = false)
	public String termsVerify(@ApiParam(value = "First Param", required = false) @QueryParam("empnum") String empnum) throws ClassNotFoundException, SQLException {
		
		DBUtil db=new DBUtil();
		
		if(db.verifyTerms(empnum)){
			
			return "ACCEPTED";
			
		}else{
			
			return "YET-TO-ACCEPT";
		}
		
	}
	

	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/sendOTP")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public String sendOTP(@ApiParam(value = "First Param", required = false) @QueryParam("empnum") String empnum) throws Exception {
	
				
		SMSUtil.sendSms(empnum);
				
		return "SUCCESS";
		
	}
	
	@ApiOperation(value = "Unprotected Resource", notes = "Example of an unprotected resource, this resource is accessible without a valid token.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A constant string is returned") })
	@GET
	@Path("/verifyOTP")
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(enabled = false)
	public String verifyOTP(@ApiParam(value = "First Param", required = false) @QueryParam("empnum") String empnum,
			@ApiParam(value = "Second Param", required = false) @QueryParam("otp") String otp) throws ClassNotFoundException, SQLException {
	
		DBUtil db=new DBUtil();
		
		if(db.verify2FToken(empnum, otp)){
			
			return "SUCCESS";
			
		}else{
			
			return "FAILURE";
		}
		
				
	}
	

}
