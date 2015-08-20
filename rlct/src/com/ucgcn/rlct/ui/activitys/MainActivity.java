package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.ab.util.AbViewUtil;
import com.ab.view.sliding.AbSlidingPlayView;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.UserInfo;

public class MainActivity extends BaseActivity implements OnClickListener {
	// 主页面
	private AbSlidingPlayView mSlidingPlayView;
	private TextView tv_title;// 顶部字体显示（融联创投）
	private RelativeLayout rl_loan, rl_repayment, rl_share;// 贷款，还款，分享页面
	private ImageButton ib_top_left, ib_tob_right;
	private ImageButton ib_menu_exit;
	private RelativeLayout menu_my_data;// 菜单我的资料（账户信息）
	private TextView tv_user_name;// 用户名
	private SharedPreferences sp;

//	private LinearLayout ll_menu;

	private TextView tv_menu_login;// 登录
	private TextView tv_menu_regist;// 注册
	private RelativeLayout rl_login_regist;
	
	private boolean isExit = false;
	
	

	
	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);

		rl_loan = (RelativeLayout) findViewById(R.id.rl_loan);// 贷款
		rl_loan.setOnClickListener(this);
		rl_repayment = (RelativeLayout) findViewById(R.id.rl_repayment);// 还款
		rl_repayment.setOnClickListener(this);
		rl_share = (RelativeLayout) findViewById(R.id.rl_share);// 分享
		rl_share.setOnClickListener(this);
		ib_menu_exit = (ImageButton) findViewById(R.id.ib_menu_exit);
		ib_menu_exit.setOnClickListener(this);

//		ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
//		tv_menu_login = (TextView) findViewById(R.id.tv_menu_login);
//		tv_menu_regist = (TextView) findViewById(R.id.tv_menu_regist);
//		tv_menu_login.setOnClickListener(this);
//		tv_menu_regist.setOnClickListener(this);
//		rl_login_regist = (RelativeLayout) findViewById(R.id.rl_login_regist);
		tv_user_name = (TextView) findViewById(R.id.tv_user_name);
		

		ib_top_left = (ImageButton) findViewById(R.id.ib_top_left);
		ib_tob_right = (ImageButton) findViewById(R.id.ib_top_right);
		tv_title = (TextView) findViewById(R.id.tv_top);
//		tv_title.setTextSize(AbViewUtil.px2sp(MainActivity.this, 40));
//		tv_title.setText(R.string.app_name);

		String username = sp.getString("username", "");
		isExit = sp.getBoolean("isExit", false);
		if("".equals(username)){
			Intent registActivity = new Intent(MainActivity.this,
					RegistActivity.class);
			startActivity(registActivity);
			
			finish();
		}else{
			if(!isExit){
				String tempstr = username.substring(3, 7);
				String showUserName = username.replaceAll(tempstr, "****");
				tv_user_name.setText("Hi," + showUserName);
			}else{
				Intent loginActivity = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(loginActivity);
				finish();
			}
//			ll_menu.setVisibility(View.VISIBLE);
//			rl_login_regist.setVisibility(View.INVISIBLE);
		}
	
		menu_my_data = (RelativeLayout) findViewById(R.id.menu_my_data);
		menu_my_data.setOnClickListener(this);
		// title左边按钮打开设置
		ib_top_left.setOnClickListener(this);
		// 右边的电话按钮
		ib_tob_right.setOnClickListener(this);

		mSlidingPlayView = (AbSlidingPlayView) findViewById(R.id.mAbSlidingPlayView);

		final View mPlayView = mInflater.inflate(R.layout.play_view_item, null);
		ImageView mPlayImage = (ImageView) mPlayView
				.findViewById(R.id.mPlayImage);
		TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
		mPlayText.setText("1111111111111");
		mPlayImage.setBackgroundResource(R.drawable.banner_1);

		final View mPlayView1 = mInflater
				.inflate(R.layout.play_view_item, null);
		ImageView mPlayImage1 = (ImageView) mPlayView1
				.findViewById(R.id.mPlayImage);
		TextView mPlayText1 = (TextView) mPlayView1
				.findViewById(R.id.mPlayText);
		mPlayText1.setText("2222222222222");
		mPlayImage1.setBackgroundResource(R.drawable.banner_2);

		final View mPlayView2 = mInflater
				.inflate(R.layout.play_view_item, null);
		ImageView mPlayImage2 = (ImageView) mPlayView2
				.findViewById(R.id.mPlayImage);
		TextView mPlayText2 = (TextView) mPlayView2
				.findViewById(R.id.mPlayText);
		mPlayText2.setText("33333333333333333");
		mPlayImage2.setBackgroundResource(R.drawable.banner_3);

		mSlidingPlayView.setNavHorizontalGravity(Gravity.RIGHT);
		mSlidingPlayView.addView(mPlayView);
		mSlidingPlayView.addView(mPlayView1);
		mSlidingPlayView.addView(mPlayView2);

	}

	@Override
	protected void onResume() {
		mSlidingPlayView.startPlay();
		super.onResume();
	}

	public void onPause() {
		mSlidingPlayView.stopPlay();
		super.onPause();
	}

	@Override
	public void finish() {
		if(mSlidingPlayView!=null){
			mSlidingPlayView.stopPlay();
		}
		super.finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_loan:// 贷款
			Intent loanActivity = new Intent(MainActivity.this,
					LoanActivity.class);
			startActivity(loanActivity);
			break;
		case R.id.rl_repayment:// 还款
			Intent repaymentActivity = new Intent(MainActivity.this,
					RepaymentActivity.class);
			startActivity(repaymentActivity);
			break;
		case R.id.rl_share:// 分享
			Intent shareActivity = new Intent(MainActivity.this,
					ShareActivity.class);
			startActivity(shareActivity);
			break;

		case R.id.menu_my_data:
			Intent accountInfoActivity = new Intent(MainActivity.this,
					AccountInfoActivity.class);
			startActivity(accountInfoActivity);
			break;
		case R.id.ib_top_left:
			Intent settingActivity = new Intent(MainActivity.this,
					SettingActivity.class);
			startActivity(settingActivity);
			break;
		case R.id.ib_top_right:
			// 创建意图
			Intent intent = new Intent();
			// 设置拨打动作
			intent.setAction(Intent.ACTION_CALL);
			// 设置数据
			intent.setData(Uri.parse("tel://01053366763"));
			// 开启
			startActivity(intent);
			break;
		case R.id.ib_menu_exit:// 退出登录
			Intent loginActivity = new Intent(MainActivity.this,
					LoginActivity.class);
			startActivity(loginActivity);
			sp.edit().putBoolean("isExit", true).commit();
			finish();
//			ll_menu.setVisibility(View.INVISIBLE);
//			rl_login_regist.setVisibility(View.VISIBLE);
			break;
//		case R.id.tv_menu_login:// 登录
//			Intent loginActivity = new Intent(MainActivity.this,
//					LoginActivity.class);
//			startActivity(loginActivity);
//			break;
//		case R.id.tv_menu_regist:// 注册
//			Intent registActivity = new Intent(MainActivity.this,
//					RegistActivity.class);
//			startActivity(registActivity);
//			break;
		default:
			break;
		}
	}

}
