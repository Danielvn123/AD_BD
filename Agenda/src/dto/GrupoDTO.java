package dto;

public class GrupoDTO {

    private String nombre_grupo;
    private int id_grupo;

    public GrupoDTO() {}
    
    public GrupoDTO(String nombre_grupo, int id_grupo) {
        this.nombre_grupo = nombre_grupo;
        this.id_grupo = id_grupo;

    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    @Override
    public String toString() {
        return "GrupoDTO [nombre_grupo=" + nombre_grupo + ", id_grupo=" + id_grupo + "]";
    }
}

    

