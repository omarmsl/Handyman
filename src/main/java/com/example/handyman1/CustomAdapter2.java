package com.example.handyman1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter2 extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;

    public CustomAdapter2(Context c , JSONArray data){
        this.con = c;
        this.data = data;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public static class Holder{
        TextView brief;
        FloatingActionButton deleteservice , editservice;
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder Holder = new Holder();
        final View rowview;
        rowview = inflater.inflate(R.layout.layout,null);
        Holder.brief = rowview.findViewById(R.id.brief);
        Holder.deleteservice = rowview.findViewById(R.id.deleteservice);
        Holder.editservice = rowview.findViewById(R.id.editservice);
        JSONObject obj = data.optJSONObject(position);
        try {
            Holder.deleteservice.setTag(obj.getInt("Sid"));
            Holder.editservice.setTag(obj.getInt("Sid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Holder.deleteservice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = con.getString(R.string.url)+"deleteservice.php?id="+Holder.deleteservice.getTag();
            RequestQueue queue = Volley.newRequestQueue(con);
            StringRequest delete = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Service deleted")){
                        Toast.makeText(con.getApplicationContext(), "Service deleted", Toast.LENGTH_SHORT).show();
                        ((Service_provider)con).onResume();
                    }
                    else {
                        Toast.makeText(con.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(con.getApplicationContext(), "Try again...", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(delete);

        }

    });
        Holder.editservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con.getApplicationContext(),   Holder.deleteservice.getTag().toString(), Toast.LENGTH_SHORT).show();
               Intent ediit = new Intent(con, editservice.class);
               ediit.putExtra("Sid",Holder.deleteservice.getTag().toString());
              con.startActivity(ediit);
            }
        });



        try {
            Holder.brief.setText("Title: "+obj.getString("title") +"\nPrice: "+obj.getString("price")+"\nDescription: "+obj.getString("description")+"\nCreated_at: "+obj.getString("created_at"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
return rowview;
    }
}
