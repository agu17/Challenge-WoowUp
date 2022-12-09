package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Long id;
    private String nombre;
    private List<Tema> temas;
    private List<Alerta> alertas;



    public Usuario(){
        this.temas =  new ArrayList<Tema>();
        this.alertas =  new ArrayList<Alerta>();
    }

    public Usuario(String unNombre){
        this.setNombre(unNombre);
        this.temas =  new ArrayList<Tema>();
        this.alertas =  new ArrayList<Alerta>();
    }

    //getters

    public List<Tema> getTemas(){
        return this.temas;
    }
    public List<Alerta> getAlertas(){
        return this.alertas;
    }
    public Long getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }

    //setters

    public void setNombre(String unNombre){
        this.nombre=unNombre;
    }


    public void agregarTema(Tema unTema){
        this.getTemas().add(unTema);
    }

    public void agregarAlerta(Alerta unaAlerta){
        this.getAlertas().add(unaAlerta);
    }

    public void leerAlerta(Alerta unaAlerta){
        for (Alerta alerta : this.getAlertas()) {
            if(alerta.equals(unaAlerta)){
                alerta.leida(true);
            }
        }
    }

    public List<Alerta> alertasNoExpiradas(){
        List<Alerta> alertasNoExpiradas = this.getAlertas();
        LocalDateTime hoy = LocalDateTime.now();
        alertasNoExpiradas.removeIf(alerta -> hoy.isAfter(alerta.getFechaExpiracion()));
        return alertasNoExpiradas;

    }

    public static List<Alerta> ordenarFechas(List<Alerta> alertas){  
        alertas.sort((alert1, alert2) -> alert1.getFechaExpiracion().compareTo(alert2.getFechaExpiracion()));
        return alertas;
    }

    public List<Alerta> obtenerAlertasNoLeidasNiExpiradas(){ 
        List<Alerta> alertasNoLeidas=new ArrayList<Alerta>();
        for (Alerta alerta : this.alertasNoExpiradas()) {
            if(!alerta.getLeida()){
                alertasNoLeidas.add(alerta);
            }
        }
        List<Alerta> alertasOrdenadas=this.ordenarFechas(alertasNoLeidas);
        return alertasOrdenadas;
        
    }


}
