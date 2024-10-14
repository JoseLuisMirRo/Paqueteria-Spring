package mx.edu.utez.paqueteria.modules.packet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacketRepository extends JpaRepository<Packet, Long> {
    //Traer todos los pacquetes
    List<Packet> findAll();

    //Traer paquetes de un usuario
    @Query(value = "SELECT * FROM packet WHERE id_user = :idUser", nativeQuery = true)
    List<Packet> findAllByIdUser(@Param("idUser") long idUser);

    //Traer paquete por id
    Packet findById(long idPacket);

    //Guardar/Actualizar paquetes
    Packet save(Packet packet);

    //Cambiar el estado del paquete
    @Modifying
    @Query(value = "UPDATE packet SET deliverded = :delivered, status = :status, WHERE id = :idPacket", nativeQuery = true)
    Packet changeStatus(
            @Param("deivered") boolean delivered,
            @Param("status") PacketStatus status,
            @Param("idPacket") long idPacket
    );
}
