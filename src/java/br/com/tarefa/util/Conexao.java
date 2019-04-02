package br.com.tarefa.util;

import br.com.tarefa.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vitor
 */
public class Conexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/tarefas-java";
    private static final String USUARIO = "root";
    private static final String SENHA = null;
    
    public static Connection getConexao() throws ErroSistema{
        
        if( conexao == null ){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
                } catch (SQLException ex) {
                    throw new ErroSistema("Não foi possível conectar ao banco de dados.", ex);
                }
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("Não foi possível localizar o driver do banco de dados.", ex);
            }
        }
        return conexao;
        
    }
    
    public static void fecharConexao() throws ErroSistema{
        if( conexao != null ){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possível fechar a conexão com o banco de dados.", ex);
            }
        }
    }
    
}
