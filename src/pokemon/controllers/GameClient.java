package pokemon.controllers;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameClient implements IGameClient {
    int score;
    String name;
    ArrayList<Player> players;
    Position pokemanPos;
    IGameServer gameServer;
    Position boardOffset;

    public static void main(String[] args) {

            // first the client as server
            GameClient obj = new GameClient();
    }

    GameClient() {
        score = 0;
        name = "";
        players = new ArrayList<Player>();
        pokemanPos = new Position(0, 0);

        try {
            // obtain a stub for the remote object from the GameServer's registry
            Registry registry = LocateRegistry.getRegistry(null);
            gameServer = (IGameServer) registry.lookup("GameServer");
            players = gameServer.getPlayers();
            setName();

            // now set this client as server, too
            IGameClient gameClient = (IGameClient) UnicastRemoteObject.exportObject(this, 0);
            Registry regClient = LocateRegistry.getRegistry();
            regClient.bind(this.name, gameClient);

            // now login in the Gameserver
            gameServer.login(this, this.name);

            // setup the game
            this.boardOffset = gameServer.getBoardOffset();
            this.pokemanPos = gameServer.getPokemanPos();
            // TODO: Here you have the offset(height,width) of the board, the players and the pokeman position -> Now you can setup the game and render it


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    void logout() {
        // TODO: just call this function when the user wants to quit the game. The server has to know that to inform the other clients that this client has logged out
        try {
            gameServer.logout(this.name);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    void setName() {
        // TODO: Here the player is asked for his name; the name should be unique and not used by another player
        // This is important because every client must also be registered as an server and for binding we need unique names
        String tempname = "";
        if(players != null && players.size() > 0) {
            for (Player player : players) {
                if (tempname.equals(player.getName())) {
                    //TODO: the entered name of the user is not unique and one player has the name -> Inform the user: He has to give a new name
                }
            }
        }
        // the name is unique so it is set to the member of this object
        this.name = tempname;

        // TODO: just a test name remove the whole lines here and return your name
        Random rn = new Random();
        int val = rn.nextInt(100);
        name = "TestUser" + val;
    }


    @Override
    public void incrementScore() {
        this.score++;
        // TODO: Here render the new score for this object
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void receiveActivePlayers(ArrayList<Player> players) {
        if(players != null && players.size() > 0) {

            for (Player player : players) {
                if (!player.getName().equals(this.name)) {
                    System.out.println(player.getName());
                    //TODO: Here print the names of the clients on the screne

                }
            }
        }
    }

    @Override
    public void receivePokemonCaught(Player player) {
        //TODO: Here you inform the player that the pokeman has been caught by that player and hide the pokeman from the board


    }

    @Override
    public void receiveNewPokemonPosition(Position pos) {
        pokemanPos = pos;
        // TODO: Render the pokeman in the new position
    }


}
