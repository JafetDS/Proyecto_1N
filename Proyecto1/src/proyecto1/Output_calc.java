package proyecto1;

import java.lang.reflect.Field;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
public class Output_calc {
    
    /**
     *
     * @param door
     * @return
     */
    public static Boolean Output_calc_Door(Door door){
        Boolean excep=true;
        Boolean excepX=false;
        Lista <Door> entradas=new Lista();
        entradas=door.getEntradas();
        Nodo<Door> temp=entradas.getHead();
        while(temp!=null){
            switch (door.getTipo()) {
                case "or":
                    excep=false;
                    if(temp.getDato().getSalida().equals(true)){return true;}
                    break; 
                case "nor":
                    if(temp.getDato().getSalida().equals(true)){return false;}
                    break;
                case "and":
                    if(temp.getDato().getSalida().equals(false)){return false;}
                    break;
                case "nand":
                    excep=false;
                    if(temp.getDato().getSalida().equals(false)){return true;}                    
                    break;
                case "xor":
                    excepX=!(temp.getDato().getSalida().equals(excepX));
                    excep=excepX;
                    break;
                case "xnor":
                    excep=temp.getDato().getSalida().equals(excep);
                     break;
         
            }  
        temp=temp.getNext();
        }
    return excep;
      
    }
       

   
    public static int getRandomNumber() {
		return (int)(Math.random() * ((146 - 1) + 1)) + 1;
	}

}
