
INSERT INTO `meal` (`ID`, `name`, `objective`, `type`, `grammage`, `carbohydratesPercentage`, `proteinPercentage`, `fatPercentage`) VALUES
(1, 'Płatki owsiane na mleku 1,5%', 'r', 'b', 150, 65, 30, 10),
(2, 'Kanapki z pomidorem i chudą szynką', 'r', 'b', 100, 67, 26, 7),
(3, 'Omlet pełnoziarnisty z warzywami', 'r', 'b', 125, 63, 22, 15),
(4, 'Kurczak gotowany na parze z ziemniakami i mizerią', 'r', 'm', 200, 61, 23, 16),
(5, 'Zupa pomidorowa z ryżem', 'r', 'm', 250, 40, 21, 9),
(6, 'Pstrąg, kasza jaglana, surówka z białej kapusty', 'r', 'm', 175, 63, 22, 15),
(7, 'Risotto z warzywami', 'r', 's', 100, 65, 22, 13),
(8, 'Naleśniki z białym serem i owocami', 'r', 's', 150, 60, 30, 10),
(9, 'Sałatka szpinakowa z szynką chudą, pieczywo pełnoziarniste', 'r', 's', 125, 61, 24, 15),
(10, 'Płatki owsiane z suszonymi owocami, na mleku 2,0%', 'm', 'b', 200, 55, 25, 20),
(11, 'Jajecznica z boczkiem, pieczywo razowe, pomidor', 'm', 'b', 225, 60, 26, 14),
(12, 'Pasta z tuńczyka, pieczywo pełnoziarniste', 'm', 'b', 250, 54, 26, 20),
(13, 'Stek wołowy, ziemniaki, surówka z czerwonej kapusty', 'm', 'm', 300, 55, 30, 15),
(14, 'Stek z tuńczyka, ryż pełnoziarnisty, sałatka warzywna', 'm', 'm', 220, 55, 27, 18),
(15, 'Gotowany kalafior z jajkami sadzonymi, chleb pełnoziarnisty', 'm', 's', 200, 52, 28, 20),
(16, 'Sałatka łososiowa z brokułami, makaron razowy, shake proteinowy', 'm', 's', 250, 55, 27, 18),
(17, 'Twaróg ze szczypiorkiem, pieczywo', 'm', 's', 175, 56, 30, 14),
(18, 'Boef Stroganoff', 'm', 'm', 200, 60, 20, 0),
(19, 'Schabowy z ziemniakami i buraczkami', 'm', 'm', 250, 70, 20, 10),
(20, 'Jogurt naturalny, chleb graham', 'r', 's', 100, 40, 10, 2),
(21, 'Jajecznica, chleb pełnoziarnisty', 'r', 'b', 100, 30, 15, 10),
(22, 'Dżem owocowy, chleb razowy', 'r', 'b', 100, 50, 5, 5),
(23, 'Spaghetti po bolońsku', 'm', 'm', 200, 60, 30, 10),
(24, 'Pieczeń wieprzowa, ryż', 'm', 'm', 250, 50, 30, 20),
(25, 'Serek wiejski', 'm', 's', 100, 2, 37, 16);

--
-- Indexes for table `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT dla tabeli `meal`
--
ALTER TABLE `meal`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
