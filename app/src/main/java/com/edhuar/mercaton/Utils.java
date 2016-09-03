package com.edhuar.mercaton;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by root on 28/08/16.
 */
public class Utils {

    private Context _context;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

    public static boolean fechaValida(Activity activity){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if(Settings.Global.getInt(activity.getContentResolver(), Settings.Global.AUTO_TIME) == 1) {
                    return true;
                }

                else {
                    Log.d("Reloj", "reloj modificado");
                    return false;
                }
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            //return false;//todo comentar luego
        }
        return false;
    }

    public static boolean camposValidos(String usuario, String password, String rol, Activity activity){

        if(usuario==null || usuario.length()==0){
            Toast.makeText(activity.getApplicationContext(), "Campo usuario vacio", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password==null || password.length()==0){
            Toast.makeText(activity.getApplicationContext(), "Campo password vacio", Toast.LENGTH_SHORT).show();
            return false;
        }

        String loginDB[] = activity.getResources().getStringArray(R.array.loginArray);
        for(int i=0;i<loginDB.length; i+=4){
            if(loginDB[i].equalsIgnoreCase(usuario)){

                if(loginDB[i+1].equalsIgnoreCase(password)){
                    if(loginDB[i+2].equalsIgnoreCase(rol)){
                        Toast.makeText(activity.getApplicationContext(), "Acceso autorizado", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else{
                        Toast.makeText(activity.getApplicationContext(), "El rol no es el correcto", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                else{
                    Toast.makeText(activity.getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        Toast.makeText(activity.getApplicationContext(),"Usuario incorrecto",Toast.LENGTH_SHORT).show();
        return false;
    }

    public static ArrayList<String> semanasCampania(){
        ArrayList<String> descripcionSemana = new ArrayList<>();
        descripcionSemana.add("No campaña");
        descripcionSemana.add("Del 01 al 10 de setiembre");
        descripcionSemana.add("Del 12 al 17 de setiembre");
        descripcionSemana.add("Del 19 al 24 de setiembre");
        descripcionSemana.add("Del 26 de setiembre al 01 de octubre");
        descripcionSemana.add("Del 03 al 07 de octubre");
        descripcionSemana.add("Del 10 al 15 de octubre");
        descripcionSemana.add("Del 17 al 22 de octubre");
        descripcionSemana.add("Del 24 al 29 de octubre");
        descripcionSemana.add("Del 31 de octubre al 05 de noviembre");
        descripcionSemana.add("Del 07 al 12 de noviembre");
        descripcionSemana.add("Del 14 al 19 de noviembre");
        descripcionSemana.add("Del 21 al 26 de noviembre");
        descripcionSemana.add("Del 28 de noviemb al 03 de diciembre");
        descripcionSemana.add("Del 05 al 10 de diciembre");
        descripcionSemana.add("Del 12 al 17 de diciembre");
        descripcionSemana.add("Del 19 al 24 de diciembre");
        descripcionSemana.add("Del 26 al 31 de diciembre");
        descripcionSemana.add("Del 02 al 07 de enero");
        descripcionSemana.add("Del 09 al 14 de enero");
        descripcionSemana.add("Del 16 al 21 de enero");
        descripcionSemana.add("Del 23 al 28 de enero");
        return descripcionSemana;
    }

    public static ArrayList<String> productosCampania(){

        ArrayList<String> productos = new ArrayList<>();
        productos.add("Ladysoft Normal con alas (verde) ");//0
        productos.add("Ladysoft Ultra Delgada Malla");//1
        productos.add("Ladysoft Ultra Delgada Suave");//2
        productos.add("Ladysoft Delgada (rosada)");//3
        productos.add("Ladysoft Natural (amarilla)");//4
        productos.add("Ladysoft Nocturna (negra)");//5
        productos.add("Ladysoft Nocturna Doble Ala ");//6
        productos.add("Babysec Recién Nacido (blanco) ");//7
        productos.add("Babysec Premium (morado) ");//8
        productos.add("Babysec Ultra (celeste)");//9
        productos.add("Babysec Cuidado Total (blanco)");//10
        productos.add("Toalla Húmeda Premium (Babysec)");//11
        productos.add("Toalla Húmeda Ultra  (Babysec)");//12
        productos.add("Cotidian Plus");//13
        productos.add("Cotidian Premium");//14
        productos.add("Cotidian Clásico ");//15
        productos.add("Cotidian Multiuso ");//16
        productos.add("Cotidian Pants");//17
        productos.add("Ladysoft Normal pack x 42");//18
        productos.add("Servilletas Ultra Doblada en 4  (Elite)");//19
        productos.add("Servilletas Cortadas (verde)  (Elite)");//20
        productos.add("Servilletas Práctica en 2 (roja)  (Elite)");//21
        productos.add("Servilletas Doblada en 4 diseño (Elite)");//22
        productos.add("Servilletas Doblada en 4 (celeste)  (Elite)");//23
        productos.add("Dispensador x 27 unidades aloe vera  / compactos (Elite)");//24
        productos.add("Dispensador x 27 unidades mentholado / compactos (Elite)");//25
        productos.add("Papel Toalla Ultra (Elite)");//26
        productos.add("Papel Toalla Ultra Megarollo (Elite)");//27
        productos.add("Papel Toalla Clásico Rojo (Elite)");//28
        productos.add("Papel Toalla Megarollo Clásico (Elite)");//29
        productos.add("Papel Toalla Megarrollo Celeste (Elite)");//30
        productos.add("Pañuelos Faciales (Elite)");//31
        productos.add("Papel Higiénico Ultra Doble Hoja (Morado) (Elite)");//32
        productos.add("Papel Higiénico Doble Hoja (Celeste) (Elite)");//33
        productos.add("Papel Higiénico Dúo Doble Hoja (Elite)");//34
        productos.add("Papel Higiénico Doble hoja Económico (Naranja) (Elite)");//35
        productos.add("Papel Higiénico Plus Doble Hoja (fucsia) (Elite)");//36
        productos.add("Papel Higiénico Noble Institucional");//37
        productos.add("PM 001");
        productos.add("PM 002");

        return productos;
    }

    public static ArrayList<String> productosCompetencia(){
        ArrayList<String> productosCompetencia = new ArrayList<>();
        productosCompetencia.add("Kleenex Supreme");
        productosCompetencia.add("Suave Supreme Care");
        productosCompetencia.add("Suave Jumbo");
        productosCompetencia.add("Suave Rindemax");
        productosCompetencia.add("Suave Extra Doble Hoja");
        productosCompetencia.add("Scott Duramax");
        productosCompetencia.add("Scott Calorie Absorb Alto");
        productosCompetencia.add("Scott Calorie Absorb 12.4");
        productosCompetencia.add("Scott Calorie Absorb 24.8mts");
        productosCompetencia.add("Scott Multiusos 105/20 mts");
        productosCompetencia.add("Scott Multiusos 50/10mts");
        productosCompetencia.add("Servilletas Scott Estilo 50 Hojas");
        productosCompetencia.add("Servilletas Scott Dobladas 100 hojas");
        productosCompetencia.add("Servilletas Scott Dobladas con diseño 80 hojas");
        productosCompetencia.add("Servilletas Scott Premium Cortadas 220 y 1000 hojas");
        productosCompetencia.add("Pañuelos Kleenex Dermoseda");
        productosCompetencia.add("Pañuelos Kleenex MainLine");
        productosCompetencia.add("Kotex Evolution Dual Nocturna");
        productosCompetencia.add("Kotex Teens");
        productosCompetencia.add("Kotex Ultrafina");
        productosCompetencia.add("Kotex Normal");
        productosCompetencia.add("Celex");
        productosCompetencia.add("Huggies Natural Care");
        productosCompetencia.add("Huggies UpGo");
        productosCompetencia.add("Huggies Recien Nacido");
        productosCompetencia.add("Huggies Active Sec");
        productosCompetencia.add("Papel Higiénico Paracas UHx2");
        productosCompetencia.add("Papel Higiénico Paracas UHx1");
        productosCompetencia.add("Papel Toalla Paracas Rollito");
        productosCompetencia.add("Papel Toalla Paracas Rollazo");

        return productosCompetencia;
    }

    public static int numeroSemanaCampania(){
        int mes, diames;
        int semanacampania=0;
        mes = (Calendar.getInstance()).get(Calendar.MONTH);
        diames = (Calendar.getInstance()).get(Calendar.DAY_OF_MONTH);


        switch (mes){
            case Calendar.JANUARY:
                if(diames>=2 && diames<9) semanacampania = 18;
                else if(diames>=9 && diames<16) semanacampania = 19;
                else if(diames>=16 && diames<23) semanacampania = 20;
                else if(diames>=23 && diames<30) semanacampania = 21;
                break;
            case Calendar.FEBRUARY:
                //campain is over
                semanacampania = 0;
                break;
            case Calendar.MARCH:
                semanacampania = 0;
                //no campain
                break;
            case Calendar.APRIL:
                //no campain
                semanacampania = 0;
                break;
            case Calendar.MAY:
                //no campain
                semanacampania = 0;
                break;
            case Calendar.JUNE:
                //no campain
                semanacampania = 0;
                break;
            case Calendar.JULY:
                //no campain
                semanacampania = 0;
                break;
            case Calendar.AUGUST:
                semanacampania = 0;
                break;
            case Calendar.SEPTEMBER:
                if(diames>=1 && diames<12) semanacampania = 1;
                else if(diames>=12 && diames<19) semanacampania = 2;
                else if(diames>=19 && diames<26) semanacampania = 3;
                else if(diames>=26) semanacampania=4;
                break;
            case Calendar.OCTOBER:
                if(diames<3) semanacampania = 4;
                else if(diames>=3 && diames<10) semanacampania = 5;
                else if(diames>=10 && diames<17) semanacampania = 6;
                else if(diames>=17 && diames<24) semanacampania = 7;
                else if(diames>=24 && diames<31) semanacampania = 8;
                else if(diames==31) semanacampania =9;
                break;
            case Calendar.NOVEMBER:
                if(diames<7) semanacampania = 9;
                else if(diames>=7 && diames<14) semanacampania = 10;
                else if(diames>=14 && diames<21) semanacampania = 11;
                else if(diames>=21 && diames<28) semanacampania = 12;
                else if(diames>=28) semanacampania = 13;
                break;
            case Calendar.DECEMBER:
                if(diames<5) semanacampania=13;
                else if(diames>=5 && diames<12) semanacampania=14;
                else if(diames>=12 && diames<19) semanacampania = 15;
                else if(diames>=19 && diames<26) semanacampania = 16;
                else if(diames>=26 ) semanacampania = 17;
                break;
        }
        return semanacampania;
    }


    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }


}
