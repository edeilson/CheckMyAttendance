package com.edge.checkmyattendance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by @edeilsonfs on 20/04/2017.
 */
public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int MY_REQUEST_CODE = 0;
    private ZXingScannerView mScannerView = null;
    boolean mStopHandler = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);


        requestCameraPermission();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(0);// 1 - is the front camera*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        String a = result+"";
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();

        //TODO make a request

        //TODO Show message for student

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(MainActivity.this);
                //alertDialog.dismiss();
                //dialogInformationOverallAttendance.dismiss();

            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.resumeCameraPreview(MainActivity.this);
    }

    private void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, MY_REQUEST_CODE);
        }
    }


}
