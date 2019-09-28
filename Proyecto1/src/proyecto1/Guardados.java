/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.lang.reflect.Field;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author User
 */
public class Guardados {
    private Lista<Door> Sistema;
    private Lista<Pane> Compuertas;
    private Lista<Led> Outs;
    private Lista<On_Off> Ins;
    private Lista<Lineas> lines;
    private Pane Interfaz;
    private ScrollPane Editable;
    private Pane temporal;
    

    
    public Guardados(ScrollPane Editable,Lista<Door> Sistema,Lista<Pane> Compuertas, Lista<Led> Outs,Lista<On_Off> Ins,Lista<Lineas> lines){
        this.Sistema=Sistema;
        this.Compuertas=Compuertas;
        this.Outs=Outs;
        this.Ins=Ins;
        this.lines=lines;
        this.Editable=Editable;
    }

    public Lista<Door> getSistema() {
        return Sistema;
    }

    public void setSistema(Lista<Door> Sistema) {
        this.Sistema = Sistema;
    }

    public Lista<Pane> getCompuertas() {
        return Compuertas;
    }

    public void setCompuertas(Lista<Pane> Compuertas) {
        this.Compuertas = Compuertas;
    }

    public Lista<Led> getOuts() {
        return Outs;
    }

    public void setOuts(Lista<Led> Outs) {
        this.Outs = Outs;
    }

    public Lista<On_Off> getIns() {
        return Ins;
    }

    public void setIns(Lista<On_Off> Ins) {
        this.Ins = Ins;
    }

    public Lista<Lineas> getLines() {
        return lines;
    }

    public void setLines(Lista<Lineas> lines) {
        this.lines = lines;
    }
    
    public Pane get_interfaz(double X){
        Pane newpane =  new  Pane();
        newpane.setLayoutX(X);
        newpane.setLayoutY(600);
        newpane.setStyle("-fx-background-color: " + "Black");
        newpane.setPrefSize(55,106);
        this.Interfaz=newpane;
        //generateout();
        return newpane;
    }
    public Pane getInterfaz(){
        return this.Interfaz;
    }
    
   
        
}
