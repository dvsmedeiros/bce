package com.dvsmedeiros.bce.core.controller;

public interface IAdapter<S, D> {

	public D adapt(S source);

}
