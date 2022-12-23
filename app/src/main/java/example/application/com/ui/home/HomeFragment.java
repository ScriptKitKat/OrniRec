package example.application.com.ui.home;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import example.application.com.MainActivity;
import example.application.com.R;
import example.application.com.ScreenRecorder;
import example.application.com.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Button nextB = ((Button) getView().findViewById(R.id.connectButton));
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ScreenRecorder.class);
                startActivity(i);
            }
        });

//        Button button = view.findViewById(R.id.sendData1btn);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                EditText editText = view.findViewById(R.id.fragment1Data);
//                Bundle result = new Bundle();
//                result.putString("df1",editText.getText().toString());
//                getParentFragmentManager().setFragmentResult("dataFrom1",result);
//                editText.setText("");
//            }
//        });

        return view;
    }
}