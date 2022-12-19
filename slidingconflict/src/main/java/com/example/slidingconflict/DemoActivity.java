package com.example.slidingconflict;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DemoActivity extends Activity {
    private static final String TAG = "SecondActivity";
    //自定义的父元素
    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.d(TAG,"onCreate");
        initView();
    }

    //初始化View
    private void initView(){
        LayoutInflater inflater = getLayoutInflater();
        //获取父容器
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        //1.这里创建了3个子元素，由ListView和TextView构成
        //把这3个子元素添加到父布局中
        for(int i=0 ; i<3 ; i++){
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout,mListContainer,false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page "+(i+1));
            layout.setBackgroundColor(Color.rgb(255/(i+1),255/(i+1),0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    //2.创建ListView
    //创建适配器Adapter，加载item布局
    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0 ; i < 50 ; i++){
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.content_list_item,R.id.name,datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DemoActivity.this,"click item",Toast.LENGTH_SHORT).show();
            }
        });
    }
}