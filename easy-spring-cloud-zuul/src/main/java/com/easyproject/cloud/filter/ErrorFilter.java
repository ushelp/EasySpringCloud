package com.easyproject.cloud.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ErrorFilter extends ZuulFilter {
	
	Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	public Object run() {
		// 过滤器业务
		logger.warn("ErrorFilter 1：错误过滤器！ ");
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable t = ctx.getThrowable();
		logger.error(ctx.getFilterExecutionSummary().toString(),t);
		ctx.setSendZuulResponse(false); // 不对其进行路由
		ctx.setResponseStatusCode(401); // 返回的错误码
		ctx.setResponseBody("过滤器出错!");
		return null;
	}

	public boolean shouldFilter() {
		return true; // 判断该过滤器是否需要被执行。false 可以跳过该滤器。
	}

	public int filterOrder() {
		return 0;// 过滤器的执行顺序，按由小到大顺序执行。
	}

	public String filterType() {
		return FilterConstants.ERROR_TYPE; // 错误过滤器
	}
}
