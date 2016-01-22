package com.leehat.exception;

import java.util.Date;

import org.aopalliance.intercept.Joinpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class ExceptionManager {

	private Log logger = LogFactory.getLog(ExceptionManager.class);
	
	public void doBefore(Joinpoint jp){
		
	}
	
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		Object result = null;
		try {
			long start_time = new Date().getTime();
			result = pjp.proceed();
			long end_time = new Date().getTime();
			long mutil_time = end_time -start_time;
			System.out.println("耗时"+((double)mutil_time)/1000+"秒");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = "error";
		}
		return result;
	}
	
	public void doAfter(JoinPoint jp){
	}
}
