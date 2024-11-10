package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import application.record.TarefaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;

    @ManyToMany
    @JoinTable(name = "tarefa_colaborador", joinColumns = @JoinColumn(name = "tarefa_id"), inverseJoinColumns = @JoinColumn(name = "colaborador_id"))
    private Set<Colaborador> colaboradores = new HashSet<>();

    public Tarefa(TarefaDTO tarefa) {
        this.id = tarefa.id();
        this.titulo = tarefa.titulo();
        this.descricao = tarefa.descricao();
        this.dataCriacao = tarefa.dataCriacao();
        this.dataInicio = tarefa.dataInicio();
        this.dataConclusao = tarefa.dataConclusao();
    }

}