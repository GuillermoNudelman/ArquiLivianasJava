package uy.edu.ort.aop;

import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import uy.edu.ort.model.LogTrazabilidad;
import uy.edu.ort.utilities.LogTrazabilidadService;
import uy.edu.ort.utilities.UsuarioService;

/*
* Esta clase se ejecuta cuando cualquier metodo de cualquier clase dentro de la carpeta
* ort.edu.uy.service, se ejecuta y guarda en la base de datos el usuario que la ejecuto, la fecha
* y el tiempo que demoró en ejecutarse
 */
public class Trazabilidad {

    private LogTrazabilidadService logTrazabilidadService;

    private UsuarioService usuarioService;

    public void setLogTrazabilidadService(LogTrazabilidadService logTrazabilidadService) {
        this.logTrazabilidadService = logTrazabilidadService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Object metodoAround(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = null;
        long beginExec = System.nanoTime();

        ret = pjp.proceed();
        long endExec = System.nanoTime();
        long time = endExec - beginExec;

        Date date = new Date();

        String arguments = "-";
        for (final Object argument : pjp.getArgs()) {
            arguments += " " + argument + " -";
        }

        LogTrazabilidad logTrazabilidad = new LogTrazabilidad();
        logTrazabilidad.setFechaOperacion(date);
        logTrazabilidad.setNombreOperacion(pjp.getSignature().getName());
        logTrazabilidad.setUsuario(usuarioService.getNombre());
        logTrazabilidad.setParametros(arguments);
        logTrazabilidad.setTiempoEjecucion(time);
        logTrazabilidadService.addLogTrazabilidad(logTrazabilidad);
        return ret;
    }
}
