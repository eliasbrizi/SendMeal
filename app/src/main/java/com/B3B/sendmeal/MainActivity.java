package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch sw = (Switch)findViewById(R.id.switchVendedor);
        final LinearLayout layoutVendedor = (LinearLayout)findViewById(R.id.layoutVendedor);

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw.isChecked()){
                    layoutVendedor.setVisibility(View.VISIBLE);
                } else layoutVendedor.setVisibility(View.GONE);

            }
        });
    }
}
