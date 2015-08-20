package com.ucgcn.rlct.ui.activitys;

import com.ab.util.AbToastUtil;
import com.ab.util.AbViewUtil;
import com.ucgcn.rlct.R;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShareActivity extends BaseActivity {
	protected static final String TAG = "ShareActivity";
	private TextView tv_title;
	private TextView tv_share_invitation_code;//邀请码
	private ImageButton ib_share_left;
	private ImageButton ib_share;//立即分享
	private RelativeLayout rl_title;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_share);
		
		tv_title = (TextView) findViewById(R.id.tv_share_title);
		ib_share_left = (ImageButton) findViewById(R.id.ib_share_left);
		tv_title.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		tv_title.setText(R.string.share);
		tv_title.setTextColor(Color.WHITE);

		ib_share_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		ib_share = (ImageButton) findViewById(R.id.ib_share);
		//邀请码
		tv_share_invitation_code = (TextView) findViewById(R.id.tv_share_invitation_code);
		//分享
		ib_share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AbToastUtil.showToast(ShareActivity.this, "分享：一起来赚钱");
			}
		});
		
	}
}
