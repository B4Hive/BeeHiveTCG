package br.ufjf.dcc025.encorecrossover.echar;

import br.ufjf.dcc025.encorecrossover.eskill.*;
import br.ufjf.dcc025.encorecrossover.euser.EAdmin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class EChar {
    
    private static final Map<String,EChar> bank = new HashMap<>();
    private static final int MAX_HP = 20;
    //attributes
    private String name;
    private int hp;
    private final Map<String,ESkill> skills;
    private final Map<String,EEffect> effects;
    private int team;
    
    //constructor
    public EChar(){
        this.name = "Character Name";
        this.skills = new HashMap<>();
        this.effects = new HashMap<>();
    }
    public EChar(String name, Map<String,ESkill> skills) {
        this.name = name;
        this.skills = new HashMap<>();
        this.skills.putAll(skills);
        this.effects = new HashMap<>();
    }
    public static EChar createTemplate(String name){
        Map<String,ESkill> tempKit = new HashMap<>();
        tempKit.put(name+" ATK", ESkillDMG.create(name+" ATK",3,0));
        tempKit.put(name+" HEAL", ESkillHeal.create(name+" HEAL",3,2));
        tempKit.put(name+" HOT", ESkillEffect.create(name+" HOT",1,3,"HOT",2));
        tempKit.put(name+" DOT", ESkillEffect.create(name+" DOT",1,3,"DOT",2));
        tempKit.put(name+" BUFF", ESkillEffect.create(name+" BUFF",1,3,"DMG",2));
        bank.put(name, new EChar(name, tempKit));
        return bank.get(name);
    }
    
    static void add(EChar object){
        bank.put(object.getName(), object);
    }
    public static void remove(EAdmin admin, String name){
        bank.remove(name);
    }
    
    public void addSkill(ESkill skill){
        this.skills.put(skill.getName(), skill);
    }
    public void removeSkill(String skill){
        this.skills.remove(skill);
    }
    
    //getters
    public static EChar get(String name){
        return bank.get(name);
    }
    public static Set<String> getCharList(){
        return bank.keySet();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getHP() {
        return hp;
    }
    public String getInfo(){
        String string = name;
        string += "\nTeam=" + team;
        string += "\nHP=" + hp;
        string += "\nEffects=" + effects.keySet();
        return string;
    }
    public List<String> getFullSkillList(){
        List<String> list = new ArrayList<>();
        for(String key : skills.keySet()){
            list.add(key);
        }
        return list;
    }
    public List<String> getOffCooldownSkills() {
        List<String> list = new ArrayList<>();
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
    
    //methods
    public static void init(){
        bank.put("PC", EChar.createTemplate("PC"));
        bank.put("NPC", EChar.createTemplate("NPC"));
    }
    
    //import
    public static String export(){
        String string = "";
        for(String key : bank.keySet()){
            string += bank.get(key).toString();
        }
        return string;
    }
    
    public void initChar(int team){
        this.hp = MAX_HP;
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
        this.hp = Math.min(hp+value, MAX_HP);
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
    
    public static void toEChar(String info){
        String n = "";
        Map<String,ESkill> skills = new HashMap<>();
        EChar ret = null;
        info = info.replaceAll("#", ":").replaceAll("->", "=").replace(";", "");
        String[] type = info.split(":");
        String[] attributeSplit = type[1].split(", ");
        for(String attribute : attributeSplit){
            String[] temp = attribute.split("=");
            switch(temp[0]){
                case "name" -> {
                    n = temp[1];
                }
                case "skills" -> {
                    String[] skill = temp[1].split("/");
                    for(String s : skill){
                        skills.put(s, ESkill.get(s));
                    }
                }
            }
        }
        ret = new EChar(n, skills);
        EChar.add(ret);
    }
    @Override
    public String toString() {
        String temp = getClass() + ":";
        String[] split = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + name;
        temp += ", skills=";//skills.keySet();
        for(String key : skills.keySet())
            temp += key + "/";
        temp += ";\n";
        return temp;
    }
    public String toRequest() {
        return toString().replaceAll(":", "#").replaceAll("=", "->");
    }
    
    @Override
    public EChar clone() throws CloneNotSupportedException {
        EChar temp = new EChar();
        temp.setName(this.name);
        temp.skills.putAll(this.skills);
        return temp;
    }
    
}