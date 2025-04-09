package dunghtph30405.example.nhom1_pro1121.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dunghtph30405.example.nhom1_pro1121.R;
import dunghtph30405.example.nhom1_pro1121.dao.GioHangDAO;
import dunghtph30405.example.nhom1_pro1121.designPantter.AccountSingle;
import dunghtph30405.example.nhom1_pro1121.fragment.frg_giohang;
import dunghtph30405.example.nhom1_pro1121.inteface.TotalUpdateListener;
import dunghtph30405.example.nhom1_pro1121.model.account;
import dunghtph30405.example.nhom1_pro1121.model.giohang;
import dunghtph30405.example.nhom1_pro1121.util.Amount;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private Context context;
    private ArrayList<giohang> list;
    private GioHangDAO gioHangDAO;
    private frg_giohang fragment;
    private TotalUpdateListener totalUpdateListener;

    public GioHangAdapter(Context context, ArrayList<giohang> list, GioHangDAO gioHangDAO, frg_giohang fragment) {
        this.context = context;
        this.list = list;
        this.gioHangDAO = gioHangDAO;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        giohang item = list.get(position);

        if (item.getSoluong() <= 0) {
            // Ẩn item nếu số lượng không hợp lệ
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.getLayoutParams().height = 0;
            return;
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        holder.txt_tenGioHang.setText(item.getTensp());
        holder.txt_soLuong.setText(String.valueOf(item.getSoluong()));
        holder.txt_giaTiengh.setText(Amount.moneyFormat(item.getGia() * item.getSoluong()));

        holder.btn_Slc.setOnClickListener(v -> thayDoiSoLuong(holder, position, 1));
        holder.btn_Slt.setOnClickListener(v -> thayDoiSoLuong(holder, position, -1));

        holder.itemView.setOnLongClickListener(view -> {
            showConfirmDialog("Bạn chắc chắn muốn xóa?", () -> xoaSanPham(position));
            return true;
        });

        holder.btn_muahang_gh.setOnClickListener(v -> dialogThongTin(item));
    }

    private void thayDoiSoLuong(ViewHolder holder, int position, int thayDoi) {
        giohang item = list.get(position);
        int soLuongMoi = item.getSoluong() + thayDoi;

        if (soLuongMoi > 20) {
            Toast.makeText(context, "Số lượng đã đạt tới tối đa", Toast.LENGTH_SHORT).show();
            return;
        }

        if (soLuongMoi < 1) {
            showConfirmDialog("Bạn muốn xóa sản phẩm này?", () -> {
                xoaSanPham(position);
            });
            return;
        }

        item.setSoluong(soLuongMoi);
        gioHangDAO.updateQuantity(item.getId(), soLuongMoi);
        notifyItemChanged(position);
        updateTotal();
    }

    private void xoaSanPham(int position) {
        giohang item = list.get(position);
        gioHangDAO.deleteFromCartUseOBJ(item);
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
        updateTotal();
        Toast.makeText(context, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
    }

    private void updateTotal() {
        double tongTien = calculateTotal();
        if (totalUpdateListener != null) {
            totalUpdateListener.onTotalUpdated(tongTien);
        }
        fragment.setSumPrice(tongTien);
    }

    public double calculateTotal() {
        double total = 0;
        for (giohang item : list) {
            if (item.getSoluong() > 0) {
                total += item.getGia() * item.getSoluong();
            }
        }
        return total;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void dialogThongTin(giohang giohang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_thongtin_muahang, null);
        builder.setView(view);

        EditText edt_sdt = view.findViewById(R.id.edt_sdt);
        EditText edt_namsinh = view.findViewById(R.id.edt_namsinh);
        EditText edt_diachi = view.findViewById(R.id.edt_diachi);

        builder.setNegativeButton("Mua Ngay", (dialog, which) -> {
            String sdt = edt_sdt.getText().toString().trim();
            String namsinh = edt_namsinh.getText().toString().trim();
            String diachi = edt_diachi.getText().toString().trim();

            if (sdt.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()) {
                Toast.makeText(context, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isPhoneNumberValid(sdt)) {
                Toast.makeText(context, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            account ac = AccountSingle.getInstance().getAccount();
            gioHangDAO.addHoaDon(giohang, ac.getHoten(), ngayHienTai());

            list.remove(giohang);
            notifyDataSetChanged();
            updateTotal();
            Toast.makeText(context, "Đã mua hàng thành công", Toast.LENGTH_SHORT).show();
        });

        builder.setPositiveButton("Đóng", null);
        builder.create().show();
    }

    private void showConfirmDialog(String message, Runnable onConfirm) {
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.canhbao)
                .setMessage(message)
                .setPositiveButton("YES", (dialog, which) -> onConfirm.run())
                .setNegativeButton("NO", null)
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String ngayHienTai() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return currentDate.format(formatter);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String regex = "^(0[3|5|7|8|9])+([0-9]{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tenGioHang, txt_giaTiengh, txt_soLuong;
        Button btn_Slt, btn_Slc, btn_muahang_gh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenGioHang = itemView.findViewById(R.id.txt_tenGioHang);
            txt_giaTiengh = itemView.findViewById(R.id.txt_giaTiengh);
            txt_soLuong = itemView.findViewById(R.id.txt_soLuong);
            btn_Slc = itemView.findViewById(R.id.btn_slC);
            btn_Slt = itemView.findViewById(R.id.btn_slT);
            btn_muahang_gh = itemView.findViewById(R.id.btn_muahang_gh);
        }
    }

    public void setTotalUpdateListener(TotalUpdateListener listener) {
        this.totalUpdateListener = listener;
    }
}
