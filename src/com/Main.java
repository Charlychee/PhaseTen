package com;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final String CMDPATTERNS = "\\?|(start-game( -h [1-8])?( -cpu [1-8])?)|(hand( -all)?)|(end)|(draw " +
            "(deck|discard))|(discard ([RrGgBbYy](1[0-2]|[1-9]))|[WwSs])";
    private static final String ALLCOMMANDS = "\\?|start-game|hand|end|draw|discard";
    private static Game CURRENTGAME;
    private static Player CURRENTPLAYER;
    public static void main(String[] args) {
        System.out.println("Welcome to PhaseTen Command Line Testing. Press ? for a list of commands.");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = in.nextLine().trim();
            if (line.matches(CMDPATTERNS)) {
                String[] cmdArr = line.split("\\s");
                String command = cmdArr[0];
                process(command, getOperands(cmdArr));
            } else {
                System.out.println("Not a valid command. Press ? for a list of valid commands.");
            }
            if (CURRENTGAME != null) {
                CURRENTPLAYER = CURRENTGAME.getCurrentPlayer();
                System.out.println("Player " + CURRENTPLAYER.getID() + "'s turn. Top of discard pile is: "
                        + CURRENTGAME.getDiscardTop());
            }
        }
    }

    public static String[] getOperands(String[] args) {
        String[] operands = new String[args.length - 1];
        System.arraycopy(args, 1, operands, 0, operands.length);
        return operands;
    }

    public static void process(String command, String[] args) {
        if (command.matches(ALLCOMMANDS)) {
            if (command.equals("?") && args.length == 0) {
                printResource("com/testcommands.txt");
                return;
            } else if (command.equals("start-game")) {
                if (CURRENTGAME != null) {
                    System.out.println("Another game is already in progress.");
                } else if (args.length == 2) {
                    if (args[0].equals("-h")) {
                        System.out.println("Creating a game with " + args[1] + " human players.");
                        CURRENTGAME = new Game(Integer.parseInt(args[1]), 0);
                    } else {
                        System.out.println("Creating a game with " + args[1] + " CPUs.");
                        CURRENTGAME = new Game(0, Integer.parseInt(args[1]));
                    }
                    CURRENTGAME.startGame();
                } else if (args.length == 4) {
                    System.out.println("Creating a game with " + args[1] + " human players and " + args[3] + " CPUS.");
                    CURRENTGAME = new Game(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
                    CURRENTGAME.startGame();
                } else {
                    System.out.println("Creating a game with 1 human player.");
                    CURRENTGAME = new Game(1, 0);
                    CURRENTGAME.startGame();
                }
            } else if (command.equals("hand")) {
                if (args.length == 0) {
                    System.out.println(CURRENTPLAYER.getHand());
                } else {
                    for (Player p : CURRENTGAME.getPlayers()) {
                        System.out.println("Player " + p.getID());
                        System.out.println(p.getHand());
                    }
                }
            } else if (command.equals("end")) {
                CURRENTGAME = null;
            } else {
                // These methods may cause PTExceptions and are commands playing the game.
                try {
                    if (command.equals("draw")) {
                        Card drawn;
                        if (args[0].equals("deck")) {
                            drawn = CURRENTPLAYER.draw(CURRENTGAME.getDeck());
                        } else {
                            drawn = CURRENTPLAYER.draw(CURRENTGAME.getDiscard());
                        }
                        System.out.println("Player drew a card: " + drawn);
                    }
                } catch (PTException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("Please enter a valid command. Press ? for a list of commands.");
        }
    }

    /** Print the contents of the resource named NAME on the standard error. */
    static void printResource(String name) {
        try {
            InputStream resourceStream =
                    Main.class.getClassLoader().getResourceAsStream(name);
            BufferedReader str =
                    new BufferedReader(new InputStreamReader(resourceStream));
            for (String s = str.readLine(); s != null; s = str.readLine())  {
                System.err.println(s);
            }
            str.close();
        } catch (IOException excp) {
            System.out.println("No help found.");
        }
    }
}
