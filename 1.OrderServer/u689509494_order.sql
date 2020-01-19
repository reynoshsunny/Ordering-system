-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 23, 2019 at 05:39 PM
-- Server version: 10.2.23-MariaDB
-- PHP Version: 7.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u689509494_order`
--

-- --------------------------------------------------------

--
-- Table structure for table `ADMIN`
--

CREATE TABLE `ADMIN` (
  `ADMIN_ID` int(11) NOT NULL,
  `ADMIN_NAME` varchar(30) NOT NULL,
  `ADMIN_ADDRESS` varchar(250) NOT NULL,
  `ADMIN_PHONE` bigint(12) NOT NULL,
  `ADMIN_EMAIL` varchar(50) NOT NULL,
  `ADMIN_PASSWORD` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ADMIN`
--

INSERT INTO `ADMIN` (`ADMIN_ID`, `ADMIN_NAME`, `ADMIN_ADDRESS`, `ADMIN_PHONE`, `ADMIN_EMAIL`, `ADMIN_PASSWORD`) VALUES
(1, 'Admin', 'Thrissur', 1111111111, 'admin@gmail.com', 'admin1234'),
(3, 'Reynosh', 'Test address', 9656455864, 'Reynosh@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `M_CUSTOMERS`
--

CREATE TABLE `M_CUSTOMERS` (
  `CUS_ID` bigint(20) NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `ADDRESS` varchar(250) NOT NULL,
  `PLACE` varchar(50) NOT NULL,
  `PINCODE` int(6) NOT NULL,
  `PHONE_NO` bigint(20) NOT NULL,
  `EMAIL_ID` varchar(50) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `X_CORD` varchar(250) NOT NULL,
  `Y_CORD` varchar(250) NOT NULL,
  `REFERALCD` varchar(100) NOT NULL,
  `JOINDT` varchar(30) NOT NULL,
  `ACTIVE` int(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `M_CUSTOMERS`
--

INSERT INTO `M_CUSTOMERS` (`CUS_ID`, `NAME`, `ADDRESS`, `PLACE`, `PINCODE`, `PHONE_NO`, `EMAIL_ID`, `PASSWORD`, `X_CORD`, `Y_CORD`, `REFERALCD`, `JOINDT`, `ACTIVE`) VALUES
(12, 'MANJU', 'THRISSUR KERALA', 'KKM', 0, 1212123311, 'manu@gmail.com', 'manu', 'x', 'y', 'aaqtt', '10-01-2019', 1),
(14, 'MANJU', 'Address ', 'place', 0, 9600001, 'manju@gmail.com', 'manju', 'x', 'y', 'aaqtt', '10-01-2019', 1),
(16, 'AISHWARYA', 'asdsds', 'dsd', 2323, 1234567899, 'aishwarya@gmail.com', '1234', 'x', 'y', 'aaqwdsw', '2019-04-12', 1),
(17, 'Reynosh Sunny', 'THRISSUR KERALA', 'Test Place', 0, 91234567, 'test@gmail.com', 'rey', 'xxxxxxx', 'xxxxxxx', 'JUI67J', '2019-05-23', 1),
(18, 'ANU', 'THRISSUR KERALA', 'FGFG', 0, 9685744574, 'anu@gmail.com', 'anu', 'x', 'y', 'yy', '2019-04-12', 1),
(22, 'VINIL   vincent', 'THRISSUR KERALA', 'KKM', 0, 968574, 'anu@gmail.com', 'vinil', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(32, 'VINIL   vincent', 'Kochi,Kerala', 'Althara', 0, 9685748, 'anu@gmail.comd', 'vinil32', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(34, 'VINIL   vincent', 'THRISSUR KERALA,India', 'KKM', 0, 100123, 'anu@gmail.comd', 'vinil456', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(40, 'Kavitha', 'Kochi', 'Kalur', 0, 102474, 'kavitha@gmail.comd', 'kavi', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(38, 'Ram', 'THRISSUR KERALA', 'place', 0, 100, 'ram@gmail.com', 'ram123', 'x', 'y', 'HD7U2DOI', '', 1),
(39, 'Anna', 'thrissur', 'chavakkad', 0, 968574822, 'anna@gmail.comd', 'anna', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(41, 'Vini', 'THRISSUR', 'Thrissur', 680524, 7891236489, 'vini@gmail.com', 'vini', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(42, 'Vinila', 'THRISSUR', 'Thrissur', 680524, 6805242365, 'viniii@gmail.com', 'vini', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(43, 'Ani', 'THRISSUR', 'Thrissur', 680578, 4569874569, 'ani@gmail.com', 'vini', 'x', 'y', 'HD7U2DOI', '2019-05-23', 1),
(44, 'REYNOSH', 'test', 'test', 236589, 9656455864, 'r@c.c', '1234', 'TEST X', 'TEST Y', 'HD7U2DOI', '2019-05-23', 1),
(45, 'Manju Gavin', 'Mampet House', 'Althara', 679561, 7034105588, 'manjuin@gmail.com', 'manju', 'TEST X', 'TEST Y', 'HD7U2DOI', '2019-05-23', 1),
(46, 'Haripriya', 'kkkk', '', 680502, 9876543210, 'kochualfy@gmail.com', 'kochu999', '', '', 'HD7U2DOI', '2019-05-23', 1),
(47, 'Vinil', 'aaaa', 'aaa', 679562, 9605658525, 'aaa@gmail.com', '12345', 'TEST X', 'TEST Y', 'HD7U2DOI', '2019-05-23', 1);

-- --------------------------------------------------------

--
-- Table structure for table `M_PINCODES`
--

CREATE TABLE `M_PINCODES` (
  `PINCODE_ID` bigint(20) NOT NULL,
  `PINCODE` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `PLACE` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `M_PINCODES`
--

INSERT INTO `M_PINCODES` (`PINCODE_ID`, `PINCODE`, `PLACE`) VALUES
(1, '679562', 'VADAKKEKAD'),
(2, '123456', 'ANJOOR');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ADMIN`
--
ALTER TABLE `ADMIN`
  ADD PRIMARY KEY (`ADMIN_ID`);

--
-- Indexes for table `M_CUSTOMERS`
--
ALTER TABLE `M_CUSTOMERS`
  ADD PRIMARY KEY (`CUS_ID`);

--
-- Indexes for table `M_PINCODES`
--
ALTER TABLE `M_PINCODES`
  ADD PRIMARY KEY (`PINCODE_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ADMIN`
--
ALTER TABLE `ADMIN`
  MODIFY `ADMIN_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `M_CUSTOMERS`
--
ALTER TABLE `M_CUSTOMERS`
  MODIFY `CUS_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `M_PINCODES`
--
ALTER TABLE `M_PINCODES`
  MODIFY `PINCODE_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
