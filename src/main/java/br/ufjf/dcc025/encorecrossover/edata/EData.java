package br.ufjf.dcc025.encorecrossover.edata;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.eskill.ESkill;
import br.ufjf.dcc025.encorecrossover.euser.EUser;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author b4bru
 */
public class EData {
    
    public static void initBaseData(){
        ESkill.init();
        EChar.init();
        EUser.init();
    }
    
    private static Scanner openInputFile(String name) throws FileNotFoundException{
        return new Scanner(new FileReader(name)).useDelimiter(";\n");
    }
    public static boolean importData(String fileName){
        Scanner file;
        try{
            file = openInputFile(fileName);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found. Contact admin.");
            initBaseData();
            return false;
        }
        while(file.hasNext()){
            String line = file.next();
            if(line.contains("ESkill")){
                ESkill.toESkill(line);
            }
            else if(line.contains("EChar")){
                
            }
            else if(line.contains("EUser")){
                
            }
        }
        return true;
    }
    
    private static FileWriter openOutFile(String fileName) throws IOException {
        return new FileWriter(fileName);
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
            fOut = openOutFile("ESkill.txt");
        }
        catch(IOException e){
            System.out.println("EData.openOutFile() failed.");
            return false;
        }
        String compiledData = "";
        compiledData += ESkill.export();
        try{
            writeOut(fOut, compiledData);
        }
        catch(IOException e){
            System.out.println("EData.writeOut() failed.");
            return false;
        }
        
        try{
            fOut = openOutFile("EChar.txt");
        }
        catch(IOException e){
            System.out.println("EData.openOutFile() failed.");
            return false;
        }
        compiledData = "";
        compiledData += EChar.export();
        try{
            writeOut(fOut, compiledData);
        }
        catch(IOException e){
            System.out.println("EData.writeOut() failed.");
            return false;
        }
        
        try{
            fOut = openOutFile("EUser.txt");
        }
        catch(IOException e){
            System.out.println("EData.openOutFile() failed.");
            return false;
        }
        compiledData = "";
        compiledData += EUser.export();
        try{
            writeOut(fOut, compiledData);
        }
        catch(IOException e){
            System.out.println("EData.writeOut() failed.");
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