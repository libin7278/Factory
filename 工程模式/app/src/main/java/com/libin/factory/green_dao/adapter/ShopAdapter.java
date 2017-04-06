package com.libin.factory.green_dao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libin.factory.MainApplication;
import com.libin.factory.R;
import com.libin.factory.green_dao.bean.ShopBean;

import java.util.List;

/**
 * Created by doudou on 2017/4/5.
 */

public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ShopBean> mData;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;

    public ShopAdapter(List<ShopBean> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DataViewHolder){
            BindViewForDate(holder,position);
        }
    }

    private void BindViewForDate(RecyclerView.ViewHolder holder, final int position) {
        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        final ShopBean shopBean = getItem(position);
        dataViewHolder.tv_id.setText("id : "+ shopBean.getId());
        dataViewHolder.tv_name.setText(shopBean.getName());
        dataViewHolder.tv_price.setText("$"+shopBean.getPrice());

        Glide.with(MainApplication.getContext())
                .load(shopBean.getImage_url())
                .into(dataViewHolder.iv_shop);

        dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });

        dataViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListener != null){
                    onItemLongClickListener.onClick(v,position);
                }
                return true;
            }
        });
    }

    private ShopBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    static class DataViewHolder extends RecyclerView.ViewHolder{
        TextView tv_price;
        TextView tv_id;
        TextView tv_name;
        ImageView iv_shop;

        public DataViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            iv_shop = (ImageView) itemView.findViewById(R.id.iv_shop);

        }
    }
    public interface OnItemClickListener{
        public void onClick(View View, int position);
    }

    public interface OnItemLongClickListener{
        public void onClick(View View, int position);
    }
}
