package com.dvsmedeiros.bce.core.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class NavigatorContext implements INavigatorContext {

	private Map<String, Object> params;

	public NavigatorContext() {
		this.params = new HashMap<>();
	}

	public Map<String, Object> getParams() {
		return new HashMap<>(this.params);
	}

	@Override
	public void setAttribute(String key, Object attribute) {
		this.params.put(key, attribute);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <R> R getAttribute(String key) {
		return (R) params.get(key);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return params;
	}

}
