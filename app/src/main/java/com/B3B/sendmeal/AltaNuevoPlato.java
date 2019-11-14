package com.B3B.sendmeal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AltaNuevoPlato extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_SAVE = 2;
    static String pathFoto;
    static String fotoEnBase64 = null;
    static ImageView fotoPlato;

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

        fotoPlato = (ImageView) findViewById(R.id.fotoPlato);

        final Button btnTomarFomar = (Button) findViewById(R.id.btnAgregarFoto);
        final Button btnGuardarPlato = (Button) findViewById(R.id.buttonGuardarPlato);

        btnTomarFomar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent takeFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takeFoto.resolveActivity(getPackageManager()) != null){
                    File photoFile = null;
                    try{
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        Log.d("FALLO","TOMAR FOTO");
                    }
                    if(photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.B3B.sendmeal.android.fileprovider", photoFile);
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
                    pl.setFotoBase64(fotoEnBase64);
                    PlatoRepository.getInstance().crearPlato(pl);
                    Toast.makeText(getApplicationContext(),R.string.platoCreado,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(REQUEST_IMAGE_SAVE == requestCode && resultCode == RESULT_OK){
            File file = new File(pathFoto);
            try{
                FileInputStream fis = new FileInputStream(file);
                byte[] bytesFoto = new byte[(int) file.length()];
                fis.read(bytesFoto);
                fotoEnBase64 = Base64.encodeToString(bytesFoto, Base64.DEFAULT);
                Bitmap b = BitmapFactory.decodeByteArray(bytesFoto, 0, bytesFoto.length);
                fotoPlato.setImageBitmap(b);
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
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
