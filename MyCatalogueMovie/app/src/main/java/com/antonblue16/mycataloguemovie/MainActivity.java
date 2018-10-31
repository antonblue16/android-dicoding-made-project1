package com.antonblue16.mycataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Vector<FilmItems>>
{
    ListView listView;
    FilmAdapter adapter;
    ImageView ivImageFilm;
    Button btnSearchFilm;
    EditText edFilm;

    static final String EXTRA_FILM = "EXTRA_FILM";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvCataogueFilm);
        edFilm = findViewById(R.id.edSearchFilm);
        btnSearchFilm = findViewById(R.id.btnSearchFilm);
        ivImageFilm = findViewById(R.id.ivImageFilm);

        String filmName = edFilm.getText().toString();

        adapter = new FilmAdapter(this);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l)
            {
                FilmItems item = (FilmItems) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailFilmActivity.class);

                intent.putExtra(DetailFilmActivity.EXTRA_NAME, item.getFilmName());
                intent.putExtra(DetailFilmActivity.EXTRA_IMAGE,item.getImageFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_LANGUAGE,item.getFilmLanguage());
                intent.putExtra(DetailFilmActivity.EXTRA_AVERAGE,item.getFilmRate());
                intent.putExtra(DetailFilmActivity.EXTRA_SYNOPSIS,item.getFilmSynopsis());
                intent.putExtra(DetailFilmActivity.EXTRA_DATETIME,item.getFilmDateTime());

                startActivity(intent);
            }
        });


        btnSearchFilm.setOnClickListener(filmListener);

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FILM, filmName);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<Vector<FilmItems>> onCreateLoader(int i, Bundle bundle)
    {
        String filmName = "";
        if(bundle != null)
        {
            filmName = bundle.getString(EXTRA_FILM);
        }
        return new AsyncTaskLoaderFilm(this, filmName);
    }

    @Override
    public void onLoadFinished(Loader<Vector<FilmItems>> loader, Vector<FilmItems> filmItems)
    {
        adapter.setData(filmItems);
    }

    @Override
    public void onLoaderReset(Loader<Vector<FilmItems>> loader)
    {
        adapter.setData(null);
    }

    View.OnClickListener filmListener= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String filmName = edFilm.getText().toString();
            if(TextUtils.isEmpty(filmName))
            {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_FILM, filmName);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}