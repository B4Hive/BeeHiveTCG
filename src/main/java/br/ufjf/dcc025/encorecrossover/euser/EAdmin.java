package br.ufjf.dcc025.encorecrossover.euser;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.eskill.ESkill;
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
    static EUser initAdmin() {
        if (EUser.get("admin") == null) {
            EUser.add(new EAdmin("admin", "admin"));
        }
        return EUser.get("admin");
    }

    @Override
    public void confirmRequest(int index) {
        String request[] = this.getHistory(index).split("\n");
        if (request[0].contains("Create")) {
            for (int i = 1; i < request.length; i++) {
                if (request[i].contains("ESkill")) {
                    ESkill.toESkill(request[i]);
                } else if (request[i].contains("EChar")) {
                    EChar.toEChar(request[i]);
                }
            }
            JOptionPane.showMessageDialog(null, "Success.", "Confirmation", 1);
        }
        if (request[0].contains("Update")) {
            JOptionPane.showMessageDialog(null, "NYI EAdmin.confirmRequest()", "NYI", 0);
        }
        if (request[0].contains("Remove")) {
            JOptionPane.showMessageDialog(null, "NYI EAdmin.confirmRequest()", "NYI", 0);
        }
        this.removeHistory(index);
    }

    public static void reportError(String error) {
        String request[] = new String[2];
        request[0] = "Error";
        request[1] = error;
        EUser.get("admin").sendRequest(request);
    }

}
