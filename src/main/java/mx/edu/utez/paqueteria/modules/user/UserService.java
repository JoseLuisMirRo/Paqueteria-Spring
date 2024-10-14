package mx.edu.utez.paqueteria.modules.user;

import org.springframework.transaction.annotation.Transactional;
import mx.edu.utez.paqueteria.modules.user.DTO.UserDTO;
import mx.edu.utez.paqueteria.modules.user.DTO.UserPacketDTO;
import mx.edu.utez.paqueteria.utils.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    //Inyecciones de servicios y repositorios
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    //Metodos del servicio
    public UserDTO transformUserToDTO(User u){
        return new UserDTO(
            u.getId(),
            u.getName(),
            u.getSurname(),
            u.getLastname(),
            u.getEmail(),
            u.getUsername(),
            u.getRol()
        );
    }

    public UserPacketDTO transformUserToDTOForPacket(User u){
        return new UserPacketDTO(
            u.getId(),
            u.getName(),
            u.getSurname(),
            u.getLastname(),
            u.getEmail()
        );
    }

    //REGLAS DE NEOGICO
    //TRAER TODOS LOS USUARIOS
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        List<UserDTO> list = new ArrayList<>();
        String message = "";
        if(userRepository.findAll().isEmpty()) {
            message = "Aun no hay registros";
        } else {
            message = "Operacion exitosa";
            for(User u : userRepository.findAll()){
                list.add(transformUserToDTO(u));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }

    //TRAER TODOS LOS USUARIOS POR UN ROL
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllByIdRol(int idRol){
        List<UserDTO> list = new ArrayList<>();
        String message = "";
        if(userRepository.findAllByRol(idRol).isEmpty()) {
            message = "Aun no hay registros";
        } else {
            message = "Operacion exitosa";
            for(User u : userRepository.findAllByRol(idRol)){
                list.add(transformUserToDTO(u));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }

    //TRAER UN USUARIO POR ID
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long idUser){
        UserDTO dto = null;
        User found = userRepository.findById(idUser);
        String message = "";
        if(found == null){
            return customResponseEntity.get404Response();
        }else{
            message = "Operacion exitosa";
            dto = transformUserToDTO(found);

            return customResponseEntity.getOkResponse(message, "OK", 200, dto);
        }
    }

    //GUARDAR UN USUARIO
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(User user){
        try{
            userRepository.save(user);
            return customResponseEntity.getOkResponse(
                    "Registro exitoso",
                    "CREATED",
                    201,
                    null
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }

    //ACTUALIZAR UN USUARIO
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(User user){
        User found = userRepository.findById(user.getId());
        if(found == null){
            return customResponseEntity.get400Response();
        }else{
            try{
                user.setPassword(found.getPassword());
                userRepository.save(user);
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

    //ELIMINAR UN USUARIO
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> deleteById(User user){
        if(userRepository.findById(user.getId()) == null) {
            return customResponseEntity.get400Response();
        }else{
            try{
                userRepository.deleteById(user.getId());
                return customResponseEntity.getOkResponse(
                        "Eliminacion exitosa",
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
