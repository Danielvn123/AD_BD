import dao.GrupoDAO;
import dto.GrupoDTO;
import java.util.List;
import java.util.Scanner;

public class Grupo {

/*Facer o CRUD de category
-Engadir film_category para o borrado+
-Facelo nunha sola transacción*/

    private static final Scanner sc = new Scanner(System.in);
    private static final GrupoDAO grupoDAO = new GrupoDAO();

    public static void main(String[] args) throws Exception {


        int opcion = 0;

        while (opcion != 6) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> listarCategorias();
                case 2 -> buscarCategoriaPorId();
                case 3 -> crearCategoria();
                case 4 -> actualizarCategoria();
                case 5 -> borrarCategoria();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Grupo ---");
        System.out.println("1. Crear grupo");
        System.out.println("2. Listar grupos");
        System.out.println("3. Modificar grupo");
        System.out.println("4. Eliminar grupo");
        System.out.println("5. Ver que contactos hai dentro dun grupo");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void listarCategorias() {
        List<GrupoDTO> grupos = grupoDAO.findAll();
        for(GrupoDTO grupo : grupos){
            System.out.println(grupo);
        }
    }

    private static void buscarCategoriaPorId() {
        System.out.print("Introduce ID de la grupo: ");
        int id = sc.nextInt();
        sc.nextLine();
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo != null) {
            System.out.println(grupo);
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    private static void crearCategoria() {
        System.out.print("Introduce nombre: ");
        String name = sc.nextLine();
        System.out.print("Introduce ultima actualización: ");
        GrupoDTO nuevo = new GrupoDTO();
        nuevo.setNombre_grupo(name);
        grupoDAO.create(nuevo);
        System.out.println("Grupo creada con ID: " + nuevo.getId_grupo());
    }

    private static void actualizarCategoria() {
        System.out.print("Introduce ID de la grupo a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo != null) {
            System.out.print("Nuevo nombre (" + grupo.getName() + "): ");
            String name = sc.nextLine();
            System.out.print("Nueva ultima actualización (" + grupo.getLast_update() + "): ");
            String last_update = sc.nextLine();
            grupo.setName(name.isEmpty() ? grupo.getName() : name);
            grupoDAO.update(grupo);
            System.out.println("Grupo actualizado.");
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    private static void borrarCategoria() {
        System.out.print("Introduce ID de la grupo a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        grupoDAO.delete(id);
        System.out.println("Grupo borrada si existía.");
    }
}
