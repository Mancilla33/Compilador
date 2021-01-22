/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author man3a
 */
class MyScrollBarUI extends BasicScrollBarUI {
  private final Dimension d = new Dimension();

  @Override
  protected JButton createDecreaseButton(int orientation) {
    return new JButton() {
      @Override
      public Dimension getPreferredSize() {
        return d;
      }
    };
  }

  @Override
  protected JButton createIncreaseButton(int orientation) {
    return new JButton() {
      @Override
      public Dimension getPreferredSize() {
        return d;
      }
    };
  }

  @Override
  protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    Graphics2D g2 = (Graphics2D) g.create();
   
    g2.setPaint(new Color(80,80,80));
    g2.fillRoundRect(r.x, r.y, r.width, r.height,0,0);
    g2.setPaint(new Color(80,80,80));
    g2.drawRoundRect(r.x, r.y, r.width, r.height,0,0);
    g2.dispose();
  }

  @Override
  protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    Color color =new Color(55,55,55);
    JScrollBar sb = (JScrollBar) c;
    g2.setPaint(color);
    g2.fillRoundRect(r.x, r.y, r.width, r.height, 20, 20);
    if (!sb.isEnabled() || r.width > r.height) {
      return;
    } else if (isDragging) {
      g2.setPaint(new Color(0,168,255));
      g2.fillOval(r.x+4, r.y+8, r.width-8, r.width-8);
    } else if (isThumbRollover()) {
     g2.setPaint(new Color(0,168,255));
     g2.fillOval(r.x+3, r.y+6, r.width-6, r.width-6);
    } else {
      
    }
    
    
    
    g2.dispose();
  }

  @Override
  protected void setThumbBounds(int x, int y, int width, int height) {
    super.setThumbBounds(x, y, width, height);
    scrollbar.repaint();
  }
}
