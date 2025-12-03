/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CH.main;

import CH.controller.DatMonController;
import CH.controller.NhanVienController;
import CH.view.MainView;
import CH.dao.DBConnection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import CH.controller.HoaDonController;
import CH.controller.KhachHangController;
import CH.controller.ThucDonController;

public class Main {
    public static void main(String[] args) {
        
        DBConnection.initializeDatabase();
        
        SwingUtilities.invokeLater(()->{
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
            //1.Tao frame chinsh (Chua Sidebar + Layout)
            MainView mainView = new MainView();
            
            // 2. Lấy Panel Nhân Viên ra từ MainView
            // 3. Gắn Panel đó vào Controller để xử lý logic
            new NhanVienController(mainView.getNhanVienView());
            new KhachHangController(mainView.getKhachHangView());
            new HoaDonController(mainView.getHoaDonView());
            new DatMonController(mainView.getDatMonView());
            new ThucDonController(mainView.getThucDonView());   
            
            mainView.setVisible(true);
        });
        
    }
}
