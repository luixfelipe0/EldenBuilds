package com.luix.eldenbuilds.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.model.StartingClass;
import com.luix.eldenbuilds.data.model.Stats;
import com.luix.eldenbuilds.domain.BuildLevelCalculator;

import java.util.ArrayList;
import java.util.List;

public class AddEditBuildActivity extends AppCompatActivity {

    public static final String EXTRA_BUILD = "com.luix.eldenbuilds.EXTRA_BUILD";

    private TextInputEditText editTextName;
    private AutoCompleteTextView spinnerClass;
    private TextInputEditText editTextLevel;

    private TextInputEditText editVigor;
    private TextInputEditText editMind;
    private TextInputEditText editEndurance;
    private TextInputEditText editStrength;
    private TextInputEditText editDexterity;
    private TextInputEditText editIntelligence;
    private TextInputEditText editFaith;
    private TextInputEditText editArcane;

    private AutoCompleteTextView editWeaponR;
    private AutoCompleteTextView editWeaponL;
    private AutoCompleteTextView editHelm;
    private AutoCompleteTextView editChest;
    private AutoCompleteTextView editHands;
    private AutoCompleteTextView editLegs;
    private AutoCompleteTextView editTal1;
    private AutoCompleteTextView editTal2;
    private AutoCompleteTextView editTal3;
    private AutoCompleteTextView editTal4;
    private TextInputEditText editNotes;

    private MaterialButton buttonSave;

