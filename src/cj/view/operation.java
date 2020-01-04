package cj.view;

import java.util.InputMismatchException;

import cj.service.ChengJiDetailService;
import cj.service.ChengJiService;
import cj.service.LoginService;

public interface operation{
	
	ChengJiService chengJiService = ChengJiService.getInstance();
	ChengJiDetailService chengJiDetailService = ChengJiDetailService.getInstance();
	LoginService loginService = LoginService.getInstantnce();
	
	String operation() throws InputMismatchException;
}
