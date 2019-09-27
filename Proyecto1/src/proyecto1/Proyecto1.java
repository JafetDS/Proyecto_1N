/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Proyecto1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lista<Door> Nodos=new Lista();
        On_Off but=new On_Off("in");
        but.setSalida(true);
        Nodos.addFirst(but);
        Nodos.addFirst(new On_Off("In"));
        Nodos.addFirst(new On_Off("In"));
        
        Nodos.addFirst(new On_Off("In"));
        Nodos.addFirst(new On_Off("In"));
        Nodos.addFirst(new On_Off("In"));
        
        Door Puerta=new Door("xor");
        
        Puerta.setEntradas(Nodos);
        
        System.out.print(Puerta.getSalida());
        
        
        launch(args);
    }
    
}
