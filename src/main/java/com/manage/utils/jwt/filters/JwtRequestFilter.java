/*
package com.manage.utils.jwt.filters;

import com.manage.service.user.UserService;
import com.manage.utils.dto.UserDto;
import com.manage.utils.exception.JwtTokenException;
import com.manage.utils.jwt.JwtTokenUtil;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter implements Filter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
   */
/* @Value("${ali.properties}")
    private String properties;*//*

    private List<String> excludeUrls; //for show url by customerUsers

    public JwtRequestFilter(UserService userService,JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //for show url by people: (notFilter)
//        System.out.println(properties);
        excludeUrls = new ArrayList<>();
        */
/*excludeUrls.add("/api/user/login");
        excludeUrls.add("/api/user/add");
        excludeUrls.add("/api/user/getAll");
        excludeUrls.add("/api/color/");
        excludeUrls.add("/api/customer/delete/1");
        excludeUrls.add("/api/utils/upload/files/");
        excludeUrls.add("/api/productCategory/getAll");*//*

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //first, not filter urls:
            String url = ((HttpServletRequest)servletRequest).getRequestURI().toLowerCase();
            if (excludeUrls.stream().anyMatch(url::startsWith)){
                filterChain.doFilter(servletRequest,servletResponse);//build
                return;
            }

            //read token header and if==ok set token:
            String requestTokenHeader = ((HttpServletRequest)servletRequest).getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("request token header dose not set");
            //get token and username:
            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new JwtTokenException("username can not resolve");
            //username == username:
            UserDto userDto = new UserDto(userService.getByUsername(username));
            if (!jwtTokenUtil.validateToken(token,userDto))
                throw new JwtTokenException("invalid token");

            filterChain.doFilter(servletRequest,servletResponse);//build

        }catch (JwtTokenException ex){
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");//for acl
        }catch (Exception ex){
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());//for exception
        }
    }
}
*/
