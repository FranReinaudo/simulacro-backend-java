package utn.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "puesto")
@NamedQueries({
    @NamedQuery(name = "Puesto.GetById", query = "SELECT p FROM Puesto p WHERE p.id = :id")
})
public class Puesto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nombre;

    @OneToMany(mappedBy = "puesto", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Empleado> empleados;
}
