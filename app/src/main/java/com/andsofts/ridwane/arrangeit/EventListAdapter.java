package com.andsofts.ridwane.arrangeit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    List<Events> eventList;

    public EventListAdapter(List<Events> eventList){
        this.eventList=eventList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.event_type.setText("# "+eventList.get(position).getEvent_Type());
        holder.location.setText("Location: "+eventList.get(position).getLocation());
        holder.event_date.setText("Date: "+eventList.get(position).getDate());
        holder.contact_details.setText("Contact Details: "+eventList.get(position).getContact_Details());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mv;
        TextView event_type,location,event_date,contact_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mv=itemView;
            event_type=mv.findViewById(R.id.list_name);
            location=mv.findViewById(R.id.list_location);
            event_date=mv.findViewById(R.id.list_email);
            contact_details=mv.findViewById(R.id.list_phone);
        }
    }
}
