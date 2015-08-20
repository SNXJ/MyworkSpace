package com.ucgcn.rlct.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ab.util.AbToastUtil;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.MyBankCard;

public class BindBankActivity extends BaseActivity implements OnClickListener {
	private ImageButton ib_bind_bank;// title返回按钮
	private ImageButton ib_bind_bank_confirm;// 绑定银行卡
	private EditText et_bank_card;// 银行卡号
	private TextView tv_bank_name;// 开户行名称
	private TextView tv_bank_city;// 开户行所在城市
	private MyBankCard mBankCard;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_bind_bank);
		ib_bind_bank = (ImageButton) findViewById(R.id.ib_bind_bank);
		ib_bind_bank_confirm = (ImageButton) findViewById(R.id.ib_bind_bank_confirm);
		ib_bind_bank.setOnClickListener(this);
		ib_bind_bank_confirm.setOnClickListener(this);

	}

	private boolean saveBankInfo() {
		mBankCard = new MyBankCard();
		et_bank_card = (EditText) findViewById(R.id.et_bank_card);
		tv_bank_name = (TextView) findViewById(R.id.tv_bank_name);
		String bankCardNumber = et_bank_card.getText().toString();
		String bankName = tv_bank_name.getText().toString();

		if (!TextUtils.isEmpty(bankCardNumber) && bankCardNumber.length() == 19) {
			mBankCard.setBankCardNumber(bankCardNumber);
		} else {
			AbToastUtil.showToast(this, "银行卡号输入有误，请重新输入");
			return false;
		}

		if (!TextUtils.isEmpty(bankName)) {
			mBankCard.setBankName(bankName);
		}
		
		mBankCard.setCheckState(true);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_bind_bank:
			finish();
			break;
		case R.id.ib_bind_bank_confirm:
			boolean state = saveBankInfo();
			if(state){
				Intent myBankCardActivity = new Intent(BindBankActivity.this,
						MyBankCardActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("mBankCard", mBankCard);
				myBankCardActivity.putExtras(bundle);
				startActivity(myBankCardActivity);
				finish();
			}
			
			break;

		default:
			break;
		}

	}

}
