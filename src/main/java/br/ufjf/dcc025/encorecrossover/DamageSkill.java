/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

/**
 *
 * @author b4bru
 */
public class DamageSkill extends ESkill {
    //constructor
    private DamageSkill(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown){
        EData.skills.put(name, new DamageSkill(name, value, cooldown));
        return EData.skills.get(name);
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
    public String cast(EChar target) {
        if(checkCooldown()){
            int value = calcValue(getValue());
            value += dmgBonus();
            target.takeDMG(value);
            startCooldown();
            return target.getName() + " takes " + value + " damage.";
        }
        return "Skill on Cooldown";
    }
    private int dmgBonus(){
        int bonus = 0;
        //verificar effect +dmg
        return bonus;
    }
    
}
