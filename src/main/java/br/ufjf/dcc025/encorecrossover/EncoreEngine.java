package br.ufjf.dcc025.encorecrossover;

import br.ufjf.dcc025.encorecrossover.echar.EChar;
import br.ufjf.dcc025.encorecrossover.euser.EUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author b4bru
 */
public class EncoreEngine {
    //attributes
    private static EUser user = null;
    private static final Map<String,EChar> characters = new HashMap<>();
    private static final List<String> history = new ArrayList<>();
    //methods
    public static void newGame(){
        Scanner teclado = new Scanner(System.in);
        String opt;
        String tar;
        
        EChar pc = EChar.createTemplate("PC");
        characters.put("PC", pc);
        pc.initChar(1);
        
        EChar npc = EChar.createTemplate("NPC");
        characters.put("NPC", npc);
        npc.initChar(2);
        
        System.out.println("*Start*");
        do{
            String hist = "";
            for(String key : characters.keySet()){
                hist += characters.get(key).updateEffects();
            }
            System.out.println("+-*-+-*-+-*-+\nEnemy: " + npc.getInfo());
            System.out.println("+-*-+-*-+-*-+\nPlayer: " + pc.getInfo());
            System.out.print("+-*-+-*-+-*-+\nType a skill: ");
            opt = teclado.nextLine().toUpperCase();
            if(opt.equals("QUIT"))
                break;
            System.out.println(pc.getSkillInfo(opt));
            System.out.print("Type a target: ");
            tar = teclado.nextLine().toUpperCase();
            while(!characters.containsKey(tar))
                tar = teclado.nextLine().toUpperCase();
            hist += pc.cast(opt, characters.get(tar));
            //IA
            System.out.println("\n#############");
            System.out.println(hist);
            System.out.println("#############");
            history.add(hist);
        }while(pc.getHP() > 0 && npc.getHP() > 0);
        if(pc.getHP() > 0)
            history.add("You Win");
        else
            history.add("You Lose");
        characters.clear();
        for(String hist : history)
            System.out.println(hist);
    }
    public static void menu(){
        Scanner teclado = new Scanner(System.in);
        while(user == null){
            System.out.print("Login\nInsert username: ");
            String name = teclado.nextLine();
            System.out.print("Insert password: ");
            String password = teclado.nextLine();
            user = EUser.login(name, password);
            if(user == null)
                System.out.println("Login failed");
        }
        int opt;
        do{
            System.out.println(user.getName() + " logged");
            System.out.println("Options:");
            System.out.println("[1] New Game");
            System.out.println("[2] ");
            opt = teclado.nextInt();
        }while(opt != 0);
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