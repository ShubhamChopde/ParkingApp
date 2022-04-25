-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2022 at 07:31 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `api`
--

-- --------------------------------------------------------

--
-- Table structure for table `p_register`
--

CREATE TABLE `p_register` (
  `id` int(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `mobile` int(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `slots` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `p_register`
--

INSERT INTO `p_register` (`id`, `name`, `mobile`, `email`, `password`, `address`, `slots`) VALUES
(1, 'Shubham', 808742393, 'shubhamchopde0@gmail.com', 'chopde', 'tapi Nagar', 500);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `mobile` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `name`, `mobile`, `email`, `username`, `password`) VALUES
(1, 'Rohit', '9869149859', 'rohitjana@gmail.com', 'Rohit123', 'Rohit@123'),
(2, 'Satyam', '9930050144', 'satyamkothawade@gmail.com', 'Satyam12', 'Satyam@12'),
(7, 'Shreyas', '8087423938', 'shreyasrane@gmail.com', 'Shreyas123', 'Shreyas@123');

-- --------------------------------------------------------

--
-- Table structure for table `tuf`
--

CREATE TABLE `tuf` (
  `id` int(11) NOT NULL,
  `owner_id` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `v_model` varchar(100) NOT NULL,
  `v_no` varchar(100) NOT NULL,
  `mobile` bigint(100) NOT NULL,
  `time_slot` bigint(100) NOT NULL,
  `Amount` bigint(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tuf`
--

INSERT INTO `tuf` (`id`, `owner_id`, `user_name`, `name`, `v_model`, `v_no`, `mobile`, `time_slot`, `Amount`) VALUES
(5, 'tuf', 'Shreyas', 'Shreyas', 'KTM', 'MH04GH6352', 9832456176, 2, 20);

-- --------------------------------------------------------

--
-- Table structure for table `tufreceipt`
--

CREATE TABLE `tufreceipt` (
  `id` bigint(200) NOT NULL,
  `owner_id` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `v_model` varchar(200) NOT NULL,
  `v_no` varchar(200) NOT NULL,
  `mobile` bigint(200) NOT NULL,
  `time_slot` bigint(200) NOT NULL,
  `Amount` bigint(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tufreceipt`
--

INSERT INTO `tufreceipt` (`id`, `owner_id`, `user_name`, `name`, `v_model`, `v_no`, `mobile`, `time_slot`, `Amount`) VALUES
(1, 'tuf', 'Satyam', 'Satyam', 'activa', 'MH05NM5003', 9845678231, 1, 10),
(3, 'tuf', 'Shreyas', 'Shreyas', 'KTM', 'MH04GH6352', 9832456176, 2, 20),
(14, 'tuf', 'varun', 'varun', 'apache', 'Mh19VB8901', 8799579875, 2, 20);

-- --------------------------------------------------------

--
-- Table structure for table `tui`
--

CREATE TABLE `tui` (
  `id` bigint(200) NOT NULL,
  `owner_id` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `v_model` varchar(200) NOT NULL,
  `v_no` varchar(200) NOT NULL,
  `mobile` bigint(200) NOT NULL,
  `time_slot` bigint(200) NOT NULL,
  `Amount` bigint(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tui`
--

INSERT INTO `tui` (`id`, `owner_id`, `user_name`, `name`, `v_model`, `v_no`, `mobile`, `time_slot`, `Amount`) VALUES
(1, 'tui', 'Shubham4440', 'Shubham', 'Access', 'MH19BD8901', 8087423938, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tuireceipt`
--

CREATE TABLE `tuireceipt` (
  `id` bigint(200) NOT NULL,
  `owner_id` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `v_model` varchar(200) NOT NULL,
  `v_no` varchar(200) NOT NULL,
  `mobile` bigint(200) NOT NULL,
  `time_slot` bigint(200) NOT NULL,
  `Amount` bigint(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tuireceipt`
--

INSERT INTO `tuireceipt` (`id`, `owner_id`, `user_name`, `name`, `v_model`, `v_no`, `mobile`, `time_slot`, `Amount`) VALUES
(1, 'tui', 'Shubham4440', 'Shubham', 'Access', 'MH19BD8901', 8087423938, 1, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `p_register`
--
ALTER TABLE `p_register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tuf`
--
ALTER TABLE `tuf`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tufreceipt`
--
ALTER TABLE `tufreceipt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tui`
--
ALTER TABLE `tui`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tuireceipt`
--
ALTER TABLE `tuireceipt`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `p_register`
--
ALTER TABLE `p_register`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tuf`
--
ALTER TABLE `tuf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `tufreceipt`
--
ALTER TABLE `tufreceipt`
  MODIFY `id` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tui`
--
ALTER TABLE `tui`
  MODIFY `id` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `tuireceipt`
--
ALTER TABLE `tuireceipt`
  MODIFY `id` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
