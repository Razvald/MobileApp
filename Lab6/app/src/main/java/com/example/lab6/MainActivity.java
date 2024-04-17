package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String name ="неопределено";
    final static String nameVariableKey = "NAME_VAR";
    final static String textViewTexKey = "TEXT_VIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString(nameVariableKey, name);
        TextView nameView = (TextView) findViewById(R.id.saveTextView);
        outState.putString(textViewTexKey, nameView.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        name = savedInstanceState.getString(nameVariableKey);
        String textViewText= savedInstanceState.getString(textViewTexKey);
        TextView nameView = (TextView) findViewById(R.id.saveTextView);
        nameView.setText(textViewText);
    }

    public void saveField(View view)
    {
        TextView nameBox = (TextView) findViewById(R.id.editTextTextMultiLine);
        name = nameBox.getText().toString();
    }
	public void restoreField(View view)
    {
        TextView nameView = (TextView) findViewById(R.id.saveTextView);
        nameView.setText(name);
    }
}