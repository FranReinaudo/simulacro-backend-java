package utn.services;

import java.util.HashMap;
import java.util.Map;

import utn.entities.Puesto;
import utn.repositories.PuestoRepository;

public class PuestoService {
    private final PuestoRepository puestoRepository;
    private Map<String, Puesto> puestos = new HashMap<>();

    public PuestoService() {
        this.puestoRepository = new PuestoRepository();
        this.puestos = new HashMap<>();
    }

    public Puesto getOrCreatePuesto(String nombrePuesto) {
        return this.puestos.computeIfAbsent(nombrePuesto, nombre -> {
            Puesto nuevoPuesto = new Puesto();
            nuevoPuesto.setNombre(nombrePuesto);
            puestoRepository.add(nuevoPuesto);
            return nuevoPuesto;
        });
    }
}