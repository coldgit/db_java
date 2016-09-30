-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2016 at 07:00 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer_types`
--

CREATE TABLE `answer_types` (
  `answer_type_id` int(11) NOT NULL,
  `answer_type_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer_types`
--

INSERT INTO `answer_types` (`answer_type_id`, `answer_type_desc`) VALUES
(1, 'single choice'),
(2, 'checkbox'),
(3, 'fill in the blanks');

-- --------------------------------------------------------

--
-- Table structure for table `lessons`
--

CREATE TABLE `lessons` (
  `lesson_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content_` text NOT NULL,
  `date_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `lqa_master_list`
--
CREATE TABLE `lqa_master_list` (
`lesson_id` int(11)
,`title` varchar(50)
,`content_` text
,`date_time` datetime
,`quest_id` int(11)
,`quest_desc` varchar(255)
,`answer_type_desc` varchar(255)
,`answer_key` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `quest_id` int(11) NOT NULL,
  `lesson_id` int(11) NOT NULL,
  `quest_desc` varchar(255) NOT NULL,
  `answer_type_id` int(11) NOT NULL,
  `answer_key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(100) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `contact_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_master_list`
--
CREATE TABLE `users_master_list` (
`user_id` int(100)
,`username` varchar(255)
,`password` varchar(255)
,`type_desc` varchar(20)
,`fname` varchar(50)
,`lname` varchar(50)
,`contact_no` varchar(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `users_score`
--

CREATE TABLE `users_score` (
  `users_score_id` int(100) NOT NULL,
  `user_id` int(100) NOT NULL,
  `score_` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_scores`
--
CREATE TABLE `users_scores` (
`user_id` int(100)
,`username` varchar(255)
,`fname` varchar(50)
,`lname` varchar(50)
,`type_desc` varchar(20)
,`score_` int(100)
);

-- --------------------------------------------------------

--
-- Table structure for table `user_answers`
--

CREATE TABLE `user_answers` (
  `user_answer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `q_id` int(11) NOT NULL,
  `users_answer_desc` int(11) NOT NULL,
  `date_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_types`
--

CREATE TABLE `user_types` (
  `user_type_id` int(11) NOT NULL,
  `type_desc` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_types`
--

INSERT INTO `user_types` (`user_type_id`, `type_desc`) VALUES
(1, 'Admin'),
(2, 'Teacher'),
(3, 'Student');

-- --------------------------------------------------------

--
-- Structure for view `lqa_master_list`
--
DROP TABLE IF EXISTS `lqa_master_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `lqa_master_list`  AS  select `lessons`.`lesson_id` AS `lesson_id`,`lessons`.`title` AS `title`,`lessons`.`content_` AS `content_`,`lessons`.`date_time` AS `date_time`,`questions`.`quest_id` AS `quest_id`,`questions`.`quest_desc` AS `quest_desc`,`answer_types`.`answer_type_desc` AS `answer_type_desc`,`questions`.`answer_key` AS `answer_key` from ((`lessons` join `questions`) join `answer_types`) where ((`lessons`.`lesson_id` = `questions`.`lesson_id`) and (`questions`.`answer_type_id` = `answer_types`.`answer_type_id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `users_master_list`
--
DROP TABLE IF EXISTS `users_master_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `users_master_list`  AS  select `users`.`user_id` AS `user_id`,`users`.`username` AS `username`,`users`.`password` AS `password`,`user_types`.`type_desc` AS `type_desc`,`users`.`fname` AS `fname`,`users`.`lname` AS `lname`,`users`.`contact_no` AS `contact_no` from (`users` join `user_types`) where (`users`.`user_type_id` = `user_types`.`user_type_id`) ;

-- --------------------------------------------------------

--
-- Structure for view `users_scores`
--
DROP TABLE IF EXISTS `users_scores`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `users_scores`  AS  select `users_master_list`.`user_id` AS `user_id`,`users_master_list`.`username` AS `username`,`users_master_list`.`fname` AS `fname`,`users_master_list`.`lname` AS `lname`,`users_master_list`.`type_desc` AS `type_desc`,`users_score`.`score_` AS `score_` from (`users_master_list` join `users_score`) where (`users_master_list`.`user_id` = `users_score`.`user_id`) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer_types`
--
ALTER TABLE `answer_types`
  ADD PRIMARY KEY (`answer_type_id`);

--
-- Indexes for table `lessons`
--
ALTER TABLE `lessons`
  ADD PRIMARY KEY (`lesson_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`quest_id`),
  ADD KEY `lesson_id` (`lesson_id`),
  ADD KEY `answer_type_id` (`answer_type_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `user_type_id` (`user_type_id`);

--
-- Indexes for table `users_score`
--
ALTER TABLE `users_score`
  ADD PRIMARY KEY (`users_score_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_answers`
--
ALTER TABLE `user_answers`
  ADD PRIMARY KEY (`user_answer_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `q_id` (`q_id`);

--
-- Indexes for table `user_types`
--
ALTER TABLE `user_types`
  ADD PRIMARY KEY (`user_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer_types`
--
ALTER TABLE `answer_types`
  MODIFY `answer_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `lessons`
--
ALTER TABLE `lessons`
  MODIFY `lesson_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `quest_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users_score`
--
ALTER TABLE `users_score`
  MODIFY `users_score_id` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_answers`
--
ALTER TABLE `user_answers`
  MODIFY `user_answer_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_types`
--
ALTER TABLE `user_types`
  MODIFY `user_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `questions_ibfk_2` FOREIGN KEY (`answer_type_id`) REFERENCES `answer_types` (`answer_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `user_types` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users_score`
--
ALTER TABLE `users_score`
  ADD CONSTRAINT `users_score_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_answers`
--
ALTER TABLE `user_answers`
  ADD CONSTRAINT `user_answers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_answers_ibfk_2` FOREIGN KEY (`q_id`) REFERENCES `questions` (`quest_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
