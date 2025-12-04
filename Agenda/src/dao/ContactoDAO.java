package dao;

import dto.ContactoDTO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    public void create(ContactoDTO contacto) {
        String sql = "INSERT INTO contacto(nombre, telefono, email) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contacto.getNombre());
            ps.setInt(1, contacto.getTelefono());
            ps.setString(1, contacto.getEmail());
            ps.setInt(1, contacto.getContacto_id());


            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    //contacto.setCategory_id(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContactoDTO findById(int id) {
        String sql = "SELECT ID_Contacto, nombre, telefono, email FROM category WHERE ID_Contacto = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setContacto_id(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    contacto.setTelefono(rs.getInt("Telefono"));
                    contacto.setEmail(rs.getString("Email"));
                    return contacto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContactoDTO> findAll() {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT ID_Contacto, Nombre, Telefono, email FROM contacto";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ContactoDTO contacto = new ContactoDTO();
                contacto.setContacto_id(rs.getInt("ID_Contacto"));
                contacto.setNombre(rs.getString("Nombre"));
                contacto.setTelefono(rs.getInt("Telefono"));
                contactos.add(contacto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    public void update(ContactoDTO contacto) {
        String sql = "UPDATE contacto SET ID_Contacto = ?, Nombre = ?, Telefono = ?, Email = ? WHERE ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, contacto.getContacto_id());
            ps.setString(2, contacto.getNombre());
            ps.setInt(3, contacto.getTelefono());            
            ps.setString(3, contacto.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlContacto = "DELETE FROM contacto WHERE ID_Contacto = ?";
        String sqlContactoGrupo = "DELETE FROM grupo WHERE ID_Grupo = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            //Desactivar autocommit para controlar la transacción
            conn.setAutoCommit(false);

            try (PreparedStatement psFA = conn.prepareStatement(sqlContacto);
                    PreparedStatement psA = conn.prepareStatement(sqlContactoGrupo)) {

                // 1. Borrar relaciones en film_actor
                psFA.setInt(1, id);
                psFA.executeUpdate();


                // 2. Borrar el actor
                psA.setInt(1, id);
                psA.executeUpdate();


                //Confirmar todo
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();// Deshacer si algo falla
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ContactoDTO> findByNameOrLastName(String nombre) {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM actor " +
                "WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + nombre + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setContacto_id(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    contacto.setTelefono(rs.getInt("Telefono"));
                    contacto.setEmail(rs.getString("Email"));
                    contactos.add(contacto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactos;
    }

}
