package pokemon.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGameServer extends Remote {
    void login(IGameClient client, String playerName) throws RemoteException;

    void logout(String playerName) throws RemoteException;

    void catchPokemon(String playerName) throws RemoteException;

    Position getPokemanPos() throws RemoteException;

    Position getBoardOffset() throws RemoteException;

    ArrayList<Player> getPlayers() throws RemoteException;
}
