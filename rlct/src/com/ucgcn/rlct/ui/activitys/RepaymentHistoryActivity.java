package com.ucgcn.rlct.ui.activitys;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.ucgcn.rlct.R;
import com.ucgcn.rlct.domain.RepaymentHistory;
import com.ucgcn.rlct.utils.RepayStrUtils;


public class RepaymentHistoryActivity extends BaseActivity {
	private ListView listViewRH;
	private TextView tv_title;
	private ImageButton ib_repay_left,ib_repay_right;
	private List<RepaymentHistory> listRepaymentHistory;
	private RelativeLayout rl__repay_title;
	@Override
	protected void initView() {
		setContentView(R.layout.repayment_history);
		listViewRH = (ListView) findViewById(R.id.lv_repayment_history);
		rl__repay_title = (RelativeLayout) findViewById(R.id.rl_title);//title 设置背景色
		rl__repay_title.setBackgroundResource(R.color.title_blue);
		tv_title = (TextView) findViewById(R.id.tv_top);
		ib_repay_left = (ImageButton) findViewById(R.id.ib_top_left);
		ib_repay_right = (ImageButton) findViewById(R.id.ib_top_right);
//		tv_title.setTextSize(AbViewUtil.px2sp(abApplication, 40));
		tv_title.setText(R.string.repayment_history);
		tv_title.setTextColor(Color.WHITE);
		ib_repay_left.setBackgroundResource(R.drawable.back);
		ib_repay_right.setVisibility(View.INVISIBLE);
		
		ib_repay_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		listRepaymentHistory = new ArrayList<RepaymentHistory>();
	/**	
	 *假数据
	 */
		RepaymentHistory mRepaymentHistory1	= new RepaymentHistory("第一期（2015年7月5日）", "20,000.00", "2,000.30", "22,000.30",true,true);
		RepaymentHistory mRepaymentHistory2	= new RepaymentHistory("第二期（2015年7月4日）", "20,000.00", "2,000.30", "22,000.30",true,true);
		RepaymentHistory mRepaymentHistory3	= new RepaymentHistory("第三期（2015年7月3日）", "20,000.00", "2,000.30", "22,000.30",false,true);
		RepaymentHistory mRepaymentHistory4	= new RepaymentHistory("第四期（2015年7月2日）", "20,000.00", "2,000.30", "22,000.30",true,false);
		
		listRepaymentHistory.add(mRepaymentHistory1);
		listRepaymentHistory.add(mRepaymentHistory2);
		listRepaymentHistory.add(mRepaymentHistory3);
		listRepaymentHistory.add(mRepaymentHistory4);
		
		listViewRH.setAdapter(new RepaymentHistoryAdapter());
	}
	
	class RepaymentHistoryAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return listRepaymentHistory.size();
		}
		@Override
		public Object getItem(int position) {
			return listRepaymentHistory.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView!=null){
				holder = (ViewHolder) convertView.getTag();
//				convertView = 
			}else{
				convertView = convertView.inflate(getApplicationContext(), R.layout.repayment_history_item, null);
				holder = new ViewHolder();
				holder.iv_repayment_state = (ImageView) convertView.findViewById(R.id.iv_repayment_state);
				holder.tv_capital1 = (TextView) convertView.findViewById(R.id.tv_capital1);
				holder.tv_capital2 = (TextView) convertView.findViewById(R.id.tv_capital2);
				holder.tv_interests_server1 = (TextView) convertView.findViewById(R.id.tv_interests_server1);
				holder.tv_interests_server2 = (TextView) convertView.findViewById(R.id.tv_interests_server2);
				holder.tv_total_repayment1 = (TextView) convertView.findViewById(R.id.tv_total_repayment1);
				holder.tv_total_repayment2 = (TextView) convertView.findViewById(R.id.tv_total_repayment2);
				holder.tv_repayment_history_item = (TextView) convertView.findViewById(R.id.tv_repayment_history_item);
				convertView.setTag(holder);
			}
			boolean state = listRepaymentHistory.get(position).isRepayState();
			boolean dealState = listRepaymentHistory.get(position).isDealFinish();
			if(!dealState){
				holder.iv_repayment_state.setVisibility(View.INVISIBLE);
				holder.tv_capital1.setTextColor(Color.GRAY);
				holder.tv_capital2.setTextColor(Color.GRAY);
				holder.tv_interests_server2.setTextColor(Color.GRAY);
				holder.tv_interests_server1.setTextColor(Color.GRAY);
				holder.tv_total_repayment1.setTextColor(Color.GRAY);
				holder.tv_total_repayment2.setTextColor(Color.GRAY);
			}else{
				if(state){
					holder.iv_repayment_state.setBackgroundResource(R.drawable.repayment_success);
				}else{
					holder.iv_repayment_state.setBackgroundResource(R.drawable.repayment_error);
				}
			}
			
			String capital = listRepaymentHistory.get(position).getCapital();
			String interestsServer = listRepaymentHistory.get(position).getInterestsServer();
			String totalRepayment = listRepaymentHistory.get(position).getTotalRepayment();
			
			holder.tv_capital1.setText(RepayStrUtils.getBefore1(capital));
			holder.tv_capital1.setTextSize(AbViewUtil.px2sp(abApplication, 36));
			holder.tv_capital2.setText(RepayStrUtils.getAfter2(capital));
			holder.tv_capital2.setTextSize(AbViewUtil.px2sp(abApplication, 26));
			holder.tv_interests_server1.setText(RepayStrUtils.getBefore1(interestsServer));
			holder.tv_interests_server1.setTextSize(AbViewUtil.px2sp(abApplication, 36));
			holder.tv_interests_server2.setText(RepayStrUtils.getAfter2(interestsServer)+"万");
			holder.tv_interests_server2.setTextSize(AbViewUtil.px2sp(abApplication, 26));
			holder.tv_total_repayment1.setText(RepayStrUtils.getBefore1(totalRepayment));
			holder.tv_total_repayment1.setTextSize(AbViewUtil.px2sp(abApplication, 36));
			holder.tv_total_repayment2.setText(RepayStrUtils.getAfter2(totalRepayment)+"万");
			holder.tv_total_repayment2.setTextSize(AbViewUtil.px2sp(abApplication, 26));

			
			return convertView;
		}
	}
	class ViewHolder{
		ImageView iv_repayment_state;
		TextView tv_capital1 , tv_capital2;
		TextView tv_interests_server1 , tv_interests_server2;
		TextView tv_total_repayment1 , tv_total_repayment2;
		TextView tv_repayment_history_item;
	}
}
