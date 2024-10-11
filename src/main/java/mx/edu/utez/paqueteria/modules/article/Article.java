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
}
