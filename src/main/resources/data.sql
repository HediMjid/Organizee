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
	
INSERT INTO `membre` (`id`, `couleur`, `date_naissance`, `email`, `is_admin`, `nom`, `password`, `prenom`, `smiley`, `team_id`) VALUES
	(1, '#fcba03', '2021-12-13', 'hedi@simplon.com', '0', 'SKYWALKER', 'toto', 'Hédi', NULL, 1),
	(2, '#8df505', '2021-07-03', 'aline@simplon.com', '0', 'FETT', 'tata', 'Aline', NULL, 1),
	(3, '#091ced', '2021-01-20', 'isabelle@simplon.com', '0', 'SOLO', 'titi', 'Isabelle', NULL, 2),
	(4, '#ed09de', '2021-06-29', 'blandine@simplon.com', '0', 'VADER', 'tutu', 'Blandine', NULL, 3),
	(5, '#ed09de', '2021-08-29', 'sana@simplon.com', '0', 'C3PO', 'riri', 'Sana', NULL, 4),
	(6, '#ed09de', '2021-10-29', 'cecile@simplon.com', '0', 'R2D2', 'loulou', 'Cecile', NULL, 4);

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