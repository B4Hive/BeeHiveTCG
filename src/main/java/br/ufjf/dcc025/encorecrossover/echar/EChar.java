package br.ufjf.dcc025.encorecrossover.echar;

import br.ufjf.dcc025.encorecrossover.eskill.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author b4bru
 */
public class EChar {
    
    private static final Map<String,EChar> characters = new HashMap<>();
    
    //attributes
    private final String name;
    private int hp;
    private final Map<String,ESkill> skills;
    private final Map<String,EEffect> effects;
    //private final Map<String,EItem> inventory;
    private int team;
    
    //constructor
    public EChar(String name, Map<String,ESkill> skills) {
        this.name = name;
        this.skills = new HashMap<>();
        this.skills.putAll(skills);
        this.effects = new HashMap<>();
        //this.inventory = new HashMap<>();
    }
    public static EChar createTemplate(String name){
        Map<String,ESkill> tempKit = new HashMap<>();
        tempKit.put(name+" ATK", ESkillDMG.create(name+" ATK",3,0));
        tempKit.put(name+" HEAL", ESkillHeal.create(name+" HEAL",3,2));
        tempKit.put(name+" HOT", ESkillEffect.create(name+" HOT",1,3,"HOT",2));
        tempKit.put(name+" DOT", ESkillEffect.create(name+" DOT",1,3,"DOT",2));
        tempKit.put(name+" BUFF", ESkillEffect.create(name+" BUFF",1,3,"DMG",2));
        characters.put(name, new EChar(name, tempKit));
        return characters.get(name);
    }
    public static EChar get(String name){
        return characters.get(name);
    }
    public static Set<String> getCharList(){
        return characters.keySet();
    }
    
    //getters
    public String getName() {
        return name;
    }
    public int getHP() {
        return hp;
    }
    public String getInfo(){
        String string = name;
        string += "\nTeam=" + team;
        string += "\nHP=" + hp;
        //string += getSkillList();
        string += "\nEffects=" + effects.keySet();
        //string += "\nInventory=" + inventory.keySet();
        return string;
    }

    public List<String> getSkillList() {
        List<String> list = new ArrayList<>();
        //only display skills off-cooldown
        for(String key : skills.keySet()){
            if(!skills.get(key).isOnCooldown())
                list.add(key);
        }
        return list;
    }
    public String getSkillInfo(String skill){
        String string = "Skill: ";
        if(skills.containsKey(skill))
            string += skills.get(skill).getDescription();
        else
            return null;
        return string;
    }
    public String getProfile(){
        String string = "Name: " + name + "\n";
        string += "Skills:\n";
        for(String s : skills.keySet()){
            string += skills.get(s).getDescription() + "\n";
        }
        return string;
    }
    //getEffects, getInventory
    
    //methods
    public static void init(){
        characters.put("PC", EChar.createTemplate("PC"));
        characters.put("NPC", EChar.createTemplate("NPC"));
    }
    //import
    public static String export(){
        String string = "";
        for(String key : characters.keySet()){
            string += characters.get(key).toString();
        }
        return string;
    }
    
    public void initChar(int team){
        this.hp = 20;
        this.team = team;
        /*
        for(String key : skills.keySet()){
            if(skills.get(key).getClass() == PassiveSkill.class)
                cast(key,this);
        }
        */
    }
    public void takeDMG(int value){
        this.hp -= value;
    }
    public void heal(int value){
        this.hp = Math.min(hp+value, 100);
    }
    public String cast(String skill, EChar target){
        String log = name + " uses ";
        if(skills.containsKey(skill)){
            log += skill + " on " + target.getName() + ".";
            log += "\n" + skills.get(skill).cast(target, effects);
        }
        else
            log += "nothing.";
        return log;
    }
    public boolean enemy(EChar target){
        return (this.team == target.team);
    }
    public void applyEffect(EEffect effect){
        effects.put(effect.getName(), effect);
    }
    public String updateEffects(){
        String string = "";
        List<String> removeList = new ArrayList<>();
        for(String key : effects.keySet()){
            if(effects.get(key).tick()){
                if(effects.get(key).getType().equals("DOT")){
                    takeDMG( effects.get(key).getValue() );
                    string += name + " takes " + effects.get(key).getValue();
                    string += " damage from " + key + ".\n";
                }
                if(effects.get(key).getType().equals("HOT")){
                    heal( effects.get(key).getValue() );
                    string += name + " heals " + effects.get(key).getValue();
                    string += " HP from " + key + ".\n";
                }
            }
            else
                removeList.add(key);
        }
        for(String key : removeList){
            effects.remove(key);
            string += name + " is no longer under the " + key + " effect.\n";
        }
        for(String key : skills.keySet()){
            skills.get(key).tickCooldown();
        }
        return string;
    }
    @Override
    public String toString() {
        String temp = getClass() + ":";
        String split[] = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + name;
        temp += ", skills=";//skills.keySet();
        for(String key : skills.keySet())
            temp += key + "/";
        temp = temp.substring(0, temp.length()-1);
        temp += ";\n";
        return temp;
    }
    
}
/*
Attributes{
    Set Skills;
    int HP;
    List effects;
    List inventory;
    int team;
}
Methods{
    takeDMG(int value);
    heal(int value);
    getSkillList();
    castSkill(EChar target);
}
*/