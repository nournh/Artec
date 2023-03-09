/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.Entities;

/**
 *
 * @author octanet
 */
public class Vote {
    int id_vote , id_client , id_oeuvre, nb_vote;

    public Vote(int id_vote, int id_client, int id_oeuvre, int nb_vote) {
        this.id_vote = id_vote;
        this.id_client = id_client;
        this.id_oeuvre = id_oeuvre;
        this.nb_vote = nb_vote;
    }

    public Vote(int id_client, int nb_vote) {
        this.id_client = id_client;
        this.nb_vote = nb_vote;
    }

    public Vote() {
         }

    public int getId_vote() {
        return id_vote;
    }

    public void setId_vote(int id_vote) {
        this.id_vote = id_vote;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getNb_vote() {
        return nb_vote;
    }

    public void setNb_vote(int nb_vote) {
        this.nb_vote = nb_vote;
    }

    @Override
    public String toString() {
        return "Vote{" + "id_vote=" + id_vote + ", id_client=" + id_client + ", id_oeuvre=" + id_oeuvre + ", nb_vote=" + nb_vote + '}';
    }
    
    
    
    
    
    
    
}
