package net.sareweb.android.dBizi.activity;


import java.util.List;

import net.sareweb.android.dBizi.R;
import net.sareweb.android.dBizi.model.City;
import net.sareweb.android.dBizi.model.Station;
import net.sareweb.android.dBizi.overlay.StationItemizedOverlay;
import net.sareweb.android.dBizi.overlay.StationOverlayItem;
import net.sareweb.android.dBizi.util.CityUtils;
import net.sareweb.android.dBizi.util.DBiziConstants;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class StationMapActivity extends MapActivity {
	
    private static String TAG = "StationListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_map);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    MapController controller =  mapView.getController();
	    controller.setCenter(getDefaultGeoPoint());
	    controller.setZoom(DBiziConstants.BDIZI_DEFAULT_ZOOM);
	    
	    mapOverlays = mapView.getOverlays();
	    city = CityUtils.initCity(DBiziConstants.IDIOMA_CAS);
	    loadStationsInMap();
    }
    
    private void loadStationsInMap(){
		Drawable drawable = this.getResources().getDrawable(android.R.drawable.btn_star);
	    itemizedoverlay = new StationItemizedOverlay(drawable, this);
	 
	    mapOverlays.clear();
	    
	    for (Station s : city.getStations()) {

			Double lat = s.getLatitud() * 1000000;
			Double lng = s.getLongitud() * 1000000;

			if (lat.intValue() != 0 && lng.intValue() != 0) {
				GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
				StationOverlayItem overlayitem = new StationOverlayItem(point,
						s.getNombre(), "", s);
				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);
			}
		}
	}
    
    private GeoPoint getDefaultGeoPoint(){
    	return new GeoPoint(DBiziConstants.BDIZI_DEFAULT_LAT, DBiziConstants.BDIZI_DEFAULT_LNG);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private City city;
	private List<Overlay> mapOverlays;
	private StationItemizedOverlay itemizedoverlay;

}

