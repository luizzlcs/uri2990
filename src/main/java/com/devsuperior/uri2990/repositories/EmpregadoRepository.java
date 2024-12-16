package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, String> {

    @Query(nativeQuery = true, value = "SELECT cpf, enome, departamentos.dnome " 
            + "FROM empregados "
            + "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero "
            + "INNER JOIN projetos ON projetos.dnumero = departamentos.dnumero "
            + "LEFT JOIN trabalha ON trabalha.cpf_emp = empregados.cpf "
            + "WHERE  trabalha.cpf_emp IS NULL")
    List<EmpregadoDeptProjection> search();

}
