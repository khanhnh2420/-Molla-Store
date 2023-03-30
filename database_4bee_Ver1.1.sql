IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'DB_4TL')
BEGIN
   CREATE DATABASE DB_4TL;
END;
go
use DB_4TL

go

USE DB_4TL
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](60) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](255) NOT NULL,
	[Photo] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[RoleId] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_UserRoles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Categories]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categories](
	[Id] [char](4) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[ProductId] [int] NOT NULL,
	[Price] [float] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[CreateDate] [date] NOT NULL,
	[Address] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Phone] [varchar](10) NOT NULL,
	[Status] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Poster] [nvarchar](50) NOT NULL,
	[Thumbnail] [nvarchar](255) NOT NULL,
	[Price] [float] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[Available] [bit] NOT NULL,
	[Source] [varchar](255) NOT NULL,
	[Link] [varchar](500) NOT NULL,
	[Details] [nvarchar](500) NOT NULL,
	[CategoryId] [char](4) NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 4/12/2021 3:07:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT INTO [dbo].[Accounts] ([Username], [Password], [Fullname], [Email], [Address], [Photo])
VALUES ('nguyenvana', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Nguyễn Văn A', 'nguyenvana@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
       ('tranvanb', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Trần Văn B', 'tranvanb@gmail.com', N'99 Lê Lợi, Quận 5, TP. HCM', 'user.png'),
       ('phamthuc', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Phạm Thục', 'phamthuc@gmail.com', N'78 Cách Mạng Tháng 8, Quận 3, TP. HCM', 'user.png'),
       ('lethuhien', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Lê Thùy Hiền', 'lethuhien@gmail.com', N'22 Bà Huyện Thanh Quan, Quận 1, TP. HCM', 'user.png'),
       ('dangkimchi', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Đặng Kim Chi', 'dangkimchi@gmail.com', N'45 Nguyễn Thị Minh Khai, Quận 1, TP. HCM', 'user.png'),
       ('nguyenhongnhan', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Nguyễn Hồng Nhân', 'nguyenhongnhan@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
       ('lethithuy', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Lê Thị Thúy', 'lethithuy@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
       ('tranthanhthao', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Trần Thanh Thảo', 'tranthanhthao@gmail.com', N'78 Cách Mạng Tháng 8, Quận 3, TP. HCM', 'user.png'),
       ('nguyenvanbao', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Nguyễn Văn Bảo', 'nguyenvanbao@gmail.com', N'102 Lý Tự Trọng, Quận 1, TP. HCM', 'user.png'),
       ('hoangtunglam', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Hoàng Tùng Lâm', 'hoangtunglam@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
	   ('dire', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Quản Lý', 'dire@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
	   ('cust', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Người dùng', 'cust@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png'),
	   ('staf', '$2a$10$REel/nhIn4pF8JssL84rcu1m82mRO71eIwPcGA7GX17OAHuRfdsRq', N'Nhân Viên', 'staf@gmail.com', N'12 Nguyễn Du, Quận 1, TP. HCM', 'user.png')

SET IDENTITY_INSERT [dbo].[Authorities] ON 
INSERT INTO [dbo].[Authorities] ([Id], [Username], [RoleId])
VALUES 
(1, N'nguyenvana', N'CUST'),
(2, N'tranvanb', N'CUST'),
(3, N'phamthuc', N'CUST'),
(4, N'lethuhien', N'CUST'),
(5, N'dangkimchi', N'CUST'),
(6, N'nguyenhongnhan', N'DIRE'),
(7, N'lethithuy', N'STAF'),
(8, N'tranthanhthao', N'STAF'),
(9, N'nguyenvanbao', N'CUST'),
(10, N'hoangtunglam', N'CUST'),
(11, N'dire', N'DIRE'),
(12, N'cust', N'CUST'),
(13, N'staf', N'STAF')


SET IDENTITY_INSERT [dbo].[Authorities] OFF
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'HD', N'Hành Động')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'PL', N'Phiêu Lưu')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'NV', N'Nhập Vai')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'MP', N'Mô Phỏng')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'CT', N'Chiến Thuật')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'TT', N'Thể Thao')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'MMO', N'MMO')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'KD', N'Kinh Dị')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'LT', N'Lập Trình')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'MOBA', N'Moba')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'FPS', N'FPS')
SET IDENTITY_INSERT [dbo].[OrderDetails] ON 

INSERT INTO [dbo].[OrderDetails] ([Id], [OrderId], [ProductId], [Price])
VALUES 
(1, 11, 1, 200000),
(2, 11, 2, 250000),
(3, 11, 3, 180000),
(4, 12, 4, 300000),
(5, 12, 5, 350000),
(6, 13, 6, 120000),
(7, 13, 7, 150000),
(8, 14, 8, 450000),
(9, 15, 9, 280000),
(10, 16, 10, 220000),
(11, 1, 1, 200000),
(12, 2, 2, 250000),
(13, 3, 3, 180000),
(14, 4, 4, 300000),
(15, 5, 5, 350000),
(16, 6, 6, 120000),
(17, 7, 7, 150000),
(18, 8, 8, 450000),
(19, 9, 9, 280000),
(20, 10, 10, 220000),
(21, 17, 8, 450000),
(22, 18, 9, 280000),
(23, 19, 10, 220000),
(24, 20, 10, 220000)
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 
GO

INSERT INTO [dbo].[Orders] ([Id], [Username], [CreateDate], [Address], [Email], [Phone], [Status])
VALUES 
(1, N'nguyenvana', '2022-02-01', N'12 Nguyễn Du, Quận 1, TP. HCM', N'nguyenvana@gmail.com', '0123456789', N'Đã hoàn thành'),
(2, N'tranvanb', '2022-02-03', N'99 Lê Lợi, Quận 5, TP. HCM', N'tranvanb@gmail.com', '0123456789', N'Đã hoàn thành'),
(3, N'phamthuc', '2022-02-04', N'78 Cách Mạng Tháng 8, Quận 3, TP. HCM', N'phamthuc@gmail.com', '0123456789', N'Đã hoàn thành'),
(4, N'lethuhien', '2022-02-05', N'22 Bà Huyện Thanh Quan, Quận 1, TP. HCM', N'lethuhien@gmail.com', '0123456789', N'Chờ xử lý'),
(5, N'dangkimchi', '2022-02-06', N'45 Nguyễn Thị Minh Khai, Quận 1, TP. HCM', N'dangkimchi@gmail.com', '0123456789', N'Đã hoàn thành'),
(6, N'nguyenvanbao', '2022-02-08', N'31 Lê Duẩn, Quận 1, TP. HCM', N'nguyenvanbao@gmail.com', '0123456789', N'Đã hoàn thành'),
(7, N'nguyenvana', '2022-02-10', N'72 Trần Hưng Đạo, Quận 1, TP. HCM', N'nguyenvana@gmail.com', '0123456789', N'Chờ xử lý'),
(8, N'nguyenvana', '2022-02-12', N'14 Tôn Thất Đạm, Quận 1, TP. HCM', N'nguyenvana@gmail.com', '0123456789', N'Đã hoàn thành'),
(9, N'nguyenvanbao', '2022-02-13', N'102 Lý Tự Trọng, Quận 1, TP. HCM', N'nguyenvanbao@gmail.com', '0123456789', N'Đã hoàn thành'),
(10, N'lethuhien', '2022-02-15', N'63 Phan Đình Phùng, Quận Phú Nhuận, TP. HCM', N'lethuhien@gmail.com', '0123456789', N'Đã hoàn thành'),
(11, N'phamthuc', '2022-02-17', N'8 Nguyễn Trãi, Quận 5, TP. HCM', N'phamthuc@gmail.com', '0123456789', N'Chờ xử lý'),
(12, N'lethuhien', '2022-02-18', N'59 Cao Thắng, Quận 10, TP. HCM', N'lethuhien@gmail.com', '0123456789', N'Đã hoàn thành'),
(13, N'tranvanb', '2022-02-19', N'22 Nguyễn Công Trứ, Quận 1, TP. HCM', N'tranvanb@gmail.com', '0123456789', N'Đã hoàn thành'),
(14, N'phamthuc', '2022-02-21', N'17 Trần Nhật Duật, Quận 1, TP. HCM', N'phamthuc@gmail.com', '0123456789', N'Đã hoàn thành'),
(15, N'phamthuc', '2022-02-23', N'3 Điện Biên Phủ, Quận 1, TP. HCM', N'phamthuc@gmail.com', '0123456789', N'Chờ xử lý'),
(16, N'dangkimchi', '2022-02-25', N'61 Nguyễn Cư Trinh, Quận 1, TP. HCM', N'dangkimchi@gmail.com', '0123456789', N'Đã hoàn thành'),
(17, N'nguyenvana', '2022-02-27', N'22 Tôn Thất Tùng, Quận 1, TP. HCM', N'nguyenvana@gmail.com', '0123456789', N'Đã hoàn thành'),
(18, N'dangkimchi', '2022-02-28', N'99 Trần Quang Khải, Quận 1, TP. HCM', N'dangkimchi@gmail.com', '0123456789', N'Chờ xử lý'),
(19, N'lethuhien', '2022-03-01', N'48 Nguyễn Thị Minh Khai, Quận 1, TP. HCM', N'lethuhien@gmail.com', '0123456789', N'Đã hoàn thành'),
(20, N'lethuhien', '2022-03-02', N'92 Đinh Tiên Hoàng, Quận 1, TP. HCM', N'lethuhien@gmail.com', '0123456789', N'Đã hoàn thành')
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Products] ON 
GO

