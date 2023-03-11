package com.nooglers.services;

import com.nooglers.dao.CookieDao;
import com.nooglers.dao.UserDao;

import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthService {
    private static final ThreadLocal<AuthService> AUTH_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(AuthService::new);
    private final CookieDao cookieDao = CookieDao.getInstance();

    public static AuthService getInstance() {
        return AUTH_SERVICE_THREAD_LOCAL.get();
    }
}
