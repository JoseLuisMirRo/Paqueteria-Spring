package mx.edu.utez.paqueteria.modules.packet;

import mx.edu.utez.paqueteria.modules.user.User;
import mx.edu.utez.paqueteria.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class PacketController {
    //INYECCION DE SERVICIOS
    @Autowired
    private UserService userService;

    //ENDPOINTS
    //Traer todos los usuarios
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return userService.findAll();
    }

    //Traer todos los usuarios por id de rol
    @GetMapping("/rol/{idRol}")
    public ResponseEntity<?> findAllByIdRol(@PathVariable("idRol") int idRol){
        return userService.findAllByIdRol(idRol);
    }

    //Traer un usuario
    @GetMapping("/{idUser}")
    public ResponseEntity<?> findById(@PathVariable("idUser") long idUser) {
        return userService.findById(idUser);
    }

    //Guardar un usuario
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody User user) {
        return userService.save(user);
    }

    //Actualizar un usuario
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user) {
        return userService.update(user);
    }

    //Eliminar un usuario
    @DeleteMapping("")
    public ResponseEntity<?> deleteById(@RequestBody User user) {
        return userService.deleteById(user);
    }

}
