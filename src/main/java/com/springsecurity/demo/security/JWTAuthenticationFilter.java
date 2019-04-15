package com.springsecurity.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.demo.SysUser;
import com.springsecurity.demo.User;
import com.springsecurity.demo.entity.UserEntity;
import com.springsecurity.demo.model.LoginUser;
import com.springsecurity.demo.repository.UserRepository;
import com.springsecurity.demo.service.UserService;
import com.springsecurity.demo.service.impl.UserSerivceImpl;
import com.springsecurity.demo.utils.DateUtils;
import com.springsecurity.demo.utils.GetIpAddress;
import com.springsecurity.demo.utils.JwtTokenUtils;
import com.springsecurity.demo.utils.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.getRememberMe());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        boolean isRemember = rememberMe.get() == 1;

        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
//        SysUser.getInstance(jwtUser.getUsername(),role,jwtUser.getRealName(),jwtUser.getEmail(),jwtUser.getTel());
        User.role=role;
//        System.out.println(user);
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
        String ip= GetIpAddress.getLocalIp(request);
        UserEntity userEntity=new UserEntity();
        userEntity.setId(jwtUser.getId());
        userEntity.setLast_ip(ip);
        try {
            userEntity.setLast_time(DateUtils.dateFormat(new Date(),"yyyy-mm-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(userEntity);
//        UserService userService=new UserSerivceImpl();
//        userService.updateUser(userEntity);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(jwtUser) + ",\"token\":\""+token+"\"}";
        out.write(s);
        out.flush();
        out.close();
        logger.info("USER : " + jwtUser.getUsername() + " LOGIN SUCCESS !  ");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("status","error");
        response.getWriter().write("{\"msg\":\"身份验证失败"  + failed.getMessage()+"\",\"status\":\"error\"}");
    }
}
