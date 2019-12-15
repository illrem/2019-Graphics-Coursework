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
		new ShapeDesigner().run( WINDOWED, "Designer", 0.05f);
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
            new Cylinder().draw(0.25f, 0.0f, 0.75f, 10, 10);
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
            GL11.glTranslatef(0.7f,-0.5f, -0.7f);
            //rotate wing
            GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
            
            
            
            
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
    		//side1 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t1.toVector(),b1.toVector(),t2.toVector()).submit();
    			
    			t1.submit();
    			b1.submit();
    			t2.submit();
    		}
    		GL11.glEnd();
    		//side 2 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(b1.toVector(),b2.toVector(),t2.toVector()).submit();
    			
    			b1.submit();
    			b2.submit();
    			t2.submit();
    		}
    		GL11.glEnd();
    		//side 3 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t2.toVector(),b2.toVector(),t3.toVector()).submit();
    			
    			t2.submit();
    			b2.submit();
    			t3.submit();
    		}
    		GL11.glEnd();
    		//side 4 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(b2.toVector(),b3.toVector(),t3.toVector()).submit();
    			
    			b2.submit();
    			b3.submit();
    			t3.submit();
    		}
    		GL11.glEnd();
    		//side 5 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t3.toVector(),b3.toVector(),t4.toVector()).submit();
    			
    			t3.submit();
    			b3.submit();
    			t4.submit();
    		}
    		GL11.glEnd();
    		//side 6 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(b3.toVector(),b4.toVector(),t4.toVector()).submit();
    			
    			b3.submit();
    			b4.submit();
    			t4.submit();
    		}
    		GL11.glEnd();
    		//side 7 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t4.toVector(),b4.toVector(),b5.toVector()).submit();
    			
    			t4.submit();
    			b4.submit();
    			b5.submit();
    		}
    		GL11.glEnd();
    		//side 8 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t4.toVector(),b5.toVector(),t5.toVector()).submit();
    			
    			t4.submit();
    			b5.submit();
    			t5.submit();
    		}
    		GL11.glEnd();
    		//side 9 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t5.toVector(),b5.toVector(),b1.toVector()).submit();
    			
    			t5.submit();
    			b5.submit();
    			b1.submit();
    		}
    		GL11.glEnd();
    		//side 10 face
    		GL11.glBegin(GL11.GL_POLYGON);
    		{
    			new Normal(t1.toVector(),b1.toVector(),t5.toVector()).submit();
    			
    			t1.submit();
    			b1.submit();
    			t5.submit();
    		}
    		GL11.glEnd();
    		////////////////////////////////////////////////////////
    		//move to right wing
            GL11.glTranslatef(0,0, 1.4f);

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
    		**/
    	
    	
        	
        	
        	
    		
    		
        }
        GL11.glPopMatrix();
        
        
    }
}
