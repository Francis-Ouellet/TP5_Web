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
    public ArrayList<Membre> obtenirMembres(@DefaultValue("0") @QueryParam("debut") int debut,
        @DefaultValue("10") @QueryParam("max") int max, @DefaultValue("") @QueryParam("nom") String nom) 
    {
        try {
            ArrayList<Membre> membres = UtilitaireTwitface.obtenirListeMembres((int)debut, (int)max, nom);
            return membres;
        }
        catch(Exception e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    } 
}
