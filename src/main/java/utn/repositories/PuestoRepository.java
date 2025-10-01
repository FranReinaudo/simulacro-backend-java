package utn.repositories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utn.entities.Puesto;

public class PuestoRepository extends Repositorio<Puesto, Integer> {
    
    @Override
    public Puesto getById(Integer id) {
        var lista = manager.createNamedQuery("Puesto.GetById", Puesto.class)
                .setParameter("id", id)
                .getResultList();
        return lista.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No se encontró el empleado con id: " + id));
    }

    @Override
    public Set<Puesto> getAll() {
        return this.manager.createQuery("SELECT t FROM Puesto t", Puesto.class)
        .getResultList()
        .stream()
        .collect(Collectors.toSet());
    }

    @Override
    public Stream<Puesto> getAllStream() {
        return this.manager.createQuery("SELECT t FROM Puesto t", Puesto.class).getResultStream();
    }
}