package com.sohail.spoonfulofhyderabad.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sohail.spoonfulofhyderabad.MainActivity;
import com.sohail.spoonfulofhyderabad.R;

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
