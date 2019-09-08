package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
    *   Variables de pantalla
    */
        final Switch sw = (Switch)findViewById(R.id.switchVendedor);
        final LinearLayout layoutVendedor = (LinearLayout)findViewById(R.id.layoutVendedor);
        final SeekBar seekCredito = (SeekBar)findViewById(R.id.seekCredito);
        final TextView textValorCredito = (TextView) findViewById(R.id.textValorCredito);
    /*
    *
    */
        seekCredito.setMax(1400);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw.isChecked()){
                    layoutVendedor.setVisibility(View.VISIBLE);
                } else layoutVendedor.setVisibility(View.GONE);

            }
        });
        seekCredito.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                textValorCredito.setText(String.valueOf(100 + new Integer(progress)));
            }
        });
    }
}
