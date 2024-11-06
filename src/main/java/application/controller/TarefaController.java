package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.tarefa.TarefaDTO;
import application.domain.tarefa.TarefaService;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public Iterable<TarefaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TarefaDTO findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public TarefaDTO insert(@RequestBody TarefaDTO tarefaDTO) {
        return service.insert(tarefaDTO);
    }

    @PutMapping("/{id}")
    public TarefaDTO update(
        @PathVariable long id,
        @RequestBody TarefaDTO tarefaDTO) {
        return service.update(id, tarefaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
