package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    public Tarefa findByTitulo(String titulo);
}