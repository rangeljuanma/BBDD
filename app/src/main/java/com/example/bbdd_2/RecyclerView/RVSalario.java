package com.example.bbdd_2.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbdd_2.R;
import com.example.bbdd_2.models.Empleado;

import java.util.List;


public class RVSalario extends RecyclerView.Adapter<RVSalario.ViewHolder> {

    private List<Empleado> mData;
    private LayoutInflater mInflater;
    private ItemClickListener  mClickListener;

    public RVSalario(Context context, List<Empleado> mData) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View parten = mInflater.inflate(R.layout.recycler_view_item, parent,false);
         return new ViewHolder(parten);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Empleado em = mData.get(position);
        holder.nombreTextRec.setText(em.getNombre());
        holder.salarioTextRec.setText(String.valueOf(em.getSalario()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombreTextRec, salarioTextRec;

        ViewHolder (View view){
            super(view);
            nombreTextRec = view.findViewById(R.id.nombreTextRec);
            salarioTextRec = view.findViewById(R.id.salarioTextRec);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mClickListener!= null)
                mClickListener.onItemClick(view,getAdapterPosition());
        }
    }

    Empleado getItem(int id){
       return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener= itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View activista, int posicion);
    }


}
