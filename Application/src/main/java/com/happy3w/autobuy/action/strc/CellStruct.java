/**
 * 
 */
package com.happy3w.autobuy.action.strc;

/**
 * 操作单元格所需参数。
 * @version 2016年10月25日上午10:06:30
 * @author happy3w
 */
public class CellStruct extends ActStruct {
	private String table;
	private String row;
	private int startRw;
	private int endRw;
	private String filter;
	private String conditionName;

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

	public int getStartRw() {
		return startRw;
	}

	public void setStartRw(int startRw) {
		this.startRw = startRw;
	}

	public int getEndRw() {
		return endRw;
	}

	public void setEndRw(int endRw) {
		this.endRw = endRw;
	}

	public String getConditionName() {
		return conditionName;
	}

	public String getFilter() {
		return filter;
	}

	public void setCondition(String filter) {
		this.filter = filter;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public CellStruct(String operation,String className, String paramName, String table, int startRw, int endRw,String target,String filter,
			String conditionName,String condition ,String returnName) {
		super(operation, className, target, paramName, returnName, condition);
		this.table = table;
		this.row = "tr";
		this.startRw = startRw;
		this.endRw = endRw;
		this.filter = filter;
		this.conditionName = conditionName;
	}
}
