package cn.xm.vemaxmall.view.sidebar;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CarBean> {

	@Override
	public int compare(CarBean lhs, CarBean rhs) {
		return sort(lhs, rhs);
	}

	private int sort(CarBean lhs, CarBean rhs) {
		// 获取ascii值
		int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
		int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);
//		// 判断若不是字母，则排在字母之后
		if (lhs_ascii < 65 || lhs_ascii > 90){
			return 1;
		}
		else
			if (rhs_ascii < 65 || rhs_ascii > 90) {
			return -1;
		}

		else{
				int r = (lhs.getPinYin().toUpperCase()).compareTo((rhs.getPinYin().toUpperCase()));
			return r;
		}
	}

}
