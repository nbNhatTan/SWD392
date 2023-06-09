USE [master]
GO
/****** Object:  Database [FPTLibrary]    Script Date: 16/03/2023 10:41:12 SA ******/
CREATE DATABASE [FPTLibrary]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FPTLibrary', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\FPTLibrary.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'FPTLibrary_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\FPTLibrary_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [FPTLibrary] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FPTLibrary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FPTLibrary] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FPTLibrary] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FPTLibrary] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FPTLibrary] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FPTLibrary] SET ARITHABORT OFF 
GO
ALTER DATABASE [FPTLibrary] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [FPTLibrary] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FPTLibrary] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FPTLibrary] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FPTLibrary] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FPTLibrary] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FPTLibrary] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FPTLibrary] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FPTLibrary] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FPTLibrary] SET  ENABLE_BROKER 
GO
ALTER DATABASE [FPTLibrary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FPTLibrary] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FPTLibrary] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FPTLibrary] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FPTLibrary] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FPTLibrary] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FPTLibrary] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FPTLibrary] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FPTLibrary] SET  MULTI_USER 
GO
ALTER DATABASE [FPTLibrary] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FPTLibrary] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FPTLibrary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FPTLibrary] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [FPTLibrary] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [FPTLibrary] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [FPTLibrary] SET QUERY_STORE = OFF
GO
USE [FPTLibrary]
GO
/****** Object:  Table [dbo].[tblAccounts]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccounts](
	[AccountID] [nvarchar](20) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[password] [nvarchar](64) NOT NULL,
	[roleID] [int] NOT NULL,
	[email] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](15) NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblAuthors]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAuthors](
	[authorID] [int] IDENTITY(1,1) NOT NULL,
	[authorName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblAuthors] PRIMARY KEY CLUSTERED 
(
	[authorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBook]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBook](
	[bookID] [nvarchar](100) NOT NULL,
	[bookName] [nvarchar](100) NOT NULL,
	[quantity] [int] NOT NULL,
	[bookshelf] [nvarchar](100) NULL,
	[languageID] [int] NOT NULL,
	[description] [nvarchar](500) NULL,
	[DDC] [nvarchar](20) NOT NULL,
	[authorID] [int] NOT NULL,
	[publisherID] [int] NOT NULL,
	[publishYear] [nvarchar](4) NULL,
	[image] [nvarchar](100) NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblBook] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBookingTicket]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookingTicket](
	[bookingTicketID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nvarchar](20) NOT NULL,
	[bookItemID] [nvarchar](20) NOT NULL,
	[borrowDate] [datetime] NOT NULL,
	[expiredDate] [datetime] NOT NULL,
	[returnDate] [datetime] NULL,
	[borrowStatus] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tblbookingTicket] PRIMARY KEY CLUSTERED 
(
	[bookingTicketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBookItem]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookItem](
	[bookItemID] [nvarchar](20) NOT NULL,
	[bookID] [nvarchar](100) NOT NULL,
	[bookStatus] [nvarchar](30) NULL,
	[packageID] [int] NOT NULL,
 CONSTRAINT [PK_tblBookItem] PRIMARY KEY CLUSTERED 
(
	[bookItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBookTag]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookTag](
	[categoryID] [int] NOT NULL,
	[bookID] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tblBookTag] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC,
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCategories]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategories](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
	[description] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategories] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblFeedback]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFeedback](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[bookID] [nvarchar](100) NULL,
	[userID] [nvarchar](20) NOT NULL,
	[comment] [nvarchar](500) NOT NULL,
	[star] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[feedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblLanguages]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblLanguages](
	[languageID] [int] IDENTITY(1,1) NOT NULL,
	[languageName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblLanguages] PRIMARY KEY CLUSTERED 
(
	[languageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblNews]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblNews](
	[newsID] [int] IDENTITY(1,1) NOT NULL,
	[writerName] [nvarchar](50) NULL,
	[title] [nvarchar](200) NOT NULL,
	[head] [nvarchar](500) NOT NULL,
	[body] [nvarchar](4000) NOT NULL,
	[staffID] [nvarchar](20) NOT NULL,
	[uploadDate] [datetime] NOT NULL,
 CONSTRAINT [PK_tblNews] PRIMARY KEY CLUSTERED 
(
	[newsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPackage]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPackage](
	[packageID] [int] IDENTITY(1,1) NOT NULL,
	[packageName] [nvarchar](20) NOT NULL,
	[price] [int] NOT NULL,
	[importDate] [datetime] NOT NULL,
 CONSTRAINT [PK_tblPackage] PRIMARY KEY CLUSTERED 
(
	[packageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPublishers]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPublishers](
	[publisherID] [int] IDENTITY(1,1) NOT NULL,
	[publisherName] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblPublishers] PRIMARY KEY CLUSTERED 
(
	[publisherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStaffTicket]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStaffTicket](
	[staffID] [nvarchar](20) NOT NULL,
	[ticketID] [int] NOT NULL,
 CONSTRAINT [PK_tblStaffTicket] PRIMARY KEY CLUSTERED 
(
	[ticketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblViolationTicket]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblViolationTicket](
	[violationTicketID] [int] IDENTITY(1,1) NOT NULL,
	[bookingTicketID] [int] NOT NULL,
	[createDate] [datetime] NOT NULL,
	[description] [nvarchar](500) NULL,
	[ticketStatus] [bit] NOT NULL,
	[staffID] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tblViolationTicket] PRIMARY KEY CLUSTERED 
(
	[violationTicketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblWishList]    Script Date: 16/03/2023 10:41:12 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblWishList](
	[bookID] [nvarchar](100) NOT NULL,
	[userID] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_tblWishList] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC,
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblAccounts]  WITH CHECK ADD  CONSTRAINT [FK__tblUser__roleID__267ABA7A] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblAccounts] CHECK CONSTRAINT [FK__tblUser__roleID__267ABA7A]
GO
ALTER TABLE [dbo].[tblBook]  WITH CHECK ADD  CONSTRAINT [FK__tblBook__authorI__31EC6D26] FOREIGN KEY([authorID])
REFERENCES [dbo].[tblAuthors] ([authorID])
GO
ALTER TABLE [dbo].[tblBook] CHECK CONSTRAINT [FK__tblBook__authorI__31EC6D26]
GO
ALTER TABLE [dbo].[tblBook]  WITH CHECK ADD  CONSTRAINT [FK__tblBook__languag__30F848ED] FOREIGN KEY([languageID])
REFERENCES [dbo].[tblLanguages] ([languageID])
GO
ALTER TABLE [dbo].[tblBook] CHECK CONSTRAINT [FK__tblBook__languag__30F848ED]
GO
ALTER TABLE [dbo].[tblBook]  WITH CHECK ADD  CONSTRAINT [FK__tblBook__publish__32E0915F] FOREIGN KEY([publisherID])
REFERENCES [dbo].[tblPublishers] ([publisherID])
GO
ALTER TABLE [dbo].[tblBook] CHECK CONSTRAINT [FK__tblBook__publish__32E0915F]
GO
ALTER TABLE [dbo].[tblBookingTicket]  WITH CHECK ADD  CONSTRAINT [FK__tblbookin__bookI__3D5E1FD2] FOREIGN KEY([bookItemID])
REFERENCES [dbo].[tblBookItem] ([bookItemID])
GO
ALTER TABLE [dbo].[tblBookingTicket] CHECK CONSTRAINT [FK__tblbookin__bookI__3D5E1FD2]
GO
ALTER TABLE [dbo].[tblBookingTicket]  WITH CHECK ADD  CONSTRAINT [FK__tblbookin__userI__3B75D760] FOREIGN KEY([userID])
REFERENCES [dbo].[tblAccounts] ([AccountID])
GO
ALTER TABLE [dbo].[tblBookingTicket] CHECK CONSTRAINT [FK__tblbookin__userI__3B75D760]
GO
ALTER TABLE [dbo].[tblBookItem]  WITH CHECK ADD  CONSTRAINT [FK__tblBookIt__bookI__35BCFE0A] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBook] ([bookID])
GO
ALTER TABLE [dbo].[tblBookItem] CHECK CONSTRAINT [FK__tblBookIt__bookI__35BCFE0A]
GO
ALTER TABLE [dbo].[tblBookItem]  WITH CHECK ADD  CONSTRAINT [FK_tblBookItem_tblPackage] FOREIGN KEY([packageID])
REFERENCES [dbo].[tblPackage] ([packageID])
GO
ALTER TABLE [dbo].[tblBookItem] CHECK CONSTRAINT [FK_tblBookItem_tblPackage]
GO
ALTER TABLE [dbo].[tblBookTag]  WITH CHECK ADD  CONSTRAINT [FK_tblBookTag_tblBook] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBook] ([bookID])
GO
ALTER TABLE [dbo].[tblBookTag] CHECK CONSTRAINT [FK_tblBookTag_tblBook]
GO
ALTER TABLE [dbo].[tblBookTag]  WITH CHECK ADD  CONSTRAINT [FK_tblBookTag_tblCategories] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategories] ([categoryID])
GO
ALTER TABLE [dbo].[tblBookTag] CHECK CONSTRAINT [FK_tblBookTag_tblCategories]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK__tblFeedba__bookI__6FE99F9F] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBook] ([bookID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK__tblFeedba__bookI__6FE99F9F]
GO
ALTER TABLE [dbo].[tblNews]  WITH CHECK ADD  CONSTRAINT [FK__tblNews__userID__440B1D61] FOREIGN KEY([staffID])
REFERENCES [dbo].[tblAccounts] ([AccountID])
GO
ALTER TABLE [dbo].[tblNews] CHECK CONSTRAINT [FK__tblNews__userID__440B1D61]
GO
ALTER TABLE [dbo].[tblStaffTicket]  WITH CHECK ADD  CONSTRAINT [FK_tblStaffBooking_tblAccounts] FOREIGN KEY([staffID])
REFERENCES [dbo].[tblAccounts] ([AccountID])
GO
ALTER TABLE [dbo].[tblStaffTicket] CHECK CONSTRAINT [FK_tblStaffBooking_tblAccounts]
GO
ALTER TABLE [dbo].[tblStaffTicket]  WITH CHECK ADD  CONSTRAINT [FK_tblStaffBooking_tblbookingTicket] FOREIGN KEY([ticketID])
REFERENCES [dbo].[tblBookingTicket] ([bookingTicketID])
GO
ALTER TABLE [dbo].[tblStaffTicket] CHECK CONSTRAINT [FK_tblStaffBooking_tblbookingTicket]
GO
ALTER TABLE [dbo].[tblViolationTicket]  WITH CHECK ADD  CONSTRAINT [FK__tblViolat__booki__403A8C7D] FOREIGN KEY([bookingTicketID])
REFERENCES [dbo].[tblBookingTicket] ([bookingTicketID])
GO
ALTER TABLE [dbo].[tblViolationTicket] CHECK CONSTRAINT [FK__tblViolat__booki__403A8C7D]
GO
ALTER TABLE [dbo].[tblViolationTicket]  WITH CHECK ADD  CONSTRAINT [FK__tblViolat__staff__412EB0B6] FOREIGN KEY([staffID])
REFERENCES [dbo].[tblAccounts] ([AccountID])
GO
ALTER TABLE [dbo].[tblViolationTicket] CHECK CONSTRAINT [FK__tblViolat__staff__412EB0B6]
GO
ALTER TABLE [dbo].[tblWishList]  WITH CHECK ADD  CONSTRAINT [FK_tblWishList_tblAccounts] FOREIGN KEY([userID])
REFERENCES [dbo].[tblAccounts] ([AccountID])
GO
ALTER TABLE [dbo].[tblWishList] CHECK CONSTRAINT [FK_tblWishList_tblAccounts]
GO
ALTER TABLE [dbo].[tblWishList]  WITH CHECK ADD  CONSTRAINT [FK_tblWishList_tblBook] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBook] ([bookID])
GO
ALTER TABLE [dbo].[tblWishList] CHECK CONSTRAINT [FK_tblWishList_tblBook]
GO
USE [master]
GO
ALTER DATABASE [FPTLibrary] SET  READ_WRITE 
GO
