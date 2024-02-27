package gb.hw_2.crud.aspect;

import gb.hw_2.crud.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @TrackUserAction
    @Before("execution(* gb.hw_2.crud.controller.UserController.createUserForm(..))")
    public void logBeforeCreateUserForm() {
        logger.info("Aspects logging: Creating user button clicked from the main page");
    }

    @TrackUserAction
    @Before("execution(* gb.hw_2.crud.controller.UserController.createUser(..))")
    public void logCreatedUser(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof User) {
            User user = (User) args[0];
            logger.info("Aspects logging: Created user with firstName: " + user.getFirstName() + ", lastName: " + user.getLastName());
        }
    }
}
