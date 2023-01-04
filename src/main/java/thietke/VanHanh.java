package thietke;

import java.util.*;

public final class VanHanh extends Observable{
    private int[][] do_kho;
    private int[][] game;
    private boolean[][] kiem_tra;
    private int nhap_so;
    private boolean tro_giup = false;
    
    public VanHanh(){
        newGame();
        kiem_tra = new boolean[9][9];
    }
    
    public void newGame(){
        do_kho = newGame(new int[9][9]);
        game = taoGame(saoChep(do_kho));
        setChanged();
        notifyObservers(CapNhat.game_moi);
    }
    
    // kiểm tra các số ở hàng ngang và hàng dọc
    private boolean hangDoc(int[][] game, int doc, int so) {
        for (int x = 0; x < 9; x++) {
            if (game[doc][x] == so) {
                return false;
            }
        }
        return true;
    }
    
    private boolean hangNgang(int[][] game, int ngang, int so) {
        for (int y = 0; y < 9; y++) {
            if (game[y][ngang] == so) {
                return false;
            }
        }
        return true;
    }
    
    // kiểm tra các số từng khối 3x3
    private boolean khoi(int[][] game, int x, int y, int so){
        int x1, y1;
        if(x < 3){
            x = 0;
        } else {
            if(x < 6){
                x = 3;
            } else {
                x = 6;
            }
        }
        x1 = x;
        
        if(y < 3){
            y = 0;
        } else {
            if(y < 6){
                y = 3;
            } else{
                y = 6;
            }
        }
        y1 = y;
        
        for(int i = x1; i < x1 + 3; i++){
            for(int j = y1; j < y1 + 3; j++){
                if(game[i][j] == so)
                    return false;
            }
        }
        return true;
    }
    
    
    
    
   
    
    
    
    
    //số tiếp theo
    private int getSoTiepTheo(int[][] game, int x, int y, List<Integer> so){
        while(!so.isEmpty()){
            int so1 = so.remove(0);
            if (hangDoc(game, x, so1) && hangNgang(game, y, so1) && khoi(game, x, y, so1)){
                return so1;
            }
        }
        return -2;
    }
    
    
    // tạo bảng và tạo game
    private int[][] taoBang(int[][] game, int chi_so){
        if(chi_so > 80) // chỉ số ô > 9 thì return về game  
            return game;
        
        int x = chi_so % 9; 
        int y = chi_so / 9;
        
        List<Integer> so = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            so.add(i);
        }
        Collections.shuffle(so);
        
        while(!so.isEmpty()){
            int so1 = getSoTiepTheo(game, x, y, so);
            if(so1 == -2)
                return null;
            
            game[x][y] = so1;
            int[][] tmp = taoBang(game, chi_so + 1);
            if(tmp != null) return tmp;
            game[x][y] = 0;
        }
        
        return null;
    }
    
    private int[][] newGame(int[][] game){
        return game = taoBang(new int[9][9], 0);
        
    }
    
    private int[][] taoGame(int[][] game){
        List<Integer> vi_tri = new ArrayList<>();
        for(int i = 0; i < 81; i++){
            vi_tri.add(i);
        }
        
        Collections.shuffle(vi_tri);
        return taoGame(game, vi_tri);
    }
    
    private int[][] taoGame(int[][] game, List<Integer> vi_tri){
        while(!vi_tri.isEmpty()){
            int vi_tri_1, x, y, t;
            vi_tri_1 = vi_tri.remove(0);
            x = vi_tri_1 % 9;
            y = vi_tri_1 / 9;
            t = game[x][y];
            game[x][y] = 0;
            
            if(!kiemTra(game)){
                game[x][y] = t;
            }
        }
        return game;
    }
    
    
    
    
    
    //chọn số để nhập 
    public void setChonSo(int nhap_so){
        this.nhap_so = nhap_so;
        setChanged();
        notifyObservers(CapNhat.nhap_so);
    }
    
    public int getChonSo(){
        return nhap_so;
    }
    
    public void setSo(int x, int y, int so){
        game[x][y] = so;
    }
    
    public int getSo(int x, int y){
        return game[x][y];
    }
    
    
    
    
    // trợ giúp
    public void troGiup(boolean tro_giup){
        this.tro_giup = tro_giup;
        setChanged();
        notifyObservers(CapNhat.tro_giup);
    }
    
    public boolean troGiup(){
        return tro_giup;
    }
    
    public boolean chonSoGoiY(int x, int y) {
        return do_kho[x][y] == nhap_so ;
    }
    
    
    
    //Kt tính hợp lệ
    private boolean ktHopLe(int[][] game, int chi_so, int[] t){
        if(chi_so > 80){
            return ++t[0] == 1;
        }
        int x, y; 
        x = chi_so % 9;
        y = chi_so / 9;
        
        if(game[x][y] == 0){
            List<Integer> so = new ArrayList<>();
            for(int i = 1; i < 10; i++){
                so.add(i);
            }
            
            
            while(!so.isEmpty()){
                int so1 = getSoTiepTheo(game, x, y ,so);
                if(so1 == -2){
                    break;
                }
                game[x][y] = so1;
                
                if(!ktHopLe(game, chi_so + 1, t)){
                    game[x][y] = 0;
                    return false;
                }
                game[x][y] = 0;
                
            }
        } else if(!ktHopLe(game, chi_so + 1, t)){
            return false;
            }
        
        return true;
    }
    
    
    public boolean kiemTra(int[][] game){
        return ktHopLe(game, 0, new int[] {0});
    }
    
    public void kiemTra(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(game[i][j] == do_kho[i][j]){
                    kiem_tra[i][j] = true;
                } else {
                    kiem_tra[i][j] = false;
                }
            }
        }
        setChanged();
        notifyObservers(CapNhat.kiem_tra);
        
    }
    
    public boolean tinhHopLe(int x, int y){
        return kiem_tra[x][y];
    }
    
    
    
    
    // sao chép để tạo game mới
    private int[][] saoChep(int[][] game){
        int[][] sao_chep = new int[9][9];
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                sao_chep[y][x] = game[y][x];
            }
        }
        return sao_chep;
    }
    
}
