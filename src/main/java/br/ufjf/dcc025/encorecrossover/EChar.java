/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class EChar {
    //attributes
    private final String name;
    private int hp;
    private final Map<String,ESkill> skills;
    private final Map<String,EEffect> effects;
    private final Map<String,EItem> inventory;
    private int team;
    
    //constructor
    public EChar(String name, Map<String,ESkill> skills) {
        this.name = name;
        this.skills = new HashMap<>();
        this.skills.putAll(skills);
        this.effects = new HashMap<>();
        this.inventory = new HashMap<>();
    }
    public static EChar createTemplate(String name){
        Map<String,ESkill> tempKit = new HashMap<>();
        tempKit.put("BA", EData.skills.get("BA"));
        tempKit.put("BH", EData.skills.get("BH"));
        EData.characters.put(name, new EChar(name, tempKit));
        return EData.characters.get(name);
    }
    
    //getters
    public String getName() {
        return name;
    }
    public int getHP() {
        return hp;
    }
    //getSkills, getEffects, getInventory
    
    //methods
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
        //verifica antes se tem effect de defesa
        this.hp -= value;
    }
    public void heal(int value){
        this.hp = Math.min(hp+value, 100);
    }
    public String cast(String skill, EChar target){
        String log = name + " uses ";
        if(skills.containsKey(skill)){
            log += skill + " on " + target.getName();
            log += "\n" + skills.get(skill).cast(target);
        }
        return log;
    }
    public boolean enemy(EChar target){
        return (this.team == target.team);
    }
    
    public String getInfo(){
        String string = name;
        string += "\nTeam=" + team;
        string += "\nHP=" + hp;
        //only display skills off-cooldown
        string += "\nSkills=" + skills.keySet();
        string += "\nEffects=" + effects.keySet();
        string += "\nInventory=" + inventory.keySet();
        return string;
    }
    public String getSkillInfo(String skill){
        String string = "Skill: ";
        if(skills.containsKey(skill))
            string += skills.get(skill).getDescription();
        else
            return null;
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