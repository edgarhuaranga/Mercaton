package com.edhuar.mercaton;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by root on 28/08/16.
 */
public class Cliente {
    public static final int INDEX_DISTRIBUIDOR01=0;
    public static final int INDEX_DISTRIBUIDOR02=1;
    public static final int INDEX_DISTRIBUIDOR03=2;
    public static final int INDEX_DISTRIBUIDOR04=3;
    public static final int INDEX_DISTRIBUIDOR05=4;
    public static final int INDEX_DISTRIBUIDOR06=5;

    public static final int INDEX_SETIEMBRE=1;
    public static final int INDEX_OCTUBRE=2;
    public static final int INDEX_NOVIEMBRE=3;
    public static final int INDEX_DICIEMBRE=4;
    public static final int INDEX_ENERO=5;

    public static final int INDEX_NOMBRE01=0;
    public static final int INDEX_NOMBRE02=1;
    public static final int INDEX_NOMBRE03=2;
    public static final int INDEX_NOMBRE04=3;
    public static final int INDEX_NOMBRE05=4;
    public static final int INDEX_NOMBRE06=5;
    public static final int INDEX_NOMBRETOTAL=6;

    String codigo;
    String supervisor;
    String gestor;
    String ciudad;
    String distrito;
    String nombre;
    String representante;
    String dni;
    String direccion;
    String mercado;
    String giro;
    String productosExhigidos;
    String comodinesTotales;
    String comodinesUsados;
    String comodinesRestantes;

    String distribuidora01;
    String distribuidora02;
    String distribuidora03;
    String distribuidora04;
    String distribuidora05;
    String distribuidora06;

    String nombreCompra01;
    String nombreCompra02;
    String nombreCompra03;
    String nombreCompra04;
    String nombreCompra05;
    String nombreCompra06;


    String[][][] infoCompras;

    String premioSetiembre;
    String premioOctubre;
    String premioNoviembre;
    String premioDiciembre;
    String premioEnero;

    public Cliente(String codigo, Context context){
        /*String db[] = context.getResources().getStringArray(R.array.dbclientes);
        for(int i =0; i<db.length; i+=27){
            if(codigo.equalsIgnoreCase(db[i])){
                this.codigo = codigo;
                this.supervisor=db[i+1];
                this.gestor=db[i+2];
                this.ciudad=db[i+3];
                this.distrito=db[i+4];
                this.nombre = db[i+5];
                this.representante = db[i+6];
                this.dni = db[i+7];
                this.direccion = db[i+8];
                this.mercado = db[i+9];
                this.giro = db[i+10];

                this.productosExhigidos = db[i+11];
                this.comodinesTotales = db[i+12];
                this.comodinesUsados = db[i+13];
                this.comodinesRestantes = db[i+14];

                this.distribuidora01 = db[i+15];
                this.distribuidora02 = db[i+16];
                this.distribuidora03 = db[i+17];
                this.distribuidora04 = db[i+18];
                this.distribuidora05 = db[i+19];
                this.distribuidora06 = db[i+20];

                this.nombreCompra01 = db[i+21];
                this.nombreCompra02 = db[i+22];
                this.nombreCompra03 = db[i+23];
                this.nombreCompra04 = db[i+24];
                this.nombreCompra05 = db[i+25];
                this.nombreCompra06 = db[i+26];

            }
        }*/
        DatabaseHandler db=new DatabaseHandler(context, null, null, 2);
        Cliente clientedb = db.getClienteFromDB(codigo);
        this.codigo = clientedb.codigo;
        this.supervisor= clientedb.supervisor;
        this.gestor=clientedb.gestor;
        this.ciudad=clientedb.ciudad;
        this.distrito=clientedb.distrito;
        this.nombre = clientedb.nombre;
        this.representante = clientedb.representante;
        this.dni = clientedb.dni;
        this.direccion = clientedb.direccion;
        this.mercado = clientedb.mercado;
        this.giro = clientedb.giro;

        this.productosExhigidos = clientedb.productosExhigidos;
        this.comodinesTotales = clientedb.comodinesTotales;
        this.comodinesUsados = clientedb.comodinesUsados;
        this.comodinesRestantes = clientedb.comodinesRestantes;

        this.distribuidora01 = clientedb.distribuidora01;
        this.distribuidora02 = clientedb.distribuidora02;
        this.distribuidora03 = clientedb.distribuidora03;
        this.distribuidora04 = clientedb.distribuidora04;
        this.distribuidora05 = clientedb.distribuidora05;
        this.distribuidora06 = clientedb.distribuidora06;

        this.nombreCompra01 = clientedb.nombreCompra01;
        this.nombreCompra02 = clientedb.nombreCompra02;
        this.nombreCompra03 = clientedb.nombreCompra03;
        this.nombreCompra04 = clientedb.nombreCompra04;
        this.nombreCompra05 = clientedb.nombreCompra05;
        this.nombreCompra06 = clientedb.nombreCompra06;
    }

