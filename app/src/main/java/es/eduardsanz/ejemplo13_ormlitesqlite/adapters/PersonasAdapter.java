package es.eduardsanz.ejemplo13_ormlitesqlite.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.eduardsanz.ejemplo13_ormlitesqlite.R;
import es.eduardsanz.ejemplo13_ormlitesqlite.VerPersonaActivity;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Persona;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.Card> {

    private Context context;
    private ArrayList<Persona> listaPersonas;
    private int resource;

    public PersonasAdapter(Context context, ArrayList<Persona> listaPersonas, int resource) {
        this.context = context;
        this.listaPersonas = listaPersonas;
        this.resource = resource;
    }

    @NonNull
    @Override
    public Card onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardLayout = LayoutInflater.from(context).inflate(resource, null);
        Card card = new Card(cardLayout);
        return card;
    }

    @Override
    public void onBindViewHolder(@NonNull Card holder, int position) {
        final Persona p = listaPersonas.get(position);
        holder.txtNombre.setText(p.getNombre());
        holder.txtApellidos.setText(p.getApellidos());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", p.getId());
                Intent intent = new Intent(context, VerPersonaActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    class Card extends RecyclerView.ViewHolder{

        private TextView txtNombre, txtApellidos;
        private CardView cardView;

        public Card(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreCard);
            txtApellidos = itemView.findViewById(R.id.txtApellidosCard);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
