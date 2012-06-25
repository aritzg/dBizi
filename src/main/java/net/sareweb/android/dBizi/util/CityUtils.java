package net.sareweb.android.dBizi.util;

import java.util.ArrayList;
import java.util.List;

import net.sareweb.android.dBizi.model.City;
import net.sareweb.android.dBizi.model.Station;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CityUtils {
	
	public static City initCity(){
		
		String serviceURL = composeServiceURL();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(serviceURL);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                String result = EntityUtils.toString(response.getEntity());
                Log.d(TAG, "Result: " + result);
                return initCityFromResult(result);
            }
			else{
				Log.e(TAG, "Not successful getting data");
				return null;
			}
		} 
		catch (Exception e) {
			Log.e(TAG, "Error requesting data", e);
			return null;
		}
	}
	
	
	private static String composeServiceURL(){
		return "http://" + DBiziConstants.SERVER + DBiziConstants.SERVICE + "?" + DBiziConstants.PARAM_CUR_CITY + "=" + DBiziConstants.BDIZI_CITY + "&" + DBiziConstants.PARAM_MODE + "=" + DBiziConstants.MODE_LOAD;
	}
	
	private static City initCityFromResult(String result){
		int initPos = 0;
		List<Station> stations = new ArrayList<Station>();
		
		initPos=result.indexOf("ÿ", initPos+1);
		while(initPos!=-1){
			int separator1 = result.indexOf("þ", initPos);
			if(separator1!=-1){
				int separator2=result.indexOf("þ", separator1+1);
				if(separator2!=-1){
					int separator3=result.indexOf("þ", separator2+1);
					if(separator3!=-1){
						String name=result.substring(separator1+1, separator2);
						String docks=result.substring(separator2+1, separator3);
						if(!name.equals(""))
							stations.add(new Station(name, docks));
					}
				}
			}
			initPos=result.indexOf("ÿ", initPos+1);
		}
		
		return new City(DBiziConstants.BDIZI_CITY_NAME, stations);
	}
	
	
	private static String TAG = "CityUtils";

}
