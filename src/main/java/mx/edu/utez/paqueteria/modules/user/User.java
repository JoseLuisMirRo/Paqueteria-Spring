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

    //1. Constructor vacio
    public User() {
    }

    //2. Constructor con todos los atributos de la clase sin ID
    public User(String name, String surname, String lastname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //3. Constructor con todos los atributos de la clase y ID
    public User(long id, String name, String surname, String lastname, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //4. Constructor con todos los atributos de la clase y de relacion, pero sin ID
    public User(String name, String surname, String lastname, String email, String username, String password, Rol rol, List<Packet> packets) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.packets = packets;
    }

    //5. Constructor con todos los atributos de la clase y de relacion, pero con ID
    public User(long id, String name, String surname, String lastname, String email, String username, String password, Rol rol, List<Packet> packets) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.packets = packets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
