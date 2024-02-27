package cz.oauh.oauhrunning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        db = new DBHelper(this);
        db.naplnMista();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_newrun) {
          Intent nova = new Intent(MainActivity.this, NewRunActivity.class);
          startActivity(nova);
          return true;
        }

        if (id == R.id.action_calendar) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Klikl jsem kalendář", Snackbar.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_statistic) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Chci zobrazit statistiku", Snackbar.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}





