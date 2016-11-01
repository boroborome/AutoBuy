/**
 * 
 */
package com.happy3w.autobuy.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.happy3w.autobuy.action.strc.ActStruct;

/**
 * 任务阶段。
 * @version 2016年10月25日上午9:20:32
 * @author happy3w
 */
public class Stage {
	/**
	 * 阶段编码。
	 */
	private String stageCode;
	/**
	 * 阶段名称。
	 */
	private String stageName;
	/**
	 * 执行此阶段的账号。
	 */
	private User user;

	/**
	 * 该阶段所包含的操作。
	 */
	private List<ActStruct> actions= new ArrayList<ActStruct>();
	public String getStageCode() {
		return stageCode;
	}
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public List<ActStruct> getActions() {
		return actions;
	}
	public void setActions(List<ActStruct> actions) {
		this.actions = actions;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Stage(String code,String name,String userid,String password)
	{
		this.stageCode=code;
		this.stageName=name;
		this.user=new User(userid,password);
	}
	public void put(ActStruct action)
	{
		action.setSeqNo(actions.size());
		actions.add(action);
	}
	public void putAll(ActStruct[] list)
	{
		for(ActStruct action:list)
		{
			put(action);
		}
	}
	public ActStruct[] getActions(String operation)
	{
		List<ActStruct> acts = new ArrayList<ActStruct>();
		for(ActStruct act:actions)
		{
			if(act.getOperation().equals(operation))
			{
				acts.add(act);
			}
		}
		return acts.toArray(new ActStruct[0]);
	}
}
