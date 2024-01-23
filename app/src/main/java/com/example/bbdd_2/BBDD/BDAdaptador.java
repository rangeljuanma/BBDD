package com.example.bbdd_2.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.bbdd_2.BBDD.BaseDatos;

public class BDAdaptador {
    private Context contexto;
    private BaseDatos baseDatos;
    private SQLiteDatabase bd;

    public BDAdaptador(Context c) {
        //Almacenamos el contexto
        contexto = c;
        //Creamos una instancia a la Base de Datos
        baseDatos = BaseDatos.getInstance(contexto);
    }
    //Método para insertar datos en la BD. Recibe los parámetros a

    public long insertar(String nombre) {
        //Abrimos la BD en modo lectura/escritura
        bd = baseDatos.getWritableDatabase();
        //Preparamos la información a insertar
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        //Insertarmos los datos. Recogemos el resultado
        long resultado = bd.insert("almacen", null, contentValues);
        //Cerramos la BD
        bd.close();
        //Devolvemos el resultado de la inserción
        return resultado;
    }

    public String findByid(String id){

        String query = "Select nombre from alumnos where _id=?";
        String[] parameter = {id};
        String nombre = "";
        Cursor cursor=null;
        try {
            cursor = baseDatos.getReadableDatabase().rawQuery(query, parameter);
            nombre = "";
            if (cursor.moveToNext()) {
                nombre = cursor.getString(0);
            }
        }
        catch (Exception e){
            Toast.makeText(contexto, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            if (cursor != null){
                cursor.close();
            }
        }
        return nombre;
    }

}