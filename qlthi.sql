-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 28, 2018 at 02:18 AM
-- Server version: 5.7.21
-- PHP Version: 7.0.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlthi`
--

-- --------------------------------------------------------

--
-- Table structure for table `cauhoi`
--

DROP TABLE IF EXISTS `cauhoi`;
CREATE TABLE IF NOT EXISTS `cauhoi` (
  `STT` int(11) NOT NULL AUTO_INCREMENT,
  `NoiDung` text COLLATE utf8_unicode_ci NOT NULL,
  `CauTraLoiDung` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`STT`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cauhoi`
--

INSERT INTO `cauhoi` (`STT`, `NoiDung`, `CauTraLoiDung`) VALUES
(1, 'OOP trong lập trình hướng đối tượng là viết tắt của chữ nào?', 'Object-Oriented Programming'),
(2, '4 tính chất của lập trình hướng đối tượng', 'Tính đóng gói, tình đa hình, tính kế thừa, tính trừu tượng'),
(3, 'Các thành phần của 1 class?', 'Dữ liệu, phương thức'),
(4, '3 cơ chế của OOP', 'Public, Protected, Private'),
(5, 'Nói ngắn gọn về Lập trình hướng đối tượng là gì?', 'Nó là phương pháp lập trình qui tất cả về 1 đối tượng, khi cần chỉ việc lấy ra sử dụng');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `Username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Type` int(11) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`Username`, `Password`, `Type`) VALUES
('gv01', '123456', 1),
('hs01', '123456', 2),
('hs02', '123456', 2);

-- --------------------------------------------------------

--
-- Table structure for table `traloi`
--

DROP TABLE IF EXISTS `traloi`;
CREATE TABLE IF NOT EXISTS `traloi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SttCauHoi` int(11) NOT NULL,
  `CauTraLoi` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `traloi`
--

INSERT INTO `traloi` (`ID`, `Username`, `SttCauHoi`, `CauTraLoi`) VALUES
(1, 'hs01', 1, 'df'),
(2, 'hs01', 2, 'dsf'),
(3, 'hs01', 3, 'dsf'),
(4, 'hs01', 4, 'sdf'),
(5, 'hs01', 5, 'sdf'),
(6, 'hs01', 1, 'Oh yeah aaa..'),
(7, 'hs01', 2, 'Oh yeahhhhhh cmon'),
(8, 'hs01', 3, 'aa kimochi ii.....'),
(9, 'hs01', 4, 'motto motto hayaku'),
(10, 'hs01', 5, 'yeah oh yeah yes yes yesssssssss!!! ikuuuuuuuuuuuu');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
