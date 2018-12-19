package com.easyproject.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class LogFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	public Object run() {
		// 过滤器业务
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println("PreFilter 2: 日志过滤器! " + String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

//		testThrowException();

		return null;
	}

	public void testThrowException() {
		throw new RuntimeException("异常测试！");
	}
	
	public boolean shouldFilter() {
		return true; // 判断该过滤器是否需要被执行。false 可以跳过该滤器。
	}
	public int filterOrder() {
		return 1;// 过滤器的执行顺序，按由小到大顺序执行。
	}
	public String filterType() {
		return FilterConstants.PRE_TYPE; // 前置过滤器
	}

}
