package de.hsos.kbse.logger.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import de.hsos.kbse.logger.level.LogLevel;
import de.hsos.kbse.logger._interface.Logable;
import java.io.Serializable;

@Logable
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 10)
public class LoggerInterceptor implements Serializable
{
    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class.getName());
    
    public LoggerInterceptor()
    {
        
    }
    
    @AroundInvoke
    public Object logMethodCall(InvocationContext ctx) 
    {
        Object result = null;
        LogLevel logLevel = ctx.getMethod().getAnnotation(Logable.class).logLevel();
        Level level = Level.OFF;
        
        switch(logLevel)
        {
            case INFO:
                level = Level.INFO;
            break;
            
            case WARNING:
                level = Level.WARNING;
            break;
            
            case SEVERE:
                level = Level.SEVERE;
            break;
        }
        
        LoggerInterceptor.LOGGER.log(level, ctx.getMethod().getName() + " started");
        
        try
        {
            result = ctx.proceed();
        }
        
        catch(Exception e)
        {
            
        }
        
        LoggerInterceptor.LOGGER.log(Level.INFO, ctx.getMethod().getName() + " ended");
        
        return result;
    }
}
