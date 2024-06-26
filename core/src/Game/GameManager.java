package Game;

import Scenes.EndScreen;
import Scenes.GameScreen;
import Scenes.SeedScreen;
import Scenes.SelectionScreen;
import com.badlogic.gdx.Game;
import Map.Map;

import java.util.Arrays;

public final class GameManager extends Game
{
    //GameManager is a Singleton
    private static GameManager instance;

    //Screens
    private GameScreen gameScreen;

    //Players
    public PlayerController player1;
    public PlayerController player2;
    private PlayerController actualPlayer;

    public static GameManager getInstance()
    {
        return instance;
    }

    @Override
    public void create()
    {
        //Initialize the Singleton
        instance = this;

        //Start with the selection screen
        setSelectionScreen();
    }

    public void EndRound()
    {
        //The stats are reset
        player1.NewRound();
        player2.NewRound();
        actualPlayer.UpdateCooldown();

        //The actual player switch
        actualPlayer = GetOtherPlayer();
    }

    public void GameOver()
    {
        setSelectionScreen();
    }

    public void LaunchSpell(int[] position)
    {
        //Only the actual player can use his spells
        actualPlayer.UseSpell(position, GetOtherPlayer());
    }

    public void Move(int[] position)
    {
        GetActualPlayer().Move(position, true);
    }

    public boolean isAValidPosition(int[] position)
    {
        Map map = Map.GetInstance();
        return !Arrays.equals(position, GetOtherPlayer().currentPosition) && map.IsGroundPosition(position[0], position[1])
                && !Arrays.equals(position, GetActualPlayer().currentPosition);
    }

    public void setPlayer1(PlayerController playerController)
    {
            this.player1 = playerController;
    }

    public void setPlayer2(PlayerController playerController)
    {
        this.player2 = playerController;

        //If the second player is selected then we can define who is playing first
        actualPlayer = player1;
    }

    public PlayerController GetActualPlayer()
    {
        return actualPlayer;
    }

    public PlayerController GetOtherPlayer()
    {
        if(actualPlayer == player1)
        {
            return player2;
        }
        else
        {
            return player1;
        }
    }

    public void setGameScreen(int seed)
    {
        gameScreen = new GameScreen(this , player1, player2, seed);
        setScreen(gameScreen);
    }

    public void setSelectionScreen()
    {
        SelectionScreen selectionScreen = new SelectionScreen(this);
        setScreen(selectionScreen);
    }

    public void setSeedScreen() {
        SeedScreen seedScreen = new SeedScreen(this);
        setScreen(seedScreen);
    }

    public void setEndScreen()
    {
        EndScreen endScreen = new EndScreen(this);
        if(player1.GetHp() <= 0) {
            endScreen.setPodium("Joueur 2", "Joueur 1", player2, player1);
        }
        else {
            endScreen.setPodium("Joueur 1", "Joueur 2", player1, player2);
        }
        setScreen(endScreen);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
    }

    @Override
    public void pause()
    {
        super.pause();
    }

    @Override
    public void resume()
    {
        super.resume();
    }
}
