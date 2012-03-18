package server;

public class ServerThread implements Runnable
{
    public char getMark()
    {
        return Mark;
    }

    public void setMark(char mark)
    {
        Mark = mark;
    }

    char Mark;

    public void run()
    {
        // TODO Auto-generated method stub

    }

    public void sendGameState(GameState toSend)
    {

    }

}
