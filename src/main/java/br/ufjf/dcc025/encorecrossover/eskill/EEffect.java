package br.ufjf.dcc025.encorecrossover.eskill;

public class EEffect {
    //attributes
    private final String name;
    private final String type;
    private final int value;
    private int timer;
    
    //constructor
    private EEffect(String name, String type, int value, int timer) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.timer = timer;
    }
    private EEffect(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.timer = 0;
    }
    public static EEffect create(String name, String type, int value){
        return new EEffect(name, type, value);
    }
    
    //getters
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public int getTimer() {
        return timer;
    }
    public String getDescription(){
        String string = getName() + ": ";
        //effect description
        return string;
    }
    //methods
    public EEffect apply(int duration){
        return new EEffect(name, type, value, duration);
    }
    public boolean tick(){
        if(timer > 0){
            timer--;
            return true;
        } 
        return (Math.round(Math.random()) == 0);
    }
}
/*
Attributes{
    String name;
    int id;
}
Methods{
    apply();
    remove();
    tick();
}
*/