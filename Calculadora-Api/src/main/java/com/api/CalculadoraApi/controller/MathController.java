package com.api.CalculadoraApi.controller;

import com.api.CalculadoraApi.exceptions.UnsupportedMathOperationException;
import com.api.CalculadoraApi.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @Autowired
    private MathService mathService;

    @RequestMapping(value = "/mul/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double mul(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        double num1 = mathService.convertToDouble(numberOne);
        double num2 = mathService.convertToDouble(numberTwo);
        return mathService.mul(num1, num2);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        double num1 = mathService.convertToDouble(numberOne);
        double num2 = mathService.convertToDouble(numberTwo);
        if (num2 == 0) {
            throw new UnsupportedMathOperationException("Divisão por zero não é permitida.");
        }
        return mathService.div(num1, num2);
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        double num1 = mathService.convertToDouble(numberOne);
        double num2 = mathService.convertToDouble(numberTwo);
        return mathService.sub(num1, num2);
    }

    @RequestMapping(value = "/add/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double add(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        double num1 = mathService.convertToDouble(numberOne);
        double num2 = mathService.convertToDouble(numberTwo);
        return mathService.add(num1, num2);
    }

    @RequestMapping(value = "media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double media(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {
        double num1 = mathService.convertToDouble(numberOne);
        double num2 = mathService.convertToDouble(numberTwo);
        return mathService.media(num1, num2);
    }

    @RequestMapping(value = "raizde/{numberOne}", method = RequestMethod.GET)
    public double raizde(@PathVariable("numberOne") String numberOne) {
        double num = mathService.convertToDouble(numberOne);
        if (num < 0) {
            throw new UnsupportedMathOperationException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return mathService.raizde(num);
    }
}
