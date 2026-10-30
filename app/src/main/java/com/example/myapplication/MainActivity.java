package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.listItem);
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView ageText = findViewById(R.id.ageText);
        Button btn = findViewById(R.id.btn);
        int[] selectedSpecious = {-1};
        EditText nameInput = findViewById(R.id.nameInput);
        EditText purposeInput = findViewById(R.id.purposeInput);
        EditText timeInput = findViewById(R.id.timeInput);

        String[] gatunki = {"Pies","Kot","Świnka morska"};
        List<String> gatunkiList = Arrays.asList(gatunki);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gatunkiList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = list.getItemAtPosition(position).toString().trim().toLowerCase();
                selectedSpecious[0] = position;
                switch (value) {
                    case "kot":
                        seekBar.setMax(20);
                        break;
                    case "pies":
                        seekBar.setMax(18);
                        break;
                    case "świnka morska":
                        seekBar.setMax(9);
                        break;
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String specious;
                String name = nameInput.getText().toString().trim();
                String purpose = purposeInput.getText().toString().trim();
                String time = timeInput.getText().toString().trim();
                String age = String.valueOf(seekBar.getProgress());
                if(selectedSpecious[0] == -1){
                    specious = "";
                } else {
                    specious = list.getItemAtPosition(selectedSpecious[0]).toString().trim();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Twoje zgłoszenie");
                builder.setMessage("Twoje imie i nazwisko: "+name+", Cel: "+purpose+", Czas: "+time+", Gatunek: "+specious+", wiek: "+age);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

                System.out.println();
            }
        });
    }
}