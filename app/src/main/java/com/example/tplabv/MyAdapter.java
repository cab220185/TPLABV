package com.example.tplabv;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {
     MyOnItemClick listener;
     List<Similar> lista ;
     static List<Similar> listaFull ;

    public MyAdapter (List<Similar> lista, MyOnItemClick listener){

        this.lista = lista;
        this.listaFull = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,
                viewGroup, false );
        MyViewHolder myViewHolder = new MyViewHolder(v,listener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
         Similar p = lista.get(i);
         myViewHolder.txtteaser.setText(p.getwTeaser());
         myViewHolder.txtTitle.setText(p.getName());
         myViewHolder.txttype.setText(p.getType());

   if ( p.getyUrl()!= "null"  ) {

                 if (p.getImagen()!= null) {
                        myViewHolder.imvPoster.setImageBitmap(BitmapFactory.decodeByteArray(p.getImagen(), 0, p.getImagen().length));
                    }

                        myViewHolder.displayYoutubeVideo.loadUrl("https://www.youtube.com/watch?v="+p.getyID());
                        myViewHolder.txtlinkyoutube.setText("Watch on YouTube App");

        }else {

                        myViewHolder.imvPoster.setVisibility(View.INVISIBLE);
                        myViewHolder.displayYoutubeVideo.setVisibility(View.INVISIBLE);
                        myViewHolder.txtlinkyoutube.setVisibility(View.INVISIBLE);
        }

        myViewHolder.setPosition(i);
    }
    @Override
    public int getItemCount() {
        return lista.size();

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter(){

                            @Override
                            protected FilterResults performFiltering(CharSequence constraint) {

                                List<Similar> listafiltrada = new ArrayList<>();
                                listafiltrada.clear();

                               if (listaFull.isEmpty()){
                                   listaFull.addAll(Lista.similares) ;}

                                if (constraint.toString().isEmpty()  || constraint.toString() =="" ) {

                                    listafiltrada.addAll(listaFull);

                                } else {

                                    for (Similar similar : listaFull) {
                                        if (similar.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                                            listafiltrada.add(similar);
                                        }

                                    }

                                }
                                FilterResults resultado = new FilterResults();
                                resultado.values = listafiltrada ;
                                return resultado ;
                            }
                            @Override
                            protected void publishResults(CharSequence constraint, FilterResults results) {
                             lista.clear();
                             lista.addAll( (List<Similar>) results.values);
                             notifyDataSetChanged();
                            }};
}