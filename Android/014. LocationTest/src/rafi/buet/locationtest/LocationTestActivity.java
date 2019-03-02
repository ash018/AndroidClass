/**
  * 22 July 2012
 */

package rafi.buet.locationtest;

import java.util.List;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class LocationTestActivity extends Activity {
	
	private LocationManager manager;
	private TextView output;
	private String bestProvider;
	private LocationListener listener;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        output = (TextView) findViewById(R.id.output_text);
        listener = new Listener();
        
        log("Location Providers:");
        dumpProviders();
        
        Criteria criteria = new Criteria();
        bestProvider = manager.getBestProvider(criteria, true);
        log("Best provider is: " + bestProvider);
        
        log("Locations (Starting with the last known):");
        Location location = manager.getLastKnownLocation(bestProvider);
        dumpLocation(location);
    }
    
    private void dumpLocation(Location location) {
    	if(location == null)
    		log("Location[unknown]");
    	else
    		log(location.toString() + "\n");
	}

	private void dumpProviders() {
		List<String> providers = manager.getAllProviders();
		for(String provider : providers)
			dumpProvider(provider);
	}

	private void dumpProvider(String provider) {
		String[] accuracy = {"invalid", "n/a", "fine", "coarse"};
		String[] power = {"invalid", "n/a", "low", "medium", "high"};
		
		LocationProvider info = manager.getProvider(provider);
		StringBuilder builder = new StringBuilder();
		
		builder.append("LocationProvider [")
			.append("name=")
			.append(info.getName())
			.append(", enabled=")
			.append(manager.isProviderEnabled(provider))
			.append(", accuracy=")
			.append(accuracy[info.getAccuracy() + 1])
			.append(", power requirement=")
			.append(power[info.getPowerRequirement() + 1])
			.append(", has monetary cost=")
			.append(info.hasMonetaryCost())
			.append(", requiresCell=")
			.append(info.requiresCell())
			.append(", requiresNetwork=")
			.append(info.requiresNetwork())
			.append(", requiresSatellite=")
			.append(info.requiresSatellite())
			.append(", supportsAltitude=")
			.append(info.supportsAltitude())
			.append(", supportsBearing=")
			.append(info.supportsBearing())
			.append(", supportsSpeed=")
			.append(info.supportsSpeed())
			.append("]\n");
		
		log(builder.toString());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		manager.requestLocationUpdates(bestProvider, 10000, 1, listener);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		manager.removeUpdates(listener);
	}

	private void log(String message) {
    	output.append(message + "\n");
    }
	
	private class Listener implements LocationListener {

		public void onLocationChanged(Location location) {
			dumpLocation(location);
		}

		public void onProviderDisabled(String provider) {
			log("Provider disabled: " + provider);
		}

		public void onProviderEnabled(String provider) {
			log("Provider enabled: " + provider);
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			String[] service = {"out of service", "temporary unavailable", "available"};
			
			log("Provider status changed: " + provider + ", status = " + 
					service[status] + ", extras = " + extras);
		}
		
	}
}