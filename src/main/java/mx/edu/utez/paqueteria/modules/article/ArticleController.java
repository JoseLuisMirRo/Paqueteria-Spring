package mx.edu.utez.paqueteria.modules.article;

import mx.edu.utez.paqueteria.modules.article.DTO.ArticleQuantityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artcile")
public class ArticleController {
    //Inyeccion de servicios
    @Autowired
    private ArticleService articleService;

    //Endpoints
    //Traer todos los articulos
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return articleService.findAll();
    }

    //Taer articulo por id
    @GetMapping("/{idArticle}")
    public ResponseEntity<?> findById(@PathVariable("idArticle") long idArticle) {
        return articleService.findById(idArticle);
    }

    //Guardar articulo
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Article article) {
        return articleService.save(article);
    }

    //Actualizar articulo
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Article article) {
        return  articleService.update(article);
    }

    @PutMapping("/quantity")
    public ResponseEntity<?> incrementStockByQuantity(@RequestBody ArticleQuantityDTO articleQuantityDTO){
        return articleService.incrementByQuantity(articleQuantityDTO);
    }
}
