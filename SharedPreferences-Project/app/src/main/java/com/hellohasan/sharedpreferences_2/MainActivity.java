package com.hellohasan.sharedpreferences_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText ageEditText;
    private CheckBox studentInfoCheckbox;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView studentInfoTextView;

    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInitialization();
        myPreferences = MyPreferences.getPreferences(this);

        showUserName();
        showUserAge();
        showStudentStatus();
    }

    public void saveName(View view) {
        String name = nameEditText.getText().toString();
        myPreferences.setUserName(name);

        showUserName();
    }

    public void saveAge(View view) {
        int age = Integer.parseInt(ageEditText.getText().toString());
        myPreferences.setAge(age);

        showUserAge();
    }

    public void changeStudentStatus(View view) {
        boolean studentFlag = studentInfoCheckbox.isChecked();
        myPreferences.setStudentFlag(studentFlag);

        showStudentStatus();
    }

    private void showUserName() {
        String name = myPreferences.getUserName();
        nameTextView.setText(name);
    }

    private void showUserAge(){
        int age = myPreferences.getAge();
        ageTextView.setText(String.valueOf(age));
    }

    private void showStudentStatus(){
        boolean status = myPreferences.isStudent();
        studentInfoTextView.setText(String.valueOf(status));
    }

    private void viewInitialization() {
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        studentInfoCheckbox = findViewById(R.id.checkbox);
        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        studentInfoTextView = findViewById(R.id.studentInfoTextView);
    }
}
