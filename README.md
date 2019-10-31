# Android SharedPreferences

`MyPreference` class:
```java
public class MyPreferences {

    private static MyPreferences myPreferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static MyPreferences getPreferences(Context context) {
        if (myPreferences == null) myPreferences = new MyPreferences(context);
        return myPreferences;
    }

    public void setUserName(String userName){
        editor.putString(Config.USER_NAME, userName);
        editor.apply();
    }

    public String getUserName(){
        //if no data is available for Config.USER_NAME then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString(Config.USER_NAME, "Name not found");
    }

    public void setAge(int age){
        editor.putInt(Config.AGE, age);
        editor.apply();
    }

    public int getAge(){
        return sharedPreferences.getInt(Config.AGE, -1); //if user's age not found then it'll return -1
    }

    public void setStudentFlag(boolean isStudent){
        editor.putBoolean(Config.IS_STUDENT, isStudent);
        editor.apply();
    }

    public boolean isStudent(){
        return sharedPreferences.getBoolean(Config.IS_STUDENT, false); //assume the default value is false
    }

}
```
`Config` class:
```java
public class Config {
    public static final String SHARED_PREFERENCES_NAME = "com.hellohasan.sharedpreferences_2.my_shared_preferences";
    public static final String USER_NAME = "user_name";
    public static final String IS_STUDENT = "is_student";
    public static final String AGE = "age";
}
```
`MainActivity` class:
```java
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
```
