package br.ufjf.dcc025.encorecrossover.euser;

import java.util.*;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public abstract class EUser {
    
    private static final Map<String,EUser> bank = new HashMap<>();
    
    //attributes
    private static EUser current = null;
    
    private String name;
    private String password;
    private final List<String[]> history;
    private final Set<String> favChars;
    
    //constructor
    public EUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.history = new ArrayList<>();
        this.favChars = new HashSet<>();
    }
    
    //methods
    public static void init(){
        EAdmin.initAdmin();
    }
    public static String export(){
        String string = "";
        for(String key : bank.keySet()){
            string += bank.get(key).toString();
        }
        return string;
    }
    
    static void add(EUser newUser){
        bank.put(newUser.getName(), newUser);
    }
    static EUser get(String name){
        return bank.get(name);
    }
    
    public static Set<String> getUserList(){
        return bank.keySet();
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        String string = "";
        string += password;
        
        //encrypt password using name as key
        
        return string;
    }
    public boolean setName(String username){
        if(bank.containsKey(username))
            return false;
        bank.remove(this.name);
        this.name = username;
        bank.put(username, this);
        return true;
    }
    public boolean setPassword(String password, String confirmation){
        if(password.equals(confirmation)){
            this.password = password;
            return true;
        }
        return false;
    }
    
    public static EUser login(String name, String password){
        if(bank.get(name) != null){
            if(bank.get(name).password.equals(password)){
                current = bank.get(name);
                return current;
            }
        }
        return null;
    }
    public static void logout(){
        if(current != null){
            current = null;
        }
    }
    
    public void addHistory(String[] string){
        String date = new Date().toString();
        String[] split = date.split(":");
        split[2] = split[2].replaceFirst(" ", "s ");
        date = split[0] + "h" + split[1] + "m" + split[2];
        string[0] = string[0] + " [" + date + "]";
        string[0] = string[0].replaceFirst(":", "h");
        string[0] = string[0].replaceFirst(":", "m");
        history.add(0, string);
    }
    public String getHistory(int id){
        String string = "";
        for(String s : history.get(id))
            string += s + "\n";
        return string;
    }
    public void removeHistory(int id){
        history.remove(id);
    }
    public List<String[]> listHistory(){
        List<String[]> list = new ArrayList<>();
        list.addAll(history);
        return list;
    }
    
    public void addFavChar(String character){
        favChars.add(character);
    }
    public List<String> getFavList(){
        List<String> temp = new ArrayList<>();
        temp.addAll(favChars);
        return temp;
    }
    public void removeFavChar(String character){
        favChars.remove(character);
    }
    
    public void sendRequest(String [] request){
        EUser.get("admin").addHistory(request);
    }
    public abstract void confirmRequest(int index);
    
    public static void toEUser(String info){
        String n = "";
        String p = "";
        List<String[]> h = new ArrayList<>();
        Set<String> fc = new HashSet<>();
        EUser ret = null;
        String[] type = info.split(":");
        String[] attributeSplit = type[1].split(", ");
        for(String attribute : attributeSplit){
            String[] temp = attribute.split("=");
            switch(temp[0]){
                case "name" -> {
                    n = temp[1];
                }
                case "password" -> {
                    p = temp[1];
                }
                case "favChars" -> {
                    if(temp.length > 1){
                        String[] cArr = temp[1].split("/");
                        Collections.addAll(fc, cArr);
                    }
                }
                case "history" -> {
                    if(temp.length > 1){
                        String[] hArr = temp[1].split("/");
                        for(String a : hArr){
                            h.add(a.split("@"));
                        }
                    }
                }
            }
        }
        if(type[0].equals("EAdmin"))
            ret = EAdmin.initAdmin();
        else //if(type[0].equals("EPlayer"))
            ret = EPlayer.create(n, p);
        ret.history.addAll(h);
        ret.favChars.addAll(fc);
    }
    @Override
    public String toString() {
        String temp = getClass() + ":";
        String[] split = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + name;
        temp += ", password=" + password; //quero cripografar essa parte
        temp += ", favChars=";
        for(String f : favChars)
            temp += f + "/";
        if(temp.charAt(temp.length()-1) == '/')
            temp = temp.substring(0, temp.length()-1);
        temp += ", history=";
        for(String[] hist : history){
            for(String h : hist)
                temp += h + "@";
            if(temp.charAt(temp.length()-1) == '@')
                temp = temp.substring(0, temp.length()-1);
            temp += "/";
        }
        if(temp.charAt(temp.length()-1) == '/')
            temp = temp.substring(0, temp.length()-1);
        temp += ";\n";
        return  temp;
    }
}