package giaodien;

import java.awt.*;
import javax.swing.*;

public class ThietLap extends JLabel {
    private int x;      
    private int y;     
    
    // Căn chỉnh và định dạng cho khung của game
    
    public ThietLap(int x, int y) {
        super("", CENTER);
        this.x = x;
        this.y = y;
        
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        setOpaque(true);
    }
    
    // Tạo màu các số hiển thị trong khung
    
    public void setNumber(int so, boolean nhap) {
        setForeground(nhap ? Color.BLUE : Color.BLACK);
        setText(so > 0 ? so + "" : "");
    }

    public int getFX() {
        return x;
    }

    public int getFY() {
        return y;
    }
    
}
