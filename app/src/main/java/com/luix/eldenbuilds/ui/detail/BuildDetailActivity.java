package com.luix.eldenbuilds.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.model.Stats;
import com.luix.eldenbuilds.ui.viewmodel.BuildViewModel;

public class BuildDetailActivity extends AppCompatActivity {

    private Build currentBuild;
    private BuildViewModel buildViewModel;

    private final ActivityResultLauncher<Intent> editBuildLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Build updatedBuild = (Build) result.getData().getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD);

                    buildViewModel.update(updatedBuild);

                    this.currentBuild = updatedBuild;
                    populateUI(updatedBuild);

                    Toast.makeText(this, "Build atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_detail);

        buildViewModel = new ViewModelProvider(this).get(BuildViewModel.class);

        currentBuild = (Build) getIntent().getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD);

        if (currentBuild != null) {
            populateUI(currentBuild);
        }

        setupToolbar();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar_detail);

        toolbar.setNavigationOnClickListener(v -> finish());

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit) {
                editBuild();
                return true;
            } else if (item.getItemId() == R.id.action_delete) {
                confirmDelete();
                return true;
            }
            return false;
        });
    }

    private void editBuild() {
        Intent intent = new Intent(BuildDetailActivity.this, AddEditBuildActivity.class);
        intent.putExtra(AddEditBuildActivity.EXTRA_BUILD, currentBuild);
        editBuildLauncher.launch(intent);
    }

    private void confirmDelete() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.action_yes, (dialog, which) -> {
                    buildViewModel.delete(currentBuild);
                    Toast.makeText(this, "Build deletada", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton(R.string.action_no, null)
                .show();
    }

    private void populateUI(Build build) {
        TextView textName = findViewById(R.id.text_detail_name);
        TextView textClass = findViewById(R.id.text_detail_class);
        TextView textLevel = findViewById(R.id.text_detail_level);

        textName.setText(build.getName());
        String className = build.getStartingClass() != null ? build.getStartingClass().getDisplayName() : getString(R.string.unknown);

        textClass.setText(getString(R.string.format_class_detail, className));
        textLevel.setText(getString(R.string.format_level_detail, build.getLevel()));

        // Stats
        Stats stats = build.getStats();
        if (stats != null) {
            ((TextView) findViewById(R.id.text_stat_vigor)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_vigor), stats.getVigor()));
            ((TextView) findViewById(R.id.text_stat_mind)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_mind), stats.getMind()));
            ((TextView) findViewById(R.id.text_stat_endurance)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_endurance), stats.getEndurance()));
            ((TextView) findViewById(R.id.text_stat_strength)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_strength), stats.getStrength()));
            ((TextView) findViewById(R.id.text_stat_dexterity)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_dexterity), stats.getDexterity()));
            ((TextView) findViewById(R.id.text_stat_intelligence)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_intelligence), stats.getIntelligence()));
            ((TextView) findViewById(R.id.text_stat_faith)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_faith), stats.getFaith()));
            ((TextView) findViewById(R.id.text_stat_arcane)).setText(getString(R.string.format_stat_detail, getString(R.string.stat_arcane), stats.getArcane()));
        }

        TextView textWeapons = findViewById(R.id.text_equip_weapons);
        TextView textArmor = findViewById(R.id.text_equip_armor);
        TextView textTalismans = findViewById(R.id.text_equip_talismans);

        String rHand = build.getRightHandWeapon() != null && !build.getRightHandWeapon().isEmpty() ? build.getRightHandWeapon() : getString(R.string.empty_slot);
        String lHand = build.getLeftHandWeapon() != null && !build.getLeftHandWeapon().isEmpty() ? build.getLeftHandWeapon() : getString(R.string.empty_slot);
        textWeapons.setText(getString(R.string.format_weapons_detail, rHand, lHand));

        String helm = build.getHeadArmor() != null && !build.getHeadArmor().isEmpty() ? build.getHeadArmor() : getString(R.string.empty_slot);
        String chest = build.getChestArmor() != null && !build.getChestArmor().isEmpty() ? build.getChestArmor() : getString(R.string.empty_slot);
        String hands = build.getHandsArmor() != null && !build.getHandsArmor().isEmpty() ? build.getHandsArmor() : getString(R.string.empty_slot);
        String legs = build.getLegsArmor() != null && !build.getLegsArmor().isEmpty() ? build.getLegsArmor() : getString(R.string.empty_slot);
        textArmor.setText(getString(R.string.format_armor_detail, helm, chest, hands, legs));

        String t1 = build.getTalisman1() != null && !build.getTalisman1().isEmpty() ? build.getTalisman1() : getString(R.string.empty_slot);
        String t2 = build.getTalisman2() != null && !build.getTalisman2().isEmpty() ? build.getTalisman2() : getString(R.string.empty_slot);
        String t3 = build.getTalisman3() != null && !build.getTalisman3().isEmpty() ? build.getTalisman3() : getString(R.string.empty_slot);
        String t4 = build.getTalisman4() != null && !build.getTalisman4().isEmpty() ? build.getTalisman4() : getString(R.string.empty_slot);
        textTalismans.setText(getString(R.string.format_talismans_detail, t1, t2, t3, t4));

        MaterialCardView cardNotes = findViewById(R.id.card_notes);
        TextView textNotes = findViewById(R.id.text_detail_notes);

        if (build.getNotes() == null || build.getNotes().trim().isEmpty()) {
            cardNotes.setVisibility(View.GONE);
        } else {
            cardNotes.setVisibility(View.VISIBLE);
            textNotes.setText(build.getNotes());
        }
    }

}