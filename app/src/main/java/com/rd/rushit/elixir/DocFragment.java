package com.rd.rushit.elixir;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 22-09-2017.
 */

public class DocFragment extends Fragment {

    ArrayList<String> namelist = new ArrayList<>();
    String url = "http://converse2k17.xyz/elixir/doc.php";
    View myView;
    BottomNavigationView bottomNavigationView;
    ListView l;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.docfragment, container, false);
        l = (ListView) myView.findViewById(R.id.list_doc);
        namelist.add("Dr. Amit Shah");
        namelist.add("Dr. Indarjit Solanki");
        namelist.add("Dr. Babubhai Palsana");
        namelist.add("Dr. Anurag Kashyap");
        namelist.add("Dr. A Shiroya");
        namelist.add("Dr. Kiran Patel");
        MyAdapter adapter = new MyAdapter(getActivity(), namelist);
        l.setAdapter(adapter);
        //callweb();
        return myView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Doctors");
    }
    public  void callweb(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("data");

                            namelist.clear();
                            if (jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    String name = ob.optString("name");
                                    String mobile = ob.optString("mobile");
                                    String location = ob.optString("location");

                                    namelist.add(name);
                                }
                                MyAdapter adapter = new MyAdapter(getActivity(), namelist);
                                l.setAdapter(adapter);
                            } else {
                                Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("log of date ", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    class MyAdapter extends BaseAdapter {
        ArrayList<String> namelist = new ArrayList<>();
        //ArrayList<String> namelist = new ArrayList<>();
        LayoutInflater inflater = null;
        Context context;

        public MyAdapter(Context context, ArrayList<String> namelist) {
            this.namelist = namelist;
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return namelist.size();
        }

        @Override
        public Object getItem(int position) {
            return namelist;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View vi = convertView;
            if (convertView == null)

                vi = inflater.inflate(R.layout.custom_doc, null);

            TextView myName = (TextView) vi.findViewById(R.id.c_txt);


            myName.setText(namelist.get(position));
            return vi;
        }
    }
}
