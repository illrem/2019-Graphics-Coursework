/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
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
import java.util.*;


/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	Random rnd = new Random(); 
	private boolean flapup = true;
	private float rotate;
	private float groundsize = -47f;
	private float advance1, advance2,advance3, advance4,advance5, advance6,advance7, advance8,advance9, advance10;
	private float position = 0;
	private float wingflap = 0;
    private Texture featherTexture;
    private Texture beakTexture;
    
    ///stolen need to replace
    /** ids for nearest, linear and mipmapped textures for the ground plane */
    private Texture groundTextures;
    /** ids for nearest, linear and mipmapped textures for the daytime back (sky) plane */
    //private Texture skyDayTextures;
    /** ids for nearest, linear and mipmapped textures for the night time back (sky) plane */
    private Texture skyNightTextures;
    
	
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.
    	// global ambient light level
    	
    	advance1 = groundsize; advance2 = advance1 + groundsize;advance3 = advance2 + groundsize; advance4 = advance3 + groundsize;advance5 = advance4 + groundsize; advance6 = advance5 + groundsize;advance7  = advance6 + groundsize; advance8  = advance7 + groundsize;advance9 = advance8 + groundsize; advance10 = advance9 + groundsize;

        featherTexture = loadTexture("coursework_180200502/textures/feathertex.bmp");
        beakTexture = loadTexture("coursework_180200502/textures/beaktex.bmp");
        groundTextures = loadTexture("coursework_180200502/textures/river.bmp");
        //skyDayTextures = loadTexture("coursework_180200502/textures/daySky.bmp");
        skyNightTextures = loadTexture("coursework_180200502/textures/nightSky.bmp");

        float globalAmbient[]   = {0.5f,  0.5f,  0.5f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
        // the first light for the scene is soft blue...
        float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
        // ...with a very dim ambient contribution...
        float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f};

        // supply OpenGL with the properties for the first light
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
        
    }
    protected void checkSceneInput()
    {//TODO: Check for keyboard and mouse input here
    	/**
    	if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {   rotate += 1f;
        }
    	else
    	{
    		rotate = 0;
    	}
    	**/
    	
    	//controls for the bird left to right
    	if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {   
    		if (position <= 7.55)
    		position += 0.01f;
        }
    	else if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {   
    		if (position >= -7.55)
    		position -= 0.01f;
        }
    }
    protected void updateScene()
    {
        //TODO: Update your scene variables here - remember to use the current animation scale value
        //        (obtained via a call to getAnimationScale()) in your modifications so that your animations
        //        can be made faster or slower depending on the machine you are working on
    	   rotate += 0.01f;
    	   
    	//wingflap 
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
    	   wingflap += 0.1f;
    	   }
    	   else
    	   {
    	   wingflap -= 0.1f;
    	   }
    	   
    	   
    	   
    	   advance1 += 0.01f;advance2 += 0.01f;advance3 += 0.01f;advance4 += 0.01f;advance5 += 0.01f;advance6 += 0.01f;advance7 += 0.01f;advance8 += 0.01f;advance9 += 0.01f;advance10 += 0.01f;
    	   
    	   
    }
    protected void renderScene()

    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
     //      It will probably call a number of other methods you will write.
    	
GL11.glLoadIdentity();
    	
        // Position the camera outside the shape
		GLU.gluLookAt(0, 10, 15,     /* Camera position */
				      0,4,0,          /* Target */
				      0, 1, 0         /* Up vector */ 
				      );
    	
    	
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
    	//drawBack();
    	drawDuck();
    }
    protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();

        //TODO: If it is appropriate for your scene, modify the camera's position and orientation here
        //        using a call to GL11.gluLookAt(...)
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }

    private void drawDuck()
    {
    	GL11.glPushMatrix();
        {
        	// disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
        	// enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,beakTexture.getTextureID());           

          //rotate to the same direction as the viewer is watching from
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            //GL11.glRotatef(rotate, 0.0f, 1.0f, 0.0f);
            
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
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(10f,-1.0f,advance);
            GL11.glScalef(75.0f, 1.0f, 80.25f);
            //GL11.glCallList(planeList);
            
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
    
    private void drawBack()
    {
    	// draw the back plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyNightTextures.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f,4.0f,-75.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 1.0f, 10.0f);
            //GL11.glCallList(planeList);
            
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
             
             /**
             // draw the top face:
             GL11.glBegin(GL11.GL_POLYGON);
             {
                 new Normal(t1.toVector(),t2.toVector(),z1.toVector()).submit();
                 
                 f1.submit();
                 t1.submit();                
                 t2.submit();
                 b1.submit();
                 b2.submit();
                 z2.submit();                
                 z1.submit();
             }
             GL11.glEnd();
             **/
             
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
             

             //side face of fins
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
             

             //side face of fins
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
             
             
         }
         GL11.glPopMatrix();
    }

private void drawTree()
{
	
}
}
