package com.dvsmedeiros.bce.core.controller.impl;

import java.util.Map;

public interface INavigatorContext {

	public Map<String, Object> getAttributes();

	public void setAttribute(String key, Object attribute);

	public <R> R getAttribute(String key);

}
