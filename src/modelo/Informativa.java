package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Informativa extends Alerta {
    
    public Informativa(LocalDateTime unaFechaDeExpiracion, Tema unTema){
        super(unaFechaDeExpiracion, unTema);
        this.setTipo("Informativa");
    }

    public  void enviarAlerta(List<Usuario> usuarios){
        for (Usuario usuario : usuarios) {
            if(usuario.getTemas().contains(this.getTema())){
                usuario.agregarAlerta(this);
            }
        }
    }
        
    
}
