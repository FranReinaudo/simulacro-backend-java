package utn.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.GetById", query = "SELECT e FROM Empleado e WHERE e.id = :id")
})
public class Empleado {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nombre;
    int edad;
    LocalDate fechaIngreso;
    double salario;
    boolean empleadoFijo;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "departamentoId", referencedColumnName = "id")
    Departamento departamento;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "puestoId", referencedColumnName = "id")
    Puesto puesto;

    public double calcularSalario() {
        return empleadoFijo ? salario * 1.08 : salario;
    }


}
