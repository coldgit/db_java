-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2016 at 10:39 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

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
-- Table structure for table `assign_teacher_subject__section`
--

CREATE TABLE `assign_teacher_subject__section` (
  `assign_id` int(11) NOT NULL,
  `t_id` int(11) DEFAULT NULL,
  `sub_id` int(11) DEFAULT NULL,
  `sec_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `choices`
--

CREATE TABLE `choices` (
  `q_id` int(10) NOT NULL,
  `choice_id` int(10) NOT NULL,
  `desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `enroll_student_section_subject`
--

CREATE TABLE `enroll_student_section_subject` (
  `enroll_id` int(11) NOT NULL,
  `stundent_id` int(11) DEFAULT NULL,
  `sec_id` int(11) DEFAULT NULL,
  `sub_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `lessons`
--

CREATE TABLE `lessons` (
  `sub_id` int(100) NOT NULL,
  `lesson_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content_` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lessons`
--

INSERT INTO `lessons` (`sub_id`, `lesson_id`, `title`, `content_`) VALUES
(1, 11, 'Stars and Galaxies', 'The Constellations seem to move because Earth rotates on its axis and revolves around the sum.'),
(1, 12, 'The Solar System', 'Our system formed about 4.6 billion years ago from a cloud of gas, ice, and dust.'),
(1, 13, 'Earth-Moon System', 'Earth rotates once a day and revolves about the sun in a little more than 365 days.'),
(1, 14, 'Exploring-Space', 'The arrangement of electromagnetic waves according to their wavelengths is the electromagnetic spectrum. '),
(1, 15, 'You, Air and Water', 'Recycling and conservation of Earth`s resources reduce pollution.'),
(1, 16, 'You and the Environment', 'Recycling, Reducing and Reusing materials are important ways we can conserve natural resources.'),
(1, 17, 'Energy', 'Releasing radioactive materials into the environment is a potential danger of nuclear energy.'),
(1, 18, 'Geologic Time', 'During the Paleozoic Era, glaciers advanced, and seas withdrew from continents.Many marine invertebrates became extinct.'),
(1, 19, 'Clues to Earth`s', 'In nearly all situations a rock layer can be no older or younger than the age of the fossils embedded in it.'),
(1, 20, 'Volcanoes', 'A caldera forms when the top of a volcano collapses, forming a very large depression.');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `quest_id` int(11) NOT NULL,
  `lesson_id` int(11) NOT NULL,
  `quest_desc` varchar(255) NOT NULL,
  `answer_key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `sec_id` int(11) NOT NULL,
  `section_description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Stand-in structure for view `student_info`
--
CREATE TABLE `student_info` (
`user_type` varchar(50)
,`username` varchar(255)
,`password` varchar(255)
,`name` varchar(255)
,`sec_id` int(11)
,`section_description` varchar(255)
,`subject_id` int(100)
,`subject_desc` varchar(255)
,`lesson_id` int(11)
,`title` varchar(50)
,`content_` text
,`quest_id` int(11)
,`quest_desc` varchar(255)
,`answer_key` varchar(255)
,`choice_id` int(10)
,`desc` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subject_id` int(100) NOT NULL,
  `subject_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`subject_id`, `subject_desc`) VALUES
(1, 'Topic: Earth Scince'),
(2, 'asd');

-- --------------------------------------------------------

--
-- Stand-in structure for view `teacher_info`
--
CREATE TABLE `teacher_info` (
`user_type` varchar(50)
,`username` varchar(255)
,`password` varchar(255)
,`name` varchar(255)
,`sec_id` int(11)
,`section_description` varchar(255)
,`subject_id` int(100)
,`subject_desc` varchar(255)
,`lesson_id` int(11)
,`title` varchar(50)
,`content_` text
,`quest_id` int(11)
,`quest_desc` varchar(255)
,`answer_key` varchar(255)
,`choice_id` int(10)
,`desc` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(100) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `name`, `user_type`) VALUES
(1, 'admin123', 'admin123', 'admin123', 'Admin'),
(2, 'Dexter123', 'Dexter123', 'Dexter123', 'Student');

-- --------------------------------------------------------

--
-- Structure for view `student_info`
--
DROP TABLE IF EXISTS `student_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `student_info`  AS  select `users`.`user_type` AS `user_type`,`users`.`username` AS `username`,`users`.`password` AS `password`,`users`.`name` AS `name`,`section`.`sec_id` AS `sec_id`,`section`.`section_description` AS `section_description`,`subjects`.`subject_id` AS `subject_id`,`subjects`.`subject_desc` AS `subject_desc`,`lessons`.`lesson_id` AS `lesson_id`,`lessons`.`title` AS `title`,`lessons`.`content_` AS `content_`,`questions`.`quest_id` AS `quest_id`,`questions`.`quest_desc` AS `quest_desc`,`questions`.`answer_key` AS `answer_key`,`choices`.`choice_id` AS `choice_id`,`choices`.`desc` AS `desc` from ((((((`users` join `section`) join `subjects`) join `lessons`) join `questions`) join `choices`) join `enroll_student_section_subject`) where ((`enroll_student_section_subject`.`stundent_id` = `users`.`user_id`) and (`enroll_student_section_subject`.`sec_id` = `section`.`sec_id`) and (`enroll_student_section_subject`.`sub_id` = `subjects`.`subject_id`) and (`subjects`.`subject_id` = `lessons`.`sub_id`) and (`lessons`.`lesson_id` = `questions`.`lesson_id`) and (`questions`.`quest_id` = `choices`.`q_id`) and (`users`.`user_type` = 'Student')) ;

-- --------------------------------------------------------

--
-- Structure for view `teacher_info`
--
DROP TABLE IF EXISTS `teacher_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `teacher_info`  AS  select `users`.`user_type` AS `user_type`,`users`.`username` AS `username`,`users`.`password` AS `password`,`users`.`name` AS `name`,`section`.`sec_id` AS `sec_id`,`section`.`section_description` AS `section_description`,`subjects`.`subject_id` AS `subject_id`,`subjects`.`subject_desc` AS `subject_desc`,`lessons`.`lesson_id` AS `lesson_id`,`lessons`.`title` AS `title`,`lessons`.`content_` AS `content_`,`questions`.`quest_id` AS `quest_id`,`questions`.`quest_desc` AS `quest_desc`,`questions`.`answer_key` AS `answer_key`,`choices`.`choice_id` AS `choice_id`,`choices`.`desc` AS `desc` from ((((((`users` join `section`) join `subjects`) join `lessons`) join `questions`) join `choices`) join `enroll_student_section_subject`) where ((`enroll_student_section_subject`.`stundent_id` = `users`.`user_id`) and (`enroll_student_section_subject`.`sec_id` = `section`.`sec_id`) and (`enroll_student_section_subject`.`sub_id` = `subjects`.`subject_id`) and (`subjects`.`subject_id` = `lessons`.`sub_id`) and (`lessons`.`lesson_id` = `questions`.`lesson_id`) and (`questions`.`quest_id` = `choices`.`q_id`) and (`users`.`user_type` = 'Teacher')) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assign_teacher_subject__section`
--
ALTER TABLE `assign_teacher_subject__section`
  ADD PRIMARY KEY (`assign_id`),
  ADD KEY `t_id` (`t_id`),
  ADD KEY `sub_id` (`sub_id`),
  ADD KEY `sec_id` (`sec_id`);

--
-- Indexes for table `choices`
--
ALTER TABLE `choices`
  ADD PRIMARY KEY (`choice_id`),
  ADD KEY `q_id` (`q_id`);

--
-- Indexes for table `enroll_student_section_subject`
--
ALTER TABLE `enroll_student_section_subject`
  ADD PRIMARY KEY (`enroll_id`),
  ADD KEY `stundent_id` (`stundent_id`),
  ADD KEY `sub_id` (`sub_id`),
  ADD KEY `sec_id` (`sec_id`);

--
-- Indexes for table `lessons`
--
ALTER TABLE `lessons`
  ADD PRIMARY KEY (`lesson_id`),
  ADD KEY `subject_id` (`sub_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`quest_id`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`sec_id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assign_teacher_subject__section`
--
ALTER TABLE `assign_teacher_subject__section`
  MODIFY `assign_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `choices`
--
ALTER TABLE `choices`
  MODIFY `choice_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `enroll_student_section_subject`
--
ALTER TABLE `enroll_student_section_subject`
  MODIFY `enroll_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lessons`
--
ALTER TABLE `lessons`
  MODIFY `lesson_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `quest_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `sec_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subject_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign_teacher_subject__section`
--
ALTER TABLE `assign_teacher_subject__section`
  ADD CONSTRAINT `assign_teacher_subject__section_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_teacher_subject__section_ibfk_2` FOREIGN KEY (`sub_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_teacher_subject__section_ibfk_3` FOREIGN KEY (`sec_id`) REFERENCES `section` (`sec_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `choices`
--
ALTER TABLE `choices`
  ADD CONSTRAINT `choices_ibfk_1` FOREIGN KEY (`q_id`) REFERENCES `questions` (`quest_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `enroll_student_section_subject`
--
ALTER TABLE `enroll_student_section_subject`
  ADD CONSTRAINT `enroll_student_section_subject_ibfk_1` FOREIGN KEY (`stundent_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enroll_student_section_subject_ibfk_2` FOREIGN KEY (`sub_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enroll_student_section_subject_ibfk_3` FOREIGN KEY (`sec_id`) REFERENCES `section` (`sec_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lessons`
--
ALTER TABLE `lessons`
  ADD CONSTRAINT `lessons_ibfk_1` FOREIGN KEY (`sub_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
