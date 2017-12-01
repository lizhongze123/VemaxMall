package cn.xm.vemaxmall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.xm.vemaxmall.R;
import cn.xm.vemaxmall.ui.entity.ItemBean;

/**
 * @author lzz
 * @desc
 * @date 2017/12/1
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

    private Context context;
    private List<ItemBean> list;

    public ItemAdapter(Context context, List<ItemBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bindItem(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView tvName;

        ItemHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.iv_icon);
            tvName = view.findViewById(R.id.tv_name);
        }

        private void bindItem(final ItemBean bean, int position) {
            tvName.setText(bean.iconName);
            ivIcon.setImageResource(bean.iconResId);
        }
    }

}
