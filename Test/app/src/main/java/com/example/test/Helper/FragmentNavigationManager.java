package com.example.test.Helper;





import androidx.fragment.app.FragmentManager;

import com.example.test.Interface.NavigationManager;
import com.example.test.MainActivity;

public class FragmentNavigationManager implements NavigationManager {
    private static FragmentNavigationManager mInstance;

    private static FragmentManager mFragmentManager;
    private MainActivity mainActivity;
    public static FragmentNavigationManager getInstance(MainActivity mainActivity){

        if(mInstance == null){
            mInstance = new FragmentNavigationManager();

            mInstance.configure(MainActivity);
            return mInstance;
        }

        public void configure(MainActivity mainActivity){
            mainActivity = mainActivity;

            mFragmentManager = mainActivity.getSupportFragmentManager();

        }
    }

    @Override
    public void showFragment(String title) {

    }
}
