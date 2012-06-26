package net.sareweb.android.dBizi.activity;


import net.sareweb.android.dBizi.R;
import net.sareweb.android.dBizi.adapter.StationAdapter;
import net.sareweb.android.dBizi.model.City;
import net.sareweb.android.dBizi.util.CityUtil;
import net.sareweb.android.dBizi.util.DBiziConstants;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
public class StationListActivity extends Activity {
	
    private static String TAG = "StationListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_list);
        
        City c = CityUtil.initCity(DBiziConstants.IDIOMA_CAS);
        
        sAdapter = new StationAdapter(this, c);
        ListView list = (ListView)findViewById(android.R.id.list);
		list.setAdapter(sAdapter);
    }
    
    StationAdapter sAdapter;

}

