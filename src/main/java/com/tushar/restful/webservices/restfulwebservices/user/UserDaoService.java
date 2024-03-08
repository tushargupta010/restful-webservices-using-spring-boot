package com.tushar.restful.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	// JPA/Hibernae > Database
	// UserDaoServie > static List

	private static List<User> users = new ArrayList<User>();

	private static int usersCount = 0;

	static {
		users.add(new User(++usersCount, "Tushar", LocalDate.now().minusYears(26)));
		users.add(new User(++usersCount, "Sahil", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Raju", LocalDate.now().minusYears(27)));
	}

	// find All users in array
	public List<User> findAll() {
		return users;
	}

	// save a user
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	// find a specific user
	public User findOne(int userId) {
		Predicate<User> predicate = u -> u.getId().equals(userId);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(int userId) {
		Predicate<User> predicate = u -> u.getId().equals(userId);
		users.removeIf(predicate);
	}

}
