//
//package com.quadecom.QuadrantEcom.Security;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/users/**").permitAll()  // Permit access to /users endpoints without authentication
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();  // Use basic authentication
//    }
//}