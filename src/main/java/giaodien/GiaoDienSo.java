package giaodien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.text.DecimalFormat;
import thietke.CapNhat;

public class GiaoDienSo extends JPanel implements Observer {
    ButtonGroup nhom_cac_so;          
    JToggleButton[] nhom_so;
    Timer timer;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");
    
    // Căn chỉnh vị trí các số và định dạng cho chúng 
    
    public GiaoDienSo() {
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        
        JPanel can_chinh = new JPanel();
        can_chinh.setLayout(new BoxLayout(can_chinh, BoxLayout.Y_AXIS));
        add(can_chinh, BorderLayout.NORTH);

        JPanel khung_so = new JPanel();
        khung_so.setLayout(new BoxLayout(khung_so, BoxLayout.X_AXIS));
        khung_so.setBorder(BorderFactory.createTitledBorder(" Số "));
        can_chinh.add(khung_so);
        
        JPanel ds_so = new JPanel(new FlowLayout(FlowLayout.CENTER));
        khung_so.add(ds_so);
        

        nhom_cac_so = new ButtonGroup();
        nhom_so = new JToggleButton[9];
        
        for (int i = 0; i < 3; i++) {
            nhom_so[i] = new JToggleButton("" + (i+1));
            nhom_so[i].setPreferredSize(new Dimension(50, 50));
            nhom_cac_so.add(nhom_so[i]);
            nhom_so[i].setFont(font);
            nhom_so[i].setBackground(Color.ORANGE);
            ds_so.add(nhom_so[i]);
        }
        
        for (int i = 3; i < 6; i++) {
            nhom_so[i] = new JToggleButton("" + (i+1));
            nhom_so[i].setPreferredSize(new Dimension(50, 50));
            nhom_cac_so.add(nhom_so[i]);
            nhom_so[i].setFont(font);
            nhom_so[i].setBackground(Color.PINK);
            ds_so.add(nhom_so[i]);
        }
        for (int i = 6; i < 9; i++) {
            nhom_so[i] = new JToggleButton("" + (i+1));
            nhom_so[i].setPreferredSize(new Dimension(50, 50));
            nhom_cac_so.add(nhom_so[i]);
            nhom_so[i].setFont(font);
            nhom_so[i].setBackground(Color.YELLOW);
            ds_so.add(nhom_so[i]);
        }
        
        // Tạo bộ bấm thời gian(giới hạn 10p)
        
        JPanel time = new JPanel(new FlowLayout(FlowLayout.LEADING));
        time.setBorder(BorderFactory.createTitledBorder("Time"));
        can_chinh.add(time);

        JLabel dong_ho = new JLabel("");
        time.add(dong_ho);
        
        dong_ho.setText("00:00");
        second = 0;
        minute = 0;
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second++;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                dong_ho.setText(ddMinute + ":" + ddSecond);

                if(second == 60) {
                    second = 0;
                    minute++;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    dong_ho.setText(ddMinute + ":" + ddSecond);
                }
                if(minute == 10) {
                    timer.stop();
                    dong_ho.setText("Bạn đã hết thời gian!!!");
                }
            }
        });
        timer.start();
       
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((CapNhat)arg) {
            case game_moi:
                nhom_cac_so.clearSelection();
                break;
        }
    }

    // Xử lí sự kiện nút số 
    
    public void setDieuKhien(XuliMenu nut_dieu_khien) {
        for (int i = 0; i < 9; i++) {
            nhom_so[i].addActionListener(nut_dieu_khien);
        }
    }
}
