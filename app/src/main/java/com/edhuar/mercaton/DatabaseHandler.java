package com.edhuar.mercaton;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 28/08/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static Context mCtx = null;
    public DatabaseHandler(Context context, Object name,
                           Object factory, int version) {
        // TODO Auto-generated constructor stub
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
        mCtx=context;
    }

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Mydatabase.db";

    //tabla encuesta
    private static final String TABLE_Clientes= "Clientes";
    public static final String  KEY_CodigoCliente="_id";
    public static final String  KEY_Sup="supervisor";
    public static final String  KEY_Gest="gestor";
    public static final String  KEY_Ciu="ciudad";
    public static final String  KEY_Dis="distrito";
    public static final String  KEY_Nom="nombre";
    public static final String  KEY_Rep="representante";
    public static final String  KEY_Dni="dni";
    public static final String  KEY_Dir="direccion";
    public static final String  KEY_Mer="mercado";
    public static final String  KEY_Giro="giro";
    public static final String  KEY_ProE="productosExhigidos";
    public static final String  KEY_ComT="comodinesTotales";
    public static final String  KEY_ComU="comodinesUsados";
    public static final String  KEY_ComR="comodinesRestantes";
    public static final String  KEY_Distr1="distribuidora01";
    public static final String  KEY_Distr2="distribuidora02";
    public static final String  KEY_Distr3="distribuidora03";
    public static final String  KEY_Distr4="distribuidora04";
    public static final String  KEY_Distr5="distribuidora05";
    public static final String  KEY_Distr6="distribuidora06";
    public static final String  KEY_NC1="nombreCompra01";
    public static final String  KEY_NC2="nombreCompra02";
    public static final String  KEY_NC3="nombreCompra03";
    public static final String  KEY_NC4="nombreCompra04";
    public static final String  KEY_NC5="nombreCompra05";
    public static final String  KEY_NC6="nombreCompra06";

    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_Clientes + "("
            + KEY_CodigoCliente + " TEXT PRIMARY KEY," + KEY_Sup + " TEXT,"+KEY_Gest + " TEXT,"+KEY_Ciu+ " TEXT,"
            + KEY_Dis + " TEXT," + KEY_Nom + " TEXT, " +KEY_Rep+" TEXT, "+KEY_Dni+ " TEXT, "+ KEY_Dir+" TEXT, "+KEY_Mer
            +" TEXT, "+KEY_Giro+" TEXT, "+KEY_ProE+" TEXT, " +KEY_ComT+" TEXT, " +KEY_ComU+" TEXT, " +KEY_ComR+" TEXT, " +KEY_Distr1+
            " TEXT, " +KEY_Distr2+" TEXT, " +KEY_Distr3+" TEXT, " +KEY_Distr4+" TEXT, "  +KEY_Distr5+" TEXT, " +KEY_Distr6
            +" TEXT, " +KEY_NC1+" TEXT, " +KEY_NC2+" TEXT, " +KEY_NC3+" TEXT, " +KEY_NC4+" TEXT, " +KEY_NC5+" TEXT, " +KEY_NC6+" TEXT "+")";





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        //llenar DDBB
        // File path = Environment.getExternalStorageDirectory().getAbsoluteFile();
        AssetManager mngr = mCtx.getAssets();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mngr.open("clientes.txt"), "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "#");
                //if (strings.length < 5) continue;
                //long id = addSong(strings[0].trim(), strings[1].trim(), strings[2].trim(), strings[3].trim(), strings[4].trim());
                ContentValues initialValues = new ContentValues();
                initialValues.put(KEY_CodigoCliente, strings[0].trim());
                initialValues.put(KEY_Sup, strings[1].trim());
                initialValues.put(KEY_Gest, strings[2].trim());
                initialValues.put(KEY_Ciu, strings[3].trim());
                initialValues.put(KEY_Dis, strings[4].trim());
                initialValues.put(KEY_Nom, strings[5].trim());
                initialValues.put(KEY_Rep, strings[6].trim());
                initialValues.put(KEY_Dni, strings[7].trim());
                initialValues.put(KEY_Dir, strings[8].trim());
                initialValues.put(KEY_Mer, strings[9].trim());
                initialValues.put(KEY_Giro,strings[10].trim());
                initialValues.put(KEY_ProE, strings[11].trim());
                initialValues.put(KEY_ComT, strings[12].trim());
                initialValues.put(KEY_ComU, strings[13].trim());
                initialValues.put(KEY_ComR, strings[14].trim());
                initialValues.put(KEY_Distr1, strings[15].trim());
                initialValues.put(KEY_Distr2, strings[16].trim());
                initialValues.put(KEY_Distr3, strings[17].trim());
                initialValues.put(KEY_Distr4, strings[18].trim());
                initialValues.put(KEY_Distr5, strings[19].trim());
                initialValues.put(KEY_Distr6, strings[20].trim());
                initialValues.put(KEY_NC1, strings[21].trim());
                initialValues.put(KEY_NC2, strings[22].trim());
                initialValues.put(KEY_NC3, strings[23].trim());
                initialValues.put(KEY_NC4, strings[24].trim());
                initialValues.put(KEY_NC5, strings[25].trim());
                initialValues.put(KEY_NC6, strings[26].trim());

                db.insert(TABLE_Clientes, null, initialValues);

                //createCustomer(strings[0].trim(),strings[2].trim(), strings[1].trim(), strings[3].trim(), strings[4].trim(), strings[5].trim(),strings[6].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Clientes);
        onCreate(db);
    }

    public Cursor consultaCliente(String user, String ind){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.query(TABLE_Clientes,new String[] {KEY_CodigoCliente , KEY_Sup ,KEY_Gest ,KEY_Ciu,KEY_Dis ,KEY_Nom , KEY_Rep,KEY_Dni, KEY_Dir,
                KEY_Mer,KEY_Giro, KEY_ProE, KEY_ComT,KEY_ComU, KEY_ComR, KEY_Distr1, KEY_Distr2, KEY_Distr3, KEY_Distr4, KEY_Distr5,KEY_Distr6,
                KEY_NC1,KEY_NC2, KEY_NC3,KEY_NC4,KEY_NC5,KEY_NC6}, null, null, null, null, null, null );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;


    }

    public List<Cliente> getDataFromDB(){
        List<Cliente> modelList = new ArrayList<Cliente>();
        String query = "select * from "+TABLE_Clientes;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Cliente model = new Cliente(cursor);

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }

    public List<Cliente> getDataFromDB(String rol, String nombreusuario){
        List<Cliente> modelList = new ArrayList<Cliente>();
        String query = "select * from "+TABLE_Clientes;
        if(rol.equalsIgnoreCase("agente")){
            query = "select * from "+TABLE_Clientes+" where "+KEY_Gest+" = '" +nombreusuario+"'";
        }

        if(rol.equalsIgnoreCase("supervisor")){
            query = "select * from "+TABLE_Clientes+" where "+KEY_Sup+" = '" +nombreusuario+"'";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Cliente model = new Cliente(cursor);

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }

    public Cliente getClienteFromDB(String codigo){
        List<Cliente> cliente = new ArrayList<>();
        String query = "select * from "+TABLE_Clientes+" where "+ KEY_CodigoCliente+"= '"+codigo+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Cliente model = new Cliente(cursor);
                cliente.add(model);
            }while (cursor.moveToNext());
        }
        Log.d("student data", cliente.toString());
        return cliente.get(0);
    }


}
