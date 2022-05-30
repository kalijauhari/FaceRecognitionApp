package com.example.imagepro;


import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

//import com.example.myproject.R;

import java.util.concurrent.Executor;


public class auth extends AppCompatActivity {

    Button btnauth;
    //VideoView videoView;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo PromptInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        btnauth = findViewById(R.id.btnauth);


        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(auth.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
               // stat.setText("Error :" + errString);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Successful"+errString,
                        Toast.LENGTH_SHORT);

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //stat.setText("Successful");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Successful",
                        Toast.LENGTH_SHORT);

                toast.show();
                sendUserToNextActivity();

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
               // stat.setText("failed");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Failed",
                        Toast.LENGTH_SHORT);
            }
        });

        PromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("biometric authentication")
                .setSubtitle("login using fingerprint or face")
                .setNegativeButtonText("cancel")
                .build();


        btnauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(PromptInfo);
            }
        });
    }


    private void sendUserToNextActivity() {
        Intent intent=new Intent(auth.this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}