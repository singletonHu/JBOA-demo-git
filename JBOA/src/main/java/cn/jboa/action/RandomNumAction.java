package cn.jboa.action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.jboa.util.RandomNumUtil;

public class RandomNumAction extends ActionSupport 
{
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	
	public String execute()
	{
		RandomNumUtil randomNum = RandomNumUtil.Instance();
		this.setInputStream(randomNum.getImage());
		ActionContext.getContext().getSession().put("random", randomNum.getString());
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
