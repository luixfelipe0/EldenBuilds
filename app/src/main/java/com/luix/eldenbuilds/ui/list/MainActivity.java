package com.luix.eldenbuilds.ui.list;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.ui.adapter.BuildAdapter;
import com.luix.eldenbuilds.ui.viewmodel.BuildViewModel;

public class MainActivity extends AppCompatActivity {

    private BuildViewModel buildViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBuild = findViewById(R.id.button_add_build);
        buttonAddBuild.setOnClickListener(v -> {
            // Por enquanto, apenas um Toast. Futuramente a Activity de Adicionar.
            Toast.makeText(MainActivity.this, "Navegar para Criar Build", Toast.LENGTH_SHORT).show();
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final BuildAdapter adapter = new BuildAdapter();
        recyclerView.setAdapter(adapter);

        // Listener de clique no item (para editar futuramente)
        adapter.setOnItemClickListener(build ->
                Toast.makeText(MainActivity.this, "Clicou em: " + build.getName(), Toast.LENGTH_SHORT).show());

        // O ViewModelProvider garante que pegamos a instância existente se a tela apenas girou.
        buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);

        // Atualiza a lista do adapter. O DiffUtil calcula a diferença em background.
        buildViewModel.getAllBuilds().observe(this, adapter::submitList);
    }
}