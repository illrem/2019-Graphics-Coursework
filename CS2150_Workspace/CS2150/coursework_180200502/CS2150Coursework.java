/* CS2150Coursework.java
 * 180200502, Aderemi Ajidahun, second year computer science
 * 		Unless stated otherwise this is my own work
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *	*Bird
 *		|
 *		+-- [T(position,2.5f,2.0f)] Bird
 *			+--[T(0,0,0)] cone (beak)
 *			+--[T(0,0, -0.5f)] cylinder (head)
 *			+--[T(0,0,0)] sphere (back of head)
 *			+--[T(0,1.5f, 1.5f)] cylinder (neck)
 *			+--[T(0,0,0)] sphere (front of body)
 *			+--[T(0,0,0)] cylinder (body)
 *			+--[T(0.4f,0.5f, 1.8f)] cylinder (left leg)
 *			|	+--[T(0.0f,0.0f, 0.75f)] sphere (foot)
 *			+--[T(-0.8f,0, 0)] cylinder (right leg)
 *			|	+--[T(0.0f,0.0f, 0.75f)] sphere (foot)
 *			+--[T(0.4f,-0.5f, -0.05f)] sphere (end of body)
 *			+--[T(0,0,0)] cylinder (tail)
 *			+--[T(0.7f,-0.7f, -0.7f)] custom (wing)
 *			+--[T(0,0, 1.4f)] custom (wing)
 *			+--[T(0,0,0)] cone (beak)
 *			+--[T(0,0,0)] cone (beak)
 *			+--[T(0,0,0)] cone (beak)
 *			+--[T(0,0,0)] cone (beak)
 *  *Ground
 *  	|
 *  	+--[T(0,-1,advance)]*13 plane (the ground)
 *  *horizon
 *  	|
 *  	+--[T(45.0f,10.0f,-75.0f)] plane (the horizon)
 *  *Fish
 *  	|
 *  	+--[S(5,5,5),T(fishx,fishy,fishz)]
 *  *Tree
 *  	|
 *  	+--[T(treeX,treeY,treeZ)] Sphere (foliage)
 *  	+--[T(0.0f, treeY+0.2f, 0.0f)] cylinder (trunk)
 */
package coursework_180200502;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;
import java.util.Random;


