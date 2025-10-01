package utn.app;

import java.net.URL;

import utn.menu.ApplicationContext;
import utn.menu.ItemMenu;
import utn.menu.Menu;
import utn.services.DepartamentoService;
import utn.services.EmpleadoService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var ctx = ApplicationContext.getInstance();
        Menu menu = new Menu();
        menu.setTitulo("\nMenu de Opciones para Museo");

        Acciones acciones = new Acciones();

        URL folderPath = App.class.getResource("/files");
        ctx.put("path", folderPath);

        ctx.registerService(EmpleadoService.class, new EmpleadoService());
        ctx.registerService(DepartamentoService.class, new DepartamentoService());

        menu.addOpcion(new ItemMenu(1, "Cargar empleados", acciones::importarEmpleados));
        menu.addOpcion(new ItemMenu(2, "Contar empleados", acciones::empleadosFijos));
        menu.addOpcion(new ItemMenu(3, "Mostrar empleados y salario", acciones::mostrarEmpleados));
        menu.addOpcion(new ItemMenu(4, "Mostrar empleados por departamento", acciones::mostrarEmpleadosPorDepartamento));
        menu.addOpcion(new ItemMenu(5, "Mostrar salario promedio por puesto", acciones::mostrarSalarioPromedioPorPuesto));
        //menu.addOpcion(new ItemMenu(0, "Salir", p -> System.exit(1)));

        menu.ejecutar(ctx);
    }
}
