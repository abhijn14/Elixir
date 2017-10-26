package com.rd.rushit.elixir;

//import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 22-09-2017.
 */

public class BloodFragment extends Fragment {

    ArrayList<String> namelist = new ArrayList<>();
    ArrayList<String> adress = new ArrayList<>();
    ArrayList<String> mobilenolist = new ArrayList<>();
    View myView;
    BottomNavigationView bottomNavigationView;
    String url = "http://prabhutibuildcone.co.in/elixir/pending.php";
    String name,address,mobileno,specialities;
    EditText search;
    ListView l;
    ImageView s;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.bloodfragment, container, false);
        s = (ImageView) myView.findViewById(R.id.src_btn);
        search = (EditText) myView.findViewById(R.id.search);
        l = (ListView) myView.findViewById(R.id.list_hospital);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                search.setText("");
                BloodavailableFragment bloodavailableFragment = new BloodavailableFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,bloodavailableFragment,bloodavailableFragment.getTag()).addToBackStack("abhisaarimg").commit();
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                pendingwise();
            }
        });
        return myView;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Blood Bank");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
    }
    public void pendingwise()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("sql1");
                            if (jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    name = ob.optString("name");
                                    address = ob.optString("address");
                                    mobileno = ob.optString("mobileno");

                                    namelist.add(name);
                                    adress.add(address);
                                    mobilenolist.add(mobileno);
                                }
                                MyAdapter adapter = new MyAdapter(getActivity(), namelist,adress,mobilenolist);
                                l.setAdapter(adapter);

                            } else {
                                Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                            }


                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        Log.d("log of date ",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("ch",search.getText().toString());



                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    class MyAdapter extends BaseAdapter {
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<String> adresslist = new ArrayList<>();
        ArrayList<String> mobilenolist = new ArrayList<>();
        LayoutInflater inflater = null;
        Context context;

        public MyAdapter(Context context, ArrayList<String> namelist,ArrayList<String> adresslist,ArrayList<String> mobilenolist) {
            this.namelist = namelist;
            this.adresslist = adresslist;
            this.mobilenolist = mobilenolist;
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

                vi = inflater.inflate(R.layout.custom_blood, null);

            TextView myName = (TextView) vi.findViewById(R.id.name);
            TextView myMobile = (TextView) vi.findViewById(R.id.mobile);
            TextView myaddress = (TextView) vi.findViewById(R.id.address);

            myName.setText(namelist.get(position));
            myMobile.setText(mobilenolist.get(position));
            myaddress.setText(adresslist.get(position));
            return vi;
        }
    }
}
