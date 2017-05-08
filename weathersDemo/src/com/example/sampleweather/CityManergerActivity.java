package com.example.sampleweather;

import java.util.ArrayList;
import java.util.List;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.example.sampleweather.fragment.ContentViewFragment;
import com.example.sampleweather.ui.SwipeDelete;
import com.example.sampleweather.ui.SwipeDelete.OnSwipeStateChangeListener;
import com.example.sampleweather.ui.SwipeLayoutManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CityManergerActivity extends Activity implements OnClickListener {
	private AMapLocationClientOption mAmapotpClientOption = new AMapLocationClientOption();
	private AMapLocationClient mLocationClient = null;
	private Button mLocation;
	private ImageButton mBack;
	private ListView mListView;
	private Button mAddCity;
	private Context mContext;
	private List<String> city;
	private Adapter adaptet;
	private String[] strings;
	private Application mApplication;
	private SlidingMenu slidingMenu;
	private SwipeDelete swipeDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method s
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.citymanerger);
		mContext = this;
		mApplication = Application.getInstance();
		slidingMenu = mApplication.getSlidingMenu();
		city = new ArrayList<String>();
		strings = mApplication.getCity();
		mLocation = (Button) findViewById(R.id.location);
		mAddCity = (Button) findViewById(R.id.addcity);
		mBack = (ImageButton) findViewById(R.id.back);
		mListView = (ListView) findViewById(R.id.citymanergerlist);
		swipeDelete = (SwipeDelete) findViewById(R.id.swipedelete);
		mLocation.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mAddCity.setOnClickListener(this);
		// System.out.println(strings.length);

		if (strings.length != 0) {

			for (int i = 0; i < strings.length; i++) {

				if ((strings[i] != "")) {

					city.add(strings[i]);
				}
			}
		}
		// adaptet = new
		// ArrayAdapter(mContext,android.R.layout.simple_expandable_list_item_1
		// , city );
		adaptet = new Adapter();
		mListView.setAdapter(adaptet);
		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
					SwipeLayoutManager.getInstance().closeCurrentDelete();
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 点击返回按钮时判断
		case R.id.back:
			// slidingMenu.toggle();
			// 弹出提示框
			if (city.size() == 0) {

				new AlertDialog.Builder(mContext)
						.setTitle("提示")
						.setMessage("请添加至少一个城市")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										// finish();
									}
								}).show();
			}
			// 跳转至主界面
			else {

				Intent intent = new Intent(CityManergerActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}

			break;
		// 点击定位时
		case R.id.location:
			initLocation();
			// 高德定位接口回调
			mLocationClient.setLocationListener(new AMapLocationListener() {

				@Override
				public void onLocationChanged(AMapLocation aMapLocation) {
					// TODO Auto-generated method stub
					if (aMapLocation != null) {
						if (aMapLocation.getErrorCode() == 0) {
							// 首先判断定位城市是否存在列表中
							if (city.indexOf(parseName(aMapLocation.getCity())) == -1) {
								city.add(parseName(aMapLocation.getCity()));
								// 更新城市列表
								updataCity();
								// mcontentview.updataCity();
							}
							// 通知刷新
							adaptet.notifyDataSetChanged();

							Toast.makeText(mContext, aMapLocation.getCity(),
									Toast.LENGTH_SHORT).show();
						} else {
							// Toast错误信息
							Toast.makeText(
									mContext,
									aMapLocation.getErrorInfo() + "     "
											+ aMapLocation.getErrorCode(),
									Toast.LENGTH_SHORT).show();

						}
					}
				}
			});
			break;
		case R.id.addcity:
			// 手动添加城市跳转
			Intent intents = new Intent(CityManergerActivity.this,
					SearchCity.class);
			startActivity(intents);
			break;

		default:
			break;
		}
	}

	// 初始化定位参数
	private void initLocation() {
		// TODO Auto-generated method stub
		// 实例化定位端
		mLocationClient = new AMapLocationClient(this);
		// 设置定位参数
		mAmapotpClientOption.setNeedAddress(true);
		// 高精度定位
		mAmapotpClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// mAmapotpClientOption.setInterval(2000);
		// 单次定位
		mAmapotpClientOption.setOnceLocationLatest(true);
		mLocationClient.setLocationOption(mAmapotpClientOption);
		// 开始定位
		mLocationClient.startLocation();
	}

	public class Adapter extends BaseAdapter implements
			OnSwipeStateChangeListener {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return city.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = View.inflate(mContext, R.layout.listitem, null);

			}
			ViewHolder viewHolder = ViewHolder.getHolder(convertView);
//			设置城市名称
			viewHolder.tv_name.setText(city.get(position));	
			viewHolder.swipeDelete.setTag(position);
			final int i = position;
			viewHolder.swipeDelete.SetOnSwipeStateChangeListener(this);
			viewHolder.tv_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
//					点击删除时，关闭当前条目，清除打开状态，移除条目，刷新
					SwipeLayoutManager.getInstance().closeCurrentDelete();
					SwipeLayoutManager.getInstance().clearCurrentLayout();
					city.remove(i);
					updataCity();
					adaptet.notifyDataSetChanged();

				}
			});
			viewHolder.tv_name.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
//判断当前是否有打开的，如果有打开的，点击时先关闭
					if (SwipeLayoutManager.getInstance().getCurrentDelete() != null) {
						SwipeLayoutManager.getInstance().closeCurrentDelete();
                        
					}
					else {
//						点击跳转
						Toast.makeText(mContext, String.valueOf(i), 0).show();
						Intent intent = new Intent();
						intent.putExtra("page", String.valueOf(i)); // id+""
						// 这样是把int转成String类型,
						// 否则会报错
						// startActivity(intent);
						// intent.putExtra("position", i);
						intent.setClass(mContext, MainActivity.class);
//						清除保存的Activity栈
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						
						startActivity(intent);
						finish();
					}
				}
			});
			return convertView;
		}

		@Override
		public void onOpen(Object tag) {
			// TODO Auto-generated method stub
			// Toast.makeText(mContext,"第"+(Integer)tag+"个打开", 0).show();
		}

		@Override
		public void onClose(Object tag) {
			// TODO Auto-generated method stub
			// Toast.makeText(mContext,"第"+(Integer)tag+"个关闭", 0).show();
		}

	}

	static class ViewHolder {
		TextView tv_name, tv_delete;
		SwipeDelete swipeDelete;

		public ViewHolder(View convertView) {
			tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
			swipeDelete = (SwipeDelete) convertView
					.findViewById(R.id.swipedelete);
		}

		public static ViewHolder getHolder(View convertView) {

			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		在手动选择城市后，重新刷新城市数据显示
		city.removeAll(city);
		strings = mApplication.getCity();
		if (strings.length != 0) {

			for (int i = 0; i < strings.length; i++) {

				if ((strings[i] != "")) {

					city.add(strings[i]);
				}
			}
		}

		adaptet.notifyDataSetChanged();
		// mcontentview.updataCity();
	}
//更新城市数据
	public void updataCity() {
		strings = (String[]) city.toArray(new String[city.size()]);
		mApplication.getPreferenceUtils().setStrings(mContext, "city", strings);
		mApplication.setCity(strings);
	}
//去县市方法
	public String parseName(String city) {
		if (city.contains("市")) {// 如果为空就去掉市字再试试
			String subStr[] = city.split("市");
			city = subStr[0];
		} else if (city.contains("县")) {// 或者去掉县字再试试
			String subStr[] = city.split("县");
			city = subStr[0];
		}
		return city;
	}

}
