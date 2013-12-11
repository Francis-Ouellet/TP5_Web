/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest;

import com.bouchardm.util.RequeteBD;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Classe regroupant des méthodes statiques d'accès aux données
 * @author Francis Ouellet & Marc-Antoine Bouchard
 */
public class UtilitaireTwitface {
    
    public static ArrayList<Membre> obtenirListeMembres(int debut, int max, String nom){
        
        int upperBound = debut + max;
        
        String nomDS = "jdbc/twitface";
        RequeteBD reqBD = new RequeteBD(nomDS);
        
        try{
            
            reqBD.obtenirConnexion();
            
            String sqlReq = "SELECT MemNom, MemVilleOrigine, MemVilleActuelle, MemCourriel, MemNomUtil " +
                    " FROM membres " +
                    " WHERE MemNom LIKE '%" + nom.trim() + "%'" + 
                    " ORDER BY MemNom" +
                    " LIMIT " + debut + "," + upperBound;
            
            reqBD.obtenirConnexion();      
            ResultSet resReq = reqBD.executerRequeteSelect(sqlReq);
            
            ArrayList<Membre> lstMembresTrouves = new ArrayList<Membre>();
            
            Membre membre;
            
            while(resReq.next()){
               membre = new Membre(
                       resReq.getString("MemNom"),
                       resReq.getString("MemVilleOrigine"),
                       resReq.getString("MemVilleActuelle"),
                       resReq.getString("MemCourriel"),
                       resReq.getString("MemNomUtil"));
               lstMembresTrouves.add(membre);
            }
            
            
            reqBD.fermerConnexion();
            return lstMembresTrouves;
        }
        catch (Exception e){
            reqBD.fermerConnexion();
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
    }
            
}
