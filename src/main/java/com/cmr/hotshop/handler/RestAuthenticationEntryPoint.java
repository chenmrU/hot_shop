package com.cmr.hotshop.handler;

import cn.hutool.json.JSONUtil;
import com.cmr.hotshop.common.resp.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenmengrui
 * @Description: 当未登录或token失效时，返回JSON格式的结果
 * @date 2019/12/26 19:35
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Response.unauthorized(e.getMessage())));
        response.getWriter().flush();
    }
}
