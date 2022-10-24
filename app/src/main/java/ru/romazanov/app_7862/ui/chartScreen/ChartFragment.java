package ru.romazanov.app_7862.ui.chartScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dagger.hilt.android.AndroidEntryPoint;
import ru.romazanov.app_7862.databinding.FragmentChartBinding;
import ru.romazanov.app_7862.model.Point;

@AndroidEntryPoint
public class ChartFragment extends Fragment {

    private ChartViewModel mViewModel;
    private FragmentChartBinding binding;
    private ArrayList<Point> points;
    private LineChart lineChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(inflater, container, false);
        points = requireArguments().getParcelableArrayList("points");
        mViewModel = new ViewModelProvider(this).get(ChartViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.table.setAdapter(new ChartAdapter(getContext(), android.R.layout.simple_list_item_1, points));

        lineChart = binding.chart;
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        List<Entry> newArray = points.stream().map(Point::pointToEntry).collect(Collectors.toList());


        LineDataSet set1 = new LineDataSet(newArray, "Data");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);

    }

}