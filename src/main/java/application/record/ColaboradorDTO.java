package application.record;

import application.model.Colaborador;

public record ColaboradorDTO(
        long id,
        String nome) {
    public ColaboradorDTO(Colaborador colaborador) {
        this(
                colaborador.getId(),
                colaborador.getNome());
    }
}