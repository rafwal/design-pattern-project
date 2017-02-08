package pl.edu.agh.app.lib.metrics.timers.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pl.edu.agh.app.lib.metrics.timers.*;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class TimerAspect {


    @Around("@annotation(pl.edu.agh.app.lib.metrics.timers.aop.Timer)")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        String timerName = getTimerName(pjp);
        pl.edu.agh.app.lib.metrics.timers.Timer timer = TimerRegistry.getInstance().timer(timerName);
        Object output = pjp.proceed();
        timer.close();
        return output;
    }

    private String getTimerName(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Annotation[] annotations = signature.getMethod().getAnnotations();
        for (Annotation ann: annotations) {
            if (ann.annotationType().equals(Timer.class)) {
                return ((Timer)ann).value();
            }
        }
        throw new RuntimeException();
    }
}
