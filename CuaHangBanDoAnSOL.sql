create database CuaHangBanDoAnSOL
go
use CuaHangBanDoAnSOL
go
create table Account(
Id_Account int identity (1000,1) primary key,
Name Nvarchar(max),
Email Nvarchar(max),
Password Nvarchar(max),
Numberphone Nvarchar(max),
Address Nvarchar(max),
JoinDate Nvarchar(max),
Picture nvarchar(max)
)
drop table Account
select*from Account
go
create table Category(
Id_Category int identity (1000,1) primary key,
NameCategory Nvarchar(max),
PictureCategory nvarchar(max),
JoinDate Nvarchar(max),
Note Nvarchar (max)
)
go
drop table Category 
select*from Category
go
INSERT INTO Category VALUES (N'Pizza',N'https://cdn-icons-png.flaticon.com/512/3595/3595458.png','05/03/2022 23:48:32',N'Không');

INSERT INTO Category VALUES (N'Mỳ Ý và cơm',N'https://cdn-icons-png.flaticon.com/512/5787/5787180.png','05/03/2022 23:48:32',N'Không');

INSERT INTO Category VALUES (N'Món Khai Vị',N'https://cdn-icons-png.flaticon.com/512/1721/1721398.png','05/03/2022 23:48:32',N'Không');

INSERT INTO Category VALUES (N'Đồ Uống',N'https://cdn-icons-png.flaticon.com/512/4329/4329542.png','05/03/2022 23:48:32',N'Không');


go
create table Product(
Id_product int identity(1000,1) primary key,
Id_danhmuc int,
NameProduct nvarchar(max),
Content nvarchar(max),
sales int,
views int,
Imagelinks nvarchar(max),
JoinDate nvarchar(max)
)
drop table Product
go
create table ProductDetails(
	Id_productdetails int identity (1000,1) primary key ,
    Id_product int ,	
	Size nvarchar(max),
	Price int,
    Promotionalprice int,
)
go
create table Cart(
    Id_Cart int identity (1000,1) primary key ,
	Id_Account int ,	
	Id_productdetails int ,
	Quantity int,
	TotalMoney int,
	Notes nvarchar(max)
)


go
create table order_(
    Id_Order int identity (1000,1) primary key ,	
	Id_Account int ,	
	Id_productdetails int ,
	Quantity int,
	TotalMoney int,
	Message nvarchar(max),
	PaymentMethod nvarchar(max),
	DateTime nvarchar(max),
	Status int,
	Notes nvarchar(max)
)
go


go
create table Notify(
 Id_notify int identity (1000,1) primary key ,
 Id_Account int ,	
Id_productdetails int ,
 Status int,
 watched int
)
go
create table Love(
 Id_love int identity (1000,1) primary key ,
 Id_Account int,
 Id_product int,
 Notes nvarchar(max)
)

INSERT INTO Product VALUES (1000,N'Pizza Pate Lạp Xưởng'
,N'Sự kết hợp đột phá của hương vị truyền thống với Lạp xưởng Mai Quế Lộ, Pate Đồng Quê và phô mai Mozzarella thơm béo mang lại trải nghiệm mới mẻ và thú vị.',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Pate_Lap_Xuong_400x275-removebg-preview.png?alt=media&token=0691a9a7-d6a9-4558-8c5f-f0d55162516b','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1000,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1000,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1000,N'Lớn',249000,239000);


INSERT INTO Product VALUES (1000,N'Pizza Rau Củ (Xốt Bơ Tỏi)'
,N'Thanh Nhẹ Với Ô Liu Đen Tuyệt Hảo, Cà Chua Bi Tươi Ngon, Nấm, Thơm, Bắp, Hành Tây Và Phô Mai Mozzarella Cho Bạn Bữa Tiệc Rau Củ Tròn Vị.',110,110
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Rau_Cu_400x275__1_-removebg-preview.png?alt=media&token=737240a3-9fd4-4cc7-8b8d-7dbe691b5743','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1001,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1001,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1001,N'Lớn',249000,239000);


