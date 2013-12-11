/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:RessourceTwitface
 * [twitface]<br>
 *  USAGE:
 * <pre>
 *        MembresClients client = new MembresClients();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Marc-Antoine
 */
public class MembresClients {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/build/webresources";

    public MembresClients() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("twitface");
    }

    public <T> T obtenirInfoMembre(Class<T> responseType, String nomUtil) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("membres/{0}", new Object[]{nomUtil}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T obtenirMembres(Class<T> responseType, String max, String debut, String nom) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (max != null) {
            resource = resource.queryParam("max", max);
        }
        if (debut != null) {
            resource = resource.queryParam("debut", debut);
        }
        if (nom != null) {
            resource = resource.queryParam("nom", nom);
        }
        resource = resource.path("membres");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
