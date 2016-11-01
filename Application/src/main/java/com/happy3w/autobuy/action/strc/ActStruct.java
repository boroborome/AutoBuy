/**
 * 
 */
package com.happy3w.autobuy.action.strc;



/**
 * 活动。
 * @version 2016年10月25日上午9:23:00
 * @author happy3w
 */
public class ActStruct {
	
	/**
	 * action所属的操作,可用于对细粒度action分组形成有业务含义的业务动作。
	 */
	private String operation;
	/**
	 * 实现Action的class.
	 */
	private String className;
	/**
	 * 操作所在的顺序。
	 */
	private int seqNo;
	/**
	 * Action要操作的具体元素。
	 */
	private String target;
	/**
	 * 参数名。
	 */
	private String paramName;
	/**
	 * 返回值名。
	 */
	private String returnName;
	/**
	 * 某个操作的重复刷新页面时间按。
	 */
	private String refreshTime;
	/**
	 * 验证操作成功的条件。
	 */
	private String condition;
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getReturnName() {
		return returnName;
	}
	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}
	public String getFilter() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
	public ActStruct(String operation,String className,String target,String paramName,String returnName,String condition)
	{
		this.operation=operation;
		this.className=className;
		this.target=target;
		this.paramName=paramName;
		this.returnName=returnName;
		this.condition=condition;
	}
}
