package ru.romazanov.app_7862;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dagger.hilt.android.AndroidEntryPoint;
import ru.romazanov.app_7862.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}