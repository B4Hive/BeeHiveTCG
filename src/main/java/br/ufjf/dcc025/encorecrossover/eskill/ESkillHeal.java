package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class ESkillHeal extends ESkill {
    //constructor
    private ESkillHeal(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown) {
        ESkill.add(name, new ESkillHeal(name, value, cooldown));
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
}
