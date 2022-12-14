package ru.romazanov.app_7862.ui.startScreen;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import dagger.hilt.android.AndroidEntryPoint;
import ru.romazanov.app_7862.R;
import ru.romazanov.app_7862.databinding.FragmentStartBinding;
import ru.romazanov.app_7862.model.Point;

@AndroidEntryPoint
public class StartFragment extends Fragment {

    private StartViewModel mViewModel;
    private FragmentStartBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(StartViewModel.class);
        verifyStoragePermissions(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.points.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), R.string.point_value, Toast.LENGTH_SHORT).show();
                } else {
                    int points = Integer.parseInt(binding.points.getText().toString());
                    mViewModel.getPoint(points);
                }

            }
        });

        mViewModel.getSingle().observe(getViewLifecycleOwner(),new Observer<ArrayList<Point>>() {
            @Override
            public void onChanged(ArrayList<Point> points) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("points", points);
                navController.navigate(R.id.action_startFragment_to_chartFragment, bundle);
            }
        });

        mViewModel.error().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );

        }
    }
}