    public Cliente(Cursor c){
        this.codigo = c.getString(0);
        this.supervisor=c.getString(1);
        this.gestor=c.getString(2);
        this.ciudad=c.getString(3);
        this.distrito=c.getString(4);
        this.nombre = c.getString(5);
        this.representante = c.getString(6);
        this.dni = c.getString(7);
        this.direccion = c.getString(8);
        this.mercado = c.getString(9);
        this.giro = c.getString(10);
        this.productosExhigidos = c.getString(11);
        this.comodinesTotales = c.getString(12);
        this.comodinesUsados = c.getString(13);
        this.comodinesRestantes = c.getString(14);
        this.distribuidora01 = c.getString(15);
        this.distribuidora02 = c.getString(16);
        this.distribuidora03 = c.getString(17);
        this.distribuidora04 = c.getString(18);
        this.distribuidora05 = c.getString(19);
        this.distribuidora06 = c.getString(20);

        this.nombreCompra01 = c.getString(21);
        this.nombreCompra02 = c.getString(22);
        this.nombreCompra03 = c.getString(23);
        this.nombreCompra04 = c.getString(24);
        this.nombreCompra05 = c.getString(25);
        this.nombreCompra06 = c.getString(26);
    }

    String getPath(){
        String s="";
        int dia, diames, semana, mes;
        int semanacampania = 0;
        mes = (Calendar.getInstance()).get(Calendar.MONTH);
        semana = (Calendar.getInstance()).get(Calendar.WEEK_OF_YEAR);
        dia = (Calendar.getInstance()).get(Calendar.DAY_OF_WEEK);
        diames = (Calendar.getInstance()).get(Calendar.DAY_OF_MONTH);

        semanacampania = Utils.numeroSemanaCampania();
        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
        String diaSemana="";
        switch (dia){
            case Calendar.MONDAY:
                diaSemana="Lunes";
                break;
            case Calendar.TUESDAY:
                diaSemana="Martes";
                break;
            case Calendar.WEDNESDAY:
                diaSemana="Miercoles";
                break;
            case Calendar.THURSDAY:
                diaSemana="Jueves";
                break;
            case Calendar.FRIDAY:
                diaSemana="Viernes";
                break;
            case Calendar.SATURDAY:
                diaSemana="Sabado";
                break;
            case Calendar.SUNDAY:
                diaSemana="Domingo";
                break;
            default:
                diaSemana="ErrorSemana";
                break;
        }
        //s += Environment.getExternalStorageDirectory() + "/MercaTon/"+meses[mes]+"/"+"Semana"+semanacampania+"/"+diaSemana+"/"+codigo+"/";
        s += "/storage/sdcard1/Mercaton/"+meses[mes]+"/"+"Semana"+semanacampania+"/"+diaSemana+"/"+codigo+"/";

        return s;
    }

    String getPathFoto(){
        String pathCliente = this.getPath();
        pathCliente += "Fotos/";
        return pathCliente;
    }

    String getPathCompetencia(){
        String pathCompetencia = this.getPath();
        pathCompetencia += "Competencia/";
        return pathCompetencia;
    }

    String getComentarioFoto(int index){
        try{
            File file = new File(getPathFoto()+"comentarioFoto"+index+".txt");
            Scanner scanner = new Scanner(file);
            String comentario="";
            while(scanner.hasNextLine()){
                comentario = scanner.nextLine();
            }
            return comentario;
        }catch (Exception e){
            return "No hay comentario";
        }
    }

