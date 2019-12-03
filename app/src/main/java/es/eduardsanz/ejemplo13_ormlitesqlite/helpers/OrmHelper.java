package es.eduardsanz.ejemplo13_ormlitesqlite.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import es.eduardsanz.ejemplo13_ormlitesqlite.config.Configuracion;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Direccion;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Persona;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Usuario;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Persona, Integer> personasDAO;
    private Dao<Usuario, Integer> usuariosDAO;
    private Dao<Direccion, Integer> direccionesDAO;

    public OrmHelper(Context context) {
        super(context, Configuracion.BD_NAME, null, Configuracion.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Persona.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Direccion.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Persona.class, true);
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            TableUtils.createTable(connectionSource, Persona.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Direccion.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Persona, Integer> getPersonasDAO() throws SQLException {
        if (personasDAO == null)
            personasDAO = getDao(Persona.class);
        return personasDAO;
    }

    public Dao<Usuario, Integer> getUsuariosDAO() throws SQLException {
        if (usuariosDAO == null)
            usuariosDAO = getDao(Usuario.class);
        return usuariosDAO;
    }

    public Dao<Direccion, Integer> getDireccionesDAO() throws SQLException {
        if (direccionesDAO == null)
            direccionesDAO = getDao(Direccion.class);
        return direccionesDAO;
    }
}
