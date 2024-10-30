package mx.edu.utez.paqueteria.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    //INYECCION DE SERVICIOS
    @Autowired
    private UserService userService;

    //ENDPOINTS
    //Traer todos los usuarios
    @GetMapping("")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> findAll(){
        return userService.findAll();
    }

    //Traer todos los usuarios por id de rol
    @GetMapping("/rol/{idRol}")
    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
    public ResponseEntity<?> findAllByIdRol(@PathVariable("idRol") int idRol){
        return userService.findAllByIdRol(idRol);
    }

    //Traer un usuario
    @GetMapping("/{idUser}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> findById(@PathVariable("idUser") long idUser) {
        return userService.findById(idUser);
    }

    //Guardar un usuario
    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> save(@RequestBody User user) {
        return userService.save(user);
    }

    //Actualizar un usuario
    @PutMapping("")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> update(@RequestBody User user) {
        return userService.update(user);
    }

    //Eliminar un usuario
    @DeleteMapping("")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteById(@RequestBody User user) {
        return userService.deleteById(user);
    }
}
