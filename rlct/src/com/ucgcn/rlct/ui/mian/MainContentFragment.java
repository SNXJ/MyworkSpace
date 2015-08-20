package com.ucgcn.rlct.ui.mian;

import com.ab.view.sliding.AbSlidingPlayView;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.ui.activitys.AccountInfoActivity;
import com.ucgcn.rlct.ui.activitys.LoanActivity;
import com.ucgcn.rlct.ui.activitys.LoginActivity;
import com.ucgcn.rlct.ui.activitys.MainActivity;
import com.ucgcn.rlct.ui.activitys.RegistActivity;
import com.ucgcn.rlct.ui.activitys.RepaymentActivity;
import com.ucgcn.rlct.ui.activitys.SettingActivity;
import com.ucgcn.rlct.ui.activitys.ShareActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainContentFragment extends Fragment implements OnClickListener  {
	
	private static final String TAG = "MainContentFragment";

	private AbSlidingPlayView mSlidingPlayView;
	
	private LinearLayout rl_loan, rl_repayment, rl_share;// 贷款，还款，分享页面
	private ImageButton ib_top_left, ib_tob_right;
	private TextView tv_user_name;// 用户名
	private SharedPreferences sp;

	private TextView tv_menu_login;// 登录
	private TextView tv_menu_regist;// 注册
	private RelativeLayout rl_login_regist;
	
	private boolean isExit = false ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_content, null);
		sp = this.getActivity().getSharedPreferences("config", 0);

		rl_loan = (LinearLayout) view.findViewById(R.id.rl_loan);// 贷款
		rl_loan.setOnClickListener(this);
		rl_repayment = (LinearLayout) view.findViewById(R.id.rl_repayment);// 还款
		rl_repayment.setOnClickListener(this);
		rl_share = (LinearLayout) view.findViewById(R.id.rl_share);// 分享
		rl_share.setOnClickListener(this);
		String username = sp.getString("username", "");
		Log.i(TAG, "username"+username);
		isExit = sp.getBoolean("isExit", false);
		if("".equals(username)){
			Intent registActivity = new Intent(this.getActivity(),
					RegistActivity.class);
			startActivity(registActivity);
			
			getActivity().finish();
		}else{
			if(!isExit&&username.length()>0){
				String tempstr = username.substring(3, 7);
				String showUserName = username.replaceAll(tempstr, "****");
				sp.edit().putString("showUserName", showUserName).commit();
				
			}else{
				Intent loginActivity = new Intent(this.getActivity(),
						LoginActivity.class);
				startActivity(loginActivity);
				onDestroy();
			}
//			ll_menu.setVisibility(View.VISIBLE);
//			rl_login_regist.setVisibility(View.INVISIBLE);
		}
		
		
		mSlidingPlayView = (AbSlidingPlayView) view.findViewById(R.id.mAbSlidingPlayView);
		final View mPlayView = inflater.inflate(R.layout.play_view_item, null);
		ImageView mPlayImage = (ImageView) mPlayView
				.findViewById(R.id.mPlayImage);
		TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
		mPlayText.setText("1111111111111");
		mPlayImage.setBackgroundResource(R.drawable.banner_1);

		final View mPlayView1 = inflater
				.inflate(R.layout.play_view_item, null);
		ImageView mPlayImage1 = (ImageView) mPlayView1
				.findViewById(R.id.mPlayImage);
		TextView mPlayText1 = (TextView) mPlayView1
				.findViewById(R.id.mPlayText);
		mPlayText1.setText("2222222222222");
		mPlayImage1.setBackgroundResource(R.drawable.banner_2);

		final View mPlayView2 = inflater
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
		
//		mSlidingPlayView.startPlay();
		return view;
	}
	@Override
	public void onResume() {
		mSlidingPlayView.startPlay();
		super.onResume();
	}

	@Override
	public void onDestroy() {
		if(mSlidingPlayView!=null){
			mSlidingPlayView.stopPlay();
		}
		super.onDestroy();
	}
	
	public void onPause() {
		mSlidingPlayView.stopPlay();
		super.onPause();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_loan:// 贷款
			Intent loanActivity = new Intent(this.getActivity(),
					LoanActivity.class);
			startActivity(loanActivity);
			break;
		case R.id.rl_repayment:// 还款
			Intent repaymentActivity = new Intent(this.getActivity(),
					RepaymentActivity.class);
			startActivity(repaymentActivity);
			break;
		case R.id.rl_share:// 分享
			Intent shareActivity = new Intent(this.getActivity(),
					ShareActivity.class);
			startActivity(shareActivity);
			break;

		/*case R.id.ib_top_left:
			Intent settingActivity = new Intent(this.getActivity(),
					SettingActivity.class);
			startActivity(settingActivity);
			break;*/
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
			Intent loginActivity = new Intent(this.getActivity(),
					LoginActivity.class);
			startActivity(loginActivity);
			sp.edit().putBoolean("isExit", true).commit();
			onDestroy();
//			ll_menu.setVisibility(View.INVISIBLE);
//			rl_login_regist.setVisibility(View.VISIBLE);
			break;
		}
	}


}
