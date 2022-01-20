<<<<<<< HEAD
-- --------------------------------------------------------
-- Hôte :                        192.168.1.16
-- Version du serveur:           10.3.32-MariaDB-0ubuntu0.20.04.1 - Ubuntu 20.04
-- SE du serveur:                debian-linux-gnu
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la table organizee.contact
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7gyd9s84tx9eeuigeu3uv984x` (`team_id`),
  CONSTRAINT `FK7gyd9s84tx9eeuigeu3uv984x` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.contact : ~0 rows (environ)
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `adresse`, `date_naissance`, `email`, `nom`, `prenom`, `telephone`, `team_id`) VALUES
	(1, '7554 Messerschmidt Center', '2021-01-24', 'oogleasane0@cargocollective.com', 'Ophelia', 'O\'Gleasane', '913-198-6499', 1),
	(2, '534 Jay Way', '2021-03-26', 'fmowett1@ocn.ne.jp', 'Fiann', 'Mowett', '248-224-7233', 1),
	(3, '077 Buell Place', '2021-06-24', 'vlewknor2@spotify.com', 'Vladamir', 'Lewknor', '922-822-3626', 1),
	(4, '6226 Esker Street', '2021-04-13', 'jbarmadier3@opensource.org', 'Jervis', 'Barmadier', '838-581-8112', 2),
	(5, '28531 Luster Circle', '2021-06-15', 'tmee4@ameblo.jp', 'Tuesday', 'Mee', '761-975-7324', 2),
	(6, '96 Hallows Avenue', '2021-08-13', 'tcolvine5@elegantthemes.com', 'Toni', 'Colvine', '348-778-7679', 2),
	(7, '6401 Jay Crossing', '2021-01-14', 'rrielly6@netlog.com', 'Riane', 'Rielly', '740-571-0835', 3),
	(8, '3273 Cascade Pass', '2021-03-22', 'jlauder7@rambler.ru', 'Juieta', 'Lauder', '928-408-6855', 3),
	(9, '1170 Burning Wood Road', '2021-05-31', 'tbolver8@google.ca', 'Thibaut', 'Bolver', '681-860-8291', 4),
	(10, '1 Westridge Road', '2021-03-11', 'emebs9@uol.com.br', 'Evered', 'Mebs', '898-483-6075', 4);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- Export de la structure de la table organizee.membre
CREATE TABLE IF NOT EXISTS `membre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `couleur` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `smiley` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKll5mmgkw1h2kmxnuo4885x2fn` (`team_id`),
  CONSTRAINT `FKll5mmgkw1h2kmxnuo4885x2fn` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.membre : ~0 rows (environ)
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
=======
INSERT INTO `team` (`id`, `nom`) VALUES
	(1, 'Team JAVA'),
	(2, 'Team Angular'),
	(3, 'Team PHP'),
	(4, 'Team Bancal');
	
INSERT INTO `contact` (`id`, `adresse`, `date_naissance`, `email`, `nom`, `prenom`, `telephone`, `team_id`) VALUES
	(1, '7554 Messerschmidt Center', '2021-01-24', 'oogleasane0@cargocollective.com', 'Ophelia', 'O\'Gleasane', '913-198-6499','1'),
	(2, '534 Jay Way', '2021-03-26', 'fmowett1@ocn.ne.jp', 'Fiann', 'Mowett', '248-224-7233','1'),
	(3, '077 Buell Place', '2021-06-24', 'vlewknor2@spotify.com', 'Vladamir', 'Lewknor', '922-822-3626','1'),
	(4, '6226 Esker Street', '2021-04-13', 'jbarmadier3@opensource.org', 'Jervis', 'Barmadier', '838-581-8112','2'),
	(5, '28531 Luster Circle', '2021-06-15', 'tmee4@ameblo.jp', 'Tuesday', 'Mee', '761-975-7324','2'),
	(6, '96 Hallows Avenue', '2021-08-13', 'tcolvine5@elegantthemes.com', 'Toni', 'Colvine', '348-778-7679','2'),
	(7, '6401 Jay Crossing', '2021-01-14', 'rrielly6@netlog.com', 'Riane', 'Rielly', '740-571-0835','3'),
	(8, '3273 Cascade Pass', '2021-03-22', 'jlauder7@rambler.ru', 'Juieta', 'Lauder', '928-408-6855','3'),
	(9, '1170 Burning Wood Road', '2021-05-31', 'tbolver8@google.ca', 'Thibaut', 'Bolver', '681-860-8291','4'),
	(10, '1 Westridge Road', '2021-03-11', 'emebs9@uol.com.br', 'Evered', 'Mebs', '898-483-6075','4');
	
>>>>>>> b147bc406c22caa9c817c7189a325058d8550a69
INSERT INTO `membre` (`id`, `couleur`, `date_naissance`, `email`, `is_admin`, `nom`, `password`, `prenom`, `smiley`, `team_id`) VALUES
	(1, '#fcba03', '2021-12-13', 'hedi@simplon.com', '0', 'SKYWALKER', 'toto', 'Hédi', NULL, 1),
	(2, '#8df505', '2021-07-03', 'aline@simplon.com', '0', 'FETT', 'tata', 'Aline', NULL, 1),
	(3, '#091ced', '2021-01-20', 'isabelle@simplon.com', '0', 'SOLO', 'titi', 'Isabelle', NULL, 2),
	(4, '#ed09de', '2021-06-29', 'blandine@simplon.com', '0', 'VADER', 'tutu', 'Blandine', NULL, 3),
	(5, '#ed09de', '2021-08-29', 'sana@simplon.com', '0', 'C3PO', 'riri', 'Sana', NULL, 4),
	(6, '#ed09de', '2021-10-29', 'cecile@simplon.com', '0', 'R2D2', 'loulou', 'Cecile', NULL, 4);
<<<<<<< HEAD
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;

-- Export de la structure de la table organizee.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_menu` date DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `membre_id` int(11) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9k2sad7pn2qsivwavhptvm3u6` (`membre_id`),
  KEY `FKky2j5l3syborv9dtqtprgpr28` (`team_id`),
  CONSTRAINT `FK9k2sad7pn2qsivwavhptvm3u6` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`),
  CONSTRAINT `FKky2j5l3syborv9dtqtprgpr28` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.menu : ~0 rows (environ)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Export de la structure de la table organizee.tache
