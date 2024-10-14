package mx.edu.utez.paqueteria.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Traer todos los usuarios
    List<User> findAll();

    //Traer todos los usuarios por rol
    @Query(value = "SELECT * FROM user WHERE id_rol = :idRol", nativeQuery = true)
    List<User> findAllByRol(@Param("idRol") long idRol);

    //Taer el usuario por id
    User findById(long idUser);

    //Guardar/Actualizar usuario
    User save(User user);

    //Eliminar usuario
    @Modifying
    @Query(value = "DELETE FROM user WHERE id = :idUser", nativeQuery = true)
    void deleteById(@Param("idUser") long idUser);

}
