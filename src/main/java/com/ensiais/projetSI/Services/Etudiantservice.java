package com.ensiais.projetSI.Services;

import com.ensiais.projetSI.Entities.*;
import com.ensiais.projetSI.Repositories.EtudianteRpository;
import com.ensiais.projetSI.Repositories.ProfesseurRpository;
import com.ensiais.projetSI.Repositories.StageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class Etudiantservice {
    private EtudianteRpository etdrepo;
    private StageRepository stage;
    private ProfesseurRpository professeurRpository;
    //static List<Etudiante> li=new ArrayList<>();
    public Etudiante creer_etudiant(Etudiante e){
        //Professeur ent = e.getProfesseur();
        //li.add(e);
        //ent.setEtudiante(li);
        //professeurRpository.save(ent);
        //Promotion pr = e.getPromotion();
        //pr.setEtudiants((List<Etudiante>) e);
        //Etudiante et=new Etudiante();
        //et=e;
        return etdrepo.save(e);
    }
    public void supprimer_etudinat(long id){
        Etudiante e =trouver_etudiant(id);
        etdrepo.delete(e);
    }
    public Etudiante trouver_etudiant(long id){
        Optional<Etudiante> et = etdrepo.findById(id);
        return et.get();
    }
    public List<Etudiante> listes_etudiant(){
        return etdrepo.findAll();
    }
    public List<Stage> stages_etudiant(long id){
        Etudiante e =trouver_etudiant(id);
        return stage.findStageByEtudiante(e);
    }
    public Tuteur tuteur_etudiant(long id) {
        Etudiante e = trouver_etudiant(id);
        return e.getTuteur();
    }
    public List<Etudiante> etudiants_par_promos(long annee){
        return etdrepo.findByPromotion(annee);
    }
    public void supprime_tout(){
        etdrepo.findAll().forEach(e->etdrepo.delete(e));
    }
}
