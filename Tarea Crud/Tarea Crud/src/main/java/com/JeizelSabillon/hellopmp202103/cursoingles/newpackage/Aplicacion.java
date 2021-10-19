/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeizelSabillon.hellopmp202103.cursoingles.newpackage;

import com.JeizelSabillon.hellopmp202103.utilidades.Layout;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yossa
 */
public class Aplicacion {
     private Scanner _EntradaTeclado;
    private ArrayList _MiCurso;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._MiCurso = new ArrayList<cursoItem>();  
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
          case "M":
                this.mostrarRegistros();
                break;
            case "I":
                System.out.println("Ingresar Registro");
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                System.out.println("Actualizar Registro");
                break;
            case "E": 
                this.eliminarRegistro();
                System.out.println("Eliminar Registro");
                break;
            case "S":
                System.out.println("Usted ha salido del sistema Gracias por usar la App---Bendiciones!");
                break;
            default:
                System.out.println("Opción no reconocida!!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Curso");
        cursoItem nuevoCursoItem =  new cursoItem();
        nuevoCursoItem.setNombre(Layout.obtenerValorParaCampo("Nombre del Curso", "Ingles A,B,C,D", this._EntradaTeclado));
        nuevoCursoItem.setNombre(Layout.obtenerValorParaCampo("Cedula", "xxxx-xxxx-xxxxx", this._EntradaTeclado));
        nuevoCursoItem.setNombre(Layout.obtenerValorParaCampo("Años", "Edad Actual(entre 7 y 60)", this._EntradaTeclado));
        nuevoCursoItem.setPrecio(Layout.obtenerValorParaCampo("Precio del Curso", "Precio en lempiras", this._EntradaTeclado));
        nuevoCursoItem.setDuracion(Layout.obtenerValorParaCampo("Duracion del Curso", "Duracion en meses ", this._EntradaTeclado));
        int anio = Integer.parseInt(Layout.obtenerValorParaCampo("Año de creacion yyyy", "2020", this._EntradaTeclado));
        int mes = Integer.parseInt(Layout.obtenerValorParaCampo("Mes de creacion 1-12", "1", this._EntradaTeclado));
        int dia = Integer.parseInt(Layout.obtenerValorParaCampo("Día de creacion 1-31", "1", this._EntradaTeclado));
        //ZonedDateTime fechaLanzamiento = new ZonedDateTime();
        //fechaLanzamiento.setYear(anio);
        //fechaLanzamiento.setMonth(mes);
        //fechaLanzamiento.setDate(dia);
        //nuevoMusicItem.setFechaLanzamiento(fechaLanzamiento); 
        this._MiCurso.add(nuevoCursoItem);
        
        Layout.printSeparator();
        System.out.println(this._MiCurso.size());
    }

    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Nombre del curso");
        columnas.add("Cedula");
        columnas.add("Años");
        columnas.add("Precio L.");
        columnas.add("Duracion (meses)");
        columnas.add("Fecha de creacion");
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(7);
        anchos.add(20);
        anchos.add(13);
        anchos.add(7);
        anchos.add(20);
        anchos.add(14);
        anchos.add(14);
        
        
        // |1234567890|123456789012345|
        try {
            //Imprimir encabezado
            Layout.imprimirEnColumna(columnas, anchos, "||");
            Layout.printSeparator();
            for(int i = 0; i< this._MiCurso.size(); i++){
                columnas = ((cursoItem) this._MiCurso.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "||");
            }
            
        } catch(Exception ex) {
            System.err.println("Error al imprimir " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        cursoItem selectCurso = null;
        for( int i=0; i < this._MiCurso.size(); i++){
            if( selectedId == ((cursoItem)this._MiCurso.get(i)).getId()) {
                selectCurso = (cursoItem)this._MiCurso.get(i);
                break;
            }
        }
        if (selectCurso == null ) {
            System.out.println("Registro solicitado no existe!!!");
        } else {
            selectCurso.setNombre(Layout.obtenerValorParaCampo("Nombre del Curso", selectCurso.getNombre(), this._EntradaTeclado));
            selectCurso.setNombre(Layout.obtenerValorParaCampo("Cedula", selectCurso.getCedula(), this._EntradaTeclado));
            selectCurso.setNombre(Layout.obtenerValorParaCampo("Años", selectCurso.getAnos(), this._EntradaTeclado));
            selectCurso.setPrecio(Layout.obtenerValorParaCampo("Precio del Curso", selectCurso.getPrecio(), this._EntradaTeclado));
            selectCurso.setDuracion(Layout.obtenerValorParaCampo("Duracion del Curso", selectCurso.getDuracion(), this._EntradaTeclado));
            int anio = Integer.parseInt(Layout.obtenerValorParaCampo("Año de Creacion yyyy", "2020", this._EntradaTeclado));
            int mes = Integer.parseInt(Layout.obtenerValorParaCampo("Mes de Creacion 1-12", "1", this._EntradaTeclado));
            int dia = Integer.parseInt(Layout.obtenerValorParaCampo("Día de Creacion 1-31", "1", this._EntradaTeclado));
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }
        
    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        int encontradoEnIndice = -1;
        for( int i=0; i < this._MiCurso.size(); i++){
            if( selectedId == ((cursoItem)this._MiCurso.get(i)).getId()) {
                encontradoEnIndice = i;
                break;
            }
        }
        if (encontradoEnIndice>=0){
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
                this._MiCurso.remove(encontradoEnIndice);
                Layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!!!");
            }
        } else {
            System.out.println("Registro solicitado no existe!!!");
        }
    
    }
}
