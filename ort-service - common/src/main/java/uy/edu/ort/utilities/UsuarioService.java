package uy.edu.ort.utilities;

/**
 * Este servicio sirve para guardar el nombre del usuario que esta ejecutando la aplicacion
 * 
 */
public interface UsuarioService {
    
    void guardarNombre(String nombre);
    
    String getNombre();
}
