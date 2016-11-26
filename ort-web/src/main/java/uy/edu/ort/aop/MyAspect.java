package uy.edu.ort.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {

    @Pointcut("execution(* uy.edu.ort.service.*.*(..))")
    public void myjp() {
    }

    @Around("myjp()")
    public Object metodoAspecto(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        System.out.println("Inicio metodo del aspecto MyAspect ");
        try {
            // ejecuto metodo interceptado
            object = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin metodo del aspecto MyAspect ");

        return object;
    }

    @After("myjp()")
    public void otroMetodoAspecto() {
        System.out.println("Otro metodo del aspecto MyAspect ");
    }
}
