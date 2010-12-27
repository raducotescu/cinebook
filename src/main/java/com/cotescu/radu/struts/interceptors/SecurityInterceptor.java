package com.cotescu.radu.struts.interceptors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cotescu.radu.cinebook.model.backend.User;
import com.cotescu.radu.struts.annotations.RequiresAuthentication;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7266247627141662134L;
	public static final String USER_OBJECT = "user";
	public static final String DEFAULT_MSG = "The page or action requested requires registration. Please sign in or register.";
	private List<String> requiresAuthentication;

	public void setRequiresAuthentication(String authentication) {
		this.requiresAuthentication = stringToList(authentication);
	}

	private List<String> stringToList(String authentication) {
		if (authentication != null) {
			String[] list = authentication.split("[ ]*,[ ]*");
			return Arrays.asList(list);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) invocation.getInvocationContext().getSession()
				.get(USER_OBJECT);
		Object action = invocation.getAction();
		boolean annotated = action.getClass().isAnnotationPresent(
				RequiresAuthentication.class);
		if (user == null
				&& (annotated || requiresAuthentication(invocation.getProxy()
						.getNamespace()))) {
			if (action instanceof ValidationAware) {
				((ValidationAware) action).addActionError(DEFAULT_MSG);
			}
			return ActionSupport.LOGIN;
		}
		return invocation.invoke();
	}

	private boolean requiresAuthentication(String namespace) {
		for (String n : requiresAuthentication) {
			if (namespace.equals(n.trim())) {
				return true;
			}
		}
		return false;
	}
}