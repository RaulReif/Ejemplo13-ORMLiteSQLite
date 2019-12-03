package es.eduardsanz.ejemplo13_ormlitesqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.eduardsanz.ejemplo13_ormlitesqlite.R;
import es.eduardsanz.ejemplo13_ormlitesqlite.pojo.Direccion;

public class DireccionAdapter extends RecyclerView.Adapter<DireccionAdapter.DireccionHolder> {


    private Context context;
    private int resource;
    private ArrayList<Direccion> lista;

    public DireccionAdapter(Context context, int resource, ArrayList<Direccion> lista) {
        this.context = context;
        this.resource = resource;
        this.lista = lista;
    }

    @NonNull
    @Override
    public DireccionAdapter.DireccionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DireccionHolder(LayoutInflater.from(context).inflate(resource, null));
    }

    @Override
    public void onBindViewHolder(@NonNull DireccionAdapter.DireccionHolder holder, int position) {
        Direccion d = lista.get(position);

        holder.tvCalle.setText(d.getCalle());
        holder.tvNumero.setText(String.valueOf(d.getNumero()));
        holder.tvCodPostal.setText(d.getCodPostal());
        holder.tvCiudad.setText(d.getCiudad());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class DireccionHolder extends RecyclerView.ViewHolder {
        private TextView tvCalle, tvNumero, tvCodPostal, tvCiudad;
        public DireccionHolder(@NonNull View itemView) {
            super(itemView);
            tvCalle = itemView.findViewById(R.id.calleTvDireccion);
            tvNumero = itemView.findViewById(R.id.numeroTvDireccion);
            tvCodPostal = itemView.findViewById(R.id.codPostalTvDireccion);
            tvCiudad = itemView.findViewById(R.id.ciudadTvDireccion);
        }
    }
}
