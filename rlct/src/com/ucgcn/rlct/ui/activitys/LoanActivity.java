package com.ucgcn.rlct.ui.activitys;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.ucgcn.rlct.R;

public class LoanActivity extends BaseActivity {
	private TextView tv_title;
	private ImageButton ib_repay_left,ib_repay_right;
	private RelativeLayout rl_title;
	private TextView et_loan_name;
	private TextView et_money;//拖动条显示金额
	private SeekBar loanSeekBar;
	
	@Override
	protected void initView() {
		setContentView(R.layout.activity_loan);
		rl_title = (RelativeLayout) findViewById(R.id.rl_title);//title 设置背景色
		rl_title.setBackgroundResource(R.color.title_blue);
		tv_title = (TextView) findViewById(R.id.tv_top);
		ib_repay_left = (ImageButton) findViewById(R.id.ib_top_left);
		ib_repay_right = (ImageButton) findViewById(R.id.ib_top_right);
//		tv_title.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		tv_title.setText(R.string.loan);
		tv_title.setTextColor(Color.WHITE);
		ib_repay_left.setBackgroundResource(R.drawable.back);
		ib_repay_right.setVisibility(View.INVISIBLE);
		
		ib_repay_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		loanSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		et_money = (TextView) findViewById(R.id.et_money);
		et_loan_name = (TextView) findViewById(R.id.et_loan_name);
		String loan_name = et_loan_name.getText().toString();
		
		loanSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				et_money.setText("¥ "+progress+"万");
			}
		});
	}
}
