package com.antonblue16.mycataloguemovie;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONObject;

public class FilmItems
{
    private String filmName;
    private String filmSynopsis;
    private String filmDateTime;
    private String imageFilm;
    private String filmRate;
    private String filmLanguage;
    private String filmStatus;

    public FilmItems(JSONObject object)
    {
        try
        {
            String name = object.getString("title");
            String synopsis = object.getString("overview");
            String dateTime = object.getString("release_date");
            String image = object.getString("poster_path");
            String rate = object.getString("vote_average");
            String language = object.getString("original_language");
            //String status = object.getString("status");

            this.filmName = name;
            this.filmSynopsis = synopsis;
            this.filmDateTime = dateTime;
            this.imageFilm = image;
            this.filmRate = rate;
            this.filmLanguage = language;
            //this.filmStatus = status;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getFilmName()
    {
        return filmName;
    }

    public void setFilmName(String filmName)
    {
        this.filmName = filmName;
    }

    public String getFilmSynopsis()
    {
        return filmSynopsis;
    }

    public void setFilmSynopsis(String filmSynopsis)
    {
        this.filmSynopsis = filmSynopsis;
    }

    public String getFilmDateTime()
    {
        return filmDateTime;
    }

    public void setFilmDateTime(String filmDateTime)
    {
        this.filmDateTime = filmDateTime;
    }

    public String getImageFilm()
    {
        return imageFilm;
    }

    public void setImageFilm(String imageFilm)
    {
        this.imageFilm = imageFilm;
    }

    public String getFilmRate()
    {
        return filmRate;
    }

    public void setFilmRate(String filmRate)
    {
        this.filmRate = filmRate;
    }

    public String getFilmLanguage()
    {
        return filmLanguage;
    }

    public void setFilmLanguage(String filmLanguage)
    {
        this.filmLanguage = filmLanguage;
    }

    public String getFilmStatus()
    {
        return filmStatus;
    }

    public void setFilmStatus(String filmStatus)
    {
        this.filmStatus = filmStatus;
    }
}
