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
        this.puerta=null;
        
    }
    /**
     *
     * @param Tipo
     * @return 
     */  
    @Override
    public Boolean getSalida(){
     if (this.puerta!=null){
         System.out.println("La salida se a calculado");
        this.Salida=this.puerta.getSalida();
        return this.Salida;
     }return false; 
    }

    @Override
    public void setEntrada(Door puerta) {
        if (!"In".equals(puerta.getTipo())){
            this.puerta = puerta;      
        }
    }
    public Door getEntrada(){
        return this.puerta;
    }
    
}
