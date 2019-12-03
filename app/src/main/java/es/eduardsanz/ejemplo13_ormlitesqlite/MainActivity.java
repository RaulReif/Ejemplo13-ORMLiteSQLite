package es.eduardsanz.ejemplo13_ormlitesqlite;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import es.eduardsanz.ejemplo13_ormlitesqlite.adapters.PersonasAdapter;
import es.eduardsanz.ejemplo13_ormlitesqlite.helpers.OrmHelper;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Direccion;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Persona;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Usuario;

public class MainActivity extends AppCompatActivity {

    // --- BD -----
    private OrmHelper helper;
    private Dao personasDAO;
    private Dao usuariosDAO;

    // ----- VISTA ------
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PersonasAdapter adapter;
    private int resource = R.layout.card_personas_layout;

    // ----- Datos ------
    private ArrayList<Persona> listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddPersonaActivity.class));
            }
        });

        // NOOOOOOO - Da error puntero a null
        // helper = new OrmHelper(this);
        helper = OpenHelperManager.getHelper(this, OrmHelper.class);
        try {
            personasDAO = helper.getPersonasDAO();
            usuariosDAO = helper.getUsuariosDAO();
            inicializaPersonas();
        } catch (SQLException e) {
            e.printStackTrace();
            listaPersonas = new ArrayList<>();
        }


        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);



    }

    private void inicializaPersonas() throws SQLException {
        for (int i = 0; i < 100; i++) {
            Persona p = new Persona("Nombre",
                    "Apellidos", i,
                    "email"+i+"@midominio.com");
            Usuario user = new Usuario("user"+i, "pass"+i);
            p.setUser(user);
            personasDAO.create(p);

            // Por cada persona voy a crear 10 direcciones
            // ForeignCollection -> null
            ForeignCollection<Direccion> direcciones = personasDAO.getEmptyForeignCollection("direcciones");
            for (int j = 0; j < 10; j++) {
                Direccion direccion = new Direccion("Calle "+i,
                        j, "4600"+i, "Valencia", p);
                direcciones.add(direccion);
            }
            p.setDirecciones(direcciones);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            listaPersonas = (ArrayList<Persona>) personasDAO.queryForAll();

            adapter = new PersonasAdapter(this, listaPersonas, resource);
            recyclerView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
