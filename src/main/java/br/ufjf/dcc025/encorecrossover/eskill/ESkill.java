package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public abstract class ESkill {
    
    private static final Map<String,ESkill> skills = new HashMap<>();
    
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
    public int getTimer() {
        return timer;
    }
    
    //methods
    public static void init(){
        skills.put("BATK", ESkillDMG.create("BATK", 3, 0));
        skills.put("BHEAL", ESkillHeal.create("BHEAL", 3, 2));
        skills.put("BDOT", ESkillEffect.create("BDOT", 1, 3, "DOT", 2));
        skills.put("BHOT", ESkillEffect.create("BHOT", 1, 3, "HOT", 2));
        skills.put("BBUFF", ESkillEffect.create("BBUFF", 1, 3, "DMG", 2));
    }
    //import
    public static String export(){
        String string = "";
        for(String key : skills.keySet()){
            string += skills.get(key).toString();
        }
        return string;
    }
    
    static void add(String key, ESkill object){
        skills.put(key, object);
    }
    public static ESkill get(String key){
        return skills.get(key);
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
        calc += Math.round(Math.random()*value);
        if(calc == value)
            calc++;
        return calc;
    }
    
    public abstract String getDescription();
    public abstract String cast(EChar target, Map<String,EEffect> effects);

    @Override
    public String toString() {
        String temp = getClass() + ":";
        String split[] = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + name;
        temp += ", value=" + value;
        temp += ", cooldown=" + cooldown;
        temp += ";\n";
        return  temp;
    }
    
}
/*
Attributes{
    String name;
    int value;
    int cooldown;
}
Methods{
    cast();
    calcValue();
    startCooldown();
}
Subclasses{
    Damage -> deals damage;
    Sustain -> heals character;
    Effect -> {
        has attribute: String effect, int duration;
    }
    Mixed -> ...;
}
*/