INSERT INTO Product VALUES (1000,N'Pizza Gấp Đôi Nhân Phủ Hải Sản Xốt Pesto'
,N'Pizza Hải Sản Xốt Pesto Với Hải Sản (Tôm, Mực) Nhân Đôi Cùng Với Nấm Trên Nền Xốt Pesto Đặc Trưng, Phủ Phô Mai Mozzarella Từ New Zealand Và Quế Tây.',120,120
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Gap_Doi_Nhan_Phu_Hai_San_Xot_Pesto_400x275-removebg-preview.png?alt=media&token=a4deba28-fba0-44fa-aa2e-466193539b9c','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1002,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1002,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1002,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Gấp Đôi Nhân Phủ Cơn Lốc Hải Sản'
,N'Pizza Cơn Lốc Hải Sản Với Hải Sản (MựC, Thanh Cua) Nhân Đôi Cùng Với Thơm, Ớt Chuông Xanh, HàNh Tây, Phủ Phô Mai Mozzarella Từ New Zealand Trên Nền XốT Cheesy Mayo.',130,130
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Gap_Doi_Nhan_Phu_Con_Loc_Hai_San_400x275-removebg-preview.png?alt=media&token=86cbfe29-1f06-41dd-a500-e9b982d3655f','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1003,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1003,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1003,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Gấp Đôi Nhân Phủ Hải Sản Xốt Tiêu Đen'
,N'Pizza Hải Sản Xốt Tiêu Đen Với Hải Sản (Tôm, Mực, Thanh Cua) Nhân Đôi Cùng Với Hành Tây, Thơm Phủ Xốt Tiêu Đen Thơm Nóng Và Phô Mai Mozzarella Từ New Zealand.',1020,1300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Gap_Doi_Nhan_Phu_Hai_San_Xot_Tieu_Den_400x275-removebg-preview.png?alt=media&token=d7da85b9-4dc2-430a-8718-f3bbcc112858','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1004,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1004,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1004,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'PIZZA VIỀN PHÔ MAI 2 LOẠI NHÂN PHỦ'
,N'Cùng thưởng thức 2 loại nhân phủ thơm ngon trên cùng 1 bánh pizza. Pizza Half Half - vị ngon nhân đôi.',300,400
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Hai_Vi_400x275-removebg-preview.png?alt=media&token=90db4854-0072-4afd-b82f-a6abd49ffac3','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1005,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1005,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1005,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Hải Sản Xốt Pesto'
,N'Tôm, Mực Và Nấm Trên Nền Xốt Pesto Đặc Trưng, Phủ Phô Mai Mozzarella',1100,3100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Hai_San_Xot_Pesto_400x275-removebg-preview.png?alt=media&token=0e8d03f2-19f0-4754-9766-7f2f60022d38','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1006,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1006,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1006,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Thịt Và Xúc Xích'
,N'Thơm Ngon Và Giàu Protein Với Thịt Xông Khói, Xúc Xích, ThịT Bò, Giăm Bông Và Pepperoni',2100,3300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Thit_Va_Xuc_Xich_400x275-removebg-preview.png?alt=media&token=d3d9aad9-da64-462a-8bf9-7344fb440540','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1007,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1007,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1007,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Hải Sản Xốt Tiêu Đen'
,N'Tôm, Mực, Thanh Cua, Hành Tây, Thơm Phủ Xốt Tiêu Đen Thơm Nóng Và Phô Mai Mozzarella',1010,1030
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Hai_San_Xot_Tieu_Den_400x275-removebg-preview.png?alt=media&token=3b857bc6-a7da-4655-9c10-10ad624c3d16','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1008,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1008,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1008,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Thập Cẩm'
,N'Mang Hương Vị Truyền Thống Với ThịT Bò, Thịt Xông Khói, Pepperoni, Ớt Chuông, Nấm Và Hành Tây, Phủ Phô Mai Mozzarella',300,400
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Thap_Cam_400x275-removebg-preview.png?alt=media&token=93d21a49-1ecd-4077-bfda-6b398027745e','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1009,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1009,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1009,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Pepperoni'
,N'Pepperoni Với Phô Mai Mozzarella Tuyệt Hảo',200,600
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Hawaiian_400x275-removebg-preview.png?alt=media&token=4b1d0f16-b0aa-45c1-93e4-dd08d79058cf','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1010,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1010,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1010,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Cơn Lốc Hải Sản'
,N'Mực, Thanh Cua, Thơm, Ớt Chuông Xanh, HàNh Tây Và Phô Mai Mozzarella Trên Nền Xốt Cheesy Mayo Và Phô Mai Mozzarella Thượng Hạng',1020,1300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Con_Loc_Hai_San_400x275-removebg-preview.png?alt=media&token=78eb8f3b-91f4-43d0-b363-0748e690a724','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1011,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1011,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1011,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Gà Nướng Nấm'
,N'Pizza Gà Nướng Nấm Trong Cuộc Phiêu Lưu Vị Giác Với Thịt Gà, Nấm, Thơm, Cà Rốt Và Rau Mầm Phủ Xốt Tiêu Đen Thơm Nồng',1080,1900
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Ga_Nuong_Nam_400x275-removebg-preview.png?alt=media&token=492881d6-5f15-4f6d-b01f-fe611ee312ed','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1012,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1012,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1012,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Cá Ngừ'
,N'Mang Hương Vị Biển Cả Nhẹ Nhàng Với Cá Ngừ, Thanh Cua, HàNh Tây, Thơm Trên Nền Xốt Pesto',1200,1800
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Ca_Ngu_400x275-removebg-preview.png?alt=media&token=c6b50b24-f348-4fe5-80d1-7ddebd99753f','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1013,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1013,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1013,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Hawaiian'
,N'Phủ Giăm Bông Và Thơm Ngọt Dịu Trên Nền Sốt Cà Chua Truyền Thống',1200,1400
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Hawaiian_400x275-removebg-preview.png?alt=media&token=4b1d0f16-b0aa-45c1-93e4-dd08d79058cf','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1014,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1014,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1014,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Phô Mai Cao Cấp'
,N'Với 3 LớP Phô Mai Mozzarella Vàng Óng Quyến Rũ',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Pho_Mai_Cao_Cap_400x275-removebg-preview.png?alt=media&token=5ce978c5-6a16-443a-8b5d-ddd3c58bc3bc','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1015,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1015,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1015,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Tôm Xốt Bơ Tỏi'
,N'Với Tôm, HàNh Tây Và Ớt Chuông Phủ Trên Nền Xốt Bơ Tỏi',120,300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Tom_Xot_Bo_Toi_400x275-removebg-preview.png?alt=media&token=a46aeee9-5a48-4ccc-9e1b-ef8721634191','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1016,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1016,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1016,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Hải Sản Viền Phô Mai 3 Vị'
,N'Tôm, Hành Tây, Ớt Chuông Đỏ, Thơm, Ô Liu, Ngò Tây, Phô Mai Mozzarella, Viền Phô Mai Hai Vị, Xốt Bơ Tỏi.',1030,1900
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Tom_Vien_Pho_Mai_3_Vi_400x275-removebg-preview.png?alt=media&token=e20fc41e-f8ac-49ee-8e31-7cf9e11c7989','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1017,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1017,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1017,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Rau Củ (Xốt Cà Chua)'
,N'Thanh Nhẹ Với Ô Liu Đen Tuyệt Hảo, Cà Chua Bi Tươi Ngon, Nấm, Thơm, Bắp, Hành Tây Và Phô Mai Mozzarella Cho Bạn Bữa Tiệc Rau Củ Tròn Vị',1100,1300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Rau_Cu_400x275__1_-removebg-preview.png?alt=media&token=737240a3-9fd4-4cc7-8b8d-7dbe691b5743','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1018,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1018,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1018,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Phô Mai Bốn Vị Xốt Mật Ong'
,N'Sự Kết Hợp Hài Hoà Giữa 4 Loại Phô Mai Trứ Danh Mozzarella, Parmesan, Cheddar Và Cream Cheese Cùng Xốt Mật Ong Mang Đến Hương Vị Hoàn Toàn Mới Lạ',1100,1300
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Pho_Mai_Bon_Vi_Mat_Ong_400x275-removebg-preview.png?alt=media&token=525a760e-64de-41d5-b0a2-07a14535e005','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1019,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1019,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1019,N'Lớn',249000,239000);

