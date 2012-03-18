package server.games;

import java.awt.Point;

import server.GameState;
import server.ServerThread;

public abstract class Game
{
    protected ServerThread firstPlayer;
    protected ServerThread secondPlayer;
    protected GameState State;

    protected abstract void CheckForWin(Point moveCord);
    protected abstract void start();
    public abstract void Move(ServerThread sender, Point from, Point to);

    public GameState getState()
    {
        return State;
    }

    public void setState(GameState state)
    {
        State = state;
    }

    public Game(ServerThread firstPlayer, ServerThread secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        start();
        SendStates();
    }

    protected char reverseChar(char toRev)
    {
        if(toRev == 'x')
            return '0';
        if(toRev =='0')
            return 'x';

        return 0;
    }

    protected void SendStates()
    {
        firstPlayer.sendGameState(State);
        secondPlayer.sendGameState(State);
    }

    public void Surrender(ServerThread sender)
    {
        State.setWhoWon(reverseChar(sender.getMark()));
        SendStates();
    }
}