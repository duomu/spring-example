package com.yll.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author：linlin.yang
 * @date：2017/10/11 19:37
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("duomu").password("123").roles("USER");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT name,password,true FROM USER WHERE name= ?")
                .authoritiesByUsernameQuery("SELECT user.name,role.name FROM user,role,user_role WHERE user.id=user_role.user_id AND user_role.role_id=role.id and user.name = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/log/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/")//登录页
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/log/listLogByPage")
                .failureForwardUrl("/user/loginPage?error=true")
                .usernameParameter("username")//和登录页面提交的用户名参数一致
                .passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/user/logout")
//                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/")//登录页
                .and()
                .rememberMe()
                .rememberMeCookieName("user")//无法读取此cookie，没有存储成功？
//                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(604800)//1周
                .key("user")
                .and()
                .csrf();//启用csrf则登出必须使用post请求
    }
}
