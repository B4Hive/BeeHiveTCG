package br.ufjf.dcc025.encorecrossover.euser;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import java.util.List;

/**
 *
 * @author b4bru
 */
public class EAdmin extends EUser {
    //attributes
    
    //constructor
    private EAdmin(String name, String password) {
        super(name, password);
    }
    
    //methods
    static EUser initAdmin(){
        if(EUser.get("admin") == null){
            EUser.add(new EAdmin("admin", "admin"));
            for(String key : EChar.getCharList()){
                EUser.get("admin").addFavChar(EChar.get(key));
            }
        }
        return EUser.get("admin");
    }
    
    @Override
    public List<String> listOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public <T> void selectOption(String option, T obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    void sendRequest(String request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