CREATE TABLE IF NOT EXISTS `tache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` bit(1) DEFAULT NULL,
  `texte` varchar(255) DEFAULT NULL,
  `todolist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50q0ja9qvoud7ujsudc9jj9yk` (`todolist_id`),
  CONSTRAINT `FK50q0ja9qvoud7ujsudc9jj9yk` FOREIGN KEY (`todolist_id`) REFERENCES `todo_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.tache : ~0 rows (environ)
/*!40000 ALTER TABLE `tache` DISABLE KEYS */;
INSERT INTO `tache` (`id`, `etat`, `texte`, `todolist_id`) VALUES
	(1, b'0', 'Apprendre le PHP', 1),
	(2, b'0', 'Revoir CRUD', 1),
	(3, b'0', 'Acheter des guirlandes', 3),
	(4, b'0', 'Acheter un sapin', 3),
	(5, b'0', 'Trouver un repas', 3);
/*!40000 ALTER TABLE `tache` ENABLE KEYS */;

-- Export de la structure de la table organizee.team
CREATE TABLE IF NOT EXISTS `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.team : ~0 rows (environ)
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` (`id`, `nom`) VALUES
	(1, 'Team JAVA'),
	(2, 'Team Angular'),
	(3, 'Team PHP'),
	(4, 'Team Bancal');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;

-- Export de la structure de la table organizee.todo_list
CREATE TABLE IF NOT EXISTS `todo_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ty40hkdysbql7xaewhujsjg` (`team_id`),
  CONSTRAINT `FK6ty40hkdysbql7xaewhujsjg` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table organizee.todo_list : ~0 rows (environ)
/*!40000 ALTER TABLE `todo_list` DISABLE KEYS */;
INSERT INTO `todo_list` (`id`, `nom`, `team_id`) VALUES
	(1, 'Pour Blandine', 1),
	(2, 'Corvées', 1),
	(3, 'Noel', 1);
/*!40000 ALTER TABLE `todo_list` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
=======

INSERT INTO `todo_list` (`id`, `nom`, `team_id`) VALUES
	(1, 'Pour Blandine',1),
	(2, 'Corvées',1),
	(3, 'Noel',1);


INSERT INTO `tache` (`id`, `etat`, `texte`, `todolist_id`) VALUES
	(1, 0, 'Apprendre le PHP', 1),
	(2, 0, 'Revoir CRUD', 1),
	(3, 0, 'Acheter des guirlandes', 3),
	(4, 0, 'Acheter un sapin', 3),
	(5, 0, 'Trouver un repas', 3);

INSERT INTO `evenement` (`id`, `all_day`, `event_debut`, `event_fin`, `libelle`, `membre_id`, `team_id`) VALUES
	(1, 0, '2022-01-13 09:00:33', '2022-01-13 13:04:38', 'Simplon', 1, 1);
>>>>>>> b147bc406c22caa9c817c7189a325058d8550a69
