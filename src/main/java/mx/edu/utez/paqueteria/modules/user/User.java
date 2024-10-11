package mx.edu.utez.paqueteria.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.paqueteria.modules.packet.Packet;
import mx.edu.utez.paqueteria.modules.rol.Rol;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    // ATRIBUTOS PROPIOS DE LA CLASE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    //ATRIBUTOS DE RELACION
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "user")
    @JsonIgnore //Evitar que aparezcan paquetes al consultar un usuario - Evitar recursividad
    private List<Packet> packets;

}
