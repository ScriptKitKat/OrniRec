
package example.application.com;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.NavigationUI;

import example.application.com.databinding.ActivityScreenRecorderBinding;
//import example.application.com.ui.fragments.AudioFragment;

public class ScreenRecorder  extends AppCompatActivity {

    private ActivityScreenRecorderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenRecorderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onBackPressed() {
        if (Build.VERSION.SDK_INT == 29) {
            this.finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}