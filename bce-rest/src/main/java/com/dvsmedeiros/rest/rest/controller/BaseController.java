package com.dvsmedeiros.rest.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.bce.domain.ApplicationEntity;

@RequestMapping("/")
@CrossOrigin(origins = "*")
public class BaseController extends ApplicationEntity {
}
