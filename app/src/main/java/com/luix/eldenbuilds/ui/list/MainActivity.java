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
import com.luix.eldenbuilds.ui.viewmodel.BuildViewModel;

public class MainActivity extends AppCompatActivity {

    private BuildViewModel buildViewModel;

    private final ActivityResultLauncher<Intent> addBuildLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Build newBuild = (Build) result.getData().getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD);
                    buildViewModel.insert(newBuild);
                    Toast.makeText(this, "Build salva com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Build não salva", Toast.LENGTH_SHORT).show();
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

        // Listener de clique no item (apenas um log visual por enquanto)
        adapter.setOnItemClickListener(build -> Toast.makeText(MainActivity.this, "Clicou em: " + build.getName(), Toast.LENGTH_SHORT).show());

        buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);
        buildViewModel.getAllBuilds().observe(this, adapter::submitList);
    }
}