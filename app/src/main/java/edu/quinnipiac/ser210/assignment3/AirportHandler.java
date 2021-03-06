package edu.quinnipiac.ser210.assignment3;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class AirportHandler {
    private static final int FIRST_AIRPORT = '0';
    private static final int LAST_AIRPORT = '7';
    final public static String[] airports = new String[LAST_AIRPORT - FIRST_AIRPORT + 1];

    //Populates the airport array with airports from API
    public AirportHandler() {
        for (int i = 0; i <= 7; i++) {
            airports[i] = Integer.toString(i);
        }
      /*  Log.d("DEBUG: AirportHandler","Airport Array is population");
        Log.d("DEBUG: AirportHandler", airports[3]);*/
    }

    //Pulls the details of the selected airport and puts them into a JSON object, turns the JSON object into a string to return
    public static String getAirportsDetails(String totalDetails) throws JSONException {
        JSONObject airDetailObj = new JSONObject(totalDetails);
        String code = airDetailObj.getString("code");
        String name = airDetailObj.getString("name");
        String city = airDetailObj.getString("city");
        String state = airDetailObj.getString("state");
        String latitude = airDetailObj.getString("latitude");
        String longitude = airDetailObj.getString("longitude");
        String precheck = airDetailObj.getString("precheck");

        String details = "Code: "+code+"\nName: "+name+"\nCity: "+city+"\nState: "+state+"\nLatitude: "+latitude+"\nLongitude: "+longitude+"\nPre-check: "+precheck;
       /* Log.d("DEBUG: AirportHandler", details);*/
        return details;


    }
}
