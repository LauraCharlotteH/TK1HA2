package pokemon.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGameClient extends Remote {
    void incrementScore() throws RemoteException;

    String getName() throws RemoteException;

    int getScore() throws RemoteException;

    void receiveActivePlayers(ArrayList<Player> playernames) throws RemoteException;

    void receivePokemonCaught(Player player) throws RemoteException;

    void receiveNewPokemonPosition(Position pos) throws RemoteException;


}
