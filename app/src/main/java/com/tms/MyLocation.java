package com.tms;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//import android.content.Context;
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//
//public class MyLocation {
//	public static Timer timer1;
//	LocationManager lm;
//	LocationResult locationResult;
//	boolean gps_enabled = false;
//	boolean network_enabled = false;
//
//	/**
//	 * This method is used to get the user's location via the Location api.
//	 * 
//	 * @param context
//	 *            - the application context.
//	 * @param result
//	 *            - the location result.
//	 * 
//	 * @return true if the location was fetched, false otherwise.
//	 */
//
//	public boolean getLocation(Context context, LocationResult result) {
//		// I use LocationResult callback class to pass location value from
//		// MyLocation to user code.
//		this.locationResult = result;
//		if (lm == null)
//			lm = (LocationManager) context
//					.getSystemService(Context.LOCATION_SERVICE);
//
//		// exceptions will be thrown if provider is not permitted.
//		try {
//			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		try {
//			network_enabled = lm
//					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		// don't start listeners if no provider is enabled
//		if (!gps_enabled && !network_enabled)
//			return false;
//
//		if (network_enabled) {
//			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
//					locationListenerNetwork);
//		} else if (gps_enabled) {
//			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
//					locationListenerGps);
//		} else {
//			Criteria criteria = new Criteria();
//			criteria.setAccuracy(Criteria.ACCURACY_FINE);
//			String provider = lm.getBestProvider(criteria, true);
//			Location mLocation = lm.getLastKnownLocation(provider);
//			locationResult.gotLocation(mLocation);
//		}
//		timer1 = new Timer();
//		// timer1.schedule(new GetLastLocation(), 50);
//
//		return true;
//	}
//
//	LocationListener locationListenerGps = new LocationListener() {
//		public void onLocationChanged(Location location) {
//			if (location == null) {
//				timer1.schedule(new GetLastLocation(), 50);
//			} else {
//				timer1.cancel();
//				locationResult.gotLocation(location);
//				// lm.removeUpdates(this);
//				// lm.removeUpdates(locationListenerNetwork);
//			}
//		}
//
//		public void onProviderDisabled(String provider) {
//		}
//
//		public void onProviderEnabled(String provider) {
//		}
//
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//		}
//	};
//
//	LocationListener locationListenerNetwork = new LocationListener() {
//		public void onLocationChanged(Location location) {
//			if (location == null) {
//				timer1.schedule(new GetLastLocation(), 50);
//			} else {
//				timer1.cancel();
//				locationResult.gotLocation(location);
//				// lm.removeUpdates(this);
//				// lm.removeUpdates(locationListenerNetwork);
//			}
//			// lm.removeUpdates(this);
//			// lm.removeUpdates(locationListenerGps);
//		}
//
//		public void onProviderDisabled(String provider) {
//		}
//
//		public void onProviderEnabled(String provider) {
//		}
//
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//		}
//	};
//
//	class GetLastLocation extends TimerTask {
//		@Override
//		public void run() {
//			// lm.removeUpdates(locationListenerGps);
//			// lm.removeUpdates(locationListenerNetwork);
//
//			Location net_loc = null, gps_loc = null;
//			if (gps_enabled)
//				gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//			if (network_enabled)
//				net_loc = lm
//						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//			// if there are both values use the latest one
//			if (gps_loc != null && net_loc != null) {
//				if (gps_loc.getTime() > net_loc.getTime())
//					locationResult.gotLocation(gps_loc);
//				else
//					locationResult.gotLocation(net_loc);
//				return;
//			}
//			if (gps_loc != null) {
//				locationResult.gotLocation(gps_loc);
//				return;
//			}
//			if (net_loc != null) {
//				locationResult.gotLocation(net_loc);
//				return;
//			}
//			locationResult.gotLocation(null);
//		}
//	}
//}
