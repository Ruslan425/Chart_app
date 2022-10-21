package ru.romazanov.app_7862;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import ru.romazanov.app_7862.databinding.ActivityMainBinding;
import ru.romazanov.app_7862.model.Point;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        LiveData<ArrayList<Point>> dataList = viewModel.getDataList();

        dataList.observe(this, new Observer<ArrayList<Point>> () {
            @Override
            public void onChanged(ArrayList<Point> str) {
                binding.text.setText(str.toString());
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.test(2);
            }
        });

        setContentView(view);
    }


}