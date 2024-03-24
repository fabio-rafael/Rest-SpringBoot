package com.api.CalculadoraApi.service;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    public double add(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    public double sub(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    public double mul(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }

    public double div(double numberOne, double numberTwo) {
        if (numberTwo == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return numberOne / numberTwo;
    }

    public double media(double numberOne, double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    public double raizde(double numberOne) {
        if (numberOne < 0) {
            throw new IllegalArgumentException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(numberOne);
    }

    public boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?\\d*\\.?\\d+");
    }

    public double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }
}

