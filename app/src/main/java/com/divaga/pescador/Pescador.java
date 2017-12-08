package com.divaga.pescador;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import java.util.ArrayList;

/**
 * Creado por dakedroid el 07/12/17.
 */

public class Pescador extends Application {

    public static ArrayList<Item> lista = new ArrayList<>();
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();


    }

    public static void deleteItem(int position){
        lista.remove(position);
    }

    public static  void addItem(Item item){

        if(lista.isEmpty()){
            lista.add(new Item("Cantidad", "Titulo", "Monto", "Opcion"));
        }

        lista.add(item);
        for(int i = 0; i < lista.size(); i++){
            Log.i("prueba",lista.get(i).getTitulo());
        }
    }



    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
