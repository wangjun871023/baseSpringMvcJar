package com.macrosoft.core.security.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.macrosoft.model.User;

public class MyRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}  
 
//    private AccountManager accountManager;  
//    public void setAccountManager(AccountManager accountManager) {  
//        this.accountManager = accountManager;  
//    }  
//  
//    /** 
//     * 授权信息 
//     */  
//    protected AuthorizationInfo doGetAuthorizationInfo(  
//                PrincipalCollection principals) {  
//        String username=(String)principals.fromRealm(getName()).iterator().next();  
//        if( username != null ){  
//            User user = accountManager.get( username );  
//            if( user != null && user.getRoles() != null ){  
//                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
//                for( SecurityRole each: user.getRoles() ){  
//                        info.addRole(each.getName());  
//                        info.addStringPermissions(each.getPermissionsAsString());  
//                }  
//                return info;  
//            }  
//        }  
//        return null;  
//    }  
//  
//    /** 
//     * 认证信息 
//     */  
//    protected AuthenticationInfo doGetAuthenticationInfo(  
//                AuthenticationToken authcToken ) throws AuthenticationException {  
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
//        String userName = token.getUsername();  
//        if( userName != null && !"".equals(userName) ){  
//            User user = accountManager.login(token.getUsername(),  
//                            String.valueOf(token.getPassword()));  
//  
//            if( user != null )  
//                return new SimpleAuthenticationInfo(  
//                            user.getLoginName(),user.getPassword(), getName());  
//        }  
//        return null;  
//    }  
  
}  