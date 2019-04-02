/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tarefa.dao;

import br.com.tarefa.entidade.Tarefa;
import br.com.tarefa.util.Conexao;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public class TarefaDAO {
    
    public void salvar(Tarefa tarefa){
        PreparedStatement ps = null;
        try {
            Connection conexao = Conexao.getConexao();
            
            if( tarefa.getIdTarefa() == null ){
                ps = (PreparedStatement) conexao.prepareCall("INSERT INTO `tarefa` (`tituloTarefa`,`descricaoTarefa`) VALUES (?,?)");
            } else {
                ps = (PreparedStatement) conexao.prepareCall("UPDATE tarefa SET tituloTarefa = ?, descricaoTarefa = ? WHERE idTarefa = ?");
                ps.setInt(3, tarefa.getIdTarefa());
            }
            ps.setString(1, tarefa.getTituloTarefa());
            ps.setString(2, tarefa.getDescricaoTarefa());
            ps.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Tarefa> buscar(){
        Connection conexao = Conexao.getConexao();
        try {
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement("SELECT * FROM tarefa");
            ResultSet resultSet = ps.executeQuery();
            List<Tarefa> tarefas = new ArrayList<>();
            while( resultSet.next() ){
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa( resultSet.getInt("idTarefa") );
                tarefa.setTituloTarefa(resultSet.getString("tituloTarefa"));
                tarefa.setDescricaoTarefa(resultSet.getString("descricaoTarefa"));
                tarefas.add(tarefa);
            }
            return tarefas;
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
