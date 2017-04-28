package cn.edu.xmu.android_course.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    }

    public void onFriesBigger(View view) {
        Toast.makeText(this, "首选项设置已保存", Toast.LENGTH_SHORT).show();
    }

    public void onDrinkBigger(View view) {
        Toast.makeText(this, "首选项设置已保存", Toast.LENGTH_SHORT).show();
    }

    public void onLoadFileClick(View view) {
        Toast.makeText(this, "文件已加载", Toast.LENGTH_SHORT).show();
    }

    public void onSaveFileClick(View view) {
        Toast.makeText(this, "文件已保存", Toast.LENGTH_SHORT).show();
    }

}
