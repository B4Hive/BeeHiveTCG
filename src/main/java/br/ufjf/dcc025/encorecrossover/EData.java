/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author b4bru
 */
public class EData {
    public static Map<String,EChar> characters = new HashMap<>();
    public static Map<String,ESkill> skills = new HashMap<>();
    
    public static void initBaseData(){
        skills.put("BA", DamageSkill.create("BA", 3, 0));
        skills.put("BH", SustainSkill.create("BH", 3, 2));
    }
    
    private static Scanner openInputFile(String name) throws FileNotFoundException{
        return new Scanner(new FileReader(name)).useDelimiter(";\n");
    }
    
    public static boolean importData(){
        Scanner file;
        try{
            file = openInputFile("EncoreData.txt");
        }catch(FileNotFoundException e){
            System.out.println("File not found. Contact admin.");
            initBaseData();
            return false;
        }
        while(file.hasNext()){
            String line = file.next();
            String splitLine[] = line.split(":",2);
            switch(splitLine[0]){
                
            }
        }
        return true;
    }
    
    private static FileWriter openOutFile() throws IOException {
        return new FileWriter("EncoreData.txt");
    }
    private static void writeOut(FileWriter fOut, String compiledData) throws IOException {
        try (fOut) {
            fOut.flush();
            fOut.write(compiledData);
        }
    }
    public static boolean exportData(){
        FileWriter fOut;
        try{
            fOut = openOutFile();
        }catch(IOException e){
            return false;
        }
        String compiledData = "";
        //read every object and save it on compiledData
        for(String key : skills.keySet()){
            compiledData += skills.get(key).toString();
        }
        for(String key : characters.keySet()){
            compiledData += characters.get(key).toString();
        }
        try{
            writeOut(fOut, compiledData);
        }catch(IOException e){
            return false;
        }
        return true;
    }

    

}
/*
Atributes{
    Map<String,EChar> characters;
    Map<String,ESkill> skills;
    Map<String,EEffect> effects;
    Map<String,EItems> items;
    Map<String,EUser> players;
}
*/