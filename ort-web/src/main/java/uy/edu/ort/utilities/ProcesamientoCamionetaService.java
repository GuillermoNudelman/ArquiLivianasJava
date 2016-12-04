/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.utilities;

import java.util.List;
import uy.edu.ort.model.Camioneta;
import uy.edu.ort.model.ProcesamientoCamioneta;

/**
 *
 * @author ptrecca
 */
public interface ProcesamientoCamionetaService {

    public void addProcesamientoCamioneta(Camioneta camioneta);

    public List<ProcesamientoCamioneta> listaProcesamientoCamioneta();
}
