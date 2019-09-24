package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDateTime;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    /*
    *   Variables de pantalla
    */
        final Switch switchVendedor = (Switch)findViewById(R.id.switchVendedor);
        final LinearLayout layoutVendedor = (LinearLayout)findViewById(R.id.layoutVendedor);
        final SeekBar seekCredito = (SeekBar)findViewById(R.id.seekCredito);
        final TextView textValorCredito = (TextView) findViewById(R.id.textValorCredito);
        final Button btnRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        final CheckBox checkCondiciones = (CheckBox) findViewById(R.id.checkAceptoCondiciones);
        final RadioGroup radioGroupTipoDeCuenta = (RadioGroup) findViewById(R.id.radioGroupTipoCuenta);
        final RadioButton radioButtonBasica = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton radioButtonFull = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton radioButtonPremium = (RadioButton) findViewById(R.id.radioButton3);
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

        radioGroupTipoDeCuenta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonBasica.isChecked()){
                    seekCredito.setProgress(0);
                    textValorCredito.setText("100");
                }   else
                    { if(radioButtonFull.isChecked()){
                        seekCredito.setProgress(150);
                        textValorCredito.setText("250");
                        } else
                            if (radioButtonPremium.isChecked())
                            seekCredito.setProgress(400);
                            textValorCredito.setText("500");
                    }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valido = true;
                /*
                Validaciones
                 */
                if (editNombre.getText().toString().equals("") || editClave.getText().toString().equals("") ||
                        editConfirmacionClave.getText().toString().equals("") || editEmail.getText().toString().equals("") ||
                        editNumeroTarjeta.getText().toString().equals("Número") || editCCV.getText().toString().equals("CCV") ||
                        editFechaExpira.getText().toString().equals("MM/AA"))
                    {
                        Toast.makeText(getApplicationContext(),R.string.faltanDatos,Toast.LENGTH_LONG).show();
                        valido = false;
                    }
                else {
                    /*
                    Vendedor
                     */
                    if (switchVendedor.isChecked() && (editAliasCBU.toString().equals("") || editCBU.toString().equals(""))) {
                        Toast.makeText(getApplicationContext(), R.string.faltanDatos, Toast.LENGTH_LONG).show();
                        valido = false;
                    }/*
                    Claves
                     */
                    if (!editClave.getText().toString().equals(editConfirmacionClave.getText().toString())){
                        Toast.makeText(getApplicationContext(),R.string.clavesDistintas,Toast.LENGTH_LONG).show();
                        valido = false;
                    }
                    /*

                     */
                    else {
                        String email = editEmail.getText().toString();
                        if (!editEmail.getText().toString().contains("@") ||
                                (email.substring(editEmail.getText().toString().lastIndexOf("@")).length() < 4)) {
                            Toast.makeText(getApplicationContext(), R.string.emailNoValido, Toast.LENGTH_LONG).show();
                            valido = false;
                        }
                        else {
                            LocalDateTime fechaActual = LocalDateTime.now();
                            Integer mesActual = fechaActual.getMonthValue();
                            Integer yearActual = fechaActual.getYear();
                            Integer mesTarjeta = Integer.parseInt(editFechaExpira.getText().toString().substring(0,1));
                            Integer yearTarjeta = Integer.parseInt(editFechaExpira.getText().toString().substring(3,4))+ 2000;
                            if(yearTarjeta < yearActual){
                                Toast.makeText(getApplicationContext(),R.string.tarjetaVencimiento,Toast.LENGTH_LONG).show();
                                valido = false;
                            }
                            else{
                                if(yearTarjeta == yearActual){
                                    if(mesTarjeta - mesActual > 3) {
                                        //valido
                                    }
                                }
                                else if(yearTarjeta == yearActual + 1){
                                    if(mesTarjeta + 12 - mesActual > 3 ){
                                        //valido
                                    }
                                }
                                else if(yearTarjeta > yearActual + 1){
                                    //valido
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),R.string.tarjetaVencimiento,Toast.LENGTH_LONG).show();
                                    valido = false;
                                }

                            }
                        };
                    }
                }
                if (valido) Toast.makeText(getApplicationContext(),R.string.guardadoExitoso,Toast.LENGTH_LONG).show();

            }
        });
    }
}