INSERT INTO Product VALUES (1000,N'Pizza Bò BBQ Xốt Cay Hàn Quốc'
,N'Thịt Bò Úc, Thơm Trên Nền Xốt Hàn Quốc Cay Nồng, Phủ Rau Mầm Và Mè Rang',1000,1900
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPizza_Bo_BBQ_Xot_Cay_Han_Quoc_400x275-removebg-preview.png?alt=media&token=b3935f2a-656a-45ec-92c1-e6f8b8f15ee2','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1020,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1020,N'Vừa',159000,149000);
INSERT INTO ProductDetails VALUES (1020,N'Lớn',249000,239000);



INSERT INTO Product VALUES (1001,N'Cơm Chiên Tôm Vị Cay'
,N'Cơm Chiên Tôm Vị Cay Nổi Tiếng Trên Nền Xốt Bơ Tỏi Độc Đáo Từ Pizza Hut',1020,1200
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCom_Chien_Tom_Vi_Cay_400x275-removebg-preview.png?alt=media&token=845855aa-00ae-4812-a18a-6439c0c5613d','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1021,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1021,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1001,N'Mì Ý Thịt Xông Khói Xốt Kem'
,N'Mì Ý Xốt Kem Với Thịt Xông Khói',1020,1400
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMi_Y_Thit_Nguoi_Xot_Kem_400x275-removebg-preview.png?alt=media&token=5892b362-cf41-4800-bfc3-e7a11d9f2c67','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1022,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1022,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1001,N'Cơm Chiên Hải Sản'
,N'Cơm Chiên Hải Sản Với Tôm, Mực, Và Rau Củ Tươi',1100,1400
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCom_Chien_Hai_San_400x275-removebg-preview.png?alt=media&token=c604cf74-2918-4962-b3a1-207a996d362d','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1023,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1023,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1001,N'Cơm Cánh Gà BBQ'
,N'Cơm Chiên Tỏi Với Cánh Gà BBQ Nóng Hổi Thơm Nồng',1100,1500
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCanh_Ga_Nuong_BBQ_400x275__1_-removebg-preview.png?alt=media&token=befc1968-96d6-4b05-97ae-6b7ff8cd01e0','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1024,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1024,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1001,N'Mì Ý Bò Bằm Xốt Cà Chua'
,N'Mì Ý Xốt Cà Chua Với Thịt Bò',1030,1580
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMi_Y_Thit_Bam_Xot_Ca_Chua_400x275-removebg-preview.png?alt=media&token=897aa3ee-d65d-4f1b-8943-2173bc34093b','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1025,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1025,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1001,N'Mì Ý Hải Sản Xốt Cà Chua'
,N'Mì Ý Xốt Cà Chua Với Tôm, Mực, Hành Tây Và Ớt Chuông Xanh',1100,1700
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMi_Y_Hai_San_Xot_Ca_Chua_400x275-removebg-preview.png?alt=media&token=77e1a984-3cc1-47b4-8aed-aa5d5e9e4e6e','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1026,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1026,N'Vừa',159000,149000);


