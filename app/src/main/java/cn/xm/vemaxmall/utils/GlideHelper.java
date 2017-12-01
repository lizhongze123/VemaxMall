package cn.xm.vemaxmall.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by lzz on 2017/3/10.
 */

public class GlideHelper {

	public static void displayImage(Context context, String url, ImageView imageView) {
		Glide.with(context).load(url)
				.crossFade(1000)
				.into(imageView);
	}
}
