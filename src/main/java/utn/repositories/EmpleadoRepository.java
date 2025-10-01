package utn.repositories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utn.entities.Empleado;

public class EmpleadoRepository extends Repositorio<Empleado, Integer> {
    
    @Override
    public Empleado getById(Integer id) {
        var lista = manager.createNamedQuery("Empleado.GetById", Empleado.class)
                .setParameter("id", id)
                .getResultList();
        return lista.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No se encontró el empleado con id: " + id));
    }

    @Override
    public Set<Empleado> getAll() {
        return this.manager.createQuery("SELECT t FROM Empleado t", Empleado.class)
        .getResultList()
        .stream()
        .collect(Collectors.toSet());
    }

    @Override
    public Stream<Empleado> getAllStream() {
        return this.manager.createQuery("SELECT t FROM Empleado t", Empleado.class).getResultStream();
    }
}
