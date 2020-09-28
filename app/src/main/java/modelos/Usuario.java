package modelos;

public class Usuario {

    private  String usuario;
    private  int razonSocial;
    private  int codigoUsuario;
    private  int codRol;
    private  String token;

    public Usuario(){

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(int razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodRol() {
        return codRol;
    }

    public void setCodRol(int codRol) {
        this.codRol = codRol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
