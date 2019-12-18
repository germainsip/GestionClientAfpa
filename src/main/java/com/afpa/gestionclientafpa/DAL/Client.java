/*
 *  ___ _                    _                   
 * |_ _| |_  __ __ _____ _ _| |__ ___  ___ _ _   
 *  | ||  _| \ V  V / _ \ '_| / /(_-< / _ \ ' \  
 * |___|\__|  \_/\_/\___/_| |_\_\/__/ \___/_||_| 
 *  _ __ _  _   _ __  __ _ __| |_ (_)_ _  ___    
 * | '  \ || | | '  \/ _` / _| ' \| | ' \/ -_)   
 * |_|_|_\_, | |_|_|_\__,_\__|_||_|_|_||_\___|   
 *       |__/                                    
 */
package com.afpa.gestionclientafpa.DAL;

import javafx.beans.property.SimpleStringProperty;

/**
 * créé le 19 nov. 2018 , 09:25:07
 *
 * @author germain
 */
public class Client {

    private int id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty ville;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom.get();
    }
    
    

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public String getPrenom() {
        return  prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);
    }

    public String getVille() {
        return ville.get();
    }

    public void setVille(String ville) {
        this.ville = new SimpleStringProperty(ville);
    }

    public Client(int id, String nom, String prenom, String ville) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.ville = new SimpleStringProperty(ville);
    }

}
