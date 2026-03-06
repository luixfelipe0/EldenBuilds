package com.luix.eldenbuilds.data.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.luix.eldenbuilds.data.model.Build;

import java.util.ArrayList;
import java.util.List;

public class BuildRepository {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference buildsRef = db.collection("builds");
    private final MutableLiveData<List<Build>> allBuildsLiveData = new MutableLiveData<>();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public BuildRepository() {
        startListeningToBuilds();
    }

    private void startListeningToBuilds() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Log.w("BuildRepository", "Usuário não autenticado. Interrompendo escuta do banco.");
            return;
        }

        String currentUserId = currentUser.getUid();

        buildsRef.whereEqualTo("authorId", currentUserId)
                .orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Log.e("BuildRepository", "Erro ao ouvir modificações no Firestore.", error);
                        return;
                    }

                    if (value != null) {
                        List<Build> builds = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            Build build = doc.toObject(Build.class);
                            builds.add(build);
                        }
                        allBuildsLiveData.postValue(builds);
                    }
                });
    }

    public LiveData<List<Build>> getAllBuilds() {
        return allBuildsLiveData;
    }

    public void insert(Build build) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return;

        build.setAuthorId(currentUser.getUid());

        buildsRef.add(build)
                .addOnFailureListener(e -> Log.e("BuildRepository", "Erro na inserção", e));
    }

    public void update(Build build) {
        if (build == null || build.getId() == null) return;

        buildsRef.document(build.getId()).set(build)
                .addOnFailureListener(e -> Log.e("BuildRepository", "Erro na atualização", e));
    }

    public void delete(Build build) {
        if (build == null || build.getId() == null) return;

        buildsRef.document(build.getId()).delete()
                .addOnFailureListener(e -> Log.e("BuildRepository", "Erro na exclusão", e));
    }
}
