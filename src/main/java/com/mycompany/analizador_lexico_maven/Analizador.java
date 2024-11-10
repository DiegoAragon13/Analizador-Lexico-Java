/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizador_lexico_maven;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {
    private static final Set<String> palabrasReservadas = new HashSet<>();
    private List<String> errores = new ArrayList<>();
    private List<String> identificadores = new ArrayList<>();
    private List<String> numeros = new ArrayList<>();
    private List<String> operadoresAsignacion = new ArrayList<>();
    private List<String> operadoresAritmeticos = new ArrayList<>(); 
    private List<String> signosPuntuacion = new ArrayList<>();

    static {
        String[] reservadas = {
           "const", "int", "char", "if", "float", "public", "class", "double", "else",
           "for", "private", "new", "final", "boolean", "static"     
        };
        for (String palabra : reservadas) {
            palabrasReservadas.add(palabra);
        }
    }

    public List<String> detectarPalabrasReservadas(String texto) {
        List<String> palabrasEncontradas = new ArrayList<>();
        String[] palabras = texto.split("\\W+");//<-- DIVIDE LAS PALABARAS y crea un arreglo []
        
        for (String palabra : palabras) {
            if (palabra.isEmpty()) continue;
            if (palabrasReservadas.contains(palabra)) {
                palabrasEncontradas.add(palabra);
            }
        }
        
        return palabrasEncontradas;
    }

    public void detectarIdentificadores(String texto) {
        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";//Rex es expresion regular y estoy definiendo las reglas para un identificador valido 
        Pattern pattern = Pattern.compile(regex);//Pettern nos va a ayudar a compilar  la expresión regular
        
        String[] palabras = texto.split("\\W+");
        
        for (String palabra : palabras) {
            if (palabra.isEmpty()) continue;
            if (!palabrasReservadas.contains(palabra)) {//Verifique que la palabra no sea palabra reservada 
                // Primero verificamos si es un número
                try {
                    Double.parseDouble(palabra);
                    continue; // Si es un número, saltamos al siguiente
                } catch (NumberFormatException e) {
                    // Si no es un número, verificamos si es un identificador
                    Matcher matcher = pattern.matcher(palabra);//Va de la mano con el Pattern para identificar coincidencias con la regex
                    if (matcher.matches()) {
                        identificadores.add(palabra);
                    } else {
                        errores.add("Error 505: '" + palabra + "' no es un identificador válido.");
                    }
                }
            }
        }
    }
    
        // Método nuevo para detectar operadores aritméticos
    public void detectarOperadoresAritmeticos(String texto) {
        String regex = "\\+\\+|--|[+\\-*/%]";
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(texto);
        
        while (matcher.find()) {
            String operador = matcher.group();
            // Evitar confundir operadores de asignación compuesta =+, -=
            if (!texto.substring(matcher.end()).startsWith("=")) {
                operadoresAritmeticos.add(operador);
            }
        }
    }

    


    public void detectarNumeros(String texto) {
        String regex = "\\b\\d+\\.?\\d*\\b";//--> //b delimita que el numero este por caracteres 
        Pattern pattern = Pattern.compile(regex);//--> //d+ es pára captirar la parte entera si esta prescente 
                                                 //--> //.? para detectar puntos decimales   ?= 0 o 1 veces
        Matcher matcher = pattern.matcher(texto);                                    //--> //d* para capturar 0 o mas cifras despues del .
        while (matcher.find()) {                    //b es para delimitar que este aislado el numero 
            numeros.add(matcher.group());
        }
    }
    
    
       



    

    public void detectarOperadoresAsignacion(String texto) {
        String regex = "(=|\\+=|-=|\\*=|/=)";
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(texto);
        
        while (matcher.find()) {
            operadoresAsignacion.add(matcher.group());
        }
    }

    public void detectarSignosPuntuacion(String texto) {
        String regex = "[.,;:]";
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(texto);
        
        while (matcher.find()) {
            signosPuntuacion.add(matcher.group());
        }
    }

    public List<String> obtenerErrores() {
        return errores;
    }

    public static void main(String[] args) {
        Analizador analizador = new Analizador();
        /*
        String texto = "const pi = 3.1416;  INT num = 123; float f = 2.5, entero = 0;";
        
        List<String> palabras = analizador.detectarPalabrasReservadas(texto);
        System.out.println("Palabras reservadas encontradas: " + palabras);
        
        analizador.detectarNumeros(texto);
        System.out.println("Números encontrados: " + analizador.numeros);
        
        analizador.detectarIdentificadores(texto);
        System.out.println("Identificadores encontrados: " + analizador.identificadores);
        
        analizador.detectarOperadoresAsignacion(texto);
        System.out.println("Operadores de asignación encontrados: " + analizador.operadoresAsignacion);
        
        analizador.detectarSignosPuntuacion(texto);
        System.out.println("Signos de puntuación encontrados: " + analizador.signosPuntuacion);
        */
        List<String> errores = analizador.obtenerErrores();
        if (!errores.isEmpty()) {
            System.out.println("Errores encontrados:");
            for (String error : errores) {
                System.out.println(error);
            }
        } else {
            System.out.println("No se encontraron errores.");
        }
    }
       public List<String> getIdentificadores() {
        return identificadores;
    }

    public List<String> getNumeros() {
        return numeros;
    }

    public List<String> getOperadoresAsignacion() {
        return operadoresAsignacion;
    }

    public List<String> getSignosPuntuacion() {
        return signosPuntuacion;
    }
        public List<String> getOperadoresAritmeticos() {
        return operadoresAritmeticos;
    }
}