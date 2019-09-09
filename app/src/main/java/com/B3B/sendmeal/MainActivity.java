package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;

import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
    *   Variables de pantalla
    */
        final Switch switchVendedor = (Switch)findViewById(R.id.switchVendedor);
        final LinearLayout layoutVendedor = (LinearLayout)findViewById(R.id.layoutVendedor);
        final SeekBar seekCredito = (SeekBar)findViewById(R.id.seekCredito);
        final TextView textValorCredito = (TextView) findViewById(R.id.textValorCredito);
        final Button btnRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        final CheckBox checkCondiciones = (CheckBox) findViewById(R.id.checkAceptoCondiciones);
        /*
        EditText
         */
        final EditText editNombre = (EditText) findViewById(R.id.editNombre);
        final EditText editClave = (EditText) findViewById(R.id.editPassword);
        final EditText editConfirmacionClave = (EditText) findViewById(R.id.editPasswordCof);
        final EditText editEmail = (EditText) findViewById(R.id.editEmail);
        final EditText editNumeroTarjeta = (EditText) findViewById(R.id.editNumTarjeta);
        final EditText editCCV = (EditText) findViewById(R.id.editCCV);
        final EditText editFechaExpira = (EditText) findViewById(R.id.editDate);
        final EditText editAliasCBU = (EditText) findViewById(R.id.editAliasCBU);
        final EditText editCBU = (EditText) findViewById(R.id.editCBU);
    /*
    *
    */
        switchVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchVendedor.isChecked()){
                    layoutVendedor.setVisibility(View.VISIBLE);
                } else layoutVendedor.setVisibility(View.GONE);
            }
        });

        seekCredito.setMax(1400);
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

        checkCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               btnRegistrar.setEnabled(checkCondiciones.isChecked());
            }
        });

        /*
        Borro texto por default cuando se selecciona el campo
         */
        //-------------------------------------------------------------------------------------------------------------------------
        // Ver como hacer lindo esto, no funca asi y no tengo ganas de fijarme ahora
        //--------------------------------------------------------------------------------------------------------------------------
        /*editNumeroTarjeta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (editNumeroTarjeta.getText().toString().equals("Número")){
                    editNumeroTarjeta.getText().clear();
                }
                return false;
            }
        });
        editCCV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (editCCV.getText().toString().equals("CCV")){
                    editCCV.getText().clear();
                }
                return false;
            }
        });
        editFechaExpira.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.)
                if (editFechaExpira.getText().toString().equals("MM/AA")){
                    editFechaExpira.getText().clear();
                }
                return false;
            }
        });*/

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validaciones
                 */
                if (editNombre.getText().toString().equals("") || editClave.getText().toString().equals("") ||
                        editConfirmacionClave.getText().toString().equals("") || editEmail.getText().toString().equals("") ||
                        editNumeroTarjeta.getText().toString().equals("Número") || editCCV.getText().toString().equals("CCV") ||
                        editFechaExpira.getText().toString().equals("MM/AA"))
                    {
                    if (switchVendedor.isChecked() && (editAliasCBU.toString().equals("") || editCBU.toString().equals("")))
                        //Faltan datos
                        Toast.makeText(getApplicationContext(),R.string.faltanDatos,Toast.LENGTH_LONG).show();
                    }
                else {
                    // No coincidencia de claves
                    if (!editClave.getText().toString().equals(editConfirmacionClave.getText().toString())){
                        Toast.makeText(getApplicationContext(),R.string.clavesDistintas,Toast.LENGTH_LONG).show();
                    } else {
                        String email = editEmail.getText().toString();
                        if (!editEmail.getText().toString().contains("@") ||
                                (email.substring(editEmail.getText().toString().lastIndexOf("@")).length() < 4))
                            Toast.makeText(getApplicationContext(),R.string.emailNoValido,Toast.LENGTH_LONG).show();
                        else ;/*
                         Hacer validacion de fecha de tarjeta
                        */
                    }
                }

            }
        });
    }
}
