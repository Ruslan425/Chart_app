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
import java.util.ArrayList;
import dagger.hilt.android.AndroidEntryPoint;
import ru.romazanov.app_7862.R;
import ru.romazanov.app_7862.databinding.FragmentChartBinding;
import ru.romazanov.app_7862.model.Point;

@AndroidEntryPoint
public class ChartFragment extends Fragment {

    private ChartViewModel mViewModel;
    private FragmentChartBinding binding;
    private ArrayList<Point> points;

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

        if (points != null) {
            Toast.makeText(getContext(), String.valueOf(points.size()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Пусто", Toast.LENGTH_SHORT).show();
        }
    }

}