    ArrayList<String> getPathPhotos(int numsemana){
        ArrayList<String> res = new ArrayList<>();
        //File mercaton = new File(Environment.getExternalStorageDirectory()+"/MercaTon/");
        File mercaton = new File("/storage/sdcard1/Mercaton/");
        Log.d("bug1", mercaton.getAbsolutePath());

        if(mercaton.exists()){
            File[] meses = mercaton.listFiles();
            for(int i=0; i<meses.length; i++){
                File mes = meses[i];
                if(mes.isDirectory()){
                    File[] semanas = mes.listFiles();
                    for(int j=0; j<semanas.length; j++){
                        File semana = semanas[j];
                        Log.d("semanapath",semana.getAbsolutePath());
                        Log.d("semanapath",semana.getName().substring(6));
                        int semnum = Integer.parseInt(semana.getName().substring(6));
                        if(semnum==numsemana){

                            if(semana.isDirectory()){
                                File[] dias = semana.listFiles();
                                for(int k=0; k<dias.length; k++){
                                    File dia = dias[k];
                                    if(dia.isDirectory()){
                                        File[] visitados = dia.listFiles();
                                        for(int m=0; m<visitados.length; m++){
                                            if(visitados[m].getName().equalsIgnoreCase(codigo)){
                                                //res.add(visitados[m].getAbsolutePath()+"/Fotos/");
                                                File fotoDir = new File(visitados[m].getPath()+"/Fotos/");
                                                if(fotoDir.exists()){
                                                    File[] fotosyComentarios = fotoDir.listFiles();
                                                    for(int ii=0; ii<fotosyComentarios.length;ii++){
                                                        if(fotosyComentarios[ii].isFile()){
                                                            if(fotosyComentarios[ii].getName().contains(".jpg")){
                                                                res.add(fotosyComentarios[ii].getAbsolutePath());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    String getUltimaEvaluacion(int numsemana){
        String res = "-";
        try {
            ArrayList<String> evaluacionesSemana = getPathEvaluacion(numsemana);
            if(evaluacionesSemana.size()>0){
                File visitatxt = new File(evaluacionesSemana.get(evaluacionesSemana.size()-1));
                Scanner scanner = new Scanner(visitatxt);
                while(scanner.hasNextLine()){
                    //Log.d("visitatxt", scanner.nextLine());
                    res = scanner.nextLine();
                }

                return res;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    int getNumProductosEncontrados(int numsemana){
        int numprodEncontrados=0;
        int productosRequeridosEnvases[] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int productosRequeridosMulti[] = new int[]{1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,1,0};
        int productosRequeridosSanitarios[] = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        String evaluacion = getUltimaEvaluacion(numsemana);
        Log.d("visitatxt1", evaluacion);
        String[] evaluacionPorProducto = evaluacion.split(",");
        for(int i=0; i<evaluacionPorProducto.length; i++){
            Log.d("visitatxt1",evaluacionPorProducto[i]+"/"+i);

            if(evaluacionPorProducto[i].charAt(0) =='1' && i<productosRequeridosEnvases.length){
                //Log.d("visitatxt1","Producto encontrado"+);
                if(this.giro.equalsIgnoreCase("Multicategoría")){
                    if(productosRequeridosMulti[i]==1) numprodEncontrados++;
                }
                if(this.giro.equalsIgnoreCase("Sanitarios")){
                    if(productosRequeridosSanitarios[i]==1) numprodEncontrados++;
                }
                if(this.giro.equalsIgnoreCase("Envases Descartables")){
                    if(productosRequeridosEnvases[i]==1) numprodEncontrados++;
                }
            }
        }
        return numprodEncontrados;
    }

    ArrayList<String> getProductosFaltantes(int numsemana){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> productos = Utils.productosCampania();

        int productosRequeridosEnvases[] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int productosRequeridosMulti[] = new int[]{1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,1,0};
        int productosRequeridosSanitarios[] = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        String evaluacion = getUltimaEvaluacion(numsemana);
        Log.d("visitatxt1", evaluacion);
        String[] evaluacionPorProducto = evaluacion.split(",");
        for(int i=0; i<evaluacionPorProducto.length; i++){
            Log.d("visitatxt1",evaluacionPorProducto[i]+"/"+i);

            if(evaluacionPorProducto[i].charAt(0) =='2' && i<productosRequeridosEnvases.length){
                //Log.d("visitatxt1","Producto encontrado"+);
                if(this.giro.equalsIgnoreCase("Multicategoría")){
                    if(productosRequeridosMulti[i]==1){
                        res.add(productos.get(i));
                    }
                }
                if(this.giro.equalsIgnoreCase("Sanitarios")){
                    if(productosRequeridosSanitarios[i]==1) {
                        res.add(productos.get(i));
                    }
                }
                if(this.giro.equalsIgnoreCase("Envases Descartables")){
                    if(productosRequeridosEnvases[i]==1){
                        res.add(productos.get(i));
                    }
                }
            }
        }
        return res;
    }

    int getNumProductosComodin(int numsemana){
        int numprodEncontrados=0;
        int productosRequeridosEnvases[] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int productosRequeridosMulti[] = new int[]{1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,1,0};
        int productosRequeridosSanitarios[] = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        String evaluacion = getUltimaEvaluacion(numsemana);
        Log.d("visitatxt1", evaluacion);
        String[] evaluacionPorProducto = evaluacion.split(",");
        for(int i=0; i<evaluacionPorProducto.length; i++){
            Log.d("visitatxt1",evaluacionPorProducto[i]+"/"+i);

            if(evaluacionPorProducto[i].charAt(0) =='3' && i<productosRequeridosEnvases.length){
                //Log.d("visitatxt1","Producto encontrado"+);
                if(this.giro.equalsIgnoreCase("Multicategoría")){
                    if(productosRequeridosMulti[i]==1) numprodEncontrados++;
                }
                if(this.giro.equalsIgnoreCase("Sanitarios")){
                    if(productosRequeridosSanitarios[i]==1) numprodEncontrados++;
                }
                if(this.giro.equalsIgnoreCase("Envases Descartables")){
                    if(productosRequeridosEnvases[i]==1) numprodEncontrados++;
                }
            }
        }
        return numprodEncontrados;
    }

    ArrayList<String> getPathEvaluacion(int numsemana) throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        //File mercaton = new File(Environment.getExternalStorageDirectory()+"/MercaTon/");
        File mercaton = new File("/storage/sdcard1/Mercaton/");
        Log.d("bug1", mercaton.getAbsolutePath());

        if(mercaton.exists()){
            File[] meses = mercaton.listFiles();
            for(int i=0; i<meses.length; i++){
                File mes = meses[i];
                if(mes.isDirectory()){
                    File[] semanas = mes.listFiles();
                    for(int j=0; j<semanas.length; j++){
                        File semana = semanas[j];
                        Log.d("semanapath",semana.getAbsolutePath());
                        Log.d("semanapath",semana.getName().substring(6));
                        int semnum = Integer.parseInt(semana.getName().substring(6));
                        if(semnum==numsemana){
                            if(semana.isDirectory()){
                                File[] dias = semana.listFiles();
                                for(int k=0; k<dias.length; k++){
                                    File dia = dias[k];
                                    if(dia.isDirectory()){
                                        File[] visitados = dia.listFiles();
                                        for(int m=0; m<visitados.length; m++){
                                            if(visitados[m].getName().equalsIgnoreCase(codigo)){
                                                File visitatxt = new File(visitados[m].getAbsolutePath()+"/visita.txt/");
                                                if(visitatxt.exists()){
                                                    Log.d("visitatxt", visitatxt.getAbsolutePath());
                                                    res.add(visitatxt.getAbsolutePath());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    String getPremio(int mes, Context context){
        String[] dbpremios = context.getResources().getStringArray(R.array.dbpremios);
        for(int i=0; i<dbpremios.length; i+=6){
            if(dbpremios[i].equalsIgnoreCase(codigo)) return dbpremios[i+mes];
        }
        return "-";
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "codigo='" + codigo + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", gestor='" + gestor + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", distrito='" + distrito + '\'' +
                ", nombre='" + nombre + '\'' +
                ", representante='" + representante + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", mercado='" + mercado + '\'' +
                ", giro='" + giro + '\'' +
                ", productosExhigidos='" + productosExhigidos + '\'' +
                ", comodinesTotales='" + comodinesTotales + '\'' +
                ", comodinesUsados='" + comodinesUsados + '\'' +
                ", comodinesRestantes='" + comodinesRestantes + '\'' +
                ", distribuidora01='" + distribuidora01 + '\'' +
                ", distribuidora02='" + distribuidora02 + '\'' +
                ", distribuidora03='" + distribuidora03 + '\'' +
                ", distribuidora04='" + distribuidora04 + '\'' +
                ", distribuidora05='" + distribuidora05 + '\'' +
                ", distribuidora06='" + distribuidora06 + '\'' +
                ", nombreCompra01='" + nombreCompra01 + '\'' +
                ", nombreCompra02='" + nombreCompra02 + '\'' +
                ", nombreCompra03='" + nombreCompra03 + '\'' +
                ", nombreCompra04='" + nombreCompra04 + '\'' +
                ", nombreCompra05='" + nombreCompra05 + '\'' +
                ", nombreCompra06='" + nombreCompra06 + '\'' +
                '}';
    }
}
