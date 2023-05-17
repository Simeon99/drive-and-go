package com.metropolitan.cs330_projekat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.metropolitan.cs330_projekat.model.Korisnik;
import com.metropolitan.cs330_projekat.model.Voznja;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Voznja> {

    private Context mContext;
    int mResorce;
    List<Voznja> listaVoznje;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Voznja> objects) {
        super(context, resource, objects);
        mContext = context;
        mResorce = resource;
        listaVoznje= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int id = getItem(position).getId();
        String start = getItem(position).getStart();
        String cilj = getItem(position).getCilj();
        String automobil = getItem(position).getAutomobil();
        int brojSedista = getItem(position).getBrojSedista();
        double geografskaSirina = getItem(position).getGeografskaSirina();
        double geografskaDuzina = getItem(position).getGeografskaDuzina();
        String datum = getItem(position).getDatum();
        String vreme = getItem(position).getVreme();
        Korisnik k = getItem(position).getK();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResorce, parent, false);

        TextView tvStart=  convertView.findViewById(R.id.txStart);
        TextView tvCilj=  convertView.findViewById(R.id.txCilj);
        TextView tvAutomobil=  convertView.findViewById(R.id.txAutomobil);
        TextView tevBrojSedista= convertView.findViewById(R.id.txBrSedista);
        TextView tvDatum= convertView.findViewById(R.id.txDatum);
        TextView tvVreme = convertView.findViewById(R.id.txVreme);
        TextView tvImeKorisnika = convertView.findViewById(R.id.txIme);
        TextView tvPrezimeKorisnika = convertView.findViewById(R.id.txPrezime);
        TextView tvEmail = convertView.findViewById(R.id.txEmail);

        Voznja voznja = new Voznja(start,cilj,automobil,brojSedista,geografskaSirina,geografskaDuzina,datum,vreme,k);
        voznja.setId(id);




         tvStart.setText("Od: "+start);
         tvCilj.setText("Do: "+cilj);
         tvAutomobil.setText("Automobil: "+automobil);
         tevBrojSedista.setText("Broj sedišta: "+String.valueOf(brojSedista));
         tvDatum.setText("Datum: "+datum);
         tvVreme.setText("Vreme: "+vreme);
         tvImeKorisnika.setText(""+k.getIme());
         tvPrezimeKorisnika.setText(k.getPrezime());
         tvEmail.setText(k.getEmail());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,k.getIme(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(mContext, DetaljiVoznje.class);
                i.putExtra("voznja", voznja);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                mContext.startActivity(i);

            }
        });

        return convertView;
    }


}
