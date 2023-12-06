package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class ESkillHeal extends ESkill {
    //constructor
    ESkillHeal(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown) {
        ESkill.add(new ESkillHeal(name, value, cooldown));
        return ESkill.get(name);
    }
    
    //methods
    @Override
    public String getDescription() {
        String string = getName() + ": ";
        string += "Heals 0~" + getValue() + " units of damage. ";
        string += "Cooldown " + getCooldown() + " turns. ";
        return string;
    }
    @Override
    public String cast(EChar target, Map<String,EEffect> effects){
        if(!isOnCooldown()){
            int value = calcValue(getValue());
            target.heal(value);
            startCooldown();
            return target.getName() + " heals " + value + " points.";
        }
        return "Skill on Cooldown";
    }
    
    static ESkill toESkillHeal(String info){
        String n = "";
        int v = 0;
        int c = 0;
        String attributeSplit[] = info.split(", ");
        for(String attribute : attributeSplit){
            String temp[] = attribute.split("=");
            switch(temp[0]){
                case "name" -> {
                    n = temp[1];
                }
                case "value" -> {
                    v = Integer.parseInt(temp[1]);
                }
                case "cooldown" -> {
                    c = Integer.parseInt(temp[1]);
                }
            }
        }
        return new ESkillHeal(n, v, c);
    }

    @Override
    public String getExtra() {
        return null;
    }
}
