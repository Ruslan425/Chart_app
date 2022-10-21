package ru.romazanov.app_7862.ui.chartScreen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import ru.romazanov.app_7862.model.Point;

public class ChartAdapter extends ArrayAdapter<Point> {

    Context context;

    ArrayList<Point> points;

    public ChartAdapter(@NonNull Context context, int resource, ArrayList<Point> points) {
        super(context, resource, points);
        this.context = context;
        this.points = points;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView item = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(context);
            item = (TextView) convertView;
        }
        String x = "X: " + points.get(position).getX();
        String y = "Y: " + points.get(position).getY();
        String text = x + " " + y;
        item.setText(text);
        return (convertView);
    }

}
