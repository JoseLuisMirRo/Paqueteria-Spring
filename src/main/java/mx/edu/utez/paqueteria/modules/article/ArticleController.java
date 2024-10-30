package mx.edu.utez.paqueteria.modules.article;

import mx.edu.utez.paqueteria.modules.article.DTO.ArticleQuantityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    //Inyeccion de servicios
    @Autowired
    private ArticleService articleService;

    //Endpoints
    //Traer todos los articulos
    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    public ResponseEntity<?> findAll() {
        return articleService.findAll();
    }

    //Taer articulo por id
    @GetMapping("/{idArticle}")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    public ResponseEntity<?> findById(@PathVariable("idArticle") long idArticle) {
        return articleService.findById(idArticle);
    }

    //Guardar articulo
    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
    public ResponseEntity<?> save(@RequestBody Article article) {
        return articleService.save(article);
    }

    //Actualizar articulo
    @PutMapping("")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
    public ResponseEntity<?> update(@RequestBody Article article) {
        return  articleService.update(article);
    }

    @PutMapping("/quantity")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
    public ResponseEntity<?> incrementStockByQuantity(@RequestBody ArticleQuantityDTO articleQuantityDTO){
        return articleService.incrementByQuantity(articleQuantityDTO);
    }
}
