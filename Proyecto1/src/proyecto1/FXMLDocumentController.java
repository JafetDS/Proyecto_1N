/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.util.event.MouseControlUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 *
 * @author User
 */


public class FXMLDocumentController implements Initializable {
    private Door Conector;
    private Lista<Door> Sistema;
    private int num=0;
    private Lista<Button> Leds;
    @FXML
    public AnchorPane anchorpane;
    public Button button1;
    public Pane pane;
    public Pane paneOr;
    public Pane paneAnd;
    public Pane paneNOr;
    public Pane paneNAnd;
    public Pane paneXor;
    public Pane paneXnor;
    public Pane Led;
    public Pane In;
    public Pane scrollpane;


    
    @FXML
    private void Listo(MouseEvent event) {
        System.out.println("fuck You!");
        Pane paneichon=(Pane)event.getSource();
        Button button=new Button("Prueba");
        button.setLayoutX(paneichon.getLayoutX());
        button.setLayoutY(paneichon.getLayoutY());
        scrollpane.getChildren().add(button);
        
    }
    
    private void Prueba(ActionEvent e) {
        System.out.println("Boi"); 
    }
    
    @FXML
    private void replace(MouseEvent event,String type,double X,double Y){
        Pane newpane =  new  Pane();
       // Button b= new Button();
        MouseControlUtil.makeDraggable(newpane);

        Pane oldpane=(Pane) event.getSource();

      //  scrollpane.getChildren().add(oldpane);
      //  scrollpane.toBack();
        oldpane.toFront();
        scrollpane.getChildren().add(oldpane);
              
        newpane.setLayoutX(X);
        newpane.setLayoutY(Y);
        newpane.setStyle("-fx-background-color: " + "Green");
        newpane.setBackground(oldpane.getBackground());
        newpane.setOnMouseClicked(e -> replace(e,type,X,Y));  
        newpane.setId(Integer.toString(num));
        newpane.setPrefSize(oldpane.getPrefWidth(), oldpane.getPrefHeight());
        pane.getChildren().add(newpane);
        oldpane.setOnMouseClicked(e -> Listo(e));
        num++;
        if (type.equals("In")){
            createONOFF(oldpane);
        }
        else if (type.equals("led")){
            createLed(oldpane);  
        }else{
            createDoor(oldpane,type);
        }
    }
    private void preConectar(MouseEvent event,Door puerta){
        System.out.println(puerta.getSalida());
        this.Conector=puerta;             
    }
    
    private void preConectar(Door puerta){
        this.Conector=puerta;     
    }
    private void Conectar(MouseEvent event,Door puerta){
        if (this.Conector!=null){
            puerta.setEntrada(this.Conector);
            this.Conector=null;       
        }invokeall_led();
    }
    
 
    private void Conectar(Door puerta){
        if (this.Conector!=null){
            puerta.setEntrada(this.Conector);
            this.Conector=null;       
        }//invokeall_led();
    }
    private void invokeall_led(){
        Nodo<Button>aux=this.Leds.getHead();
        while(aux!=null){
            aux.getDato().fire();
            aux=aux.getNext();
        } 
    }
    
    
    private void pressOnoff(MouseEvent event,On_Off interruptor){
        Button but=(Button)event.getSource();
        interruptor.setSalida();
        if(interruptor.getSalida()==true){
            but.setStyle("-fx-background-color: " + "blue");
        }else{
          but.setStyle("-fx-background-color: " + "black");  
        }
        preConectar(interruptor);
 
        invokeall_led();
        
    }
    private void pressOnoffled(ActionEvent event,Led led){
        Button but=(Button)event.getSource();
        if (led.getEntrada()==null){
            Conectar(led);
        }
        if(led.getSalida()==true){
            but.setStyle("-fx-background-color: " + "Green");
        }else{
            but.setStyle("-fx-background-color: " + "Red");
            }      
    }
    

    @FXML
    private void createDoor (Pane oldpane,String type){
        Door puerta=new Door(type);
        this.Sistema.addFirst(puerta);
        float x=10;
        float y=10;     
        
        Button Out=new Button();

        oldpane.getChildren().add(Out);
        Out.setLayoutX(40);
        Out.setLayoutY(25);
        Out.setPrefSize(7, 7);
        Out.setMinSize(7, 7);
        for(int i=0;i<2;i++){
            Button but=new Button ();
            but.setOnMouseClicked(e -> Conectar(e,puerta));
            oldpane.getChildren().add(but);
            but.setId(Integer.toString(i));
            but.setLayoutX(x);
            but.setLayoutY(y);
            but.setPrefSize(7, 7);
            but.setMinSize(7, 7);
            y+=15;
            Out.setOnMouseClicked(e -> preConectar(e,puerta));
        }   

    }
    @FXML  
    private void createLed(Pane oldpane){
        Led led= new Led("led");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnAction(e -> pressOnoffled(e,led));
        this.Leds.addFirst(but);
    }
    @FXML
    private void createONOFF(Pane oldpane){
        On_Off interruptor= new On_Off("In");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnMouseClicked(e -> pressOnoff(e,interruptor));
       // but.setOnAction(e -> invokeall_led(e));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Sistema=new Lista();
        this.Leds=new Lista();
       // this.Conector=null;
        MouseControlUtil.makeDraggable(paneOr);
        paneOr.setOnMouseClicked(e -> replace(e,"or",720,10));     
        
        MouseControlUtil.makeDraggable(paneAnd);
        paneAnd.setOnMouseClicked(e -> replace(e,"and",720,80));  
        
        MouseControlUtil.makeDraggable(paneNOr);
        paneNOr.setOnMouseClicked(e -> replace(e,"nor",720,150));  
        
        MouseControlUtil.makeDraggable(paneNAnd);
        paneNAnd.setOnMouseClicked(e -> replace(e,"nand",720,220)); 
        
        MouseControlUtil.makeDraggable(paneXor);
        paneXor.setOnMouseClicked(e -> replace(e,"xor",720,290)); 
        
        MouseControlUtil.makeDraggable(paneXnor);
        paneXnor.setOnMouseClicked(e -> replace(e,"xnor",720,360)); 
        
        
        
        
        MouseControlUtil.makeDraggable(In);
        In.setOnMouseClicked(e -> replace(e,"In",720,490));
        
        MouseControlUtil.makeDraggable(Led);
        Led.setOnMouseClicked(e -> replace(e,"led",765,471));
        
        
       


  
        //    Image image = new Image(getClass().getResourceAsStream("file:src/Media/or.jpg")) ;
        //   button.setGraphic(new ImageView(image));
      //TODO
    }    
}   
