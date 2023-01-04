package giaodien;

import dieukhien.DieuKhien;
import java.awt.*;
import javax.swing.*;
import thietke.VanHanh;

public class Sudoku extends JFrame {
    
    public Sudoku() {
        
        //  Tạo cửa sổ và tên game
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setTitle("Game Sudoku");
        VanHanh game = new VanHanh();
        
        // Tạo thanh công cụ và giao diện
        
        JMenuBar menuBar = new JMenuBar();
        JMenu game1 = new JMenu("Game");
        JMenu congcu = new JMenu("Công cụ");
        JMenuItem gamemoi = new JMenuItem("Game mới");
        JMenuItem check = new JMenuItem("Kiểm tra");
        JMenuItem help = new JMenuItem("Trợ giúp");
        JMenuItem exit = new JMenuItem("Thoát");

        XuliMenu chuc_nang = new XuliMenu(game);
        GiaoDienSo buttonPanel = new GiaoDienSo();
        buttonPanel.setDieuKhien(chuc_nang);
        add(buttonPanel, BorderLayout.SOUTH);

        GiaoDien sudokuPanel = new GiaoDien();
        DieuKhien sudokuController = new DieuKhien(sudokuPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setDieuKhien(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);

        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);
        
        game1.add(gamemoi);
        game1.add(exit);
        congcu.add(check);
        congcu.add(help);
                
        gamemoi.addActionListener(chuc_nang);
        exit.addActionListener(chuc_nang);
        check.addActionListener(chuc_nang);
        help.addActionListener(chuc_nang);
               
        menuBar.add(game1);
        menuBar.add(congcu);
        
        this.setJMenuBar(menuBar);

        pack();
        setVisible(true);        
    }
    
    public static void main(String[] args) {
        new Sudoku();
    }
}
