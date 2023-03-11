package com.nooglers.dao;


import com.nooglers.domains.AppCookie;
import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieDao extends BaseDAO<AppCookie, String> {

    private static final ThreadLocal<CookieDao> cookieDaoLocal = ThreadLocal.withInitial(CookieDao::new);


    public boolean removeCookie(Integer userId) {
        begin();
        final int i = entityManager.createQuery("delete from cookie c where c.user.id=?1")
                .setParameter(1 , userId).executeUpdate();

        commit();
        return i!=0;
    }

    public static CookieDao getInstance() {

        return cookieDaoLocal.get();
    }
}
