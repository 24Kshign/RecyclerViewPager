package com.example.candyhh.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.am_btn_left)
    Button amBtnLeft;
    @BindView(R.id.am_btn_center)
    Button amBtnCenter;
    @BindView(R.id.am_btn_right)
    Button amBtnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        amBtnLeft.setOnClickListener(this);
        amBtnCenter.setOnClickListener(this);
        amBtnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.am_btn_left:
                startActivity(new Intent(this, LeftActivity.class));
                break;
            case R.id.am_btn_center:
                startActivity(new Intent(this, CenterActivity.class));
                break;
            case R.id.am_btn_right:
                startActivity(new Intent(this, RightActivity.class));
                break;
        }
    }
}
