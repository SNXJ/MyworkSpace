package com.ucgcn.rlct.ui.activitys;

import com.ab.util.AbToastUtil;
import com.ucgcn.rlct.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SettingActivity extends BaseActivity implements OnClickListener {
	private ImageButton ib_setting_left;
	private RelativeLayout rl_questions,rl_feedback,rl_check_version;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_setting);
		ib_setting_left = (ImageButton) findViewById(R.id.ib_setting_left);
		ib_setting_left.setOnClickListener(this);
		rl_check_version = (RelativeLayout) findViewById(R.id.rl_check_version);
		rl_feedback = (RelativeLayout) findViewById(R.id.rl_feedback);
		rl_questions = (RelativeLayout) findViewById(R.id.rl_questions);
		rl_questions.setOnClickListener(this);
		rl_feedback.setOnClickListener(this);
		rl_check_version.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_setting_left:
			finish();
			break;
		case R.id.rl_questions:
			AbToastUtil.showToast(this, "常见问题");
			break;
		case R.id.rl_feedback:
			AbToastUtil.showToast(this, "意见反馈");
			break;
		case R.id.rl_check_version:
			AbToastUtil.showToast(this, "已经是最新版本");
			break;
		default:
			break;
		}
	}
}
