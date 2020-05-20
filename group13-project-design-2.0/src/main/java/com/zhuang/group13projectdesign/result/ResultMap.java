package com.zhuang.group13projectdesign.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回结果集")
public class ResultMap {

	//状态
	public static final String SUCCESS = "1";
	public static final String ERROR = "0";
	public static final String TOKEN = "-1";
	//返回的消息
	@ApiModelProperty("返回的消息")
	private String msg;
	//返回状态
	@ApiModelProperty("返回状态")
	private String state;
	//返回数据
	@ApiModelProperty("返回数据")
	private Object data;
	
	public ResultMap() {
		super();
	}
	
	public ResultMap(String msg, String state, Object data) {
		super();
		this.msg = msg;
		this.state = state;
		this.data = data;
	}
	/**
	 * 
	 * @param result 结果集
	 * @param msg	返回信息
	 * @param state 返回状态
	 * @param data 返回数据
	 * @return
	 */
	public static ResultMap getResult(ResultMap result , String msg, String state, Object data){
		if (result==null) {
			return new ResultMap(msg, state, data);
		}else {
			result.setData(data);
			result.setMsg(msg);
			result.setState(state);
			return result;
		}
	}
	
	@Override
	public String toString() {
		return "ResultMap [msg=" + msg + ", state=" + state + ", data=" + data + "]";
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


	public ResultMap(String msg, String state) {
		this.msg = msg;
		this.state = state;
	}
}
