package application.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.ColaboradorRepository;
import application.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Iterable<TarefaDTO> findAll() {
        return tarefaRepository.findAll().stream().map(TarefaDTO::new).toList();
    }

    public TarefaDTO findById(long id) {
        Optional<Tarefa> resultado = tarefaRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tarefa Não Encontrada");
        }
        return new TarefaDTO(resultado.get());
    }

    public TarefaDTO insert(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefa.setDataCriacao(LocalDate.now());
        Tarefa insertTarefa = tarefaRepository.save(tarefa);
        return new TarefaDTO(insertTarefa);
    }

    public TarefaDTO update(long id, TarefaDTO tarefaDTO) {
        if (!tarefaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tarefa Não Encontrada");
        }
        Tarefa novo = new Tarefa(tarefaDTO);
        novo.setId(id);
        Tarefa atualizado = tarefaRepository.save(novo);
        return new TarefaDTO(atualizado);
    }

    public void delete(long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tarefa Não Encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    public void insertColaborador(Long tarefaId, Long colaboradorId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElse(null);
        if (tarefa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }

        Colaborador colaborador = colaboradorRepository.findById(colaboradorId).orElse(null);
        if (colaborador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado");
        }

        tarefa.getColaboradores().add(colaborador);
        tarefaRepository.save(tarefa);
    }

    public void deleteColaborador(Long tarefaId, Long colaboradorId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElse(null);
        if (tarefa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }

        Colaborador colaborador = colaboradorRepository.findById(colaboradorId).orElse(null);
        if (colaborador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado");
        }

        tarefa.getColaboradores().remove(colaborador);
        tarefaRepository.save(tarefa);
    }
}