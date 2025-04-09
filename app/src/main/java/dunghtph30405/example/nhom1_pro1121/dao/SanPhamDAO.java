package dunghtph30405.example.nhom1_pro1121.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dunghtph30405.example.nhom1_pro1121.database.DbHelper;
import dunghtph30405.example.nhom1_pro1121.designPantter.AccountSingle;
import dunghtph30405.example.nhom1_pro1121.model.giohang;
import dunghtph30405.example.nhom1_pro1121.model.sanpham;

public class SanPhamDAO {
    DbHelper dbHelper;
    Context context;


    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
        this.context = context;
    }

    public ArrayList<sanpham> selectSANPHAM() {
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new sanpham(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17)));
            } while (cursor.moveToNext());
        }
        return list;


    }


    public ArrayList<sanpham> selectGAMING() {
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GAMING", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                sanpham sp = new sanpham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17)
                );
                list.add(sp);

                // In dữ liệu để kiểm tra
                Log.d("DB_DEBUG_GM", "SanPham: " + sp.tostring());

            } while (cursor.moveToNext());
        } else {
            Log.d("DB_DEBUG", "No data found.");
        }
        cursor.close();  // Đảm bảo đóng cursor
        return list;
    }


    public ArrayList<sanpham> selectMACBOOK() {
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM VANPHONG", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                sanpham sp = new sanpham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17)
                );
                list.add(sp);

                // In dữ liệu để kiểm tra
                Log.d("DB_DEBUG_VP", "SanPham: " + sp.tostring());

            } while (cursor.moveToNext());
        } else {
            Log.d("DB_DEBUG", "No data found.");
        }
        cursor.close(); // Đảm bảo đóng cursor
        return list;
    }


    //delete
    public boolean deleteSP(int masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("SANPHAM", "masp = ?",
                new String[]{String.valueOf(masp)});
        if (row <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteGAM(int masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("GAMING", "masp = ?", new String[]{String.valueOf(masp)});
        if (row <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteVP(int masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("VANPHONG", "masp = ?", new String[]{String.valueOf(masp)});
        if (row <= 0) {
            return false;
        }
        return true;
    }

    //update
    public boolean updateSP(int masp, String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu, String ocung, String ram, String rom, String card, String tocdocpu, String congusb, String vantay, String hinhanh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);

        long check = sqLiteDatabase.update("SANPHAM", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1) {
            return false;
        }
        return true;

    }

    public boolean updateGAM(int masp, String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu, String ocung, String ram, String rom, String card, String tocdocpu, String congusb, String vantay, String hinhanh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);

        long check = sqLiteDatabase.update("GAMING", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1) {
            return false;
        }
        return true;

    }

    public boolean updateVP(int masp, String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu, String ocung, String ram, String rom, String card, String tocdocpu, String congusb, String vantay, String hinhanh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);

        long check = sqLiteDatabase.update("VANPHONG", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1) {
            return false;
        }
        return true;

    }


    //add
    public boolean addGAM(String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu, String ocung, String ram, String rom, String card, String tocdocpu, String congusb, String vantay, String hinhanh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);

        long check = sqLiteDatabase.insert("GAMING", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }

    public boolean addVP(String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu, String ocung, String ram, String rom, String card, String tocdocpu, String congusb, String vantay, String hinhanh
    ) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);
        long check = sqLiteDatabase.insert("VANPHONG", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }

    public boolean addSP(String tensp, int gia, String thuonghieu, String xuatxu, String kichthuocmanhinh,
                         String mausac, String trongluong, String chatlieu, String cpu, String ocung,
                         String ram, String rom, String card,
                         String tocdocpu, String congusb, String vantay, String hinhanh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        contentValues.put("hinhanh", hinhanh);
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }

    public Boolean addToCart(sanpham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        GioHangDAO gioHangDAO = new GioHangDAO(context);
        ArrayList<giohang> list = gioHangDAO.listGH();

        for (giohang item : list) {
            if (item.getMasp() == sanPham.getMasp() && item.getTensp().equals(sanPham.getTensp())) {
                ContentValues values = new ContentValues();
                values.put("TENSP", sanPham.getTensp());
                values.put("GIA", sanPham.getGia());
                values.put("SOLUONG", item.getSoluong() + 1);
                values.put("id_ac", AccountSingle.getInstance().getAccount().getId());
                values.put("masp", sanPham.getMasp());

                db.update("GIOHANG", values, "tensp = ?", new String[]{sanPham.getTensp()});
                db.close();
                return true;
            }
        }

        // Nếu chưa có trong giỏ hàng
        ContentValues values = new ContentValues();
        values.put("TENSP", sanPham.getTensp());
        values.put("GIA", sanPham.getGia());
        values.put("SOLUONG", 1);
        values.put("id_ac", AccountSingle.getInstance().getAccount().getId());
        values.put("masp", sanPham.getMasp());

        db.insert("GIOHANG", null, values);
        db.close();
        return true;
    }
}