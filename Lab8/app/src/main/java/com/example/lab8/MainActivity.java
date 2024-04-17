package com.example.lab8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonAdd, buttonDelete, buttonClear, buttonRead, buttonUpdate;
    EditText etName, etEmail, etID;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(this);

        buttonRead = (Button) findViewById(R.id.btnCount);
        buttonRead.setOnClickListener(this);

        buttonClear = (Button) findViewById(R.id.btnClean);
        buttonClear.setOnClickListener(this);

        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(this);

        buttonDelete = (Button) findViewById(R.id.btnDelete);
        buttonDelete.setOnClickListener(this);

        etID = (EditText) findViewById(R.id.editTextId);
        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);

        dbHelper = new DBHelper(this);

    }

    public static class DBHelper extends SQLiteOpenHelper
    {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "myBase";
        public static final String TABLE_PERSONS = "persons";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MAIL = "mail";

        public DBHelper(@Nullable Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table " + TABLE_PERSONS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text," + KEY_MAIL + " text" + ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("drop table if exists " + TABLE_PERSONS);
            onCreate(db);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextIdButton);

        String ID = etID.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // класс для добавления новых строк в таблицу

        if (v.getId() == R.id.btnAdd) {
            editText.setText(null);
            String buttonName = getResources().getResourceEntryName(v.getId());
            editText.setText("Нажата кнопка: " + buttonName);

            contentValues.put(DBHelper.KEY_NAME, name);
            contentValues.put(DBHelper.KEY_MAIL, email);
            database.insert(DBHelper.TABLE_PERSONS, null, contentValues);
        } else if (v.getId() == R.id.btnCount) {
            editText.setText(null);
            String buttonName = getResources().getResourceEntryName(v.getId());
            editText.setText("Нажата кнопка: " + buttonName);

            Cursor cursor = database.query(DBHelper.TABLE_PERSONS, null, null, null,
                    null, null, null); // все поля без сортировки и группировки

            if (cursor.moveToFirst())
            {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                do {
                    Log.d("mLog", "ID =" + cursor.getInt(idIndex) +
                            ", name = " + cursor.getString(nameIndex) +
                            ", email = " + cursor.getString(emailIndex));

                } while (cursor.moveToNext());
            } else
                Log.d("mLog", "0 rows");

            cursor.close(); // освобождение памяти
        } else if (v.getId() == R.id.btnClean) {
            editText.setText(null);
            String buttonName = getResources().getResourceEntryName(v.getId());
            editText.setText("Нажата кнопка: " + buttonName);

            database.delete(DBHelper.TABLE_PERSONS, null, null);
        } else if (v.getId() == R.id.btnDelete) {
            editText.setText(null);
            String buttonName = getResources().getResourceEntryName(v.getId());
            editText.setText("Нажата кнопка: " + buttonName);

            if (!ID.equalsIgnoreCase(""))
            {
                int delCount = database.delete(DBHelper.TABLE_PERSONS, DBHelper.KEY_ID + "= " + ID, null);
                Log.d("mLog", "Удалено строк = " + delCount);
            }
        } else if (v.getId() == R.id.btnUpdate) {
            editText.setText(null);
            String buttonName = getResources().getResourceEntryName(v.getId());
            editText.setText("Нажата кнопка: " + buttonName);

            if (!ID.equalsIgnoreCase(""))
            {
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_NAME, name);
                int updCount = database.update(DBHelper.TABLE_PERSONS, contentValues, DBHelper.KEY_ID + "= ?", new String[]{ID});
                Log.d("mLog", "Обновлено строк = " + updCount);
            }
        }
        dbHelper.close(); // закрываем соединение с БД
    }
}
