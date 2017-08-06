package cn.cnr.admin.base.entity;

/**
 * 
 * 类功能描述:controller返回对象 创建者： 余超 创建日期： 2016年7月2日
 * 
 * 修改内容： 修改者： 修改日期：
 */
public class Msg {
	private boolean success;
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Msg(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Msg() {
		super();
		this.success=true;
		this.msg = "";
	}

}
