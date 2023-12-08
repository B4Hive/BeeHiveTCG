package br.ufjf.dcc025.encorecrossover.eengine;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.eskill.ESkill;
import br.ufjf.dcc025.encorecrossover.euser.EAdmin;
import br.ufjf.dcc025.encorecrossover.euser.EUser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class EData {
    
    private static Scanner openInputFile(String name) throws FileNotFoundException{
        return new Scanner(new FileReader(name)).useDelimiter(";\n");
    }
    public static void importData(){
        List<String> errors = new ArrayList<>(); 
        if (!EImport("ESkill.txt")) {
            errors.add("EImport(ESkill.txt)");
            ESkill.init();
        }
        if (!EImport("EChar.txt")) {
            errors.add("EImport(EChar.txt)");
            EChar.init();
        }
        if (!EImport("EUser.txt")) {
            errors.add("EImport(EUser.txt)");
            EUser.init();
        }
        for(String e : errors)
            EAdmin.reportError(e);
    }
    private static boolean EImport(String fileName) {
        Scanner file;
        try {
            file = openInputFile(fileName);
        } catch (FileNotFoundException e) {
            return false;
        }
        
        while(file.hasNext()){
            String line = file.next();
            if(fileName.equals("ESkill.txt"))
                ESkill.toESkill(line);
            if(fileName.equals("EChar.txt"))
                EChar.toEChar(line);
            if(fileName.equals("EUser.txt"))
                EUser.toEUser(line);
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
    public static void exportData(){
        ESkill.demlurb();
        FileWriter fOut = null;
        ESkillExport(fOut);
        ECharExport(fOut);
        EUserExport(fOut);
    }
    
    private static void EUserExport(FileWriter fOut) {
        //EUser.export();
        try{
            fOut = openOutFile("EUser.txt");
        }
        catch(IOException e){
            EAdmin.reportError("EData.openOutFile(EUser.txt);");
        }
        try{
            writeOut(fOut, EUser.export());
        }
        catch(IOException e){
            EAdmin.reportError("EData.writeOut(EUser.txt);");
        }
    }
    private static void ECharExport(FileWriter fOut) {
        //EChar.export();
        try{
            fOut = openOutFile("EChar.txt");
        }
        catch(IOException e){
            EAdmin.reportError("EData.openOutFile(EChar.txt);");
        }
        try{
            writeOut(fOut, EChar.export());
        }
        catch(IOException e){
            EAdmin.reportError("EData.writeOut(EChar.txt);");
        }
    }
    private static void ESkillExport(FileWriter fOut) {
        //ESkill.export();
        try{
            fOut = openOutFile("ESkill.txt");
        }
        catch(IOException e){
            EAdmin.reportError("EData.openOutFile(ESkill.txt);");
        }
        try{
            writeOut(fOut, ESkill.export());
        }
        catch(IOException e){
            EAdmin.reportError("EData.writeOut(ESkill.txt)");
        }
    }
    
}