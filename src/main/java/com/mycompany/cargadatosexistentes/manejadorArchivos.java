/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cargadatosexistentes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Artist
 */
public class manejadorArchivos {

    // private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto", "txt");
    public File archivo = null;
    private boolean abierto = false;//bandera de control para saber si se abrio un archivo
    private ArrayList contenido = new ArrayList();//almacena los registros leidos de *.txt
    private int index = 0; //lleva control del registro actualmente visible

    public void Abrir(JFileChooser fileChooser) {
        //fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.archivo = fileChooser.getSelectedFile();
            leer(this.archivo);
            this.abierto = true;
        }
    }

    /* Lee linea por linea un archivo de texto y almacena los registros
     * en un ArrayList segun orden de lectura
     * input: File
     */
    public boolean leer(File fichero) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fichero));
            this.contenido.clear();
            String linea;
            while ((linea = reader.readLine()) != null) {
                this.contenido.add(linea);
            }
            //muestra el primer registro en la interfaz
            //Siguiente();
            reader.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return false;
    }

    public void imprimirArraylist() {
        for (int i = 0; i < contenido.size(); i++) {
            System.out.println(contenido.get(i) + "\n");
        }

    }

    /* funcion qye avanza al siguiente registro del ArrayList y lo muestra en pantalla
     */
    public void comprabacionYCreacionDatosExistentes() {
        if (this.archivo != null) {
            //incrementa en 1 la variable "index", si se supera el tamaño de lineas, vuelve a valor 1

            Iterator It = contenido.iterator();
            //comienza busqueda
            while (It.hasNext()) {
                String tmp = It.next().toString();
                //separa el registro por campos. Separador = ","
                String[] datos = tmp.split(",");

                if (datos[0].equals("TIENDA")) {
                    System.out.println("se logro capturar: " + datos[0] + " - " + datos[1]);

                } else {
                    System.out.println("no se logro capturar: " + datos[0]);

                }

            }
        }
    }

}
