package cl.municipalidadsanpedrodelapaz.smartapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cl.municipalidadsanpedrodelapaz.smartapp.Config;
import cl.municipalidadsanpedrodelapaz.smartapp.R;
import cl.municipalidadsanpedrodelapaz.smartapp.utilities.Pojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavorite extends ArrayAdapter<Pojo> {

    private Activity activity;
    private List<Pojo> item;
    Pojo object;
    private int row;
    //public ImageLoader imageLoader;

    public AdapterFavorite(Activity act, int resource, List<Pojo> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.item = arrayList;
        //imageLoader = new ImageLoader(activity);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((item == null) || ((position + 1) > item.size()))
            return view;

        object = item.get(position);

        holder.title = (TextView) view.findViewById(R.id.news_title);
        holder.date = (TextView) view.findViewById(R.id.news_date);
        holder.image = (ImageView) view.findViewById(R.id.news_image);

        Typeface font1 = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.title.setTypeface(font1);

        Typeface font2 = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        holder.date.setTypeface(font2);

        holder.title.setText(object.getNewsHeading());
        holder.date.setText(object.getNewsDate());
        //imageLoader.DisplayImage(Config.SERVER_URL + "/upload/thumbs/" + object.getNewsImage(), holder.image);

        Picasso.with(getContext()).load(Config.SERVER_URL + "/upload/thumbs/" +
                object.getNewsImage()).placeholder(R.drawable.ic_thumbnail).into(holder.image);

        return view;

    }

    public class ViewHolder {

        public ImageView image;
        public TextView title;
        public TextView date;

    }
}