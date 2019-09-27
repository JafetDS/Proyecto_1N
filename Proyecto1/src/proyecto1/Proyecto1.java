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
import static proyecto1.Output_calc.getRandomNumber;

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
    public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException  {
        Lista<Door> Nodos=new Lista();
    
//        Lista<String> Lista_Color=Singleton.getInstance().getLista_Color();
       // System.out.println(Lista_Color.len());
        System.out.println(Singleton.getInstance().getLista_Color().getNodo(getRandomNumber()).getDato());
        
        
        launch(args);
    }
    
}
