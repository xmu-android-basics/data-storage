package cn.edu.xmu.android_course.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "internal_storage.dat";

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
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);

            StringBuffer buffer = new StringBuffer();

            byte[] bytes = new byte[1024];

            int result;

            try {
                while ((result = fis.read(bytes)) > 0) {
                    buffer.append(new String(bytes, 0, result));
                }
            } catch (IOException e) {
                Toast.makeText(this, "读文件错误: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "无法关闭文件: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }
                }
            }

            inputText.setText(buffer.toString());
            Toast.makeText(this, "文件已加载", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "找不到文件: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    public void onSaveFileClick(View view) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);

            try {
                fos.write(inputText.getText().toString().getBytes());
            } catch (IOException e) {
                Toast.makeText(this, "写文件错误: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "无法关闭文件: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }
                }
            }

            Toast.makeText(this, "文件已保存至" + getFileStreamPath(FILENAME), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "找不到文件: " + getFileStreamPath(FILENAME), Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

}
