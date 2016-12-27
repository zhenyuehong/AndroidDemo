package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mtelnet.myview.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;
// 标签云
public class SymptomActivity extends AppCompatActivity {
    // 标签云父布局
    private FlowLayout mFlowLayout;
    // 标签名称列表
    private List<String> labelNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);

        mFlowLayout= (FlowLayout) findViewById(R.id.fly_symptom_one);

        labelNameList = new ArrayList<>();
        labelNameList.add("感冒");
        labelNameList.add("头疼");
        labelNameList.add("头晕");
        labelNameList.add("反胃");
        labelNameList.add("肚子疼");
        labelNameList.add("脖子痛");
        labelNameList.add("腰痛");
        labelNameList.add("脑袋痛");
        labelNameList.add("脚痛");

        // 数据源为空则返回
        if (labelNameList == null || labelNameList.size() == 0){
            return;
        }
        // 遍历数据
        for (String labelName: labelNameList){
            // 指定子标签布局
            final View labelView = LayoutInflater.from(this).inflate(R.layout.layout_child_selected_label, null);
            // 症状名称
            TextView symptomSelectedNameTv = (TextView) labelView.findViewById(R.id.tv_symptom_selected_name);
            // 清除事件
            labelView.findViewById(R.id.iv_symptom_selected_clear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFlowLayout != null && labelView != null)
                        mFlowLayout.removeView(labelView);
                }
            });
            if (symptomSelectedNameTv != null && labelName != null && !labelName.trim().equals("")){
                symptomSelectedNameTv.setText(labelName);
            }
            if (mFlowLayout != null && labelView != null){
                mFlowLayout.addView(labelView);
            }
        }
        // 刷新界面
        mFlowLayout.requestLayout();
        mFlowLayout.invalidate();
    }
}
