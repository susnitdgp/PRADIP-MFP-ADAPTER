package com.ntpc.pradip.mobility;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBUtil {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs;
	
	public String getUserMail(String userid)
			throws ClassNotFoundException, SQLException{
		
		String ret="";
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
			
			pstm = con.prepareStatement("SELECT EMAIL_ID FROM PCOMMON.P_EMPLOYEE_MASTER WHERE EMP_NO=?");
			pstm.setString(1, userid);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
											
				ret=rs.getString(1);

			}
		
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally {
			if (con != null | pstm != null || rs != null) {
				con.close();
				pstm.close();
				rs.close();
				con = null;
				pstm = null;
				rs = null;
			}
		}
		return ret.toLowerCase();
		
	}
	
	public String getUserMobile(String userid)
			throws ClassNotFoundException, SQLException{
		
		String ret="";
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
			
			pstm = con.prepareStatement("SELECT PHONE_NUMBER FROM PCOMMON.P_EMPLOYEE_MASTER where EMP_NO=?");
			pstm.setString(1, userid);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
											
				ret=rs.getString(1);

			}
		
		} catch (ClassNotFoundException ex) {
			
			System.out.println(ex.getMessage());
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		} finally {
			if (con != null | pstm != null || rs != null) {
				con.close();
				pstm.close();
				rs.close();
				con = null;
				pstm = null;
				rs = null;
			}
		}
		return ret;
		
	}
	
		
	 public boolean insertOTPInfo(String userid,String otp)throws ClassNotFoundException, SQLException{
	    	
	    	boolean result=false;
	    	
	    	
	    	
	    	Date today = new Date();
	    	Timestamp createdon = new Timestamp(today.getTime());
	    	
	    	try {
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
				
				pstm = con.prepareStatement("INSERT INTO PCOMMON.P_2FA_TRN(EMP_ID,OTP,SENTIME,LOGIN_STAT,TRY_COUNT,OTP_TARGET) VALUES(?,?,?,?,?,?)");
				pstm.setString(1, userid);
				pstm.setString(2, otp);
				//pstm.setString(3,new Date().toString());
				//pstm.setInt(4, 0);
				//pstm.setString(5, "");
				//pstm.setInt(6, 0);
				//pstm.setString(3, session);
				
				pstm.setTimestamp(3, createdon);
				pstm.setInt(4, 0);
				pstm.setInt(5, 0);
				pstm.setString(6, "MOB");
				
				pstm.executeUpdate();
				result=true;
				
			
	    	} catch (ClassNotFoundException ex) {
				System.out.println(ex.getMessage());
				
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}finally {
				
				if (con != null | pstm != null ) {
					con.close();
					pstm.close();
					
					con = null;
					pstm = null;
					
				}
			}
		
	    	return result;
	 }
	
	 public boolean verify2FToken(String emp, String otp)
				throws ClassNotFoundException, SQLException{
			
			boolean ret=false;
			
			try {
				
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
				
				pstm = con.prepareStatement("SELECT OTP FROM PCOMMON.P_2FA_TRN where EMP_ID=? AND OTP=?");
				pstm.setString(1, emp);
				pstm.setString(2,otp);
				
				
				rs = pstm.executeQuery();
				
				while (rs.next()) {
					
					ret=true;

				}
			
			} catch (SQLException ex) {
				ret=false;
				System.out.println(ex.getMessage());
				
			} finally {
				if (con != null | pstm != null || rs != null) {
					con.close();
					pstm.close();
					rs.close();
					con = null;
					pstm = null;
					rs = null;
				}
			}
			return ret;
			
	}
	 
	 public boolean verifyTerms(String emp)
				throws ClassNotFoundException, SQLException{
			
			boolean ret=false;
			
			try {
				
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
				
				pstm = con.prepareStatement("SELECT FLAG FROM PCOMMON.P_MOBILE_TERMS where EMP_NUM=?");
				pstm.setString(1, emp);
								
				
				rs = pstm.executeQuery();
				
				while (rs.next()) {
					
					int val=rs.getInt(1);
					
					if(val==1){
						ret=true;
					}else{
						
						ret=false;
					}
					

				}
			
			} catch (SQLException ex) {
				ret=false;
				System.out.println(ex.getMessage());
				
			} finally {
				if (con != null | pstm != null || rs != null) {
					con.close();
					pstm.close();
					rs.close();
					con = null;
					pstm = null;
					rs = null;
				}
			}
			return ret;
			
	}
	 
	 public boolean insertTerms(String userid)throws ClassNotFoundException, SQLException{
	    	
	    	boolean result=false;
	    	
	    	
	    	
	    	Date today = new Date();
	    	Timestamp createdon = new Timestamp(today.getTime());
	    	
	    	try {
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				con = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
				
				pstm = con.prepareStatement("INSERT INTO PCOMMON.P_MOBILE_TERMS(FLAG,EMP_NUM,UPDATE_TIME) VALUES(?,?,?)");
				pstm.setInt(1, 1);
				pstm.setString(2, userid);
						
				pstm.setTimestamp(3, createdon);
				
				
				pstm.executeUpdate();
				result=true;
				
			
	    	} catch (ClassNotFoundException ex) {
				System.out.println(ex.getMessage());
				
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}finally {
				
				if (con != null | pstm != null ) {
					con.close();
					pstm.close();
					
					con = null;
					pstm = null;
					
				}
			}
		
	    	return result;
	 }
	
	 
	 

}
