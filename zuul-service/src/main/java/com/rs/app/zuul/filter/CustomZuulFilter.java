package com.rs.app.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Component
public class CustomZuulFilter extends ZuulFilter {
    static {
        System.out.println("CustomZuulFilter: static block");
    }

    public CustomZuulFilter() {
        System.out.println("CustomZuulFilter: 0-param constr");
    }

    @Override

    public String filterType() {
        System.out.println("--- CustomZuulFilter: filterType");
        // pre,post,route and error
        return "pre";
    }

    @Override
    public int filterOrder() {
        System.out.println("--- CustomZuulFilter: filterOrder");
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("--- CustomZuulFilter: shouldFilter");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("--- CustomZuulFilter: run");
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader("author", "Ramesh");
        return null;
    }
}
