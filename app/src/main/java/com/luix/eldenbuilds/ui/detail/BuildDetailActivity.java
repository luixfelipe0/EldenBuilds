package com.luix.eldenbuilds.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.model.Stats;

public class BuildDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_detail);

        setSupportActionBar(findViewById(R.id.toolbar_detail));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Build build = (Build) getIntent().getSerializableExtra(AddEditBuildActivity.EXTRA_BUILD);

        if (build != null) {
            populateUI(build);
        }
    }

    private void populateUI(Build build) {
        TextView textName = findViewById(R.id.text_detail_name);
        TextView textClass = findViewById(R.id.text_detail_class);
        TextView textLevel = findViewById(R.id.text_detail_level);

        textName.setText(build.getName());
        String className = build.getStartingClass() != null ? build.getStartingClass().getDisplayName() : "Unknown";
        textClass.setText("Class: " + className);
        textLevel.setText("Level " + build.getLevel());

        // Stats
        Stats stats = build.getStats();
        if (stats != null) {
            ((TextView) findViewById(R.id.text_stat_vigor)).setText("Vigor: " + stats.getVigor());
            ((TextView) findViewById(R.id.text_stat_mind)).setText("Mind: " + stats.getMind());
            ((TextView) findViewById(R.id.text_stat_endurance)).setText("Endurance: " + stats.getEndurance());
            ((TextView) findViewById(R.id.text_stat_strength)).setText("Strength: " + stats.getStrength());
            ((TextView) findViewById(R.id.text_stat_dexterity)).setText("Dexterity: " + stats.getDexterity());
            ((TextView) findViewById(R.id.text_stat_intelligence)).setText("Intelligence: " + stats.getIntelligence());
            ((TextView) findViewById(R.id.text_stat_faith)).setText("Faith: " + stats.getFaith());
            ((TextView) findViewById(R.id.text_stat_arcane)).setText("Arcane: " + stats.getArcane());
        }

        TextView textWeapons = findViewById(R.id.text_equip_weapons);
        TextView textArmor = findViewById(R.id.text_equip_armor);
        TextView textTalismans = findViewById(R.id.text_equip_talismans);

        String rHand = build.getRightHandWeapon().isEmpty() ? "-" : build.getRightHandWeapon();
        String lHand = build.getLeftHandWeapon().isEmpty() ? "-" : build.getLeftHandWeapon();
        textWeapons.setText("Weapons: " + rHand + " (R) / " + lHand + " (L)");

        String helm = build.getHeadArmor().isEmpty() ? "-" : build.getHeadArmor();
        String chest = build.getChestArmor().isEmpty() ? "-" : build.getChestArmor();
        String hands = build.getHandsArmor().isEmpty() ? "-" : build.getHandsArmor();
        String legs = build.getLegsArmor().isEmpty() ? "-" : build.getLegsArmor();
        textArmor.setText("Armor: " + helm + ", " + chest + ", " + hands + ", " + legs);

        String t1 = build.getTalisman1().isEmpty() ? "-" : build.getTalisman1();
        String t2 = build.getTalisman2().isEmpty() ? "-" : build.getTalisman2();
        String t3 = build.getTalisman3().isEmpty() ? "-" : build.getTalisman3();
        String t4 = build.getTalisman4().isEmpty() ? "-" : build.getTalisman4();
        textTalismans.setText("Talismans: " + t1 + ", " + t2 + ", " + t3 + ", " + t4);

        MaterialCardView cardNotes = findViewById(R.id.card_notes);
        TextView textNotes = findViewById(R.id.text_detail_notes);

        if (build.getNotes() == null || build.getNotes().trim().isEmpty()) {
            cardNotes.setVisibility(View.GONE);
        } else {
            cardNotes.setVisibility(View.VISIBLE);
            textNotes.setText(build.getNotes());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}