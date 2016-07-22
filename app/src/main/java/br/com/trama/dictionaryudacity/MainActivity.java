package br.com.trama.dictionaryudacity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView viewById = (TextView) findViewById(R.id.textview_id);
        Cursor cursor = null;
        try {

            ContentResolver resolver = getContentResolver();

            cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

            if (cursor != null && viewById != null) {

                String lineBreak = getString(R.string.line_brake);
                String separator = getString(R.string.separator);

                viewById.setText(getString(R.string.total_columns, cursor.getCount()));
                viewById.append(lineBreak);
                viewById.append(getString(R.string.column_names));

                int idIdx = cursor.getColumnIndex(UserDictionary.Words._ID);
                int frequencyIdx = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
                int wordIdx = cursor.getColumnIndex(UserDictionary.Words.WORD);

                while (cursor.moveToNext()) {
                    viewById.append(lineBreak);
                    viewById.append(""+cursor.getInt(idIdx));
                    viewById.append(separator);
                    viewById.append(""+cursor.getInt(frequencyIdx));
                    viewById.append(separator);
                    viewById.append(cursor.getString(wordIdx));
                }
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
