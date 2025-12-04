import java.util.List;
import java.util.Scanner;

import dao.ContactoDAO;
import dto.ContactoDTO;

public class Contacto {

    private static final Scanner sc = new Scanner(System.in);
    private static final ContactoDAO contactoDAO = new ContactoDAO();

    public static void main(String[] args) throws Exception {


        int opcion = 0;

        while (opcion != 6) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> listarActores();
                case 2 -> buscarActorPorId();
                case 3 -> crearActor();
                case 4 -> actualizarActor();
                case 5 -> borrarActor();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Contactos ---");
        System.out.println("1. Crear contacto");
        System.out.println("2. Listar  contactos");
        System.out.println("3. Buscar contactos");
        System.out.println("4. Modificar contacto");
        System.out.println("5. Eliminar contacto");
        System.out.println("6. Ver que a que grupos pertenece");
        System.out.println("7. Cargar un CSV de contactos");
        System.out.println("8. Engadir un usuario por id de usuario a un grupo por id de grupo. ");
        System.out.println("9. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void listarActores() {
        List<ContactoDTO> contactos = ContactoDTO.findAll();
        for(ContactoDTO contacto : contactos){
            System.out.println(contacto);
        }
    }

    private static void buscarActorPorId() {
        System.out.print("Introduce ID del contacto: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDTO.findById(id);
        if (contacto != null) {
            System.out.println(contacto);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void crearActor() {
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce email: ");
        String email = sc.nextLine();
        ContactoDTO nuevo = new ContactoDTO();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        contactoDAO.create(nuevo);
        System.out.println("Contacto creado con ID: " + nuevo.getContacto_id());
    }

    private static void actualizarActor() {
        System.out.print("Introduce ID del actor a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO actor = contactoDAO.findById(id);
        if (actor != null) {
            System.out.print("Nuevo nombre (" + actor.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nuevo email (" + actor.getEmail() + "): ");
            String apellido = sc.nextLine();
            actor.setNombre(nombre.isEmpty() ? actor.getNombre() : nombre);
            actor.setEmail(apellido.isEmpty() ? actor.getEmail() : apellido);
            contactoDAO.update(actor);
            System.out.println("Contacto actualizado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void borrarActor() {
        System.out.print("Introduce ID del Contacto a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        contactoDAO.delete(id);
        System.out.println("Contacto borrado si existía.");
    }
}
