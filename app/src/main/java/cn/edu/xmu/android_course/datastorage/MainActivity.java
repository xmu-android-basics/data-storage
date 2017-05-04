package cn.edu.xmu.android_course.datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "pref_storage";
    private static final String PREF_BIGGER_FRIES = "is_bigger_fries";
    private static final String PREF_BIGGER_DRINK = "is_bigger_drink";

    private CheckBox bigger_fries;
    private CheckBox bigger_drink;

    private EditText inputText;

    private Button loadFileButton;
    private Button saveFileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bigger_fries = (CheckBox) findViewById(R.id.bigger_fries);
        bigger_drink = (CheckBox) findViewById(R.id.bigger_drink);

        inputText = (EditText) findViewById(R.id.editText);

        loadFileButton = (Button) findViewById(R.id.buttonLoadFromFile);
        saveFileButton = (Button) findViewById(R.id.buttonSaveToFile);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        boolean is_bigger_fries = settings.getBoolean(PREF_BIGGER_FRIES, false);
        bigger_fries.setChecked(is_bigger_fries);

        boolean is_bigger_drink = settings.getBoolean(PREF_BIGGER_DRINK, false);
        bigger_drink.setChecked(is_bigger_drink);
    }

    private void savePreferenceBooleanValue(String name, boolean value) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(name, value);

        editor.commit();
    }

    public void onFriesBigger(View view) {
        savePreferenceBooleanValue(PREF_BIGGER_FRIES, bigger_fries.isChecked());

        Toast.makeText(this, "首选项设置已保存", Toast.LENGTH_SHORT).show();
    }

    public void onDrinkBigger(View view) {
        savePreferenceBooleanValue(PREF_BIGGER_DRINK, bigger_drink.isChecked());

        Toast.makeText(this, "首选项设置已保存", Toast.LENGTH_SHORT).show();
    }

    public void onLoadFileClick(View view) {
        Toast.makeText(this, "文件已加载", Toast.LENGTH_SHORT).show();
    }

    public void onSaveFileClick(View view) {
        Toast.makeText(this, "文件已保存", Toast.LENGTH_SHORT).show();
    }

}
