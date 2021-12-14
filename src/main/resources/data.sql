
-- Export de la structure de la table jpa. team
CREATE TABLE IF NOT EXISTS `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.team : ~0 rows (environ)
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` (`id`, `nom`) VALUES
	(1, 'Team JAVA'),
	(2, 'Team Angular'),
	(3, 'Team PHP'),
	(4, 'Team Bancal');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;


-- Export de la structure de la table jpa. membre
CREATE TABLE IF NOT EXISTS `membre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `couleur` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKll5mmgkw1h2kmxnuo4885x2fn` (`team_id`),
  CONSTRAINT `FKll5mmgkw1h2kmxnuo4885x2fn` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.membre : ~0 rows (environ)
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
INSERT INTO `membre` (`id`, `couleur`, `date_naissance`, `email`, `is_admin`, `nom`, `password`, `prenom`, `team_id`) VALUES
	(1, '#fcba03', '2021-12-13', 'hedi@simplon.com', '0', 'SKYWALKER', 'toto', 'Hédi', 1),
	(2, '#8df505', '2021-07-03', 'aline@simplon.com', '0', 'FETT', 'tata', 'Aline', 1),
	(3, '#091ced', '2021-01-20', 'isabelle@simplon.com', '0', 'SOLO', 'titi', 'Isabelle', 2),
	(4, '#ed09de', '2021-06-29', 'blandine@simplon.com', '0', 'VADER', 'tutu', 'Blandine', 3);
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;


