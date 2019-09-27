/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;
import static proyecto1.Output_calc.Output_calc_Door;
/**
 *
 * @author User
 */
public class Door {
    private Lista<Door> Entradas;
    private Boolean Salida;
    private String Tipo;
    private String Nombre;
  
    public Door(String Tipo){
        this.Entradas = new Lista();
        this.Tipo=Tipo;
        this.Salida=false;
    } 

    public Boolean getSalida() {
        this.Salida=Output_calc_Door(this);
        return Salida; 
    }

    public void setEntrada(Door Entrada){
        this.Entradas.addFirst(Entrada);
    }


    public String getTipo() {
        return Tipo;
    }

    public Lista<Door> getEntradas() {
        return Entradas;
    }
    
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }   
    
    public void printout (){
        System.out.print(this.Salida);
    }
    
    public void deleteEntrada(String i){
        this.Entradas.remove(Integer.parseInt(i));
    }

    public void setEntradas(Lista<Door> Entradas) {
        this.Entradas = Entradas;
    }
    
    
}

