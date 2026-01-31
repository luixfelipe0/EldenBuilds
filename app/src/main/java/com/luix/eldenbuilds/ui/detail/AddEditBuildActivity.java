package com.luix.eldenbuilds.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.model.StartingClass;
import com.luix.eldenbuilds.data.model.Stats;

import java.util.ArrayList;
import java.util.List;

public class AddEditBuildActivity extends AppCompatActivity {

    public static final String EXTRA_BUILD = "com.luix.eldenbuilds.EXTRA_BUILD";

    private TextInputEditText editTextName;
    private AutoCompleteTextView spinnerClass;
    private TextInputEditText editTextLevel;

    // Stats Inputs
    private TextInputEditText editVigor, editMind, editEndurance, editStrength,
            editDexterity, editIntelligence, editFaith, editArcane;

    private MaterialButton buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_build);

        initViews();
        setupClassSpinner();

        setSupportActionBar(findViewById(R.id.topAppBar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        }
        buttonSave.setOnClickListener(v -> saveBuild());
    }

    private void initViews() {
        editTextName = findViewById(R.id.edit_text_name);
        spinnerClass = findViewById(R.id.spinner_class);
        editTextLevel = findViewById(R.id.edit_text_level);

        editVigor = findViewById(R.id.edit_vigor);
        editMind = findViewById(R.id.edit_mind);
        editEndurance = findViewById(R.id.edit_endurance);
        editStrength = findViewById(R.id.edit_strength);
        editDexterity = findViewById(R.id.edit_dexterity);
        editIntelligence = findViewById(R.id.edit_intelligence);
        editFaith = findViewById(R.id.edit_faith);
        editArcane = findViewById(R.id.edit_arcane);

        buttonSave = findViewById(R.id.button_save);
    }

    private void setupClassSpinner() {
        List<String> classNames = new ArrayList<>();
        for (StartingClass startClass : StartingClass.values()) {
            classNames.add(startClass.getDisplayName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, classNames);

        spinnerClass.setAdapter(adapter);
        spinnerClass.setText(StartingClass.VAGABOND.getDisplayName(), false);
    }

    private void saveBuild() {
        String name = String.valueOf(editTextName.getText());
        String className = String.valueOf(spinnerClass.getText());

        if (name.trim().isEmpty()) {
            editTextName.setError("Please insert a name");
            return;
        }

        int level = parseStat(editTextLevel);
        int vigor = parseStat(editVigor);
        int mind = parseStat(editMind);
        int endurance = parseStat(editEndurance);
        int strength = parseStat(editStrength);
        int dexterity = parseStat(editDexterity);
        int intelligence = parseStat(editIntelligence);
        int faith = parseStat(editFaith);
        int arcane = parseStat(editArcane);

        StartingClass selectedClass = StartingClass.VAGABOND; // Default
        for (StartingClass sc : StartingClass.values()) {
            if (sc.getDisplayName().equals(className)) {
                selectedClass = sc;
                break;
            }
        }

        Stats stats = new Stats(vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane);

        Build newBuild = new Build(name, selectedClass, level);
        newBuild.setStats(stats);

        Intent data = new Intent();
        data.putExtra(EXTRA_BUILD, newBuild);

        setResult(RESULT_OK, data);
        finish();
    }

    private int parseStat(TextInputEditText editText) {
        String value = String.valueOf(editText.getText());
        if (TextUtils.isEmpty(value)) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}