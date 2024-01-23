package com.example.bbdd_2.BBDD;

//Imports necesarios
        import android.content.Context;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.widget.Toast;

//Crear una clase que extienda de SQLiteOpenHelper
public class BaseDatos extends SQLiteOpenHelper {
    Context contexto;
    private static BaseDatos baseDeDatos;

    //Constructor
    private BaseDatos(Context context) {
        //Pasamos al constructor de la superclase el contexto, el nombre de la BD y la Versión
        super(context,"BD", null, 1);
        //Almacenamos el contexto
        contexto=context;
    }

    public static BaseDatos getInstance(Context context){
        if(baseDeDatos==null){
            return baseDeDatos=new BaseDatos(context);
        }
        return baseDeDatos;
    }


    @Override
    //onCreate se ejecuta cuando se CREA la BD
    public void onCreate(SQLiteDatabase db) {
        try {
            //Creamos la tabla
            db.execSQL("CREATE TABLE almacen " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre VARCHAR);");
        }
        catch (SQLException e){
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto,""+e,Toast.LENGTH_SHORT).show();
        }
    }



    //onUpgrade se ejecuta cuando se ACTUALIZA la versíon de la BD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //Eliminamos la tabla anterior (si existe)
            db.execSQL("DROP TABLE IF EXISTS alumnos");
            //Llamamos a onCreate para que cree la tabla con las nuevas especificaciones
            onCreate(db);
        }
        catch (SQLException e){
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto,""+e,Toast.LENGTH_SHORT).show();
        }

    }
}

