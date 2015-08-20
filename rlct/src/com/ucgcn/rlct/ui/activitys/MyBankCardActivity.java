package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.MyBankCard;

public class MyBankCardActivity extends BaseActivity {
	private ImageView iv_logo_bank;//银行卡图标
	private ImageView iv_check_state;//验证状态
	private TextView tv_my_bind_bank;//银行名称
	private TextView tv_my_bind_bank_info;//银行卡信息
	private MyBankCard mBankCard;
	private ImageButton ib_mybank_left;
	
	@Override
	protected void initView() {
		setContentView(R.layout.activity_mybank_card);
		ib_mybank_left = (ImageButton) findViewById(R.id.ib_mybank_left);
		
		iv_logo_bank = (ImageView) findViewById(R.id.iv_logo_bank);
		iv_check_state = (ImageView) findViewById(R.id.iv_check_state);
		tv_my_bind_bank = (TextView) findViewById(R.id.tv_my_bind_bank);
		tv_my_bind_bank_info = (TextView) findViewById(R.id.tv_my_bind_bank_info);
		
		mBankCard = (MyBankCard) getIntent().getSerializableExtra("mBankCard");
		
//		iv_logo_bank.setBackground(background);
//		tv_my_bind_bank.setText(mBankCard.getBankName());
		
		String bankCardNumber = mBankCard.getBankCardNumber();
		String weiHao = bankCardNumber.substring(15);
		tv_my_bind_bank_info.setText("尾号"+weiHao+" 储蓄卡");
		if(mBankCard.isCheckState()){
			iv_check_state.setBackgroundResource(R.drawable.check_successful);
		}else{
			iv_check_state.setBackgroundResource(R.drawable.check_failure);
		}
		
		
		ib_mybank_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			finish();
			}
		});
	}
}
