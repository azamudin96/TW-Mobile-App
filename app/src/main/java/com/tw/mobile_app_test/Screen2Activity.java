package com.tw.mobile_app_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Screen2Activity extends AppCompatActivity {

    Button save,cancel;
    EditText edt_fname,edt_lname,edt_phone,edt_email;
    String fName , lName , email , phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

         fName = getIntent().getStringExtra("fName");
         lName = getIntent().getStringExtra("lName");
         email = getIntent().getStringExtra("email");
         phone = getIntent().getStringExtra("phone");

        initUi();
    }

    private void initUi(){
        save = findViewById(R.id.save_button);
        cancel = findViewById(R.id.cancel_button);
        edt_fname = findViewById(R.id.edt_fname);
        edt_lname = findViewById(R.id.edt_lname);
        edt_phone = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);

        edt_fname.setText(fName);
        edt_lname.setText(lName);
        edt_phone.setText(phone);
        edt_email.setText(email);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Screen2Activity.this, "Cannot save yet. Function not done yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(){

    }
}