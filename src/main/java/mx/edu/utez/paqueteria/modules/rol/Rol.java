package mx.edu.utez.paqueteria.modules.rol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.paqueteria.modules.user.User;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol {
    // ATRIBUTOS PROPIOS DE LA CLASE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    // Atributos de relacion
    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private List<User> users;
}
