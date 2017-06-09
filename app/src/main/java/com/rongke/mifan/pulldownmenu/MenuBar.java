package com.rongke.mifan.pulldownmenu;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：菜单按钮组
 */

public class MenuBar extends LinearLayout implements PopupWindow.OnDismissListener {
    private final int SMALL = 0;
    private List<RelativeLayout> mPupViewList = new ArrayList<>();
    private Context mContext;
    private int displayWidth;
    private int displayHeight;
    private int selectPosition;
    private PopupWindow popupWindow;
    private MenuButton mCurrMenuButton;//当前选中的菜单按钮

    public MenuBar(Context context) {
        super(context);
    }

    public MenuBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
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
    public void setView(List<String> titleList,List<View> viewList) {
        if (titleList == null || titleList.isEmpty()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < titleList.size(); i++) {
            //添加popuwindow的布局
            RelativeLayout rlRoot = new RelativeLayout(mContext);
            int maxHeight = (int) (displayHeight * 0.7);
            RelativeLayout.LayoutParams params = new RelativeLayout
                    .LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rlRoot.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            rlRoot.addView(viewList.get(i),params);
            mPupViewList.add(rlRoot);
            rlRoot.setTag(SMALL);
            rlRoot.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    onPressBack();
                }
            });


            //添加菜单按钮
            MenuButton menuButton = (MenuButton) inflater.inflate(R.layout.menu_button, this, false);
            menuButton.setTag(i);
            menuButton.setTitle(titleList.get(i));
            menuButton.setOnSelectListener(new MenuButton.OnSelectListener() {
                @Override
                public void onSelect(View v, boolean isSelect) {
                    MenuButton menuButton = (MenuButton) v;
                    if (mCurrMenuButton != null && mCurrMenuButton != menuButton) {
                        mCurrMenuButton.setSelect(false);
                    }
                    mCurrMenuButton = menuButton;
                    Log.e("--------------",mCurrMenuButton.isSelected()+"");
                    selectPosition = (int) menuButton.getTag();
                    startAnimation();

                }
            });
            addView(menuButton);


        }
    }
    private void startAnimation(){
        if (popupWindow == null) {
            popupWindow = new PopupWindow(mPupViewList.get(selectPosition), displayWidth, displayHeight);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(this);
//			popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.color.transparent));
        }

        if (mCurrMenuButton.isSelected()) {
            if (!popupWindow.isShowing()) {
                showPopup(selectPosition);
            } else {
                popupWindow.dismiss();
                hideView();
            }
        }else {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                hideView();
            }
        }
    }
    private void showPopup(int position) {



        if (popupWindow.getContentView() != mPupViewList.get(position)) {
            popupWindow.setContentView(mPupViewList.get(position));
        }
        popupWindow.showAsDropDown(this, 0, 0);
    }
    /**
     * 如果菜单成展开状态，则让菜单收回去
     */
    public boolean onPressBack(){
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            hideView();
            if (mCurrMenuButton != null) {
                mCurrMenuButton.setSelect(false);
            }
            return true;
        }else {
            return false;
        }
    }

    /**
     * 隐藏对应的
     */
    private void hideView(){

    }

    /**
     * 设置标题
     */
    public void setTitle(String title){
        if(mCurrMenuButton!=null&&title!=null){
            mCurrMenuButton.setTitle(title);
        }
        onPressBack();
    }


    @Override
    public void onDismiss() {
      //  popupWindow.setOnDismissListener(null);
        if (mCurrMenuButton != null) {
            mCurrMenuButton.setSelect(false);
        }
    }



}
