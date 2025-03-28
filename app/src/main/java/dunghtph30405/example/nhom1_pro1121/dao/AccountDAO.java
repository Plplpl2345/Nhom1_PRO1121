package dunghtph30405.example.nhom1_pro1121.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dunghtph30405.example.nhom1_pro1121.database.DbHelper;
import dunghtph30405.example.nhom1_pro1121.designPantter.AccountSingle;
import dunghtph30405.example.nhom1_pro1121.model.account;

public class AccountDAO {
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;

    public AccountDAO(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }

    public ArrayList<account> selectALL_Account() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<account> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new account(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean checkdn(String email, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT WHERE email = ? AND matkhau = ?",
                new String[]{email, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", cursor.getString(3));
            editor.putString("loaitaikhoan", cursor.getString(4));
            editor.putString("hoten", cursor.getString(1));
            editor.commit();
            AccountSingle.getInstance().setAccount(new account(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    // Signup method updated to include correct column name
    public boolean signup(String hoten, String matkhau, String email, String role) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("ACCOUNT", null, "email = ?",
                new String[]{email}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("matkhau", matkhau);
        contentValues.put("email", email);
        contentValues.put("loaitaikhoan", role); // Sửa từ "role" thành "loaitaikhoan"
        contentValues.put("sodienthoai", ""); // Thêm giá trị mặc định cho cột sodienthoai
        try {
            long check = sqLiteDatabase.insertOrThrow("ACCOUNT", null, contentValues);
            dbHelper.resetLocalData();
            cursor.close();
            sqLiteDatabase.close();
            return check != -1;
        } catch (SQLiteConstraintException e) {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
    }

    // Forgot password
    public String fogotpass(String email) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT matkhau FROM ACCOUNT WHERE email = ? ", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String matkhau = cursor.getString(0);
            cursor.close();
            return matkhau;
        }
        cursor.close();
        return "";
    }

    // Change password
    public boolean capNhatMatKhau(String email, String matkhauCu, String matkhauMoi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT WHERE email = ? AND matkhau = ? ",
                new String[]{email, matkhauCu});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", matkhauMoi);
            long check = sqLiteDatabase.update("ACCOUNT", contentValues, "email = ?", new String[]{email});
            cursor.close();
            if (check == -1) {
                return false;
            }
            return true;
        }
        cursor.close();
        return false;
    }
}