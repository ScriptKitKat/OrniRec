package example.application.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ScreenRecorder extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;

    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_recorder);

        Button uploadBtn = findViewById(R.id.uploadBtn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check permission greater than equal to marshmallow we used run time permission
                if(Build.VERSION.SDK_INT >= 23) {
                    if(checkPermission()) {
                        filePicker();
                    } else {
                        requestPermission();
                    }
                } else {
                    filePicker();
                }
            }
        });
    }

    private void filePicker() {
        Toast.makeText(ScreenRecorder.this, "File Picker Called", Toast.LENGTH_SHORT).show();

        Intent openGallery = new Intent(Intent.ACTION_PICK);
        openGallery.setType("mp3/*");
        startActivityForResult(openGallery, REQUEST_GALLERY);
    }

    private void requestPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(ScreenRecorder.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(ScreenRecorder.this, "Please give Permission to Upload file", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(ScreenRecorder.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int first = ActivityCompat.checkSelfPermission(getApplicationContext() ,Manifest.permission.READ_EXTERNAL_STORAGE);

        return (first == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ScreenRecorder.this, "Permission Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ScreenRecorder.this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            String filePath = getRealPathFromUri(data.getData(), ScreenRecorder.this);
            Log.d("File Path : ", " " + filePath);
        }
    }

    public String getRealPathFromUri(Uri uri, Activity activity) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
        if(cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int id = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
            return cursor.getString(id);
        }
    }


//    ImageButton mic;
//    TextView result;
//    Button play;
//    Button stop;
//
//    MediaRecorder mediaRecorder;
//    MediaPlayer mediaPlayer;
//    String AudioSavaPath = null;
//
//    boolean isRecording = false;
//    int count = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_screen_recorder);
//
//        mic = findViewById(R.id.recordButton);
//        result = findViewById(R.id.result);
//        play = findViewById(R.id.play);
//        stop = findViewById(R.id.stop);
//
//        mic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (checkPermissions()) {
//                    if (!isRecording) {
//                        isRecording = true;
//
//                        AudioSavaPath = Environment.getExternalStorageDirectory().getAbsolutePath()
//                                + "/" + "recordingAudio.mp3";
//                        mediaRecorder = new MediaRecorder();
//                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//                        mediaRecorder.setOutputFile(AudioSavaPath);
//
//                        try {
//                            mediaRecorder.prepare();
//                            mediaRecorder.start();
//                            Toast.makeText(ScreenRecorder.this, "recording started", Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        mediaRecorder.stop();
//                        mediaRecorder.release();
//                        isRecording = false;
//                        Toast.makeText(ScreenRecorder.this, "recording stopped", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//
//                    // Requesting permission from the user
//                    ActivityCompat.requestPermissions(ScreenRecorder.this, new String[]{
//                            Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    }, 1);
//                }
//            }
//        });
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer = new MediaPlayer();
//
//                try {
//                    mediaPlayer.setDataSource(AudioSavaPath);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mediaPlayer != null) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    Toast.makeText(ScreenRecorder.this, "Stopped playing", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//
//    private boolean checkPermissions() {
//        int first = ActivityCompat.checkSelfPermission(getApplicationContext() ,Manifest.permission.RECORD_AUDIO);
//        int second = ActivityCompat.checkSelfPermission(getApplicationContext() ,Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        return (first == PackageManager.PERMISSION_GRANTED) && (second == PackageManager.PERMISSION_GRANTED);
//    }

}