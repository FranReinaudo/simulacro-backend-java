package utn.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import utn.entities.Departamento;
import utn.entities.Puesto;
import utn.menu.ApplicationContext;
import utn.services.EmpleadoService;

public class Acciones {
    // public void nombreMetodo(ApplicationContext context) cada metodo de esta
    // clase la firma debe ser esta

    public void importarEmpleados(ApplicationContext context) {
        var pathToImport = (URL) context.get("path");

        try (var paths = Files.walk(Paths.get(pathToImport.toURI()))) {
            var csvFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .map(Path::toFile)
                    .toList();

            csvFiles.stream()
                    .filter(f -> f.getName().contains("empleados"))
                    .findFirst()
                    .ifPresentOrElse(f -> {
                        var service = context.getService(EmpleadoService.class);
                        try {
                            service.bulkInsert(f);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    },
                    () -> new IllegalArgumentException("Archivo inexistente"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void empleadosFijos(ApplicationContext context) {
        var service = context.getService(EmpleadoService.class);
        int[] count = service.countEmpleadosFijos();
        System.out.println("Cantidad de empleados fijos: " + count[0]);
        System.out.println("Cantidad de empleados no fijos: " + count[1]);
    }

    public void mostrarEmpleados(ApplicationContext context) {
        var service = context.getService(EmpleadoService.class);
        service.mostrarSalarioEmpleados();
    }

    public void mostrarEmpleadosPorDepartamento(ApplicationContext context) {
        var service = context.getService(EmpleadoService.class);
        Map<Departamento, Long> empleadosPorDepartamento = service.countEmpleadosPorDepartamento();
        empleadosPorDepartamento.forEach((departamento, count) ->
                System.out.println("Departamento: " + departamento.getNombre() + ", Empleados: " + count));
    }

    public void mostrarSalarioPromedioPorPuesto(ApplicationContext context) {
        var service = context.getService(EmpleadoService.class);
        Map<Puesto, Double> salarioPromedioPorPuesto = service.salarioPromedioPorPuesto();
        salarioPromedioPorPuesto.forEach((puesto, salarioPromedio) ->
                System.out.println("Puesto: " + puesto.getNombre() + ", Salario Promedio: " + salarioPromedio));
    }
}
