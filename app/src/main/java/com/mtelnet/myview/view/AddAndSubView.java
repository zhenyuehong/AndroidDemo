package com.mtelnet.myview.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtelnet.myview.R;
import com.mtelnet.myview.utils.Utils;


/**
 * Created by mtelnet on 16/1/6.
 */
public class AddAndSubView extends RelativeLayout {

    private TextView add, subtract, money;
    private EditText numberEdit;
    private View view;
    Context context;

    OnNumChangeListener onNumChangeListener;

    long num;          //editText中的数值
    int addAmount;

    public AddAndSubView(Context context) {
        super(context);
        this.context = context;
        num = 0;
        control();
    }

    /**
     * 带初始数据实例化
     *
     * @param context
     */
    public AddAndSubView(Context context, int num) {
        super(context);
        this.context = context;
        this.num = (long)num;
        control();
    }


    public AddAndSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        num = 0;
        control();
    }

    /**
     *
     */
    private void control() {
//        initTextWithHeight();
        initialise();          //实例化内部view
//        setViewsLayoutParm();  //设置内部view的布局参数
//        insertView();            //将子view放入linearlayout中
        setViewListener();
    }


    /**
     * 实例化内部View
     */
    private void initialise() {
        view = inflate(context, R.layout.addandsubtract, null);
        addView(view);
        money = (TextView) view.findViewById(R.id.id_money);
        add = (TextView) view.findViewById(R.id.id_add);
        subtract = (TextView) view.findViewById(R.id.id_subtract);
        numberEdit = (EditText) view.findViewById(R.id.id_number);
        add.setTag("+");
        subtract.setTag("-");
        //设置输入类型为数字
        numberEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        numberEdit.setText(String.valueOf(num));

    }

    /**
     * 设置+ - 的颜色
     */
    public void setAddSubColor(int color) {
        add.setTextColor(color);
        subtract.setTextColor(color);
    }

    /**
     * 设置edittext文字的颜色
     */
    public void setMoneyColor(int color) {
        numberEdit.setTextColor(color);
        money.setTextColor(color);
    }

    /**
     * 设置editText中增加减少的值
     *
     * @param addAmount
     */
    public void setAddAmount(int addAmount) {
        this.addAmount = addAmount;
    }

    /**
     * 设置editText中的值
     *
     * @param num
     */
    public void setNum(int num) {
        this.num = (long)num;
        numberEdit.setText(Utils.addComma(String.valueOf(num)));
    }

    /**
     * 获取editText中的值
     *
     * @return
     */
    public String getNum() {
        if (numberEdit.getText().toString() != null) {
            return numberEdit.getText().toString();
        } else {
            return "0";
        }
    }


    /**
     * 设置EditText文本变化监听
     *
     * @param onNumChangeListener
     */
    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
        this.onNumChangeListener = onNumChangeListener;
    }


    /**
     * 设置文本变化相关监听事件
     */
    private void setViewListener() {
        add.setOnClickListener(new OnButtonClickListener());
        subtract.setOnClickListener(new OnButtonClickListener());
        numberEdit.addTextChangedListener(new OnTextChangeListener());
    }

    /**
     * 加减按钮事件监听器
     */
    class OnButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            String numString = numberEdit.getText().toString();
            if (numString == null || numString.equals("")) {
                num = 0;
                numberEdit.setText("0");
            } else {
                if (v.getTag().equals("+")) {
                    num = num + addAmount;
                    if (num < 0)  //先加，再判断
                    {
                        num = num - addAmount;

                    } else {
                        numberEdit.setText(Utils.addComma(String.valueOf(num)));

                        if (onNumChangeListener != null) {
                            onNumChangeListener.onNumChange(AddAndSubView.this, num);
                        }
                    }
                } else if (v.getTag().equals("-")) {
                    num = num - addAmount;
                    if (num < 0)  //先减，再判断
                    {
                        num = num + addAmount;

                    } else {
                        numberEdit.setText(Utils.addComma(String.valueOf(num)));
                        if (onNumChangeListener != null) {
                            onNumChangeListener.onNumChange(AddAndSubView.this, num);
                        }
                    }
                }
            }
        }
    }

    /**
     * EditText输入变化事件监听器
     */
    class OnTextChangeListener implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {

            String numString = s.toString();
            int length = numString.length();

//            if (length >= 9) {
//                Toast.makeText(context, "字符不能超过10个",
//                        Toast.LENGTH_SHORT).show();
//                return;
//            }

//            //如果字符串中有小数点，就对小数点进行分割，
//            if(numString.contains(".")){
//                String[] strArr = numString.split("\\.");
//                //判断小数点后面的位数，如果超过两位就删除后面的数据，不让输入
//                if(strArr.length>1 && strArr[1].length()>2){
//                    s.delete(numString.length()-1, numString.length());
//                }
//            }
//            //如果不包含小数点，只能输入20整数，如果包含小数点就按照小数点的进行处理
//            if(numString.length()>20 && !numString.contains(".")){
//                s.delete(numString.length() - 1, numString.length());
//            }

            if (numString == null || numString.equals("")) {
                num = 0;
//                numberEdit.setText("");
//                numberEdit.setText(Utils.addComma(String.valueOf(num)));
                if (onNumChangeListener != null) {
                    onNumChangeListener.onNumChange(AddAndSubView.this, num);
                }
            } else {
                long numInt = Long.parseLong(numString.replace(",",""));
//                if (length >= 9) {
//                    Toast.makeText(context, "字符不能超过10个",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (numInt < 0) {
//                    Toast.makeText(context, "请输入一个大于0的数字",
//                            Toast.LENGTH_SHORT).show();
//                } else {

                    //设置EditText光标位置 为文本末端
                    numberEdit.setSelection(numberEdit.getText().toString().length());
                    num = numInt;
//                    numberEdit.setText("");
//                    numberEdit.setText(Utils.addComma(String.valueOf(num)));
                    if (onNumChangeListener != null) {
                        onNumChangeListener.onNumChange(AddAndSubView.this, num);
                    }
//                }
            }
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

    }


    public interface OnNumChangeListener {
        /**
         * 输入框中的数值改变事件
         *
         * @param view 整个AddAndSubView
         * @param num  输入框的数值
         */
        public void onNumChange(View view, long num);
    }

}