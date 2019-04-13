package com.gavin.itnews.interceptor;

import com.gavin.itnews.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gavin
 * on 2019/4/11 21:52
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        User user = (User) request.getSession().getAttribute("user");
        String requestURI = request.getRequestURI(); // /news/13
        if(user!=null){
            return true;
        }else {
//            request.getRequestDispatcher("/home?pop=1").forward(request,response);
            response.sendRedirect(contextPath+"/home?pop=0");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
