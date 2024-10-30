package mx.edu.utez.paqueteria.auth;

import mx.edu.utez.paqueteria.auth.DTO.AuthLoginDTO;
import mx.edu.utez.paqueteria.modules.user.User;
import mx.edu.utez.paqueteria.modules.user.UserDetailsImpl;
import mx.edu.utez.paqueteria.modules.user.UserRepository;
import mx.edu.utez.paqueteria.utils.CustomResponseEntity;
import mx.edu.utez.paqueteria.utils.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Autowired
    private JWTUtil jwtUtil;

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(AuthLoginDTO authLoginDTO) {
        User found = userRepository.findByPasswordAndEmailOrUsername(
                authLoginDTO.getPassword(),
                authLoginDTO.getUser()
        );
        if(found == null) {
            return customResponseEntity.get404Response();
        }else{
            try{
                UserDetails userDetails = new UserDetailsImpl(found);
                return customResponseEntity.getOkResponse(
                        "Inicio de sesion exitoso",
                        "OK",
                        200,
                        jwtUtil.generateToken(userDetails)
                );
            }catch(Exception e) {
                e.printStackTrace();
                return customResponseEntity.get400Response();
            }
        }
    }
}
