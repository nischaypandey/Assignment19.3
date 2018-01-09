package com.example.vibhor.assignment193;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vibhor on 07-01-2018.
 */

//Class extending RecyclerView.Adapter.
public class DataAdaptor extends RecyclerView.Adapter<DataAdaptor.ViewHolder>{

    //creating fields.
    Context context;

    List<DataHandler> data;

    ClickListener clickListener;

    //Constructor.
    public  DataAdaptor(Context context, List<DataHandler> data){

        this.context=context;

        this.data=data;

    }


    @Override
    //Method to Create ViewHolder object.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    //Method to set Values to Views.
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.name.setText(data.get(position).getName());

        holder.vote.setText("Votes : "+data.get(position).getVote());

        holder.id.setText("Id : "+data.get(position).getId());

        //Setting onClickListener.
        holder.parent.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if(clickListener!=null){
                    clickListener.ItemClicked(v,position);
                }
            }
        });
    }

    @Override
    //Method to get number of Items.
    public int getItemCount()
    {
        return data.size();
    }

    //Creating nested class.
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Creating Fields.
        TextView name,vote,id;
        LinearLayout parent;

        //Constructor.
        public ViewHolder(View itemView) {
            super(itemView);

            //Setting IDs with Views.
            name= (TextView)itemView.findViewById(R.id.name);
            vote= (TextView)itemView.findViewById(R.id.vote);
            id= (TextView)itemView.findViewById(R.id.id);
            parent= (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }

    //Interface to Make clickListener.
    public interface ClickListener{
        void ItemClicked(View v, int position);
    }

    //Setting onClickListener.
    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
