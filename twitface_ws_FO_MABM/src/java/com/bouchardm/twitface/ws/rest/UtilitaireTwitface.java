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
        
        String nomDS = "jdbc/twitface";
        RequeteBD reqBD = new RequeteBD(nomDS);
        try{
        String sqlReq = "SELECT MemNom, MemVilleOrigine, MemVilleActuelle, MemCourriel, MemNomUtil " +
                    " FROM membres " +
                    " WHERE MemNom LIKE '%" + nom.trim() + "%'" + 
                    " ORDER BY MemNom" +
                    " LIMIT " + debut + "," + max;
            
            ResultSet resReq = reqBD.executerRequeteSelect(sqlReq);
            
            ArrayList lstMembresTrouves = new ArrayList();
            
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
            
            return lstMembresTrouves;
        }
        catch (Exception e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
    }
    
}
