package dunghtph30405.example.nhom1_pro1121.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dunghtph30405.example.nhom1_pro1121.R;
import dunghtph30405.example.nhom1_pro1121.adapter.QuanLySanPhamAdapter;
import dunghtph30405.example.nhom1_pro1121.dao.SanPhamDAO;
import dunghtph30405.example.nhom1_pro1121.model.sanpham;

public class frg_quanly_sanpham_home extends Fragment {
    SanPhamDAO sanPhamDAO;
    ArrayList<sanpham> list;
    QuanLySanPhamAdapter quanLySanPhamAdapter;
    RecyclerView recyclerQuanliSP;

    public frg_quanly_sanpham_home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_quanly_sanpham_home, container, false);

        FloatingActionButton float_add_sp = view.findViewById(R.id.float_add_sp);
        recyclerQuanliSP = view.findViewById(R.id.recyclerView_quanli_sp);
        SearchView searchView = view.findViewById(R.id.search_quanly_sp);

        // Initialize DAO and load data
        sanPhamDAO = new SanPhamDAO(getContext());
        loadData();

        // Search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                quanLySanPhamAdapter.filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                quanLySanPhamAdapter.filterList(newText);
                return true;
            }
        });

        // Add new product button
        float_add_sp.setOnClickListener(v -> showdialogThem());

        return view;
    }

    public void loadData() {
        if (sanPhamDAO == null) {
            sanPhamDAO = new SanPhamDAO(getContext());
        }
        list = sanPhamDAO.selectSANPHAM();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        recyclerQuanliSP.setLayoutManager(gridLayoutManager);

        quanLySanPhamAdapter = new QuanLySanPhamAdapter(getContext(), list, sanPhamDAO);
        recyclerQuanliSP.setAdapter(quanLySanPhamAdapter);
    }

    public void showdialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_sanpham, null);
        builder.setView(view);

        // Initialize EditText fields
        EditText edt_tensp_add = view.findViewById(R.id.edt_tensp_add);
        EditText edt_giasp_add = view.findViewById(R.id.edt_giasp_add);
        EditText edt_thuonghieu_add = view.findViewById(R.id.edt_thuonghieu_add);
        EditText edt_xuatxu_add = view.findViewById(R.id.edt_xuatxu_add);
        EditText edt_kichthuocmanhinh_add = view.findViewById(R.id.edt_kichthuocmanhinh_add);
        EditText edt_mausac_add = view.findViewById(R.id.edt_mausac_add);
        EditText edt_trongluong_add = view.findViewById(R.id.edt_trongluong_add);
        EditText edt_chatlieu_add = view.findViewById(R.id.edt_chatlieu_add);
        EditText edt_cpu_add = view.findViewById(R.id.edt_cpu_add);
        EditText edt_ocung_add = view.findViewById(R.id.edt_ocung_add);
        EditText edt_ram_add = view.findViewById(R.id.edt_ram_add);
        EditText edt_rom_add = view.findViewById(R.id.edt_rom_add);
        EditText edt_card_add = view.findViewById(R.id.edt_card_add);
        EditText edt_tocdocpu_add = view.findViewById(R.id.edt_tocdocpu_add);
        EditText edt_congusb_add = view.findViewById(R.id.edt_congusb_add);
        EditText edt_vantay_add = view.findViewById(R.id.edt_vantay_add);
        EditText edt_hinhanh_add = view.findViewById(R.id.edt_hinhanh_add); // Added image field

        builder.setPositiveButton("Hủy", (dialog, which) -> {});

        builder.setNegativeButton("Thêm", (dialog, which) -> {
            try {
                String tensp_add = edt_tensp_add.getText().toString();
                String giasp_add = edt_giasp_add.getText().toString();
                String thuonghieu_add = edt_thuonghieu_add.getText().toString();
                String xuatxu_add = edt_xuatxu_add.getText().toString();
                String kichthuocmanhinh_add = edt_kichthuocmanhinh_add.getText().toString();
                String mausac_add = edt_mausac_add.getText().toString();
                String trongluong_add = edt_trongluong_add.getText().toString();
                String chatlieu_add = edt_chatlieu_add.getText().toString();
                String cpu_add = edt_cpu_add.getText().toString();
                String ocung_add = edt_ocung_add.getText().toString();
                String ram_add = edt_ram_add.getText().toString();
                String rom_add = edt_rom_add.getText().toString();
                String card_add = edt_card_add.getText().toString();
                String tocdocpu_add = edt_tocdocpu_add.getText().toString();
                String congusb_add = edt_congusb_add.getText().toString();
                String vantay_add = edt_vantay_add.getText().toString();
                String hinhanh_add = edt_hinhanh_add.getText().toString();

                if (tensp_add.isEmpty() || giasp_add.isEmpty() || thuonghieu_add.isEmpty() ||
                        xuatxu_add.isEmpty() || kichthuocmanhinh_add.isEmpty() || mausac_add.isEmpty() ||
                        trongluong_add.isEmpty() || chatlieu_add.isEmpty() || cpu_add.isEmpty() ||
                        ocung_add.isEmpty() || ram_add.isEmpty() || rom_add.isEmpty() ||
                        card_add.isEmpty() || tocdocpu_add.isEmpty() || congusb_add.isEmpty() ||
                        vantay_add.isEmpty() || hinhanh_add.isEmpty()) {
                    Toast.makeText(getContext(), "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = sanPhamDAO.addSP(tensp_add, Integer.parseInt(giasp_add), thuonghieu_add,
                            xuatxu_add, kichthuocmanhinh_add, mausac_add, trongluong_add, chatlieu_add,
                            cpu_add, ocung_add, ram_add, rom_add, card_add, tocdocpu_add, congusb_add,
                            vantay_add, hinhanh_add);

                    if (check) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Giá phải là số hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}