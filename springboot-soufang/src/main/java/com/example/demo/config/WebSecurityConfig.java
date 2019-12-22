package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.security.AuthorProvider;
import com.example.demo.security.LoginAuthFailHandler;
import com.example.demo.security.LoginUrlEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final  Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	/***
	 * 
	 * prePostEnabled： 确定是否应启用Spring Security的预发布注释。
	 *  securedEnabled：确定是否应启用Spring Security的@Secured 注解。
	 * 
	 **/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 资源访问权限
		http.authorizeRequests()
		        .antMatchers("/admin/login").permitAll()// 管理员登录
				.antMatchers("/static/**").permitAll()// 静态资源
				.antMatchers("/user/login").permitAll()// 用户登录
				.antMatchers("/admin/**").hasRole("ADMIN")//
				.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
			//	.antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
				.and()
				.formLogin()
				.loginProcessingUrl("/login")//定义登录页面对应的url
				.failureHandler(authFailHandler())
				//.loginPage("/login")//自定义login页面，将security默认页面覆盖
		        //.defaultSuccessUrl("/index")    //不管时管理员还是普通用户，登录成功后都跳转到login
				.and()
		        //.failureUrl("/404")
		        //.and()
		        .logout()
		        .logoutUrl("/logout") //等出的url
		        .logoutSuccessUrl("/logout/page")//登出成功后，跳转的url
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(urlEntryPoint())
				.accessDeniedPage("/403");
				

		/****** 以上表示，谁都可以访问这个链接url *******/
		/****** 以下表示，登录对应的url，需要有的权限 ****/
		
		
		//-------------------------/
		//关闭csrf攻击
		http.csrf().disable();
		//同源策略
		http.headers().frameOptions().sameOrigin();
		
		
	}
	/**
	 * 自定义认证策略
	 * @throws Exception 
	 * **/
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//在内存中设定一个用户角色，但是不符合实际场景
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
		
		auth.authenticationProvider(authProvider()).eraseCredentials(true);
		
	}
	
	@Bean
	public AuthorProvider authProvider() {
		
		return new AuthorProvider();
	}
	
	@Bean
	public LoginUrlEntryPoint urlEntryPoint() {
		return new LoginUrlEntryPoint("/user/login");//默认登录页面设置为/user/login
	}
	@Bean
	public LoginAuthFailHandler authFailHandler() {
		return new LoginAuthFailHandler(urlEntryPoint());
	} 
	
	
	
	
	
	
	
	
	

}
