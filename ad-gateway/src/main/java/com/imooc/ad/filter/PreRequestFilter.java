package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

//将过滤器注册到容器中
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {
    //filter执行类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //filter执行顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否需要执行过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //具体操作，记录该操作的起始时间戳
    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis());
        return null;
    }
}
