package com.example.photoapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.photoapp.Configuracion.SQLiteConexion;
import com.example.photoapp.Configuracion.Trans;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText descripcion;
    Button btnGuardar;

    static final int peticion_acceso_camara = 101;
    static final int peticion_captura_imagen = 102;

    private Bitmap fotoBitmap;
    ImageView ObjectoImagen;
    Button btncaptura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        descripcion = (EditText) findViewById(R.id.txtdescripcion);
        btnGuardar = (Button) findViewById(R.id.btnsave);
        ObjectoImagen = (ImageView) findViewById(R.id.photo);
        btncaptura = (Button) findViewById(R.id.btnfoto);

        btncaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Permisos();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
    }

    private void Guardar(){
        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Trans.DBname, null, Trans.Version);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Trans.descripcion, descripcion.getText().toString());

            if (fotoBitmap != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                fotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] fotoBytes = baos.toByteArray();
                valores.put(Trans.foto, fotoBytes);
            }

            Long resultado = db.insert(Trans.TablaFotografias , Trans.id, valores);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Registro Exitoso");
            builder.setMessage("REGISTRO INGRESADO CON EXITO ");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            db.close();

        }
        catch (Exception ex) {
            Log.e("DB_ERROR", ex.toString());
            Toast.makeText(getApplicationContext(), "ERROR AL INGRESAR REGISTRO", Toast.LENGTH_LONG).show();
        }
    }

    private void Permisos()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String [] {Manifest.permission.CAMERA},
                    peticion_acceso_camara);
        }
        else
        {
            TomarFoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == peticion_acceso_camara)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                TomarFoto();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Acceso Denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void TomarFoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(intent,  peticion_captura_imagen);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  peticion_captura_imagen && resultCode == RESULT_OK)
        {
            if(data != null){
                Bundle extras = data.getExtras();
                if(extras != null){
                    Bitmap imagen = (Bitmap) extras.get("data");
                    ObjectoImagen.setImageBitmap(imagen);
                    fotoBitmap = imagen;

                }
            }
        }
    }
}