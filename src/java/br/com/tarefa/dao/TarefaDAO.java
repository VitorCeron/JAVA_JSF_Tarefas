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
import java.sql.SQLException;
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
            ps = (PreparedStatement) conexao.prepareCall("INSERT INTO `tarefa` (`tituloTarefa`,`descricaoTarefa`) VALUES (?,?)");
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
    
}
