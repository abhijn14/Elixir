package com.rd.rushit.elixir;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    EditText name,email,dob,addr,blood,pass,cnfpass,mobile,userid;
    String url = "http://converse2k17.xyz/elixir/signup.php";
    Button singup;
    String b;
    RadioGroup g;
    RadioButton r;
    private View mProgressView;
    private View mLoginFormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dob = (EditText) findViewById(R.id.dob);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        addr = (EditText) findViewById(R.id.addr);
        blood = (EditText) findViewById(R.id.blood);
        pass = (EditText) findViewById(R.id.pass);
        cnfpass = (EditText) findViewById(R.id.cnfpass);
        mobile = (EditText) findViewById(R.id.mobile);
        userid = (EditText) findViewById(R.id.userid);
        g = (RadioGroup) findViewById(R.id.gen_g);
        singup = (Button) findViewById(R.id.signup);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        int d=dayOfMonth;
                        // make it like if s is less that 2 digit append 0 in a
                        String s1=String.valueOf(s),s2=String.valueOf(d);
                        if (s1.length()==1)
                        {
                            s1="0"+s1;
                        }
                        else
                        {
                            s1=String.valueOf(s);
                        }
                        if (s2.length()==1)
                        {
                            s2="0"+s2;
                        }
                        else
                        {
                            s2=String.valueOf(d);
                        }
                        b = year+"-"+s1+"-"+s2;
                        String a2 = s2+"-"+s1+"-"+year;
                        dob.setText(""+a2);
                        Log.d("logg date",a2+" "+b);

                    }
                };
                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(signup.this, dpd,1910,00,01);
                d.show();
            }
        });
        mProgressView = findViewById(R.id.regi_progress);
        mLoginFormView = findViewById(R.id.title_src1);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public void signup() {
        showProgress(true);
        int selectedId = g.getCheckedRadioButtonId();
        r = (RadioButton) findViewById(selectedId);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showProgress(false);
                        Log.d("loldd",response.toString());
                        if(response.toString().contains("true")) {
                            Toast.makeText(signup.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent si1 = new Intent(signup.this,Login.class);
                            startActivity(si1);
                        }
                        else {
                            Toast.makeText(signup.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("name",name.getText().toString());
                parameters.put("mobile",mobile.getText().toString());
                parameters.put("pass",pass.getText().toString());
                parameters.put("blood",blood.getText().toString());
                parameters.put("dob",b);
                parameters.put("email",email.getText().toString());

                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
