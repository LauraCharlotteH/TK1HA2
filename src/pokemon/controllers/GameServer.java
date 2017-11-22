package pokemon.controllers;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class GameServer implements IGameServer {

    public static void main(String args[]) {

        try {
            GameServer obj = new GameServer();
            IGameServer stub = (IGameServer) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("GameServer", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    ArrayList<IGameClient> clients;
    ArrayList<Player> players;
    Position pokemanPos;
    Position boardOffset;
    int width;
    int height;

    GameServer() {
        width = 5;
        height = 3;
        boardOffset = new Position(height, width);
        this.createPokemanPosition();
    }

    private Position createPokemanPosition() {
        Random rn = new Random();
        int posX = rn.nextInt(width);
        int posY = rn.nextInt(height);
        pokemanPos = new Position(posX, posY);
        return pokemanPos;
    }

    // Here the server acts as a client to the new GameClient
    // this is needed so that server is also able to send notifications to the clients
    private IGameClient locateServer(IGameClient client)
    {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            IGameClient stub = (IGameClient) registry.lookup(client.getName());
            return  stub;

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void login(IGameClient client, String playerName) {
        if (clients == null)
            clients = new ArrayList<IGameClient>();
        clients.add(locateServer(client));
        if (players == null)
            players = new ArrayList<Player>();
        players.add(new Player(playerName, 0));
        // now inform players that a new client has logged in
        for (IGameClient cl : clients) {
            try {
                cl.receiveActivePlayers(players);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }


    }

    @Override
    public void logout(String playerName) {
        int index = 0;
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                clients.remove(index);
                players.remove(index);
                break;
            }
            index++;
        }

        // inform players that a client has logged out
        for (IGameClient client : clients) {
            try {
                client.receiveActivePlayers(players);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void catchPokemon(String playerName) {

        int index = 0;
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                // inform the winner
                try {
                    clients.get(index).incrementScore();
                    player.incrementScore();
                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                break;
            } else {
                // other clients has to be informed so that they can inform the user
                try {
                    clients.get(index).receivePokemonCaught(player);
                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            }


            index++;
        }

        // give the clients the updated player list to render
        for (IGameClient client : clients) {
            try {
                client.receiveActivePlayers(players);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

        // get a new position for the pokeman and inform the clients
        createPokemanPosition();
        for (IGameClient gameClient : this.clients)
            try {
                System.out.println("Calling clients about the new pos");
                gameClient.receiveNewPokemonPosition(this.pokemanPos);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }


    }


    @Override
    public Position getBoardOffset() {
        return boardOffset;
    }

    @Override
    public Position getPokemanPos() {
        return pokemanPos;
    }

    @Override
    public ArrayList<Player> getPlayers() throws RemoteException {
        return players;
    }
}
