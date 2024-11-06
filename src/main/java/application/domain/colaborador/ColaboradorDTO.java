package application.domain.colaborador;

public record ColaboradorDTO(
    long id, 
    String nome) {
    public ColaboradorDTO(Colaborador colaborador) {
        this(
            colaborador.getId(),
            colaborador.getNome()
        );
    }
}