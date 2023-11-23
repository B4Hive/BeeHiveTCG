/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

/**
 *
 * @author b4bru
 */
public abstract class ESkill {
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
    public void startCooldown(){
        timer = cooldown;
    }
    public boolean checkCooldown(){
        if(timer > 0){
            timer--;
            return false;
        }
        return true;
    }
    public int calcValue(int value){
        int calc = 0;
        for(int i = 0; i < value; i++)
            calc += Math.round(Math.random());
        if(calc == value)
            calc++;
        return calc;
    }
    
    public abstract String getDescription();
    public abstract String cast(EChar target);

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