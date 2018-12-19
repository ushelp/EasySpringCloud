package com.easyproject.cloud.filter;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class PostFilter extends ZuulFilter {
	Logger logger = LoggerFactory.getLogger(PostFilter.class);
	public Object run() {
		// 过滤器业务
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		System.out.println("PostFilter: 后置过滤器！" + String.format("response's content type is %s", response.getStatus()));
		
//		RequestContext context = RequestContext.getCurrentContext();
//    	HttpServletResponse servletResponse = context.getResponse();
//		servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
		
		return null;
	}

	public boolean shouldFilter() {
		return true; // 判断该过滤器是否需要被执行。false 可以跳过该滤器。
	}

	public int filterOrder() {
		return 0;// 过滤器的执行顺序，按由小到大顺序执行。
	}

	public String filterType() {
		return FilterConstants.POST_TYPE; // 后置过滤器，无论有无异常
	}
}
