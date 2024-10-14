package mx.edu.utez.paqueteria.modules.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.paqueteria.modules.packet.Packet;

import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    //ATRIBUTOS PROPIOS DE LA CLASE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "on_stock", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private long onStock;

    @Column(name = "registered_on", nullable = false)
    private String registeredOn;

    //ATRIBUTOS DE RELACION
    @ManyToMany(mappedBy = "articles")
    @JsonIgnore
    private List<Packet> packets;

    //1. Constructor vacio
    public Article() {
    }

    //2. Constructor con todos los atributos de la clase sin ID
    public Article(String name, String description, long onStock, String registeredOn) {
        this.name = name;
        this.description = description;
        this.onStock = onStock;
        this.registeredOn = registeredOn;
    }

    //3. Constructor con todos los atributos de la clase y ID
    public Article(long id, String name, String description, long onStock, String registeredOn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.onStock = onStock;
        this.registeredOn = registeredOn;
    }

    //4. Constructor con todos los atributos de clase y de las relaciones sin ID
    public Article(String name, String description, long onStock, String registeredOn, List<Packet> packets) {
        this.name = name;
        this.description = description;
        this.onStock = onStock;
        this.registeredOn = registeredOn;
        this.packets = packets;
    }

    //5. Constructor con todos los atributos de clase y de las relaciones con ID
    public Article(long id, String name, String description, long onStock, String registeredOn, List<Packet> packets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.onStock = onStock;
        this.registeredOn = registeredOn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOnStock() {
        return onStock;
    }

    public void setOnStock(long onStock) {
        this.onStock = onStock;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }
}
