package com.ufrb.lardosidosos.api.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

	public static final String SECRET = "LarDosIdososApi";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/usuarios/sign-up";
	public static final Long EXPIRATION_TIME = 86400000L; //1 Dia
	
//	public static void main(String[] args) {
//		System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//	}
	
}