-- Export de la structure de la table jpa. team_membres
CREATE TABLE IF NOT EXISTS `team_membres` (
  `team_id` int(11) NOT NULL,
  `membres_id` int(11) NOT NULL,
  UNIQUE KEY `UK_9e71olu3b26ah1kdrljr5i004` (`membres_id`),
  KEY `FK83yf7h5v4bhum1i9bsj8io105` (`team_id`),
  CONSTRAINT `FK7w6e9srma4vxmthpdvo9130qp` FOREIGN KEY (`membres_id`) REFERENCES `membre` (`id`),
  CONSTRAINT `FK83yf7h5v4bhum1i9bsj8io105` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.team_membres : ~0 rows (environ)
/*!40000 ALTER TABLE `team_membres` DISABLE KEYS */;
INSERT INTO `team_membres` (`team_id`, `membres_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 3),
	(3, 4);
/*!40000 ALTER TABLE `team_membres` ENABLE KEYS */;


-- Export de la structure de la table jpa. todo_list
CREATE TABLE IF NOT EXISTS `todo_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.todo_list : ~0 rows (environ)
/*!40000 ALTER TABLE `todo_list` DISABLE KEYS */;
INSERT INTO `todo_list` (`id`, `nom`) VALUES
	(1, 'Pour Blandine'),
	(2, 'Corvées'),
	(3, 'Noel');
/*!40000 ALTER TABLE `todo_list` ENABLE KEYS */;

-- Export de la structure de la table jpa. tache
CREATE TABLE IF NOT EXISTS `tache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` bit(1) NOT NULL,
  `texte` varchar(255) DEFAULT NULL,
  `todolist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50q0ja9qvoud7ujsudc9jj9yk` (`todolist_id`),
  CONSTRAINT `FK50q0ja9qvoud7ujsudc9jj9yk` FOREIGN KEY (`todolist_id`) REFERENCES `todo_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.tache : ~0 rows (environ)
/*!40000 ALTER TABLE `tache` DISABLE KEYS */;
INSERT INTO `tache` (`id`, `etat`, `texte`, `todolist_id`) VALUES
	(1, b'0', 'Apprendre le PHP', 1),
	(2, b'0', 'Revoir CRUD', 1),
	(3, b'0', 'Acheter des guirlandes', 3),
	(4, b'0', 'Acheter un sapin', 3),
	(5, b'0', 'Trouver un repas', 3);
/*!40000 ALTER TABLE `tache` ENABLE KEYS */;

-- Export de la structure de la table jpa. team_todolist
CREATE TABLE IF NOT EXISTS `team_todolist` (
  `team_id` int(11) NOT NULL,
  `todolists_id` int(11) NOT NULL,
  UNIQUE KEY `UK_fjwjvprqqeeugglduq8rdhsyw` (`todolists_id`),
  KEY `FK7jh4kgpji05rll7nagp4dsago` (`team_id`),
  CONSTRAINT `FK4ntj6ub8hheh7f4w79qy3ql40` FOREIGN KEY (`todolists_id`) REFERENCES `todo_list` (`id`),
  CONSTRAINT `FK7jh4kgpji05rll7nagp4dsago` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.team_todolist : ~0 rows (environ)
/*!40000 ALTER TABLE `team_todolist` DISABLE KEYS */;
INSERT INTO `team_todolist` (`team_id`, `todolists_id`) VALUES
	(2, 2),
	(2, 3),
	(3, 1);
/*!40000 ALTER TABLE `team_todolist` ENABLE KEYS */;

-- Export de la structure de la table jpa. smiley
CREATE TABLE IF NOT EXISTS `smiley` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smiley_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.smiley : ~0 rows (environ)
/*!40000 ALTER TABLE `smiley` DISABLE KEYS */;
INSERT INTO `smiley` (`id`, `smiley_url`) VALUES
	(1, 'https://assets.wprock.fr/emoji/joypixels/512/1f600.png'),
	(2, 'https://assets.wprock.fr/emoji/joypixels/512/1f618.png'),
	(3, 'https://assets.wprock.fr/emoji/joypixels/512/1f92e.png'),
	(4, 'https://assets.wprock.fr/emoji/joypixels/512/1f92c.png');
/*!40000 ALTER TABLE `smiley` ENABLE KEYS */;


-- Export de la structure de la table jpa. smiley_membres
CREATE TABLE IF NOT EXISTS `smiley_membres` (
  `smiley_id` int(11) NOT NULL,
  `membres_id` int(11) NOT NULL,
  UNIQUE KEY `UK_haqfbvq4lbo8rb77uew2yvwuw` (`membres_id`),
  KEY `FK9msxvne1uolrrrg8aq9p53sc4` (`smiley_id`),
  CONSTRAINT `FK4fj42p25xaldnw9koqktnhil2` FOREIGN KEY (`membres_id`) REFERENCES `membre` (`id`),
  CONSTRAINT `FK9msxvne1uolrrrg8aq9p53sc4` FOREIGN KEY (`smiley_id`) REFERENCES `smiley` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.smiley_membres : ~0 rows (environ)
/*!40000 ALTER TABLE `smiley_membres` DISABLE KEYS */;
INSERT INTO `smiley_membres` (`smiley_id`, `membres_id`) VALUES
	(1, 3),
	(3, 2),
	(4, 1);
/*!40000 ALTER TABLE `smiley_membres` ENABLE KEYS */;


-- Export de la structure de la table jpa. menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_menu` date DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.menu : ~0 rows (environ)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


-- Export de la structure de la table jpa. contact
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

-- Export de données de la table jpa.contact : ~0 rows (environ)
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `adresse`, `date_naissance`, `email`, `nom`, `prenom`, `telephone`, `team_id`) VALUES
	(1, '7554 Messerschmidt Center', '2021-01-24', 'oogleasane0@cargocollective.com', 'Ophelia', 'O\'Gleasane', '913-198-6499', 1),
	(2, '534 Jay Way', '2021-03-26', 'fmowett1@ocn.ne.jp', 'Fiann', 'Mowett', '248-224-7233', 1),
	(3, '077 Buell Place', '2021-06-24', 'vlewknor2@spotify.com', 'Vladamir', 'Lewknor', '922-822-3626', 1),
	(4, '6226 Esker Street', '2021-04-13', 'jbarmadier3@opensource.org', 'Jervis', 'Barmadier', '838-581-8112', 1),
	(5, '28531 Luster Circle', '2021-06-15', 'tmee4@ameblo.jp', 'Tuesday', 'Mee', '761-975-7324', 2),
	(6, '96 Hallows Avenue', '2021-08-13', 'tcolvine5@elegantthemes.com', 'Toni', 'Colvine', '348-778-7679', 2),
	(7, '6401 Jay Crossing', '2021-01-14', 'rrielly6@netlog.com', 'Riane', 'Rielly', '740-571-0835', 2),
	(8, '3273 Cascade Pass', '2021-03-22', 'jlauder7@rambler.ru', 'Juieta', 'Lauder', '928-408-6855', 2),
	(9, '1170 Burning Wood Road', '2021-05-31', 'tbolver8@google.ca', 'Thibaut', 'Bolver', '681-860-8291', 3),
	(10, '1 Westridge Road', '2021-03-11', 'emebs9@uol.com.br', 'Evered', 'Mebs', '898-483-6075', 3);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;



-- Export de la structure de la table jpa. repertoire
CREATE TABLE IF NOT EXISTS `repertoire` (
  `team_id` int(11) NOT NULL,
  `contacts_id` int(11) NOT NULL,
  UNIQUE KEY `UK_g91q6p6ssjxdhcallbd9yfary` (`contacts_id`),
  KEY `FK7u57nrpbtl5yhuh3nyx0ccy88` (`team_id`),
  CONSTRAINT `FK7u57nrpbtl5yhuh3nyx0ccy88` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKe3fy0q6urmw03kn29mj7h7vf5` FOREIGN KEY (`contacts_id`) REFERENCES `contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Export de données de la table jpa.repertoire : ~0 rows (environ)
/*!40000 ALTER TABLE `repertoire` DISABLE KEYS */;
INSERT INTO `repertoire` (`team_id`, `contacts_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 5),
	(2, 6),
	(2, 7),
	(2, 8),
	(3, 9),
	(3, 10);
/*!40000 ALTER TABLE `repertoire` ENABLE KEYS */;