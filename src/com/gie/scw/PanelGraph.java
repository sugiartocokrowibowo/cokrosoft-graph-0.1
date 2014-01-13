package com.gie.scw;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;


public class PanelGraph extends JPanel{
	/**
	 * @author sugiarto cokrowibowo
	 * @author sugiartocokrowibowo@gmail.com
	 * @category Mathematics and Computer Science
	 * 
	 */
	
	//property	
	private final int MAX		= (int) Math.pow(2, 10);
	int MAXi					= MAX;
	
	public String[] label		= new String[MAX];
	public int[][] posisi		= new int[MAX][2];
	public int index			= 0;
	public int[][] adjacency	= new int[MAX][MAX];	
	
	double translateX	= 0;
	double translateY	= 0;
	double scale		= 1;
	
	public int status	= 0;
	
	public void setStatus(int status){
		this.status	= status;
	}
	
	public void clear(){
		repaint();
	}
	
	public Graph getGraph(){
		Graph graph	= null;
		
		return graph;
	}
	
	public PanelGraph() {
		super();
		Translasi t	= new Translasi();
		this.addMouseListener(t);
		this.addMouseMotionListener(t);
		this.addMouseWheelListener(t);		
		this.setBackground(Color.WHITE);
	}

	public void paint(Graphics g){
		super.paint(g);		
		Graphics2D g2d	= (Graphics2D)g;
		
		AffineTransform af	= new AffineTransform();
		af.translate(translateX, translateY);
		af.scale(scale, scale);
		g2d.setTransform(af);	
		
		//--------------------------------------		
		//draw grid
		float gridWidth=0;
		float[] dash={10,5,10,5};
		Stroke stroke1=new BasicStroke(gridWidth,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,dash,0);		
		for(int i=0;i<MAXi;i+=50){
			g2d.setStroke(stroke1);
			g2d.setColor(Color.GRAY);
			g2d.setComposite(AlphaComposite.SrcOver.derive(0.2F));
			g2d.drawLine(0, i, MAXi, i);
			g2d.drawLine(i, 0, i, MAXi);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(1));
			g2d.setComposite(AlphaComposite.SrcOver.derive(1.0F));
			g2d.drawLine(-4, i, 0, i);
			g2d.drawLine(i, -4, i, 0);
			g2d.setColor(Color.GRAY);
			g2d.setComposite(AlphaComposite.SrcOver.derive(1.0F));
			String si	= ""+(i)+"";
			g2d.drawString(si, (-8*si.length()-4), 5+i);
			if(i>0){
				g2d.drawString(si, i-((int)(8.00*(double)si.length()/2.00)), -4);
			}
		}
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.6F));
		for(int i=0;i<MAXi;i+=100){
			g2d.setStroke(stroke1);
			g2d.drawLine(0, i, MAXi, i);
			g2d.drawLine(i, 0, i, MAXi);
		}
		
		//draw edge
		if(index>0){
			for(int i=0;i<index;i++){
				for(int j=0;j<index;j++){
					if(adjacency[i][j]==1&&i!=j){
						int x1 = posisi[i][0];
						int y1 = posisi[i][1];
						int x2 = posisi[j][0];
						int y2 = posisi[j][1];
						g2d.setComposite(AlphaComposite.SrcOver.derive(1.0F));
						//Color c1	= new Color((x1%256),(x2%256),(0));
						Color c1	= Color.BLACK;
						g2d.setColor(c1);
						g2d.setStroke(new BasicStroke(1));
						g2d.drawLine(x1, y1, x2, y2);
					}
				}
			}
		}
		
		//draw vertex
		if(index>0){
			for(int i=0;i<index;i++){
				int x = posisi[i][0];
				int y = posisi[i][1];
				g2d.setComposite(AlphaComposite.SrcOver.derive(0.9F));
				g2d.setColor(Color.RED);
				g2d.fillOval(x-6, y-6, 12, 12);
				g2d.setStroke(new BasicStroke(3));
				g2d.setComposite(AlphaComposite.SrcOver.derive(1.0F));
				g2d.setColor(Color.WHITE);
				g2d.drawOval(x-6, y-6, 12, 12);
				g2d.setColor(Color.RED);
				g2d.setStroke(stroke1);
				g2d.setComposite(AlphaComposite.SrcOver.derive(1.0F));
				g2d.drawOval(x-8, y-8, 16, 16);
				g2d.setStroke(stroke1);
				g2d.setColor(Color.BLACK);
				g2d.drawString(label[i], x+14, y);				
			}
		}
		
		//draw rect
		g2d.setStroke(new BasicStroke(6));
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.2F));
		g2d.setColor(Color.blue);
		g2d.drawRect(0, 0, MAXi, MAXi);
		
	}
		
	private class Translasi implements MouseListener, MouseMotionListener, MouseWheelListener{
		int xLama;
		int yLama;	
		
		int radius	= 20;
		int original;
		int destination;
		
		public int whereAmI(int x, int y){
			int posisiku	= -1;
			for(int i=0;i<index;i++){
				int xo	= posisi[i][0];
				int yo	= posisi[i][1];
				if((Math.abs(xo-x)<=radius)&&(Math.abs(yo-y)<=radius)){
					posisiku=i;
					break;
				}
			}
			System.out.println("Posisiku: "+(posisiku));
			return posisiku;
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {			
				scale += (.1 * e.getWheelRotation());
				scale = Math.max(0.00001, scale); 
				repaint();
			}			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int xBaru	= e.getX()-xLama;
			int yBaru	= e.getY()-yLama;
			translateX	+= xBaru;
			translateY	+= yBaru;
			xLama	+= xBaru;
			yLama	+= yBaru;
			repaint();			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX();
            int Y = e.getY();
        	int titik_y=(int) Math.floor((Y-translateY)/(scale));
            int titik_x=(int) Math.floor((X-translateX)/(scale));            
            if(titik_x<0){titik_x=0;}
            if(titik_y<0){titik_y=0;}
            
            if(titik_x>MAXi){titik_x=MAXi;}
            if(titik_y>MAXi){titik_y=MAXi;}
			
            //switch
            if(status==0){
            	posisi[index][0]=titik_x;
    			posisi[index][1]=titik_y;        	
    			//label[index]	= "V"+(1+index)+" ("+(titik_x)+","+(titik_y)+")";        	
    			label[index]	= "V"+(1+index);
    			index++;
    			repaint();
            }else if(status==1){
            	int posisiku	= whereAmI(titik_x, titik_y);
            	if(posisiku>-1){
            		original	= posisiku;
            		status		= 2;
            	}
            }else if(status==2){
            	int posisiku	= whereAmI(titik_x, titik_y);
            	if(posisiku>-1){
            		destination							= posisiku;
            		adjacency[original][destination]	= 1;
            		adjacency[destination][original]	= 1;
            		status								= 1;
        			repaint();
            	}
            }  
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			xLama	= e.getX();
			yLama	= e.getY();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
		

}
