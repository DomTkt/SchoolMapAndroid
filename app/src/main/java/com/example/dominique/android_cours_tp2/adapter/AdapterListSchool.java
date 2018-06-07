package com.example.dominique.android_cours_tp2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dominique.android_cours_tp2.viewholder.ListSchoolViewHolder;
import com.example.dominique.android_cours_tp2.R;
import com.example.dominique.android_cours_tp2.model.School;

import java.io.IOException;
import java.util.List;

/**
 * Created by dominique on 02/02/2018.
 */

//L'adapter permet de contenir l'ensemble des données à afficher dans le RecyclerView.
public class AdapterListSchool extends RecyclerView.Adapter<ListSchoolViewHolder> {

    private List<School> list;

    //ajouter un constructeur prenant en entrée une liste
    public AdapterListSchool(List<School> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ListSchoolViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards_list_school,viewGroup,false);
        return new ListSchoolViewHolder(view, viewGroup.getContext(),list);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(ListSchoolViewHolder listSchoolViewHolder, int position) {
        School myObject = list.get(position);
        try {
            listSchoolViewHolder.bind(myObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}