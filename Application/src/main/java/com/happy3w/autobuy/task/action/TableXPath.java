/**
 * 
 */
package com.happy3w.autobuy.task.action;

/**
 * 获取表格中内容的Xpath.
 * @version 2016年10月19日上午11:43:53
 * @author happy3w
 */
public class TableXPath {
	/**
	 * 表格的xpath.
	 */
	private String table;
	/**
	 * 相对于表格的row的xpath.
	 */
	private String row;
	/**
	 * 获取值需要满足的条件,row中的cell.
	 */
	private String condition;
	/**
	 * 满足条件后，需要取得的对象,row中的cell.
	 */
	private String target;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String product) {
		this.condition = product;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	
}
