package dunghtph30405.example.nhom1_pro1121.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dunghtph30405.example.nhom1_pro1121.R;
import dunghtph30405.example.nhom1_pro1121.dao.SanPhamDAO;
import dunghtph30405.example.nhom1_pro1121.model.sanpham;
import dunghtph30405.example.nhom1_pro1121.util.Amount;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ViewHolder> {

    private Context context;
    private ArrayList<sanpham> list;
    private SanPhamDAO sanPhamDAO;
    private OnAddToCartClickListener addToCartClickListener;

    public SanphamAdapter(Context context, ArrayList<sanpham> list, SanPhamDAO sanPhamDAO, OnAddToCartClickListener addToCartClickListener) {
        this.context = context;
        this.list = list;
        this.sanPhamDAO = sanPhamDAO;
        this.addToCartClickListener = addToCartClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sanpham product = list.get(position);
        holder.txt_tensp.setText(product.getTensp());
        holder.txt_gia.setText("Giá: " + Amount.moneyFormat(product.getGia()));

        // Load image if available
        if (product.getHinhanh() != null && !product.getHinhanh().isEmpty()) {
            Glide.with(context)
                    .load(product.getHinhanh())
                    .into(holder.img_anhsp_home);
        }

        // Show product details on item click
        holder.itemView.setOnClickListener(v -> showdialogXemThem(product));

        // Handle add to cart button - combined both listeners
        holder.btn_giohang.setOnClickListener(v -> {
            boolean success = sanPhamDAO.addToCart(product);
            if (success) {
                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                if (addToCartClickListener != null) {
                    addToCartClickListener.onAddToCartClick(product);
                }
            } else {
                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateData(ArrayList<sanpham> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    public interface OnAddToCartClickListener {
        void onAddToCartClick(sanpham sanPham);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tensp, txt_gia, txt_xemthem;
        AppCompatButton btn_giohang;
        ImageView img_anhsp_home;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tensp = itemView.findViewById(R.id.txt_tensp_home);
            txt_gia = itemView.findViewById(R.id.txt_giasp_home);
            txt_xemthem = itemView.findViewById(R.id.txt_xemthem_home);
            btn_giohang = itemView.findViewById(R.id.btn_giohang_home);
            img_anhsp_home = itemView.findViewById(R.id.img_sanpham_home);
        }
    }

    private void showdialogXemThem(sanpham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
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
}