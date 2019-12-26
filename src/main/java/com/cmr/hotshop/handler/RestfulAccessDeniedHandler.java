package com.cmr.hotshop.handler;

import cn.hutool.json.JSONUtil;
import com.cmr.hotshop.common.resp.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenmengrui
 * @Description: 当用户没有访问权限时的处理器，用于返回JSON格式的处理结果
 * @date 2019/12/26 19:28
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Response.unauthorized(e.getMessage())));
        response.getWriter().flush();
    }
}
