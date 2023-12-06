package br.ufjf.dcc025.encorecrossover.eskill;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class ESkillDMG extends ESkill {
    //constructor
    ESkillDMG(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown){
        ESkill.add(new ESkillDMG(name, value, cooldown));
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
            int bonus = dmgBonus(effects);
            target.takeDMG(value+bonus);
            startCooldown();
            String sum = target.getName() + " takes " + value;
            if(bonus > 0)
                sum += " + " + bonus;
            sum += " damage.";
            return sum;
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
    static ESkill toESkillDMG(String info){
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
        return new ESkillDMG(n, v, c);
    }

    @Override
    public String getExtra() {
        return null;
    }
}
