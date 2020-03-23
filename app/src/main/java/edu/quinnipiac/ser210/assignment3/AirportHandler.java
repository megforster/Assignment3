package edu.quinnipiac.ser210.assignment3;

import org.json.JSONException;
import org.json.JSONObject;

public class AirportHandler {
    private static final int FIRST_AIRPORT = '0';
    private static final int LAST_AIRPORT = '7'; //See if making this 8 includes the last airport?
    final public static String[] airports = new String[LAST_AIRPORT - FIRST_AIRPORT + 1]; //Does the +1 cut off the first airport?

    public AirportHandler() {
        //populate the array
        for (int i = 0; i <= 7; i++) { //Does this have something to do with the cut off airports?
            airports[i] = Integer.toString(i);
        }


    }

    public static String getAirportsDetails(String totalDetails) throws JSONException { //String airportJsonString
        //puts the airport data into a json object

        JSONObject airDetailObj = new JSONObject(totalDetails);
        String code = airDetailObj.getString("code");
        String name = airDetailObj.getString("name");
        String city = airDetailObj.getString("city");
        String state = airDetailObj.getString("state");
        String latitude = airDetailObj.getString("latitude");
        String longitude = airDetailObj.getString("longitude");
        String precheck = airDetailObj.getString("precheck");

        String details = "Code: "+code+"\nName: "+name+"\nCity: "+city+"\nState: "+state+"\nLatitude: "+latitude+"\nLongitude: "+longitude+"\nPre-check: "+precheck;
        return details;


    }
}
