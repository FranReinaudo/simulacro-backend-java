package utn.services;

import java.util.HashMap;
import java.util.Map;

import utn.entities.Departamento;
import utn.repositories.DepartamentoRepository;

public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private Map<String, Departamento> departamentos = new HashMap<>();

    public DepartamentoService() {
        this.departamentoRepository = new DepartamentoRepository();
        this.departamentos = new HashMap<>();
    }

    public Departamento getOrCreateDepartamento(String nombreDepartamento) {
        return this.departamentos.computeIfAbsent(nombreDepartamento, nombre -> {
            Departamento nuevoDepartamento = new Departamento();
            nuevoDepartamento.setNombre(nombreDepartamento);
            departamentoRepository.add(nuevoDepartamento);
            return nuevoDepartamento;
        });
    }
}
