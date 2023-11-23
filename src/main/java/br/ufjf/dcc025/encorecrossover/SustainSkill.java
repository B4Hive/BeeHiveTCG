/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

/**
 *
 * @author b4bru
 */
public class SustainSkill extends ESkill {
    //constructor
    private SustainSkill(String name, int value, int cooldown) {
        super(name, value, cooldown);
    }
    public static ESkill create(String name, int value, int cooldown) {
        EData.skills.put(name, new SustainSkill(name, value, cooldown));
        return EData.skills.get(name);
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
    public String cast(EChar target){
        if(checkCooldown()){
            int value = calcValue(getValue());
            target.heal(value);
            startCooldown();
            return target.getName() + " heals " + value + " points.";
        }
        return "Skill on Cooldown";
    }
}
