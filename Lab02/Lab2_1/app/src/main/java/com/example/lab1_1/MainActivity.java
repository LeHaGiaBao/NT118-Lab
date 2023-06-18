package com.example.lab1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ListView lvPerson;
    TextView tvPerson;
    ArrayList<String> arrayName;
    Button btnAdd;
    EditText edtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPerson = (TextView) findViewById(R.id.tv_person);
        lvPerson = (ListView) findViewById(R.id.lv_person);
        edtName = (EditText) findViewById(R.id.edtName);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        arrayName = new ArrayList<>();
        arrayName.add("Tèo");
        arrayName.add("Tý");
        arrayName.add("Bin");
        arrayName.add("Bo");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayName);
        lvPerson.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String ten = edtName.getText().toString();
                    arrayName.add(ten);
                    adapter.notifyDataSetChanged();
            }
        });
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
//                        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        String value = lvPerson.getItemAtPosition(position).toString();
                        tvPerson.setText("position :" + position+ " ; value =" + value);
                    }
        });
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                arrayName.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}