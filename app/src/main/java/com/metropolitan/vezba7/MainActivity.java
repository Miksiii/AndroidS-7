package com.metropolitan.vezba7;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickAddName(View view) {

        ContentValues values = new ContentValues();

        values.put(KonsultacijeProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(KonsultacijeProvider.WORK_WITH_STUDENTS,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                KonsultacijeProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickUpdateContent(View view){

        ContentValues values = new ContentValues();

        EditText idEditText = (EditText)findViewById(R.id.editText4);
        String num = idEditText.getText().toString();

        values.put(KonsultacijeProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(KonsultacijeProvider.WORK_WITH_STUDENTS,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        getContentResolver().update(KonsultacijeProvider.CONTENT_URI,values,KonsultacijeProvider._ID+"=?",new String[] {String.valueOf(num)}); //id is the id of the row you wan to update
    }

    public  void onClickDeleteContent(View view){

        ContentValues values = new ContentValues();


        EditText idEditText = (EditText)findViewById(R.id.editText4);
        String num = idEditText.getText().toString();

        getContentResolver().delete(KonsultacijeProvider.CONTENT_URI, "sifra_predmeta=?", new String[] {String.valueOf(num)});
    }

    public void onClickRetrieveContent(View view) {

        String URL = "content://com.metropolitan.provider.Konsultacije/predmeti";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "naziv_predmeta");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(KonsultacijeProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( KonsultacijeProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( KonsultacijeProvider.WORK_WITH_STUDENTS)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
