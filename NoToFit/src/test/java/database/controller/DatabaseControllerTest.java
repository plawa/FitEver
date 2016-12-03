package database.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;

import database.entities.Entity;
import database.entities.Exercise;
import database.entities.Meal;
import database.entities.User;

public class DatabaseControllerTest {

	@Before
	public void init() {
	}

	@Test
	public void testSaveAndGetEntityFromDatabase() {
		Meal mealToSave = new Meal();
		mealToSave.setType('b');
		mealToSave.setName("Test Meal");
		mealToSave.setObjective('m');

		int id = (int) DatabaseController.saveEntityToDatabase(mealToSave);

		Entity savedMeal = DatabaseController.getEntityByID(Meal.class, id);

		assertNotNull(savedMeal);
		assertTrue(savedMeal instanceof Meal);
	}

	@Test(expected = PersistenceException.class)
	public void testSaveEntityToDatabaseWithNullParameter() {
		Meal mealToSave = new Meal();
		mealToSave.setType('b');
		mealToSave.setObjective('m');
		// Mandatory name not set

		DatabaseController.saveEntityToDatabase(mealToSave);
	}

	@Test
	public void testTransactionRollbackAfterSaveFailure() {
		User userToSave = new User();
		userToSave.setName("Testname");
		userToSave.setSurname("Testsurname");
		userToSave.setSex('m');
		// Mandatory birthDate is not set

		try {
			DatabaseController.saveEntityToDatabase(userToSave);
			fail("Exception not thrown!");
		} catch (Exception e) {
			// Save fails, as the mandatory birthDate is not set
		}

		// Mandatory birthDate is set
		userToSave.setDateOfBirth(new Date());
		// As the transaction has been rollbacked, save should succeed
		DatabaseController.saveEntityToDatabase(userToSave);

	}

	@Test
	public void testGetAll() {
		List<Exercise> allExercises = DatabaseController.getAll(Exercise.class);

		assertNotNull(allExercises);
		assertTrue(allExercises.size() > 0);
	}

	@Test
	public void testGetEntitiesByParameter() {
		List<Meal> breakfasts = DatabaseController.getEntitiesByParameter(Meal.class, "type", 'b');

		assertNotNull(breakfasts);
		assertTrue(breakfasts.size() > 0);
	}

}
