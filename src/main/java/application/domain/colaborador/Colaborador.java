package application.domain.colaborador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colaborador")
@Getter
@Setter
@NoArgsConstructor
public class Colaborador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    public Colaborador(ColaboradorDTO colaborador) {
        this.id = colaborador.id();
        this.nome = colaborador.nome();
    }
    
}
