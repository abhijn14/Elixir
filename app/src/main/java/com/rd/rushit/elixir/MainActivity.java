package com.rd.rushit.elixir;

//import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content,new HomeFragment(),"home").addToBackStack("home").commit();
                    getSupportActionBar().setTitle("Home");
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_doc:
                    transaction.replace(R.id.content,new DocFragment(),"doc").addToBackStack("doc").commit();
                    getSupportActionBar().setTitle("My Doctors");
                   // mTextMessage.setText(R.string.title_doc);
                    return true;
                case R.id.navigation_acc:
                    transaction.replace(R.id.content,new AccFragment(),"acc").addToBackStack("acc").commit();
                    getSupportActionBar().setTitle("My Account");
                    //mTextMessage.setText(R.string.title_myacc);
                    return true;
                case R.id.navigation_search:
                    transaction.replace(R.id.content,new SearchmainFragment(),"search").addToBackStack("search").commit();
                    getSupportActionBar().setTitle("Hospitals");
                    //mTextMessage.setText(R.string.title_myacc);
                    return true;
               /* case R.id.navigation_hospitals:
                    transaction.replace(R.id.content,new HospitalFragment(),"hospital").addToBackStack("hospital").commit();
                    getSupportActionBar().setTitle("Hospital");
                    //mTextMessage.setText(R.string.title_hospital);
                    return true;
                case R.id.navigation_stores:
                    transaction.replace(R.id.content,new StoresFragment(),"stores").addToBackStack("stores").commit();
                    getSupportActionBar().setTitle("Medical Stores");
                    //mTextMessage.setText(R.string.title_hospital);
                    return true;
                case R.id.navigation_blood:
                    transaction.replace(R.id.content,new BloodFragment(),"blood").addToBackStack("blood").commit();
                    getSupportActionBar().setTitle("Blood Banks");
                    //mTextMessage.setText(R.string.title_hospital);
                    return true;*/
                case R.id.navigation_faq:
                    transaction.replace(R.id.content,new FAQFragment(),"faq").addToBackStack("faq").commit();
                    getSupportActionBar().setTitle("FAQs");
                   // mTextMessage.setText(R.string.faq);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new HomeFragment(),"home").addToBackStack("home").commit();
        getSupportActionBar().setTitle("Home");
    }

}
