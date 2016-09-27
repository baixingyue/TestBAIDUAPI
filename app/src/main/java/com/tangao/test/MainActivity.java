package com.tangao.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText etNeedToTrans;
    private Button btnStartTrans;
    private TextView tvTransResult;
    //翻译源语言
    private String from = "auto";
    //目标语言
    private String to = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        etNeedToTrans = (EditText) findViewById(R.id.main_et_needToTrans);
        tvTransResult = (TextView) findViewById(R.id.main_tv_transResult);
        btnStartTrans = (Button) findViewById(R.id.main_btn_startTrans);

        btnStartTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext = etNeedToTrans.getText().toString();

                RequestUtils requestUtils = new RequestUtils();

                if (!inputtext.isEmpty()) {
                    try {
                        requestUtils.translate(inputtext, from, to, new HttpCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                tvTransResult.setText("翻译结果：\n" + result);
                            }

                            @Override
                            public void onFailure(String exception) {
                                Toast.makeText(MainActivity.this, "翻译出错信息： " + exception, Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请输入要翻译的内容!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}