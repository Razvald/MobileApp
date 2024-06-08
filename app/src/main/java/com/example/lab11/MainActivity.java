package com.example.lab11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void smsSend(View v) {

        EditText number=(EditText)findViewById(R.id.number);
        EditText message=(EditText)findViewById(R.id.message);

        String numberText = number.getText().toString();
        String messageText= message.getText().toString();

        SmsManager.getDefault()
                .sendTextMessage(numberText, null, messageText.toString(), null, null);
    }

    public void phoneCall(View v) {
        EditText number=(EditText)findViewById(R.id.number);
        String toDial="tel:"+number.getText().toString();

        //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));

        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
    }
}
