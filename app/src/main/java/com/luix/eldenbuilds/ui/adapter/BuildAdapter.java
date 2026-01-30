package com.luix.eldenbuilds.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luix.eldenbuilds.R;
import com.luix.eldenbuilds.data.model.Build;

public class BuildAdapter extends ListAdapter<Build, BuildAdapter.BuildHolder> {

    private OnItemClickListener listener;

    public BuildAdapter() {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull Build oldItem, @NonNull Build newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Build oldItem, @NonNull Build newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public BuildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_build, parent, false);
        return new BuildHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildHolder holder, int position) {
        Build currentBuild = getItem(position);
        Context context = holder.itemView.getContext();

        holder.textViewName.setText(currentBuild.getName());
        holder.textViewLevel.setText(context.getString(R.string.text_level_format, currentBuild.getLevel()));

        String className = currentBuild.getStartingClass() != null
                ? currentBuild.getStartingClass().getDisplayName()
                : context.getString(R.string.unknown);
        holder.textViewClass.setText(context.getString(R.string.text_class_format, className));

        String rHand = currentBuild.getRightHandWeapon() != null ? currentBuild.getRightHandWeapon() : context.getString(R.string.empty_slot);
        String lHand = currentBuild.getLeftHandWeapon() != null ? currentBuild.getLeftHandWeapon() : context.getString(R.string.empty_slot);
        holder.textViewWeapons.setText(context.getString(R.string.text_weapons_format, rHand, lHand));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentBuild);
            }
        });
    }

    public static class BuildHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewLevel;
        private final TextView textViewClass;
        private final TextView textViewWeapons;

        public BuildHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_build_name);
            textViewLevel = itemView.findViewById(R.id.text_view_level);
            textViewClass = itemView.findViewById(R.id.text_view_class);
            textViewWeapons = itemView.findViewById(R.id.text_view_weapons);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Build build);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}