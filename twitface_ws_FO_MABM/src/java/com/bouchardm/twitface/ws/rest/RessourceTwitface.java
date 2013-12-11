package com.bouchardm.twitface.ws.rest;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.bouchardm.twitface.ws.rest.UtilitaireTwitface;
import java.sql.ResultSet;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author owner
 */
@Path("twitface")
public class RessourceTwitface {

    
    /**
     * Retrieves representation of an instance of com.bouchardm.twitface.ws.rest.RessourceTwitface
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/membres")
    @Produces("application/xml")
    public ArrayList<Membre> obtenirMembres(@DefaultValue("0") @QueryParam("debut") String debut,
        @DefaultValue("10") @QueryParam("max") String max, @DefaultValue("") @QueryParam("nom") String nom) 
    {
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
        
        try {
            ArrayList<Membre> membres = UtilitaireTwitface.obtenirListeMembres(i_debut, i_max, nom);
            return membres;
        }
        catch(Exception e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    } 
}
