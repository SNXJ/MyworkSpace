package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.UserInfo;
import com.ucgcn.rlct.ui.mian.MainActivitys;
import com.ucgcn.rlct.utils.MatchUtil;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = "LoginActivity";
	private ImageButton ib_login_left;
	private EditText et_user_login;// 用户名
	private EditText et_login_password;// 密码
	private UserInfo user;
	private TextView tv_forget_password;// 忘记密码
	private ImageButton ib_login_confirm;// 确认登录
	private TextView tv_login_regist;// 标题栏的登录
	private SharedPreferences sp;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_login);
		ib_login_left = (ImageButton) findViewById(R.id.ib_login_left);
		tv_login_regist = (TextView) findViewById(R.id.tv_login_regist);
		tv_login_regist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegistActivity.class);
				startActivity(intent);
			}
		});
		ib_login_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		et_user_login = (EditText) findViewById(R.id.et_user_login);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		ib_login_confirm = (ImageButton) findViewById(R.id.ib_login_confirm);
		tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
		tv_forget_password.setOnClickListener(this);
		ib_login_confirm.setOnClickListener(this);
		user = (UserInfo) getIntent().getSerializableExtra("userInfo");

	}

	private boolean login() {

		String userName = et_user_login.getText().toString();
		String password = et_login_password.getText().toString();

		sp = getSharedPreferences("config", MODE_PRIVATE);
		if (TextUtils.isEmpty(userName)) {
			AbToastUtil.showToast(LoginActivity.this, "手机号码不能为空");
			return false;
		} else if (!MatchUtil.isPhoneNum(userName)) {
			AbToastUtil.showToast(LoginActivity.this, "手机号码格式有误");
			return false;
		}
		if (TextUtils.isEmpty(password)) {
			AbToastUtil.showToast(LoginActivity.this, "密码不能为空");
			return false;
		}
		if (user != null) {
			if (userName.equals(user.getPhoneNumber())
					&& password.equals(user.getPassword())) {

				Editor edit = sp.edit();
				edit.putString("username", userName);
				edit.putString("password", password);
				edit.commit();
				Intent intent = new Intent(LoginActivity.this,
						MainActivitys.class);
				startActivity(intent);
				return true;
			} else {
				AbToastUtil.showToast(LoginActivity.this, "手机号码或者密码错误");
				return false;
			}
		} else {
			String name = sp.getString("username", "");
			String mima = sp.getString("password", "");
			Log.i(TAG, name);
			Log.i(TAG, mima);
			if (userName.equals(name) && password.equals(mima)) {
				Intent intent = new Intent(LoginActivity.this,
						MainActivitys.class);
				startActivity(intent);
				sp.edit().putBoolean("isExit", false).commit();
				return true;
			} else {
				AbToastUtil.showToast(LoginActivity.this, "手机号码或者密码错误2");
				return false;
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_login_confirm:// 确认登录
			if (login()) {
				finish();
			}
			break;
		case R.id.tv_forget_password:
			AbToastUtil.showToast(LoginActivity.this, "忘记密码");

			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

}
