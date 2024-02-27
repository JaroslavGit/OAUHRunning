package cz.oauh.oauhrunning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class NewRunActivity extends AppCompatActivity {

    private AutoCompleteTextView start;
    private AutoCompleteTextView cil;
    private List<String> mista;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_run);
        start = findViewById(R.id.startText);
        cil = findViewById(R.id.endText);
        db = new DBHelper(this);
        mista = new ArrayList<String>();
        Cursor data = db.vratMista();
        data.moveToFirst();
        while(!data.isAfterLast())
        {
         mista.add(data.getString(1));
         data.moveToNext();
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,mista.toArray(new String[0]));
        start.setAdapter(adapter);
        cil.setAdapter(adapter);


    }
}