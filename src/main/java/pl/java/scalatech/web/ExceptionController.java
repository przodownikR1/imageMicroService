package pl.java.scalatech.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

//f@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public String illegalArgs(Model model,IllegalArgumentException iea){
        model.addAttribute("error",new Error(iea.getMessage()));
        log.info("++++ {}",model);
         return "errors";
    }




}
