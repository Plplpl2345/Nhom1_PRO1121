package dunghtph30405.example.nhom1_pro1121.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dunghtph30405.example.nhom1_pro1121.R;
import dunghtph30405.example.nhom1_pro1121.dao.SanPhamDAO;
import dunghtph30405.example.nhom1_pro1121.model.sanpham;
import dunghtph30405.example.nhom1_pro1121.util.Amount;

public class QuanLyGamingAdapter extends RecyclerView.Adapter<QuanLyGamingAdapter.ViewHolder> {
    private Context context;
    private ArrayList<sanpham> list;
    private ArrayList<sanpham> fullList;
    private SanPhamDAO sanPhamDAO;

    public QuanLyGamingAdapter(Context context, ArrayList<sanpham> list, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.list = list;
        this.fullList = new ArrayList<>(list);
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_admin_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sanpham product = list.get(position);
        holder.txt_tensp.setText(product.getTensp());
        holder.txt_gia.setText("Giá: " + Amount.moneyFormat(product.getGia()));

        // Load image using Glide if available
        if (product.getHinhanh() != null && !product.getHinhanh().isEmpty()) {
            Glide.with(context)
                    .load(product.getHinhanh())
                    .into(holder.img_anhsp_home_ql);
        }

        holder.txt_xemthem.setOnClickListener(view -> showdialogXemThem(product));

        holder.btn_delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.canhbao);
            builder.setMessage("Bạn chắc chắn muốn xóa?");
            builder.setPositiveButton("YES", (dialog, which) -> {
                boolean check = sanPhamDAO.deleteGAM(product.getMasp());
                if (check) {
                    loadData();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("NO", (dialog, which) ->
                    Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show());
            builder.create().show();
        });

        holder.btn_update.setOnClickListener(view -> dialogUpdate(product));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(String query) {
        query = query.toLowerCase();
        list.clear();

        if (query.isEmpty()) {
            list.addAll(fullList);
        } else {
            for (sanpham sp : fullList) {
                if (sp.getTensp().toLowerCase().contains(query)) {
                    list.add(sp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tensp, txt_gia, txt_xemthem;
        Button btn_delete, btn_update;
        ImageView img_anhsp_home_ql;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_anhsp_home_ql = itemView.findViewById(R.id.img_anhsp_home_ql);
            txt_tensp = itemView.findViewById(R.id.txt_tensp_home_ql);
            txt_gia = itemView.findViewById(R.id.txt_giasp_home_ql);
            txt_xemthem = itemView.findViewById(R.id.txt_xemthem_home_ql);
            btn_delete = itemView.findViewById(R.id.btn_deletesp_ql);
            btn_update = itemView.findViewById(R.id.btn_updatesp_ql);
        }
    }

    public void loadData() {
        list.clear();
        list.addAll(sanPhamDAO.selectGAMING());
        fullList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    private void showdialogXemThem(sanpham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_chitiet_sanpham, null);
        builder.setView(view);

        // Initialize views
        ImageView img_anhsp_chitiet = view.findViewById(R.id.img_anhsp_chitiet);
        TextView txt_tensp_chitiet = view.findViewById(R.id.txt_tensp_chitiet);
        TextView txt_giasp_chitiet = view.findViewById(R.id.txt_giasp_chitiet);
        TextView txt_thuonghieu_chtiet = view.findViewById(R.id.txt_thuonghieu_chtiet);
        TextView txt_xuatxu_chtiet = view.findViewById(R.id.txt_xuatxu_chtiet);
        TextView txt_kichthuoc_chitiet = view.findViewById(R.id.txt_kichthuoc_chitiet);
        TextView txt_trongluong_chitiet = view.findViewById(R.id.txt_trongluong_chitiet);
        TextView txt_mausac_chitiet = view.findViewById(R.id.txt_mausac_chitiet);
        TextView txt_chatlieu_chitiet = view.findViewById(R.id.txt_chatlieu_chitiet);
        TextView txt_cpu_chitiet = view.findViewById(R.id.txt_cpu_chitiet);
        TextView txt_ram_chitiet = view.findViewById(R.id.txt_ram_chitiet);
        TextView txt_card_chitiet = view.findViewById(R.id.txt_card_chitiet);
        TextView txt_congUSB_chitiet = view.findViewById(R.id.txt_congUSB_chitiet);
        TextView txt_ocung_chitiet = view.findViewById(R.id.txt_ocung_chitiet);
        TextView txt_rom_chitiet = view.findViewById(R.id.txt_rom_chitiet);
        TextView tocdoCPU_chitiet = view.findViewById(R.id.tocdoCPU_chitiet);
        TextView txt_vantay_chitiet = view.findViewById(R.id.txt_vantay_chitiet);

        // Set data
        Glide.with(context).load(sanPham.getHinhanh()).centerCrop().into(img_anhsp_chitiet);
        txt_tensp_chitiet.setText("Tên: " + sanPham.getTensp());
        txt_giasp_chitiet.setText("Giá: " + Amount.moneyFormat(sanPham.getGia()));
        txt_thuonghieu_chtiet.setText("Thương hiệu: " + sanPham.getThuonghieu());
        txt_xuatxu_chtiet.setText("Xuất xứ: " + sanPham.getXuatxu());
        txt_kichthuoc_chitiet.setText("Kích thước: " + sanPham.getKichthuocmanhinh());
        txt_trongluong_chitiet.setText("Trọng lượng: " + sanPham.getTrongluong());
        txt_mausac_chitiet.setText("Màu sắc: " + sanPham.getMausac());
        txt_chatlieu_chitiet.setText("Chất liệu: " + sanPham.getChatlieu());
        txt_cpu_chitiet.setText("CPU: " + sanPham.getCpu());
        txt_ram_chitiet.setText("RAM: " + sanPham.getRam());
        txt_card_chitiet.setText("Card: " + sanPham.getCard());
        txt_congUSB_chitiet.setText("USB: " + sanPham.getCongusb());
        txt_ocung_chitiet.setText("Ổ cứng: " + sanPham.getOcung());
        txt_rom_chitiet.setText("ROM: " + sanPham.getRom());
        tocdoCPU_chitiet.setText("Tốc độ: " + sanPham.getTocdocpu());
        txt_vantay_chitiet.setText("Vân tay: " + sanPham.getVantay());

        builder.setPositiveButton("Đóng", (dialog, which) -> {});
        builder.create().show();
    }

    public void dialogUpdate(sanpham sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_update_sanpham, null);
        builder.setView(view);

        // Initialize views
        EditText edt_tensp_ud = view.findViewById(R.id.edt_tensp_ud);
        EditText edt_giasp_ud = view.findViewById(R.id.edt_giasp_ud);
        EditText edt_thuonghieu_ud = view.findViewById(R.id.edt_thuonghieu_ud);
        EditText edt_xuatxu_ud = view.findViewById(R.id.edt_xuatxu_ud);
        EditText edt_kichthuocmanhinh_ud = view.findViewById(R.id.edt_kichthuocmanhinh_ud);
        EditText edt_mausac_ud = view.findViewById(R.id.edt_mausac_ud);
        EditText edt_trongluong_ud = view.findViewById(R.id.edt_trongluong_ud);
        EditText edt_chatlieu_ud = view.findViewById(R.id.edt_chatlieu_ud);
        EditText edt_cpu_ud = view.findViewById(R.id.edt_cpu_ud);
        EditText edt_ocung_ud = view.findViewById(R.id.edt_ocung_ud);
        EditText edt_ram_ud = view.findViewById(R.id.edt_ram_ud);
        EditText edt_rom_ud = view.findViewById(R.id.edt_rom_ud);
        EditText edt_card_ud = view.findViewById(R.id.edt_card_ud);
        EditText edt_tocdocpu_ud = view.findViewById(R.id.edt_tocdocpu_ud);
        EditText edt_congusb_ud = view.findViewById(R.id.edt_congusb_ud);
        EditText edt_vantay_ud = view.findViewById(R.id.edt_vantay_ud);
        EditText edt_hinhanh_ud = view.findViewById(R.id.edt_hinhanh_ud); // Added missing field

        // Set initial values
        edt_tensp_ud.setText(sp.getTensp());
        edt_giasp_ud.setText(String.valueOf(sp.getGia()));
        edt_thuonghieu_ud.setText(sp.getThuonghieu());
        edt_xuatxu_ud.setText(sp.getXuatxu());
        edt_kichthuocmanhinh_ud.setText(sp.getKichthuocmanhinh());
        edt_mausac_ud.setText(sp.getMausac());
        edt_trongluong_ud.setText(sp.getTrongluong());
        edt_chatlieu_ud.setText(sp.getChatlieu());
        edt_cpu_ud.setText(sp.getCpu());
        edt_ocung_ud.setText(sp.getOcung());
        edt_ram_ud.setText(sp.getRam());
        edt_rom_ud.setText(sp.getRom());
        edt_card_ud.setText(sp.getCard());
        edt_tocdocpu_ud.setText(sp.getTocdocpu());
        edt_congusb_ud.setText(sp.getCongusb());
        edt_vantay_ud.setText(sp.getVantay());
        edt_hinhanh_ud.setText(sp.getHinhanh());

        builder.setNegativeButton("Cập Nhật", (dialog, which) -> {
            try {
                String tensp_ud = edt_tensp_ud.getText().toString();
                int giasp_ud = Integer.parseInt(edt_giasp_ud.getText().toString());
                String thuonghieu_ud = edt_thuonghieu_ud.getText().toString();
                String xuatxu_ud = edt_xuatxu_ud.getText().toString();
                String kichthuocmanhinh_ud = edt_kichthuocmanhinh_ud.getText().toString();
                String mausac_ud = edt_mausac_ud.getText().toString();
                String trongluong_ud = edt_trongluong_ud.getText().toString();
                String chatlieu_ud = edt_chatlieu_ud.getText().toString();
                String cpu_ud = edt_cpu_ud.getText().toString();
                String ocung_ud = edt_ocung_ud.getText().toString();
                String ram_ud = edt_ram_ud.getText().toString();
                String rom_ud = edt_rom_ud.getText().toString();
                String card_ud = edt_card_ud.getText().toString();
                String tocdocpu_ud = edt_tocdocpu_ud.getText().toString();
                String congusb_ud = edt_congusb_ud.getText().toString();
                String vantay_ud = edt_vantay_ud.getText().toString();
                String hinhanh_ud = edt_hinhanh_ud.getText().toString();
                int masp = sp.getMasp();

                if (tensp_ud.isEmpty() || thuonghieu_ud.isEmpty() || xuatxu_ud.isEmpty() ||
                        kichthuocmanhinh_ud.isEmpty() || mausac_ud.isEmpty() || trongluong_ud.isEmpty() ||
                        chatlieu_ud.isEmpty() || cpu_ud.isEmpty() || ocung_ud.isEmpty() ||
                        ram_ud.isEmpty() || rom_ud.isEmpty() || card_ud.isEmpty() ||
                        tocdocpu_ud.isEmpty() || congusb_ud.isEmpty() || vantay_ud.isEmpty() ||
                        hinhanh_ud.isEmpty()) {
                    Toast.makeText(context, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = sanPhamDAO.updateGAM(masp, tensp_ud, giasp_ud, thuonghieu_ud,
                            xuatxu_ud, kichthuocmanhinh_ud, mausac_ud, trongluong_ud, chatlieu_ud,
                            cpu_ud, ocung_ud, ram_ud, rom_ud, card_ud, tocdocpu_ud, congusb_ud,
                            vantay_ud, hinhanh_ud);

                    if (check) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Hủy", (dialog, which) -> {});
        builder.create().show();
    }
}