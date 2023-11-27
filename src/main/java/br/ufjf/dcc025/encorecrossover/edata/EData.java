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
    public static boolean importData(){
        Scanner file;
        try{
            file = openInputFile("EncoreData.txt");
        }
        catch(FileNotFoundException e){
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
        String path = "EncoreData.txt";
        return new FileWriter(path);
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
        }
        catch(IOException e){
            System.out.println("openOutFile failed.");
            return false;
        }
        String compiledData = "";
        //read every object and save it on compiledData
        compiledData += ESkill.export();
        compiledData += EChar.export();
        compiledData += EUser.export();
        try{
            writeOut(fOut, compiledData);
        }
        catch(IOException e){
            System.out.println("writeOut failed.");
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