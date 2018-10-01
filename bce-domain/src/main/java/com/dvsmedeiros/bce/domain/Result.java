package com.dvsmedeiros.bce.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class Result extends ApplicationEntity {

	public static final String RESULT_KEY = "result";
	public static final String RESULTS_KEY = "results";

	private Map<String, Object> params;
	private String message;
	private boolean error;

	public Result() {
		this.params = new HashMap<String, Object>();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean hasError() {
		return this.error;
	}

	public void setError() {
		this.error = true;
	}

	public void addEntity(String key, Object entity) {
		Optional.ofNullable(entity).ifPresent(e -> params.put(key, entity));
	}

	public void addEntity(Object entity) {
		Optional.ofNullable(entity).ifPresent(e -> params.put(RESULT_KEY, entity));
	}

	public void addEntities(Stream<?> result) {
		Optional.ofNullable(result).ifPresent(r -> params.put(RESULTS_KEY, r));
	}

	public <R> R getEntity(String key) {
		return (R) Optional.ofNullable(params.get(key));
	}

	public <R> R getEntity() {
		return (R) Optional.ofNullable(params.get(RESULT_KEY));
	}

	public <R> R getEntities() {
		return (R) Optional.ofNullable(params.get(RESULTS_KEY));
	}

}
