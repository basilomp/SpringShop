package spring.shop.aspects;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Aspect
@Component
@Data
@AllArgsConstructor
public class ServiceTimeAspect {

    private Map<String, Long> servicesTotalTime = new HashMap<>();

    @Around("execution(public * spring.shop.services.*.*(..))")
    public Object countTotalTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long totalTime = end - start;
        String serviceName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        if(servicesTotalTime.containsKey(serviceName)) {
            long newtotalTime = totalTime + servicesTotalTime.get(serviceName);
            servicesTotalTime.replace(serviceName, newtotalTime);
        } else {
            servicesTotalTime.put(serviceName, totalTime);
        }

        for (Map.Entry<String, Long> stringLongEntry : servicesTotalTime.entrySet()) {
            System.out.println(stringLongEntry.toString());
        }
        System.out.println();
        return out;
    }





}
