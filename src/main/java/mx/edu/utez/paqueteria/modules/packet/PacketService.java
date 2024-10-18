package mx.edu.utez.paqueteria.modules.packet;

import mx.edu.utez.paqueteria.modules.article.Article;
import mx.edu.utez.paqueteria.modules.article.ArticleService;
import mx.edu.utez.paqueteria.modules.packet.DTO.PacketDTO;
import mx.edu.utez.paqueteria.modules.user.UserService;
import mx.edu.utez.paqueteria.utils.CustomResponseEntity;
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
public class PacketService {
    //Inyecciones de servicios y repositorios
    @Autowired
    private PacketRepository packetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    //Metodos del servicio
    //Traer todos los paquetes
    public PacketDTO transformPacketToDTO(Packet p){
        return new PacketDTO(
                p.getId(),
                p.getOrderedOn(),
                p.isDelivered(),
                p.getStatus(),
                userService.transformUserToDTOForPacket(p.getUser()),
                articleService.transformArticlesToDTOsForPacket(p.getArticles())
        );
    }

    //Traer todos los paquetes por ID de usuario
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        List<PacketDTO> list = new ArrayList<>();
        String message = "";
        if(packetRepository.findAll().isEmpty()){
            message = "Aun no hay registros";
        }else{
            message = "Operacion exitosa";
            for(Packet p: packetRepository.findAll()){
                list.add(transformPacketToDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(message,"OK", 200, list);
    }

    //Traer un paquete por ID
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllIdUser(long idUser){
        List<PacketDTO> list = new ArrayList<>();
        String message ="";
        if(packetRepository.findAllByIdUser(idUser).isEmpty()){
            message = "Aun no hay registros";
        }else{
            message = "Operacion exitosa";
            for(Packet p : packetRepository.findAllByIdUser(idUser)){
                list.add(transformPacketToDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(message,"OK", 200, list);
    }

    //Guardar paquete
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long idPacket){
        PacketDTO dto = null;
        Packet found = packetRepository.findById(idPacket);
        String message = "";
        if(found == null){
            return customResponseEntity.get404Response();
        }else{
            message = "Operacion exitosa";
            dto = transformPacketToDTO(found);
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, dto);
    }

    //Actualizar Paquete
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Packet packet){
        Date currentDay = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("es-MX"));

        packet.setOrderedOn(sdf.format(currentDay));
        packet.setDelivered(false);
        packet.setStatus(PacketStatus.PENDING);

        try{
            for(Article a: packet.getArticles()) {
                articleService.decrementStockById(a.getId());
            }
            packetRepository.save(packet);
            return customResponseEntity.getOkResponse(
                    "Registro exitoso",
                    "CREATED",
                    201,
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }

    //Actualizar estado del paquete
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Packet packet){
        if(packetRepository.findById(packet.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                packetRepository.save(packet);
                return customResponseEntity.getOkResponse(
                        "Actualizacion exitosa",
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

    //Cambiar estado del paquete
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> changeStatus(Packet packet){
        if(packetRepository.findById(packet.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                packet.setDelivered(packet.getStatus() == PacketStatus.DELIVERED);
                packetRepository.changeStatus(
                        packet.isDelivered(),
                        packet.getStatus(),
                        packet.getId()
                );
                return customResponseEntity.getOkResponse(
                        "Actualizacion exitosa",
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

}
