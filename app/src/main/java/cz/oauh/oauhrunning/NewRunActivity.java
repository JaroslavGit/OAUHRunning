package cz.oauh.oauhrunning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        Button ukladani = (Button) findViewById(R.id.ulozBeh);
        ukladani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idStart;
                int idCil;
               if((idStart= db.idMista(start.getText().toString())) == -1)
               { //start není v DB ->vytvořím ho
                idStart = (int) db.vlozMisto(start.getText().toString());
               }

                if((idCil= db.idMista(cil.getText().toString())) == -1)
                { //start není v DB ->vytvořím ho
                    idCil = (int) db.vlozMisto(cil.getText().toString());
                }

                EditText pocetM = findViewById(R.id.kmNumber);
                DatePicker datumPicker = findViewById(R.id.datum);

                Calendar kalendar = Calendar.getInstance();
                kalendar.set(datumPicker.getYear(), datumPicker.getMonth(), datumPicker.getDayOfMonth());
                java.sql.Date datum = new Date(kalendar.getTimeInMillis());
                System.out.println(datum.toString());
                db.vlozBeh(idStart, idCil, (int)(1000*Double.parseDouble(pocetM.getText().toString())), datum);
                Snackbar.make(findViewById(android.R.id.content),
                        "Data byla uložena", Snackbar.LENGTH_LONG).show();
                start.setText("");
                cil.setText("");
                pocetM.setText("0");
            }
        });

    }
}














