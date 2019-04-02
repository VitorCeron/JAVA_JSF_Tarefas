
package br.com.tarefa.bean;
import br.com.tarefa.dao.TarefaDAO;
import br.com.tarefa.entidade.Tarefa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
        tarefaDAO.salvar(tarefa);
        tarefa = new Tarefa();
    }
    
    public void listar(){
        tarefas = tarefaDAO.buscar();
    }
    
    public void editar(Tarefa t){
        tarefa = t;
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
