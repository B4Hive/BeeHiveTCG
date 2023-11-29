/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover.eengine;

import java.util.Scanner;

/**
 *
 * @author b4bru
 */
public class Screen {
    
    static Scanner kb = new Scanner(System.in);
    
    public static void loginScreen(){
        int opt = 0;
        do{
            System.out.println();
            System.out.println("*Login*");
            //option list
            opt = kb.nextInt();
            //option methods
        } while(opt != 999);
    }
    
    public static void userScreen(){
        
    }
    
    public static void skillCreationScreen(){
        
    }
    
    public static void characterCreationScreen(){
        
    }
    
    //itemCreationScreen()
    
    public static void characterSelectionScreen(){
        
    }
    
    
}