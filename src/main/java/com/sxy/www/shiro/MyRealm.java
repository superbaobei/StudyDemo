package com.sxy.www.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;

/**
 * @ClassName MyRealm
 * @Description TODO
 * @Author sunxiangyu
 * @Email sunxiangyu@huli.com
 * @Date 2019-10-18 17:55
 * @Version 1.0
 **/
@Slf4j
public class MyRealm extends AuthorizingRealm {

    public static final String NAME = "sun";

    public static final String PASS = "123";

    public MyRealm() {
        log.info("MyRealm created");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        HashSet<String> roles = new HashSet<>();
        roles.add("user");
        roles.add("admin");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(NAME,PASS,getName());
        return authenticationInfo;
    }

}