INSERT INTO [dbo].[Products] ([Id], [Name], [Poster], [Thumbnail], [Price], [CreateDate], [Available], [Source], [Link], [Details], [CategoryId]) 
VALUES 
(1, N'Assassin Creed Valhalla', N'Valhalla.jpg', N'Valhalla-thumb.jpg-*-Valhalla-thumb2.jpg-*-Valhalla-thumb3.jpg', 200000, '2021-01-10', 1, N'Ubisoft', 'https://uploadhaven.com/download/21e3c5f14bc4f127992c6cc914ba6756', N'Assassin Creed Valhalla là phiên bản mới nhất của dòng game hành động nhập vai lịch sử nổi tiếng Assassin Creed. Trong game, người chơi sẽ hóa thân thành Eivor, một vị vua hoặc nữ hoàng của người Viking, và tham gia vào cuộc chiến giữa người Viking và quân đội của người Anh vào thế kỷ thứ 9.', 'HD'),
(2, N'Grand Theft Auto V', N'GTA.jpg', N'GTA-thumb.jpg-*-GTA-thumb2.jpg-*-GTA-thumb3.jpg', 114000, '2021-02-15', 1, N'Rockstar Games', 'http://phanmemnet.com/download-gta-5-viet-hoa-full-link-google-drive-grand-theft-auto-v1-50/', N'Grand Theft Auto V là phiên bản thứ 5 trong loạt game Grand Theft Auto. Trong game, người chơi sẽ được đưa đến thành phố hư cấu Los Santos và có thể tham gia vào các nhiệm vụ để kiếm tiền và trở thành tay mafia lừng danh.', 'HD'),
(3, N'Resident Evil Village', N'REV.jpg', N'REV-thumb.jpg-*-REV-thumb2.jpg-*-REV-thumb3.png', 150000, '2021-04-23', 1, N'Capcom', 'https://khiphach.info/tai-resident-evil-village-full/', N'Resident Evil Village là phiên bản mới nhất trong loạt game kinh dị Resident Evil. Trong game, người chơi sẽ hóa thân thành Ethan Winters, nhân vật chính của phần 7, và tham gia vào cuộc chiến với những sinh vật kinh dị để cứu người vợ của mình.', 'KD'),
(4, N'FIFA 22', N'FF22.png', N'FF22-thumb.png-*-FF22-thumb2.png-*-FF22-thumb3.png', 432000, '2021-09-27', 1, N'Electronic Arts', 'https://dtvc.edu.vn/cach-choi-fifa-22-mien-phi-phien-ban-moi-nhat-tren-steam-how-to-play-fifa-22-for-free-on-pc/', N'FIFA 22 là phiên bản mới nhất của loạt game bóng đá FIFA. Trong game, người chơi sẽ được trải nghiệm các giải đấu bóng đá hàng đầu thế giới và cạnh tranh với các đội bóng khác để giành chiến thắng.', 'TT'),
(5, N'The Witcher 3: Wild Hunt', N'WC3.jpg', N'WC3-thumb.jpeg-*-WC3-thumb2.jpg-*-WC3-thumb3.jpg', 70000, '2021-03-01', 1, N'Steam', 'https://uploadhaven.com/download/f26c63c29101c0571e8b945dd01babdc', N'The Witcher 3: Wild Hunt là một trò chơi nhập vai thế giới mở dựa trên câu chuyện, được thiết lập trong một vũ trụ huyền bí đầy hấp dẫn về lựa chọn có ý nghĩa và hậu quả tác động. Trong The Witcher, bạn vào vai Geralt of Rivia, một thợ săn quái vật chuyên nghiệp được giao nhiệm vụ tìm kiếm một đứa trẻ của tiên tri trong một thế giới mở rộng lớn với những thành phố thương gia, các hòn đảo cướp biển, những đường đèo núi nguy hiểm và những hang động bị lãng quên để khám phá.', 'NV'),
(6, N'Minecraft', N'MC.jpg', N'MC-thumb.jpg-*-MC-thumb2.jpg-*-MC-thumb3.jpg', 299000, '2022-02-15', 1, N'Mojang', 'https://minefc.com/tai-game/', N'Game sinh tồn thế giới mở.', 'MP'),
(7, N'Genshin Impact', N'GI.jpg', N'GI-thumb.jpg-*-GI-thumb2.jpg-*-GI-thumb3.jpg', 200000, '2022-02-12', 1, N'Mihoyo', 'https://genshin.hoyoverse.com/pc-launcher/?utm_source=SEA_google_NZMY_SEM_brand_1022&mhy_trace_channel=ga_channel&new=1&gclid=CjwKCAjw5pShBhB_EiwAvmnNV6GJFce63Vpo1M5LlCEgCJqASjGBBLeVsTKAdQG3NUDasg-bd3KKhhoC3FYQAvD_BwE#/', N'Game nhập vai phiêu lưu thế giới mở đầy hấp dẫn.', 'PL'),
(8, N'Among Us', N'AU.jpg', N'AU-thumb.jpg-*-AU-thumb2.jpg-*-AU-thumb3.jpg', 59900, '2022-02-08', 1, N'InnerSloth', 'https://www.memuplay.com/vi/how-to-play-Among-Us-on-pc.html', N'Game trinh thám nhiệm vụ thực hiện trong tàu vũ trụ.', 'CT'),
(9, N'League of Legends', N'LOL.jpg', N'LOL-thumb.jpg-*-LOL-thumb2.png-*-LOL-thumb3.webp', 50000, '2022-02-06', 1, N'Riot Games', 'https://lienminh.vnggames.com/dang-ky/redownload/', N'Game chiến đấu 5v5 eSports phong cách MOBA.', 'MOBA'),
(10, N'Diablo III', N'DIABLO.jpg', N'DB-thumb.jpg-*-DB-thumb2.jpg-*-Db-thumb3.jpg', 199000, '2022-02-03', 1, N'Blizzard Entertainment', 'https://technetvietnam.net/download-diablo-3/', N'Game nhập vai hành động phiêu lưu.', 'NV'),
(11, N'Counter Strike: Global Offensive', N'CSGO.jpg', N'CSGO-thumb.png-*-CSGO-thumb2.webp-*-CSGO-thumb3.webp', 300000, '2022-01-29', 1, N'Valve Corporation', 'https://hadoantv.com/counter-strike-global-offensive-online/', N'Game bắn súng trực tuyến FPS.', 'FPS'),
(12, N'Dota 2', N'DOTA.jpg', N'DOTA-thumb.jpg-*-DOTA-thumb2.jpg-*-DOTA-thumb3.jpg', 150000, '2022-01-27', 1, N'Valve Corporation', 'https://dota-2.en.softonic.com/download', N'Game chiến đấu eSports phong cách MOBA.', 'MOBA')

