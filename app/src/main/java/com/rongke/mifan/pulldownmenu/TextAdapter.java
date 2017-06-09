package com.rongke.mifan.pulldownmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：列表适配器
 */

public class TextAdapter extends ArrayAdapter<String> {
    private int selectedDrawble;
    private int normalDrawbleId;
    private List<String> mListData;
    private Context mContext;
    private int selectedPos = 0;
    private View.OnClickListener onClickListener;
    private String selectedText = "";
    private OnItemClickListener mOnItemClickListener;

    public TextAdapter(Context context, List<String> listData, int sId, int nId) {
        super(context, R.string.no_any, listData);
        mContext = context;
        mListData = listData;
        if (sId != 0) {
            selectedDrawble = sId;
        }
        normalDrawbleId = nId;
        initView();
    }

    private void initView() {
        onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedPos = (Integer) view.getTag(R.id.position);
                notifyDataSetChanged();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, selectedPos);
                }
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_list, parent, false);
            holder.llRoot= (LinearLayout) convertView.findViewById(R.id.ll_root);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvLine = (TextView) convertView.findViewById(R.id.tv_line);
            AutoUtils.auto(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(R.id.position, position);
        holder.tvTitle.setText(mListData.get(position));
        if(position==mListData.size()-1){
            holder.tvLine.setVisibility(View.GONE);
        }else {
            holder.tvLine.setVisibility(View.VISIBLE);
        }
        //选中选项
        if(position==selectedPos){
            holder.llRoot.setBackgroundColor(mContext.getResources().getColor(selectedDrawble));
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.public_orange));
        }else {
            holder.llRoot.setBackgroundColor(mContext.getResources().getColor(normalDrawbleId));
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.color333333));
        }
        convertView.setOnClickListener(onClickListener);
        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle,tvLine;
        LinearLayout llRoot;
    }
    public void reSetPosition(){
        selectedPos=0;
    }



    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

}
