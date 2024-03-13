package Scenes;

import Class.Aleator;
import Class.Character;
import Class.Lamenpeine;
import Game.GameManager;
import Game.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class SelectionScreen implements Screen, InputProcessor
{
    Vector3 pos;
    private boolean clicEffectue;
    private int nbClicEffectue;
    //Game Manager
    private GameManager gameManager;
    private Character characterSelected;
    private Character[] characters;

    //Batch
    private SpriteBatch batch;

    OrthographicCamera camera;
    public int classNumber;
    public int spellNumber;
    public int playerNumber;
    public Texture backgroundImage;
    public Texture spellSquareImage;
    public Texture rectangleImage; // for PV, PA, PM, and spells

    //Class Image
    public Texture[] classImages = new Texture[5];

    public Texture validationImage;
    public Music menuSound;

    //Spells
    public Rectangle[] spellsRectangles = new Rectangle[10];

    public Rectangle rectangle;

    //Class Rectangle
    public Rectangle[] classRectangles = new Rectangle[5];

    public BitmapFont spellTextFont;
    public Rectangle validation;
    public String spellText;
    public Rectangle textCoord;

    public SelectionScreen(GameManager GM)
    {
        this.gameManager = GM;
        this.characters = new Character[5];
        this.characters[1] = new Aleator();
        this.characters[2] = new Lamenpeine();
        this.characters[0] = new Lamenpeine();
        this.characters[3] = new Aleator();
        this.characters[4] = new Lamenpeine();

        pos = new Vector3();
        clicEffectue = false;
        nbClicEffectue = 0;
        Gdx.input.setInputProcessor(this);


        //Initialize the batch
        batch = new SpriteBatch();

        this.classNumber = 5;
        this.playerNumber = 1;
        this.spellNumber = 10;

        //Initialize the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        //Load the Images
        backgroundImage = new Texture(Gdx.files.internal("backgroundV2.jpeg"));
        spellSquareImage = new Texture(Gdx.files.internal("spellSquare.JPG"));
        LoadClassImages();

        //Create the spells and class Rectangle
        CreateSpellsRectangle();
        CreateClassRectangles();

        //Rectangle and Validation
        rectangleImage = new Texture(Gdx.files.internal("rectangleV2.jpeg"));
        validationImage = new Texture(Gdx.files.internal("validation.JPG")); // 100x100
        rectangle = new Rectangle(1600 /2 - 1200/2, 0, 1200, 600);
        validation = new Rectangle(1450, 100, 100, 100);
        textCoord = new Rectangle(1600 / 2 - 1200 / 2 + 50, 200, 1200, 200 );

        // load the background sound in the menu
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("pkm.mp3"));

        // start the playback of the background music immediately

        spellText = "Cliquez sur un sort";
        spellTextFont = new BitmapFont();

    }

    private void CreateClassRectangles()
    {
        // classSquare seront de 200x200 dans un rectangle de 1280x240 espacés de 40 pixels
        for(int i = 0; i < classRectangles.length; i++)
        {
            Rectangle newClassRectangle = new Rectangle(100 + 200 * i + 100 * i,650,200,200);
            classRectangles[i] = newClassRectangle;
        }
    }

    private void LoadClassImages()
    {
        for(int i = 0; i < classImages.length; i++)
        {
            classImages[i] = new Texture(Gdx.files.internal("classSquare" + i + ".JPG"));
        }
    }

    private void CreateSpellsRectangle()
    {
        // Les carrés seront espacés de 10 pixels et compris dans un rectangle de 160 de hauteur et 1100 de long
        for(int i = 0; i < spellsRectangles.length; i++)
        {
            Rectangle newSpellRectangle = new Rectangle(200 + 20 + 100 * i + 18 * i,200+50,100,100);
            spellsRectangles[i] = newSpellRectangle;
        }
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Begin the batch
        batch.begin();

        //Draw the backgrounds
        batch.draw(backgroundImage, 0, 0);
        batch.draw(rectangleImage, rectangle.x, rectangle.y);
        batch.draw(validationImage, validation.x, validation.y);

        //Draw the spells rectangle
        /*for(int i = 0; i < spellsRectangles.length; i++)
        {
            batch.draw(spellSquareImage, spellsRectangles[i].x, spellsRectangles[i].y);
        }*/

        //Draw the class rectangle
        for(int i = 0; i < classRectangles.length; i++)
        {
            batch.draw(classImages[i], classRectangles[i].x, classRectangles[i].y);
        }

        /*if (this.classNumber == 1)
        {
            //this.characterSelected = new Aleator();
            //int nbSort = this.characterSelected.nbSort;
            //for(int i = 0; i < nbSort; i++) {
            //game.batch.draw(classSpellImage, 180/2 + 5 + 100 * i + 10 * i, 160 + 30)
            //}
        }*/

        /*if(Gdx.input.isTouched())
        {
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pos);
        }*/
        spellTextFont.getData().setScale(2f);

        spellTextFont.draw(batch, spellText, textCoord.x, textCoord.y);

        if(classNumber <= 4 && classNumber >= 0) {
            for (int j = 0; j < this.characters[classNumber].getNbSpell(); j++) {
                batch.draw(spellSquareImage, spellsRectangles[j].x, spellsRectangles[j].y);
            }
        }

        /*for (int k = 0; k < 10; k++) {
            if (spellNumber == k) {
            }
        }*/


        /*if (clicEffectue)
        {
            for (int j = 0; j < this.characters[classNumber].getNbSpell(); j++) {
                batch.draw(spellSquareImage, spellsRectangles[j].x, spellsRectangles[j].y);
            }
        }*/


        //if(true)
        //{
            /*Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);*/

            /*for (int i = 0; i < classRectangles.length; i++)
            {
                if ((pos.x <= classRectangles[i].x + classRectangles[i].width && pos.x >= classRectangles[i].x)
                        && (pos.y <= classRectangles[i].y + classRectangles[i].height && pos.y >= classRectangles[i].y)) {
                    // En gros faire classText = "Pa : X            PM : X              PV : X" + System.currentTimeMillis(), séparer en 3 éventuellement
                    this.classNumber = i;
                    //Character chosen = this.characters[i];
                    for (int j = 0; j < this.characters[i].getNbSpell(); j++) {
                        batch.draw(spellSquareImage, spellsRectangles[j].x, spellsRectangles[j].y);
                    }
                }
            }*/

            /*if (touchPos.x >= validation.x && touchPos.x <= validation.x + validation.width && touchPos.y >= validation.y && touchPos.y <= validation.y + validation.height)
            {
                this.classNumber = 0;
                if (playerNumber == 1)
                {
                    this.gameManager.player1.character = this.characterSelected;
                    this.playerNumber = 2;
                }
                else 
            }*/

            // Il faut rajouter un si on clic sur validation alors on définit player1.class et on remet classNumber à 0, on incrémente countPlayer
            // puis quand countPlayer vaut 2 : on switch sur la map de combat
        //}

        batch.end();

        /*if(classNumber <= 4 && classNumber >= 0) {
            if (spellNumber <= characters[classNumber].getNbSpell() && spellNumber >= 0) {
                spellText = "PA = " + characters[classNumber].GetSpell(spellNumber).getPa() + "| ";
            }
        }*/
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenX, screenY, 0);
        clicEffectue = true;

        //Several check
        /*System.out.println(pos.x);
        //System.out.println(pos.y);
        //System.out.println(1600 - pos.x);
        System.out.println(900 - pos.y);
        System.out.println(900 - pos.y >= spellsRectangles[0].y);
        System.out.println(900 - pos.y <= spellsRectangles[0].y + spellsRectangles[0].height);
        System.out.println(pos.x <= spellsRectangles[0].x + spellsRectangles[0].width);
        System.out.println(pos.x >= spellsRectangles[0].x);
        System.out.println(classNumber <= 4 && classNumber >= 0);*/

        if(button == Input.Buttons.LEFT) {
            System.out.println("c'est good");

            for (int i = 0; i < classRectangles.length; i++) {
                System.out.println("c'est aussi good");
                // Cheking if a classRectangle has been chosen
                if (pos.x <= classRectangles[i].x + classRectangles[i].width && pos.x >= classRectangles[i].x
                        && 900 - pos.y <= classRectangles[i].y + classRectangles[i].height && 900 - pos.y >= classRectangles[i].y) {
                    //System.out.println(pos.x);
                    //System.out.println(pos.y);
                    // En gros faire classText = "Pa : X            PM : X              PV : X" + System.currentTimeMillis(), séparer en 3 éventuellement
                    this.classNumber = i;
                    //System.out.println(classNumber);
                    //Character chosen = this.characters[i];
                }
            }
            if(classNumber <= 4 && classNumber >= 0) {
                for (int j = 0; j < characters[classNumber].getNbSpell(); j++) {
                    if(pos.x <= spellsRectangles[j].x + spellsRectangles[j].width && pos.x >= spellsRectangles[j].x
                            && 900 - pos.y <= /*spellsRectangles[j].y - spellsRectangles[j].height*/ 460 && 900 - pos.y >= /*spellsRectangles[j].y*/ 360) {
                        spellNumber = j;
                        //System.out.println("okkk");
                        spellText = "PA = " + characters[classNumber].GetSpell(spellNumber).getPa() + " |";
                        Gdx.graphics.requestRendering();
                    }
                }
                // Now looking if validation button is clicked
                if(pos.x <= validation.x + validation.width && pos.x >= validation.x && 900 - pos.y <= validation.y + validation.height && 900 - pos.y >= validation.y) {
                    if (gameManager.player1 == null) {
                        gameManager.player1 = new PlayerController(characters[classNumber], new int[] {0, 0});
                    }
                    else {
                        gameManager.player2 = new PlayerController(characters[classNumber], new int[] {0, 0});
                        gameManager.setGameScreen();
                    }
                }
            }

        }
        System.out.println(classNumber);

        nbClicEffectue++;
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void dispose()
    {
        spellSquareImage.dispose();
        spellTextFont.dispose();

        //Dispose all class images
        for(int i = 0; i < classImages.length; i++)
        {
            classImages[i].dispose();
        }

        rectangleImage.dispose();
        validationImage.dispose();
        backgroundImage.dispose();
        menuSound.dispose();
    }
}
