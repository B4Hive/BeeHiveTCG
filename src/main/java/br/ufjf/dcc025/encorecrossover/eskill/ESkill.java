package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public abstract class ESkill {
    
    private static final Map<String,ESkill> bank = new HashMap<>();
    
    //attributes
    private final String name;
    private final int value;
    private final int cooldown;
    private int timer;
    
    //constructor
    public ESkill(String name, int value, int cooldown) {
        this.name = name;
        this.value = value;
        this.cooldown = cooldown;
        this.timer = 0;
    }
    
    //getters
    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public int getCooldown() {
        return cooldown;
    }
    public abstract String getExtra();
    
    //methods
    public static void init(){
    }
    public static boolean exists(String skill){
        return bank.containsKey(skill);
    }
    public static String export(){
        String string = "";
        for(String key : bank.keySet()){
            string += bank.get(key).toString();
        }
        return string;
    }
    
    static void add(ESkill object){
        bank.put(object.getName(), object);
    }
    public static ESkill get(String key){
        return bank.get(key);//.clone();
    }
    public static void demlurb(){
        List<String> trashList = new ArrayList<>(bank.keySet());
        for(String character : EChar.getCharList()){
            for(String skill : EChar.get(character).getFullSkillList()){
                trashList.remove(skill);
            }
        }
        for(String trash : trashList){
            bank.remove(trash);
        }
    }
    
    public void startCooldown(){
        timer = cooldown;
    }
    public boolean isOnCooldown(){
        return timer > 0;
    }
    public void tickCooldown(){
        if(timer > 0)
            timer--;
    }
    
    public int calcValue(int value){
        int calc = 0;
        calc += (int) Math.round(Math.random()*value);
        if(calc == value)
            calc++;
        return calc;
    }
    
    public abstract String getDescription();
    public abstract String cast(EChar target, Map<String,EEffect> effects);
    
    public static void toESkill(String info){
        ESkill ret = null;
        info = info.replaceAll("#", ":").replaceAll("->", "=").replace(";", "");
        String[] type = info.split(":");
        switch(type[0]){
            case "ESkillDMG" -> ret = ESkillDMG.toESkillDMG(type[1]);
            case "ESkillHeal" -> ret = ESkillHeal.toESkillHeal(type[1]);
            case "ESkillEffect" -> ret = ESkillEffect.toESkillEffect(type[1]);
        }
        ESkill.add(ret);
    }
    @Override
    public String toString() {
        String temp = getClass() + ":";
        String[] split = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + name;
        temp += ", value=" + value;
        temp += ", cooldown=" + cooldown;
        temp += ";\n";
        return  temp;
    }
    
    public String toRequest(){
        return toString().replaceAll(":", "#").replaceAll("=", "->");
    }
    
}