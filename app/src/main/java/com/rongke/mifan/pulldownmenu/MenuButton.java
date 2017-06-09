package com.rongke.mifan.pulldownmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：自定义菜单按钮
 */

public class MenuButton extends RelativeLayout {
    private Context mContext;
    private TextView tv_title;
    private TextView tv_line;
    private RelativeLayout rl_root;
    private boolean selected;
    private OnSelectListener onSelectListener;

    public MenuButton(Context context) {
        super(context);
    }

    public MenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }


    public MenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_menu_button, this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_line = (TextView) findViewById(R.id.tv_line);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        rl_root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected){
                    selected=false;
                }else {
                    selected=true;
                }
                setSelect(selected);
                if(onSelectListener!=null){
                    onSelectListener.onSelect(MenuButton.this,selected);
                }

            }
        });
    }
    /**
     * s设置标题
     */
    public void setTitle(String title){
        if(title!=null){
            tv_title.setText(title);
        }
    }
    /**
     * 获取标题
     */
    public String getTitle(){
        return tv_title.getText().toString();
    }
    /**
     * 是否被选中
     */
    public boolean  isSelected(){
        return selected;
    }
    /**
     * 设置选中状态
     */
    public void setSelect(boolean selected){
        this.selected=selected;
        if(selected){
            tv_title.setTextColor(mContext.getResources().getColor(R.color.public_orange));
           setDrawable(R.mipmap.arrow_top);
        }else {
            tv_title.setTextColor(mContext.getResources().getColor(R.color.color333333));
            setDrawable(R.mipmap.arrow_bottom);
        }
    }
    /**
     * 设置检讨
     */
    private void setDrawable(int resourId){
        Drawable drawable = mContext.getResources().getDrawable(
                resourId);
        tv_title.setCompoundDrawablePadding(15);
        tv_title.setCompoundDrawablesWithIntrinsicBounds(null, null,
                drawable, null);
    }
    /**
     * 隐藏分隔条
     */
    public void hideLine(){
        tv_line.setVisibility(GONE);
    }

    /**
     * 设置监听
     * @param onSelectListener
     */
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }
    interface OnSelectListener {
        void onSelect(View v,boolean isSelect);
    }
}
