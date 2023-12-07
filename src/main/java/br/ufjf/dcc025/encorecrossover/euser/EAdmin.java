package br.ufjf.dcc025.encorecrossover.euser;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.eskill.ESkill;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno dos Santos Silva - 201935031
 */
public class EAdmin extends EUser {
    //attributes

    //constructor
    private EAdmin(String name, String password) {
        super(name, password);
    }

    //methods
    static EUser initAdmin() {
        if (EUser.get("admin") == null) {
            EUser.add(new EAdmin("admin", "admin"));
        }
        return EUser.get("admin");
    }

    @Override
    public void confirmRequest(int index) {
        String[] request = this.getHistory(index).split("\n");
        if (request[0].contains("Create") || request[0].contains("Update")) {
            for (int i = 1; i < request.length; i++) {
                if (request[i].contains("ESkill")) {
                    ESkill.toESkill(request[i]);
                }
                if (request[i].contains("EChar")) {
                    EChar.toEChar(request[i]);
                }
            }
            for(String un : EUser.getUserList()){
                if(EUser.get(un).getFavList().contains(request[2])){
                    String[] notification = {"Character Updated by admin", "Character Name: " + request[2]};
                    EUser.get(un).addHistory(notification);
                }
            }
            JOptionPane.showMessageDialog(null, "Success.", "Confirmation", 1);
        }
        if (request[0].contains("Remove")) {
            EChar.remove(this, request[2]);
            for(String un : EUser.getUserList()){
                if(EUser.get(un).getFavList().contains(request[2])){
                    EUser.get(un).removeFavChar(request[2]);
                    String[] notification = {"Character Removed by admin", "Character Name: " + request[2]};
                    EUser.get(un).addHistory(notification);
                }
            }
            JOptionPane.showMessageDialog(null, "Success", "Confirmation", 1);
        }
        ESkill.demlurb();
        this.removeHistory(index);
    }

    public static void reportError(String error) {
        String[] request = new String[2];
        request[0] = "Error";
        request[1] = error;
        EUser.get("admin").sendRequest(request);
    }

}
