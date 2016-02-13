package com.ggdg.www.linedtablefixeheaders;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.inqbarna.tablefixheaders.TableFixHeaders;

public class MainActivity extends AppCompatActivity {

    private TableFixHeaders table;
    private SampleTableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.hasLineView())
                {
                    Snackbar.make(view, "LINE VIEW VISIBILITY", Snackbar.LENGTH_LONG)
                            .setAction("HIDE", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    adapter.setShowLine(!adapter.hasLineView());
                                    adapter.notifyDataSetChanged();
                                }
                            }).show();
                }
                else {
                    Snackbar.make(view, "LINE VIEW VISIBILITY", Snackbar.LENGTH_LONG)
                            .setAction("SHOW", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    adapter.setShowLine(!adapter.hasLineView());
                                    adapter.notifyDataSetChanged();
                                }
                            }).show();
                }
            }
        });

        initTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initTable()
    {
        table = (TableFixHeaders)findViewById(R.id.table);

        adapter = new SampleTableAdapter(this);
        table.setAdapter(adapter);
    }
}
