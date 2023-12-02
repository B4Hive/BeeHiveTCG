package br.ufjf.dcc025.encorecrossover;

import br.ufjf.dcc025.encorecrossover.edata.EData;
import br.ufjf.dcc025.encorecrossover.eengine.EScreen;

/**
 *
 * @author b4bru
 */
public class EncoreCrossover {

    public static void main(String[] args) {
        EData.importData();
        EScreen screen = new EScreen();
        screen.setVisible(true);
    }
}
/*
Objects{
    Skills;
    Characters;
    Effects;
    Items;
    Users;
}
Subclasses{
    Skills -> Damage, Sustain, Effect, Mixed;
    Effects -> Passive, Reactive, ...;
    Users -> Players, Admin;
    Items -> Equipable, Consumable;
}
Program{
    Screen;
    Gameplay;
    Data;
}
*/