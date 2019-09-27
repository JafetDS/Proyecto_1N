package proyecto1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
public class On_Off extends Door {
    private Boolean Salida;
    public On_Off(String Tipo) {
        super(Tipo);
        this.Salida=false;
    }

    @Override
    public Boolean getSalida() {
        return Salida;
    }

    public void setSalida(Boolean Salida) {
        this.Salida = Salida;
    }   
    public void setSalida() {
        if(this.Salida==true){
            this.Salida=false;
        }else{
            this.Salida = true;
        }
    }   
}
