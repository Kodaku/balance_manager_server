-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 22, 2022 alle 16:31
-- Versione del server: 10.4.18-MariaDB
-- Versione PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `balance_manager`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `app_user`
--

CREATE TABLE `app_user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `app_user`
--

INSERT INTO `app_user` (`id`, `name`) VALUES
(75, 'Axel');

-- --------------------------------------------------------

--
-- Struttura della tabella `balance`
--

CREATE TABLE `balance` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `balance`
--

INSERT INTO `balance` (`id`, `name`, `user_id`) VALUES
(21, 'Portafogli', 75),
(22, 'Banca', 75),
(23, 'DataScience', 75),
(24, 'Partite2022-2023', 75);

-- --------------------------------------------------------

--
-- Struttura della tabella `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(8, 'Initialization'),
(9, 'Gas'),
(10, 'Books'),
(11, 'Restaurant'),
(12, 'Sport'),
(13, 'Clothes'),
(14, 'Shoes'),
(15, 'KebabWork'),
(16, 'Work'),
(17, 'Loan'),
(18, 'CreditFromLoan'),
(19, 'MatchToBePaid'),
(20, 'MatchPaied');

-- --------------------------------------------------------

--
-- Struttura della tabella `expense`
--

CREATE TABLE `expense` (
  `id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `is_debit` tinyint(1) DEFAULT NULL,
  `balance_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `expense`
--

INSERT INTO `expense` (`id`, `amount`, `date`, `description`, `is_debit`, `balance_id`, `category_id`) VALUES
(21, 940.07, '2022-07-21', 'Initialization', 0, 22, 8),
(22, 2600, '2022-07-21', 'Initialization', 1, 23, 8),
(23, 65.3, '2022-07-21', 'Initialization', 0, 21, 8);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `balance`
--
ALTER TABLE `balance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_DETAIL_idx` (`user_id`);

--
-- Indici per le tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_BALANCE_idx` (`balance_id`),
  ADD KEY `FK_CATEGORY_idx` (`category_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `app_user`
--
ALTER TABLE `app_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT per la tabella `balance`
--
ALTER TABLE `balance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT per la tabella `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `expense`
--
ALTER TABLE `expense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `balance`
--
ALTER TABLE `balance`
  ADD CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `FK_BALANCE` FOREIGN KEY (`balance_id`) REFERENCES `balance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CATEGORY` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
