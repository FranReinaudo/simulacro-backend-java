package utn.repositories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utn.entities.Departamento;

public class DepartamentoRepository extends Repositorio<Departamento, Integer> {

    @Override
    public Departamento getById(Integer id) {
        var lista = manager.createNamedQuery("Departamento.GetById", Departamento.class)
                .setParameter("id", id)
                .getResultList();
        return lista.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No se encontró el empleado con id: " + id));
    }

    @Override
    public Set<Departamento> getAll() {
        return this.manager.createQuery("SELECT t FROM Departamento t", Departamento.class)
                .getResultList()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Stream<Departamento> getAllStream() {
        return this.manager.createQuery("SELECT t FROM Departamento t", Departamento.class).getResultStream();
    }
}
