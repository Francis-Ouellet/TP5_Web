/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest.client.modele;

import com.bouchardm.twitface.ws.rest.client.ListeMembre;
import com.bouchardm.twitface.ws.rest.client.MembresClient;

/**
 *
 * @author Francis Ouellet & Marc-Antoine Bouchard M
 */
public class ModeleRechMembres {
    
    public ListeMembre obtenirMembres(String debut, String max, String nom ){
        
        MembresClient client = new MembresClient();
        
        return client.obtenirMembres(ListeMembre.class, debut, max, nom);
    }
    
}
