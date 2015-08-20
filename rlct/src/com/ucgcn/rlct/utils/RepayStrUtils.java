package com.ucgcn.rlct.utils;

public class RepayStrUtils {
	
	public static String getBefore1(String str){
		int indext = 0 ;
		for(int i =0;i<str.length();i++){
			if(".".equals(str.charAt(i))){
				indext = i;
			}
		}
		
		return str.substring(0,indext);
	}
	
	public static String getAfter2(String str){
		int indext = 0 ;
		for(int i =0;i<str.length();i++){
			if(".".equals(str.charAt(i))){
				indext = i;
			}
		}
		
		return str.substring(indext);
	}
}