INSERT INTO Product VALUES (1001,N'Mỳ Ý Hải Sản Xốt Tiêu Đen'
,N'Tôm, Thanh Cua, Mực, Đậu Pháp, Nấm, Ớt Chuông Xanh Cùng Xốt Tiêu Đen Cay Nồng',1000,1900
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMi_Y_Hai_San_Xot_Tieu_Den_400x275-removebg-preview.png?alt=media&token=6787a7e2-95ce-407c-93bc-277f98467f77','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1027,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1027,N'Vừa',159000,149000);




INSERT INTO Product VALUES (1002,N'Bánh Mì Bơ Tỏi'
,N'Bánh Mì Bơ Tỏi Nướng Giòn Ngon Phủ Xốt Bơ Tỏi Thơm Nồng',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FBanh_Mi_Bo_Toi_400x275-removebg-preview.png?alt=media&token=362eb1b2-a086-4970-9c9f-d3f1ca105fb2','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1028,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1028,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Nachos'
,N'Bánh Nachos Giòn Rụm Kiểu Mexico Kèm Xốt Phô Mai Thơm Ngậy Hoặc Xốt Cà Chua Đặc Biệt. ',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FNachos_400x275-removebg-preview.png?alt=media&token=d31f16ab-b43a-41a7-9aee-185e965ba603','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1029,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1029,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Xúp Kem Nấm Thịt Gà'
,N'Khoai Tây Chiên Với Xốt Cà Chua',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXup_Kem_Nam_Thit_Ga_400x275-removebg-preview.png?alt=media&token=26b5c1dd-e3ee-4194-b5cc-814f09a38c28','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1030,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1030,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Khoai Tây Chiên'
,N'Khoai Tây Chiên Với Xốt Cà Chua',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FKhoai_Tay_Chien_400x275-removebg-preview.png?alt=media&token=5627a123-0c58-4d94-bf54-7e0967a0d05b','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1031,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1031,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Bánh Cuộn Phô Mai'
,N'Được Làm Từ 2 Loại Phô Mai',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FBanh_Cuon_Pho_Mai_400x275-removebg-preview.png?alt=media&token=2f1635aa-6792-4eaa-a6b0-d24ae8ebc4b4','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1032,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1032,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Xúp Hải Sản'
,N'Hải sản',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXup_Hai_San_400x275-removebg-preview.png?alt=media&token=4b1d1753-cf35-4bd7-ad80-a21c4d5b41f9','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1033,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1033,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Phô Mai Chiên Giòn'
,N'Phô Mai Chiên Giòn',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPho_Mai_Chien_Gion_400x275-removebg-preview.png?alt=media&token=79d4300e-d147-48c9-ba4f-a353f70090b4','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1034,N'Nhỏ',109000,99000);
INSERT INTO ProductDetails VALUES (1034,N'Vừa',159000,149000);

