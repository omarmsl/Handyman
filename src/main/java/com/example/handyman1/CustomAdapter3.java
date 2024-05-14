package com.example.handyman1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter3 extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;

    public CustomAdapter3(Context c , JSONArray data){
        this.con = c;
        this.data = data;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public static class Holder{
        TextView info;
        RatingBar Bar;
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
        rowview = inflater.inflate(R.layout.frag,null);
        Holder.info = rowview.findViewById(R.id.informationnn);
        Holder.Bar = rowview.findViewById(R.id.Bar);
        JSONObject obj = data.optJSONObject(position);
        try {
            Holder.Bar.setTag(obj.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Holder.Bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        String url = con.getString(R.string.url)+"newrate.php?userid="+Holder.Bar.getTag()+"&rate="+rating;
        RequestQueue queue = Volley.newRequestQueue(con);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(con.getApplicationContext(), response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(con.getApplicationContext(), "Try again...", Toast.LENGTH_SHORT).show();

            }
        });

    }
});




        try {
            Holder.info.setText("Title: "+obj.getString("title") +"\nPrice: "+obj.getString("price")+"\nDescription: "+obj.getString("description")+"\nCreated_at: "+obj.getString("created_at"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
return rowview;
    }
}
