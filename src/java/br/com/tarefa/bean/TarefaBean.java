
package br.com.tarefa.bean;
import br.com.tarefa.dao.TarefaDAO;
import br.com.tarefa.entidade.Tarefa;
import br.com.tarefa.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vitor
 */
@ManagedBean
@SessionScoped
public class TarefaBean {

    private Tarefa tarefa = new Tarefa();
    private List<Tarefa> tarefas = new ArrayList<>();
    private TarefaDAO tarefaDAO = new TarefaDAO();
    
    public void adicionar(){
        try {
            tarefaDAO.salvar(tarefa);
            tarefa = new Tarefa();
            adicionarMensagem("Salvo!", "Tarefa salva com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            tarefas = tarefaDAO.buscar();
            if( tarefas == null || tarefas.size() == 0 ){
                adicionarMensagem("Ops!", "Nenhuma tarefa localizada.", FacesMessage.SEVERITY_INFO);
            }
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void deletar(Tarefa t){
        try {
            tarefaDAO.deletar(t.getIdTarefa());
            adicionarMensagem("Deletado!", "Tarefa excluída com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Tarefa t){
        tarefa = t;
    }
    
    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
    
    
}
