package com.example.camerapoc;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "MainActivity";
    private ImageView mImageView;
    private FragmentManager fragmentManager;
    private Fragment captureImageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to fragment manager and fragments.
        fragmentManager = getFragmentManager();
        captureImageFragment = fragmentManager.findFragmentById(R.id.captureImageFragment);

        // Set listeners to GUI items.
        Button captureImgButton = (Button) findViewById(R.id.captureImageButton);
        Log.d(TAG, "captureImgButton = " + captureImgButton);
        captureImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        mImageView = (ImageView) findViewById(R.id.capturedImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Retrieve the icon of the image that was just taken.
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                Log.d(TAG, "onActivityResult(): received bitmap");
                mImageView.setImageBitmap(imageBitmap);
            } else {
                Log.d(TAG, "onActivityResult(): did not receive bitmap");
            }
        }
    }

    private void takePicture() {
        PackageManager pm = getPackageManager();
        if (pm != null && pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // Then take the picture.
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            Toast.makeText(this, R.string.no_camera_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
