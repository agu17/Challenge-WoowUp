package modelo;

public class Tema {
    private Long id;
    private String nombre;
    
    // No estaba permitido usar un tema sin nombre, por lo que no es necesario utilizar el constructor tema vacio.
    public Tema(){}

    public Tema(String unNombre){
        this.setNombre(unNombre);
    }
    
    //getters
    public Long getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String unNombre){
        this.nombre=unNombre;
    }
}
