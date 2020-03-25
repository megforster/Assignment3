package edu.quinnipiac.ser210.assignment3;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

//All the code associated with assignment two details page



    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        String details = getArguments().getString("details");
        TextView textView = (TextView) view.findViewById(R.id.details);
        textView.setText(details);

        // Inflate the layout for this fragment
        return view;

    }

}
