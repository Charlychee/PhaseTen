package com;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /** Name of help text resource. */
    private static final String HELP_FILE = "com/testcommands.txt";

    /** Mapping of strings to Card.typeEnum. */
    private static final Map<String, Card.typeEnum> CARD_TYPES = new HashMap<>() {
        {
            put("red", Card.typeEnum.RED);
            put("blue", Card.typeEnum.BLUE);
            put("yellow", Card.typeEnum.YELLOW);
            put("green", Card.typeEnum.GREEN);
            put("wild", Card.typeEnum.WILD);
            put("skip", Card.typeEnum.SKIP);
        }
    };

    /** Mapping of abbreviations to Card.typeEnum. */
    private static final Map<String, Card.typeEnum> CARD_ABBREV = new HashMap<>() {
        {
            put("r", Card.typeEnum.RED);
            put("b", Card.typeEnum.BLUE);
            put("y", Card.typeEnum.YELLOW);
            put("g", Card.typeEnum.GREEN);
            put("w", Card.typeEnum.WILD);
        }
    };

    /** Describes a command with up to four arguments. */
    private static final Pattern CMD_PATN = Pattern.compile("(\\S+)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*).*");

    /** Describes a command in Phase Creation mode (allows up to 12 cards to be added). */
    private static final Pattern PHASE_CMD = Pattern.compile("(done|run|num|evenodd|color|create)\\s*(-?\\S*)\\s*(-?\\S*)" +
            "\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)\\s*(-?\\S*)" +
            "\\s*(-?\\S*)\\s*(-?\\S*).*");

    /** Describes a card pattern. */
    private static final Pattern CARD_PATN = Pattern.compile("([rbgyw])(1[0-2]|[1-9])?");

    /** String format for starting a game. */
    private static final String START_GAME = "Starting a game with %s human players and %s cpus.";

    /** String format for turn prompts. */
    private static final String TURN_PROMPT = "Player %s's turn. Top of the discard pile: %s";

    /** String format for drawing a card. */
    private static final String DRAW_CARD = "Player drew a %s from the %s.";

    /** String format for discarding a card. */
    private static final String DISCARD = "Player %s discarded a card[%s] and ended their turn.";

    /** Determines if a game is being played. */
    private static boolean PLAYING = false;

    /** Determines if the current player is creating a phase. */
    private static boolean CREATING_PHASE = false;

    /** The current game being played. */
    private static Game GAME;

    /** The current player. */
    private static Player PLAYER;

    /** Specifies whether the next line should prompt with player's name/id. */
    private static boolean NEXTPROMPT;

    /** Input source. */
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to PhaseTen Command Line Testing. Press ? for a list of commands.");
        String next;
        while (true) {
            next = readLine(NEXTPROMPT);
            NEXTPROMPT = false;
            if (PLAYING) {
                if (CREATING_PHASE) {
                    processPhaseCreation(next);
                } else {
                    processCommands(next);
                }
            } else {
                processStartGame(next);
                NEXTPROMPT = true;
            }
        }
    }

    /** Returns a command from the standard input after prompting the player's name if PROMPT. */
    public static String readLine(boolean prompt) {
        prompt(prompt);
        if (IN.hasNextLine()) {
            return IN.nextLine().trim();
        }
        return null;
    }

    /** Print a prompt. Prints the player's name if PLAYER */
    private static void prompt(boolean player) {
        if (PLAYING && player) {
            System.out.println(String.format(TURN_PROMPT, PLAYER.getID(), GAME.getDiscardTop()));
        }
        System.out.print("> ");
    }

    // TODO: Fix up startgame case to account for all possible exceptions.
    /** Processes only start-game commands. */
    private static void processStartGame(String line) {
        line = line.trim();
        if (line.length() == 0) {
            return;
        }
        Matcher command = CMD_PATN.matcher(line);
        if (command.matches()) {
            switch (command.group(1).toLowerCase()) {
                case "?":
                    printResource(HELP_FILE);
                    break;
                case "startgame":
                    String tag1 = command.group(2);
                    String tag2 = command.group(4);
                    int humans = 0;
                    int cpus = 0;

                    if (tag1.equals("")) {
                        humans = 1;
                    } else {
                        try {
                            if (tag1.equals("-h")) {
                                humans = Integer.parseInt(command.group(3));
                            } else if (tag1.equals("-cpu")) {
                                cpus = Integer.parseInt(command.group(3));
                            }
                            if (tag2.equals("-h")) {
                                humans = Integer.parseInt(command.group(5));
                            } else if (tag2.equals("-cpu")) {
                                cpus = Integer.parseInt(command.group(5));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Improper number arguments provided.");
                        }
                    }
                    GAME = new Game(humans, cpus);
                    PLAYING = true;
                    GAME.startGame();
                    PLAYER = GAME.getCurrentPlayer();
                    System.out.println(String.format(START_GAME, humans, cpus));
                    break;
                default:
                    System.out.println("Unable to process the command.");
            }
        }
    }

    /** Processes in-game commands. */
    private static void processCommands(String line) {
        line = line.trim();
        String arg1;
        String arg2;
        if (line.length() == 0) {
            return;
        }
        Matcher command = CMD_PATN.matcher(line);
        if (command.matches()) {
            switch (command.group(1).toLowerCase()) {
                case "startgame":
                    System.out.println("A game is already in session. Please end the current game before trying again.");
                    break;
                case "hand":
                    arg1 = command.group(2);
                    if (arg1.equals("")) {
                        System.out.println(PLAYER.getHandDescription());
                        NEXTPROMPT = true;
                        break;
                    } else if (arg1.equals("-all")) {
                        for (Player p : GAME.getPlayers()) {
                            System.out.println("Player " + p.getID());
                            System.out.println(p.getHandDescription());
                        }
                        NEXTPROMPT = true;
                        break;
                    }
                case "draw":
                    try {
                        arg1 = command.group(2);
                        if (arg1.equals("deck") || arg1.equals("discard")) {
                            Card drawn;
                            String pile = "";
                            if (arg1.equals("deck")) {
                                drawn = PLAYER.draw(GAME.getDeck());
                                pile = "deck";
                            } else {
                                drawn = PLAYER.draw(GAME.getDiscard());
                                pile = "discard pile";
                            }
                            System.out.println(String.format(DRAW_CARD, drawn, pile));
                        } else {
                            System.out.println("Invalid operands.");
                        }
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "discard":
                    try {
                        String card;
                        arg1 = command.group(2).toLowerCase();
                        arg2 = command.group(3);
                        Card.typeEnum type = CARD_TYPES.get(arg1);
                        if (type == null) {
                            System.out.println("Improper card type given.");
                            break;
                        }
                        int value = -1;
                        if (!arg2.equals("")) {
                            value = Integer.parseInt(arg2);
                            card = String.format("Color: %s, Value %s", arg1, value);
                        } else {
                            card = "Type: " + arg1;
                        }
                        PLAYER.discard(type, value, GAME.getDiscard());
                        System.out.println(String.format(DISCARD, PLAYER.getID(), card));
                        PLAYER = GAME.switchTurn();
                        NEXTPROMPT = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Improper card value given.");
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "phase":
                    arg1 = command.group(2);
                    if (arg1.equals("")) {
                        System.out.println(PLAYER.getCurrentPhase());
                        NEXTPROMPT = true;
                    } else if (arg1.equals("-all")) {
                        for (Player p: GAME.getPlayers()) {
                            System.out.println("Player " + p.getID() + ": " + p.getPhaseSummary());
                        }
                        NEXTPROMPT = true;
                    }
                    break;
                case "createphase":
                    if (PLAYER.getCompletedPhase()) {
                        System.out.println("The phase is already completed.");
                    } else {
                        CREATING_PHASE = true;
                        System.out.println("Entered phase creation.");
                    }
                    break;
                case "end":
                    GAME = null;
                    PLAYING = false;
                    System.out.println("Successfully ended the game.");
                    break;
                default:
                    System.out.println("Invalid command or operands. Press ? for a list of valid commands and arguments.");
            }
        }
    }

    /** Process and create a card from the given command matcher. */
    private static void createCards(Matcher command, LinkedList<Card> cards) {
        String card;
        Matcher cardMatch;
        Card.typeEnum type;
        int value;
        for (int i = 2; i < 14; i += 1) {
            card = command.group(i);
            if (card.equals("")) {
                break;
            }
            cardMatch = CARD_PATN.matcher(card);
            if (cardMatch.matches()) {
                type = CARD_ABBREV.get(cardMatch.group(1));
                if (type == Card.typeEnum.WILD) {
                    cards.add(new Card(type));
                } else {
                    try {
                        value = Integer.parseInt(cardMatch.group(2));
                        cards.add(new Card(type, value));
                    } catch (NumberFormatException e) {
                        System.out.println("Improper arguments provided for card value.");
                    }
                }
            }  else {
                System.out.println("Problem with arguments.");
                break;
            }
        }
    }

    /** Processes phase creation commands. */
    private static void processPhaseCreation(String line) {
        line = line.trim();
        if (line.length() == 0) {
            return;
        }
        Matcher command = PHASE_CMD.matcher(line);
        if (command.matches()) {
            LinkedList<Card> cards = new LinkedList<>();
            switch(command.group(1)) {
                case "done":
                    System.out.println("Successfully quit out of phase creator.");
                    CREATING_PHASE = false;
                    break;
                case "create":
                    try {
                        PLAYER.createCurrentPhase();
                        System.out.println("Successfully completed the phase.");
                        System.out.println("Exited phase creation.");
                        CREATING_PHASE = false;
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "num":
                    try {
                        createCards(command, cards);
                        PLAYER.createPlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, cards);
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Successfully created the number set.");
                    break;
                case "run":
                    try {
                        createCards(command, cards);
                        PLAYER.createPlayerCardPile(PlayerCardPile.typeEnum.RUN, cards);
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Successfully created the run.");
                    break;
                case "evenodd":
                    try {
                        createCards(command, cards);
                        PLAYER.createPlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, cards);
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Successfully created the even/odd set.");
                    break;
                case "color":
                    try {
                        createCards(command, cards);
                        PLAYER.createPlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, cards);
                    } catch (PTException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Successfully created the color set.");
                    break;
            }
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
