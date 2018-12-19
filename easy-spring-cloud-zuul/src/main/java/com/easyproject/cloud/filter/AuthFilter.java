package com.easyproject.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	public Object run() {
		// 过滤器业务
		logger.info("PreFilter 1：认证过滤器！ ");

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(String.format("%s requ est to %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("accessToken");

		logger.info("accessToken is: " + accessToken);
		if (accessToken == null) {
//			ctx.setSendZuulResponse(false); // 不对其进行路由
//			ctx.setResponseStatusCode(401); // 返回的错误码
//			ctx.setResponseBody("Invalid authentication!");
			logger.info("accessToken is null");
		}
		return null;

	}

	public boolean shouldFilter() {
		return true; // 判断该过滤器是否需要被执行。false 可以跳过该滤器。
	}

	public int filterOrder() {
		return 0; // 过滤器的执行顺序，按由小到大顺序执行。
	}

	public String filterType() {
		return FilterConstants.PRE_TYPE; // 前置过滤器
	}
}
