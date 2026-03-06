package com.luix.eldenbuilds.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.ui.adapter.BuildAdapter;
import com.luix.eldenbuilds.ui.detail.AddEditBuildActivity;
import com.luix.eldenbuilds.ui.detail.BuildDetailActivity;
import com.luix.eldenbuilds.ui.viewmodel.BuildViewModel;

public class MainActivity extends AppCompatActivity {

    private BuildViewModel buildViewModel;

    private final ActivityResultLauncher<Intent> addBuildLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Build newBuild = getBuildExtra(result.getData());
                    if (newBuild == null) {
                        Toast.makeText(this, R.string.error_invalid_build_payload, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    buildViewModel.insert(newBuild);
                    Toast.makeText(this, R.string.toast_build_saved, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.toast_build_not_saved, Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBuild = findViewById(R.id.button_add_build);
        buttonAddBuild.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditBuildActivity.class);
            addBuildLauncher.launch(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BuildAdapter adapter = new BuildAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(build -> {
            Intent intent = new Intent(MainActivity.this, BuildDetailActivity.class);
            intent.putExtra(AddEditBuildActivity.EXTRA_BUILD, build);
            startActivity(intent);
        });

        buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);
        buildViewModel.getAllBuilds().observe(this, adapter::submitList);
    }

    @SuppressWarnings("deprecation")
    private Build getBuildExtra(Intent intent) {
        if (intent == null) return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            return intent.getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD, Build.class);
        }
        Object extra = intent.getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD);
        return extra instanceof Build ? (Build) extra : null;
    }
}
