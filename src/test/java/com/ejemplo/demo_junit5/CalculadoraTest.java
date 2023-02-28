package com.ejemplo.demo_junit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) //Esta extension es necesaria para que la creacion de insrancia como mock funcione
public class CalculadoraTest {
    //En caso de que no podamos acceder a las dependencias mediante el constructor de la clase se coloca:

    @InjectMocks
    Calculadora calculadora = new Calculadora();; //Se crea esta variable de la clase Calculadora para que todos los metodos de la clase puedan hacer uso de ella

    /*
    //Stub o mock
    CalculadoraOracleCloud calculadoraOracleCloud = (double... number) -> {
        return  number[0] + number[1];
    };
    */



    //En lugar de escribir el stub o mock con comportamiento predecible se hace lo siguiente:
    @Mock CalculadoraOracleCloud calculadoraOracleCloud; //Se genera una instancia como mock

    @BeforeAll //Esta anotacion se ejecuta antes que cualquier otro y solo se ejecuta una sola vez
    public static void startup(){ //Se declara como static
        //System.out.println("Inicalizacion global");
    }

    @AfterAll //Esta se ejecuta al final de todos los metodos test de la clase y solo se ejecuta una sola vez
    public static void destroy(){
        //System.out.println("Finalizacion global");
    }

    /*
    @BeforeEach //Esta anotacion permite que se ejecute el siguiente metodo antes de cada test
    public void init(){
        calculadora = new Calculadora(calculadoraOracleCloud); //De esta manera podemos evitar colocar la creacion de la instancia de la clase en cada uno de los metodos test
        //System.out.println("Inicializando test");
    }
    */

    @AfterEach //Esta anotacion permite que se ejecute al final de cada test
    public void shutDown(){
        //Dentro de ella podemos cerrar conexiones a bases de datos, preparar la ejecucion del test siguiente, limpiar tablas, limpiar inserts, etc
        //System.out.println("Finalizando test");
    }

	@Test //Indica que el siguiente metodo es un test
    @DisplayName("Test de suma") //Esta anotacion permite personalizar el nombre del test
	public void probarSuma() {
		//Esperando
		//double esperado = 25;
		//Ejecutar y obtener
		//var calculadora = new Calculadora();
		//double obtenido = calculadora.sumar(10, 15);
		//Comparar esperado vs obtenido
		//assertEquals(esperado, obtenido);

        //Codigo simplificado
        //var calculadora = new Calculadora();
        assertEquals(25.0,calculadora.sumar(5,20));
        //System.out.println("Sumando...");
	}

    @Test
    @Disabled("El test de resta fue deshabilitado porque...") //Esta anotacion permite deshabilitar un test y colocar el motivo
    public void probarResta() {
        //Codigo simplificado
        //var calculadora = new Calculadora();
        assertEquals(30,calculadora.restar(50,20));
        //System.out.println("Restando...");
    }

    @Test
    public void probarMultiplicacion() {
        //Codigo simplificado
        //var calculadora = new Calculadora();
        assertEquals(25,calculadora.multiplicar(5,5));
        //System.out.println("Multiplicando...");
    }


    @Test
    public void division(){
        assertThrows(ArithmeticException.class, () -> {
            var division = 100/0;
        }); //AssertThrows lo que nos permite es que al momento de ejecutarlo nos muestre la excepcion que lanza
    }

    @Test
    @DisplayName("Prueba de la tabla del 5")
    public void porbarTabla5(){
        int[] numbers = {1,2,3,4,5};
        //Para ejecutar una collecion de bloques de ejecucion se usa assertAll mediante una expresion lambda, esta expresion fallara su uno de los blooques falla
        assertAll("Tabla del 5",
                () -> {assertEquals(5,calculadora.multiplicar(5,1));},
                () -> {assertEquals(10,calculadora.multiplicar(5,2));},
                () -> {assertEquals(15,calculadora.multiplicar(5,3));},
                () -> {assertEquals(20,calculadora.multiplicar(5,4));},
                () -> {assertEquals(25,calculadora.multiplicar(5,5));}
                );
    }

    @Test
    public void probarSumaEnLaNube(){
        when(calculadoraOracleCloud.sumarEnOracleCloud(5,20)).thenReturn(25.0);
        lenient().when(calculadoraOracleCloud.sumarEnOracleCloud(5,5)).thenReturn(10.0);

        assertEquals(25, calculadora.sumarEnLaNube(5,20));
    }

}
