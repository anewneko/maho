package bot.discord.maho.api.Login.Exception.Handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import bot.discord.maho.api.Login.Controller.LoginController;
import bot.discord.maho.api.Login.Exception.SpeedKeyNotFoundException;
import bot.discord.maho.core.Model.ApiResponse;

@RestControllerAdvice(assignableTypes = {LoginController.class})
public class SpeedKeyNotFoundHandler {
	
	@ExceptionHandler(SpeedKeyNotFoundException.class)
    public ApiResponse handleDuplicateKeyException(SpeedKeyNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(), 403);
    }

}
