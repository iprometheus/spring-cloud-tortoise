package com.fangxing.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    //判断该过滤器是否被执行，默认为不执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体逻辑
    @Override
    public Object run() {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request= context.getRequest();

        System.out.println(String.format("rend{} request to {}",request.getMethod(),request.getRequestURL().toString()));

        Object accessToken=request.getParameter("accessToken");
        if(accessToken==null){
            System.out.println("401 error");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }

        System.out.println("access token ok");
        return null;
    }

    //过滤器的类型，它决定过滤器在哪个生命周期中执行。
    //这里定义为pre，代表会在请求被路由之前执行。
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器的执行顺序
    //当请求在一个阶段中存在多个过滤器时，需要根据该方法的返回值来依次执行。
    @Override
    public int filterOrder() {
        return 0;
    }
}
