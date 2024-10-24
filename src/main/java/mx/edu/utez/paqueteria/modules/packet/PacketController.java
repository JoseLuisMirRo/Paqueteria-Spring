package mx.edu.utez.paqueteria.modules.packet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packet")
public class PacketController {
    @Autowired
    private PacketService packetService;

    //Endpoints
    //Traer todos los paquetes
    @GetMapping("")
    private ResponseEntity<?> findAll(){
        return packetService.findAll();
    }

    //Traer todos los paquetes por id de usuario
    @GetMapping("/user/{idUser}")
    private ResponseEntity<?> findAllByIdUser(@PathVariable("idUser") long idUser){
        return packetService.findAllIdUser(idUser);
    }

    //Traer paquete por id
    @GetMapping("/{idPacket}")
    private ResponseEntity<?> findById(@PathVariable("idPacket") long idPacket){
        return packetService.findById(idPacket);
    }

    //Guardar paquete
    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody Packet packet){
        return packetService.save(packet);
    }

    //Actualizar paquete
    @PutMapping("")
    private ResponseEntity<?> update(@RequestBody Packet packet){
        return packetService.update(packet);
    }

    //Actualizar estado del paquete
    @PutMapping("/status")
    private ResponseEntity<?> changeStatus(@RequestBody Packet packet){
        return packetService.changeStatus(packet);
    }
}
