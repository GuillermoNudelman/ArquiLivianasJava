package uy.edu.ort.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {

    public void metodoAspecto() {
        System.out.println("metodo del aspecto MyAspect");
        // logica negocio aspecto
    }

    public void metodoRet(Object retVal) {
        System.out.println("metodo aspecto retVal [" + retVal + "].");
    }

    public Object metodoAround(ProceedingJoinPoint pjp) {
        Object ret = null;
        try {
            long beginExec = System.nanoTime();
            // ejecucion del metodo interceptado
            ret = pjp.proceed();
            long endExec = System.nanoTime();
            long time = endExec - beginExec;
            System.out.println("around => " + pjp.getSignature().getName());
            System.out.println("time nano ms => " + time);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return ret;
    }
}
