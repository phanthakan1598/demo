package com.example.demo.member.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class MemberException extends BaseException {
	
	private static final long serialVersionUID = -3371503320561323817L;

    protected MemberException(String code) {
        super("member."+code);
    }
    
	protected MemberException(String code, HttpStatus status) {
        super("member."+code, status);
    }
//--------------------------------------------------------------------------------------------------//
	public static MemberException memberAddressUsed() {
		return new MemberException("memberAddress.used", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberUsernameEmpty() {
		return new MemberException("memberUsername.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static MemberException memberAdminOver() {
		return new MemberException("memberAdmin.over", HttpStatus.BAD_REQUEST);
	}
	
	public static MemberException memberPasswordEmpty() {
		return new MemberException("memberPassword.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberNameThEmpty() {
		return new MemberException("memberNameTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberNameEnEmpty() {
		return new MemberException("memberNameEn.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberBirthdayEmpty() {
		return new MemberException("memberBirthday.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberTelEmpty() {
		return new MemberException("memberTel.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberEmailEmpty() {
		return new MemberException("memberEmail.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException addressDetailThEmpty() {
		return new MemberException("addressDetailTh.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException addressDetailEnEmpty() {
		return new MemberException("addressDetailEn.empty", HttpStatus.BAD_REQUEST);
	}
	public static MemberException tambonUuidEmpty() {
		return new MemberException("tambonUuid.empty", HttpStatus.BAD_REQUEST);
	}
	
	
	
	public static MemberException memberUsernameUsed() {
		return new MemberException("memberUsername.used", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberEmailUsed() {
		return new MemberException("memberEmail.used", HttpStatus.BAD_REQUEST);
	}
	
	
	public static MemberException memberIncorrect() {
		return new MemberException("member.incorrect", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberTelIncorrect() {
		return new MemberException("memberTel.incorrect", HttpStatus.BAD_REQUEST);
	}
	public static MemberException memberEmailIncorrect() {
		return new MemberException("memberEmail.incorrect", HttpStatus.BAD_REQUEST);
	}
	
	public static MemberException studentOver() {
		return new MemberException("student.over", HttpStatus.BAD_REQUEST);
	}
//--------------------------------------------------------------------------------------------------//
    public static MemberException userIsEmptyOrNull() {
        return new MemberException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static MemberException userNotFound() {
        return new MemberException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
    
    public static MemberException userIncorrectCurrentPassword() {
        return new MemberException("incorrect.currentPassword", HttpStatus.BAD_REQUEST);
    }
    
    public static MemberException userEmptyCurrentPassword() {
        return new MemberException("empty.currentPassword", HttpStatus.BAD_REQUEST);
    }
    
    public static MemberException userEmptyNewPassword() {
        return new MemberException("empty.newPassword", HttpStatus.BAD_REQUEST);
    }
    
    public static MemberException userStatusEnabledOutOfRange() {
        return new MemberException("status.outOfRange", HttpStatus.BAD_REQUEST);
    }
	
}
