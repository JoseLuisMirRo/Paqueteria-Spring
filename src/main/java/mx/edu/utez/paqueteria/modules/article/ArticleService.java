package mx.edu.utez.paqueteria.modules.article;

import mx.edu.utez.paqueteria.modules.article.DTO.ArticlePacketDTO;
import mx.edu.utez.paqueteria.modules.article.DTO.ArticleQuantityDTO;
import mx.edu.utez.paqueteria.utils.CustomResponseEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    public ArticlePacketDTO transformArticleToDTOForPacket(Article a){
        return new ArticlePacketDTO(
            a.getId(),
            a.getName(),
            a.getDescription()
        );
    }

    public List<ArticlePacketDTO> transformArticlesToDTOsForPacket(List<Article> articles) {
        List<ArticlePacketDTO> list = new ArrayList<>();
        for (Article a : articles) {
            list.add(transformArticleToDTOForPacket(a));
        }
        return list;
    }

    //--------METODOS DEL ERVICIO
    //Traer todos los articulos
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        List<Article> list = articleRepository.findAll();
        return customResponseEntity.getOkResponse(
                list.isEmpty() ? "Aun no hay registros" : "Operacion exitosa",
                "OK",
                200,
                list
        );
    }

    //Traer articulo por id
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long idArticle){
        Article found = articleRepository.findById(idArticle);
        if(found == null){
            return customResponseEntity.get404Response();
        }else{
            return customResponseEntity.getOkResponse(
                    "Operacion exitosa",
                    "OK",
                    200,
                    found
            );
        }
    }

    //Guardar articulo
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Article article){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("es-MX"));
        Date currentDay = new Date();

        try{
            article.setRegisteredOn(sdf.format(currentDay));
            articleRepository.save(article);
            return customResponseEntity.getOkResponse(
                    "Registro exitoso",
                    "CREATED",
                    201,
                    null
            );
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }

    //Actualizar articulo
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Article article){
        Article found = articleRepository.findById(article.getId());
        if(found == null){
            return customResponseEntity.get404Response();
        }else{
            try{
                articleRepository.save(article);
                return customResponseEntity.getOkResponse(
                        "Actualizacion exitosa",
                        "OK",
                        200,
                        null
                );
            } catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

    //Incrementar el stock de un articulo por una cantidad
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> incrementByQuantity(ArticleQuantityDTO data){
        if(articleRepository.findById(data.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                articleRepository.incrementStockByQuantity(data.getQuantity(), data.getId());
                return customResponseEntity.getOkResponse(
                        "Se aumento el stock del articulo",
                        "OK",
                        200,
                        null
                );
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

    //Decrementar el stock por id
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void decrementStockById(long idArticle){
        if(articleRepository.findById(idArticle)==null){
            System.out.println("El id del articulo no existe");
        } else {
            try{
                articleRepository.decrementStockById(idArticle);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }


}
