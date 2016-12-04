ALTER TABLE WorkoutDay DROP FOREIGN KEY FKWorkoutDay651768;
ALTER TABLE WorkoutDay_Exercise DROP FOREIGN KEY FKWorkoutDay864809;
ALTER TABLE Diet DROP FOREIGN KEY FKDiet387251;
ALTER TABLE Workout DROP FOREIGN KEY FKWorkout310121;
ALTER TABLE Shadow DROP FOREIGN KEY FKShadow740385;
ALTER TABLE WorkoutDay_Exercise DROP FOREIGN KEY FKWorkoutDay837449;
ALTER TABLE DietDay DROP FOREIGN KEY FKDietDay609595;
ALTER TABLE DietDay_Meal DROP FOREIGN KEY FKDietDay_Me548787;
ALTER TABLE DietDay_Meal DROP FOREIGN KEY FKDietDay_Me27096;
ALTER TABLE WeightHistory DROP FOREIGN KEY FKWeightHist254002;
DROP TABLE IF EXISTS Shadow;
DROP TABLE IF EXISTS Diet;
DROP TABLE IF EXISTS Workout;
DROP TABLE IF EXISTS WorkoutDay;
DROP TABLE IF EXISTS WorkoutDay_Exercise;
DROP TABLE IF EXISTS Meal;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS Exercise;
DROP TABLE IF EXISTS DietDay;
DROP TABLE IF EXISTS DietDay_Meal;
DROP TABLE IF EXISTS WeightHistory;
CREATE TABLE Shadow (UserID int(4) NOT NULL, login varchar(32) NOT NULL UNIQUE, encryptedPass char(32) NOT NULL, PRIMARY KEY (UserID));
CREATE TABLE Diet (ID int(4) NOT NULL AUTO_INCREMENT, UserID int(4) NOT NULL, name varchar(30) NOT NULL, validFrom date NOT NULL, validTo date NOT NULL, dailyReq int(5) DEFAULT 0 NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Workout (ID int(4) NOT NULL AUTO_INCREMENT, UserID int(4) NOT NULL, name varchar(30) NOT NULL, validFrom date NOT NULL, validTo date NOT NULL, objective char(1), PRIMARY KEY (ID));
CREATE TABLE WorkoutDay (ID int(8) NOT NULL AUTO_INCREMENT, WorkoutID int(4) NOT NULL, `Date` date NOT NULL, PRIMARY KEY (ID));
CREATE TABLE WorkoutDay_Exercise (ExerciseID int(5) NOT NULL, WorkoutDayID int(8) NOT NULL, PRIMARY KEY (ExerciseID, WorkoutDayID));
CREATE TABLE Meal (ID int(5) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, objective char(1) NOT NULL comment 'One char:
''m'' stands for mass gain
''r'' stands for reduction
''p'' stands for power
 
 
 ', type char(1) NOT NULL comment 'One char: ''b'' stands for breakfast, ''m'' stands for main dish, ''s'' stands for supper', grammage int(4) NOT NULL, carbohydratesPercentage int(2) NOT NULL, proteinPercentage int(2) NOT NULL, fatPercentage int(2) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE `User` (ID int(4) NOT NULL AUTO_INCREMENT, name varchar(25) NOT NULL, surname varchar(35) NOT NULL, dateOfBirth date NOT NULL, sex char(1) NOT NULL comment 'One char:
''m'' for male
''f'' for female
any other char means undefined
 ', height int(3) NOT NULL, goalWeight float NOT NULL, lifeStyle int(1) comment 'from 1 (lazy) to 5 (hard-working)', somatotype int(1) comment 'from 1 (ectomorphic) to 3 (endomorphic)', PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Exercise (ID int(5) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, description varchar(255), objective char(1) NOT NULL comment 'One char:
''m'' stands for mass gain
''r'' stands for reduction
''p'' stands for power
 
 ', requiresEquipment tinyint(1) DEFAULT 0 NOT NULL, difficultyLevel int(1) comment 'Scale from 1 (easy) to 3 (hard).', PRIMARY KEY (ID));
CREATE TABLE DietDay (ID int(8) NOT NULL AUTO_INCREMENT, DietID int(4) NOT NULL, `Date` date NOT NULL, PRIMARY KEY (ID));
CREATE TABLE DietDay_Meal (MealID int(5) NOT NULL, DietDayID int(8) NOT NULL, PRIMARY KEY (MealID, DietDayID));
CREATE TABLE WeightHistory (UserID int(4) NOT NULL, `date` date NOT NULL, weight float NOT NULL, PRIMARY KEY (UserID, `date`));
ALTER TABLE WorkoutDay ADD INDEX FKWorkoutDay651768 (WorkoutID), ADD CONSTRAINT FKWorkoutDay651768 FOREIGN KEY (WorkoutID) REFERENCES Workout (ID);
ALTER TABLE WorkoutDay_Exercise ADD INDEX FKWorkoutDay864809 (WorkoutDayID), ADD CONSTRAINT FKWorkoutDay864809 FOREIGN KEY (WorkoutDayID) REFERENCES WorkoutDay (ID);
ALTER TABLE Diet ADD INDEX FKDiet387251 (UserID), ADD CONSTRAINT FKDiet387251 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Workout ADD INDEX FKWorkout310121 (UserID), ADD CONSTRAINT FKWorkout310121 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Shadow ADD INDEX FKShadow740385 (UserID), ADD CONSTRAINT FKShadow740385 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE WorkoutDay_Exercise ADD INDEX FKWorkoutDay837449 (ExerciseID), ADD CONSTRAINT FKWorkoutDay837449 FOREIGN KEY (ExerciseID) REFERENCES Exercise (ID);
ALTER TABLE DietDay ADD INDEX FKDietDay609595 (DietID), ADD CONSTRAINT FKDietDay609595 FOREIGN KEY (DietID) REFERENCES Diet (ID);
ALTER TABLE DietDay_Meal ADD INDEX FKDietDay_Me548787 (DietDayID), ADD CONSTRAINT FKDietDay_Me548787 FOREIGN KEY (DietDayID) REFERENCES DietDay (ID);
ALTER TABLE DietDay_Meal ADD INDEX FKDietDay_Me27096 (MealID), ADD CONSTRAINT FKDietDay_Me27096 FOREIGN KEY (MealID) REFERENCES Meal (ID);
ALTER TABLE WeightHistory ADD INDEX FKWeightHist254002 (UserID), ADD CONSTRAINT FKWeightHist254002 FOREIGN KEY (UserID) REFERENCES `User` (ID);
