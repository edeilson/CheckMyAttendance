package com.edge.checkmyattendance;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by @edeilsonfs on 20/04/2017.
 */
public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    //TODO create a container to show the camera and ISE Logo
    private static final int MY_REQUEST_CODE = 0;
    private ZXingScannerView mScannerView = null;

    public static String CMALog = "CheckMyAttendance";
    public Activity getMainActivity = (Activity) MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);


        Log.i(CMALog, "Request Camera Permission...");
        requestCameraPermission();

        Log.i(CMALog, "Starting Camera...");
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
        Log.i(CMALog, "The QR code was captured successfully.\n Result: " + result);

        //TODO Validate the QRCode

        Log.i(CMALog, "Requesting HTTP");
        Student s1 = new Student("6101","10121989");
        new AttendanceRequest(this).execute(s1);

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
            Log.i(CMALog, "Acepted");
        }
        Log.i(CMALog, "Done");
    }


}
