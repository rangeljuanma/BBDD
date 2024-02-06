package com.example.bbdd_2.BBDD;

//Imports necesarios
        import android.content.Context;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.Toast;

//Crear una clase que extienda de SQLiteOpenHelper
public class BaseDatos extends SQLiteOpenHelper {
    Context contexto;
    private static BaseDatos baseDeDatos;
    private final String T_EMPLEADOS ="empleados";
    private final String T_DEPARTAMENTO ="departamentos";
    private final static int version =15
            ;



    //Constructor
    private BaseDatos(Context context) {
        //Pasamos al constructor de la superclase el contexto, el nombre de la BD y la Versión
        super(context,"BD", null, version);
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


            db.execSQL("CREATE TABLE " + this.T_DEPARTAMENTO +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT)");

            db.execSQL("CREATE TABLE "+ this.T_EMPLEADOS+
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT," +
                    "id_departamento INTEGER," +
                    "telefono TEXT," +
                    "email TEXT," +
                    "salario DECIMAL(7,2)," +
                    "FOREIGN KEY('id_departamento') REFERENCES "+this.T_DEPARTAMENTO+"(_id));");

            db.execSQL("INSERT INTO "+ this.T_DEPARTAMENTO +"(nombre) VALUES('matematicas');");
            db.execSQL("INSERT INTO "+ this.T_DEPARTAMENTO +"(nombre) VALUES('lengua');");
            db.execSQL("INSERT INTO "+ this.T_DEPARTAMENTO +"(nombre) VALUES('ingles');");
            db.execSQL("INSERT INTO "+ this.T_EMPLEADOS +"(nombre, id_departamento,telefono,email,salario) " +
                    "VALUES('Santiago Miguez Cea',1,'659120492', 'santiagomiguezcea@gmail.com', 1345.53)," +
                          "('david beltran',2, '612934016', 'davidbeltranesperilla@gmail.com', 1925.45)," +
                        "('Manu Curado',3,'659120492', 'manu@hotmail.com', 1345.53)," +
                        "('Felix putero',1,'659120492', 'felix@gmail.es', 1345.53)," +
                    "('elena',2,'659120492', 'elena@gmail.com', 1345.53)," +
                    "('inma',3,'659120492', 'inma@gmail.com', 1345.53);");


        }

        catch (SQLException e){
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto,""+e,Toast.LENGTH_LONG).show();
            Log.e("error",e.getMessage());
        }
    }



    //onUpgrade se ejecuta cuando se ACTUALIZA la versíon de la BD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //Eliminamos la tabla anterior (si existe)
            db.execSQL("DROP TABLE IF EXISTS " + this.T_EMPLEADOS);
            db.execSQL("DROP TABLE IF EXISTS " + this.T_DEPARTAMENTO);
            //Llamamos a onCreate para que cree la tabla con las nuevas especificaciones
            onCreate(db);
        }
        catch (SQLException e){
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto,""+e,Toast.LENGTH_LONG).show();
        }

    }
}

