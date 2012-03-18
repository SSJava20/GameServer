package server.games;

import java.awt.Point;

import server.GameState;
import server.ServerThread;

public abstract class Game
{
    protected ServerThread firstPlayer;
    protected ServerThread secondPlayer;
    protected GameState State;

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

    public void Move(ServerThread sender, Point coords)
    {
        if(State.Board[coords.x][coords.y] == ' ' && State.getWhoWon() == ' ')
            if(sender.getMark() == State.getCurrentPlayer())
            {
                State.Board[coords.x][coords.y] = sender.getMark();
                MovesCount++;
                CheckForWin(coords);
                State.setCurrentPlayer(reverseChar(State.getCurrentPlayer()));
            }

        SendStates();
    }


    protected abstract void CheckForWin(Point moveCord);

    protected void SendStates()
    {
        firstPlayer.sendGameState(State);
        secondPlayer.sendGameState(State);
    }

    protected abstract void start();

    public void Surrender(ServerThread sender)
    {
        State.setWhoWon(reverseChar(sender.getMark()));
        SendStates();
    }
}