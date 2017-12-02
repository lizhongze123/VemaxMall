package cn.xm.vemaxmall.view.sidebar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.xm.vemaxmall.R;

public class SortAdapter extends BaseAdapter {
	private Context context;
	private List<CarBean> cars;
	private LayoutInflater inflater;

	public SortAdapter(Context context, List<CarBean> cars) {
		this.context = context;
		this.cars = cars;
		this.inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return cars.size();
	}

	@Override
	public Object getItem(int position) {
		return cars.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		CarBean car = cars.get(position);
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_side, null);
			viewholder.tv_tag = (TextView) convertView
					.findViewById(R.id.tv_lv_item_tag);
			viewholder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_lv_item_name);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		// 获取首字母的assii值
		int selection = car.getFirstPinYin().charAt(0);
		// 通过首字母的assii值来判断是否显示字母
		int positionForSelection = getPositionForSelection(selection);
		if (position == positionForSelection) {
			// 相等说明需要显示字母
			viewholder.tv_tag.setVisibility(View.VISIBLE);
			viewholder.tv_tag.setText(car.getFirstPinYin());
		} else {
			viewholder.tv_tag.setVisibility(View.GONE);

		}
		viewholder.tv_name.setText(car.getName());
		return convertView;
	}

	public int getPositionForSelection(int selection) {
		for (int i = 0; i < cars.size(); i++) {
			String Fpinyin = cars.get(i).getFirstPinYin();
			char first = Fpinyin.toUpperCase().charAt(0);
			if (first == selection) {
				return i;
			}
		}
		return -1;

	}

	class ViewHolder {
		TextView tv_tag;
		TextView tv_name;
	}

}
