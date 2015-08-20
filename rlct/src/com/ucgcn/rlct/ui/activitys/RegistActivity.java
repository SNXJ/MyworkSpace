package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.UserInfo;
import com.ucgcn.rlct.utils.MatchUtil;

/**
 * 注册界面
 * 
 */
public class RegistActivity extends BaseActivity implements OnClickListener {
	private ImageButton ib_regist_left;

	private EditText et_phone_regist;// 注册的手机号码
	private EditText et_validation;// 验证码输入框
	private EditText et_regist_password;// 密码输入框
	private EditText et_regist_again_password;// 确认密码输入框
	private TextView tv_validation;// 获取验证码
	private ImageButton ib_password_eyes;// 显示密码
	private CheckBox cb_arrge_deal;// 同意协议的checkbox
	private TextView tv_arrge_deal;// 协议连接
	private ImageButton ib_regist_confirm;// 确定注册
	private UserInfo userInfo;
	private int showPassword = 1;

	@Override
	protected void initView() {

		setContentView(R.layout.activity_regist);
		userInfo = new UserInfo();
		ib_regist_left = (ImageButton) findViewById(R.id.ib_regist_left);
		et_phone_regist = (EditText) findViewById(R.id.et_phone_regist);
		et_validation = (EditText) findViewById(R.id.et_validation);
		et_regist_password = (EditText) findViewById(R.id.et_regist_password);
		et_regist_again_password = (EditText) findViewById(R.id.et_regist_again_password);
		tv_validation = (TextView) findViewById(R.id.tv_validation);
		tv_arrge_deal = (TextView) findViewById(R.id.tv_arrge_deal);
		ib_password_eyes = (ImageButton) findViewById(R.id.ib_password_eyes);
		ib_password_eyes = (ImageButton) findViewById(R.id.ib_password_eyes);
		ib_regist_confirm = (ImageButton) findViewById(R.id.ib_regist_confirm);
		cb_arrge_deal = (CheckBox) findViewById(R.id.cb_arrge_deal);
		ib_regist_confirm.setOnClickListener(this);
		tv_arrge_deal.setOnClickListener(this);
		tv_validation.setOnClickListener(this);
		ib_password_eyes.setOnClickListener(this);
		ib_regist_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_password_eyes:
			showPassword++;
			if (showPassword % 2 == 0) {
				et_regist_password
						.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
			} else {
				et_regist_password
						.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}
			String registPassword = et_regist_password.getText().toString();
			et_regist_password.setText(registPassword);
			break;
		case R.id.tv_validation:
			TimeCount time = new TimeCount(60000, 1000);
			time.start();
			// 发送给手机验证码

			break;
		case R.id.tv_arrge_deal:
			AbToastUtil.showToast(RegistActivity.this, "显示贷款协议界面");
			break;

		case R.id.ib_regist_confirm:// 确定注册
			if (cb_arrge_deal.isChecked()) {
				if(regist()){
					Intent intent = new Intent(RegistActivity.this,
							LoginActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("userInfo", userInfo);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			} else {
				AbToastUtil.showToast(RegistActivity.this, "请选择同意贷款协议");
				return;
			}

		default:
			break;
		}
	}

	/**
	 * 注册。
	 */
	private boolean regist() {
		String userPhoneNumber = et_phone_regist.getText().toString();
		String userPassword = et_regist_password.getText().toString();
		String againPassword = et_regist_again_password.getText().toString();
		String validation = et_validation.getText().toString();

		if (!TextUtils.isEmpty(userPhoneNumber)
				&& userPhoneNumber.length() == 11
				&& MatchUtil.isPhoneNum(userPhoneNumber)) {
			userInfo.setPhoneNumber(userPhoneNumber);
		} else {
			AbToastUtil.showToast(RegistActivity.this, "手机号码输入有误，请重新输入");
			return false;
		}

		if (TextUtils.isEmpty(userPassword)) {
			AbToastUtil.showToast(RegistActivity.this, "密码不能为空");
			return false;
		} else if (userPassword.length() < 6) {
			AbToastUtil.showToast(RegistActivity.this, "密码的长度必须大于6位");
			return false;
		}
		if (againPassword.equals(userPassword)) {
			userInfo.setPassword(userPassword);
		} else {
			AbToastUtil.showToast(RegistActivity.this, "两次输入密码不一致");
			return false;
		}

		if (!TextUtils.isEmpty(validation)) {

			if (!"1234".equals(validation)) {
				AbToastUtil.showToast(RegistActivity.this, "验证码输入有误，请重新输入");
				return false;
			}
		} else {
			AbToastUtil.showToast(RegistActivity.this, "请输入验证码");
			return false;
		}

		return true;
	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			tv_validation.setText("重新获取验证码");
			tv_validation.setClickable(true);
			tv_validation.setBackgroundColor(Color.parseColor("#55B945"));
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			tv_validation.setClickable(false);
			tv_validation.setBackgroundColor(Color.GRAY);
			tv_validation.setText(millisUntilFinished / 1000 + "秒后重新获取");
		}
	}

}
