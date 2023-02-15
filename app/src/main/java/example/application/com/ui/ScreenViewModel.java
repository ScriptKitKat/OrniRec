package example.application.com.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScreenViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ScreenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Screen fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
