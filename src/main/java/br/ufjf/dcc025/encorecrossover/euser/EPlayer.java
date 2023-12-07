package br.ufjf.dcc025.encorecrossover.euser;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
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
    public void confirmRequest(int index) {
        //
    }
    
}
