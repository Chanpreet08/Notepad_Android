package com.notepad.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notepad.Activity.MainActivity;
import com.notepad.Model.Data;
import com.notepad.R;

import java.util.ArrayList;

/**
 * Created by cc on 22/5/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Data> dataList = new ArrayList<>();


    public RecyclerAdapter(Context context,ArrayList<Data> dataList)
    {
        this.context=context;
        this.dataList = dataList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.content.setText(dataList.get(position).getContent());
        holder.view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Data data = dataList.get(position);
                        Dbhelper dbhelper = new Dbhelper(context);
                        dbhelper.deleteRow(data.getId()+"");
                        dataList = dbhelper.getData();
                        notifyDataSetChanged();
                        return false;
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView content;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view=  itemView;
            title = (TextView) itemView.findViewById(R.id.recycler_title);
            content = (TextView) itemView.findViewById(R.id.recycler_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}
