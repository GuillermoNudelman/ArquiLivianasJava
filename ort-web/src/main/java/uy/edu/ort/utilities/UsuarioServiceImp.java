package uy.edu.ort.utilities;

/**
 * Implementacion de la interfaz usuarioService
 * guarda en memoria el numbre del usuario ejecutando la aplicacion.
 * 
 */
public class UsuarioServiceImp implements UsuarioService{
    
    private String nombre;

    @Override
    public void guardarNombre(String nombreUsuario) {        
        nombre = nombreUsuario;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
