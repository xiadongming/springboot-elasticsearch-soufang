package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint{

	private PathMatcher pathMatcher = new AntPathMatcher();
	
	private final Map<String,String> authEntryPointMap;
	
	public LoginUrlEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
		authEntryPointMap = new HashMap<String, String>();
		//普通用户登录
		authEntryPointMap.put("/user/**", "/user/login");
		//管理员
		authEntryPointMap.put("/admin/**", "/admin/login");
	}
    /***
     * 根据请求跳转到指定的页面
     * ***/
	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {

		String uri = request.getRequestURI().replace(request.getContextPath(), "");

		 for (Entry<String, String> entry : this.authEntryPointMap.entrySet()) {
			if(this.pathMatcher.match(entry.getKey(), uri)) {
				return entry.getValue();
			}
		}
		return super.determineUrlToUseForThisRequest(request, response, exception);
	}

}
