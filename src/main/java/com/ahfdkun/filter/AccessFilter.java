package com.ahfdkun.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		log.info("Send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		String accessToken = request.getParameter("accessToken");
		if (StringUtils.isEmpty(accessToken)) {
			log.warn("Access token is empty");
			sendError(context);
			return null;
		}
		log.info("Access token is OK");
		return null;
	}

	private void sendError(RequestContext context) {
		RuntimeException ex = new RuntimeException("accessToken is null");
		context.set("error.status_code", 400);
		context.set("error.exception", ex);
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
