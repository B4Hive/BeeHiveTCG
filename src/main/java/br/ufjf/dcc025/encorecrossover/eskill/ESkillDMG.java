package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class ESkillDMG extends ESkill {
    //constructor
    private ESkillDMG(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown){
        ESkill.add(name, new ESkillDMG(name, value, cooldown));
        return ESkill.get(name);
    }
    //methods
    @Override
    public String getDescription() {
        String string = getName() + ": ";
        string += "Deals 0~" + getValue() + " units of damage. ";
        string += "Cooldown " + getCooldown() + " turns. ";
        return string;
    }
    @Override
    public String cast(EChar target, Map<String,EEffect> effects) {
        if(!isOnCooldown()){
            int value = calcValue(getValue());
            value += dmgBonus(effects);
            target.takeDMG(value);
            startCooldown();
            return target.getName() + " takes " + value + " damage.";
        }
        return "Skill on Cooldown";
    }
    private int dmgBonus(Map<String,EEffect> effects){
        int bonus = 0;
        for(String key : effects.keySet()){
            if( effects.get(key).getType().equals("DMG") ){
                bonus += Math.round( Math.random() * effects.get(key).getValue());
            }
        }
        return bonus;
    }
    
}
