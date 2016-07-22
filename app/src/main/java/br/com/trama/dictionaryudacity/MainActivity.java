package br.com.trama.dictionaryudacity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.dictionary_list_view);

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        String[] columns = {UserDictionary.Words.WORD, UserDictionary.Words.FREQUENCY};
        int[] layoutIds = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter simpleCursorAdapter =
                new SimpleCursorAdapter(this,
                        android.R.layout.two_line_list_item,
                        cursor,
                        columns,
                        layoutIds,
                        0);

        listView.setAdapter(simpleCursorAdapter);

    }
}
