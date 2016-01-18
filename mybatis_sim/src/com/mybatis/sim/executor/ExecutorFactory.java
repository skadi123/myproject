package com.mybatis.sim.executor;

public class ExecutorFactory {

	private static ExecutorFactory instance = null;

	private ExecutorFactory() {
	}
	
	/**
	 * 做一个单例模式
	 * @return
	 */
	public static ExecutorFactory getInstance() {
		if (instance == null) {
			instance = new ExecutorFactory();
		}
		return instance;
	}
	
	//调用IExecutor接口实例化
	public IExecutor getExecutor(int type) {
		IExecutor executor = null;
		//判断configuration.xml下setting的value值，传值不同所做操作不同
		switch (type) {
		case 1:
			executor = new SimpleExecutor();
			break;
		default:
			break;
		}
		return executor;
	}
	
}
