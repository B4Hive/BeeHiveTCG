/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufjf.dcc025.encorecrossover;

/**
 *
 * @author b4bru
 */
public class EncoreCrossover {

    public static void main(String[] args) {
        EData.initBaseData();
        EGameplay.newGame();
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