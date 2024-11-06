package application.domain.colaborador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Iterable<ColaboradorDTO> findAll() {
        return repository.findAll().stream().map(ColaboradorDTO::new).toList();
    }

    public ColaboradorDTO findById(Long id) {
        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        return new ColaboradorDTO(colaborador);
    }

    public ColaboradorDTO insert(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador(colaboradorDTO);
        Colaborador insertColaborador = repository.save(colaborador);
        return new ColaboradorDTO(insertColaborador);
    }

    public ColaboradorDTO update(Long id, ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        colaborador.setNome(colaboradorDTO.nome());
        Colaborador updateColaborador = repository.save(colaborador);
        return new ColaboradorDTO(updateColaborador);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Colaborador não encontrado");
        }
        repository.deleteById(id);
    }
}