INSERT INTO Product VALUES (1002,N'Bắp Nướng Phô Mai Thịt Xông Khói'
,N'Bắp Ngọt, Thịt Xông Khói Kèm Xốt Cheesy Mayo Và Bơ Tỏi, Phủ Phô Mai Mozzarella Đút Lò',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FBap_Nuong_Pho_Mai_Thit_Xong_Khoi-removebg-preview.png?alt=media&token=e4a10e92-d07c-4722-b8a1-35623c171352','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1035,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Xà Lách Trộn Cá Ngừ Và Thịt Xông Khói'
,N'Rau Xanh Trộn Với Cá Ngừ, Thịt Xông Khói, Đậu Pháp, Cà Chua Bi, Bắp Và Xốt Mayonnaise',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXa_Lach_Ca_Ngu_400x275-removebg-preview.png?alt=media&token=1ca96c73-b83d-414d-b5a2-6dac2aa97b4c','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1036,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Xà Lách Gà Pesto'
,N'Salad Tươi Xanh Với Thịt Gà Nướng Và Xốt Pesto Kiểu Ý',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXa_Lach_Ga_Pesto_400x275-removebg-preview.png?alt=media&token=9a207cbb-f2b5-45c0-b3f8-b215d8efa223','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1037,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Xà Lách Da Cá Hồi'
,N'Da Cá Hồi, Cà Chua Bi, Ớt Chuông Cùng Với Xốt Mè Rang Đặc Biệt',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXa_Lach_Da_Ca_Hoi_400x275-removebg-preview.png?alt=media&token=1bc187c9-0b9a-4b24-a995-83c50fd3657d','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1038,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Xúc Xích Ý Nướng'
,N'Luôn Là Món Khoái Khẩu Của Bất Kì Tín Đồ Pizza Nào',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FXuc_Xich_Y_Nuong_400x275-removebg-preview.png?alt=media&token=9b578828-1eca-4732-b1fc-df7deac21757','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1039,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Mực Chiên Giòn'
,N'Mực Khoanh Tẩm Bột Chiên Giòn',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMuc_Chien_Gion_400x275-removebg-preview.png?alt=media&token=3a9f17e1-e4e7-4369-9d4c-0c61b05d702f','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1040,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Cánh Gà Nướng BBQ (4 Miếng)'
,N'Cánh Gà Nướng Thơm Lừng Ngon Tuyệt Với Hương Vị BBQ',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCanh_Ga_Nuong_BBQ_400x275__1_-removebg-preview.png?alt=media&token=befc1968-96d6-4b05-97ae-6b7ff8cd01e0','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1041,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Cánh Gà Chiên Xốt Cay Hàn Quốc (4 Miếng) '
,N'Cánh Gà Chiên Giòn Phủ Xốt Hàn Quốc',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCanh_Ga_Chien_Xot_Cay_Han_Quoc_400x275__1_-removebg-preview.png?alt=media&token=79939cf8-cb5a-43a4-9d51-f77c73efc7f6','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1042,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Cánh Gà Nướng BBQ (6 Miếng)'
,N'Cánh Gà Nướng Thơm Lừng Ngon Tuyệt Với Hương Vị BBQ',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCanh_Ga_Nuong_BBQ_400x275__1_-removebg-preview.png?alt=media&token=befc1968-96d6-4b05-97ae-6b7ff8cd01e0','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1043,N'Nhỏ',109000,99000);


INSERT INTO Product VALUES (1002,N'Gà Cay Phô Mai Hàn Quốc '
,N'Thịt Gà Nướng Phô Mai Kiểu Hàn Quốc Với Bắp Ngọt Và Hành Tây',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FGa_Cay_Pho_Mai_Han_Quoc_400x275__1_-removebg-preview.png?alt=media&token=95adc7d0-d836-46f0-b659-6993e883dbbb','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1044,N'Nhỏ',109000,99000);

