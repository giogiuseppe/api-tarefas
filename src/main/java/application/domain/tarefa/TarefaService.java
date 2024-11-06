package application.domain.tarefa;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Iterable<TarefaDTO> findAll() {
        return repository.findAll().stream().map(TarefaDTO::new).toList();
    }

    public TarefaDTO findById(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        return new TarefaDTO(tarefa);
    }

    public TarefaDTO insert(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefa.setDataCriacao(LocalDate.now());
        Tarefa insertTarefa = repository.save(tarefa);
        return new TarefaDTO(insertTarefa);
    }

    public TarefaDTO update(Long id, TarefaDTO tarefaDTO) {
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setDataInicio(tarefaDTO.dataInicio());
        tarefa.setDataConclusao(tarefaDTO.dataConclusao());
        Tarefa updateTarefa = repository.save(tarefa);
        return new TarefaDTO(updateTarefa);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        repository.deleteById(id);
    }
}
