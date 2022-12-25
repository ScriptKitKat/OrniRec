package example.application.com.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.NavigableMap;

import example.application.com.R;
import example.application.com.ScreenRecorder;

public class HomeFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        Button nextB = ((Button) view.findViewById(R.id.connectButton));
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_record);
            }
        });
    }
}