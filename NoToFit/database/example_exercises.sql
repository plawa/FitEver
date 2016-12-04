

INSERT INTO `exercise` (`ID`, `name`, `description`, `objective`, `requiresEquipment`, `difficultyLevel`) VALUES
(1, 'Podciąganie na drążku', 'Ćwiczący łapie drążek szerokim chwytem (nachwyt), po czym podciąga swoje ciało aż klatka piersiowa dotknie drążka.', 'm', b'1', 3),
(2, 'Pompki', 'Zwykłe pompki.', 'r', b'0', 1),
(3, 'Brzuszki', 'Ćwiczący leży na plecach z ugiętymi kolanami, po czym stara się podciągnąć swój tułów tak, by dotknąć głową kolan.', 'r', b'0', 1),
(7, 'Wyciskanie pionowe sztangi', 'Dość trudne ćwiczenie, leżysz i wyciskasz sztangę.', 'm', b'1', 2),
(9, 'Unoszenie nóg w zwisie do drążka', 'Bardzo trudne ćwiczenie na brzuch', 'r', b'1', 3),
(10, 'Uginanie ramion ze sztangielkami', 'Uginanie przedramion, dobre na biceps', 'm', b'1', 2),
(11, 'Rozpiętki ze sztangielkami na ławce poziomej', 'Trudne ćwiczenie na klatkę piersiową', 'r', b'1', 3),
(12, 'Przysiady bez sztangi', 'Istotnym elementem wykonania ćwiczenia jest zachowanie prawidłowej postawy.', 'r', b'0', 1),
(13, 'Przysiady ze sztangą', 'Idealne na mięśnie posladkowe', 'm', b'1', 2),
(16, 'Pajacyki', 'Tradycyjne pajacyki.', 'r', b'0', 1),
(18, 'Podskoki', 'Szybkie podskoki', 'r', b'0', 1),
(19, 'Skrętoskłony', 'Ćwiczenie stricte rozgrzewkowe, zwiększa ruchliwość kręgosłupa, jego wykonywanie przez dłuższy czas potrafi zwiększyć metabolizm, co sprzyja redukcji tkanki tłuszczowej.', 'r', b'0', 1),
(20, 'Wyciskanie sztangi sprzed głowy', 'Ćwiczenie polegające na unoszeniu sztangi w pozycji stojącej, z opadek do klatki piersiowej.', 'm', b'1', 2),
(21, 'Podciąganie na drążku, chwyt neutralny', 'Ćwiczący łapie drążek chwytem neutralnym, po czym podciąga swoje ciało aż klatka piersiowa dotknie drążka i opuszcza.', 'm', b'1', 3),
(22, 'Wiosłowanie sztangą w opadzie tułowia', 'Ćwiczący pochyla się przy wykorzystaniu np. ławeczki skośnej, po czym zaczyna podciągać sztangę do klatki piersiowej. Istotne jest zachowanie prostych pleców.', 'm', b'1', 3),
(23, 'Przyciąganie linki wyciągu dolnego w siadzie płaskim', 'Ważną rolę odgrywa zachowanie prostej linii pleców.', 'm', b'1', 2),
(24, 'Wspięcia na palce w staniu', 'Ćwiczenie rozbudowujące mięśnie łydek.', 'r', b'0', 1),
(25, 'Wpięcia na palce w staniu ze sztangą', 'Dodanie obciążenia umożliwia nadanie ćwiczeniu charakter odpowiedniego do budowy masy mięśniowej.', 'm', b'1', 2),
(26, 'Uginanie ramion ze sztangą stojąc podchwytem', 'Bardzo istotna jest poprawna technika wykonania oraz, co ułatwi niewygórowany cieżar.', 'm', b'1', 2),
(27, 'Syzyfki', 'Przysiady o całkowicie ograniczonym ruchu stawów biodrowych', 'm', b'0', 3),
(28, 'Prostowanie nóg w siadzie', 'Ćwiczenie na maszynie.', 'm', b'1', 1),
(29, 'Uginanie nóg w leżeniu', 'Ćwiczący lezy na brzuchu na maszynie, po czym dogina nogi do pośladków.', 'r', b'1', 2),
(30, 'Wypychanie ciężaru na suwnicy', 'Dobre ćwiczenie na zwiększenie siły oraz masy mieśniowej nóg.', 'm', b'1', 1),
(31, 'Bieganie na bieżni', '30 minut.', 'r', b'1', 1),
(32, 'Bieganie na orbitreku', '30 minut.', 'r', b'1', 2),
(33, 'Jazda na rowerze stacjonarnym', '30 minut.', 'r', b'1', 1);

--
-- Indexes for table `exercise`
--
ALTER TABLE `exercise`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT dla tabeli `exercise`
--
ALTER TABLE `exercise`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
