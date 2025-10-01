package utn.repositories.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpleadoDbContext {

    private final EntityManager manager;

    public static EmpleadoDbContext instance = null;

    private EmpleadoDbContext() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empleados");
        this.manager = emf.createEntityManager();
    }

    public static EmpleadoDbContext getInstance() {
        if (instance == null) {
            instance = new EmpleadoDbContext();
        }
        return instance;
    }

    public EntityManager getManager() {
        return this.manager;
    }
}