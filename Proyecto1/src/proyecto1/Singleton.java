/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.lang.reflect.Field;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class Singleton { 
    // static variable single_instance of type Singleton 
    private static Singleton single_instance = null; 

    private Lista<String> Lista_Color; 

    public Lista<String> getLista_Color() {
       // this.Lista_Color=;
        return this.Lista_Color;
    }

    private Singleton() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException { 
       this.Lista_Color = allColors();
            
 
    }        
    
    private Lista<String> allColors() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
            Lista<String> colors = new Lista();
            Class clazz = Class.forName("javafx.scene.paint.Color");
            if (clazz != null) {
                Field[] field = clazz.getFields();
                for (Field f : field) {                
                    Object obj = f.get(null);
                    if(obj instanceof Color){
                        colors.addFirst(f.getName());
                    }
                }
            }
       return colors;
    }
     
  
    // static method to create instance of Singleton class 
    public static Singleton getInstance() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException { 
        if (single_instance == null) {
            single_instance = new Singleton();
        } 
  
        return single_instance; 
    } 
} 
                