package es.eduardsanz.ejemplo13_ormlitesqlite.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Usuario {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String user;
    @DatabaseField(canBeNull = false)
    private String password;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
