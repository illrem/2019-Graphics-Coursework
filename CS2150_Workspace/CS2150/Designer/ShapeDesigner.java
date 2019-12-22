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
            Vertex lf1 = new Vertex(  0f,  0f,    0f);  
            Vertex lf2 = new Vertex(  0f,  0f,    0f);  
            Vertex lf3 = new Vertex(  0f,  0f,    0f);            
            Vertex lf4 = new Vertex(  0f,  0f,    0f);
            
            //right vectors            
            Vertex r1 = new Vertex(  -0.25f,  -0.1f,    0.02f);  
            Vertex r2 = new Vertex(  -0.35f,  -0.1f,    0.02f);  
            Vertex r3 = new Vertex(  -0.25f,  -0.1f,   -0.02f);   
            Vertex r4 = new Vertex(  -0.35f,  -0.1f,   -0.02f); 
            Vertex rf1 = new Vertex(  0f,  0f,    0f);  
            Vertex rf2 = new Vertex(  0f,  0f,    0f);  
            Vertex rf3 = new Vertex(  0f,  0f,    0f);            
            Vertex rf4 = new Vertex(  0f,  0f,    0f);
            
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
            
        }
        GL11.glPopMatrix();
        
        
    }
}
