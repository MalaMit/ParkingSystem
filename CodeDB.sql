CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `secondName` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

CREATE TABLE `employee` (
  `pesel` bigint(20) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `secondName` varchar(20) NOT NULL,
  `dateEmployed` date NOT NULL,
  `hourlyWage` float(4,2) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `jobPost` enum('Guard','Manager','Cashier','Regular','Administrator') DEFAULT NULL,
  PRIMARY KEY (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `shift` (
  `idShift` int(11) NOT NULL AUTO_INCREMENT,
  `shiftStart` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shiftEnd` timestamp NULL,
  `pesel` bigint(20) DEFAULT NULL,
  `shiftType` enum('working','holiday','sick') DEFAULT NULL,
  PRIMARY KEY (`idShift`),
  UNIQUE KEY `pesel` (`pesel`,`idShift`),
  CONSTRAINT `shift_ibfk_1` FOREIGN KEY (`pesel`) REFERENCES `employee` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

CREATE TABLE `typevehicle` (
  `type` varchar(30) NOT NULL,
  `priceForHour` int(3) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `parkingspot` (
  `number` varchar(5) NOT NULL,
  `status` enum('FREE','TAKEN') NOT NULL DEFAULT 'FREE',
  `typeVehicle` varchar(30) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_parkingSpot_typeVehicle` (`typeVehicle`),
  CONSTRAINT `fk_parkingSpot_typeVehicle` FOREIGN KEY (`typeVehicle`) REFERENCES `typevehicle` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `client` (
  `LicensePlate` varchar(10) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `SecondName` varchar(20) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  PRIMARY KEY (`LicensePlate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `parkingtime` (
  `licensePlate` varchar(10) NOT NULL,
  `timeIn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeOut` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `bill` float(10,2) DEFAULT NULL,
  `typeVehicle` varchar(30) DEFAULT NULL,
  `parkingNumber` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`licensePlate`),
  KEY `fk_ParrkingTime_client1_idx` (`typeVehicle`),
  KEY `fk_ParrkingTime_parkingSpot1_idx` (`typeVehicle`,`parkingNumber`),
  CONSTRAINT `fk_ParrkingTime_client1` FOREIGN KEY (`licensePlate`) REFERENCES `client` (`LicensePlate`),
  CONSTRAINT `fk_ParrkingTime_parkingSpot1` FOREIGN KEY (`typeVehicle`, `parkingNumber`) REFERENCES `parkingspot` (`typeVehicle`, `number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8




CREATE TABLE `parkinghistory` (
  `historyParkingID` int(11) NOT NULL AUTO_INCREMENT,
  `timeIn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeOut` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `bill` float(10,2) DEFAULT NULL,
  `licensePlate` varchar(10) NOT NULL,
  `parkingNumber` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`historyParkingID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


//////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO admin (firstName, secondName, login, password)  VALUES ('Michał', 'Motyka', 'mm', '11111');
