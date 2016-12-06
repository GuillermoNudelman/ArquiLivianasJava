package uy.edu.ort.aop;

import java.util.Date;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import uy.edu.ort.model.LogTrazabilidad;
import uy.edu.ort.utilities.LogTrazabilidadService;

/*
* Esta clase se ejecuta cuando cualquier metodo de cualquier clase dentro de la carpeta
* ort.edu.uy.service, se ejecuta y guarda en la base de datos el usuario que la ejecuto, la fecha
* y el tiempo que demoró en ejecutarse
 */
public class Trazabilidad {

    private LogTrazabilidadService logTrazabilidadService;

    public void setLogTrazabilidadService(LogTrazabilidadService logTrazabilidadService) {
        this.logTrazabilidadService = logTrazabilidadService;
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

        String userName = "Usuario Anonimo";
        try {
            HttpSession session = (HttpSession) RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
            userName = (String) session.getAttribute("user");
        } catch (Exception e) {}

        LogTrazabilidad logTrazabilidad = new LogTrazabilidad();
        logTrazabilidad.setFechaOperacion(date);
        logTrazabilidad.setNombreOperacion(pjp.getSignature().getName());
        logTrazabilidad.setUsuario(userName);
        logTrazabilidad.setParametros(arguments);
        logTrazabilidad.setTiempoEjecucion(time);
        logTrazabilidadService.addLogTrazabilidad(logTrazabilidad);
        return ret;
    }
}
