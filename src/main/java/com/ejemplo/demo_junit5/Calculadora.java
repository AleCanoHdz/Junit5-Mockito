package com.ejemplo.demo_junit5;

public class Calculadora {

	private CalculadoraOracleCloud calculadoraOracleCloud;
	public double sumar(double num1, double num2) {
		return num1 + num2;
	}

	public double restar(double num1, double num2) {
		return num1 - num2;
	}
	
	public double multiplicar(double num1, double num2) {
		return num1 * num2;
	}
	
	public double dividir(double num1, double num2) {
		return num1 / num2;
	}

	public double sumarEnLaNube(double num1, double num2){
		return calculadoraOracleCloud.sumarEnOracleCloud(num1, num2);
	}


}
