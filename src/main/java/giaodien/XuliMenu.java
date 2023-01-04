package giaodien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import thietke.VanHanh;

// Xử lí sự kiện của thanh công cụ

public class XuliMenu implements ActionListener {
    private VanHanh game;

    public XuliMenu(VanHanh game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Game mới":
                game.newGame();
                break;
            case "Kiểm tra":
                game.kiemTra();
                break;
            case "Trợ giúp":
                game.troGiup(true);
                break;
            case "Thoát":
                System.exit(0);
                break;
            default:
                game.setChonSo(Integer.parseInt(e.getActionCommand()));
                break;
        }
    }
}
