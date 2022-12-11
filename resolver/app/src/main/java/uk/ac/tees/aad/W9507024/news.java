package uk.ac.tees.aad.W9507024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class news extends AppCompatActivity {

    RequestQueue queue ;
    ArrayList<NewsObj> news;

    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        queue= Volley.newRequestQueue(getApplicationContext());
        news = new ArrayList<NewsObj>();

        String url = "https://newslit-news-search.p.rapidapi.com/news?q=drugs%20spike";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                       JsonArray arr =  jsonObject.get("results").getAsJsonObject().get("stories").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                           JsonObject obj = arr.get(i).getAsJsonObject();
                           try{
                               String image =  obj.get("image_url").toString();
                               String title =  obj.get("title").toString();
                               String des =  obj.get("excerpt").toString();
                               news.add(new NewsObj(image,title,des));
                           }catch(Exception e)
                           {

                           }


                        }
                      showout(news.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }}){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "ba3d3c59cdmshdeb594ffe544049p1af077jsn62b4e2e5ab05");
                params.put("X-RapidAPI-Host", "newslit-news-search.p.rapidapi.com");
                return params;
            }
        };
        queue.add(stringRequest);






    }

    void showout(String msh)
    {
        simpleList = (ListView) findViewById(R.id.list_news);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), news);
        simpleList.setAdapter(customAdapter);
    }
}

class NewsObj{
    String image;
    String title;
    String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NewsObj(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public NewsObj() {
    }

    @Override
    public String toString() {
        return "News{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

 class CustomAdapter extends BaseAdapter {
    Context context;
    String countryList[];
    int flags[];
    LayoutInflater inflter;
    ArrayList<NewsObj> newsObj;

    public CustomAdapter(Context applicationContext, String[] countryList, int[] flags) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public CustomAdapter(Context applicationContext, ArrayList<NewsObj> newsObj ) {
        this.newsObj = newsObj;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return newsObj.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_for_list, null);

        TextView country = (TextView) view.findViewById(R.id.title_text);
        ImageView icon = (ImageView) view.findViewById(R.id.image_for_item);
        TextView couns = (TextView) view.findViewById(R.id.description_text);

        country.setText(newsObj.get(i).getTitle().replace("\"",""));
        Glide.with(view.getContext().getApplicationContext()).load(newsObj.get(i).getImage().replace("\"","")).into(icon);
        couns.setText(newsObj.get(i).getDescription().replace("\"",""));
        return view;
    }
}