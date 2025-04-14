package dunghtph30405.example.nhom1_pro1121.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dunghtph30405.example.nhom1_pro1121.database.DbHelper;

public class ThongKeDAO {
    DbHelper dbHelper;

    public ThongKeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/", ""); // yyyyMMdd
        ngayketthuc = ngayketthuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT SUM(gia * soluong) FROM HOADON " +
                        "WHERE substr(ngaymua,7)||substr(ngaymua,4,2)||substr(ngaymua,1,2) BETWEEN ? AND ?",
                new String[]{ngaybatdau, ngayketthuc}
        );

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
