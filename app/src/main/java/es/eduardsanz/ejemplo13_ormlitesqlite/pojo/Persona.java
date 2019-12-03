package es.eduardsanz.ejemplo13_ormlitesqlite.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable
public class Persona {

    @DatabaseField(generatedId = true, columnName = "id_persona")
    private int id;
    @DatabaseField(canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private String apellidos;
    @DatabaseField
    private int edad;
    @DatabaseField(unique = true, canBeNull = false)
    private String email;
// Claves AJENAS
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Usuario user;
    @ForeignCollectionField(eager = true, foreignFieldName = "persona")
    private ForeignCollection<Direccion> direcciones;





    public ForeignCollection<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ForeignCollection<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Persona(){}

    public Persona(String nombre, String apellidos, int edad, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
