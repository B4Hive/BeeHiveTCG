package br.ufjf.dcc025.encorecrossover.euser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b4bru
 */
public class EPlayer extends EUser {
    //attributes
    
    //constructor
    private EPlayer(String name, String password) {
        super(name, password);
    }
    public static EUser create(String name, String password) {
        if(EUser.get(name) != null)
            return null;
        EPlayer temp = new EPlayer(name, password);
        EUser.add(temp);
        return EUser.get(name);
    }
    
    //methods
    
    @Override
    public List<String> listOptions() {
        List<String> options = new ArrayList<>();
        options.add("Add Favorite Character");
        options.add("List Favorite Characters");
        
        return options;
    }
    @Override
    public <T> void selectOption(String option, T obj) {
        switch(option){
            case "List Favorite Characters" -> {
                
            }
        }
    }
    @Override
    void sendRequest(String request) {
        EUser.get("admin").addHistory(request);
    }
}
