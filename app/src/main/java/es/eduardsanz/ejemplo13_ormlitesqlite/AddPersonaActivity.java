package es.eduardsanz.ejemplo13_ormlitesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import es.eduardsanz.ejemplo13_ormlitesqlite.helpers.OrmHelper;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Persona;

public class AddPersonaActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellidos, txtEdad, txtEmail;
    private Button btnPersonaAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        txtNombre = findViewById(R.id.txtNombreAdd);
        txtApellidos = findViewById(R.id.txtApellidosAdd);
        txtEdad = findViewById(R.id.txtEdadAdd);
        txtEmail = findViewById(R.id.txtEmailAdd);
        btnPersonaAdd = findViewById(R.id.btnPersonaAdd);


        btnPersonaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtApellidos.getText().toString().isEmpty() &&
                        !txtEdad.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty())
                {
                    Persona persona =  new Persona(txtNombre.getText().toString(),
                                                txtApellidos.getText().toString(),
                                                Integer.parseInt(txtEdad.getText().toString()),
                                                txtEmail.getText().toString());
                    OrmHelper helper = OpenHelperManager
                            .getHelper(AddPersonaActivity.this, OrmHelper.class);
                    try {
                        Dao daoPersonas = helper.getPersonasDAO();
                        int numRows = daoPersonas.create(persona);
                        if (numRows == 1){
                            finish();
                        }
                        else{
                            Toast.makeText(AddPersonaActivity.this, "ERROR AL INSERTAR", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