/**
 * 
 * Scene Description:
 * A bird flies forward, flapping its wings, along a river towards the sunset.
 * A fish jumps out of the river at random points and intervals
 * There are trees that are generated to be unique, that are randomly scattered along the riverbank.
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>press A to move the bird Left Press D to move the bird Right
 * <li>Use the arrow keys to rotate the camera about the center of the scene
 * </ul>
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	//variables for the current fish position
	float fishx,fishy,fishz,fishacel;
	//variable to create randoms
	Random rnd = new Random(); 
	//variable to control the direction the wings are moving
	private boolean flapup = true;
	//size of the ground plane
	private float groundsize = -47.05f;
	//variables to move the ground towards the camera
	private float advance1, advance2,advance3, advance4,advance5, advance6,advance7, advance8,advance9, advance10;
	//position of the bird in the x direction
	private float position = 0;
	//angle of the wings
	private float wingflap = 0;

	//standard speed for movement in the scene
	private float gamespeed=0.01f;
	//number of trees per side
	private int treeamount = 75;

	//array of arrays that store tree positions
	private float[][] treesr;
	private float[][] treesl;

	//camera coordinates
	private float camerax;
	private float cameray;


	//textures for the scene
	private Texture featherTexture;
	private Texture beakTexture;
	private Texture scaleTexture;
	private Texture groundTextures;
	private Texture Horizon;


	//window naming and gamespeed set
	public static void main(String args[])
	{   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",1f);
	}

	protected void initScene() throws Exception
	{
		//set the ground variables to put ground planes one after another
		advance1 = 0; advance2 = advance1 + groundsize;advance3 = advance2 + groundsize; advance4 = advance3 + groundsize;advance5 = advance4 + groundsize; advance6 = advance5 + groundsize;advance7  = advance6 + groundsize; advance8  = advance7 + groundsize;advance9 = advance8 + groundsize; advance10 = advance9 + groundsize;

		//set the coordinates of the fish
		fishz = groundsize;
		fishx=-5f; fishy = -3.5f;fishacel=0.03f;

		//setup the textures
		featherTexture = loadTexture("coursework_180200502/textures/feathertex.bmp");
		beakTexture = loadTexture("coursework_180200502/textures/beaktex.bmp");
		groundTextures = loadTexture("coursework_180200502/textures/river.bmp");
		scaleTexture = loadTexture("coursework_180200502/textures/scales.bmp");
		Horizon = loadTexture("coursework_180200502/textures/nightSky.bmp");

		//setup lighting conditions
		float globalAmbient[]   = {0.5f,  0.5f,  0.5f, 1.0f};
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
		// the first light for the scene is soft blue...
		float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
		// ...with a very dim ambient contribution...
		float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
		// ...and is positioned above the viewpoint
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f};

		// supply OpenGL with the properties for the first light (using the same as lab6 as that seemed to work best)
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);

		//define trees
		//1=x,2-y,3=z,4=height,5=sphere width;
		treesr = new float[treeamount][5];
		treesl = new float[treeamount][5];
		for (int x=0; x < treeamount; x++)
		{
			//set a random x position at the edge of the river for the trees
			treesr[x][0]=15.0f+(rnd.nextFloat()*10);
			treesl[x][0]=-15.0f+(rnd.nextFloat()*-10);
			treesr[x][1]=-2f;
			treesl[x][1]=-2f;
			//set the trees to the correct z dimensions
			treesr[x][2]=-x*(250/treeamount);
			treesl[x][2]=-x*(250/treeamount);

			//set a random height
			treesr[x][3]=1+rnd.nextFloat();
			treesl[x][3]=1+rnd.nextFloat();
			//set random sphere size
			treesr[x][4]=0.5f + rnd.nextFloat();
			treesl[x][4]=0.5f + rnd.nextFloat();
		}

		//set camera starting coordinates
		camerax = 0f;
		cameray = 10f;

	}
	protected void checkSceneInput()
	{

		//controls for the bird left to right
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{   
			if (position <= 7.55)
				position += gamespeed;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{   
			if (position >= -7.55)
				position -= gamespeed;
		}

		//move camera position within bounds using arrow keys
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&(cameray<15f))
		{
			cameray+=gamespeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)&&(cameray>7.75f))
		{
			cameray-=gamespeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&(camerax<1f))
		{
			camerax+=gamespeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&(camerax>-1f))
		{
			camerax-=gamespeed;
		}
	}
	protected void updateScene()
	{
		//TODO: Update your scene variables here - remember to use the current animation scale value
		//        (obtained via a call to getAnimationScale()) in your modifications so that your animations
		//        can be made faster or slower depending on the machine you are working on


		//wingflap make the wing flap up and down
		if (wingflap <= 0)
		{
			flapup = true;
		}
		else if(wingflap >= 45)
		{
			flapup = false;    		   
		}

		if (flapup)
		{
			wingflap += gamespeed*10;
		}
		else
		{
			wingflap -= gamespeed*5;
		}

		for (int x=0; x < treeamount; x++)
		{
			treesl[x][2]+=gamespeed;   
			treesr[x][2]+=gamespeed;

			if (treesl[x][2]>=0)
			{
				treesl[x][2] += 10*groundsize;
			}
			if (treesr[x][2]>=0)
			{
				treesr[x][2] += 10*groundsize;
			}
		}

		//move the ground towards the camera and reset it to the end of the queue if it goes past the camera 
		checkadvance();

		//fish movement
		fishz += gamespeed;
		fishx += 1.5*gamespeed;

		//give the fish an arc'ed flight
		fishacel -= 0.00005;
		fishy += fishacel;

		//set the fish to reappear at random intervals
		if (fishy < -3.5f + ((1+rnd.nextFloat())*-500))
		{
			fishz = groundsize;
			fishx=(rnd.nextFloat()+1f)*-4; fishy = -3.5f;fishacel=0.03f;
		}

	}
	protected void renderScene()

	{




		//draw the ground planes
		drawGround(advance1);
		drawGround(advance2);
		drawGround(advance3);
		drawGround(advance4);
		drawGround(advance5);
		drawGround(advance6);
		drawGround(advance7);
		drawGround(advance8);
		drawGround(advance9);
		drawGround(advance10);
		//draw the horizon
		drawBack();

		//draw the duck
		drawDuck();    	 
		//draw the fish 
		drawFish();

		//draw the the trees that are in shot (only renders som trees to reduce lag)
		for (int x=0; x < treeamount; x++)
		{
			if (treesl[x][2] > -76)
			{
				drawTree(treesl[x]);
				drawTree(treesr[x]);
			}
		}
	}
	protected void setSceneCamera()
	{
		// call the default behaviour defined in GraphicsLab. This will set a default perspective projection
		// and default camera settings ready for some custom camera positioning below...  
		super.setSceneCamera();


		GL11.glLoadIdentity();
		// Position the camera above the bird
		GLU.gluLookAt(camerax,cameray, 15,     /* Camera position */
				0,4,0,          /* Target */
				0, 1, 0         /* Up vector */ 
				);
	}

	protected void cleanupScene()
	{//TODO: Clean up your resources here
	}

	private void drawDuck()
	{
		GL11.glPushMatrix();
		{
			//(textures and lighting stuff mostly modified lab6)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,beakTexture.getTextureID());           

			//rotate to the same direction as the viewer is watching from
			GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);

			//make the center of the bird the middle
			GL11.glTranslatef(position,2.5f,2.0f);


			//draw cone/beak
			new Cylinder().draw(0.25f, 0.0f, 0.75f, 10, 10);
			//change the texture
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,featherTexture.getTextureID());
			//move cylinder to back of cone
			GL11.glTranslatef(0,0, -0.5f);
			//draw cylinder
			new Cylinder().draw(0.25f,0.25f, 0.5f, 10, 10);
			//GL11.glTranslatef(0,0, -0.5f);
			//create sphere
			new Sphere().draw(0.25f,10,10);
			//rotate the neck
			GL11.glRotatef(135.0f, 1.0f, 0.0f, 0.0f);
			//draw neck
			new Cylinder().draw(0.25f,0.25f, 2.5f, 10, 10);
			//rotate back from neck plane to normal
			GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
			//move the body to the end of the neck
			GL11.glTranslatef(0,1.5f, 1.5f);
			//draw the body
			new Cylinder().draw(0.75f,0.75f, 2.5f, 10, 10);
			//draw a sphere to cap off the front of the body
			new Sphere().draw(0.75f,10,10);
			//currently at the location for the start of the cylinder/end of the neck
			// move to the left foot
			GL11.glTranslatef(0.4f,0.5f, 1.8f);
			//make foot vertical
			GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
			//draw leg
			new Cylinder().draw(0.1f,0.1f, 0.75f, 10, 10);
			//move to foot
			GL11.glTranslatef(0.0f,0.0f, 0.75f);

			//change texture
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,beakTexture.getTextureID());

			//draw foot
			new Sphere().draw(0.2f,10,10);

			//change texture
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,featherTexture.getTextureID());

			//go back to the top of the leg
			GL11.glTranslatef(0.0f,0.0f, -0.75f);            
			//move along to right leg
			GL11.glTranslatef(-0.8f,0, 0);
			//draw right leg
			new Cylinder().draw(0.1f,0.1f, 0.75f, 10, 10);
			//move to foot
			GL11.glTranslatef(0.0f,0.0f, 0.75f);

			//change texture
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,beakTexture.getTextureID());

			//draw foot
			new Sphere().draw(0.2f,10,10);
			//go back to the top of the leg  

			//change texture
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,featherTexture.getTextureID());

			GL11.glTranslatef(0.0f,0.0f, -0.75f);
			//move to end of body
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0.4f,-0.5f, 0.7f);
			//currently at the end of the body
			//draw end of body
			new Sphere().draw(0.75f,10,10);
			//rotate to the tail
			GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
			//place the cone for the tail
			new Cylinder().draw(0.65f,0, 1.25f, 10, 10);
			//return rotation
			GL11.glRotatef(-45.0f, 1.0f, 0.0f, 0.0f);
			//move to left wing
			GL11.glTranslatef(0.7f,-0.7f, -0.7f);
			//rotate wing
			GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);            
			//make wings flap
			GL11.glRotatef(wingflap, 1.0f, 0.0f, 0.0f);




			// the vertices for the left wing

			//the top face
			Vertex t1 = new Vertex( -0.5f,  0.5f,    0f);
			Vertex t2 = new Vertex( -0.5f,  0.5f,-0.75f);
			Vertex t3 = new Vertex( -0.6f,  0.5f,-1.75f);
			Vertex t4 = new Vertex(  0.3f,  0.5f,   -1f);
			Vertex t5 = new Vertex(  0.5f,  0.5f,    0f);

			//the bottom face
			Vertex b1 = new Vertex( -0.3f,  0.3f,    0f);
			Vertex b2 = new Vertex( -0.3f,  0.3f, -0.9f);
			Vertex b3 = new Vertex( -0.5f,  0.3f, -2.6f);
			Vertex b4 = new Vertex(  0.5f,  0.3f, -1.8f);
			Vertex b5 = new Vertex(  0.9f,  0.3f,    0f);

			// draw the top face:
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),t2.toVector(),t3.toVector()).submit();

				t5.submit();
				t4.submit();
				t3.submit();
				t2.submit();
				t1.submit();
			}
			GL11.glEnd();

			//draw bottom face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b5.toVector(),b4.toVector(),b3.toVector(),b2.toVector()).submit();

				b1.submit();
				b2.submit();
				b3.submit();
				b4.submit();
				b5.submit();   			

			}
			GL11.glEnd();
			//side1 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),b1.toVector(),t2.toVector()).submit();

				t2.submit();
				b1.submit();
				t1.submit();
			}
			GL11.glEnd();
			//side 2 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b2.toVector(),t2.toVector()).submit();

				t2.submit();
				b2.submit();
				b1.submit();
			}
			GL11.glEnd();
			//side 3 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t2.toVector(),b2.toVector(),t3.toVector()).submit();

				t3.submit();
				b2.submit();
				t2.submit();
			}
			GL11.glEnd();
			//side 4 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b2.toVector(),b3.toVector(),t3.toVector()).submit();

				t3.submit();
				b3.submit();
				b2.submit();
			}
			GL11.glEnd();
			//side 5 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t3.toVector(),b3.toVector(),t4.toVector()).submit();

				t4.submit();
				b3.submit();
				t3.submit();
			}
			GL11.glEnd();
			//side 6 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b3.toVector(),b4.toVector(),t4.toVector()).submit();

				t4.submit();
				b4.submit();
				b3.submit();
			}
			GL11.glEnd();
			//side 7 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t4.toVector(),b4.toVector(),b5.toVector()).submit();

				b5.submit();
				b4.submit();
				t4.submit();
			}
			GL11.glEnd();
			//side 8 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t4.toVector(),b5.toVector(),t5.toVector()).submit();

				t5.submit();
				b5.submit();
				t4.submit();
			}
			GL11.glEnd();
			//side 9 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t5.toVector(),b5.toVector(),b1.toVector()).submit();

				b1.submit();
				b5.submit();
				t5.submit();
			}
			GL11.glEnd();
			//side 10 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),b1.toVector(),t5.toVector()).submit();

				t5.submit();
				b1.submit();
				t1.submit();
			}
			GL11.glEnd();

			//move back to correct rotation
			GL11.glRotatef(-wingflap, 1.0f, 0.0f, 0.0f);


			////////////////////////////////////////////////////////
			//move to right wing
			GL11.glTranslatef(0,0, 1.4f);


			//rotate right wing
			GL11.glRotatef(-wingflap, 1.0f, 0.0f, 0.0f);
			//GL11.glRotatef(10.0f, 0.0f, 1.0f, 0.0f);
			// the vertices for the right wing

			//the top face
			Vertex tl1 = new Vertex( -0.5f,  0.5f,    0f);
			Vertex tl2 = new Vertex( -0.5f,  0.5f, 0.75f);
			Vertex tl3 = new Vertex( -0.6f,  0.5f, 1.75f);
			Vertex tl4 = new Vertex(  0.3f,  0.5f,    1f);
			Vertex tl5 = new Vertex(  0.5f,  0.5f,    0f);

			//the bottom face
			Vertex bl1 = new Vertex( -0.3f,  0.3f,    0f);
			Vertex bl2 = new Vertex( -0.3f,  0.3f,  0.9f);
			Vertex bl3 = new Vertex( -0.5f,  0.3f,  2.6f);
			Vertex bl4 = new Vertex(  0.5f,  0.3f,  1.8f);
			Vertex bl5 = new Vertex(  0.9f,  0.3f,    0f);

			// draw the top face:
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl1.toVector(),tl2.toVector(),tl3.toVector()).submit();

				tl1.submit();
				tl2.submit();
				tl3.submit();
				tl4.submit();
				tl5.submit();
			}
			GL11.glEnd();

			//draw bottom face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(bl5.toVector(),bl4.toVector(),bl3.toVector(),bl2.toVector()).submit();

				bl5.submit();
				bl4.submit();
				bl3.submit();
				bl2.submit();
				bl1.submit();   			

			}
			GL11.glEnd();
			//side1 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl1.toVector(),bl1.toVector(),tl2.toVector()).submit();

				tl1.submit();
				bl1.submit();
				tl2.submit();
			}
			GL11.glEnd();
			//side 2 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(bl1.toVector(),bl2.toVector(),tl2.toVector()).submit();

				bl1.submit();
				bl2.submit();
				tl2.submit();
			}
			GL11.glEnd();
			//side 3 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl2.toVector(),bl2.toVector(),tl3.toVector()).submit();

				tl2.submit();
				bl2.submit();
				tl3.submit();
			}
			GL11.glEnd();
			//side 4 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(bl2.toVector(),bl3.toVector(),tl3.toVector()).submit();

				bl2.submit();
				bl3.submit();
				tl3.submit();
			}
			GL11.glEnd();
			//side 5 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl3.toVector(),bl3.toVector(),tl4.toVector()).submit();

				tl3.submit();
				bl3.submit();
				tl4.submit();
			}
			GL11.glEnd();
			//side 6 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(bl3.toVector(),bl4.toVector(),tl4.toVector()).submit();

				bl3.submit();
				bl4.submit();
				tl4.submit();
			}
			GL11.glEnd();
			//side 7 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl4.toVector(),bl4.toVector(),bl5.toVector()).submit();

				tl4.submit();
				bl4.submit();
				bl5.submit();
			}
			GL11.glEnd();
			//side 8 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl4.toVector(),bl5.toVector(),tl5.toVector()).submit();

				tl4.submit();
				bl5.submit();
				tl5.submit();
			}
			GL11.glEnd();
			//side 9 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl5.toVector(),bl5.toVector(),bl1.toVector()).submit();

				tl5.submit();
				bl5.submit();
				bl1.submit();
			}
			GL11.glEnd();
			//side 10 face
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(tl1.toVector(),bl1.toVector(),tl5.toVector()).submit();

				tl1.submit();
				bl1.submit();
				tl5.submit();
			}
			GL11.glEnd();
			GL11.glRotatef(wingflap, 1.0f, 0.0f, 0.0f);

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void drawGround(float advance)
	{
		GL11.glPushMatrix();
		{
			//textures and lighting (modified lab6)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());

			// position, scale and draw the ground plane
			GL11.glTranslatef(10f,-1.0f,advance);
			GL11.glScalef(75.0f, 1.0f, 80.25f);

			Vertex v1 = new Vertex(-0.5f, -1.0f,-0.5f); // left,  back
			Vertex v2 = new Vertex( 0.5f, -1.0f,-0.5f); // right, back
			Vertex v3 = new Vertex( 0.5f, -1.0f, 0.5f); // right, front
			Vertex v4 = new Vertex(-0.5f, -1.0f, 0.5f); // left,  front

			// draw the ground
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();

				GL11.glTexCoord2f(0.0f,0.0f);
				v4.submit();

				GL11.glTexCoord2f(1.0f,0.0f);
				v3.submit();

				GL11.glTexCoord2f(1.0f,1.0f);
				v2.submit();

				GL11.glTexCoord2f(0.0f,1.0f);
				v1.submit();
			}
			GL11.glEnd();

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();


	}

	private void drawBack()
	{
		// draw the back plane
		GL11.glPushMatrix();
		{
			// textures and lighting
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,Horizon.getTextureID());

			// position, scale, rotate and draw the back plane
			GL11.glTranslatef(45.0f,10.0f,-75.0f);
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(250.0f, 1.0f, 50.0f);

			Vertex v1 = new Vertex(-0.5f, -1.0f,-0.5f); // left,  back
			Vertex v2 = new Vertex( 0.5f, -1.0f,-0.5f); // right, back
			Vertex v3 = new Vertex( 0.5f, -1.0f, 0.5f); // right, front
			Vertex v4 = new Vertex(-0.5f, -1.0f, 0.5f); // left,  front

			// draw the plane geometry. order the vertices so that the plane faces up
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();

				GL11.glTexCoord2f(0.0f,0.0f);
				v4.submit();

				GL11.glTexCoord2f(1.0f,0.0f);
				v3.submit();

				GL11.glTexCoord2f(1.0f,1.0f);
				v2.submit();

				GL11.glTexCoord2f(0.0f,1.0f);
				v1.submit();
			}
			GL11.glEnd();


			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

	}

	private void drawFish()

	{
		GL11.glPushMatrix();
		{
			//move fish to correct place
			GL11.glTranslatef(fishx,fishy,fishz);

			//fish textures
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,scaleTexture.getTextureID());      

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 50f);


			//scale up the fish
			GL11.glScalef(5.0f, 5.0f, 5.0f);

			//front vector
			Vertex f1 = new Vertex(0,0,0);

			//the top vectors
			Vertex t1 = new Vertex( -0.2f,  0f, 0.2f);
			Vertex t2 = new Vertex( -0.4f,  0f, 0.2f);

			// the bottom vectors
			Vertex z1 = new Vertex( -0.2f,  0.0f, -0.2f);
			Vertex z2 = new Vertex( -0.4f,  0.0f, -0.2f);

			//left vectors            
			Vertex l1 = new Vertex(  -0.25f,  0.1f,    0.02f);  
			Vertex l2 = new Vertex(  -0.35f,  0.1f,    0.02f);  
			Vertex l3 = new Vertex(  -0.25f,  0.1f,   -0.02f);   
			Vertex l4 = new Vertex(  -0.35f,  0.1f,   -0.02f); 
			Vertex lf1 = new Vertex(  -0.2f,  0.15f,    0f);  
			Vertex lf2 = new Vertex(  -0.4f,  0.15f,    0f); 

			//right vectors            
			Vertex r1 = new Vertex(  -0.25f,  -0.1f,    0.02f);  
			Vertex r2 = new Vertex(  -0.35f,  -0.1f,    0.02f);  
			Vertex r3 = new Vertex(  -0.25f,  -0.1f,   -0.02f);   
			Vertex r4 = new Vertex(  -0.35f,  -0.1f,   -0.02f); 
			Vertex rf1 = new Vertex(  -0.2f, -0.15f,    0f);  
			Vertex rf2 = new Vertex(  -0.4f, -0.15f,    0f); 

			//tail vectors          
			Vertex b1 = new Vertex(-0.5f, 0f, 0.05f);  
			Vertex b2 = new Vertex(-0.5f, 0f,-0.05f); 
			Vertex b3 = new Vertex(-0.6f, 0.05f,-0.15f);  
			Vertex b4 = new Vertex(-0.6f, 0.05f, 0.15f);  
			Vertex b5 = new Vertex(-0.6f,-0.05f,-0.15f);  
			Vertex b6 = new Vertex(-0.6f,-0.05f, 0.15f);  



			// draw the tail:
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b2.toVector(),b3.toVector()).submit();

				b2.submit();
				b3.submit();                
				b4.submit();
				b1.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b2.toVector(),b5.toVector()).submit();

				b1.submit();
				b6.submit();                
				b5.submit();
				b2.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b4.toVector(),b6.toVector()).submit();

				b1.submit();
				b4.submit();                
				b6.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b2.toVector(),b5.toVector(),b3.toVector()).submit();

				b2.submit();
				b5.submit();                
				b3.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b4.toVector(),b3.toVector(),b5.toVector()).submit();

				b4.submit();
				b3.submit();
				b5.submit();
				b6.submit();
			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b2.toVector(),b5.toVector(),b3.toVector()).submit();

				b2.submit();
				b5.submit();                
				b3.submit();

			}
			GL11.glEnd();




			////////////////draw the right side of the fish
			//s1
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),r1.toVector(),f1.toVector()).submit();

				t1.submit();
				r1.submit();                
				f1.submit();

			}
			GL11.glEnd();
			//s2
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),t2.toVector(),r2.toVector()).submit();

				t1.submit();
				t2.submit();                
				r2.submit();
				r1.submit();

			}
			GL11.glEnd();

			//s3
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),r2.toVector(),t2.toVector()).submit();

				b1.submit();
				r2.submit();                
				t2.submit();

			}
			GL11.glEnd();
			//s4
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b2.toVector(),r2.toVector()).submit();

				b1.submit();
				b2.submit();                
				r4.submit();
				r2.submit();

			}
			GL11.glEnd();
			//s6
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b2.toVector(),z2.toVector(),r4.toVector()).submit();

				b2.submit();
				z2.submit();                
				r4.submit();

			}
			GL11.glEnd();
			//s7
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(r4.toVector(),r3.toVector(),z1.toVector()).submit();

				r4.submit();
				z2.submit();                
				z1.submit();
				r3.submit();

			}
			GL11.glEnd();
			//s8
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(r3.toVector(),f1.toVector(),z1.toVector()).submit();

				r3.submit();
				z1.submit();                
				f1.submit();

			}
			GL11.glEnd();
			//s9
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(f1.toVector(),r3.toVector(),r1.toVector()).submit();

				r1.submit();
				r3.submit();                
				f1.submit();

			}
			GL11.glEnd();

			//draw right fin

			//top face of fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(r1.toVector(),r2.toVector(),rf1.toVector()).submit();

				r2.submit();
				rf2.submit();                
				rf1.submit();
				r1.submit();

			}
			GL11.glEnd();

			//bottom face of fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(rf1.toVector(),rf2.toVector(),rf1.toVector()).submit();


				rf2.submit();                
				r4.submit(); 
				r3.submit();              
				rf1.submit();

			}
			GL11.glEnd();


			//side faces of the fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(r1.toVector(),r3.toVector(),rf1.toVector()).submit();


				rf1.submit();                
				r3.submit();              
				r1.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(rf1.toVector(),r4.toVector(),r3.toVector()).submit();


				rf2.submit();
				r2.submit(); 

				r4.submit();  

			}
			GL11.glEnd();


			///////////////draw left side of fish   
			//s1
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),l1.toVector(),f1.toVector()).submit();

				f1.submit();
				l1.submit();                
				t1.submit();

			}
			GL11.glEnd();
			//s2
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(t1.toVector(),t2.toVector(),l2.toVector()).submit();

				l1.submit();
				l2.submit();                
				t2.submit();
				t1.submit();

			}
			GL11.glEnd();

			//s3
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),l2.toVector(),t2.toVector()).submit();

				t2.submit();
				l2.submit();                
				b1.submit();

			}
			GL11.glEnd();
			//s4
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b1.toVector(),b2.toVector(),l2.toVector()).submit();

				l2.submit();
				l4.submit();                
				b2.submit();
				b1.submit();

			}
			GL11.glEnd();
			//s6
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(b2.toVector(),z2.toVector(),l4.toVector()).submit();

				l4.submit();
				z2.submit();                
				b2.submit();

			}
			GL11.glEnd();
			//s7
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(l4.toVector(),l3.toVector(),z1.toVector()).submit();

				l3.submit();
				z1.submit();                
				z2.submit();
				l4.submit();

			}
			GL11.glEnd();
			//s8
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(l3.toVector(),f1.toVector(),z1.toVector()).submit();

				f1.submit();
				z1.submit();                
				l3.submit();

			}
			GL11.glEnd();
			//s9
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(f1.toVector(),l3.toVector(),l1.toVector()).submit();

				f1.submit();
				l3.submit();                
				l1.submit();

			}
			GL11.glEnd();

			//draw LEFT fin

			//top face of fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(l1.toVector(),l2.toVector(),lf1.toVector()).submit();

				l1.submit();
				lf1.submit();                
				lf2.submit();
				l2.submit();

			}
			GL11.glEnd();

			//bottom face of fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(lf1.toVector(),lf2.toVector(),lf1.toVector()).submit();


				lf1.submit();                
				l3.submit(); 
				l4.submit();              
				lf2.submit();

			}
			GL11.glEnd();


			//side faces of the fin
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(l1.toVector(),l3.toVector(),lf1.toVector()).submit();


				l1.submit();                
				l3.submit();              
				lf1.submit();

			}
			GL11.glEnd();

			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(lf1.toVector(),l4.toVector(),l3.toVector()).submit();


				l4.submit();
				l2.submit();                
				lf2.submit();  

			}
			GL11.glEnd();

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();

		}
		GL11.glPopMatrix();
	}

	private void drawTree(float[] tree)
	{
		// draw the tree
		GL11.glPushMatrix();
		{

			//set lighting properties
			float trunkFrontShininess  = 15.0f;
			float trunkFrontSpecular[] = {0.14f, 0.14f, 0.15f, 1.0f};
			float trunkFrontDiffuse[]  = {0.4f, 0.3f, 0.1f, 1.0f};

			// set the material properties for the trunk
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

			// position the tree
			GL11.glTranslatef(tree[0], tree[1], tree[2]);
			//scale the tree
			GL11.glScalef(2.0f, 2.0f, 2.0f);


			GL11.glPushMatrix();
			{
				//rotate and draw the trunk
				GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				new Cylinder().draw(0.25f, 0.25f, tree[3], 10, 10);
			}
			GL11.glPopMatrix();

			//set material properties for leaves (based on lab6 leaves)
			float headFrontShininess  = 20.0f;
			float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
			float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};

			// set the material properties for the leaves
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));

			// position and draw the leaves
			GL11.glTranslatef(0.0f, tree[3]+0.2f, 0.0f);
			new Sphere().draw(tree[4], 10, 10);
		}
		GL11.glPopMatrix();
	}

	private void checkadvance()
	{
		//move the ground towards the camera
		advance1 += gamespeed;advance2 += gamespeed;advance3 += gamespeed;advance4 += gamespeed;advance5 += gamespeed;advance6 += gamespeed;advance7 += gamespeed;advance8 += gamespeed;advance9 += gamespeed;advance10 += gamespeed;

		//move the ground to the back of the queue if it is behind the camera
		if (advance1 > 15)
		{
			advance1 = advance2+groundsize;
		}
		if (advance2 > 15)
		{
			advance2 = advance3+groundsize;
		}
		if (advance3 > 15)
		{
			advance3 = advance4+groundsize;
		}
		if (advance4 > 15)
		{
			advance4 = advance5+groundsize;
		}
		if (advance5 > 15)
		{
			advance5 = advance6+groundsize;
		}
		if (advance6 > 15)
		{
			advance6 = advance7+groundsize;
		}
		if (advance7 > 15)
		{
			advance7 = advance8+groundsize;
		}
		if (advance8 > 15)
		{
			advance8 = advance9+groundsize;
		}
		if (advance9 > 15)
		{
			advance9 = advance10+groundsize;
		}
		if (advance10 > 15)
		{
			advance10 = advance1+groundsize;
		}
	}

}
