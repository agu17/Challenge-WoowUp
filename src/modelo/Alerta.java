package modelo;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Alerta {
    private Long id;
    private LocalDateTime fechaExpiracion;
    private Tema tema;
    private Boolean leida=false;
    private String tipo="";


    public Alerta(){}

    public Alerta(LocalDateTime unaFechaDeExpiracion, Tema unTema){
        this.setFechaExpiracion(unaFechaDeExpiracion);
        this.setTema(unTema);

    }

    //getters
    public Long getId(){
        return this.id;
    }
    public LocalDateTime getFechaExpiracion(){
        return this.fechaExpiracion;
    }
    
    public Tema getTema(){
        return this.tema;
    }
    public Boolean getLeida(){
        return this.leida;
    }
    public String getTipo(){
        return this.tipo;
    }

    //setters

    public void setTipo(String unTipo){
        this.tipo=unTipo;
    }
    public void setFechaExpiracion(LocalDateTime unaFecha){
       this.fechaExpiracion=unaFecha;
    }
    public void setTema(Tema unTema){
        this.tema=unTema;
    }

    public void leida(Boolean leida){
        this.leida=leida;
    }

    public abstract void enviarAlerta(List<Usuario> usuarios);

}
