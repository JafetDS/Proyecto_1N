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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


/**
 *
 * @author User
 */


public class FXMLDocumentController implements Initializable {
    private Door Conector;
    private Lista<Door> Sistema;
    private int num=0;
    public Lista<Lista> Circuito;
    public Lista<Pane> Compuertas;
    private Lista<Button> Leds;
    public Lista<On_Off> Ins;
    public Lista<Lineas> lines;
    public Lineas actualline;
    
    @FXML
    public AnchorPane anchorpane;
    public Button guardar;
    public Pane pane;
    public Pane paneOr;
    public Pane paneAnd;
    public Pane paneNOr;
    public Pane paneNAnd;
    public Pane paneXor;
    public Pane paneXnor;
    public Pane Led;
    public Pane In;
    public AnchorPane scrollpane;
    public TextField cantidad;
   


    
    
    
    private void MakeLine(ActionEvent event){ 
            Button but=(Button) event.getSource(); 
            Lineas linea; 
        try {
            linea = new Lineas(this.scrollpane);
            linea.setPoitA(but); 
            this.actualline=linea; 
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

           // ActualiceLines();
    }
   
    private void MakeLine2(MouseEvent event){
        if (this.actualline!=null){
            Button but=(Button) event.getSource();
            this.actualline.setPoitB(but);
            this.actualline.ubicate();
            this.lines.addFirst(this.actualline);
            this.actualline=null;
            //ActualiceLines();
        }
    }
    private void MakeLine2(ActionEvent event){
        if (this.actualline!=null){
            Button but=(Button) event.getSource();
            this.actualline.setPoitB(but);
            this.actualline.ubicate();
            this.lines.addFirst(this.actualline);
            this.actualline=null;
           // ActualiceLines();
        }
    }
    
    
    private void  ActualiceLines(){
        Nodo<Lineas>aux=this.lines.getHead();
        while(aux!=null){
            aux.getDato().ubicate();
            aux=aux.getNext();
        } 
    }
            

    @FXML
    public void Guardar(ActionEvent event){
        this.Circuito.addFirst(this.Compuertas);
        this.Circuito.addFirst(this.Leds);
        this.Circuito.addFirst(this.Ins);
        this.Circuito.addFirst(this.lines);
        System.out.print("Guardado");
    
    }
    
    
    
    
    
       
    
    
    @FXML
    private void Listo(MouseEvent event) {
        System.out.println("fuck You!");
        ActualiceLines();

        
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
        }
        invokeall_led();
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
        
        Button Out=new Button();

        oldpane.getChildren().add(Out);
        Out.setLayoutX(100);
        Out.setLayoutY(25);
        Out.setPrefSize(7, 7);
        Out.setMinSize(7, 7);
        Out.setId("O<"+Integer.toString(num)+">"); 
        Out.setOnMouseClicked(e -> preConectar(e,puerta)); 
        
        Label tiket=new Label();
        tiket.setLayoutX(70);
        tiket.setLayoutY(8);
        tiket.setPrefSize(40, 20);
        tiket.setText("O<"+Integer.toString(num)+">");
        oldpane.getChildren().add(tiket);
        this.num++;
        
        
        if ("".equals(this.cantidad.getText())){
            this.cantidad.setText("2");   
        }
        if(Integer.parseInt(this.cantidad.getText())<2){
           this.cantidad.setText("2");    
        }
        if(type=="not"){
            this.cantidad.setText("1");
        }
        float cantidad_de_entradas=(Integer.parseInt(this.cantidad.getText()));
        float x=2;//-(Integer.parseInt(this.cantidad.getText()));
        float y=35-(cantidad_de_entradas*10); 
        
        
        for(int i=0;i<cantidad_de_entradas;i++){
            Button but=new Button ();
            but.setOnMouseClicked(e -> Conectar(e,puerta));
            but.setOnAction(e -> MakeLine2(e));
            oldpane.getChildren().add(but);
            but.setId("i<"+Integer.toString(i)+">");
            but.setLayoutX(x);
            but.setLayoutY(y);
            but.setPrefSize(7, 7);
            but.setMinSize(7, 7);
 
            
            Label tiketIN=new Label();
            tiketIN.setLayoutX(x+10);
            tiketIN.setLayoutY(y-7);
            tiketIN.setPrefSize(40, 20);
            tiketIN.setText("i<"+Integer.toString(i)+">");
            oldpane.getChildren().add(tiketIN);
            
            y+=15;
        }Out.setOnAction(e -> MakeLine(e));

    }
    @FXML  
    private void createLed(Pane oldpane){
        Led led= new Led("led");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnAction(e -> pressOnoffled(e,led));
        this.Leds.addFirst(but);
        Label tiketIN=new Label();
        tiketIN.setLayoutX(10);
        tiketIN.setLayoutY(10);
        tiketIN.setPrefSize(40, 20);
        tiketIN.setText("Out<"+Integer.toString(num)+">");
        oldpane.getChildren().add(tiketIN);
      //  but.setOnAction(e -> MakeLine(e));
        
        but.setOnMouseClicked(e -> MakeLine2(e));
    }
    @FXML
    private void createONOFF(Pane oldpane){
        On_Off interruptor= new On_Off("In");
        Button but = new Button();
        oldpane.getChildren().add(but);
        but.setOnMouseClicked(e -> pressOnoff(e,interruptor));
       // but.setOnAction(e -> invokeall_led(e));
        Label tiketIN=new Label();
        tiketIN.setLayoutX(10);
        tiketIN.setLayoutY(10);
        tiketIN.setPrefSize(40, 20);
        tiketIN.setText("In<"+Integer.toString(num)+">");
        oldpane.getChildren().add(tiketIN);
        but.setOnAction(e -> MakeLine(e));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Sistema=new Lista();
        this.Leds=new Lista();
        this.lines=new Lista();
        
        this.Compuertas=new Lista();
   
        this.Ins= new Lista();

        
       // this.Conector=null;
        MouseControlUtil.makeDraggable(paneOr);
        paneOr.setOnMouseClicked(e -> replace(e,"or",1000,10));     
        
        MouseControlUtil.makeDraggable(paneAnd);
        paneAnd.setOnMouseClicked(e -> replace(e,"and",1000,80));  
        
        MouseControlUtil.makeDraggable(paneNOr);
        paneNOr.setOnMouseClicked(e -> replace(e,"nor",1000,150));  
        
        MouseControlUtil.makeDraggable(paneNAnd);
        paneNAnd.setOnMouseClicked(e -> replace(e,"nand",1000,220)); 
        
        MouseControlUtil.makeDraggable(paneXor);
        paneXor.setOnMouseClicked(e -> replace(e,"xor",1000,290)); 
        
        MouseControlUtil.makeDraggable(paneXnor);
        paneXnor.setOnMouseClicked(e -> replace(e,"xnor",1000,360)); 
        
        
        
        
        MouseControlUtil.makeDraggable(In);
        In.setOnMouseClicked(e -> replace(e,"In",1000,490));
        
        MouseControlUtil.makeDraggable(Led);
        Led.setOnMouseClicked(e -> replace(e,"led",1050,471));
        
        this.actualline=null;
        
        
       


  
        //    Image image = new Image(getClass().getResourceAsStream("file:src/Media/or.jpg")) ;
        //   button.setGraphic(new ImageView(image));
      //TODO
    }    
}   
