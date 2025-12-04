package dto;

public class ContactoGrupoDTO {

    private int id_contacto;
    private int id_grupo;

    
    public ContactoGrupoDTO() {}
    
    public ContactoGrupoDTO(int id_contacto, int id_grupo) {
        this.id_contacto = id_contacto;
        this.id_grupo = id_grupo;

    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    @Override
    public String toString() {
        return "ContactoGrupoDTO [id_contacto=" + id_contacto + ", id_grupo=" + id_grupo + "]";
    }
}

    
    

