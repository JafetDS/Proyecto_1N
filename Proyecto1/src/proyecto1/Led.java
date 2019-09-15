/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author User
 */
public class Led extends Door{
    private Door puerta;
    private Boolean Salida;
    private String Tipo;

    public Led(String Tipo) {
        super(Tipo);
        this.Salida=false;
        this.Tipo=Tipo;
        
    }
    /**
     *
     * @param Tipo
     * @return 
     */

    
    @Override
    public Boolean getSalida(){
        
       Salida=this.puerta.getSalida();
       return Salida;
        
    }

    public void setPuerta(Door puerta) {
        this.puerta = puerta;
    }
    
}
