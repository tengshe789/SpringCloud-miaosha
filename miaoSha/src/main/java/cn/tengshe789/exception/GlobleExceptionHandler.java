package cn.tengshe789.exception;

import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
//        e.printStackTrace();
        if (e instanceof BindException){
            BindException exception=(BindException) e;
            String message = exception.getMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        }else if (e instanceof GlobleException){
            GlobleException exception=(GlobleException)e;
            return Result.error(exception.getCm());
        }
        else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