SET IDENTITY_INSERT [dbo].[Products] OFF
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'CUST', N'Người Dùng')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'DIRE', N'Quản Lý')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STAF', N'Nhân Viên')


ALTER TABLE [dbo].[Accounts] ADD  CONSTRAINT [DF_Customers_Photo]  DEFAULT (N'Photo.gif') FOR [Photo]
GO
ALTER TABLE [dbo].[OrderDetails] ADD  CONSTRAINT [DF_Order_Details_UnitPrice]  DEFAULT ((0)) FOR [Price]
GO
ALTER TABLE [dbo].[Orders] ADD  CONSTRAINT [DF_Orders_OrderDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_Poster]  DEFAULT (N'Poster.jpg') FOR [Poster]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_Thumbnail]  DEFAULT (N'Thumbnail.jpg') FOR [Thumbnail]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_UnitPrice]  DEFAULT ((0)) FOR [Price]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_ProductDate]  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_Available]  DEFAULT ((1)) FOR [Available]
GO

ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_UserRoles_Roles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_UserRoles_Roles]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_UserRoles_Users] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_UserRoles_Users]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Customers] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Customers]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK3ess0s7i9qs6sim1pf9kxhkpq] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK3ess0s7i9qs6sim1pf9kxhkpq]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã khách hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Accounts', @level2type=N'COLUMN',@level2name=N'Username'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mật khẩu đăng nhập' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Accounts', @level2type=N'COLUMN',@level2name=N'Password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Họ và tên' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Accounts', @level2type=N'COLUMN',@level2name=N'Fullname'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Email' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Accounts', @level2type=N'COLUMN',@level2name=N'Email'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hình ảnh' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Accounts', @level2type=N'COLUMN',@level2name=N'Photo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã loại' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Categories', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên tiếng Việt' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Categories', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã chi tiết' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderDetails', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hóa đơn' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderDetails', @level2type=N'COLUMN',@level2name=N'OrderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hàng hóa' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderDetails', @level2type=N'COLUMN',@level2name=N'ProductId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Đơn giá bán' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderDetails', @level2type=N'COLUMN',@level2name=N'Price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hóa đơn' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Orders', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã khách hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Orders', @level2type=N'COLUMN',@level2name=N'Username'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày đặt hàng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Orders', @level2type=N'COLUMN',@level2name=N'CreateDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Địa chỉ nhận' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Orders', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã hàng hóa' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Tên hàng hóa' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hình ảnh Poster' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Poster'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Hình ảnh Thumbnail' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Thumbnail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Đơn giá' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ngày sản xuất' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'CreateDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Đang kinh doanh ?' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'Available'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Mã loại, FK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Products', @level2type=N'COLUMN',@level2name=N'CategoryId'
GO
