package com.ucgcn.rlct.ui.adapter;

import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	private List list;
	private BaseHolder holder;
	public MyBaseAdapter(List<T> list) {
		setData(list);
	}
	public void setData(List<T> list) {
		this.list = list;
	}
	public List<T> getData(){
		return list;
	}
	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(null == convertView){
			holder = getHolder();
		}else{
			holder = (BaseHolder) convertView.getTag();
		}
		holder.setData(list.get(position));
		return holder.getRootView();
	}
	public abstract BaseHolder getHolder();
}
