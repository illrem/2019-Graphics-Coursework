package Designer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise)
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
    protected void drawUnitShape()
    {
    	/**
    	GL11.glPushMatrix();
        {
            GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            new Cylinder().draw(0.25f, 0.25f, 1.5f, 10, 10);
        }
        GL11.glPopMatrix();
        **/
    	
    	
        GL11.glPushMatrix();
        {
        	/**
        	//make cone sideways
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            //draw cone/beak
            new Cylinder().draw(0.25f, 0.0f, 1.5f, 10, 10);
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
            //move along to right leg
            GL11.glTranslatef(-0.8f,0, 0);
            //draw right leg
            new Cylinder().draw(0.1f,0.1f, 0.75f, 10, 10);
            //move to end of body
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0.4f,-0.5f, 0.7f);
            //currently at the end of the body
            //draw end of body
            new Sphere().draw(0.75f,10,10);
            //rotate to the tail
            GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
            //place the cone for the tail
            new Cylinder().draw(0.75f,0, 1.25f, 10, 10);
            //return rotation
            GL11.glRotatef(-45.0f, 1.0f, 0.0f, 0.0f);
            //move to left wing
            GL11.glTranslatef(0.74f,-0.5f, -0.7f);
            
            //
            //GL11.glTranslatef(2,0, 0.375xzf);
            //
            //GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
            
            **/
            
            
            // the vertices for the wing

        	//the top face
            Vertex t1 = new Vertex( -0.5f,  0.5f,    0f);
            Vertex t2 = new Vertex( -0.5f,  0.5f,-0.75f);
            Vertex t3 = new Vertex( -0.75f, 0.5f,-1.75f);
            Vertex t4 = new Vertex(  0.25f, 0.5f,-1f   );
            Vertex t5 = new Vertex(  0.5f,  0.5f, 0f   );
            
            //the bottom face
            Vertex b1 = new Vertex( -0.5f,  0.3f,    0f);
            Vertex b2 = new Vertex( -0.5f,  0.3f,-0.75f);
            Vertex b3 = new Vertex( -0.75f, 0.3f,-1.75f);
            Vertex b4 = new Vertex(  0.25f, 0.3f,-1f   );
            Vertex b5 = new Vertex(  0.5f,  0.3f, 0f   );
            
            // draw the top face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(t1.toVector(),t2.toVector(),t3.toVector(),t4.toVector()).submit();
                
                t1.submit();
                t2.submit();
                t3.submit();
                t4.submit();
                t5.submit();
            }
            GL11.glEnd();
            
            //draw bottom face
            GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(b5.toVector(),b4.toVector(),b3.toVector(),b2.toVector()).submit();
                
                b5.submit();
                b4.submit();
                b3.submit();
                b2.submit();
                b1.submit();   			
    			
    		}
    		GL11.glEnd();

        }
        GL11.glPopMatrix();
        
        
    }
}
