INSERT INTO `team` (`id`, `nom`) VALUES
	(1, 'Team JAVA'),
	(2, 'Team Angular'),
	(3, 'Team PHP'),
	(4, 'Team Bancal');

INSERT INTO `membre` (`id`, `couleur`, `date_naissance`, `email`, `is_admin`, `nom`, `password`, `prenom`) VALUES
	(1, '#fcba03', '2021-12-13', 'hedi@simplon.com', '0', 'SKYWALKER', 'toto', 'Hédi'),
	(2, '#8df505', '2021-07-03', 'aline@simplon.com', '0', 'FETT', 'tata', 'Aline'),
	(3, '#091ced', '2021-01-20', 'isabelle@simplon.com', '0', 'SOLO', 'titi', 'Isabelle'),
	(4, '#ed09de', '2021-06-29', 'blandine@simplon.com', '0', 'VADER', 'tutu', 'Blandine');

INSERT INTO `todo_list` (`id`, `nom`) VALUES
	(1, 'Pour Blandine'),
	(2, 'Corvées'),
	(3, 'Noel');

INSERT INTO `contact` (`id`, `adresse`, `date_naissance`, `email`, `nom`, `prenom`, `telephone`) VALUES
	(1, '7554 Messerschmidt Center', '2021-01-24', 'oogleasane0@cargocollective.com', 'Ophelia', 'O\'Gleasane', '913-198-6499'),
	(2, '534 Jay Way', '2021-03-26', 'fmowett1@ocn.ne.jp', 'Fiann', 'Mowett', '248-224-7233'),
	(3, '077 Buell Place', '2021-06-24', 'vlewknor2@spotify.com', 'Vladamir', 'Lewknor', '922-822-3626'),
	(4, '6226 Esker Street', '2021-04-13', 'jbarmadier3@opensource.org', 'Jervis', 'Barmadier', '838-581-8112'),
	(5, '28531 Luster Circle', '2021-06-15', 'tmee4@ameblo.jp', 'Tuesday', 'Mee', '761-975-7324'),
	(6, '96 Hallows Avenue', '2021-08-13', 'tcolvine5@elegantthemes.com', 'Toni', 'Colvine', '348-778-7679'),
	(7, '6401 Jay Crossing', '2021-01-14', 'rrielly6@netlog.com', 'Riane', 'Rielly', '740-571-0835'),
	(8, '3273 Cascade Pass', '2021-03-22', 'jlauder7@rambler.ru', 'Juieta', 'Lauder', '928-408-6855'),
	(9, '1170 Burning Wood Road', '2021-05-31', 'tbolver8@google.ca', 'Thibaut', 'Bolver', '681-860-8291'),
	(10, '1 Westridge Road', '2021-03-11', 'emebs9@uol.com.br', 'Evered', 'Mebs', '898-483-6075');


INSERT INTO `smiley` (`id`, `smiley_url`) VALUES
	(1, 'https://assets.wprock.fr/emoji/joypixels/512/1f600.png'),
	(2, 'https://assets.wprock.fr/emoji/joypixels/512/1f618.png'),
	(3, 'https://assets.wprock.fr/emoji/joypixels/512/1f92e.png'),
	(4, 'https://assets.wprock.fr/emoji/joypixels/512/1f92c.png');

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

INSERT INTO `smiley_membres` (`smiley_id`, `membres_id`) VALUES
	(1, 3),
	(3, 2),
	(4, 1);

INSERT INTO `tache` (`id`, `etat`, `texte`, `todolist_id`) VALUES
	(1, b'0', 'Apprendre le PHP', 1),
	(2, b'0', 'Revoir CRUD', 1),
	(3, b'0', 'Acheter des guirlandes', 3),
	(4, b'0', 'Acheter un sapin', 3),
	(5, b'0', 'Trouver un repas', 3);

INSERT INTO `team_membres` (`team_id`, `membres_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 3),
	(3, 4);

INSERT INTO `team_todolist` (`team_id`, `todolists_id`) VALUES
	(2, 2),
	(2, 3),
	(3, 1);