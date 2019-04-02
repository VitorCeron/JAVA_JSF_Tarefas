package br.com.tarefa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public class Conexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/tarefas-java";
    private static final String USUARIO = "root";
    private static final String SENHA = null;
    
    public static Connection getConexao(){
        
        if( conexao == null ){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
        
    }
    
    public static void fecharConexao(){
        if( conexao != null ){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
