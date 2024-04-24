package Game;

import Class.Character;
import Spells.Spell;

public class PlayerController
{
    //Character use by the Player
    public Character character;

    //Combat Data's
    public int[] currentPosition;
    public Spell actualSpell;
    private float hp;
    private int pm;
    private int pa;

    public PlayerController(Character character, int[] beginPosition)
    {
        changeCharacter(character);
        currentPosition = beginPosition;
        actualSpell = character.GetSpell(0);
    }

    //region <Datas Management>
    public void changeCharacter(Character character)
    {
        //Get the character associated to the player
        this.character = character;

        //Initialize his data's
        this.hp = character.GetHp();
        this.pm = character.GetPm();
        this.pa = character.GetPa();
    }

    public void NewRound()
    {
        //Reset the PM and PA
        pm = character.GetPm();
        pa = character.GetPa();
        actualSpell = character.GetSpell(0);
    }

    public void Hit(int damage)
    {
        this.hp -= damage;

        if(this.hp <= 0)
        {
            GameManager.getInstance().GameOver(this);
        }
    }
    //endregion

    //region <Spells>
    public boolean CanUseSpell(int[] position, Spell spell)
    {
        return Distance(position) <= spell.getRange() && pa - spell.getPa() >= 0;
    }

    public void UseSpell(int[] position, PlayerController otherPlayer)
    {
        if(CanUseSpell(position, actualSpell))
        {
            //Use the spell
            actualSpell.Launch(position, otherPlayer,this);

            //Decrease PA
            pa -= actualSpell.getPa();

        }
    }

    //endregion

    //region <Movement>
    public boolean CanMovePM(int[] position)
    {
        //Determine if a player can access a position
        return (pm - Distance(position)) >= 0 && GameManager.getInstance().isAValidPosition(position);
    }

    private boolean CanMovePA(int[] position)
    {
        return GameManager.getInstance().isAValidPosition(position);
    }

    public int Distance(int[] position)
    {
        //Get the number of move to do on x-axis and y-axis
        int xMove = Math.abs(position[0] - currentPosition[0]);
        int yMove = Math.abs(position[1] - currentPosition[1]);

        return xMove + yMove;
    }

    public void Move(int[] position, boolean usePM)
    {
        //Move the player to the position if it's possible and decrease PM
        if(usePM && CanMovePM(position))
        {
            pm -= Distance(position);
            currentPosition = position;
        }
        else if(!usePM && CanMovePA(position))
        {
            currentPosition = position;
        }
    }
    public void Echange()
    {
        int y = this.pm;
        this.pm = this.pa;
        this.pa = y;
    }
    // region <Getters>
    public int[] getCurrentPosition()
    {
        return currentPosition;
    }
    public float getHp() { return hp; }
    public int getPa() { return pa; }
    public int getPm() {return pm;}
    public Character getCharacter() {return character;}

    public float getHpInitiaux() {return character.GetHp();}

    //endregion
}
