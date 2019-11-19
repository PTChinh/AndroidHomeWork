package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button callButton = (Button) findViewById(R.id.callButton);
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);

        callButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v;
                int id = v.getId();
                if(id == R.id.callButton) {
                    if(checkPermission()) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber.getText()));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                    else {
                        requestPermission();
                    }
                }


            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean callPhoneAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (callPhoneAccepted) {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Permission Granted, Now you can access call phone", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Permission Denied, You cannot access call phone", Toast.LENGTH_SHORT);
                        toast.show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CALL_PHONE)) {

                                requestPermissions(new String[]{CALL_PHONE}, PERMISSION_REQUEST_CODE);

                                return;
                            }
                        }

                    }
                }
                break;
        }
    }

}
