package com.edhuar.mercaton;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComprasMesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComprasMesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ComprasMesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComprasMesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComprasMesFragment newInstance(String param1, String param2) {
        ComprasMesFragment fragment = new ComprasMesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_compras_mes, container, false);
        Cliente cliente = new Cliente(mParam1, getContext());


        Log.d("fragmentcliente", cliente.toString());
        TextView nombre01 = (TextView) view.findViewById(R.id.textview_nombre01);
        TextView nombre02 = (TextView) view.findViewById(R.id.textview_nombre02);
        TextView nombre03 = (TextView) view.findViewById(R.id.textview_nombre03);
        TextView nombre04 = (TextView) view.findViewById(R.id.textview_nombre04);
        TextView nombre05 = (TextView) view.findViewById(R.id.textview_nombre05);
        TextView nombre06 = (TextView) view.findViewById(R.id.textview_nombre06);

        TextView distribuidor01 = (TextView) view.findViewById(R.id.textview_distribuidor01);
        TextView distribuidor02 = (TextView) view.findViewById(R.id.textview_distribuidor02);
        TextView distribuidor03 = (TextView) view.findViewById(R.id.textview_distribuidor03);
        TextView distribuidor04 = (TextView) view.findViewById(R.id.textview_distribuidor04);
        TextView distribuidor05 = (TextView) view.findViewById(R.id.textview_distribuidor05);
        TextView distribuidor06 = (TextView) view.findViewById(R.id.textview_distribuidor06);

        TextView compraDist01 = (TextView) view.findViewById(R.id.textview_compradist01);
        TextView compraDist02 = (TextView) view.findViewById(R.id.textview_compradist02);
        TextView compraDist03 = (TextView) view.findViewById(R.id.textview_compradist03);
        TextView compraDist04 = (TextView) view.findViewById(R.id.textview_compradist04);
        TextView compraDist05 = (TextView) view.findViewById(R.id.textview_compradist05);
        TextView compraDist06 = (TextView) view.findViewById(R.id.textview_compradist06);
        TextView compraTotal = (TextView) view.findViewById(R.id.textview_compratotal);

        nombre01.setText(cliente.nombreCompra01);
        nombre02.setText(cliente.nombreCompra02);
        nombre03.setText(cliente.nombreCompra03);
        nombre04.setText(cliente.nombreCompra04);
        nombre05.setText(cliente.nombreCompra05);
        nombre06.setText(cliente.nombreCompra06);

        distribuidor01.setText(cliente.distribuidora01);
        distribuidor02.setText(cliente.distribuidora02);
        distribuidor03.setText(cliente.distribuidora03);
        distribuidor04.setText(cliente.distribuidora04);
        distribuidor05.setText(cliente.distribuidora05);
        distribuidor06.setText(cliente.distribuidora06);

        if(mParam2.equalsIgnoreCase("setiembre")){
            compraDist01.setText("S/"+cliente.getCompra(1, 1, getContext()));
            compraDist02.setText("S/"+cliente.getCompra(2, 1, getContext()));
            compraDist03.setText("S/"+cliente.getCompra(3, 1, getContext()));
            compraDist04.setText("S/"+cliente.getCompra(4, 1, getContext()));
            compraDist05.setText("S/"+cliente.getCompra(5, 1, getContext()));
            compraDist06.setText("S/"+cliente.getCompra(6, 1, getContext()));
            compraTotal.setText(cliente.getCompra(7, 1, getContext()));
        }
        else if(mParam2.equalsIgnoreCase("octubre")){
            compraDist01.setText("S/"+cliente.getCompra(1, 2, getContext()));
            compraDist02.setText("S/"+cliente.getCompra(2, 2, getContext()));
            compraDist03.setText("S/"+cliente.getCompra(3, 2, getContext()));
            compraDist04.setText("S/"+cliente.getCompra(4, 2, getContext()));
            compraDist05.setText("S/"+cliente.getCompra(5, 2, getContext()));
            compraDist06.setText("S/"+cliente.getCompra(6, 2, getContext()));
            compraTotal.setText(cliente.getCompra(7, 2,getContext()));
        }

        return view;
    }

}
