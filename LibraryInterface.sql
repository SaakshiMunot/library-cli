TABLE `bookinfo` (
  `ISBN` varchar(100) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  `authorLastName` varchar(100) DEFAULT NULL,
  `authorFirstName` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) NOT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `coverType` varchar(100) NOT NULL,
  `onLoan` tinyint NOT NULL,
  `numberOfCopiesTotal` int NOT NULL,
  `numberOfCopiesCurrent` int DEFAULT NULL,
  `numberOfCopiesOnLoan` int DEFAULT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `member` (
  `libraryCardNumber` varchar(100) NOT NULL,
  `memberLastName` varchar(100) NOT NULL,
  `memberFirstName` varchar(100) NOT NULL,
  `memberEmail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`libraryCardNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `memberviewbooks` (
  `ISBN` varchar(100) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  `authorLastName` varchar(100) DEFAULT NULL,
  `authorFirstName` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `coverType` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `receipt` (
  `libraryCard#` int NOT NULL,
  `memberLastName` varchar(100) NOT NULL,
  `memberFirstName` varchar(100) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  `ISBN` int NOT NULL,
  `authorLastName` varchar(100) DEFAULT NULL,
  `authorFirstName` varchar(100) DEFAULT NULL,
  `dateIssued` date NOT NULL,
  `dateDue` date NOT NULL,
  PRIMARY KEY (`libraryCard#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
