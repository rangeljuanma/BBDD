package com.example.bbdd_2.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.bbdd_2.BBDD.BaseDatos;
import com.example.bbdd_2.models.Empleado;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class BDAdaptador {
    private Context contexto;
    private BaseDatos baseDatos;
    private SQLiteDatabase bd;
    private final String T_EMPLEADOS = "empleados";
    private final String T_DEPARTAMENTOS = "departamentos";
;
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

        String query = "Select nombre from "+this.T_EMPLEADOS+" where _id=?";
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

    public void showEmpleados(){
        String query = "Select * from empleados;" ;
        Cursor cursor = null;
        try{
            cursor = baseDatos.getReadableDatabase().rawQuery(query,null);
            while(cursor.moveToNext()){
                String nombre= cursor.getString(1);
                int depa= cursor.getInt(2);
                Toast.makeText(contexto,nombre +" "+ depa, Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){

        }
        finally {
            cursor.close();;
        }
    }


    public boolean isIn (int id){
        String query = "Select _id from empleados;" ;
        int _id =0;
        Cursor cursor=null;
        try {
            cursor = baseDatos.getReadableDatabase().rawQuery(query, null);
            while(cursor.moveToNext()) {
                _id = cursor.getInt(0);
                Log.i("lista",""+_id);
                if(_id==id){
                    return true;
                }
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
        return false;
    }

    public void insertarLista(List<String>nombres) {

        //Abrimos la BD en modo lectura/escritura
        bd = baseDatos.getWritableDatabase();
        //Preparamos la información a insertar
        ContentValues contentValues = new ContentValues();
        nombres.stream()
                        .forEach((nombre)->{
                            contentValues.put("nombre", nombre);
                            //Insertarmos los datos. Recogemos el resultado
                            long resultado = bd.insert("almacen", null, contentValues);
                        });
        //Cerramos la BD
        bd.close();
    }

    public Empleado findAllById(int id) {
        Empleado empleado = null;
        String query = "Select e.nombre, d.nombre, e.email, e.telefono, e.salario from "+this.T_EMPLEADOS+" e, "+this.T_DEPARTAMENTOS+" d where e._id=?";
        String[] parameter = {String.valueOf(id)};
        String nombre, departamento, email, telefono;
        Float salario;
        Cursor cursor=null;
        try {
            cursor = baseDatos.getReadableDatabase().rawQuery(query, parameter);
            nombre = "";
            departamento = "";
            if (cursor.moveToNext()) {
                nombre = cursor.getString(0);
                departamento = cursor.getString(1);
                email = cursor.getString(2);
                telefono = cursor.getString(3);
                salario = cursor.getFloat(4);
                empleado = new Empleado(id,nombre, departamento,telefono, email, salario);
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
        return empleado;
    }

    public void modifyPhoneEmployee(int id, String phone) {
        String update = "UPDATE "+this.T_EMPLEADOS
                +" SET telefono =?"
                +" WHERE _id = ?";
        String[] parameters = {phone,String.valueOf(id)};

        baseDatos.getWritableDatabase().execSQL(update,parameters);
    }

    public void modifyEmail(int id, String email) {
        String update = "UPDATE "+this.T_EMPLEADOS
                +" SET email =?"
                +" WHERE _id = ?";
        String[] parameters = {email,String.valueOf(id)};

        baseDatos.getWritableDatabase().execSQL(update,parameters);
    }

    public List<Empleado> findAllByDepartamento(int idDepart) {
        List<Empleado> empleados = new ArrayList<>();
        bd = baseDatos.getWritableDatabase();

        String query = "SELECT e._id, e.nombre, d.nombre, e.email, e.telefono, e.salario from "+this.T_EMPLEADOS+" e, "+this.T_DEPARTAMENTOS+" d where d._id=? AND d._id == e.id_departamento";
        String[] parameter = {String.valueOf(idDepart)};
        Cursor cursor;
        cursor = bd.rawQuery(query,parameter);

        while(cursor.moveToNext()) {
            empleados.add(new Empleado(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getFloat(5)));
        }


        bd.close();


        return empleados;

    } public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();
        bd = baseDatos.getWritableDatabase();
        String query = "SELECT e._id, e.nombre, d.nombre, e.email, e.telefono, e.salario from "+this.T_EMPLEADOS+" e, "+this.T_DEPARTAMENTOS+" d where e.id_departamento= d._id";
        Cursor cursor;
        cursor = bd.rawQuery(query,null);

        while(cursor.moveToNext()) {
            empleados.add(new Empleado(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getFloat(5)));
            }
        bd.close();


        return empleados;

    }

    public void increase10() {
        bd = baseDatos.getWritableDatabase();
        String update = "UPDATE "+this.T_EMPLEADOS
                +" SET salario = (salario+salario*0.1)";
        bd.execSQL(update);

        bd.close();;

    }

    public void eliminarEmpleado(String id) {

        bd=baseDatos.getWritableDatabase();
        String query = "DELETE FROM "+this.T_EMPLEADOS
                +" WHERE _id = ?;";
        String[] parameter = {id};
        bd.execSQL(query,parameter);


        bd.close();

    }
}