/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JeizelSabillon.hellopmp202103.conexion;
import com.JeizelSabillon.hellopmp202103.cursoingles.newpackage.cursoItem;
import java.util.ArrayList;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author yossa
 */
public class CursoDB {
     private ArrayList _cursoItems;
    
    public CursoDB(){
            this._cursoItems = new ArrayList<cursoItem>();
    }
    
    public ArrayList<cursoItem> getCursoItems(){
        return this.getCursoItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS CURSO"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " NUMERO DE CEDULA TEXT NOT NULL,"
                        + " CANTIDAD DE AÑOS TEXT NOT NULL,"
                        + " NOMBRE DEL CURSO TEXT NOT NULL,"
                        + " PRECIO TEXT NOT NULL,"
                        + " DURACION TEXT NOT NULL"
                        + ")";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<cursoItem> getCursoItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from CURSO;");
                this._cursoItems.clear();
                while (misRegistro.next()) {
                    cursoItem registro = new cursoItem();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setPrecio(misRegistro.getString("CEDULA"));
                    registro.setPrecio(misRegistro.getString("AÑOS"));
                    registro.setNombre(misRegistro.getString("NOMBRE DEL CURSO"));
                    registro.setPrecio(misRegistro.getString("PRECIO"));
                    registro.setDuracion(misRegistro.getString("DURACION"));
                    this._cursoItems.add(registro);
                }
                comando.close();
           }
           return this._cursoItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._cursoItems;
        }   
    }
    
    public cursoItem getCursoItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM CURSO WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            cursoItem registro = new cursoItem();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                 registro.setPrecio(misRegistro.getString("CEDULA"));
                   registro.setPrecio(misRegistro.getString("AÑOS"));
                registro.setNombre(misRegistro.getString("NOMBRE DEL CURSO"));
                registro.setPrecio(misRegistro.getString("PRECIO"));
                registro.setDuracion(misRegistro.getString("DURACION"));
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updateCursoItem(cursoItem ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE CURSO set NOMBRE DEL CURSO=?,CEDULA=?,ANOS=?, PRECIO=?, DURACION=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getNombre());
            comando.setString(2, ItemToUpdate.getCedula());
            comando.setString(3, ItemToUpdate.getAnos());
            comando.setString(4, ItemToUpdate.getPrecio());
            comando.setString(5, ItemToUpdate.getDuracion());
            comando.setInt(6, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertCursoItem(cursoItem ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO CURSO (NOMBRE,CEDULA,AÑOS, PRECIO, DURACION) values (?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getNombre());
            comando.setString(2, ItemToInsert.getCedula());
            comando.setString(3, ItemToInsert.getAnos());
            comando.setString(4, ItemToInsert.getPrecio());
            comando.setString(5, ItemToInsert.getDuracion());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deleteCursoItem(cursoItem ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM CURSO WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
