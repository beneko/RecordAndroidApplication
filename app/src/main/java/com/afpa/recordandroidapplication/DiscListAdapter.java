package com.afpa.recordandroidapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DiscListAdapter extends BaseAdapter {

    private List<Disc> discs;
    private LayoutInflater layoutInflater;
    private Context context;

    static class ViewHolder{
        TextView discTitle, discLabel, discYear, discGenre, artistName;
        ImageView discImage;
    }

    /**
     * Constructeur à 2 paramètres
     * @param discs
     * @param context
     */

    public DiscListAdapter(List<Disc> discs, Context context) {
        this.discs = discs;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("unliste", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    @Override
    public int getCount() {
        return discs.size();
    }

    @Override
    public Object getItem(int position) {
        return discs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.disc_list,null);
            holder = new ViewHolder();
            holder.discImage = (ImageView) convertView.findViewById(R.id.discImage);
            holder.discTitle = (TextView) convertView.findViewById(R.id.discTitle);
            holder.discLabel = (TextView) convertView.findViewById(R.id.discLabel);
            holder.discYear = (TextView) convertView.findViewById(R.id.discYear);
            holder.discGenre = (TextView) convertView.findViewById(R.id.discGenre);
            holder.artistName = (TextView) convertView.findViewById(R.id.artistName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Disc disc = this.discs.get(positon);
        holder.discTitle.setText(disc.getTitle());
        holder.discLabel.setText(disc.getLabel());
        holder.discYear.setText(disc.getYear());
        holder.discGenre.setText(disc.getGenre());
        holder.artistName.setText(disc.getArtist());

        int imageId = this.getDrawableResIdByName(disc.getPicture());

        holder.discImage.setImageResource(imageId);

        return convertView;
    }

}
