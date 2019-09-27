/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author User
 * @param <T>
 */
public class Lista<T> {
    private Nodo<T> head;

    private int len;
   
    /**
     * Constructor de lista 
     *  
     */
    public Lista(){
   
        head=null;

        len=0;        
    }

    public Nodo<T> getHead() {
        return head;
    }
    
    /**
     * Método para obtener el largo de la lista
     * @return 
     */
    public int len(){
        return this.len;
    }

    /**
     * Metodo para hacer un print de los elementos dwe la lista 
     */
    public void print(){
        Nodo<T> aux= head;
        while(aux !=null){
            System.out.println(aux.getDato());
            aux=aux.getNext();
            
        }
    
    }
    public Nodo<T> getNodo(int pos){
        Nodo<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==pos){
                return aux;
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
    }
   
    /**
     * Metodo para conseguir el valor de una posición de la lista
     * @param i
     * @return 
     */
    public T getInfo(int i){
        Nodo<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==i){
                return aux.getDato();
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
    }
    /**
     * Metodo para agregar un espacio vacio al inicio de la lista
     */
 
    public void addFirst(){
        if (this.head==null){
            this.head= new Nodo();

        }
        else{
            Nodo<T> temp= this.head;
            this.head= new Nodo();
            this.head.setNext(temp);
        }
        len++;
    }
    /**
     * Metodo para agregar un dato al inicio de la lista
     * @param dato 
     */
    public void addFirst(T dato){
        if (this.head==null){
            this.head= new Nodo(dato);           
        }
        else{
            Nodo<T> temp= this.head;
            this.head= new Nodo(dato);
            this.head.setNext(temp);
        }
        len++;
    }
    
    public void addLast(T dato ){
        Nodo<T> aux=this.head;
        while(aux.getNext()!=null){
            aux=aux.getNext();
        }
        aux.setNext(new Nodo(dato));
    }

    /**
     * Metodo para agregar un dato a una posicion dada de la lista
     * @param i 
     * @param dato 
     */
    public void add(int i,T dato){
        
        if (i==0){
           addFirst(dato);
           return;
        }
        if (i==len){
            return ;
        }
        int pos = 1;
        Nodo<T> aux=this.head;
        while(pos<i){
            aux=aux.getNext();
            pos++;
        }
        Nodo<T> temp=aux.getNext();
        aux.setNext(new Nodo(dato));
        aux.getNext().setNext(temp);
        len++;
    
    }
    /**
     * Metodo para buscar si un dato esta en la lista
     * @param dato
     * @return retorna un boolean segun si esta o no el dato
     */
    public boolean in(T dato){
        Nodo<T> aux=head;
        while (aux != null){
            if (aux.getDato().equals(dato)){
                System.out.println(aux.getDato());
                return true;
            }
            aux=aux.getNext();  
        }
        return false;
    }
    /**
     * Metodo para eliminar el primer elemento 
     */
    private void removeFirst(){
        this.head=this.head.getNext();
    }
    /**
     * Metodo para eliminar un elemento en una posicion dada
     * @param pos 
     */
    public void remove(int pos){
        if (pos==0){
            removeFirst();
        }
        if (pos>len){
            System.out.println("indice inexistente");
            return;
        }
        int cont=1;
        Nodo<T> aux=this.head;
        while (cont<pos){
            cont++;
            aux= aux.getNext();
        }
        aux.setNext(aux.getNext().getNext());
    }  
    public void delete_all(T dato){
        this.addFirst();
        Nodo<T>aux=this.head;
        while(aux.getNext()!=null){
            if (aux.getNext().getDato()==dato){
                aux.setNext(aux.getNext().getNext());             
            }else{
                aux=aux.getNext();   
            }
        } 
        
        this.removeFirst();
    }
}
