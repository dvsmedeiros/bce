package com.dvsmedeiros.bce.core.controller;

public interface ITask extends Runnable {

	Long getFixedRate();

	boolean isRestart();
}
