package br.ufjf.dcc025.encorecrossover.euser;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import javax.swing.JOptionPane;

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
                EUser.get("admin").addFavChar(key);
            }
        }
        return EUser.get("admin");
    }
    @Override
    public void confirmRequest(String request) {
        JOptionPane.showMessageDialog(null, "NYI EAdmin.confirmRequest()", "NYI", 0);
    }
    public static void reportError(String error){
        String request[] = new String [2];
        request[0] = "Error";
        request[1] = error;
        EUser.get("admin").sendRequest(request);
    }
    
}
