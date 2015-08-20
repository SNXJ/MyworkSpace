package com.ucgcn.rlct.ui.mian;

import com.ucgcn.rlct.R;
import com.ucgcn.rlct.ui.activitys.AccountInfoActivity;
import com.ucgcn.rlct.ui.activitys.LoginActivity;
import com.ucgcn.rlct.ui.activitys.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainMenuFragment extends Fragment implements OnClickListener {
	private static final String TAG = "MainMenuFragment";
	private TextView tv_user_name;// 用户名
	private RelativeLayout menu_my_data;// 菜单我的资料（账户信息）
	private ImageButton ib_menu_exit;
	private SharedPreferences sp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sliding_menu_menu, null);
		sp = this.getActivity().getSharedPreferences("config", 0);
		ib_menu_exit = (ImageButton) view.findViewById(R.id.ib_menu_exit);
		ib_menu_exit.setOnClickListener(this);
		tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
		String username = sp.getString("showUserName", "");
		boolean isexit = sp.getBoolean("isExit", false);
		if (!isexit&&username.length()>0) {
			String tempstr = username.substring(3, 7);
			String showUserName = username.replace(tempstr, "****");
			tv_user_name.setText("Hi," + showUserName);
		} else {
			Intent loginActivity = new Intent(this.getActivity(),
					LoginActivity.class);
			startActivity(loginActivity);
			getActivity().finish();
		}
		menu_my_data = (RelativeLayout) view.findViewById(R.id.menu_my_data);
		menu_my_data.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_my_data:
			Intent accountInfoActivity = new Intent(this.getActivity(),
					AccountInfoActivity.class);
			startActivity(accountInfoActivity);
			break;

		case R.id.ib_menu_exit:// 退出登录
			sp.edit().putBoolean("isExit", true).commit();
			Intent loginActivity = new Intent(this.getActivity(),
					LoginActivity.class);
			startActivity(loginActivity);
			this.getActivity().finish();
			break;
		}

	}
}
