package mx.edu.utez.paqueteria.modules.packet;

import jakarta.persistence.*;
import mx.edu.utez.paqueteria.modules.article.Article;
import mx.edu.utez.paqueteria.modules.user.User;

import java.util.List;

@Entity
@Table(name = "packet")
public class Packet {
    //ATRIBUTOS PROPIOS DE LA CLASE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ordered_on", nullable = false)
    private String orderedOn;

    @Column(name = "delivered", nullable = false)
    private boolean delivered;

    @Column(name = "status", nullable = true)
    private PacketStatus status;

    //ATRIBUTOS DE RELACION CON OTRAS COSAS XD
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "packet_has_articles",
            joinColumns = @JoinColumn(name = "id_packet"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles;
}
