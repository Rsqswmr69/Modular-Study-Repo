-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 01, 2020 at 07:37 PM
-- Server version: 8.0.13-4
-- PHP Version: 7.2.24-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nEHRVrOue2`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories_tbl`
--

CREATE TABLE `categories_tbl` (
  `category_id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories_tbl`
--

INSERT INTO `categories_tbl` (`category_id`, `category`) VALUES
(1, 'Geography'),
(2, 'Math'),
(3, 'History');

-- --------------------------------------------------------

--
-- Table structure for table `choices_tbl`
--

CREATE TABLE `choices_tbl` (
  `choice_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `choice_text` text NOT NULL,
  `is_correct` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `choices_tbl`
--

INSERT INTO `choices_tbl` (`choice_id`, `question_id`, `choice_text`, `is_correct`) VALUES
(1, 1, 'Pacific Ocean', 1),
(2, 1, 'Artic Ocean', 0),
(3, 1, 'Atlantic Ocean', 0),
(4, 2, '51', 0),
(5, 2, '50', 1),
(6, 2, '49', 0),
(7, 3, '10', 0),
(8, 3, '5', 0),
(9, 3, '7', 1),
(10, 1, 'Indian Ocean', 0),
(11, 2, '52', 0),
(12, 3, '8', 0),
(13, 4, 'Celebes Sea', 0),
(14, 4, 'Mediterranean Sea', 0),
(15, 4, 'Sargasso Sea', 1),
(16, 4, 'Adriatic Sea', 0),
(17, 5, '83%', 0),
(18, 5, '100%', 0),
(19, 5, '9%', 0),
(20, 5, '22%', 1),
(21, 6, 'Atacama Desert', 0),
(22, 6, 'McMurdo Dry Valleys', 1),
(23, 6, 'Sahara Desert', 0),
(24, 6, 'Kufra, Libya', 0),
(25, 7, 'Peru', 1),
(26, 7, 'Chile', 0),
(27, 7, 'Columbia', 0),
(28, 7, 'Bolivia', 0),
(29, 8, 'Egypt', 0),
(30, 8, 'Algeria', 0),
(31, 8, 'Libya', 0),
(32, 8, 'Sudan', 1),
(33, 9, 'Gabon', 0),
(34, 9, 'Tunisia', 1),
(35, 9, 'Ghana', 0),
(36, 9, 'Ethiopia', 0),
(37, 10, 'Tigris', 1),
(38, 10, 'Karun', 0),
(39, 10, 'Euphrates', 0),
(40, 10, 'Jordan', 0),
(41, 11, '3', 0),
(42, 11, '7', 1),
(43, 11, '8', 0),
(44, 11, '9', 0),
(45, 12, '4', 0),
(46, 12, '6', 0),
(47, 12, '2', 1),
(48, 12, '8', 0),
(49, 13, '93', 0),
(50, 13, '94', 0),
(51, 13, '95', 0),
(52, 13, '96', 1),
(53, 14, '56', 1),
(54, 14, '14', 0),
(55, 14, '42', 0),
(56, 13, '52', 0),
(57, 15, '60', 0),
(58, 15, '43', 0),
(59, 15, '30', 0),
(60, 15, '35', 1),
(61, 16, '30', 0),
(62, 16, '64', 0),
(63, 16, '24', 0),
(64, 16, '32', 1),
(65, 17, '9', 1),
(66, 17, '10', 0),
(67, 17, '8', 0),
(68, 17, '11', 0),
(69, 18, '45', 0),
(70, 18, '35', 0),
(71, 18, '0', 1),
(72, 18, '20', 0),
(73, 19, '1', 0),
(74, 19, '5', 0),
(75, 19, '0', 0),
(76, 19, '6', 1),
(77, 20, '18', 0),
(78, 20, '19', 1),
(79, 20, '17', 0),
(80, 20, '20', 0);

-- --------------------------------------------------------

--
-- Table structure for table `questions_tbl`
--

CREATE TABLE `questions_tbl` (
  `question_id` int(11) NOT NULL,
  `question_text` text NOT NULL,
  `question_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions_tbl`
--

INSERT INTO `questions_tbl` (`question_id`, `question_text`, `question_category`) VALUES
(1, 'What is the largest ocean', 1),
(2, 'How many states are in the United States', 1),
(3, 'How many continents are in the world?', 1),
(4, 'What is the only sea without any coasts', 1),
(5, 'What percentage of the River Nile is located in Egypt', 1),
(6, 'What is the driest place on Earth', 1),
(7, 'In what country can you visit Machu Picchu', 1),
(8, 'Which African nation has the most pyramids', 1),
(9, 'What African country served as the setting for Tatooine in Star Wars', 1),
(10, 'What river runs through Baghdad', 1),
(11, '5 + 2', 2),
(12, '8 / 4', 2),
(13, '48 * 2', 2),
(14, '(3 + 5) * 7', 2),
(15, 'Mike bought 60 apples, lost 43 then found 18. How many apples does he have?', 2),
(16, 'What is the perimeter, in inches, of a square with 8 inch sides?', 2),
(17, '18 - 9', 2),
(18, ' 3 * 7 * 2 * 0 * 18', 2),
(19, '0 + 6', 2),
(20, '38 / 2', 2);

-- --------------------------------------------------------

--
-- Table structure for table `scores_tbl`
--

CREATE TABLE `scores_tbl` (
  `score_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scores_tbl`
--

INSERT INTO `scores_tbl` (`score_id`, `user_id`, `score`) VALUES
(2, 1, '33.33'),
(3, 1, '66.67'),
(4, 1, '100.00'),
(5, 1, '100.00'),
(6, 1, '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `users_tbl`
--

CREATE TABLE `users_tbl` (
  `user_id` int(11) NOT NULL,
  `user_fname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_lname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_uname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users_tbl`
--

INSERT INTO `users_tbl` (`user_id`, `user_fname`, `user_lname`, `user_uname`, `user_password`) VALUES
(1, 'Justin', 'Casey', 'jcasey', 'test');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories_tbl`
--
ALTER TABLE `categories_tbl`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `choices_tbl`
--
ALTER TABLE `choices_tbl`
  ADD PRIMARY KEY (`choice_id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `questions_tbl`
--
ALTER TABLE `questions_tbl`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `question_category` (`question_category`);

--
-- Indexes for table `scores_tbl`
--
ALTER TABLE `scores_tbl`
  ADD PRIMARY KEY (`score_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users_tbl`
--
ALTER TABLE `users_tbl`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories_tbl`
--
ALTER TABLE `categories_tbl`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `choices_tbl`
--
ALTER TABLE `choices_tbl`
  MODIFY `choice_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `questions_tbl`
--
ALTER TABLE `questions_tbl`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `scores_tbl`
--
ALTER TABLE `scores_tbl`
  MODIFY `score_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users_tbl`
--
ALTER TABLE `users_tbl`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `choices_tbl`
--
ALTER TABLE `choices_tbl`
  ADD CONSTRAINT `choices_tbl_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions_tbl` (`question_id`);

--
-- Constraints for table `questions_tbl`
--
ALTER TABLE `questions_tbl`
  ADD CONSTRAINT `questions_tbl_ibfk_1` FOREIGN KEY (`question_category`) REFERENCES `categories_tbl` (`category_id`);

--
-- Constraints for table `scores_tbl`
--
ALTER TABLE `scores_tbl`
  ADD CONSTRAINT `scores_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users_tbl` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;