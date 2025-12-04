package dto;

public class ContactoDTO {

    private String nombre;
    private int telefono;
    private String email;
    private int contacto_id;

    
    public ContactoDTO() {}
    
    public ContactoDTO(String nombre, int telefono, String email, int contacto_id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.contacto_id = contacto_id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContacto_id() {
        return contacto_id;
    }

    public void setContacto_id(int contacto_id) {
        this.contacto_id = contacto_id;
    }

    @Override
    public String toString() {
        return "ContactoDTO [nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", contacto_id=" + contacto_id + "]";
    }
}
    

