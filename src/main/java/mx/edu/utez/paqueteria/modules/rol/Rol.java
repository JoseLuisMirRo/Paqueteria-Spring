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

    //1. Constructor vacio
    public Rol() {
    }

    //2. Constructor con todos los atributos de la clase sin ID
    public Rol(String name) {
        this.name = name;
    }

    //3. Constructor con todos los atributos de la clase y ID
    public Rol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //4. Constructor con todos los atributos de clase y de las relaciones sin ID
    public Rol(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    //5. Constructor con todos los atributos de clase y de las relaciones con ID
    public Rol(int id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
