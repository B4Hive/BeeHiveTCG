/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.encorecrossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author b4bru
 */
public class EGameplay {
    //attributes
    private static final Map<String,EChar> characters = new HashMap<>();
    private static final List<String> history = new ArrayList<>();
    //methods
    public static void newGame(){
        Scanner teclado = new Scanner(System.in);
        String opt;
        String tar;
        characters.put("PC", EChar.createTemplate("PC"));
        characters.get("PC").initChar(1);
        characters.put("NPC", EChar.createTemplate("NPC"));
        characters.get("NPC").initChar(2);
        do{
            //update ticks
            System.out.println("+-*-+-*-+-*-+\nEnemy: " + characters.get("NPC").getInfo());
            System.out.println("+-*-+-*-+-*-+\nPlayer: " + characters.get("PC").getInfo());
            System.out.print("+-*-+-*-+-*-+\nType a skill: ");
            opt = teclado.nextLine();
            if(opt.equals("quit"))
                break;
            System.out.println(characters.get("PC").getSkillInfo(opt));
            System.out.print("Type a target: ");
            tar = teclado.nextLine();
            while(!characters.containsKey(tar))
                tar = teclado.nextLine();
            //IA
            System.out.println("\n#############");
            System.out.println(characters.get("PC").cast(opt, characters.get(tar)));
            System.out.println("\n#############");
        }while(characters.get("PC").getHP() > 0 && characters.get("NPC").getHP() > 0);
        characters.clear();
    }
}
/*
Attributes{
    List characters;
    List history;
}
Methods{
    newNormalGame();
    newBattleRoyale();
    tempScreen(); //CLI
}
*/