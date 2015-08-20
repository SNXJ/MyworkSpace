package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.ucgcn.rlct.R;
/**
 * 还款
 * @author IT
 *
 */
public class RepaymentActivity extends BaseActivity implements OnClickListener {
	private TextView tv_title,tv_repayment_right;
	private ImageButton ib_repayment_left;
	private RelativeLayout rl_choose_bind_bank;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_repayment);
		rl_choose_bind_bank = (RelativeLayout) findViewById(R.id.rl_choose_bind_bank);
		rl_choose_bind_bank.setOnClickListener(this);
	}
	
	@Override
	protected void initActionBar() {
		tv_title = (TextView) findViewById(R.id.tv_repayment_title);
		ib_repayment_left = (ImageButton) findViewById(R.id.ib_repayment_left);
//		tv_title.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		tv_title.setText(R.string.repayment);
		tv_repayment_right = (TextView) findViewById(R.id.tv_repayment_right);
//		tv_repayment_right.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		ib_repayment_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		tv_repayment_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent repaymentHistoryActivity = new Intent(RepaymentActivity.this, RepaymentHistoryActivity.class);
				startActivity(repaymentHistoryActivity);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_choose_bind_bank://选择绑定银行卡
			Intent bindBankActivity = new Intent(RepaymentActivity.this, BindBankActivity.class);
			startActivity(bindBankActivity);
			break;

		default:
			break;
		}
	}
}
