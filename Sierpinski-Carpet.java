import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


class SierpinskiCarpet implements ActionListener
{

    int width = 1000;
    int height = 1000;
    
    Timer ticker;
    Panel panel;
    JButton button;

    boolean COLOR_MODE=false;

    public static void main(String[] args)
    {
        new SierpinskiCarpet();
    }

    public SierpinskiCarpet()
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width,height);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        panel = new Panel(width, height, COLOR_MODE);
        window.add(panel);

        button=new JButton("Sierpinski Carpet");
        button.addActionListener(this);

        ticker = new Timer(1000, this);
        ticker.start();

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ticker)
        {
            panel.updateDisplay();
        }
    }
}

class Panel extends JPanel
{
    int count;
    int width, height;
    boolean color_mode;

    public Panel(int width, int height, boolean color_mode)
    {
        count=0;
        this.width=width-100;
        this.height=height-100;
        this.color_mode=color_mode;
    }

    public void paintSponge(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int rep=count;
        while(rep>0)
        {
            double size=width/(Math.pow(3, rep));

            for(int i=0; i<Math.pow(3, rep); i++)
            {
                for(int j=0; j<Math.pow(3, rep); j++)
                {
                    if(i%3==1&&j%3==1)
                    {
                        if(color_mode)
                            g.setColor(new Color((int)(Math.random()*256),(int) (Math.random()*226),(int)(Math.random()*256)));
                        g2.fill(new Rectangle2D.Double(50+size*i,50+size*j, size, size));
                    }
                    g.setColor(Color.MAGENTA);
                    g2.draw(new Rectangle2D.Double(50+size*i,50+size*j, size, size));
                }
            }
            rep--;
        }
        count++;
    }

    public void updateDisplay()
    {
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        if(count<6)
            paintSponge(g);

    }

    public void drawCenteredBox(Graphics g, String text, Rectangle rect, Font font)
    {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}