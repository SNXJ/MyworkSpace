package com.ucgcn.rlct.ui.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class ShufflingAdapter<ImageView> extends PagerAdapter {
	private List<ImageView> imageViewList ;
	private ViewPager mViewPager;
	public ShufflingAdapter(List<ImageView> list,ViewPager viewPager) {
		super();
		this.imageViewList = list;
		this.mViewPager = viewPager;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = imageViewList.get(position % imageViewList.size());
		mViewPager.addView((View) iv);
		return iv;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		mViewPager.removeView((View) imageViewList.get(position % imageViewList.size()));
	}
}
