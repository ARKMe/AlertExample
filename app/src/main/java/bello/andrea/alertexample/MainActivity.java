package bello.andrea.alertexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.text);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("TITOLO");

                LayoutInflater inflater = (LayoutInflater)getSystemService (Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_layout, null);

                String[] array = getResources().getStringArray(R.array.values);
                List<String> myResArrayList = Arrays.asList(array);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        myResArrayList
                );
                final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner);
                spinner.setAdapter(arrayAdapter);

                builder.setView(dialogView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_light));
                        text.setText(((TextView)spinner.getSelectedView().findViewById(android.R.id.text1)).getText());
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));
                    }
                });

                AlertDialog dialog = builder.create();



                dialog.show();
            }
        });

    }
}
