package com.ucgcn.rlct.ui.mian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.ab.activity.AbActivity;
import com.ab.util.AbViewUtil;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.ucgcn.rlct.R;


public class MainActivitys extends AbActivity {
	private SlidingMenu menu;
	private MainMenuFragment mMainMenuFragment = null;
	private MainContentFragment mMainContentFragment = null;

	private AbTitleBar mAbTitleBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.sliding_menu_content);
		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleBarGravity(Gravity.CENTER, Gravity.CENTER);
		mAbTitleBar.setPadding((int) AbViewUtil.px2dip(MainActivitys.this,60),
				0, (int) AbViewUtil.px2dip(MainActivitys.this, 40), 0);
		mAbTitleBar.setTitleTextSize((int) AbViewUtil.px2dip(MainActivitys.this,40));
		mAbTitleBar.setTitleText(R.string.app_name);
		mAbTitleBar.setLogo(R.drawable.setting);
		
		
		//设置标题栏右边的资源
		ImageButton ib_titlebar_right= new ImageButton(MainActivitys.this);
		ib_titlebar_right.setBackgroundResource(R.drawable.phone);
		mAbTitleBar.getRightLayout().setPadding(0, 0, (int) AbViewUtil.px2dip(MainActivitys.this, 40), 0);
		mAbTitleBar.addRightView(ib_titlebar_right);
		//打电话
		ib_titlebar_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建意图
				Intent intent = new Intent();
				// 设置拨打动作
				intent.setAction(Intent.ACTION_CALL);
				// 设置数据
				intent.setData(Uri.parse("tel://01053366763"));
				// 开启
				startActivity(intent);
				
			}
		});
		  
		mMainContentFragment = new MainContentFragment();
		// 主视图的Fragment添加
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_content_frame, mMainContentFragment).commit();
		
		mMainMenuFragment = new MainMenuFragment();

		// SlidingMenu的配置
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// menu视图的Fragment添加
		menu.setMenu(R.layout.sliding_menu_menu);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, mMainMenuFragment).commit();

		mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (menu.isMenuShowing()) {
					menu.showContent();
				} else {
					menu.showMenu();
				}
			}
		});
	}
	@Override
	public void onBackPressed() {
		finish();
	}
	
}
