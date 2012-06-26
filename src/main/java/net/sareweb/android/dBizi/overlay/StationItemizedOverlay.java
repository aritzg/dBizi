package net.sareweb.android.dBizi.overlay;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;

public class StationItemizedOverlay extends ItemizedOverlay<StationOverlayItem> {

	private ArrayList<StationOverlayItem> mOverlayItems = new ArrayList<StationOverlayItem>();
	Context mContext;

	public StationItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public StationItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(StationOverlayItem overlay) {
		mOverlayItems.add(overlay);
		populate();
	}

	@Override
	protected StationOverlayItem createItem(int i) {
		return mOverlayItems.get(i);
	}

	@Override
	public int size() {
		return mOverlayItems.size();
	}

	
}
