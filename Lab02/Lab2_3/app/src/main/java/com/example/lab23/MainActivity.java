package com.example.lab23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etId;
    EditText etName;
    RadioButton rd_chinhthuc, rdBtnKhong;
    RadioGroup rgType;
    Button btnNhap;
    ListView lvNV;
    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;
    Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        rgType = (RadioGroup) findViewById(R.id.rgType);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        lvNV = (ListView) findViewById(R.id.lv_NV);
        employees = new ArrayList<Employee>();
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1,employees);
        lvNV.setAdapter(adapter);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radId = rgType.getCheckedRadioButtonId();
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (radId == R.id.rd_chinhthuc) {
                    //tạo instance là FullTime
                    employee = new EmployeeFulltime();
                } else {
                    //Tạo instance là Partime
                    employee = new EmployeeParttime();
                }
                //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
                employee.setId(id);
                employee.setName(name);
                //Đưa employee vào ArrayList
                employees.add(employee);
                //Cập nhập giao diện
                adapter.notifyDataSetChanged();
            }
        });
    }
}