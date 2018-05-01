package com.gnstkd95.cafego;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alfo6-7 on 2018-04-26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;
    Item item;

    int x,y;

    int pos;

    public RecyclerAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_item,parent,false);
        VH holder = new VH(itemView);
        return holder;
        //TODO 여기 부분 확인

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        pos = position;
        final VH vh = (VH)holder;

        item = items.get(position);

        vh.title.setText(item.getTitle());

        if (item.getLink() != null){
            vh.link.setText(item.getLink());
            Glide.with(context).load(R.drawable.golink).into(vh.golink);
            //TODO 여기확인
        }else{

        }

        vh.address.setText(item.getAddress());

        vh.roadaddress.setText(item.getRoadAddress());

        vh.telephone.setText(item.getTelephone());

        vh.category.setText(item.getCategory());

        x = item.getMapx();
        y = item.getMapy();
        final String weblink = items.get(position).link;
        final String link = items.get(position).thumbnail;
        if (link != null){
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(context).load(link).into(vh.thumbnail);
                        }
                    });
                }
            }.start();
        }else {
            Glide.with(context).load(R.drawable.no_image).into(vh.thumbnail);
        }

        vh.golink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "enter", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Recycler_FocusActivity.class);
                intent.putExtra("Link",weblink);
                intent.putExtra("X",x);
                intent.putExtra("Y",y);

                context.startActivity(intent);

            }
        });
//        vh.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(context, "enter", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,Recycler_FocusActivity.class);
//                intent.putExtra("Link",weblink);
//                intent.putExtra("X",x);
//                intent.putExtra("Y",y);
//
//                context.startActivity(intent);
//
//            }
//        });


    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView title;
        TextView link;
        TextView telephone;
        TextView address;
        TextView roadaddress;
        TextView category;
        ImageView thumbnail;
        ImageView golink;
        public VH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_cafename);
            link = itemView.findViewById(R.id.tv_link);
            telephone = itemView.findViewById(R.id.tv_tel);
            address = itemView.findViewById(R.id.tv_address);
            roadaddress = itemView.findViewById(R.id.tv_roadAddress);
            category = itemView.findViewById(R.id.tv_category);
            thumbnail = itemView.findViewById(R.id.iv);
            golink = itemView.findViewById(R.id.golink);
        }
    }






}
