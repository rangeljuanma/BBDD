package com.example.bbdd_2.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbdd_2.R;
import com.example.bbdd_2.models.Empleado;

import java.util.List;


public class RVDepartamento extends RecyclerView.Adapter<RVDepartamento.ViewHolder> {

    private List<Empleado> mData;
    private LayoutInflater mInflater;
    private RVSalario.ItemClickListener mClickListener;

    public RVDepartamento(Context context, List<Empleado> mData) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View parten = mInflater.inflate(R.layout.recycler_view_item_depa, parent,false);
         return new ViewHolder(parten);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Empleado em = mData.get(position);
        Log.i("locura", em.getNombre());
        holder.nombreTextRecDepa.setText(em.getNombre());
        holder.depaTextRec.setText(em.getDepartamento());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombreTextRecDepa, depaTextRec;

        ViewHolder (View view){
            super(view);
            nombreTextRecDepa = view.findViewById(R.id.nombreTextoDep);
            depaTextRec = view.findViewById(R.id.depaRecycler);
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

    public void setClickListener(RVSalario.ItemClickListener itemClickListener){
        this.mClickListener= itemClickListener;
    }



}
