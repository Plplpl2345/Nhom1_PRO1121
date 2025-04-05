package dunghtph30405.example.nhom1_pro1121.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class  DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "LapMarket", null, 44);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String account = "CREATE TABLE ACCOUNT(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoten TEXT, " +
                "matkhau TEXT," +
                "email TEXT," +
                "loaitaikhoan TEXT,"+
                "sodienthoai TEXT)";
        db.execSQL(account);

        db.execSQL("INSERT INTO ACCOUNT (hoten, matkhau, email, loaitaikhoan, sodienthoai) VALUES " +
                "('admin', 'admin', 'admin@gmail.com', 'admin', '0101010101')," +
                "('user1', 'user1', 'user1@gmail.com', 'user','0202020202')," +
                "('hoangdung59', 'hoangdung59', 'hoangdung59@gmail.com', 'user','0303030303')," +
                "('ngocdang2', 'ngocdang2', 'ngocdang2@gmail.com', 'user', '0404040404')");


        String sanpham_home = "CREATE TABLE SANPHAM(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT ," +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT," +
                "hinhanh TEXT)";

        db.execSQL(sanpham_home);

        db.execSQL("INSERT INTO SANPHAM VALUES (1,'Acer Nitro 5',15900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không', 'https://ttcenter.com.vn/uploads/product/9fxc438a-852-asus-vivobook-x521eq-s533eq-core-i5-1135g7-ram-8gb-ssd-512gb-mx350.jpg')," +
                "(2,'Asus Vivo Book',16900000, 'Asus', 'Trung Quốc', '15.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530U', 'SSD', '8GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Không', 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Products/Images/44/329858/asus-x1504za-i5-nj1608w-170225-113200-098-600x600.jpg'),"  +
                "(3,'Lenovo Thinkpad',18900000, 'Lenovo', 'Nga', '15.6 inch', 'Trắng', '2.8 kg', 'Nhựa', 'Intel i3 13400H', 'HDD', '12GB', '512GB', 'GTX 1680', '2.7GHz', '3 cổng', 'Có', 'https://cdn.tgdd.vn/Products/Images/44/328131/lenovo-thinkpad-e16-ultra-7-21ma004pvn-1-750x500.jpg'),"  +
                "(4,'Acer Aspire 3',8690000, 'Acer', 'Triều Tiên', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Ryzen 5 10530H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.2GHz', '3 cổng', 'Không', 'https://laptopworld.vn/media/product/19187_acer_aspire_3_a315_44p_r9w8__7.jpg'),"  +
                "(5,'MSI Modern 15',13790000, 'MSI', 'Trung Quốc', '15.6 inch', 'Đỏ', '3.1 kg', 'Nhựa', 'Intel i7 10400H', 'HDD', '8GB', '256GB', 'RTX 1030', '2.1GHz', '2 cổng', 'Có', 'https://img.websosanh.vn/v2/users/root_product/images/laptop-msi-modern-15-a5m-237vn/f3fd4xhnaz2ct.jpg'),"  +
                "(6,'Macbook Air 2020',17690000, 'Apple', 'Nhật Bản', '13.3 inch', 'Trắng', '1.5 kg', 'Nhôm', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không', 'https://cdn.tgdd.vn/Products/Images/44/220173/apple-macbook-air-2020-va%CC%82ntay-220173-600x600.jpg'),"  +
                "(7,'Macbook Air 2022',25450000, 'Apple', 'Việt Nam', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không', 'https://cdn.tgdd.vn/News/1471803/macbook-air-m2-2022-co-tot-khong-gia-bao-nhieu-5-800x500.jpg'),"  +
                "(8,'Macbook Pro 16',57500000, 'Apple', 'Thái Lan', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '12GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Có', 'https://macstores.vn/wp-content/uploads/2023/12/macbook-pro-16-inch-m3-pro-2023-36gb-ram-512gb-ssd-baterrylife.jpg')" );

        String sanpham_gaming = "CREATE TABLE GAMING(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT , " +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT, " +
                "hinhanh TEXT)";
        db.execSQL(sanpham_gaming);

        db.execSQL("INSERT INTO GAMING VALUES (1,'Acer Nitro Gaming',15900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không', 'https://product.hstatic.net/200000722513/product/nitro5_an515-57_bl_bk_03d_2ee1d376162c414fa864bc536c36573f_62ac422229e54e148052a2c4966846c8_1024x1024.jpg')," +
                "(2,'Asus TUF Gaming',16900000, 'Asus', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://www.tncstore.vn/media/product/8560-laptop-asus-tuf-f15-fx507zc-hn124w-2.jpg'),"  +
                "(3,'MSI Thin GF63 Gaming',18900000, 'MSI', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://cdn.tgdd.vn/Products/Images/44/316940/msi-gaming-gf63-thin-12ucx-i5-873vn-glr-thumb-600x600.jpg'),"  +
                "(4,'Acer Aspire 3',15600000, 'Acer', 'Việt Nam', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i3 12500H', 'HDD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://product.hstatic.net/200000320233/product/laptop-aspire-3-spin-14-a3sp14-31pt-387z-2_ee9107ff9be44049b779c62817acc855_grande.jpg'),"  +
                "(5,'Nitro 5 Tiger',23900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Có', 'https://laptopaz.vn/media/product/3241_acer_nitro_5_tiger_rtx_3050_4__1700107372.jpg'),"  +
                "(6,'Nitro 5 2022',17690000, 'Acer', 'Việt Nam', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không', 'https://product.hstatic.net/1000167396/product/acer-nitro-5-2021-laptop365__6___1__b70a26b886cd4785835412312f8f0690_master.jpg'),"  +
                "(7,'HP Victus Gaming',15900000, 'HP', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://anphat.com.vn/media/lib/28-08-2023/hpvictus15-fa1087tx8c5m4pa1.jpg'),"  +
                "(8,'Lenovo Thinkpad Gaming',17900000, 'Lenovo', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://image.ceneostatic.pl/data/products/165266234/i-laptop-lenovo-ideapad-gaming-3-15ach6-15-6-ryzen5-16gb-512gb-noos-82k2027arm.jpg')");


        String sanpham_vanphong = "CREATE TABLE VANPHONG(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT , " +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT, " +
                "hinhanh TEXT)";
        db.execSQL(sanpham_vanphong);

        db.execSQL("INSERT INTO VANPHONG VALUES (1,'HP Pavilion 15',16800000, 'HP', 'Việt Nam', '15.6 inch', 'Đen', '1.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Có', 'https://laptoptuanminh.vn/images/products/2023/05/31/original/z4391832736235_800b9a6c6da4f69abd43809f433ef805_1685511648.jpg')," +
                "(2,'HP 240 G9 ',15790000, 'HP', 'Trung Quốc', '14.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://ttcenter.com.vn/uploads/photos/1730187544_3369_c7e7bc89ece6968359e02428df3d89d2.jpg'),"  +
                "(3,'Dell Inspiron T7430',21690000, 'Dell', 'Việt Nam', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://maytinhcdc.vn/media/product/7531_110326_c0f059b7_b902_4f18_93bc_d963029de804.jpg'),"  +
                "(4,'Dell Vostro',16990000, 'Dell', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không', 'https://product.hstatic.net/200000553329/product/dell-vostro-5510-i5-70253901-021121-015009-600x600_081bdf64cc5c4129aa7f56b908a5fa0c.jpg'),"  +
                "(5,'Asus Zenbook 14',24590000,  'Asus', 'Trung Quốc', '13.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530H', 'SSD', '16GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Có', 'https://d28jzcg6y4v9j1.cloudfront.net/asus_zenbook_14_oled_ux3402_2_1717584310901.jpg'),"  +
                "(6,'Zenbook 14X OLED',18790000,  'Asus', 'Việt Nam', '13.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530U', 'HDD', '32GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Không', 'https://cdn.tgdd.vn/Files/2022/04/15/1426146/zenbook-14x-oled-space-edition-display_1280x640-800-resize.jpg'),"  +
                "(7,'Macbook Air 2020',17690000, 'Apple', 'Thái Lan', '13.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '64GB', '256GB', 'RTX 4050', '2.7GHz', '3 cổng', 'Có', 'https://tmp.phongvu.vn/wp-content/uploads/2021/03/Laptop-MacBook-Air-2020-13.3-MGND3SAA-vang-2.jpg'),"  +
                "(8,'Macbook Air 2022',17690000, 'Apple', 'Việt Nam', '16.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Apple m1', 'SSD', '12GB', '512GB', 'RTX 6050', '5.7GHz', '3 cổng', 'Có', 'https://images.expertreviews.com/wp-content/uploads/2022/08/m2_macbook_air_2022_review_0.jpg'),"  +
                "(9,'Macbook Pro 16',57000000, 'Apple', 'Thái Lan', '12.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Appple m2', 'HDD', '12GB', '256GB', 'RTX 1050', '4.7GHz', '3 cổng', 'Có', 'https://macstores.vn/wp-content/uploads/2023/12/macbook-pro-16-inch-m3-pro-2023-36gb-ram-512gb-ssd-baterrylife.jpg')");


        String sanpham_phukien = "CREATE TABLE PHUKIEN (mapk INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenpk TEXT , " +
                "gia INTEGER, " +
                "dungluong TEXT, " +
                "loairam TEXT, " +
                "hotro TEXT, " +
                "voltage TEXT, " +
                "busram TEXT, " +
                "hangsanxuat TEXT)";
        db.execSQL(sanpham_phukien);

        db.execSQL("INSERT INTO PHUKIEN VALUES (1,'RAM Laptop Kingston',570000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz', 'Kingston')," +
                " (2,'RAM Laptop Lexar',490000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Lexar')," +
                " (3,'RAM Laptop Samsung',650000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Samsung')," +
                " (4,'RAM Laptop Samsung',1090000, '16GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Samsung')," +
                " (5,'RAM PC T-Force',750000, '8GB', 'DDR4', 'DIMM(PC, Destop)', '1.35V', '3200MHz','Hãng khác')," +
                " (6,'RAM Laptop Kingston',890000, '8GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '4800MT/s','Kingston')," +
                " (7,'RAM Laptop Samsung',790000, '8GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '4800MT/s','Samsung')," +
                " (8,'RAM Laptop Kingston',1490000, '16GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '3200MHz','Kingston')");


        String giohang = "CREATE TABLE GIOHANG(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT REFERENCES SANPHAM(tensp), " +
                "gia INTEGER REFERENCES SANPHAM(gia),  " +
                "soluong INTEGER, " +
                "id_ac INTEGER REFERENCES ACCOUNT(id), " +
                "masp INTEGER REFERENCES SANPHAM(masp))";
        db.execSQL(giohang);





        String hoadon_sanpham = "CREATE TABLE HOADON (mahd INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoten TEXT REFERENCES ACCOUNT(hoten), " +
                "tensp TEXT REFERENCES GIOHANG(tensp),  " +
                "soluong INTEGER REFERENCES GIOHANG(soluong),  " +
                "gia INTEGER REFERENCES GIOHANG(gia)," +
                "ngaymua TEXT," +
                "trangthai INTEGER, id_ac_hd INTEGER REFERENCES ACCOUNT(id) )";
        db.execSQL(hoadon_sanpham);


        String chitiet_hoadon = "CREATE TABLE CTHOADON (mahdct INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sdt TEXT,  " +
                "namsinh TEXT, " +
                "diachi TEXT, " +
                "hoten TEXT REFERENCES ACCOUNT(hoten))";
        db.execSQL(chitiet_hoadon);



//



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("drop table if exists ACCOUNT");
            db.execSQL("drop table if exists SANPHAM");
            db.execSQL("drop table if exists GAMING");
            db.execSQL("drop table if exists VANPHONG");
            db.execSQL("drop table if exists PHUKIEN");
            db.execSQL("drop table if exists GIOHANG");
            db.execSQL("drop table if exists HOADON");
            db.execSQL("drop table if exists CTHOADON");
            onCreate(db);
        }
    }

    public void resetLocalData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("HOADON", null, null);
        db.delete("GIOHANG", null, null);
    }
}

