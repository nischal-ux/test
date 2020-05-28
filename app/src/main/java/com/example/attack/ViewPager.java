package com.example.attack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPager extends AppCompatActivity {
    TextView tab1,tab2;
    String id;

    androidx.viewpager.widget.ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        ActionBar actionBar;
        id=getIntent().getStringExtra("email");
        actionBar =getSupportActionBar();

        // Define Colo getSupportActionBar()rDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
//
        pager=findViewById(R.id.page);
        pager.setAdapter(new hamroAdapter(getSupportFragmentManager()));
    }
    public void tablistner(View v){

            if(v.getId()==R.id.tab1){
                    pager.setCurrentItem(0);
            }else if(v.getId()==R.id.tab1){
                    pager.setCurrentItem(1);

            }else{
                pager.setCurrentItem(2);
            }
    }
    public class hamroAdapter extends FragmentPagerAdapter{
        private ContentResolver contentResolver;
        protected hamroAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        public hamroAdapter(FragmentManager fm, ContentResolver contentResolver) {
            super(fm);
            this.contentResolver = contentResolver;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ArtFragment();

            }
          else{
                return new Music();


            }
        }
        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu1:

                Toast.makeText(this, "click1", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(),RegistrationActivity.class));

                break;
            case R.id.menu2:
                Toast.makeText(this, "click2", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
