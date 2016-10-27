/**
 * 
 */
package com.happy3w.autobuy.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务
 * @version 2016年10月25日上午9:18:33
 * @author happy3w
 */
public class Task {
	/**
	 * 任务编码，唯一。
	 */
	private String taskCode;
	/**
	 * 任务名称。
	 */
	private String taskName;
	/**
	 * 任务执行所包含的阶段。
	 */
	private Map<String,Stage> stages = new HashMap<String,Stage>();
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Collection<Stage> getStages() {
		return stages.values();
	}

	public void put(Stage stage)
	{
		this.stages.put(stage.getStageCode(),stage);
	}
	public void putAll(Stage[] list)
	{
		for(Stage stage:list)
		{
			this.stages.put(stage.getStageCode(), stage);
		}
	}
	public Task(String code,String name)
	{
		this.taskCode=code;
		this.taskName=name;
	}
	public Stage getStage(String stageCode)
	{
		return this.stages.get(stageCode);
	}
}
