package com.example.demo.security.util;

import com.example.demo.security.model.UserAuth;

public interface JWTAuthImpl {
	UserAuth getCurrentUser();
}
