/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bouchardm.twitface.ws.rest.client;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marc-Antoine
 */
@XmlRootElement(name = "Membres")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListeMembre {

    @XmlElement(name = "Membre")
    private ArrayList<Membre> lesMembres = new ArrayList<Membre>();

    public ListeMembre() {
        this.lesMembres = new ArrayList<Membre>();
    }
    
    public int nbBourses() {
        return this.getLesBourse().size();
    }

    public ArrayList<Membre> getLesBourse() {
        return lesMembres;
    }

    public void setLesBourse(ArrayList<Membre> lesMembres) {
        this.lesMembres = lesMembres;
    }
}
