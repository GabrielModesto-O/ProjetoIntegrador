
package dao;


import ConnectionFactory.ConnectionFactory;
import java.security.Timestamp;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import models.Usuarios;
public class UsuarioDao {
    
public void create(Usuarios usuario) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement(
         "insert into usuarios (login, senha, tipo_u, nome_u, foto_u, criacao_u, atualizacao_u, status_u) values (?,?,?,?,?,?,?,?,?);") ;
            sql.setString(1, usuario.getLogin());
            sql.setString(2, usuario.getPassword());
            sql.setString(3, usuario.getType());
            sql.setString(4, usuario.getName());
            sql.setString(5, usuario.getPhotograph());
            sql.setTimestamp(6, Timestamp.valueOf(usuario.getCreation()));
            sql.setTimestamp(7, Timestamp.valueOf(usuario.getUpdate()));
            sql.setString(8, usuario.getStatus());
            
            sql.executeUpdate();
            
            JOptionPane.showMessageDialog(
                    null, "Cadasdtrado com sucesso!");
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(
                   null, "Erro ao Cadastrar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, sql);
        }
        
    }
    
    
    public List<Usuarios> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Usuarios> usuarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM usuario;");
            rs = sql.executeQuery();
            while(rs.next()){
                Usuarios usuario = new Usuarios();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPassword(rs.getString("password"));
                usuario.setType(rs.getString("type_u"));
                usuario.setName(rs.getString("name_u"));
                usuario.setPhotograph(rs.getString("foto_u"));
                usuario.setCreation(rs.getTimestamp("craicao_u").toLocalDateTime());
                usuario.setUpdate(rs.getTimestamp("atualizacao_u").toLocalDateTime());
                usuario.setStatus(rs.getString("status_u"));
                
                usuarios.add(usuario);
           
            
            
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
    List<Usuarios> usuario = null;
        return usuarios;
      
    }
    
    
    public void update(Usuarios usuario) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        try{
            sql = con.prepareStatement("update usuarios set login = ?, senha = ?, tipo_u = ?, nome_u = ?, foto_u = ? criacao_u = ?, atualizacao_i = ?, status_i = ? where id = ?");
           
            sql.setString(1, usuario.getLogin());
            sql.setString(2, usuario.getPassword());
            sql.setString(3, usuario.getType());
            sql.setString(4, usuario.getName());
            sql.setString(5, usuario.getPhoto());
            sql.setTimestamp(6, Timestamp.valueOf(usuario.getCreation()));
            sql.setTimestamp(7, Timestamp.valueOf(usuario.getUpdate()));
            sql.setString(8, usuario.getStatus());
         
            
            JOptionPane.showMessageDialog(null, "Sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql,rs);
        }
    }
    
    public void delete(Usuarios usuario) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?");
            sql.setInt(1, usuario.getId());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql);
        }
    }
    
     
    public List<Usuarios> readBusca(String busca) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Usuarios> usuarios = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM usuarios WHERE nome LIKE ?");
            sql.setString(1, "%"+busca+"%");
            rs = sql.executeQuery();
            while(rs.next()){
               Usuarios usuario = new Usuarios();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPassword(rs.getString("senha"));
                usuario.setType(rs.getString("tipo_u"));
                usuario.setName(rs.getString("nome_u"));
                usuario.setCreation(rs.getTimestamp("criacao_u").toLocalDateTime());
                usuario.setUpdate(rs.getTimestamp("atualizacao_u").toLocalDateTime());
                usuario.setStatus(rs.getString("status_u"));
                usuarios.add(usuario);
           
               
            
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return usuarios; 
    }

    
}





 