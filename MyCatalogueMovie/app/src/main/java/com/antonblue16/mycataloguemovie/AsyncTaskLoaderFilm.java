package com.antonblue16.mycataloguemovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

import cz.msebera.android.httpclient.Header;

public class AsyncTaskLoaderFilm extends AsyncTaskLoader<Vector<FilmItems>>
{
    private Vector<FilmItems> dataFilm;
    private boolean result = false;

    private String titleFilm;

    public AsyncTaskLoaderFilm(final Context context, String nameFilm)
    {
        super(context);

        onContentChanged();
        this.titleFilm = nameFilm;
    }

    @Override
    protected void onStartLoading()
    {
        if(takeContentChanged())
        {
            forceLoad();
        }
        else if(result)
        {
            deliverResult(dataFilm);
        }
    }

    @Override
    public void deliverResult(Vector<FilmItems> data)
    {
        dataFilm = data;
        result = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset()
    {
        super.onReset();
        onStopLoading();
        if(result)
        {
            onReleaseResource(dataFilm);
            dataFilm = null;
            result = false;
        }

    }

    private static final String API_KEY = "70da25e37e7e5105ac0b8484979d4056";

    @Override
    public Vector<FilmItems> loadInBackground()
    {
        SyncHttpClient client = new SyncHttpClient();

        final Vector<FilmItems> filmItemses = new Vector<>();

        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +API_KEY+"&language=en-US&query="+titleFilm;

        client.get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onStart()
            {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                try
                {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for(int i=0; i<list.length(); i++)
                    {
                        JSONObject film = list.getJSONObject(i);
                        FilmItems filmItems = new FilmItems(film);
                        filmItemses.add(filmItems);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {

            }
        });
        return filmItemses;
    }

    private Vector<FilmItems> onReleaseResource(Vector<FilmItems> data)
    {
        return null;
    }
}