INSERT INTO Product VALUES (1002,N'Cánh Gà Chiên Xốt Cay Hàn Quốc (6 Miếng) '
,N'Cánh Gà Chiên Giòn Phủ Xốt Hàn Quốc',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FCanh_Ga_Chien_Xot_Cay_Han_Quoc_400x275__1_-removebg-preview.png?alt=media&token=79939cf8-cb5a-43a4-9d51-f77c73efc7f6','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1045,N'Nhỏ',109000,99000);



INSERT INTO Product VALUES (1003,N'Lipton 450ml'
,N'Lipton 450ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FLiptop_455ml_400x275-removebg-preview.png?alt=media&token=87635485-0bba-48c1-ba75-93e747ac69fb','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1046,N'Nhỏ',10900,0);

INSERT INTO Product VALUES (1003,N'Aquafina 500ml'
,N'Aquafina 500ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FAquafina_400x275-removebg-preview.png?alt=media&token=6ca3d3ee-d0e4-404e-b406-bebc4fd8a671','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1047,N'Nhỏ',10900,0);

INSERT INTO Product VALUES (1003,N'Pepsi No Calories Lon 320ml'
,N'Pepsi No Calories Lon 320ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPepsi_Can_400x275-removebg-preview.png?alt=media&token=5b661364-134c-4b6c-b8a2-ea2328b7c632','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1048,N'Nhỏ',10900,0);

INSERT INTO Product VALUES (1003,N'Pepsi Lemon Lon 320ml'
,N'Pepsi Lemon Lon 320ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPepsi_Lemon_Can_400x275-removebg-preview.png?alt=media&token=b1071da1-27ab-449c-b1f2-9d5895180bc2','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1049,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'7Up Can 320ml'
,N'7Up Can 320ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2F7Up_Can_400x275-removebg-preview.png?alt=media&token=ff14e0c7-c735-4ad2-aa2f-a17cf79aa876','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1050,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'Mirinda Orange Lon 320ml'
,N'Mirinda Orange Lon 320ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMirinda_Orange_Can_400x275-removebg-preview.png?alt=media&token=85ac3a88-aac4-4054-9de7-466f2c64d58e','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1051,N'Nhỏ',10900,0);

INSERT INTO Product VALUES (1003,N'Mirinda Soda Lon 320ml'
,N'Mirinda Soda Lon 320ml',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMirinda_Soda_Can_400x275-removebg-preview.png?alt=media&token=17579125-4780-446c-8066-d84a69f3fd94','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1052,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'Trà Sữa Trà Đen'
,N'Trà Sữa Trà Đen',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FTra_Sua_Tra_Den_400x275-removebg-preview.png?alt=media&token=c1cf2483-c946-4bf5-b360-048bdfb2db16','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1053,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'Trà Đào Hạt Chia'
,N'Trà Đào Hạt Chia',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FTra_Dao_Hat_Chia_400x275-removebg-preview.png?alt=media&token=30d5b927-ddfb-4796-bd9b-dc4b45a7376b','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1054,N'Nhỏ',10900,0);

INSERT INTO Product VALUES (1003,N'Trà Vải'
,N'Trà Vải',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FTra_Vai_400x275-removebg-preview.png?alt=media&token=8e7954f3-3337-47e4-8c9d-1dddc7f37eff','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1055,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'Pepsi Chai 1.5L'
,N'Pepsi Chai 1.5L',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FPepsi_1.5L_400x275-removebg-preview.png?alt=media&token=e1cee40d-c314-45ff-a340-ab7e16864c58','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1056,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'7Up Chai 1.5L'
,N'7Up Chai 1.5L',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2F7Up_1.5L_400x275-removebg-preview.png?alt=media&token=53988ada-3e90-4684-a1e9-a55a117c7578','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1057,N'Nhỏ',10900,0);


INSERT INTO Product VALUES (1003,N'Mirinda  Chai 1.5L'
,N'Mirinda  Chai 1.5L',100,100
,N'https://firebasestorage.googleapis.com/v0/b/project-4-ab37d.appspot.com/o/food%2FMirinda_1.5L_400x275-removebg-preview.png?alt=media&token=a76cf84c-586c-4afe-be4b-aa501ad9db79','05/03/2022 23:48:32');
INSERT INTO ProductDetails VALUES (1058,N'Nhỏ',10900,0);
