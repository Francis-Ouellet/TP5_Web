/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest.client.modele;

import com.bouchardm.twitface.ws.rest.client.ListeMembre;
import com.bouchardm.twitface.ws.rest.client.MembresClient;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Francis Ouellet & Marc-Antoine Bouchard M
 */
public class ModeleRechMembres {
    
    public void obtenirMembres(HttpServletRequest request){
        
        int i_debut, i_max;
        MembresClient client = new MembresClient();
        
        // Test s'il s'agit bien d'un nombre
        try{
            i_debut = Integer.parseInt(request.getParameter("debut").trim());
        }
        catch(Exception e){
            i_debut = 0;
        }
        try{
            i_max = Integer.parseInt(request.getParameter("max").trim());
        }
        catch(Exception e){
            i_max = 10;
        }
        
        // Membres à afficher
        ListeMembre lstMembres = client.obtenirMembres(ListeMembre.class, 
                request.getParameter("max").trim(), request.getParameter("debut").trim(), request.getParameter("nom"));
        
        request.setAttribute("lesMembres", lstMembres.getLesMembres());
        
        // Détermine s'il y a encore des membres après les membres à afficher
        String suivant = Integer.toString(i_debut + i_max);
        lstMembres = client.obtenirMembres(ListeMembre.class, "1", suivant, request.getParameter("nom"));
        
        request.setAttribute("suivant", !lstMembres.getLesMembres().isEmpty());
        
        // Détermine s'il y avait des membres avant les membres à afficher
        String precedent = Integer.toString(i_debut - 1);
        lstMembres = client.obtenirMembres(ListeMembre.class, "1", precedent, request.getParameter("nom"));
        
        request.setAttribute("precedent", !lstMembres.getLesMembres().isEmpty());
    }
    
}
