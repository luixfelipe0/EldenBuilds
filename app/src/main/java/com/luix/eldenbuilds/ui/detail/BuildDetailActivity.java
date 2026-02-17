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

        String rHand = build.getRightHandWeapon().isEmpty() ? getString(R.string.empty_slot) : build.getRightHandWeapon();
        String lHand = build.getLeftHandWeapon().isEmpty() ? getString(R.string.empty_slot) : build.getLeftHandWeapon();
        textWeapons.setText(getString(R.string.format_weapons_detail, rHand, lHand));

        String helm = build.getHeadArmor().isEmpty() ? getString(R.string.empty_slot) : build.getHeadArmor();
        String chest = build.getChestArmor().isEmpty() ? getString(R.string.empty_slot) : build.getChestArmor();
        String hands = build.getHandsArmor().isEmpty() ? getString(R.string.empty_slot) : build.getHandsArmor();
        String legs = build.getLegsArmor().isEmpty() ? getString(R.string.empty_slot) : build.getLegsArmor();
        textArmor.setText(getString(R.string.format_armor_detail, helm, chest, hands, legs));

        String t1 = build.getTalisman1().isEmpty() ? getString(R.string.empty_slot) : build.getTalisman1();
        String t2 = build.getTalisman2().isEmpty() ? getString(R.string.empty_slot) : build.getTalisman2();
        String t3 = build.getTalisman3().isEmpty() ? getString(R.string.empty_slot) : build.getTalisman3();
        String t4 = build.getTalisman4().isEmpty() ? getString(R.string.empty_slot) : build.getTalisman4();
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}