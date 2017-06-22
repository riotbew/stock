package com.tauren.stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tauren.common.BaseActivity;
import com.tauren.common.BindView;

import butterknife.ButterKnife;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class DemoActivity extends BaseActivity {

    @BindView(id = R.id.demo_annotation)
    public Button button1 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        ButterKnife.bind(this);
        findViewById(R.id.demo_annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DemoActivity", "click");
                button1.setText("什么鬼");
            }
        });
    }
}
