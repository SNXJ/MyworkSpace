package com.ucgcn.rlct.ui.activitys;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.ucgcn.rlct.R;

public class AccountInfoActivity extends BaseActivity {
	private TextView tv_title;
	private ImageButton ib_account_left;
	@Override
	protected void initView() {
		setContentView(R.layout.account_info);
		
		tv_title = (TextView) findViewById(R.id.tv_account_title);
		ib_account_left = (ImageButton) findViewById(R.id.ib_account_left);
		tv_title.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		tv_title.setText(R.string.account_info);
		tv_title.setTextColor(Color.WHITE);

		ib_account_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
