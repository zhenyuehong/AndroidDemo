package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.mtelnet.myview.utils.EditTextHelper;

public class EditInputActivity extends AppCompatActivity {
    private static final String INT_REGEX = "^[1-9]\\d*$";
    private static final String DECIMAL_REGEX = "^[0-9]+(\\.[0-9]+)?$";
    private static final String EXTRA_REGEX = "^[0-9]+(\\.)$";

    private static final String DECIMAL_ZERO_REGEX = "\\.[0-9]+";
    private static final String DECIMAL_POINT = ".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_input);
        final EditText et_input = (EditText) findViewById(R.id.et_input);
//        setPricePoint(et_input);

        et_input.setText("0%");
        et_input.setSelection(et_input.getText().toString().trim().length()-1);

        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length()-1>0)
                et_input.setSelection(s.length()-1);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String str = edt.toString().trim();

                if (str.length()-1>0){
                    et_input.setSelection(str.length()-1);
                }else {
                    if (!str.startsWith("%")){
                        et_input.setSelection(str.length());
                    }else {
                        et_input.setText("");
                    }

                }
                if (str.startsWith(".")){
                    et_input.setText("0");

                }

                if ( !TextUtils.isEmpty(str)){
                    if (!str.contains("%")&&str.length()>1){

                        str=str+"%";
                        String sub=str.substring(0,str.indexOf("%"));
                        Log.d(" sub:", sub);
                        if (!str.startsWith("0.")&&str.startsWith("0")){
                            str=str.substring(1,str.length());
                            et_input.setSelection(str.length()-1);
                        }

                        et_input.setText(str);
                    }
                }
            }
        });

        et_input.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String content = et_input.getText().toString();

                    Log.d(" content ", "onKey: "+content);
                    if (!content.contains("%")){
                        et_input.setText("");
                    }
                }
                return false;
            }
        });



        EditText et_limit2= (EditText) findViewById(R.id.et_limit2);
        EditTextHelper.INSTANCE.limitTwoDecimalPlaces(et_limit2);
    }


    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }
}
