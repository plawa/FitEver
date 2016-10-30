CREATE TABLE `User` (ID int(4) NOT NULL AUTO_INCREMENT, name varchar(25) NOT NULL, surname varchar(35) NOT NULL, dateOfBirth date NOT NULL, sex char(1) NOT NULL comment 'One char:
''m'' for male
''f'' for female
any other char means undefined
 ', height int(3) NOT NULL, startWeight float NOT NULL, actualWeight float, goalWeight float NOT NULL, fatPercentage int(2), userObjective char(1) comment 'One char:
''m'' stands for mass gain
''r'' stands for reduction
 ', lifeStyle int(1) comment 'from 1 (lazy) to 5 (hard-working)', somatotype int(1) comment 'from 1 (ectomorphic) to 3 (endomorphic)', PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Meal (ID int(5) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, objective char(1) NOT NULL comment 'One char:
''m'' stands for mass gain
''r'' stands for reduction
''p'' stands for power
 
 
 ', type char(1) NOT NULL comment 'One char: ''b'' stands for breakfast, ''m'' stands for main dish, ''s'' stands for supper', grammage int(4) NOT NULL, carbohydratesPercentage int(2) NOT NULL, proteinPercentage int(2) NOT NULL, fatPercentage int(2) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Exercise (ID int(4) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, description varchar(255), objective char(1) NOT NULL comment 'One char:
''m'' stands for mass gain
''r'' stands for reduction
''p'' stands for power
 
 ', requiresEquipment tinyint(1) DEFAULT 0 NOT NULL, difficultyLevel int(1) comment 'Scale from 1 (easy) to 3 (hard).', PRIMARY KEY (ID));
CREATE TABLE Shadow (UserID int(4) NOT NULL, login varchar(32) NOT NULL UNIQUE, encryptedPass char(32) NOT NULL, PRIMARY KEY (UserID));
CREATE TABLE Diet (ID int(4) NOT NULL AUTO_INCREMENT, UserID int(4) NOT NULL, name varchar(30) NOT NULL, validFrom date NOT NULL, validTo date NOT NULL, dailyReq int(5) DEFAULT 0 NOT NULL, breakfastCount int(1) DEFAULT 0, mainDishCount int(1) DEFAULT 0, supperCount int(1) DEFAULT 0, PRIMARY KEY (ID));
CREATE TABLE Workout (ID int(4) NOT NULL AUTO_INCREMENT, UserID int(4) NOT NULL, name varchar(30) NOT NULL, validFrom date NOT NULL, validTo date NOT NULL, objective char(1), PRIMARY KEY (ID));
CREATE TABLE Diet_Meal (DietID int(4) NOT NULL, MealID int(5) NOT NULL, PRIMARY KEY (DietID, MealID));
CREATE TABLE WorkoutDay (ID int(4) NOT NULL AUTO_INCREMENT, WorkoutID int(4) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE WorkoutDay_Exercise (WorkoutDayID int(4) NOT NULL, ExerciseID int(4) NOT NULL, PRIMARY KEY (WorkoutDayID, ExerciseID));
ALTER TABLE Diet ADD INDEX FKDiet387251 (UserID), ADD CONSTRAINT FKDiet387251 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Diet_Meal ADD INDEX FKDiet_Meal348419 (DietID), ADD CONSTRAINT FKDiet_Meal348419 FOREIGN KEY (DietID) REFERENCES Diet (ID);
ALTER TABLE Diet_Meal ADD INDEX FKDiet_Meal454239 (MealID), ADD CONSTRAINT FKDiet_Meal454239 FOREIGN KEY (MealID) REFERENCES Meal (ID);
ALTER TABLE Workout ADD INDEX FKWorkout310121 (UserID), ADD CONSTRAINT FKWorkout310121 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Shadow ADD INDEX FKShadow740385 (UserID), ADD CONSTRAINT FKShadow740385 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE WorkoutDay ADD INDEX FKWorkoutDay651768 (WorkoutID), ADD CONSTRAINT FKWorkoutDay651768 FOREIGN KEY (WorkoutID) REFERENCES Workout (ID);
ALTER TABLE WorkoutDay_Exercise ADD INDEX FKWorkoutDay864809 (WorkoutDayID), ADD CONSTRAINT FKWorkoutDay864809 FOREIGN KEY (WorkoutDayID) REFERENCES WorkoutDay (ID);
ALTER TABLE WorkoutDay_Exercise ADD INDEX FKWorkoutDay837449 (ExerciseID), ADD CONSTRAINT FKWorkoutDay837449 FOREIGN KEY (ExerciseID) REFERENCES Exercise (ID);
