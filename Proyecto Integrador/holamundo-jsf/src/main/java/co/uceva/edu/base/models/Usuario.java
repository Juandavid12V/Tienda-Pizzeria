package co.uceva.edu.base.models;

public class Usuario {

    private  String login;
    private  String password;
    private  String nombre;
    private  String tipo;

    public String getTipo() {
        return tipo;
    }

    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
