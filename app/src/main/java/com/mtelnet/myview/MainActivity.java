package com.mtelnet.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtelnet.myview.zoomanim.FeedActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String str1 = "自定义View进阶 - 绘制基本图形";
    String str2 = "自定义View进阶 - 画布操作";
    String str3 = "Notification";
    String str4 = "轮播图";
    String str5 = "自定义加减view";
    String str6 = "自定义View进阶 - Path完结篇(伪)";
    String str7 = "自定义CircleProgressBar-显示进度";
    String str8 = "EditText 输入百分数";
    String str9 = "Activity 共享元素转场动画";
    String str10 = "android图片中添加文字水印";
    String str11 = "使用 CoordinatorLayout 实现复杂联动效果 ";
    String str12 = "仿iOS dialog";
    String str13 = "使用jsoup抓取网页数据";
    String str14 = "标签云";
    String str15 = "下拉刷新RefreshLayout";
    String str16 = "日历";
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList.add(str1);
        mList.add(str2);
        mList.add(str3);
        mList.add(str4);
        mList.add(str5);
        mList.add(str6);
        mList.add(str7);
        mList.add(str8);
        mList.add(str9);
        mList.add(str10);
        mList.add(str11);
        mList.add(str12);
        mList.add(str13);
        mList.add(str14);
        mList.add(str15);
        mList.add(str16);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RAdapter());
    }


    class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.recycle_item, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv_item.setText(mList.get(position));
            holder.tv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        goTargetPage(View1Activity.class);
                    } else if (position == 1) {
                        goTargetPage(View2Activity.class);
                    } else if (position == 2) {
                        goTargetPage(NotificationActivity.class);
                    } else if (position == 3) {
                        goTargetPage(lunboActivity.class);
                    } else if (position == 4) {
                        goTargetPage(AddAndSubActivity.class);
                    } else if (position == 5) {
                        goTargetPage(View3Activity.class);
                    } else if (position == 6) {
                        goTargetPage(CircleProgressBarActivity.class);
                    } else if (position == 7) {
                        goTargetPage(EditInputActivity.class);
                    } else if (position == 8) {
                        goTargetPage(FeedActivity.class);
                    } else if (position == 9) {
                        goTargetPage(DrawTextToBitmapActivity.class);
                    }else if (position==10){
                        goTargetPage(ScrollingActivity.class);
                    }else if (position==11){
                        goTargetPage(IOSDialogActivity.class);
                    }else if (position==12){
                        goTargetPage(JsoupActivity.class);
                    }else if (position==13){
                        goTargetPage(SymptomActivity.class);
                    }else if (position==14){
                        goTargetPage(RefreshLayoutActivity.class);
                    }else if (position==15){
                        goTargetPage(CalendarActivity.class);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mList.size() > 0 ? mList.size() : 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_item;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_item = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }

    private void goTargetPage(Class<?> atyClass) {
        startActivity(new Intent(this, atyClass));
    }


}
