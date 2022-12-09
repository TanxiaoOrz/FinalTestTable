package com.example.finaltesttable;



import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost tabHost;
    private String[] context;

    private Record record;
    private DbUtils dbUtils;

    private ArrayList<Record> recordArrayList;

    private Button load;
    private Button save;

    private FirstPageFragment firstPageFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbUtils = new DbUtils(this,"my.db",null,1);
        recordArrayList=Record.selectAll(dbUtils.getReadableDatabase());
        for(Record r:recordArrayList){
            System.out.println(r.toString());
        }//辅助查看
        tabHost=(FragmentTabHost)super.findViewById(android.R.id.tabhost);
        //在tabHost创建FragmentLayout , contentLayout的id就是布局文件里面FragmentLayout的id，如果在布局文件FragmentLayout添加子元素，那么实际上相互切换的3个FragmentLayout都会被添加子元素
        tabHost.setup(this,super.getSupportFragmentManager(),R.id.activity_home_container);
        //getTabWidget()方法表示获取切换卡，setDividerDrawable(null)表示没有分割线
        tabHost.getTabWidget().setDividerDrawable(null);
        //点击下面5个图标的时候会变色，就是setOnTabChangedListener(this)方法影响的，如果没有这个方法，那么点击图标不会变色
        tabHost.setOnTabChangedListener(this);
        initTab();
        load=findViewById(R.id.read);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPageFragment.loadRecord();
            }
        });

        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//若是新纪录
                if (record==null) {
                    record = firstPageFragment.getRecord();//获取
                    record.insert(dbUtils.getWritableDatabase());//插入
                    Toast.makeText(MainActivity.this, "保存完毕~", Toast.LENGTH_SHORT).show();
                }else {//若是更新记录
                    String id =record.getId();
                    record = firstPageFragment.getRecord();
                    record.setId(id);
                    record.update(dbUtils.getWritableDatabase());
                    Toast.makeText(MainActivity.this, "更新完毕~", Toast.LENGTH_SHORT).show();
                }
                recordArrayList = Record.selectAll(dbUtils.getReadableDatabase());//更新
                for(Record r:recordArrayList){
                    System.out.println(r.toString());
                }//辅助查看
                record=recordArrayList.get(recordArrayList.size()-1);

            }
        });
    }

    //initTab()方法表示没有点击图标的时候的初始状态
    private void initTab() {
        String tabs[]=TabDb.getTabsTxt();
        for(int i=0;i<tabs.length;i++){
            TabHost.TabSpec tabSpec=tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            //addTab()方法表示添加导航栏
            tabHost.addTab(tabSpec,TabDb.getFragment()[i],null);
            tabHost.setTag(i);
            tabHost.setCurrentTab(0);
        }
    }

    private View getTabView(int idx) {
        //引入footer_tabs.xml布局，这个布局包含了导航栏的图片和文本，我这里通过设置布局文件参数把文本隐藏了
        View view= LayoutInflater.from(this).inflate(R.layout.foot_tab,null);

        ((TextView)view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
        //下面的判断语句表示图标颜色的默认状态，第一个图标默认点亮，其他默认不点亮
        if(idx==0){

            ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.RED);
            //设置为点亮的文字颜色
        }else{
            //设置为点不亮的文字颜色
            ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.GRAY);
        }
        return view;
    }

    //onTabChanged自带方法
    @Override
    public void onTabChanged(String tabId) {
        updateTab();
    }

    //点击图标就会调用updateTab()方法
    private void updateTab(){
        //TabWidget表示切换卡
        TabWidget tabw=tabHost.getTabWidget();
        for(int i=0;i<tabw.getChildCount();i++){
            View view=tabw.getChildAt(i);
            if(i==tabHost.getCurrentTab()){
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.RED);
            }else{
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.GRAY);
            }

        }
    }
    public void setRecord(Record record) {
        this.record = record;
    }

    public ArrayList<Record> getRecordArrayList() {
        return recordArrayList;
    }

    public void setFirstPageFragment(FirstPageFragment firstPageFragment) {
        this.firstPageFragment = firstPageFragment;
    }

    public String[] getContext() {
        return context;
    }

    public void setContext(String[] context) {
        this.context = context;
    }

    public Record getRecord() {
        return record;
    }
}