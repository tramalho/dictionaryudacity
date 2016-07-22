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

                while (cursor.moveToNext()) {
                    viewById.append(lineBreak);
                    viewById.append(getValueByCursor(cursor, UserDictionary.Words._ID));
                    viewById.append(separator);
                    viewById.append(getValueByCursor(cursor, UserDictionary.Words.FREQUENCY));
                    viewById.append(separator);
                    viewById.append(getValueByCursor(cursor, UserDictionary.Words.WORD));
                }
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getValueByCursor(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }
}
