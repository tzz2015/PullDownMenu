package com.rongke.mifan.pulldownmenu;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：菜单按钮组
 */

public class MenuBar extends LinearLayout {
    private  Context mContext;
    private int displayWidth;
    private int displayHeight;
    private int selectPosition;
    private  MenuButton mCurrMenuButton;//当前选中的菜单按钮
    private OnButtonClickListener mOnButtonClickListener;
    public MenuBar(Context context) {
        super(context);
    }

    public MenuBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MenuBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 设置布局
     */
    public void setView(List<String> titleList){
        if(titleList==null||titleList.isEmpty()){
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i=0;i<titleList.size();i++){
             MenuButton menuButton= (MenuButton) inflater.inflate(R.layout.menu_button,this,false);
            menuButton.setTag(i);
            menuButton.setTitle(titleList.get(i));
            menuButton.setOnSelectListener(new MenuButton.OnSelectListener() {
                @Override
                public void onSelect(View v, boolean isSelect) {
                    MenuButton menuButton = (MenuButton)v;
                    if(mCurrMenuButton!=null&&mCurrMenuButton!=menuButton){
                        mCurrMenuButton.setSelect(false);
                    }
                    mCurrMenuButton=menuButton;
                    selectPosition= (int) menuButton.getTag();
                    //回调
                    if(mOnButtonClickListener!=null&&isSelect){
                        mOnButtonClickListener.onClick(selectPosition);
                    }
                }
            });
            addView(menuButton);


        }
    }

    /**
     * 设置tabitem的点击监听事件
     */
    public void setOnButtonClickListener(OnButtonClickListener l) {
        mOnButtonClickListener = l;
    }

    /**
     * 自定义tabitem点击回调接口
     */
    public interface OnButtonClickListener {
        public void onClick(int selectPosition);
    }

}
