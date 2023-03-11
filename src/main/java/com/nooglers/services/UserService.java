package com.nooglers.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final ThreadLocal<UserService> USER_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(UserService::new);

    public static UserService getInstance() {
        return USER_SERVICE_THREAD_LOCAL.get();
    }
}
