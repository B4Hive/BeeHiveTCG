package br.ufjf.dcc025.encorecrossover.eskill;


import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.Map;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class ESkillEffect extends ESkill {
    //attributes
    private EEffect effect;
    private int duration;
    
    //constructor
    ESkillEffect(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown, String type, int duration) {
        ESkillEffect temp = new ESkillEffect(name, value, cooldown);
        temp.effect = EEffect.create(name, type, value);
        temp.duration = duration;
        ESkill.add(temp);
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
        String[] split = temp.split("\\.");
        temp = split[split.length - 1];
        temp += "name=" + getName();
        temp += ", value=" + getValue();
        temp += ", cooldown=" + getCooldown();
        temp += ", effect=" + effect.getType();
        temp += ", duration=" + duration;
        temp += ";\n";
        return  temp;
    }
    
    static ESkill toESkillEffect(String info){
        String n = "";
        int v = 0;
        int c = 0;
        String e = "";
        int d = 0;
        String[] attributeSplit = info.split(", ");
        for(String attribute : attributeSplit){
            String[] temp = attribute.split("=");
            switch(temp[0]){
                case "name" -> n = temp[1];
                case "value" -> v = Integer.parseInt(temp[1]);
                case "cooldown" -> c = Integer.parseInt(temp[1]);
                case "effect" -> e = temp[1];
                case "duration" -> d = Integer.parseInt(temp[1]);
            }
        }
        ESkillEffect temp = new ESkillEffect(n,v,c);
        temp.effect = EEffect.create(n, e, v);
        temp.duration = d;
        return temp;
    }

    @Override
    public String getExtra() {
        return effect.getType() + ":" + duration;
    }
    
}
