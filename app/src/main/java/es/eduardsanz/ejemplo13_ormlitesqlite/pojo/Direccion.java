package es.eduardsanz.ejemplo13_ormlitesqlite.pojo;

import android.app.Person;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Direccion {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String calle;
    @DatabaseField(canBeNull = false)
    private int numero;
    @DatabaseField(canBeNull = false, columnName = "cod_postal")
    private String codPostal;
    @DatabaseField(canBeNull = false)
    private String ciudad;
// Claves Ajenas
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Persona persona;

    public int getId() {
        return id;
    }

    public Direccion() {
    }

    public Direccion(String calle, int numero, String codPostal, String ciudad, Persona persona) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.persona = persona;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
