package project.shimozukuri.datastoremicroservice.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.shimozukuri.datastoremicroservice.model.exception.SensorNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SensorNotFoundException.class)
    public String sensorNotFound(SensorNotFoundException e) {
        e.printStackTrace();
        return "Sensor not found";
    }

    @ExceptionHandler
    public String server(Exception e) {
        e.printStackTrace();
        return "Something happend.";
    }
}
