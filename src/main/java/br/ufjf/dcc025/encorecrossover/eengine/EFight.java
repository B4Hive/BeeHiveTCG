package br.ufjf.dcc025.encorecrossover.eengine;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.euser.EUser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b4bru
 */
public class EFight {
    //attributes
    private final EUser user;
    private final EChar char1;
    private final EChar char2;
    private final List<String> history;
    //constructor
    private EFight(EUser user, EChar char1, EChar char2) {
        this.user = user;
        this.char1 = char1;
        this.char2 = char2;
        this.history = new ArrayList<>();
    }
    public static EFight pve(EUser user, String char1){
        EChar temp = EChar.get(char1);
        List<String> characters = new ArrayList<>();
        characters.addAll(EChar.getCharList());
        characters.remove(temp.getName());
        int rand = (int) (Math.random() * characters.size());
        EChar temp2 = EChar.get(characters.get(rand));
        return new EFight(user, temp, temp2);
    }
    //getters
    public String getChar1(){
        return char1.getInfo();
    }
    public String getChar2(){
        return char2.getInfo();
    }
    public List<String> getActionList(){
        return char1.getSkillList();
    }
    public String getLastTurn(){
        return history.get( history.size() - 1 );
    }
    //methods
    public void start(){
        char1.initChar(1);
        char2.initChar(2);
    }
    public String turn(String target, String action){
        String hist = "";
        hist += char1.updateEffects();
        hist += char2.updateEffects();
        if(target.equals("Self"))
            hist += char1.cast(action, char1);
        else
            hist += char1.cast(action, char2);
        hist += IA();
        history.add(hist);
        return hist;
    }
    public String IA(){
        //
        return "";
    }
    public boolean end(){
        if(char1.getHP() <= 0 || char2.getHP() <= 0){
            history.add("Fight ended.");
            if(char1.getHP() > char2.getHP())
                history.add(0, "Victory");
            else
                history.add(0,"Defeat");
            String result[] = new String[history.size()];
            for(int i = 0; i < history.size(); i++)
                result[i] = history.get(i);
            user.addHistory(result);
            return true;
        }
        return false;
    }
}
/*
Attributes{
    List characters;
    List history;
}
Methods{
    newNormalGame();
    newBattleRoyale();
    tempScreen(); //CLI
}
*/