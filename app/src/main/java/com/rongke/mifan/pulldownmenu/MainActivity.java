package com.rongke.mifan.pulldownmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        MenuBar menuBar= (MenuBar) findViewById(R.id.mb_bar);
        List<String> title=new ArrayList<>();
        title.add("商圈");
        title.add("分类");
        title.add("综合排序");
        menuBar.setView(title);
    }
}
