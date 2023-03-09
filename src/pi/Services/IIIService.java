/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.Services;

import java.util.List;
import pi.Entities.Vote;

/**
 *
 * @author octanet
 * @param <Vote>
 */
public interface IIIService <Vote>{
   
        public void add(Vote v );
         public List<Vote> afficher();
        public Boolean modifier(Vote v);
           public Boolean supprimer(Vote v);
}
