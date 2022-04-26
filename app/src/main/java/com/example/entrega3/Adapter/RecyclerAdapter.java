package com.example.entrega3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entrega3.R;
import com.example.entrega3.model.ItemList;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener{
    private List<ItemList> items;
    LayoutInflater inflater;

    //listener
    private View.OnClickListener  listener;
    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }



    public RecyclerAdapter(Context context, List<ItemList> items){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Llama a este método siempre que necesita crear una ViewHolder nueva.
        // El método crea el ViewHolder y su View asociada, y los inicializa,
        // pero no completa el contenido de la vista
        // (aún no se vinculó el ViewHolder con datos específicos).
        View view = inflater.inflate(R.layout.item_list_view,parent,false);
        view.setOnClickListener(this);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view,parent,false);
        //inflate para modificar la jerarquia de views, para poder utiliar diseños en otras views
        return new ViewHolder(view);
    }
    //el ViewHolder se utiliza para cada contenedor dentro de la vista de Recycler, para
    //vincular los datos
    // tenemos unos datos y queremos vincularlos con esas vistas, asi que se crea el adapter
    //public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Llama a este método para asociar una ViewHolder con los datos.
        // El método recupera los datos correspondientes y los usa
        // para completar el diseño del contenedor de vistas.
        String Titulo = items.get(position).getTitulo();
        String Release = items.get(position).getRelease();
        String Genrer = items.get(position).getGenrer();
        //Glide.with(getContext()).load(poster_paths.get(j)).into();
        holder.Titulo.setText(Titulo);
        holder.Release.setText(Release);
        holder.Genrer.setText(Genrer);
        //ItemList item = items.get(position);
        //holder.imgItem.setImageResource(item.getImgResource());
       // holder.Titulo.setText(item.getTitulo());
        //holder.Release.setText(item.getRelease());
        //holder.Genrer.setText(item.getGenrer());
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Titulo;
        private TextView Release;
        private TextView Genrer;
       // private ImageView poster;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            Titulo = itemView.findViewById(R.id.Titulo);
            Release = itemView.findViewById(R.id.Release);
            Genrer = itemView.findViewById(R.id.Genrer);
           // poster = itemView.findViewById(R.id.imgItem);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //RecyclerView llama a este método a fin de obtener el tamaño
    // del conjunto de datos

    public static class RecyclerHolder extends RecyclerView.ViewHolder{
        //private ImageView imgItem;
        private TextView Titulo;
        private TextView Release;
        private TextView Genrer;

        public RecyclerHolder(@NonNull View itemView){
            super(itemView);
            //imgItem = itemView.findViewById(R.id.imgItem);
            Titulo = itemView.findViewById(R.id.Titulo);
            Release = itemView.findViewById(R.id.Release);
            Genrer = itemView.findViewById(R.id.Genrer);
        }


    }
}
