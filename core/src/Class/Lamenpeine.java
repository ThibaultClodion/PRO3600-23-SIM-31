package Class;

import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Lamenpeine extends Character{

    public Lamenpeine()
    {
        super(1500f, 3, 6, new Texture("goblin64x64.png"), new Spell[] {new Morsure(), new Des(), new Epee(), new Lance(), new Morsure(), new Morsure(), new Morsure(), new Morsure()
    });
    }
}
