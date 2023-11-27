package br.ufjf.dcc025.encorecrossover;

import br.ufjf.dcc025.encorecrossover.edata.EData;

/**
 *
 * @author b4bru
 */
public class EncoreCrossover {

    public static void main(String[] args) {
        EData.initBaseData();
        EncoreEngine.newGame();
        EData.exportData();
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