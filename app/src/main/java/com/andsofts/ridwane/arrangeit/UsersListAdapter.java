package com.andsofts.ridwane.arrangeit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    List<Users> usersList;

    public UsersListAdapter(List<Users> usersList){
        this.usersList=usersList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText("# "+usersList.get(position).getName());
        holder.location.setText("Location: "+usersList.get(position).getAddress());
        holder.email.setText("Email: "+usersList.get(position).getEmail());
        holder.phone.setText("Phone: "+usersList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mv;
        TextView name,location,email,phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mv=itemView;
            name=mv.findViewById(R.id.list_name);
            location=mv.findViewById(R.id.list_location);
            email=mv.findViewById(R.id.list_email);
            phone=mv.findViewById(R.id.list_phone);
        }
    }
}
