package com.divaga.pescador;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class MenuContentFragment extends Fragment {

    String titleString;

    String precioString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView description;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.modelo_menu, parent, false));
            image =  itemView.findViewById(R.id.item_image);
            title =  itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    titleString = title.getText().toString();
                    precioString = description.getText().toString();
                    alertAdd();
                }
            });

        }

    }

    public void alertAdd(){
        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder.setTitle("Carrito");

        final AlertDialog alertDialog = alertDialogBuilder.create();



        final MaterialBetterSpinner spinnerNivel = (MaterialBetterSpinner) promptsView.findViewById(R.id.spr_nivel);

        spinnerNivel.setText("1");

        String[] numeros = new String[]{
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20"};

        ArrayAdapter<String> adaptadorNivel  = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line , numeros );

        spinnerNivel.setAdapter(adaptadorNivel);

        final Button mButton =  promptsView.findViewById(R.id.myButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Item item = new Item();
                item.setCantidad(spinnerNivel.getText().toString());
                item.setTitulo(titleString);
                item.setMonto(precioString);
                item.setOpcion("true");
                Pescador.addItem(item);
                alertDialog.dismiss();



              //  Log.i("prueba_lista", lista.get());
            }
        });

        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }





    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Tiles in RecyclerView.
        private static final int LENGTH = 6;

        private final String[] mPlaces;
        private final Drawable[] mPlacePictures;
        private final String[] mDescriptions;

        public ContentAdapter(Context context) {

            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.menu_titulos);
            TypedArray a = resources.obtainTypedArray(R.array.menu_imagenes);
            mPlacePictures = new Drawable[a.length()];
            for (int i = 0; i < mPlacePictures.length; i++) {
                mPlacePictures[i] = a.getDrawable(i);
            }
            mDescriptions = resources.getStringArray(R.array.cuenta_precios);

            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.image.setAlpha((float) 0.9);
            holder.title.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mDescriptions[position % mPlaces.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
