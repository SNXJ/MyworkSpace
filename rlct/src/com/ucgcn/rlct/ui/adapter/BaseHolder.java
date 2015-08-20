package com.ucgcn.rlct.ui.adapter;

import android.view.View;

public abstract class BaseHolder<T> {

	private View view;
	private T data;
	public BaseHolder() {
		view = initView();
		view.setTag(this);
	}
	public void setData(T data){
		this.data = data;
		//填充完数据。刷新界面
		refreshView();
	}
	public T getData(){
		return data;
	}
	public abstract void refreshView();

	public View getRootView() {
		return view;
	}
	public abstract View initView() ;
}
