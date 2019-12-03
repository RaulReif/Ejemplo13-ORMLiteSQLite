package es.eduardsanz.ejemplo13_ormlitesqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.FontRequest;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.Inflater;

import es.eduardsanz.ejemplo13_ormlitesqlite.adapters.DireccionAdapter;
import es.eduardsanz.ejemplo13_ormlitesqlite.helpers.OrmHelper;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Direccion;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Persona;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Usuario;

public class VerPersonaActivity extends AppCompatActivity {

    private TextView lblNombre, lblApellidos, lblEdad, lblEmail;
    private Button btnAddUser;

    private OrmHelper helper;
    private Dao daoPersonas;
    private Dao daoUsuarios;
    private Persona p;

    //RecyclerView
    private RecyclerView containerDirecciones;
    private DireccionAdapter adapterDirecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona);

        lblNombre = findViewById(R.id.lblNombreVer);
        lblApellidos = findViewById(R.id.lblApellidosVer);
        lblEdad = findViewById(R.id.lblEdadVer);
        lblEmail = findViewById(R.id.lblEmailVer);
        btnAddUser = findViewById(R.id.btnAddUserVer);
        containerDirecciones = findViewById(R.id.direccionesRecyclerVerPersona);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser().show();
            }
        });

        if (getIntent().getExtras() != null){
            int id = getIntent().getExtras().getInt("id");
            helper = OpenHelperManager.getHelper(this, OrmHelper.class);
            try {
                daoPersonas = helper.getPersonasDAO();
                p = (Persona) daoPersonas.queryForId(id);
                lblNombre.setText(p.getNombre());
                lblApellidos.setText(p.getApellidos());
                lblEdad.setText(String.valueOf(p.getEdad()));
                lblEmail.setText(p.getEmail());

                ArrayList<Direccion> listaDirecciones = new ArrayList<>(p.getDirecciones());
                adapterDirecciones = new DireccionAdapter(this, R.layout.card_direcciones_layout, listaDirecciones);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                containerDirecciones.setLayoutManager(layoutManager);
                containerDirecciones.setAdapter(adapterDirecciones);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private AlertDialog addUser(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View alertView = inflater.inflate(R.layout.add_user_layout, null);
        final EditText txtUser = alertView.findViewById(R.id.txtUserAddUser);
        final EditText txtPass1 = alertView.findViewById(R.id.txtPass1AddUser);
        final EditText txtPass2 = alertView.findViewById(R.id.txtPass2AddUser);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertView);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancelar", null);
        alert.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!txtUser.getText().toString().isEmpty() &&
                    !txtPass1.getText().toString().isEmpty() &&
                    txtPass1.getText().toString().equals(txtPass2.getText().toString())){
                    Toast.makeText(VerPersonaActivity.this, "ESTOY EN EL IF", Toast.LENGTH_SHORT).show();
                    Usuario usuario = new Usuario(txtUser.getText().toString(), txtPass1.getText().toString());
                    p.setUser(usuario);
                    try {
                        daoUsuarios = helper.getUsuariosDAO();
                        daoUsuarios.create(p.getUser());
                        daoPersonas.update(p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(VerPersonaActivity.this, "ERROR EN EL IF", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return alert.create();

    }
}
