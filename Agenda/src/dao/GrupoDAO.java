package dao;

import dto.GrupoDTO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    public void create(GrupoDTO grupo) {
        String sql = "INSERT INTO grupo(nombre_grupo) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, grupo.getNombre_grupo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    grupo.setId_grupo(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GrupoDTO findById(int id) {
        String sql = "SELECT id_grupo, nombre_grupo FROM grupo WHERE id_grupo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GrupoDTO grupo = new GrupoDTO();
                    grupo.setId_grupo(rs.getInt("id_grupo"));
                    grupo.setNombre_grupo(rs.getString("nombre_grupo"));
                    return grupo;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GrupoDTO> findAll() {
        List<GrupoDTO> grupos = new ArrayList<>();
        String sql = "SELECT id_grupo, nombre_grupo FROM grupo";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GrupoDTO grupo = new GrupoDTO();
                grupo.setId_grupo(rs.getInt("id_grupo"));
                grupo.setNombre_grupo(rs.getString("nombre_grupo"));
                grupos.add(grupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public void update(GrupoDTO grupo) {
        String sql = "UPDATE grupo SET nombre_grupo = ? WHERE id_grupo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, grupo.getId_grupo());
            ps.setString(2, grupo.getNombre_grupo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlContacto_Grupo = "DELETE FROM contacto_grupo WHERE grupo_id = ?";
        String sqlGrupo = "DELETE FROM grupo WHERE grupo_id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            //Desactivar autocommit para controlar la transacción
            conn.setAutoCommit(false);

            try (PreparedStatement psFA = conn.prepareStatement(sqlContacto_Grupo);
                    PreparedStatement psA = conn.prepareStatement(sqlGrupo)) {

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

   /*  public List<GrupoDTO> findByNameOrLastName(String nombre) {
        List<GrupoDTO> grupos = new ArrayList<>();
        String sql = "SELECT id_grupo, nombre_grupo FROM grupo " +
                "WHERE nombre_grupo LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + nombre + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GrupoDTO grupo = new GrupoDTO();
                    grupo.setId_grupo(rs.getInt("id_grupo"));
                    grupo.setNombre_grupo(rs.getString("nombre_grupo"));
                    grupos.add(grupo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupos;
    }*/

}