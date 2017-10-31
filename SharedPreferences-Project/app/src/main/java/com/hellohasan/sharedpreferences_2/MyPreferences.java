package com.hellohasan.sharedpreferences_2;

import android.content.Context;
import android.content.SharedPreferences;

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
        editor.commit();
    }

    public String getUserName(){
        //if no data is available for Config.USER_NAME then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString(Config.USER_NAME, "Name not found");
    }

    public void setAge(int age){
        editor.putInt(Config.AGE, age);
        editor.apply();
        editor.commit();
    }

    public int getAge(){
        return sharedPreferences.getInt(Config.AGE, -1); //if user's age not found then it'll return -1
    }

    public void setStudentFlag(boolean isStudent){
        editor.putBoolean(Config.IS_STUDENT, isStudent);
        editor.apply();
        editor.commit();
    }

    public boolean isStudent(){
        return sharedPreferences.getBoolean(Config.IS_STUDENT, false); //assume the default value is false
    }

}
