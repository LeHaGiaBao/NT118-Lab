package com.example.lab3_new;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editAVGScore;
    private StudentAdapter studentAdapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editAVGScore = findViewById(R.id.editAVGScore);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        RecyclerView recyclerViewStudents = findViewById(R.id.recyclerViewStudents);

        studentAdapter = new StudentAdapter();
        studentAdapter.setOnItemClickListener(this);
        recyclerViewStudents.setAdapter(studentAdapter);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM students", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                float score = cursor.getFloat(cursor.getColumnIndex("score"));
                Student student = new Student(id, name, age, score);
                studentAdapter.addStudent(student);
            } while (cursor.moveToNext());
        }
        cursor.close();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                float score = Float.parseFloat(editAVGScore.getText().toString());
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                values.put("score", score);
                long id = database.insert("students", null, values);
                Student student = new Student((int) id, name, age, score);
                studentAdapter.addStudent(student);
                editTextName.setText("");
                editTextAge.setText("");
                editAVGScore.setText("");
            }
        });
    }

    @Override
    public void onItemClick(final Student student) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit student");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_student, null);
        final EditText editTextName = view.findViewById(R.id.edit1TextName);
        final EditText editTextAge = view.findViewById(R.id.edit1TextAge);
        final EditText editTextAVGScore = view.findViewById(R.id.edit1TextAVGScore);
        editTextName.setText(student.getName());
        editTextAge.setText(String.valueOf(student.getAge()));
        editTextAVGScore.setText(String.valueOf(student.getScore()));
        builder.setView(view);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                float score = Float.parseFloat(editTextAVGScore.getText().toString());
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                values.put("score", score);
                database.update("students", values, "id = ?", new String[]{String.valueOf(student.getId())});
                studentAdapter.updateStudent(new Student(student.getId(), name, age, score));
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.delete("students", "id = ?", new String[]{String.valueOf(student.getId())});
                studentAdapter.deleteStudent(student);
            }
        });
        builder.show();
    }
}