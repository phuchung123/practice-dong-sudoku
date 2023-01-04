package giaodien;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import thietke.VanHanh;
import thietke.CapNhat;
import dieukhien.DieuKhien;

public class GiaoDien extends JPanel implements Observer {
     
    // Hằng số màu cho các ô trợ giúp
    
    private Color Mau = new Color(51,204,255);
    private ThietLap[][] thietlap;  
    private JPanel[][] bocuc;      
    
    // Xây dựng bố cục và thiết lập
     
    public GiaoDien() {
        
        super(new GridLayout(3, 3));

        bocuc = new JPanel[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                bocuc[y][x] = new JPanel(new GridLayout(3, 3));
                bocuc[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(bocuc[y][x]);
            }
        }

        thietlap = new ThietLap[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                thietlap[y][x] = new ThietLap(x, y);
                bocuc[y / 3][x / 3].add(thietlap[y][x]);
            }
        }
    }
    
    // Điều khiển
    
    public void setDieuKhien(DieuKhien dieukhien) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                bocuc[y][x].addMouseListener(dieukhien);
            }
        }
    }

    // Gửi thông báo cập nhật
    
    @Override
    public void update(Observable o, Object arg) {
        switch ((CapNhat)arg) {
            case game_moi:
                setGame((VanHanh)o);
                break;
            case kiem_tra:
                 setKiemTra((VanHanh)o);
                break;
            case tro_giup:
                setTroGiup((VanHanh)o);
            case nhap_so:
                 break;
        }
    }
    
    // Làm nổi bật các số trong ô đã chọn để được trợ giúp
    
    private void setTroGiup(VanHanh game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                thietlap[y][x].setBackground(Color.WHITE);
                if (game.troGiup() && game.chonSoGoiY(x, y))
                    thietlap[y][x].setBackground(Mau);
            }
        }
    }

    // Tạo màu nền cho tất cả các ô của game
    
    public void setGame(VanHanh game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                thietlap[y][x].setBackground(Color.WHITE);
                thietlap[y][x].setNumber(game.getSo(x, y), false);
            }
        }
    }

    
    private void setKiemTra(VanHanh game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                thietlap[y][x].setBackground(Color.WHITE);
                if (thietlap[y][x].getForeground().equals(Color.BLUE)) {
                    thietlap[y][x].setBackground(game.tinhHopLe(x, y) ? Color.GREEN : Color.RED);
                }
            }
        }
     }
          
}
