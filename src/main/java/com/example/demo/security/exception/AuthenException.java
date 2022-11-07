package com.example.demo.security.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class AuthenException extends BaseException {

	
	private static final long serialVersionUID = 1884987268860286201L;
	
	public AuthenException(String code, HttpStatus status) {
		super("authen."+code, status);
	}

   public static AuthenException authenUsernameEmpty() {
        return new AuthenException("memberUsername.empty", HttpStatus.BAD_REQUEST);
    }
   
   public static AuthenException authenPasswordEmpty() {
       return new AuthenException("memberPassword.empty", HttpStatus.BAD_REQUEST);
   }
   
   public static AuthenException authenIncorrect() {
        return new AuthenException("memberUsername.memberPassword.incorrect", HttpStatus.BAD_REQUEST);
   }
}
