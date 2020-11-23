package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import jdk.internal.instrumentation.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    //filter执行类型
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    //filter执行顺序
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    //是否需要执行过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //具体操作：日志记录操作的执行时间和路径
    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        long startTime = (Long) context.get("startTime");
        //请求路径
        String uri = request.getRequestURI();
        //请求时间
        long duration = System.currentTimeMillis() - startTime;

        log.info("uri: " + uri + ", duration: " + duration / 100 + "ms");

        return null;
    }
}
