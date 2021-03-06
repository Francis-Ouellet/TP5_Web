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
 *
 * @author Francis Ouellet & Marc-Antoine Bouchard
 */
public class UtilitaireTwitface {
    
    public static ArrayList<Membre> obtenirListeMembres(String debut, String max, String nom){
        
        int i_debut, i_max;
        
        // Test s'il s'agit bien d'un nombre
        try{
            i_debut = Integer.parseInt(debut.trim());
        }
        catch(Exception e){
            i_debut = 0;
        }
        try{
            i_max = Integer.parseInt(max.trim());
        }
        catch(Exception e){
            i_max = 10;
        }
        
        String nomDS = "jdbc/twitface";
        RequeteBD reqBD = new RequeteBD(nomDS);
        
        try{
            
            reqBD.obtenirConnexion();
            
            String sqlReq = "SELECT MemNom, MemVilleOrigine, MemVilleActuelle, MemCourriel, MemNomUtil " +
                    " FROM membres " +
                    " WHERE MemNom LIKE '%" + nom.trim() + "%'" + 
                    " ORDER BY MemNom" +
                    " LIMIT " + i_debut + "," + i_max;
            
            reqBD.obtenirConnexion();      

            ResultSet resReq = reqBD.executerRequeteSelect(sqlReq);

            ArrayList<Membre> lstMembresTrouves = new ArrayList<Membre>();

            Membre membre;

            while (resReq.next()) {
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
        } catch (Exception e) {
            reqBD.fermerConnexion();
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
	}

    public static Membre obtenirInfoMembre(String nomUtil) {
        String nomDS = "jdbc/twitface";
        RequeteBD reqBD = new RequeteBD(nomDS);

        try {
            String sqlReq = "SELECT MemNom, MemVilleOrigine, MemVilleActuelle, MemCourriel, MemNomUtil "
                    + " FROM membres "
                    + " WHERE MemNomUtil = '" + nomUtil + "'";

            reqBD.obtenirConnexion();
            ResultSet resReq = reqBD.executerRequeteSelect(sqlReq);

            Membre membre = null;

            resReq.next();

            membre = new Membre(
                    resReq.getString("MemNom"),
                    resReq.getString("MemVilleOrigine"),
                    resReq.getString("MemVilleActuelle"),
                    resReq.getString("MemCourriel"),
                    resReq.getString("MemNomUtil"));

            reqBD.fermerConnexion();
            return membre;

        } catch (Exception e) {
            reqBD.fermerConnexion();
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
