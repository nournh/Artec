/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Entities;

/**
 *
 * @author eyach
 */
public class Coupant {
     private int Id_Coupant,Valeur;
      private String Code;

    public Coupant() {
    }

    public Coupant(String Code, int Valeur) {
        
        this.Code = Code;
        this.Valeur = Valeur;
    }
     public Coupant(int Id_Coupant,String Code, int Valeur) {
        this.Id_Coupant = Id_Coupant;
        this.Code = Code;
        this.Valeur = Valeur;
    }


    public int getId_Coupant() {
        return Id_Coupant;
    }

    public void setId_Coupant(int Id_Coupant) {
        this.Id_Coupant= Id_Coupant;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
     public int getValeur() {
        return Valeur;
    }

    public void setValeur(int Valeur) {
        this.Valeur= Valeur;
    }
   
    
    
    @Override
    public String toString() {
        return "Coupant{" + "Id_Coupant=" + Id_Coupant + ", Code=" + Code + ", Valeur=" + Valeur  +'}';
    }

  

    
}
