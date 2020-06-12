package com.example.attack.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.attack.ArtFragment;
import com.example.attack.Music;
import com.example.attack.R;

public class HomeFragment extends Fragment {
    TextView tab1,tab2,tab3;
    androidx.viewpager.widget.ViewPager pager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_view_pager, container, false);
        tab1=root.findViewById(R.id.tab1);
        tab2=root.findViewById(R.id.tab2);

        pager=root.findViewById(R.id.page);

        return root;
        }
    public void tablistner(View root){

        if(root.getId()==R.id.tab1){
            pager.setCurrentItem(0);
        }else if(root.getId()==R.id.tab1){
            pager.setCurrentItem(1);

        }else{
            pager.setCurrentItem(2);
        }
    }
    public HomeFragment(ViewPager pager) {
        this.pager = pager;
    }
    public class hamroAdapter extends FragmentPagerAdapter {

        protected hamroAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position==1){
                return new ArtFragment();
            }
            if(position==2){
                return new ArtFragment();
            }
            return new ArtFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
