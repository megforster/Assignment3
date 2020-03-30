package edu.quinnipiac.ser210.assignment3;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public DetailFragment() {
        /*Log.d("DEBUG DETAIL FRAGMENT", "Here is consturctor");*/
    }


    //Sets the textview to the select airport's details
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        String details = getArguments().getString("details");
       /* Log.d("DEBUG:DETAIL FRAGMENT", "details");*/
        TextView textView = (TextView) view.findViewById(R.id.details);
        textView.setText(details);
        return view;

    }

}
