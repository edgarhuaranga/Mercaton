package com.edhuar.mercaton;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExhibicionMesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExhibicionMesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "mes";
    private static final String ARG_PARAM2 = "cliente";


    TextView numeroSem[] = new TextView[22];
    TextView descripcionSem[] = new TextView[22];
    TextView productosExhigidosSem[] = new TextView[22];
    TextView productosEncontradosSem[] = new TextView[22];
    TextView productosEvaluadosSem[] = new TextView[22];
    TextView productosFaltantes[] = new TextView[22];
    TextView comodinesUtilizados[] = new TextView[22];
    Cliente cliente;
    TextView textViewComodinesUtilizados;
    TextView textViewLogroMes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ExhibicionMesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExhibicionMesFragment newInstance(String cliente, String mes) {
        ExhibicionMesFragment fragment = new ExhibicionMesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mes);
        args.putString(ARG_PARAM2, cliente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exhibicion_mes, container, false);

        //LinearLayout tablaAgosto = (LinearLayout) view.findViewById(R.id.tabla_agosto);
        LinearLayout tablaSetiembre = (LinearLayout) view.findViewById(R.id.tabla_setiembre);
        LinearLayout tablaOctubre = (LinearLayout) view.findViewById(R.id.tabla_octubre);
        LinearLayout tablaNoviembre = (LinearLayout) view.findViewById(R.id.tabla_noviembre);
        LinearLayout tablaDiciembre = (LinearLayout) view.findViewById(R.id.tabla_diciembre);
        LinearLayout tablaEnero = (LinearLayout) view.findViewById(R.id.tabla_enero);

        textViewComodinesUtilizados = (TextView) view.findViewById(R.id.textview_comodines_utilizados_exhibicion_mes);
        textViewLogroMes = (TextView) view.findViewById(R.id.textview_logro_mes);



        cliente = new Cliente(mParam2, getActivity().getApplicationContext());
        textViewComodinesUtilizados.setText(cliente.comodinesUsados);

        setupViewsNumeroSemanas(view);
        setupViewsDescripcionSemanas(view);
        setupViewsProductosExhigidosSem(view);
        setupViewsProductosEncontradosSem(view);
        setupViewsProductosEvaluadosSem(view);
        setupViewsProductosFaltantes(view);
        setupViewsComodinesUtilizados(view);

        fillProductosEncontrados();
        fillProductosEvaluados();
        fillProductosComodin();

        if(mParam1.equalsIgnoreCase("setiembre")){
            tablaSetiembre.setVisibility(View.VISIBLE);
            int logro = 0;
            for(int i=1; i<=4; i++){
                int prodExhigidos = Integer.parseInt(cliente.productosExhigidos);
                int prodEvaluados = Integer.parseInt(productosEvaluadosSem[i].getText().toString());
                if(prodEvaluados>=prodExhigidos) logro++;
            }
            textViewLogroMes.setText(logro+" semana(s)");
        }
        if(mParam1.equalsIgnoreCase("octubre")){
            tablaOctubre.setVisibility(View.VISIBLE);
            int logro = 0;
            for(int i=5; i<=8; i++){
                int prodExhigidos = Integer.parseInt(cliente.productosExhigidos);
                int prodEvaluados = Integer.parseInt(productosEvaluadosSem[i].getText().toString());
                if(prodEvaluados>=prodExhigidos) logro++;
            }
            textViewLogroMes.setText(logro+" semana(s)");
        }
        if(mParam1.equalsIgnoreCase("noviembre")){
            tablaNoviembre.setVisibility(View.VISIBLE);
            int logro = 0;
            for(int i=9; i<=12; i++){
                int prodExhigidos = Integer.parseInt(cliente.productosExhigidos);
                int prodEvaluados = Integer.parseInt(productosEvaluadosSem[i].getText().toString());
                if(prodEvaluados>=prodExhigidos) logro++;
            }
            textViewLogroMes.setText(logro+" semana(s)");
        }
        if(mParam1.equalsIgnoreCase("diciembre")){
            tablaDiciembre.setVisibility(View.VISIBLE);
            int logro = 0;
            for(int i=13; i<=17; i++){
                int prodExhigidos = Integer.parseInt(cliente.productosExhigidos);
                int prodEvaluados = Integer.parseInt(productosEvaluadosSem[i].getText().toString());
                if(prodEvaluados>=prodExhigidos) logro++;
            }
            textViewLogroMes.setText(logro+" semana(s)");
        }
        if(mParam1.equalsIgnoreCase("enero")){
            tablaEnero.setVisibility(View.VISIBLE);
            int logro = 0;
            for(int i=18; i<=21; i++){
                int prodExhigidos = Integer.parseInt(cliente.productosExhigidos);
                int prodEvaluados = Integer.parseInt(productosEvaluadosSem[i].getText().toString());
                if(prodEvaluados>=prodExhigidos) logro++;
            }
            textViewLogroMes.setText(logro+" semana(s)");
        }

        return view;
    }

    private void setupViewsComodinesUtilizados(View view) {
        comodinesUtilizados[1] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem01);
        comodinesUtilizados[2] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem02);
        comodinesUtilizados[3] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem03);
        comodinesUtilizados[4] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem04);
        comodinesUtilizados[5] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem05);
        comodinesUtilizados[6] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem06);
        comodinesUtilizados[7] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem07);
        comodinesUtilizados[8] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem08);
        comodinesUtilizados[9] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem09);
        comodinesUtilizados[10] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem10);
        comodinesUtilizados[11] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem11);
        comodinesUtilizados[12] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem12);
        comodinesUtilizados[13] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem13);
        comodinesUtilizados[14] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem14);
        comodinesUtilizados[15] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem15);
        comodinesUtilizados[16] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem16);
        comodinesUtilizados[17] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem17);
        comodinesUtilizados[18] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem18);
        comodinesUtilizados[19] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem19);
        comodinesUtilizados[20] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem20);
        comodinesUtilizados[21] = (TextView) view.findViewById(R.id.textview_uso_comodin_sem21);

    }

    private void setupViewsProductosFaltantes(View view) {
        productosFaltantes[1] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem01);
        productosFaltantes[2] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem02);
        productosFaltantes[3] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem03);
        productosFaltantes[4] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem04);
        productosFaltantes[5] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem05);
        productosFaltantes[6] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem06);
        productosFaltantes[7] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem07);
        productosFaltantes[8] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem08);
        productosFaltantes[9] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem09);
        productosFaltantes[10] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem10);
        productosFaltantes[11] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem11);
        productosFaltantes[12] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem12);
        productosFaltantes[13] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem13);
        productosFaltantes[14] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem14);
        productosFaltantes[15] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem15);
        productosFaltantes[16] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem16);
        productosFaltantes[17] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem17);
        productosFaltantes[18] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem18);
        productosFaltantes[19] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem19);
        productosFaltantes[20] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem20);
        productosFaltantes[21] = (TextView) view.findViewById(R.id.textview_productos_faltantes_sem21);

        for(int i=1; i<22; i++) productosFaltantes[i].setOnClickListener(this);
    }

    private void setupViewsProductosEvaluadosSem(View view) {
        productosEvaluadosSem[1] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem01);
        productosEvaluadosSem[2] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem02);
        productosEvaluadosSem[3] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem03);
        productosEvaluadosSem[4] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem04);
        productosEvaluadosSem[5] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem05);
        productosEvaluadosSem[6] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem06);
        productosEvaluadosSem[7] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem07);
        productosEvaluadosSem[8] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem08);
        productosEvaluadosSem[9] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem09);
        productosEvaluadosSem[10] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem10);
        productosEvaluadosSem[11] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem11);
        productosEvaluadosSem[12] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem12);
        productosEvaluadosSem[13] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem13);
        productosEvaluadosSem[14] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem14);
        productosEvaluadosSem[15] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem15);
        productosEvaluadosSem[16] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem16);
        productosEvaluadosSem[17] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem17);
        productosEvaluadosSem[18] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem18);
        productosEvaluadosSem[19] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem19);
        productosEvaluadosSem[20] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem20);
        productosEvaluadosSem[21] = (TextView) view.findViewById(R.id.textview_productos_evaluados_sem21);


    }

    private void fillProductosEncontrados(){
        for(int i=1; i<22; i++){
            Log.d("visitatxt", i+"Productos encontrados "+cliente.getNumProductosEncontrados(i));
            productosEncontradosSem[i].setText(cliente.getNumProductosEncontrados(i)+"");
            //comodinesUtilizados[i].setText(cliente.getNumProductosComodin(i)+"");
        }
    }

    private void fillProductosEvaluados(){
        for(int i=1; i<22; i++){
            Log.d("visitatxt", i+"Productos encontrados "+cliente.getNumProductosEncontrados(i));
            int evaluados = cliente.getNumProductosEncontrados(i)+cliente.getNumProductosComodin(i);
            productosEvaluadosSem[i].setText(evaluados+"");
            //comodinesUtilizados[i].setText(cliente.getNumProductosComodin(i)+"");
        }
    }

    private void fillProductosComodin(){
        for(int i=1; i<22; i++){
            Log.d("visitatxt", i+"Productos encontrados "+cliente.getNumProductosEncontrados(i));
            int numComodinesUtilizados = cliente.getNumProductosComodin(i);
            if(numComodinesUtilizados<10){
                comodinesUtilizados[i].setText("0"+numComodinesUtilizados+"\n"+"comodines");
            }
            else{
                comodinesUtilizados[i].setText(numComodinesUtilizados+"\n"+"comodines");
            }

        }
    }

    private void setupViewsProductosEncontradosSem(View view) {
        productosEncontradosSem[1] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem01);
        productosEncontradosSem[2] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem02);
        productosEncontradosSem[3] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem03);
        productosEncontradosSem[4] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem04);
        productosEncontradosSem[5] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem05);
        productosEncontradosSem[6] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem06);
        productosEncontradosSem[7] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem07);
        productosEncontradosSem[8] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem08);
        productosEncontradosSem[9] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem09);
        productosEncontradosSem[10] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem10);
        productosEncontradosSem[11] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem11);
        productosEncontradosSem[12] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem12);
        productosEncontradosSem[13] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem13);
        productosEncontradosSem[14] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem14);
        productosEncontradosSem[15] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem15);
        productosEncontradosSem[16] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem16);
        productosEncontradosSem[17] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem17);
        productosEncontradosSem[18] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem18);
        productosEncontradosSem[19] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem19);
        productosEncontradosSem[20] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem20);
        productosEncontradosSem[21] = (TextView) view.findViewById(R.id.textview_productos_encontrados_sem21);

    }

    private void setupViewsProductosExhigidosSem(View view) {
        productosExhigidosSem[1] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem01);
        productosExhigidosSem[2] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem02);
        productosExhigidosSem[3] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem03);
        productosExhigidosSem[4] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem04);
        productosExhigidosSem[5] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem05);
        productosExhigidosSem[6] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem06);
        productosExhigidosSem[7] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem07);
        productosExhigidosSem[8] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem08);
        productosExhigidosSem[9] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem09);
        productosExhigidosSem[10] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem10);
        productosExhigidosSem[11] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem11);
        productosExhigidosSem[12] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem12);
        productosExhigidosSem[13] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem13);
        productosExhigidosSem[14] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem14);
        productosExhigidosSem[15] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem15);
        productosExhigidosSem[16] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem16);
        productosExhigidosSem[17] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem17);
        productosExhigidosSem[18] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem18);
        productosExhigidosSem[19] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem19);
        productosExhigidosSem[20] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem20);
        productosExhigidosSem[21] = (TextView) view.findViewById(R.id.textview_productos_exhigidos_sem21);

        for(int i=1; i<22; i++){
            productosExhigidosSem[i].setText(cliente.productosExhigidos);
        }
    }

    private void setupViewsDescripcionSemanas(View view) {
        descripcionSem[1] = (TextView) view.findViewById(R.id.textview_descripcionsemana_01);
        descripcionSem[2] = (TextView) view.findViewById(R.id.textview_descripcionsemana_02);
        descripcionSem[3] = (TextView) view.findViewById(R.id.textview_descripcionsemana_03);
        descripcionSem[4] = (TextView) view.findViewById(R.id.textview_descripcionsemana_04);
        descripcionSem[5] = (TextView) view.findViewById(R.id.textview_descripcionsemana_05);
        descripcionSem[6] = (TextView) view.findViewById(R.id.textview_descripcionsemana_06);
        descripcionSem[7] = (TextView) view.findViewById(R.id.textview_descripcionsemana_07);
        descripcionSem[8] = (TextView) view.findViewById(R.id.textview_descripcionsemana_08);
        descripcionSem[9] = (TextView) view.findViewById(R.id.textview_descripcionsemana_09);
        descripcionSem[10] = (TextView) view.findViewById(R.id.textview_descripcionsemana_10);
        descripcionSem[11] = (TextView) view.findViewById(R.id.textview_descripcionsemana_11);
        descripcionSem[12] = (TextView) view.findViewById(R.id.textview_descripcionsemana_12);
        descripcionSem[13] = (TextView) view.findViewById(R.id.textview_descripcionsemana_13);
        descripcionSem[14] = (TextView) view.findViewById(R.id.textview_descripcionsemana_14);
        descripcionSem[15] = (TextView) view.findViewById(R.id.textview_descripcionsemana_15);
        descripcionSem[16] = (TextView) view.findViewById(R.id.textview_descripcionsemana_16);
        descripcionSem[17] = (TextView) view.findViewById(R.id.textview_descripcionsemana_17);
        descripcionSem[18] = (TextView) view.findViewById(R.id.textview_descripcionsemana_18);
        descripcionSem[19] = (TextView) view.findViewById(R.id.textview_descripcionsemana_19);
        descripcionSem[20] = (TextView) view.findViewById(R.id.textview_descripcionsemana_20);
        descripcionSem[21] = (TextView) view.findViewById(R.id.textview_descripcionsemana_21);

        for(int i=1; i<22; i++){
            descripcionSem[i].setText(Utils.semanasCampania().get(i));
        }
    }

    private void setupViewsNumeroSemanas(View view){
        numeroSem[1] = (TextView) view.findViewById(R.id.textview_numerosemana_01);
        numeroSem[2] = (TextView) view.findViewById(R.id.textview_numerosemana_02);
        numeroSem[3] = (TextView) view.findViewById(R.id.textview_numerosemana_03);
        numeroSem[4] = (TextView) view.findViewById(R.id.textview_numerosemana_04);
        numeroSem[5] = (TextView) view.findViewById(R.id.textview_numerosemana_05);
        numeroSem[6] = (TextView) view.findViewById(R.id.textview_numerosemana_06);
        numeroSem[7] = (TextView) view.findViewById(R.id.textview_numerosemana_07);
        numeroSem[8] = (TextView) view.findViewById(R.id.textview_numerosemana_08);
        numeroSem[9] = (TextView) view.findViewById(R.id.textview_numerosemana_09);
        numeroSem[10] = (TextView) view.findViewById(R.id.textview_numerosemana_10);
        numeroSem[11] = (TextView) view.findViewById(R.id.textview_numerosemana_11);
        numeroSem[12] = (TextView) view.findViewById(R.id.textview_numerosemana_12);
        numeroSem[13] = (TextView) view.findViewById(R.id.textview_numerosemana_13);
        numeroSem[14] = (TextView) view.findViewById(R.id.textview_numerosemana_14);
        numeroSem[15] = (TextView) view.findViewById(R.id.textview_numerosemana_15);
        numeroSem[16] = (TextView) view.findViewById(R.id.textview_numerosemana_16);
        numeroSem[17] = (TextView) view.findViewById(R.id.textview_numerosemana_17);
        numeroSem[18] = (TextView) view.findViewById(R.id.textview_numerosemana_18);
        numeroSem[19] = (TextView) view.findViewById(R.id.textview_numerosemana_19);
        numeroSem[20] = (TextView) view.findViewById(R.id.textview_numerosemana_20);
        numeroSem[21] = (TextView) view.findViewById(R.id.textview_numerosemana_21);

        for(int i=1; i<22; i++){
            if(i<10){
                numeroSem[i].setText("Semana \n0"+i);
            }
            else{
                numeroSem[i].setText("Semana \n"+i);
            }
        }
    }

    int getViewIndex(TextView listaview[], int id){
        for(int i=1; i<listaview.length; i++)
            if(listaview[i].getId() == id) return i;
        return -1;
    }

    @Override
    public void onClick(View v) {
        int semana = getViewIndex(productosFaltantes, v.getId());
        AlertDialog.Builder mensaje = new AlertDialog.Builder(getActivity());
        mensaje.setTitle("Productos faltantes")
                .setMessage(cliente.getProductosFaltantes(semana).toString());
        mensaje.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mensaje.create().show();
        //Toast.makeText(getActivity().getApplicationContext(), "Productos faltantes de la semana +\n"+semana, Toast.LENGTH_SHORT).show();
    }
}
