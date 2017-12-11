

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cochinitoDigital;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lizbeth Flores
 * Tomas Ascencio
 */
public class Alcancia {

    private double valorMoneda;
    private double total;
    Scanner s = null;
    Monedas moneda;
    File archivo;
    File archivoFecha;

    public Alcancia() {
    }

    public double ContarMonedas(ArrayList<Monedas> Cochinito, double total) {
        this.total = total;
        Iterator<Monedas> monedaSigui = Cochinito.iterator();
        while (monedaSigui.hasNext()) {
            moneda = monedaSigui.next();
            total = total + moneda.getValor();
        }
        return total;
    }

    public void GuardarEnArchivo(ArrayList<Monedas> Cochinito) {
        try {
            archivo = new File("Cochinito.txt");
            FileWriter escribir = new FileWriter(archivo, true);
            Iterator<Monedas> monedaSigui = Cochinito.iterator();
            while (monedaSigui.hasNext()) {
                Monedas moneda = monedaSigui.next();
                escribir.write(" " + moneda.getValor());
            }
            escribir.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }

    public ArrayList<Monedas> AbrirArchivo() {
        ArrayList<Monedas> Cochinito;
        try {
//             Leemos el contenido del fichero
            FileReader lector = new FileReader("Cochinito.txt");
            s = new Scanner(lector);
            // Obtengo los datos del archivo 
            Cochinito = new ArrayList<Monedas>();
            while (s.hasNextLine()) {
                String linea = s.nextLine(); // Obtengo una linea del fichero con los numeros
                String[] cortarString = linea.split(" "); // Obtengo los numeros de la linea en un array
                // Pongo los numeros de la fila en el ArrayList
                for (int i = 1; i < cortarString.length; i++) {
                    valorMoneda = Double.parseDouble(cortarString[i]);
                    moneda = new Monedas(valorMoneda);
                    // Añado el ArrayList de enteros al ArratList de ArrayList
                    Cochinito.add(moneda);
                }
            }
            lector.close();
            return Cochinito;
        } //Si se causa un error al leer cae aqui
        catch (IOException e) {
            System.out.println("Error al leer" + e);
        }
        return null;
    }

    public void GuardarFecha(String fecha) {
        try {
            archivoFecha = new File("fecha.txt");
            FileWriter escribirF = new FileWriter(archivoFecha, true);

            escribirF.write(fecha + " ");

            escribirF.close();
        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }

    public String LeerFecha() throws IOException {
        String fecha = "";
//             Leemos el contenido del fichero
        FileReader fechaSistema;
        try {
            fechaSistema = new FileReader("fecha.txt");
            s = new Scanner(fechaSistema);
            while (s.hasNextLine()) {
                fecha = s.nextLine();
            }
            fechaSistema.close();
            return fecha;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Alcancia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void RomperAlcancia() {
        try {
            archivoFecha = new File("fecha.txt");
            archivo = new File("Cochinito.txt");
            
            archivo.delete();
            archivoFecha.delete();
            if (archivo.delete() && archivoFecha.delete()) {
                throw new Exception("El archivo no se pudo borrar");
            } else {
                archivo.createNewFile();
                archivoFecha.createNewFile();
            }
        } //Si se causa un error al leer cae aqui //Si se causa un error al leer cae aqui
        catch (Exception e) {
        }
    }

}
