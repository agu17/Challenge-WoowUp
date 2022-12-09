package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private Long id;
    private static Sistema sistema;
    private List<Tema> temas;
    private List<Usuario> usuarios;
    private List<Alerta> alertas;

    
    private Sistema(){
        this.usuarios = new ArrayList<Usuario>();
        this.temas =  new ArrayList<Tema>();
        this.alertas =  new ArrayList<Alerta>();
    }


    public static Sistema getInstance(){
        if(sistema==null){
            sistema= new Sistema();
        }
        return sistema;
    }

    //getters
    public List<Usuario> getUsuarios(){
            return this.usuarios;
    }
    public List<Tema> getTemas(){
        return this.temas;
    }
    public Long getId(){
        return this.id;
    }
    public List<Alerta> getAlertas(){
        return this.alertas;
    }


    public void registrarUsuario(Usuario unUsuario){
        getUsuarios().add(unUsuario);

    }

    public void registrarTema(Tema unTema){
        getTemas().add(unTema);
    }
   
    public void agregarUnTemaAlUsuario(Usuario unUsuario, Tema unTema){
        unUsuario.agregarTema(unTema);
    }

    
    public void enviarAlerta(Alerta unaAlerta){
        this.getAlertas().add(unaAlerta);
        unaAlerta.enviarAlerta(this.getUsuarios());
    }

    public List<Alerta> obtenerLasAlertasDeUnUsuario(Usuario unUsuario){
        return unUsuario.getAlertas();
    }
    
    
    public void enviarAlertaAUnUsuario(Alerta unaAlerta, Usuario unUsuario){
        if(unUsuario.getTemas().contains(unaAlerta.getTema())){
            this.getAlertas().add(unaAlerta);
            unUsuario.agregarAlerta(unaAlerta);
        }
        else{
            System.out.println("El usuario no posee el tema");

        }
        
    }

    
    public void alertaLeida(Alerta unaAlerta, Usuario unUsuario){
        unUsuario.leerAlerta(unaAlerta);    // acá puede ir tanto un objeto alerta, como directamente el id de alguna alerta.
    }
    

    

    public List<Alerta> obtenerAlertasNoLeidasNoExpiradas(Usuario unUsuario){
        return unUsuario.obtenerAlertasNoLeidasNiExpiradas();
    }



    
    public List<Alerta> obtenerAlertasDelTema(Tema unTema){
        List<Alerta> alertasDeUnTema= new ArrayList<>();
        for (Alerta alerta : this.getAlertas()) {
            if(alerta.getTema().equals(unTema)){
                alertasDeUnTema.add(alerta);
            }
        }
        return alertasDeUnTema;
    }


    public List<Alerta> obtenerAlertasNoExpiradas(List<Alerta> alertas){
            LocalDateTime hoy = LocalDateTime.now();
            alertas.removeIf(alert -> hoy.isAfter(alert.getFechaExpiracion()));
            return alertas;
    }


    public List<Alerta> obtenerAlertasNoExpiradasDeUnTema(Tema unTema){ 
        
        List<Alerta> alertasDeUnTema = obtenerAlertasDelTema(unTema);
        List<Alerta> alertasDeUnTemaNoExpiradas = obtenerAlertasNoExpiradas(alertasDeUnTema);
        alertasDeUnTemaNoExpiradas.sort((alert1, alert2) -> alert1.getFechaExpiracion().compareTo(alert2.getFechaExpiracion()));
        for (Alerta alerta : alertasDeUnTemaNoExpiradas) {
            System.out.println("El tipo de alerta es: " +  alerta.getTipo());
        }
        return alertasDeUnTemaNoExpiradas;
        
    }


   


}
