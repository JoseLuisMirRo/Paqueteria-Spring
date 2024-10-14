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

    //1. Constructor vacio
    public Packet() {
    }

    //2. Constructor con todos los atributos de la clase sin ID
    public Packet(String orderedOn, boolean delivered, PacketStatus status) {
        this.orderedOn = orderedOn;
        this.delivered = delivered;
        this.status = status;
    }

    //3. Constructor con todos los atributos de la clase y ID
    public Packet(Long id, String orderedOn, boolean delivered, PacketStatus status) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.delivered = delivered;
        this.status = status;
    }

    //4. Constructor con todos los atributos de clase y de las relaciones sin ID
    public Packet(String orderedOn, boolean delivered, PacketStatus status, User user, List<Article> articles) {
        this.orderedOn = orderedOn;
        this.delivered = delivered;
        this.status = status;
        this.user = user;
        this.articles = articles;
    }

    //5. Constructor con todos los atributos de clase y de las relaciones con ID
    public Packet(Long id, String orderedOn, boolean delivered, PacketStatus status, User user, List<Article> articles) {
        this.id = id;
        this.orderedOn = orderedOn;
        this.delivered = delivered;
        this.status = status;
        this.user = user;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public PacketStatus getStatus() {
        return status;
    }

    public void setStatus(PacketStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
