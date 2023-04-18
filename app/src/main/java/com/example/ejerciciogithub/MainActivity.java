package com.example.ejerciciogithub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et_tabla, et_archivo, et_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_tabla = (EditText) findViewById(R.id.et_tabla);
        et_archivo = (EditText) findViewById(R.id.et_archivo);
        et_mostrar = (EditText) findViewById(R.id.et_mostrar);

    }
    public void multiplicar(View view){
        String cad = "";
        if (et_tabla.getText().toString().equals("")){
            Toast.makeText(this, "Introduce un número", Toast.LENGTH_SHORT).show();
        }else {
            int tabla = Integer.parseInt(et_tabla.getText().toString());
            for (int i = 0; i < tabla + 1; i++) {
                cad += String.valueOf(tabla) + "\t x \t" + i + "\t = \t" + (tabla * i) + "\n";
            }
            et_mostrar.setText(cad);
        }
    }
    //metodo boton guardar
    public void Guardar (View view) {
        String nombre = et_archivo.getText().toString();
        String contenido = et_mostrar.getText().toString();
        String tabla = et_tabla.getText().toString();
        try {
            // Indicamos donde se encuentra la tarjeta SD //
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();
            //CREAMOS EL ARCHIVO con el nombre que nos india el usuario, y el path de la tarjeta SD
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre + ".txt");
            //ABRIMOS EL ARCHIVO
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
            //guardamos los datos en el archivo
            crearArchivo.write(contenido);
            //limpiamos el buffer
            crearArchivo.flush();
            crearArchivo.close();
            Toast.makeText(this, "El archivo se ha gauardado correctamente", Toast.LENGTH_SHORT).show();
            et_archivo.setText("");
            et_mostrar.setText("");
            et_tabla.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo guardar el archivo", Toast.LENGTH_SHORT).show();
        }
    }
    public void Consultar(View view){
        String nombre = et_archivo.getText().toString();
        try{
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while(linea != null){

                contenidoCompleto = contenidoCompleto + linea + "\n";
                linea = leerArchivo.readLine();

            }

            leerArchivo.close();
            abrirArchivo.close();
            et_mostrar.setText(contenidoCompleto);


        }catch (IOException e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }

    }

}
