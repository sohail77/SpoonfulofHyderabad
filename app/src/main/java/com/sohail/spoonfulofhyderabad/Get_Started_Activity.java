package com.sohail.spoonfulofhyderabad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class Get_Started_Activity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    Button getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__started_);

        getStarted=(Button)findViewById(R.id.get_started);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Get_Started_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
