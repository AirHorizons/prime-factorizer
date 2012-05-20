package com.prime.Factorizer;


import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class PrimeFactorizerActivity extends TabActivity {
	
	public static final int DIALOG_EXCEPTION_ERROR = 100;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); 
        TabHost tabHost = getTabHost(); 
        TabHost.TabSpec spec;  
        Intent intent;  
        intent = new Intent().setClass(this, SearchPrimeNumberActivity.class);  
        spec = tabHost.newTabSpec("primeNumber").setIndicator("소수 검색",
        		res.getDrawable(android.R.drawable.ic_lock_idle_alarm))  //res.getDrawable(R.drawable.ic_tab_artists))   
        		.setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SearchPrimeFactorActivity.class);    
        spec = tabHost.newTabSpec("primeFactor").setIndicator("소인수분해",                      
        		res.getDrawable(android.R.drawable.ic_media_ff))	//res.getDrawable(R.drawable.ic_tab_albums))                  
        		.setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
   
}

	
