package com.example.qamberhaider.mazdor_user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by qamber.haider on 5/23/2018.
 */

public class Pro_RecycleviewAdapter extends RecyclerView.Adapter<Pro_RecycleviewAdapter.ViewHolder> {

    private List<Listdata_Pro>  mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Pro_RecycleviewAdapter(Context context, List<Listdata_Pro> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Listdata_Pro info = mData.get(position);

        holder.vname.setText(info.getPRFirstName().toUpperCase());
        holder.vemail.setText(info.getPRDesignation().toUpperCase());
//        holder.vaddress.setText(info.getPREmail());
//        Log.v(info.getPRDesignation().toString())

//        holder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean Onclick) {
//
//                Listdata listdata1 = listdata.get(position);
//
//                Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext,Job_Details.class);
//                intent.putExtra(KEY_TITLE, listdata1.getName());
//                intent.putExtra(KEY_TYPE, listdata1.getEmail());
//                intent.putExtra(KEY_RATE, listdata1.getAddress());
//                //intent.putExtra(KEY_REGNO,dataModel.getRegno());
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vname , vemail;
        CircleImageView imgPro;

        ViewHolder(View itemView) {
            super(itemView);
            //imgPro=(CircleImageView)itemView.findViewById(R.id.proit);
            vname = (TextView) itemView.findViewById(R.id.emp_title);
            vemail = (TextView) itemView.findViewById(R.id.emp_typ);
            //vaddress = (TextView) itemView.findViewById(R.id.rate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    int getItem(int id) {
        return mData.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}