-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24/05/2023 às 02:05
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projetointegrador2`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `ingredients`
--

CREATE TABLE `ingredients` (
  `ingredientId` int(11) NOT NULL,
  `nameI` varchar(25) NOT NULL,
  `priceI` float NOT NULL,
  `weightI` float NOT NULL,
  `typeI` enum('u','k','g','h') NOT NULL,
  `creationI` varchar(30) DEFAULT NULL,
  `updateI` varchar(30) DEFAULT NULL,
  `statusI` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `input`
--

CREATE TABLE `input` (
  `inpuId` int(11) NOT NULL,
  `weightI` float NOT NULL,
  `costI` float NOT NULL,
  `quantityI` int(11) NOT NULL,
  `creationI` varchar(30) DEFAULT NULL,
  `updateI` varchar(30) DEFAULT NULL,
  `statusI` enum('a','n','d','') NOT NULL,
  `idIngredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `outputi`
--

CREATE TABLE `outputi` (
  `ingredientId` int(11) NOT NULL,
  `requestId` int(11) NOT NULL,
  `snackId` int(11) NOT NULL,
  `creationOut` varchar(20) DEFAULT NULL,
  `updateOut` varchar(20) DEFAULT NULL,
  `statusOut` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `relationsi`
--

CREATE TABLE `relationsi` (
  `snackId` int(11) NOT NULL,
  `ingredientId` int(11) NOT NULL,
  `quantitySI` float DEFAULT NULL,
  `WeightSI` float DEFAULT NULL,
  `creationSI` varchar(20) DEFAULT NULL,
  `updateSI` varchar(20) DEFAULT NULL,
  `statusSI` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `relationsr`
--

CREATE TABLE `relationsr` (
  `requestId` int(11) NOT NULL,
  `snackId` int(11) NOT NULL,
  `creationSR` varchar(20) DEFAULT NULL,
  `updateSR` varchar(20) DEFAULT NULL,
  `statusSR` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `request`
--

CREATE TABLE `request` (
  `requestId` int(11) NOT NULL,
  `nameR` varchar(255) NOT NULL,
  `valueR` float NOT NULL,
  `creationR` varchar(20) DEFAULT NULL,
  `updateR` varchar(20) DEFAULT NULL,
  `statusR` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `snack`
--

CREATE TABLE `snack` (
  `snackId` int(11) NOT NULL,
  `nameS` varchar(255) NOT NULL,
  `costS` float NOT NULL,
  `priceS` float NOT NULL,
  `weightS` float NOT NULL,
  `creationS` varchar(30) DEFAULT NULL,
  `updateS` varchar(30) DEFAULT NULL,
  `statusS` enum('a','s','e','d') NOT NULL,
  `whitS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `typeU` enum('a','g','e','c') NOT NULL,
  `nameU` varchar(255) NOT NULL,
  `photoU` varchar(255) NOT NULL,
  `creationU` varchar(20) DEFAULT NULL,
  `updateU` varchar(20) DEFAULT NULL,
  `statusU` enum('a','n','d','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`ingredientId`);

--
-- Índices de tabela `input`
--
ALTER TABLE `input`
  ADD PRIMARY KEY (`inpuId`);

--
-- Índices de tabela `outputi`
--
ALTER TABLE `outputi`
  ADD PRIMARY KEY (`ingredientId`);

--
-- Índices de tabela `relationsi`
--
ALTER TABLE `relationsi`
  ADD PRIMARY KEY (`snackId`);

--
-- Índices de tabela `relationsr`
--
ALTER TABLE `relationsr`
  ADD PRIMARY KEY (`requestId`);

--
-- Índices de tabela `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`requestId`);

--
-- Índices de tabela `snack`
--
ALTER TABLE `snack`
  ADD PRIMARY KEY (`snackId`);

--
-- Índices de tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `ingredientId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `input`
--
ALTER TABLE `input`
  MODIFY `inpuId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `outputi`
--
ALTER TABLE `outputi`
  MODIFY `ingredientId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `relationsi`
--
ALTER TABLE `relationsi`
  MODIFY `snackId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `relationsr`
--
ALTER TABLE `relationsr`
  MODIFY `requestId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `request`
--
ALTER TABLE `request`
  MODIFY `requestId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `snack`
--
ALTER TABLE `snack`
  MODIFY `snackId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
