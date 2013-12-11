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
@XmlRootElement(name = "membres")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListeMembre {

    @XmlElement(name = "membre")
    private ArrayList<Membre> lesMembres = new ArrayList<Membre>();

    public ListeMembre() {
        this.lesMembres = new ArrayList<Membre>();
    }

    public ArrayList<Membre> getLesMembres() {
        return lesMembres;
    }

    public void setLesBourse(ArrayList<Membre> lesMembres) {
        this.lesMembres = lesMembres;
    }
}
