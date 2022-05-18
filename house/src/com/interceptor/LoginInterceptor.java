package com.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(o instanceof HandlerMethod){//是不是controller方法

            HandlerMethod hm = (HandlerMethod)o;

            Method md = hm.getMethod();//取方法

            Login login = md.getAnnotation(Login.class);//通过方法取注解

            if(login != null){
                boolean ck = login.check();// 取注解属性的值
                if(ck){//要求有login注解并且check=true才验证登录
                    HttpSession session = httpServletRequest.getSession();
                    Object uobj = session.getAttribute("userInfo");
                    if(uobj == null){//没有登录
                        loginError(httpServletResponse);
                        System.out.println("-----------没有登录,请先登录!");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //json的登录错误信息
    private void loginError(HttpServletResponse httpServletResponse) throws Exception{
        PrintWriter out = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();

        map.put("loginCode",1);
        map.put("loginInfo","没有登录,请先登录!");

        String js = JSON.toJSONString(map);

        out.print(js);
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //
    }
}
