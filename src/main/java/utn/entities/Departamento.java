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
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "departamento")
@NamedQueries({
    @NamedQuery(name = "Departamento.GetById", query = "SELECT d FROM Departamento d WHERE d.id = :id")
})
public class Departamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Empleado> empleados;
}
