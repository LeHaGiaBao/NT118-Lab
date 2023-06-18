package com.example.lab2_5;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerMonAn;
    SpinnerAdapter spinnerAdapter;
    MonAn res_monan;
    Button btnAdd;
    GridView gvDish;
    EditText edtName;
    ArrayList<MonAn> arrayMonan;
    ArrayList<MonAn> monanGv;
    MonAnAdapter adapter;
    CheckBox chbxPromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);


        arrayMonan = new ArrayList<MonAn>();
        arrayMonan.add(new MonAn("Thumbnail 1", R.drawable.first_thumbnail));
        arrayMonan.add(new MonAn("Thumbnail 2", R.drawable.second_thumbnail));
        arrayMonan.add(new MonAn("Thumbnail 3", R.drawable.third_thumbnail));
        arrayMonan.add(new MonAn("Thumbnail 4", R.drawable.fourth_thumbnail));
        arrayMonan.add(new MonAn("Thumbnail 5", R.drawable.fifth_thumbnail));

        //final List<String> thumbnails = Arrays.asList("cake", "candy", "chocolate", "icecream", "omelette");
        spinnerMonAn = (Spinner) findViewById(R.id.spinner);

        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), R.layout.my_dropdown_item, arrayMonan);
        spinnerMonAn.setAdapter(spinnerAdapter);


        spinnerMonAn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                res_monan = arrayMonan.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        chbxPromotion = (CheckBox) findViewById(R.id.checkBox) ;
        btnAdd  = (Button) findViewById(R.id.btnAdd) ;
        gvDish = (GridView) findViewById(R.id.gvDish);

        edtName = (EditText) findViewById(R.id.edtName);
        monanGv = new ArrayList<MonAn>();
        adapter = new MonAnAdapter(this, R.layout.item_dish,monanGv);
        gvDish.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                MonAn monan = new MonAn();
                monan.setName(name);

                monan.setThumbnail(res_monan.getThumbnail());

                if (chbxPromotion.isChecked())
                {
                    monan.setPromotion(true);
                }
                else
                {
                    monan.setPromotion(false);
                }

                monanGv.add(monan);
                edtName.setText("");
                spinnerMonAn.setSelection(0);
                chbxPromotion.setChecked(false);
                adapter.notifyDataSetChanged();
            }
        });
    }
}