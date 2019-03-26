package com.meng.mengtestapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.bean.DropBean;
import com.meng.mengtestapplication.view.DropdownButton;

import java.util.ArrayList;
import java.util.List;

public class TestDropdownButtonActivity extends AppCompatActivity {
    private DropdownButton dropdownButton1;
    private DropdownButton dropdownButton2;
    private DropdownButton dropdownButton3;
    private List<DropBean> times;
    private List<DropBean> types;
    private List<DropBean> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dropdown_button);

        dropdownButton1 = findViewById(R.id.dropdown_button1);
        dropdownButton2 = findViewById(R.id.dropdown_button2);
        dropdownButton3 = findViewById(R.id.dropdown_button3);

        initSomeData();

        dropdownButton1.setData(times);
        dropdownButton2.setData(types);
        dropdownButton3.setData(names);
    }

    private void initSomeData() {
        times = new ArrayList<>();
        types = new ArrayList<>();
        names = new ArrayList<>();

        int mouth = 5;
        int year = 2019;
        times.add(new DropBean("全部时间"));
        times.add(new DropBean(year + "年 全年"));

        for (int i = 0; i < mouth; i++) {
            times.add(new DropBean(year + "年" + (i + 1) + "月"));
        }

        types.add(new DropBean("交通"));
        types.add(new DropBean("饮食"));

        names.add(new DropBean("小张"));
        names.add(new DropBean("小王"));
        names.add(new DropBean("小李"));
        names.add(new DropBean("小刘"));
    }
}
