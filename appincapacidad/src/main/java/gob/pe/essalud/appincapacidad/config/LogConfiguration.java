package gob.pe.essalud.appincapacidad.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogConfiguration {
    private static final Logger logger = LogManager.getLogger(LogConfiguration.class);

    @Pointcut("@annotation(gob.pe.essalud.appincapacidad.seguridad.annotation.LogUsuario)")
    public void annotationPointCut(){}



    @After("annotationPointCut()")
    public void logAfter(JoinPoint joinPoint)throws Throwable{

    }

}
