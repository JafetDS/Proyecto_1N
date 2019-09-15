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
    public Door Conector;
    public Lista<Door> Sistema;
    public int num=0;
    public Lista<Button> Leds;
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
   //    label.setText("Hello World!");
    }
    @FXML
    private void replace(MouseEvent event,String type,double X,double Y){
        Pane newpane =  new  Pane();
       // Button b= new Button();
        MouseControlUtil.makeDraggable(newpane);

        Pane oldpane=(Pane) event.getSource();

        scrollpane.getChildren().add(oldpane);
      //  scrollpane.toBack();
        oldpane.toFront();
              
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
    @FXML
    private void preConectar(MouseEvent event,Door puerta){
        this.Conector=puerta;             
    }
    @FXML
    private void Conectar(MouseEvent event,Door puerta){
        if (this.Conector!=null){
            puerta.setEntrada(this.Conector);
            this.Conector=null;       
        }
    }
    private void invokeall_led(){
        Nodo<Button>aux=this.Leds.getHead();
        while(aux.getNext()!=null){
            aux.getNext().getDato().fire();
        }
    }
    
    
    private void pressOnoff(MouseEvent event,On_Off interruptor){
        Button but=(Button)event.getSource();
        interruptor.setSalida();
        if(interruptor.getSalida()==true){
            but.setStyle("-fx-background-color: " + "blue");
        }else{
          but.setStyle("-fx-background-color: " + "black");  
        }this.Conector=interruptor;
        //invokeall_led();
    }
    private void pressOnoffled(MouseEvent event,Led led){
        System.out.println("Maria");
        Button but=(Button)event.getSource();
        
        if(led.getSalida()==true){
            System.out.println("Maria2");
            but.setStyle("-fx-background-color: " + "Green");
        }else{
            System.out.println("Maria3");
            but.setStyle("-fx-background-color: " + "Red");  
        }
    }
    
    @FXML
    private void createONOFF(Pane oldpane){
        On_Off interruptor= new On_Off("In");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnMouseClicked(e -> pressOnoff(e,interruptor));
     
    }
    @FXML
    private void createDoor (Pane oldpane,String type){
        Door puerta=new Door(type);
        this.Sistema.addFirst(puerta);
        float x=10;
        float y=10;        
        for(int i=0;i<2;i++){
            Button but=new Button ();
            oldpane.getChildren().add(but);
            but.setId(Integer.toString(i));
            but.setLayoutX(x);
            but.setLayoutY(y);
            but.setPrefSize(7, 7);
            but.setMinSize(7, 7);
            puerta.setEntrada(null);
            but.setOnMouseClicked(e -> Conectar(e,puerta));
            y+=15;
        }   
        Button Out=new Button();
        Out.setOnMouseClicked(e -> preConectar(e,puerta));
        oldpane.getChildren().add(Out);
        Out.setLayoutX(40);
        Out.setLayoutY(25);
        Out.setPrefSize(7, 7);
        Out.setMinSize(7, 7);
    }
    @FXML  
    private void createLed(Pane oldpane){
        Led led= new Led("led");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnMouseClicked(e -> pressOnoffled(e,led));  
       // this.Leds.addFirst(but);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Sistema=new Lista();
        MouseControlUtil.makeDraggable(paneOr);
        paneOr.setOnMouseClicked(e -> replace(e,"and",20,10));     
        
        MouseControlUtil.makeDraggable(paneAnd);
        paneAnd.setOnMouseClicked(e -> replace(e,"or",20,80));  
        
        MouseControlUtil.makeDraggable(paneNOr);
        paneNOr.setOnMouseClicked(e -> replace(e,"nor",20,150));  
        
        MouseControlUtil.makeDraggable(paneNAnd);
        paneNAnd.setOnMouseClicked(e -> replace(e,"nand",20,220)); 
        
        MouseControlUtil.makeDraggable(paneXor);
        paneXor.setOnMouseClicked(e -> replace(e,"xor",20,290)); 
        
        MouseControlUtil.makeDraggable(paneXnor);
        paneXnor.setOnMouseClicked(e -> replace(e,"xnor",20,360)); 
        
        
        
        
        MouseControlUtil.makeDraggable(In);
        In.setOnMouseClicked(e -> replace(e,"In",63,480));
        
        MouseControlUtil.makeDraggable(Led);
        Led.setOnMouseClicked(e -> replace(e,"led",14,471));
        
        
       


  
        //    Image image = new Image(getClass().getResourceAsStream("file:src/Media/or.jpg")) ;
        //   button.setGraphic(new ImageView(image));
      //TODO
    }    
}   
