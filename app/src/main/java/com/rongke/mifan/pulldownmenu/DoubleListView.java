package com.rongke.mifan.pulldownmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：
 */

public class DoubleListView extends LinearLayout {
    private final List<String> contentList;
    private Context mContext;
    private OnSelectListener mOnSelectListener;
    private final List<List<String>> childList=new ArrayList<>();
    private final List<String> child=new ArrayList<>();
    private TextAdapter adapter2;
    private int parentPositoin;

    public DoubleListView(Context context, List<String> contentList, List<List<String>> childList) {
        super(context);
        this.contentList = contentList;
        this.childList.clear();
        child.clear();
        this.childList.addAll(childList);
        child.addAll(childList.get(0));
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.double_list, this, true);
        ListView listView = (ListView)view. findViewById(R.id.listViewOne);
        ListView listView2 = (ListView)view. findViewById(R.id.listViewTwo);
        if (contentList == null || contentList.isEmpty()) {
            return;
        }
        TextAdapter adapter = new TextAdapter(mContext, contentList, R.color.colorWhite, R.color.colorWhite);
        listView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                parentPositoin=position;
                child.clear();
                child.addAll(childList.get(position));
                adapter2.reSetPosition();
                adapter2.notifyDataSetChanged();
            }
        });
        adapter2 = new TextAdapter(mContext, child, R.color.colorWhite, R.color.colorWhite);
        listView2.setAdapter(adapter2);
        adapter2.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mOnSelectListener != null) {
                    mOnSelectListener.getValue(child.get(position), position);
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String value, int position);
    }
}
