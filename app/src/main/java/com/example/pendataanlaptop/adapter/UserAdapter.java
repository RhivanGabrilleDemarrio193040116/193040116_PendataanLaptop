package com.example.pendataanlaptop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendataanlaptop.R;
import com.example.pendataanlaptop.database.entitas.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewAdapter> {
    private List<User> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.id.setText(String.valueOf(list.get(position).getId()));
        holder.merekLaptop.setText(list.get(position).getMerekLaptop());
        holder.tipeLaptop.setText(list.get(position).getTipeLaptop());
        holder.processor.setText(list.get(position).getProcessor());
        holder.harga.setText(String.valueOf(list.get(position).getHarga()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView id, merekLaptop, tipeLaptop, processor, harga;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            merekLaptop = itemView.findViewById(R.id.merek_laptop);
            tipeLaptop = itemView.findViewById(R.id.tipe_laptop);
            processor = itemView.findViewById(R.id.processor);
            harga = itemView.findViewById(R.id.harga);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
