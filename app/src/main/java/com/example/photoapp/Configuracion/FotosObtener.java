package com.example.photoapp.Configuracion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FotosObtener {
    Context ctx = null;
    public FotosObtener(Context ctx) {
        this.ctx = ctx;
    }

    public List<photograh> getAll() {
        List<photograh> fotos = new ArrayList<>();
        try {
            SQLiteConexion conexion = new SQLiteConexion(ctx, Trans.DBname, null, Trans.Version);
            SQLiteDatabase db = conexion.getReadableDatabase();

            String[] columnas = {Trans.id, Trans.descripcion, Trans.foto};
            Cursor cursor = db.query(Trans.TablaFotografias, columnas, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(Trans.id));
                    String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(Trans.descripcion));
                    byte[] fotoBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(Trans.foto));

                    Bitmap fotoBitmap = null;
                    if (fotoBytes != null) {
                        fotoBitmap = BitmapFactory.decodeByteArray(fotoBytes, 0, fotoBytes.length);
                    }

                    // Aquí puedes usar los datos obtenidos
                    //Log.d("DB_DATA", "ID: " + id + ", Descripcion: " + descripcion);
                    if (fotoBitmap != null) {
                        // Mostrar la imagen, por ejemplo, en un ImageView
                    }

                    fotos.add(new photograh(id, descripcion, fotoBitmap));

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

        } catch (SQLiteException ex) {
            Log.e("DB_ERROR", String.valueOf(ex.getMessage()));

            //Toast.makeText(getApplicationContext(), "ERROR AL OBTENER REGISTROS", Toast.LENGTH_LONG).show();
        }

        return fotos;
    }
}
