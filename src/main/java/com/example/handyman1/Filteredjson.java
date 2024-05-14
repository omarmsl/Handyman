package com.example.handyman1;

import org.json.JSONArray;

public class Filteredjson {

    private static JSONArray xx;


    public static void setJson(JSONArray jsonaa){
       Filteredjson.xx = jsonaa;
    }
    public static JSONArray getJson(){
        return xx;
    }
}
