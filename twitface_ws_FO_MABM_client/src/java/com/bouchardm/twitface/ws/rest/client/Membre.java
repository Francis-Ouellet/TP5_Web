/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marc-Antoine
 */
@XmlRootElement(name="membre")
@XmlAccessorType(XmlAccessType.FIELD)
public class Membre {
    
    @XmlElement
    private String nom;
    @XmlElement
    private String villeOrigine;
    @XmlElement
    private String villeActuelle;
    @XmlElement
    private String courriel;
    @XmlAttribute
    private String nomUtilisateur;

    public Membre(String nom, String villeOrigine, String villeActuelle, String courriel, String nomUtilisateur){
        this.nom = nom;
        this.villeOrigine = villeOrigine;
        this.villeActuelle = villeActuelle;
        this.courriel = courriel;
        this.nomUtilisateur = nomUtilisateur;
    }
    
    public Membre(){
        this.nom = null;
        this.villeOrigine = null;
        this.villeActuelle = null;
        this.courriel = null;
        this.nomUtilisateur = null;
    }
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the villeOrigine
     */
    public String getVilleOrigine() {
        return villeOrigine;
    }

    /**
     * @param villeOrigine the villeOrigine to set
     */
    public void setVilleOrigine(String villeOrigine) {
        this.villeOrigine = villeOrigine;
    }

    /**
     * @return the villeActuelle
     */
    public String getVilleActuelle() {
        return villeActuelle;
    }

    /**
     * @param villeActuelle the villeActuelle to set
     */
    public void setVilleActuelle(String villeActuelle) {
        this.villeActuelle = villeActuelle;
    }

    /**
     * @return the courriel
     */
    public String getCourriel() {
        return courriel;
    }

    /**
     * @param courriel the courriel to set
     */
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    /**
     * @return the nomUtilisateur
     */
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * @param nomUtilisateur the nomUtilisateur to set
     */
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
            
    
}