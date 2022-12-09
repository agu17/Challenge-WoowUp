package modelo;

public class Tema {
    private Long id;
    private String nombre;
    

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
