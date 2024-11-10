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

import application.record.ColaboradorDTO;
import application.service.ColaboradorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("colaboradores")
@SecurityRequirement(name = "bearer-key")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @GetMapping
    public Iterable<ColaboradorDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ColaboradorDTO findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public ColaboradorDTO insert(@RequestBody ColaboradorDTO colaboradorDTO) {
        return service.insert(colaboradorDTO);
    }

    @PutMapping("/{id}")
    public ColaboradorDTO update(
            @PathVariable long id,
            @RequestBody ColaboradorDTO colaboradorDTO) {
        return service.update(id, colaboradorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}