package edu.quinnipiac.ser210.assignment3;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

//Code associated with airport assignment two airport selection
AirportHandler airportHandler = new AirportHandler();
    ShareActionProvider provider;
    boolean userPick = false;
    String item = "";
    private String url01 = "https://tsa-wait-times.p.rapidapi.com/airports/test?APIKEY=test";
    private String url02 =   "test?APIKEY=test";
    Fragment thisFrag = this;

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisFragment = inflater.inflate(R.layout.fragment_main, container, false);
        Spinner spinner = (Spinner) thisFragment.findViewById(R.id.spinner);
        ArrayAdapter<String> arAdapter = new ArrayAdapter<String>(thisFragment.getContext(), android.R.layout.simple_spinner_item, airportHandler.airports);
        arAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userPick=true;
                Log.d("DEBUG: onItemSelected", "HERE B4 IF");
                if (userPick) {
                    Log.d("DEBUG: onItemSelected", "HERE");
                    item = (String) parent.getItemAtPosition(position);
                    Intent selectedAirPort = new Intent(getActivity(), AirportHandler.class);
                    selectedAirPort.putExtra("selected", item);

                    //Calls the async subclass FetchAirData to get the airport item details
                    new FetchAirData().execute(item);

                    userPick = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Inflate the layout for this fragment
        return thisFragment;

    }
/*

   @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userPick = true;

    }
*/

    //ASYNC SUBCLASS: Private class to do background work on the app fetching the data from the api to display
    private class FetchAirData extends AsyncTask<String, Void, String> {

        @Override
        //Method to connect to the API url and grab data
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            String airportDetails;
            try {
                URL url = new URL(url01 + strings[0]
                        + url02);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key", "d2af84939emshd185dedf4ece551p12119bjsn1aede712b856");

                urlConnection.connect();


                if (urlConnection.getInputStream() == null) {

                    //DEBUG
                    Log.e("DEBUG no connection", "no connection");
                    return null;
                }
                //Buffers in the input in of the airportdata from the url
                airportDetails = getStringFromBuffer(
                        new BufferedReader(new InputStreamReader(urlConnection.getInputStream())));

                //DEBUG
                Log.d("DEBUG: airportDetails", airportDetails);

                //Catch error exceptions
            } catch (Exception e) {
                //DEBUG
                Log.e("MainActivity", "Error" + e.getMessage());
                return null;

            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            //return the data that has been buffered in
            return airportDetails;
        }

        //Private method to handle buffering the data into strings
        private String getStringFromBuffer(BufferedReader bufferedReader) throws Exception {
            StringBuffer stringBuffer = new StringBuffer();
            String string;
            int userChoice = Integer.parseInt(item);
            //String buff = bufferedReader.readLine();
            int k = 0;
            int index = 0;
            int startPoint = 0;
            String[] arrayOfAirports = new String[8];


            while ((string = bufferedReader.readLine()) != null) {
                stringBuffer.append(string + ','+'\n');
                for (int i = 0; i <string.length(); i++) {
                    if (string.charAt(i) == ',') {
                        k++;
                        if (k == 7) {
                            String temp = string.substring(startPoint, i + 1);
                            System.out.println("+++++++++++++++++++++ " + temp);
                            arrayOfAirports[index] = temp;
                            index++;
                            k = 0;
                            startPoint = i + 1;
                        }
                    }
                }

            }
            //Checking buffered reader to make sure not null
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (IOException e) {
                    Log.e("DEBUG: MainActivity", "Error" + e.getMessage());
                    return null;
                }
            }
            Log.d("DEBUG: airp", stringBuffer.toString());
            //Returns data from the airportHandler
            return AirportHandler.getAirportsDetails(arrayOfAirports[userChoice]);
        }

        @Override
        protected void onPostExecute(String airportDetails) {

            //Maybe get a reference to the Main Activity and call the bundle and fragment stuff there?
            if (airportDetails != null) {
                //DEBUG
                Log.d("DEBUG: MainFragment", airportDetails);

                //This needs to be in main activity
                DetailFragment display = new DetailFragment();

                Log.d("DEBUG MAIN FRAG", "Detail fragment should have been created");
                Bundle bundle = new Bundle();
                bundle.putString("details", airportDetails);
                display.setArguments(bundle);

                //This lets me get to help after selecting an airport, but the detail fragment is onCreateView isn't called
                getFragmentManager().beginTransaction()
                        .attach(display)
                        .detach(thisFrag)
                        .commit();

               /* //This is what makes main fragment go to detail fragment, but also make main and help not work after
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, display)
                        .commit();*/
            }

        }
    }

}
