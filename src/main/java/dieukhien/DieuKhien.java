package dieukhien;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import giaodien.ThietLap;
import thietke.VanHanh;
import giaodien.GiaoDien;

public class DieuKhien implements MouseListener {
    private VanHanh game;           

    public DieuKhien(GiaoDien gameLayout, VanHanh game) {
        this.game = game;
    }

    //xử lí thao tác chuột
    
    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof ThietLap f) {
            int x = f.getFX();
            int y = f.getFY();

            if (e.getButton() == MouseEvent.BUTTON1 && (game.getSo(x, y) == 0 || f.getForeground().equals(Color.BLUE))) {
                int so = game.getChonSo();
                if (so == -1) {
                    return;
                }
                game.setSo(x, y, so);
                f.setNumber(so, true);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !f.getForeground().equals(Color.BLACK)) {
                game.setSo(x, y, 0);
                f.setNumber(0, false);
            }
           
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    { }
    @Override
    public void mouseEntered(MouseEvent e) 
    { }
    @Override
    public void mouseExited(MouseEvent e) 
    { }
    @Override
    public void mouseReleased(MouseEvent e) 
    { }
}
