package utn.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import utn.entities.Departamento;
import utn.entities.Empleado;
import utn.entities.Puesto;
import utn.repositories.EmpleadoRepository;

public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoService departamentoService;
    private final PuestoService puestoService;

    public EmpleadoService() {
        this.empleadoRepository = new EmpleadoRepository();
        this.departamentoService = new DepartamentoService();
        this.puestoService = new PuestoService();
    }

    public void bulkInsert(File fileToImport) throws IOException {
        Files.lines(Paths.get(fileToImport.toURI()))
                .skip(1) // saltar la primera linea (header)
                .forEach(linea -> {
                    Empleado empleado = procesarLinea(linea);
                    empleadoRepository.add(empleado);
                });
        System.out.println("Importación finalizada.");
    }

    private Empleado procesarLinea(String linea) {
        String[] tokens = linea.split(",");
        Empleado empleado = new Empleado();

        empleado.setNombre(tokens[0]);
        empleado.setEdad(Integer.parseInt(tokens[1]));
        empleado.setFechaIngreso(LocalDate.parse(tokens[2]));
        empleado.setSalario(Double.parseDouble(tokens[3]));
        empleado.setEmpleadoFijo(tokens[4].equalsIgnoreCase("1"));
        var departamento = departamentoService.getOrCreateDepartamento(tokens[5]);
        empleado.setDepartamento(departamento);
        var puesto = puestoService.getOrCreatePuesto(tokens[6]);
        empleado.setPuesto(puesto);

        // String nombreAutor = tokens[2];
        // var autor = autorService.getOrCreateAutor(nombreAutor);
        // obra.setAutor(autor);
        // boolean tieneSeguro = tokens[6].equalsIgnoreCase("1"); // forma de pasar a booleano cuando te dan 1 o 0.
        return empleado;
    }

    public int[] countEmpleadosFijos() {
        Map<Boolean, Long> conteo = empleadoRepository.getAllStream().collect(Collectors.groupingBy(Empleado::isEmpleadoFijo, Collectors.counting()));
        int fijos = conteo.getOrDefault(true, 0L).intValue();
        int noFijos = conteo.getOrDefault(false, 0L).intValue();

        return new int[]{fijos, noFijos};
    }

    public void mostrarSalarioEmpleados() {
        var empleadosStream = empleadoRepository.getAllStream();
        empleadosStream.forEach(e -> System.out.println("Empleado: " + e.getNombre() + ", Salario: " + e.calcularSalario()));
    }

    public Map<Departamento, Long> countEmpleadosPorDepartamento() {
        return empleadoRepository.getAllStream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));
    }

    public Map<Puesto, Double> salarioPromedioPorPuesto() {
        var empleadosStream = empleadoRepository.getAllStream();
        return empleadosStream.collect(Collectors.groupingBy(Empleado::getPuesto,
                Collectors.averagingDouble(Empleado::calcularSalario)));
    }
}
