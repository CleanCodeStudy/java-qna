package com.ccstudy.qna.domain.support;

import org.springframework.util.ObjectUtils;

public class AccessUserStore {

    private static ThreadLocal<AccessUser> accessUserThreadLocal = new ThreadLocal<>();


    public static AccessUser getUser() {
        AccessUser accessUserInfo = accessUserThreadLocal.get();
        if (accessUserInfo != null) {
            return accessUserInfo;
        }
        return new AccessUser();
    }

    public static Long getUserId() {
        AccessUser user = getUser();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    public static void setAccessUser(AccessUser accessUser) {
        accessUserThreadLocal.set(accessUser);
    }

    public static void clear() {
        accessUserThreadLocal.remove();
    }

    public static boolean hasUser() {
        return !ObjectUtils.isEmpty(accessUserThreadLocal.get());
    }
}
