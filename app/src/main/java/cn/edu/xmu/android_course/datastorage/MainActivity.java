package cn.edu.xmu.android_course.datastorage;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String INTERNAL_FILENAME = "internal_storage.dat";
    private static final String EXTERNAL_FILENAME = "external_storage.txt";

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

    private void internalStorageOpen() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(INTERNAL_FILENAME);

            StringBuffer buffer = new StringBuffer();

            byte[] bytes = new byte[1024];

            int result;

            try {
                while ((result = fis.read(bytes)) > 0) {
                    buffer.append(new String(bytes, 0, result));
                }
            } catch (IOException e) {
                Toast.makeText(this, "读文件错误: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "无法关闭文件: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }
                }
            }

            inputText.setText(buffer.toString());
            Toast.makeText(this, "文件已加载", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "找不到文件: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    private void internalStorageSave() {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(INTERNAL_FILENAME, MODE_PRIVATE);

            try {
                fos.write(inputText.getText().toString().getBytes());
            } catch (IOException e) {
                Toast.makeText(this, "写文件错误: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "无法关闭文件: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }
                }
            }

            Toast.makeText(this, "文件已保存至" + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "找不到文件: " + getFileStreamPath(INTERNAL_FILENAME), Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private File getExternalStorageFile() {
        return new File(getExternalFilesDir(null), EXTERNAL_FILENAME);
    }

    private void externalStorageOpen() {
        if (isExternalStorageReadable()) {

        }

    }

    private void externalStorageSave() {
        if (isExternalStorageWritable()) {

        }
    }

    public void onCheckboxClick(View view) {
        switch (view.getId()) {
            case R.id.bigger_fries:
                savePreferenceBooleanValue(PREF_BIGGER_FRIES, ((CheckBox)view).isChecked());
                break;
            case R.id.bigger_drink:
                savePreferenceBooleanValue(PREF_BIGGER_DRINK, ((CheckBox)view).isChecked());
        }

        Toast.makeText(this, "首选项设置已保存", Toast.LENGTH_SHORT).show();
    }

    public void onLoadFileClick(View view) {
        externalStorageOpen();
    }

    public void onSaveFileClick(View view) {
        externalStorageSave();
    }

}
