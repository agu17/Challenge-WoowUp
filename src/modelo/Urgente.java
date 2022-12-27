package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Urgente extends Alerta{



    public Urgente(LocalDateTime unaFechaDeExpiracion, Tema unTema){
        super(unaFechaDeExpiracion, unTema);
        this.setTipo("Urgente");
    }
    public  void enviarAlerta(List<Usuario> usuarios){
        for (Usuario usuario : usuarios) {
            usuario.agregarAlerta(this);
        }
    }
}
