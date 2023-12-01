package br.ufjf.dcc025.encorecrossover.eskill;


import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author b4bru
 */
public class ESkillEffect extends ESkill {
    //attributes
    private EEffect effect;
    private int duration;
    
    //constructor
    private ESkillEffect(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown, String type, int duration) {
        ESkillEffect temp = new ESkillEffect(name, value, cooldown);
        temp.effect = EEffect.create(name, type, value);
        temp.duration = duration;
        ESkill.add(name, temp);
        return ESkill.get(name);
    }
    
    public String getEffectName(){
        return effect.getName();
    }
    //methods
    @Override
    public String getDescription() {
        String string = getName() + ": ";
        string += "Apply " + getEffectName() + " to the target. ";
        if(duration > 0)
            string += "Duration " + duration + " turns. ";
        else
            string += "Permanent. ";
        string += "Cooldown " + getCooldown() + " turns. ";
        string += effect.getDescription();
        return string;
    }
    @Override
    public String cast(EChar target, Map<String,EEffect> effects) {
        if(!isOnCooldown()){
            target.applyEffect(effect.apply(duration));
            startCooldown();
            return target.getName() + " receives " + effect.getName() + " effect.";
        }
        return "Skill on Cooldown";
    }
    @Override
    public String toString(){
        String temp = getClass() + ":";
        String split[] = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + getName();
        temp += ", value=" + getValue();
        temp += ", cooldown=" + getCooldown();
        temp += ", effect=" + effect.getType();
        temp += ", duration=" + duration;
        temp += ";\n";
        return  temp;
    }
    
}
