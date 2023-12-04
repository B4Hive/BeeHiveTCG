package br.ufjf.dcc025.encorecrossover.euser;

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
    public void confirmRequest(String request) {
        
        String req[] = new String [2];
        req[0] = "Request";
        req[1] = request;
        EUser.get("admin").sendRequest(req);
        
    }
    
}
