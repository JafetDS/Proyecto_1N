/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;


import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import static proyecto1.Output_calc.getRandomNumber;

/**
 *
 * @author User
 */
public class Lineas {
    private Button pointA;
    private Button pointB;
    private Pane continer;
    private Line linea;
    private String color;
    
    public Lineas( Pane continer) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        this.continer=continer;
        this.linea=new Line();
        this.continer.getChildren().add(this.linea);
        this.color=Singleton.getInstance().getLista_Color().getNodo(getRandomNumber()).getDato();
        this.linea.setStyle("-fx-stroke:"+ this.color);
      //  getRandomNumber()
    }

    public void setPoitA(Button pointA) {
        this.pointA = pointA;
    }

    public void setPoitB(Button pointB) {
        this.pointB = pointB;
    }
    
    public void ubicate(){
        this.linea.setStartX( ((this.pointA.getParent().getLayoutX())+(this.pointA.getLayoutX())) );
        this.linea.setStartY( ((this.pointA.getParent().getLayoutY())+(this.pointA.getLayoutY())) );
        
        this.linea.setEndX( ((this.pointB.getParent().getLayoutX())+(this.pointB.getLayoutX())) );
        this.linea.setEndY( ((this.pointB.getParent().getLayoutY())+(this.pointB.getLayoutY())) );

    }
    
    
}
