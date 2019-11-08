package com.B3B.sendmeal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AltaNuevoPlato extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_SAVE = 2;
    static String pathFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altanuevoplato);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        Variables de pantalla
         */
        final EditText editIdPlato = (EditText) findViewById(R.id.editNumIDANP);
        final EditText editTituloPlato = (EditText) findViewById(R.id.editTituloANP);
        final EditText editDescripcionPlato = (EditText) findViewById(R.id.editDescripcionANP);
        final EditText editPrecio = (EditText) findViewById(R.id.editPrecioANP);
        final EditText editCalorias = (EditText) findViewById(R.id.editCaloriasANP);

        final Button btnTomarFomar = (Button) findViewById(R.id.btnAgregarFoto);
        final Button btnGuardarPlato = (Button) findViewById(R.id.buttonGuardarPlato);

        /*

        */

        btnTomarFomar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent takeFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takeFoto.resolveActivity(getPackageManager()) != null){
                    File photoFile = null;
                    try{
                        photoFile = createImageFile();
                    } catch (IOException ex) {

                    }
                    if(photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.android.fileprovider", photoFile);
                        takeFoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takeFoto, REQUEST_IMAGE_SAVE);
                    }
                }
            }
        });

        btnGuardarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editCalorias.getText().toString().equals("") ||
                editDescripcionPlato.getText().toString().equals("") ||
                editIdPlato.getText().toString().equals("") ||
                editPrecio.getText().toString().equals("") ||
                editTituloPlato.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),R.string.faltanDatos,Toast.LENGTH_SHORT).show();
                } else {
                    Plato pl = new Plato(Integer.parseInt(editIdPlato.getText().toString()),
                            Integer.parseInt(editCalorias.getText().toString()),
                            Double.parseDouble(editPrecio.getText().toString()),
                            editTituloPlato.getText().toString(),
                            editDescripcionPlato.getText().toString());
                    //ListaPlatos._PLATOS.add(pl);
                    PlatoRepository.getInstance().crearPlato(pl);
                    Toast.makeText(getApplicationContext(),R.string.platoCreado,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();

        }
    }

    private File createImageFile () throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", dir);
        pathFoto = image.getAbsolutePath();
        return image;
    }

}
