package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Flash extends Spell{
    public Flash(){super(new SpellComponent[] {new Deplacement()},2,4,2,new Texture("flash.png"),"Flash est un déplacement de 4 cases");}
}
