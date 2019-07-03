package com.sxy.www.utils;

import com.sxy.www.model.User;

public class AuthClient {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static void removeUser() {
        threadLocal.remove();
    }

    public static User getUser() {
        return threadLocal.get();
    }
}
