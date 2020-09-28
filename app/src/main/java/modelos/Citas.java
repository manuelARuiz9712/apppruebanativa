package modelos;

public class Citas {

    private int cupos_disponibles;
    private int cupos_totales;
    private String descripcion;
    private int cod_usuario_prestador;
    private String usuario;

    public Citas(){

    }

    public int getCupos_disponibles() {
        return cupos_disponibles;
    }

    public void setCupos_disponibles(int cupos_disponibles) {
        this.cupos_disponibles = cupos_disponibles;
    }

    public int getCupos_totales() {
        return cupos_totales;
    }

    public void setCupos_totales(int cupos_totales) {
        this.cupos_totales = cupos_totales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCod_usuario_prestador() {
        return cod_usuario_prestador;
    }

    public void setCod_usuario_prestador(int cod_usuario_prestador) {
        this.cod_usuario_prestador = cod_usuario_prestador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
