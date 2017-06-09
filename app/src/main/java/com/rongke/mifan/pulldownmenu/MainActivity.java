package com.rongke.mifan.pulldownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        AutoUtils.setSize(this, false, 720, 1280);
        AutoUtils.auto(this);
    }

    private void initView() {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.activity_main);
        final MenuBar menuBar= (MenuBar) findViewById(R.id.mb_bar);
        List<String> title=new ArrayList<>();
        title.add("商圈");
        title.add("分类");
        title.add("综合排序");
        List<String> content=new ArrayList<>();
        content.add("全部");
        content.add("广州");
        content.add("杭州");
        content.add("苏州");
        content.add("柳州");
        List<View> viewList=new ArrayList<>();
        MyListView myListView = new MyListView(getApplicationContext(), content);
        myListView.setOnSelectListener(new MyListView.OnSelectListener() {
            @Override
            public void getValue(String value, int position) {
                 menuBar.setTitle(value);
            }
        });
        viewList.add(myListView);
        //双列表
        List<String> child=new ArrayList<>();
        for(int i=0;i<content.size();i++){
           child.add("子项"+i);
        }
        List<List<String>> childList=new ArrayList<>();
        for(int i=0;i<content.size();i++){
            childList.add(child);
        }
         DoubleListView doubleListView=new DoubleListView(getApplicationContext(),content,childList);
        doubleListView.setOnSelectListener(new DoubleListView.OnSelectListener() {
            @Override
            public void getValue(String value, int position) {
                menuBar.setTitle(value);
            }
        });
        viewList.add(doubleListView);
        viewList.add(new MyListView(getApplicationContext(),content));
        menuBar.setView(title,viewList);

    }
}
