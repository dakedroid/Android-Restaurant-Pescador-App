package com.divaga.pescador;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


public class CuentaFragment extends Fragment {

    RecyclerView recyclerView;
    ContentAdapter adapter;
    int total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cantidad;
        public TextView title;
        public TextView monto;
        public TextView btnEliminar;
      //  public TextView description;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.modelo_cuenta, parent, false));

            cantidad = itemView.findViewById(R.id.item_cantidad);
            title =  itemView.findViewById(R.id.item_title);
            monto = itemView.findViewById(R.id.item_description);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);

            btnEliminar.setTextColor(getResources().getColor(R.color.colorAccent));

           // description = itemView.findViewById(R.id.item_description);

        }

    }


    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Tiles in RecyclerView.
        private final int LENGTH = Pescador.lista.size();

        private final String[] mPlaces;
       // private final String[] mDescriptions;

        public ContentAdapter(Context context) {

            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.cuenta_titulos);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {


            holder.title.setText(Pescador.lista.get(position).getTitulo());
            holder.cantidad.setText(Pescador.lista.get(position).getCantidad());
            holder.monto.setText(Pescador.lista.get(position).getMonto());

            if(position == 0){

                holder.btnEliminar.setText("Opcion");
            }else {

                holder.btnEliminar.setText("Eliminar");

                holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Pescador.lista.get(position) != null){
                            Pescador.deleteItem(position);
                            adapter = new ContentAdapter(getContext());
                            recyclerView.setAdapter(adapter);

                            for(int i = 1; i < Pescador.lista.size(); i++){

                                total += Integer.valueOf(Pescador.lista.get(i).getMonto()) * Integer.valueOf(Pescador.lista.get(i).getCantidad());

                            }
                            CuentaActivity.precio.setText(String.valueOf(total));
                        }
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
