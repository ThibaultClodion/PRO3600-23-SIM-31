package Class;

import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Lamenpeine extends Character
{

    public Lamenpeine()
    {
        super(1400f, 2, 5, new Texture("goblin64x64.png"), new Texture("creationnisteRectangle.JPG"), new Spell[] {new SautDeLange(), new Morsure(),new Coup_circulaire(), new Coup_d_epee(), new Dechiquetage()});
    }
}
