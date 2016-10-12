/**
 * 
 */
package com.happy3w.autobuy.exp;

/**
 * 系统内部异常。
 *
 * @version 2016年9月1日 下午11:51:11
 * @author Happy3W Cherry
 *
 */
public class SysException extends RuntimeException {

	public SysException(String msg, Throwable e) {
		super(msg, e);
	}

	public SysException(Throwable e) {
		super(e);
	}

}
