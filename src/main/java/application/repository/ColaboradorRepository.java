package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}