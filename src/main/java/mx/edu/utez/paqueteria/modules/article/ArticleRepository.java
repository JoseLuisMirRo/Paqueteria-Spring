package mx.edu.utez.paqueteria.modules.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //Traer todos los articulos
    List<Article> findAll();

    //Traer articulos por id
    Article findById(long idArticle);

    //Guardar/Actualizar articulos
    Article save(Article article);

    //Incrementar stock por cantidad
    @Modifying
    @Query(value="UPDATE article SET on_stock = on_stock + :quantity WHERE id = :idArticle; ", nativeQuery = true)
    void incrementStockByQuantity(@Param("quantity") long quantity, @Param("idArticle") long idArticle);

    //Decrementar stock por id del articulo
    @Modifying
    @Query(value="UPDATE artciel SET on_stock = on_stock -1 WHERE id = :idArticle;", nativeQuery = true)
    void decrementStockById(@Param("idArticle") long idArticle);
}
