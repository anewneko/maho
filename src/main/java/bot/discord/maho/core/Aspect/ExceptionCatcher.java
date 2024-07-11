package bot.discord.maho.core.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class ExceptionCatcher {
	
	@Pointcut("@annotation(org.springframework.stereotype.Controller)")
    void controller(){}
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    void restController(){}
	
	@Around("controller() && restController()")
    public Object around(ProceedingJoinPoint pjp) {
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
