package CH.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import CH.model.HoaDon;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoaDonView extends JPanel {
    private final Color TEAL_COLOR = new Color(0, 77, 77);
    
    // Components
    private JTextField txtMaHD, txtNhanVien, txtKhachHang;
    private JDateChooser txtNgayLap;
    private JTable tableHoaDon;
    private DefaultTableModel tableModel;
    
    // Buttons
    private JButton btnXemChiTiet; // Nút to bên phải
    private JButton btnThem, btnSua, btnXoa, btnReset; // Các nút chức năng mới

    public HoaDonView() {
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        // --- TOP PANEL (Chứa Form + Tất cả các nút) ---
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(TEAL_COLOR);
        pnlTop.setBorder(new EmptyBorder(10, 20, 10, 20));

        // 1. LEFT SIDE: Inputs + CRUD Buttons
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(TEAL_COLOR);

        // a. Form Inputs
        JPanel pnlInputs = new JPanel(new GridBagLayout());
        pnlInputs.setBackground(TEAL_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addInput(pnlInputs, gbc, 0, 0, "Mã hóa đơn", txtMaHD = new JTextField(15));
        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new Color(230, 230, 230));
        txtMaHD.setText("Tự động sinh");
        
        
        addInput(pnlInputs, gbc, 0, 1, "Nhân viên lập", txtNhanVien = new JTextField(15));
        addInput(pnlInputs, gbc, 0, 2, "Khách hàng", txtKhachHang = new JTextField(15));
        
        // JDateChooser cho Ngày lập
        txtNgayLap = new JDateChooser();
        txtNgayLap.setDateFormatString("dd/MM/yyyy");
        txtNgayLap.setPreferredSize(new Dimension(150, 25));
        addInput(pnlInputs, gbc, 0, 3, "Ngày lập", txtNgayLap);

        pnlLeft.add(pnlInputs, BorderLayout.CENTER);

        // b. CRUD Buttons (Thêm, Sửa, Xóa, Reset) - Nằm dưới Form
        JPanel pnlCRUD = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlCRUD.setBackground(TEAL_COLOR);
        pnlCRUD.setBorder(new EmptyBorder(10, 0, 0, 0)); // Cách form một chút

        btnThem = createStyledButton("Thêm");
        btnSua = createStyledButton("Sửa");
        btnXoa = createStyledButton("Xóa");
        btnReset = createStyledButton("Reset");

        pnlCRUD.add(btnThem);
        pnlCRUD.add(btnSua);
        pnlCRUD.add(btnXoa);
        pnlCRUD.add(btnReset);

        pnlLeft.add(pnlCRUD, BorderLayout.SOUTH);

        // 2. RIGHT SIDE: Nút Xem chi tiết (To)
        JPanel pnlRight = new JPanel(new GridBagLayout()); // Dùng GridBag để căn giữa nút theo chiều dọc
        pnlRight.setBackground(TEAL_COLOR);
        
        btnXemChiTiet = new JButton("<html><center>Xem chi tiết<br>hóa đơn</center></html>");
        btnXemChiTiet.setPreferredSize(new Dimension(120, 60));
        btnXemChiTiet.setBackground(Color.WHITE);
        btnXemChiTiet.setForeground(TEAL_COLOR); // Chữ màu xanh cho nổi
        btnXemChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pnlRight.add(btnXemChiTiet);

        // Thêm Left và Right vào Top Panel
        pnlTop.add(pnlLeft, BorderLayout.CENTER);
        pnlTop.add(pnlRight, BorderLayout.EAST);

        add(pnlTop, BorderLayout.NORTH);

        // --- CENTER TABLE ---
        String[] columns = {"Mã hóa đơn", "Nhân viên lập", "Tên Khách hàng", "Ngày lập", "Tổng tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        tableHoaDon = new JTable(tableModel);
        tableHoaDon.setRowHeight(30);
        tableHoaDon.getTableHeader().setBackground(new Color(230, 230, 230));
        tableHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    // --- Helpers ---
    private void addInput(JPanel p, GridBagConstraints gbc, int x, int y, String label, Component cmp) {
        gbc.gridx = x; gbc.gridy = y; gbc.weightx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        p.add(lbl, gbc);
        
        gbc.gridx = x + 1; gbc.weightx = 1.0;
        if (cmp.getPreferredSize() == null || cmp.getPreferredSize().width == 0) {
             cmp.setPreferredSize(new Dimension(150, 25));
        }
        p.add(cmp, gbc);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(80, 30));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- Public Methods for Controller ---
    
    // 1. Lấy dữ liệu từ Form (Nếu sau này bạn làm chức năng Thêm hóa đơn)
    public HoaDon getHoaDonInfo() {
        String strNgay = "";
        if (txtNgayLap.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            strNgay = sdf.format(txtNgayLap.getDate());
        }
        // Lưu ý: Tổng tiền mặc định là 0 khi lấy từ form nhập tay
        return new HoaDon(txtMaHD.getText(), txtNhanVien.getText(), txtKhachHang.getText(), strNgay, 0);
    }

    // 2. Đổ dữ liệu lên form (khi click bảng)
    public void fillForm(HoaDon hd) {
        txtMaHD.setText(hd.getMaHD());
        txtNhanVien.setText(hd.getTenNV());
        txtKhachHang.setText(hd.getTenKH());
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(hd.getNgayLap());
            txtNgayLap.setDate(date);
        } catch (Exception e) {
            txtNgayLap.setDate(null);
        }
    }
    
    // 3. Xóa trắng form
    public void clearForm() {
        txtMaHD.setText("Tự động sinh");
        txtNhanVien.setText("");
        txtKhachHang.setText("");
        txtNgayLap.setDate(null);
    }

    // 4. Các phương thức bảng và nút
    public void addRow(HoaDon hd) { tableModel.addRow(hd.toObjectArray()); }
    public void clearTable() { tableModel.setRowCount(0); }
    public int getSelectedRow() { return tableHoaDon.getSelectedRow(); }
    public JTable getTable() { return tableHoaDon; }
    
    // Listeners
    public void addXemChiTietListener(ActionListener al) { btnXemChiTiet.addActionListener(al); }
    public void addThemListener(ActionListener al) { btnThem.addActionListener(al); }
    public void addSuaListener(ActionListener al) { btnSua.addActionListener(al); }
    public void addXoaListener(ActionListener al) { btnXoa.addActionListener(al); }
    public void addResetListener(ActionListener al) { btnReset.addActionListener(al); }
}