    private String currentBuildId = null;
    private StartingClass currentSelectedClass = StartingClass.VAGABOND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_build);

        initViews();
        setupClassSpinner();
        setupAutoLevelCalculation();
        setupAutoCompletes();

        setSupportActionBar(findViewById(R.id.topAppBar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        }

        buttonSave.setOnClickListener(v -> saveBuild());

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_BUILD)) {
            Build buildToEdit = getBuildExtra(intent);
            if (buildToEdit != null) {
                currentBuildId = buildToEdit.getId();
                populateForEdit(buildToEdit);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.title_edit_build);
                }
            }
        }
    }

    private void setupAutoCompletes() {
        String[] weapons = getResources().getStringArray(R.array.seed_weapons);
        String[] helms = getResources().getStringArray(R.array.seed_armor_helms);
        String[] chests = getResources().getStringArray(R.array.seed_armor_chests);
        String[] hands = getResources().getStringArray(R.array.seed_armor_hands);
        String[] legs = getResources().getStringArray(R.array.seed_armor_legs);
        String[] talismans = getResources().getStringArray(R.array.seed_talismans);

        int layout = android.R.layout.simple_dropdown_item_1line;

        ArrayAdapter<String> weaponAdapter = new ArrayAdapter<>(this, layout, weapons);
        ArrayAdapter<String> helmAdapter = new ArrayAdapter<>(this, layout, helms);
        ArrayAdapter<String> chestAdapter = new ArrayAdapter<>(this, layout, chests);
        ArrayAdapter<String> handsAdapter = new ArrayAdapter<>(this, layout, hands);
        ArrayAdapter<String> legsAdapter = new ArrayAdapter<>(this, layout, legs);
        ArrayAdapter<String> talismanAdapter = new ArrayAdapter<>(this, layout, talismans);

        editWeaponR.setAdapter(weaponAdapter);
        editWeaponL.setAdapter(weaponAdapter);

        editHelm.setAdapter(helmAdapter);
        editChest.setAdapter(chestAdapter);
        editHands.setAdapter(handsAdapter);
        editLegs.setAdapter(legsAdapter);

        editTal1.setAdapter(talismanAdapter);
        editTal2.setAdapter(talismanAdapter);
        editTal3.setAdapter(talismanAdapter);
        editTal4.setAdapter(talismanAdapter);
    }

    private void populateForEdit(Build build) {
        editTextName.setText(build.getName());

        if (build.getStartingClass() != null) {
            spinnerClass.setText(build.getStartingClass().getDisplayName(), false);
            currentSelectedClass = build.getStartingClass();
        }

        if (build.getStats() != null) {
            editVigor.setText(String.valueOf(build.getStats().getVigor()));
            editMind.setText(String.valueOf(build.getStats().getMind()));
            editEndurance.setText(String.valueOf(build.getStats().getEndurance()));
            editStrength.setText(String.valueOf(build.getStats().getStrength()));
            editDexterity.setText(String.valueOf(build.getStats().getDexterity()));
            editIntelligence.setText(String.valueOf(build.getStats().getIntelligence()));
            editFaith.setText(String.valueOf(build.getStats().getFaith()));
            editArcane.setText(String.valueOf(build.getStats().getArcane()));
        }

        editWeaponR.setText(build.getRightHandWeapon());
        editWeaponL.setText(build.getLeftHandWeapon());
        editHelm.setText(build.getHeadArmor());
        editChest.setText(build.getChestArmor());
        editHands.setText(build.getHandsArmor());
        editLegs.setText(build.getLegsArmor());
        editTal1.setText(build.getTalisman1());
        editTal2.setText(build.getTalisman2());
        editTal3.setText(build.getTalisman3());
        editTal4.setText(build.getTalisman4());
        editNotes.setText(build.getNotes());

        recalculateLevel();
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

        editWeaponR = findViewById(R.id.edit_weapon_r);
        editWeaponL = findViewById(R.id.edit_weapon_l);
        editHelm = findViewById(R.id.edit_armor_helm);
        editChest = findViewById(R.id.edit_armor_chest);
        editHands = findViewById(R.id.edit_armor_hands);
        editLegs = findViewById(R.id.edit_armor_legs);
        editTal1 = findViewById(R.id.edit_talisman_1);
        editTal2 = findViewById(R.id.edit_talisman_2);
        editTal3 = findViewById(R.id.edit_talisman_3);
        editTal4 = findViewById(R.id.edit_talisman_4);
        editNotes = findViewById(R.id.edit_notes);

        buttonSave = findViewById(R.id.button_save);
    }

    private void setupClassSpinner() {
        List<String> classNames = new ArrayList<>();
        for (StartingClass startClass : StartingClass.values()) {
            classNames.add(startClass.getDisplayName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                classNames
        );

        spinnerClass.setAdapter(adapter);

        spinnerClass.setOnItemClickListener((parent, view, position, id) -> {
            String selection = (String) parent.getItemAtPosition(position);
            updateBaseStatsForClass(selection);
        });

        spinnerClass.setText(StartingClass.VAGABOND.getDisplayName(), false);
        updateBaseStatsForClass(StartingClass.VAGABOND.getDisplayName());
    }

    private void updateBaseStatsForClass(String className) {
        for (StartingClass sc : StartingClass.values()) {
            if (sc.getDisplayName().equals(className)) {
                currentSelectedClass = sc;
                editVigor.setText(String.valueOf(sc.baseVigor));
                editMind.setText(String.valueOf(sc.baseMind));
                editEndurance.setText(String.valueOf(sc.baseEndurance));
                editStrength.setText(String.valueOf(sc.baseStrength));
                editDexterity.setText(String.valueOf(sc.baseDexterity));
                editIntelligence.setText(String.valueOf(sc.baseIntelligence));
                editFaith.setText(String.valueOf(sc.baseFaith));
                editArcane.setText(String.valueOf(sc.baseArcane));

                recalculateLevel();
                break;
            }
        }
    }

    private void setupAutoLevelCalculation() {
        TextWatcher statsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recalculateLevel();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        editVigor.addTextChangedListener(statsWatcher);
        editMind.addTextChangedListener(statsWatcher);
        editEndurance.addTextChangedListener(statsWatcher);
        editStrength.addTextChangedListener(statsWatcher);
        editDexterity.addTextChangedListener(statsWatcher);
        editIntelligence.addTextChangedListener(statsWatcher);
        editFaith.addTextChangedListener(statsWatcher);
        editArcane.addTextChangedListener(statsWatcher);
    }

    private void recalculateLevel() {
        if (currentSelectedClass == null) return;

        int vig = parseStat(editVigor, currentSelectedClass.baseVigor);
        int min = parseStat(editMind, currentSelectedClass.baseMind);
        int end = parseStat(editEndurance, currentSelectedClass.baseEndurance);
        int str = parseStat(editStrength, currentSelectedClass.baseStrength);
        int dex = parseStat(editDexterity, currentSelectedClass.baseDexterity);
        int intl = parseStat(editIntelligence, currentSelectedClass.baseIntelligence);
        int fai = parseStat(editFaith, currentSelectedClass.baseFaith);
        int arc = parseStat(editArcane, currentSelectedClass.baseArcane);

        Stats currentStats = new Stats(vig, min, end, str, dex, intl, fai, arc);
        int calculatedLevel = BuildLevelCalculator.calculateLevel(currentSelectedClass, currentStats);

        editTextLevel.setText(String.valueOf(calculatedLevel));
    }

    private void saveBuild() {
        String name = String.valueOf(editTextName.getText());

        if (name.trim().isEmpty()) {
            editTextName.setError(getString(R.string.error_name_required));
            return;
        }

        int vigor = parseStat(editVigor, 0);
        int mind = parseStat(editMind, 0);
        int endurance = parseStat(editEndurance, 0);
        int strength = parseStat(editStrength, 0);
        int dexterity = parseStat(editDexterity, 0);
        int intelligence = parseStat(editIntelligence, 0);
        int faith = parseStat(editFaith, 0);
        int arcane = parseStat(editArcane, 0);
        int level = Integer.parseInt(String.valueOf(editTextLevel.getText()));

        Stats stats = new Stats(vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane);

        Build newBuild = new Build(name, currentSelectedClass, level);
        if (currentBuildId != null) {
            newBuild.setId(currentBuildId);
        }

        newBuild.setStats(stats);
        newBuild.setRightHandWeapon(getTextSafe(editWeaponR));
        newBuild.setLeftHandWeapon(getTextSafe(editWeaponL));
        newBuild.setHeadArmor(getTextSafe(editHelm));
        newBuild.setChestArmor(getTextSafe(editChest));
        newBuild.setHandsArmor(getTextSafe(editHands));
        newBuild.setLegsArmor(getTextSafe(editLegs));
        newBuild.setTalisman1(getTextSafe(editTal1));
        newBuild.setTalisman2(getTextSafe(editTal2));
        newBuild.setTalisman3(getTextSafe(editTal3));
        newBuild.setTalisman4(getTextSafe(editTal4));
        newBuild.setNotes(getTextSafe(editNotes));

        Intent data = new Intent();
        data.putExtra(EXTRA_BUILD, newBuild);

        setResult(RESULT_OK, data);
        finish();
    }

    private int parseStat(TextInputEditText editText, int fallback) {
        String value = String.valueOf(editText.getText());
        if (TextUtils.isEmpty(value)) {
            return fallback;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private String getTextSafe(android.widget.EditText editText) {
        return editText.getText() != null ? editText.getText().toString().trim() : "";
    }

    @SuppressWarnings("deprecation")
    private Build getBuildExtra(Intent intent) {
        if (intent == null) return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            return intent.getSerializableExtra(EXTRA_BUILD, Build.class);
        }
        Object extra = intent.getSerializableExtra(EXTRA_BUILD);
        return extra instanceof Build ? (Build) extra : null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
