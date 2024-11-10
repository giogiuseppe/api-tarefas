package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.record.ColaboradorDTO;
import application.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Iterable<ColaboradorDTO> findAll() {
        return repository.findAll().stream().map(ColaboradorDTO::new).toList();
    }

    public ColaboradorDTO findById(long id) {
        Optional<Colaborador> resultado = repository.findById(id);
        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Colaborador Não Encontrado");
        }
        return new ColaboradorDTO(resultado.get());
    }

    public ColaboradorDTO insert(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador(colaboradorDTO);
        Colaborador insertColaborador = repository.save(colaborador);
        return new ColaboradorDTO(insertColaborador);
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaboradorDTO) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Colaborador Não Encontrado");
        }
        Colaborador novo = new Colaborador(colaboradorDTO);
        novo.setId(id);
        Colaborador atualizado = repository.save(novo);
        return new ColaboradorDTO(atualizado);
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Colaborador Não Encontrado");
        }
        repository.deleteById(id);
    }
}