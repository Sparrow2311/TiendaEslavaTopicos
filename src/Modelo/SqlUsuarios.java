/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import vista.Conectar;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gateway
 */
public class SqlUsuarios extends Conectar{
    
    
     public boolean registrar(usuarios usr)
     {
         PreparedStatement ps=null;
         Connection con=(Connection) getConexion();
         
          String sql="INSERT INTO usuarios (usuario, password, nombre, correo, id_tipo) VALUES(?,?,?,?,?)";
     
          
          
         try {
             ps=(PreparedStatement) con.prepareStatement(sql);
             ps.setString(1, usr.getUsuario());
             ps.setString(2, usr.getPassword());
             ps.setString(3, usr.getNombre());
             ps.setString(4, usr.getCorreo());
             ps.setInt(5, usr.getId_tipo());
             ps.executeUpdate();
             //ps.execute();
             System.out.println("Guardo en sqlUsuarios");
             return true;
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error en SqlUsuarios");
        return false;
         }
     
     }
    
     
     public int existeUsuario(String usuario)
     {
         PreparedStatement ps=null;
         ResultSet rs = null;
         Connection con=(Connection) getConexion();
         
          String sql="SELECT count(id_Usuario) FROM usuarios WHERE usuario = ?";
     
          
          
         try {
             ps=(PreparedStatement) con.prepareStatement(sql);
             ps.setString(1, usuario);
             
             
             rs = ps.executeQuery();
             //ps.execute();
             System.out.println("Guardo en sqlUsuarios");
             if (rs.next()) {
                 
                 return rs.getInt(1);
                 
             }
             
             return 1;
             
         } catch (SQLException ex) {
             Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error en SqlUsuarios");
        return 1;
         }
     
     }

public boolean  esEmail(String correo){
    
    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    
    Matcher matcher = pattern.matcher(correo);
    
    return matcher.find();

}
 public boolean login(usuarios usr)
     {
         PreparedStatement ps=null;
         ResultSet rs = null;
         Connection con=(Connection) getConexion();
         
          String sql="SELECT u.id_Usuario, u.usuario, u.password, u.nombre, u.id_tipo, t.nombre FROM usuarios AS u INNER JOIN tipo_usuario AS t ON u.id_tipo=t.id WHERE usuario = ?";
     
          
          
         try {
             ps=(PreparedStatement) con.prepareStatement(sql);
             ps.setString(1, usr.getUsuario());
             
             
             rs = ps.executeQuery();
             //ps.execute();
             System.out.println("Guardo en sqlUsuarios");
             if (rs.next()) 
             {
                 
                 if(usr.getPassword().equals(rs.getString(3))){
                     
                     usr.setId_Usuario(rs.getInt(1));
                     usr.setNombre(rs.getString(4));
                     usr.setId_tipo(rs.getInt(5));
                     usr.setNombre_tipo(rs.getString(6));
                     
                     return true;                     
                 }else{
                     return false;
                 }
                 
                 
             }
             
             return false;
             
         } catch (SQLException ex) {
             Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error en SqlUsuarios");
        return false;
         }}

}


