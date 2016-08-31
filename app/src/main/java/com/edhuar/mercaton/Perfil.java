package com.edhuar.mercaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Perfil extends AppCompatActivity {
    String codigo;
    TextView textViewCiudad,textViewNombrePuesto, textViewCodigo, textViewRepresentante, textViewDNI,
            textViewDireccion, textViewMercado, textViewGiro, textViewProductosExhigidos, textViewComodinesUtilizados;

    TextView textviewstatusPremioSetiembre, textviewpremioSetiembre;
    TextView textviewstatusPremioOctubre, textviewpremioOctubre;
    TextView textviewstatusPremioNoviembre, textviewpremioNoviembre;
    TextView textviewstatusPremioDiciembre, textviewpremioDiciembre;
    TextView textviewstatusPremioEnero, textviewpremioEnero;
    TextView textViewSemanaCampania;

    Cliente cliente;
    TextView textViewPremio[];
    TextView textViewStatusPremio[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        codigo = getIntent().getStringExtra("codigo");
        cliente = new Cliente(codigo, getApplicationContext());

        setViewsID();
        setInfoCliente();
        setInfoPremios();
        setCurrentWeek();
    }

    private void setCurrentWeek() {
        ArrayList<String> semanasDescripcion = Utils.semanasCampania();
        int semanaCampania = Utils.numeroSemanaCampania();
        textViewSemanaCampania.setText("Semana "+ semanaCampania+"\n"+semanasDescripcion.get(semanaCampania));
    }

    private void setInfoPremio(int indexmes){
        String premio = cliente.getPremio(indexmes, getApplicationContext());
        if(premio.equalsIgnoreCase("-")){
            textViewStatusPremio[indexmes-1].setText("No");
        }
        else{
            textViewStatusPremio[indexmes-1].setText("Sí");
        }
        textViewPremio[indexmes-1].setText(premio);
    }

    private void setInfoPremios() {
        setInfoPremio(Cliente.INDEX_SETIEMBRE);
        setInfoPremio(Cliente.INDEX_OCTUBRE);
        setInfoPremio(Cliente.INDEX_NOVIEMBRE);
        setInfoPremio(Cliente.INDEX_DICIEMBRE);
        setInfoPremio(Cliente.INDEX_ENERO);
    }

    private void setInfoCliente() {
        textViewCiudad.setText(cliente.ciudad);
        textViewNombrePuesto.setText(cliente.nombre);
        textViewCodigo.setText(cliente.codigo);
        textViewRepresentante.setText(cliente.representante);
        textViewDNI.setText(cliente.dni);
        textViewDireccion.setText(cliente.direccion);
        textViewMercado.setText(cliente.mercado);
        textViewGiro.setText(cliente.giro);
        textViewProductosExhigidos.setText(cliente.productosExhigidos);
        textViewComodinesUtilizados.setText(cliente.comodinesUsados);
    }

    private void setViewsID(){
        textViewCiudad = (TextView) findViewById(R.id.textview_ciudad);
        textViewNombrePuesto = (TextView) findViewById(R.id.textview_nombre);
        textViewCodigo   = (TextView) findViewById(R.id.textview_codigo);
        textViewRepresentante = (TextView) findViewById(R.id.textview_representante);
        textViewDNI = (TextView) findViewById(R.id.textview_dni);
        textViewDireccion = (TextView) findViewById(R.id.textview_direccion);
        textViewMercado = (TextView) findViewById(R.id.textview_mercado);
        textViewGiro   = (TextView) findViewById(R.id.textview_giro);
        textViewProductosExhigidos = (TextView) findViewById(R.id.textview_productosexhigidos);
        textViewComodinesUtilizados = (TextView) findViewById(R.id.textview_comodines_utilizados);
        textViewSemanaCampania = (TextView) findViewById(R.id.textview_semana_campania);

        textviewstatusPremioSetiembre = (TextView) findViewById(R.id.textview_status_premio_setiembre);
        textviewpremioSetiembre = (TextView) findViewById(R.id.textview_premio_setiembre);

        textviewstatusPremioOctubre = (TextView) findViewById(R.id.textview_status_premio_octubre);
        textviewpremioOctubre = (TextView) findViewById(R.id.textview_premio_octubre);

        textviewstatusPremioNoviembre = (TextView) findViewById(R.id.textview_status_premio_noviembre);
        textviewpremioNoviembre = (TextView) findViewById(R.id.textview_premio_noviembre);

        textviewstatusPremioDiciembre = (TextView) findViewById(R.id.textview_status_premio_diciembre);
        textviewpremioDiciembre = (TextView) findViewById(R.id.textview_premio_diciembre);

        textviewstatusPremioEnero = (TextView) findViewById(R.id.textview_status_premio_enero);
        textviewpremioEnero = (TextView) findViewById(R.id.textview_premio_enero);

        textViewPremio = new TextView[5];
        textViewPremio[0] = textviewpremioSetiembre;
        textViewPremio[1] = textviewpremioOctubre;
        textViewPremio[2] = textviewpremioNoviembre;
        textViewPremio[3] = textviewpremioDiciembre;
        textViewPremio[4] = textviewpremioEnero;

        textViewStatusPremio = new TextView[5];
        textViewStatusPremio[0] = textviewstatusPremioSetiembre;
        textViewStatusPremio[1] = textviewstatusPremioOctubre;
        textViewStatusPremio[2] = textviewstatusPremioNoviembre;
        textViewStatusPremio[3] = textviewstatusPremioDiciembre;
        textViewStatusPremio[4] = textviewstatusPremioEnero;
    }
}
