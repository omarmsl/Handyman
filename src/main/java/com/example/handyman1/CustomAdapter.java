package com.example.handyman1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class CustomAdapter extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;

    public CustomAdapter(Context c, JSONArray data){
        this.con = c;
        this.data = data;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public static class Holder {
        TextView Name,created;
        ImageView image;
        FloatingActionButton layout;
    }
    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.fragment_1,null);
        holder.Name = rowView.findViewById(R.id.name);
        holder.created = rowView.findViewById(R.id.created);

        holder.image = rowView.findViewById(R.id.image);


        holder.layout = rowView.findViewById(R.id.layout);
        JSONObject obj = data.optJSONObject(i);
        try{
           Intent ress = new Intent(con , service_desc.class);
            ress.putExtra("serviceid", obj.getString("id"));
           ress.putExtra("userid" , obj.getString("service_provider_id"));
            holder.Name.setText(" "+obj.getString("f_name")+obj.getString("l_name")+"\n Description: "+obj.getString("description")+"\n Price: "+obj.getString("price"));
            holder.created.setText(obj.getString("created_at"));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  con.startActivity(ress);
                }
            });

        } catch (JSONException e) {
           Toast.makeText(con, "error", Toast.LENGTH_SHORT).show();
        } return rowView;
    }
}
