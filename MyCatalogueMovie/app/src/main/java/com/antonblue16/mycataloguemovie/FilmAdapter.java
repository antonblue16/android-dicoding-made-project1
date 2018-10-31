package com.antonblue16.mycataloguemovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Vector;

public class FilmAdapter extends BaseAdapter
{
    private Vector<FilmItems> dataFilm = new Vector<>();
    private LayoutInflater mInflater;
    private Context context;

    public FilmAdapter(Context context)
    {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(Vector<FilmItems> items)
    {
        dataFilm = items;
        notifyDataSetChanged();
    }

    public void clearData()
    {
        dataFilm.clear();
    }

    public void addItem(final FilmItems item)
    {
        dataFilm.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public int getCount()
    {
        if(dataFilm == null)
        {
            return 0;
        }
        return dataFilm.size();
    }

    @Override
    public FilmItems getItem(int position)
    {
        return dataFilm.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder holder = null;

        if(view == null)
        {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.film_items, null);
            holder.textViewFilmName = view.findViewById(R.id.tvNameFilm);
            holder.textViewFilmSynopsis = view.findViewById(R.id.tvSynopsisFilm);
            holder.textViewFilmDateTime = view.findViewById(R.id.tvdDateTimeFilm);
            holder.imageViewImageFilm = view.findViewById(R.id.ivImageFilm);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.textViewFilmName.setText(dataFilm.get(position).getFilmName());
        holder.textViewFilmSynopsis.setText(dataFilm.get(position).getFilmSynopsis());
        holder.textViewFilmDateTime.setText(dataFilm.get(position).getFilmDateTime());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/"+
                dataFilm.get(position).getImageFilm())
                .into(holder.imageViewImageFilm);

        return view;
    }

    private static class ViewHolder
    {
        TextView textViewFilmName;
        TextView textViewFilmSynopsis;
        TextView textViewFilmDateTime;
        ImageView imageViewImageFilm;
    }
}
