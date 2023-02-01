package com.ntpc.pradip.mobility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSUtil {
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static String generateOTP() {
		int randomPin   =(int)(Math.random()*9000)+1000;
		String otp  =String.valueOf(randomPin);
		return otp;
	}
	
	public static void sendSms(String emp_num) throws Exception{
		
		DBUtil db=new DBUtil();
		
		String OTP=generateOTP();
		
		String mobile=db.getUserMobile(emp_num);
		
		String pattern="dd-MM-yy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String date = simpleDateFormat.format(new Date());
		
		String message="PRADIP One Time Password(OTP) for user " + emp_num + " Login At: " + date+ " is: "+OTP;
		
		String message_final=URLEncoder.encode(message, "UTF-8");
		
		System.out.println(message_final);
		
		// http://api.instaalerts.zone/SendSMS/sendmsg.php?uname=ROLNTPCLIVE&pass=f5e7J!G9&send=PRADIP
		//	&msg=PRADIP One Time Password(OTP) for user 009697 Login At: 23-9-2019 04:55:41 is: 6789&dest=9438334255
		
		String url = "http://api.instaalerts.zone/SendSMS/sendmsg.php?uname=ROLNTPCLIVE&pass=f5e7J!G9&send=PRADIP&msg=" + message_final + "&dest="+mobile;

		//System.out.println(url);
		
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.0.1", 8080));
		
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		
		db.insertOTPInfo(emp_num, OTP);

		//print result
		System.out.println(response.toString());
		
	}

}
