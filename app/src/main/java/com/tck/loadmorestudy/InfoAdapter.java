package com.tck.loadmorestudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tck.loadmorestudy.bean.InfoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context mContext;
    private List<InfoBean> mInfoBeanList;
    private LayoutInflater mInflater;

    public InfoAdapter(Context context, List<InfoBean> infoBeanList) {
        mContext = context;
        mInfoBeanList = infoBeanList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        InfoBean infoBean = mInfoBeanList.get(position);
        holder.iconIv.setImageResource(infoBean.iconId);
        holder.titleTv.setText(infoBean.title);
    }

    @Override
    public int getItemCount() {
        return mInfoBeanList == null ? 0 : mInfoBeanList.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;
        private TextView titleTv;

        public InfoViewHolder(View itemView) {
            super(itemView);

            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);

        }
    }
}
