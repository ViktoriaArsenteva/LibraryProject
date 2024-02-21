package ru.gb.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gb.springdemo.model.Role;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

	// http://localhost:8080/swagger-ui.html

	/*
	 * План занятия:
     -- 0. Анонс группы в телеграме
     -- 1. Поговорить про стандартную структуру пакетов и "слои" в spring-web приложениях
     2. Поговорить про swagger и его подключение к приложению
     -- 3. Поговорить про REST-соглашения путей
     -- 4. Чуть подробнее рассмотреть исполнение входящего HTTP-запроса (https://mossgreen.github.io/Servlet-Containers-and-Spring-Framework/)
     -- 5. Чуть подробнее поговорить про жизненный цикл бина (и аннотацию PostConstruct)
     -- 6. Чуть подробнее поговорить про жизненный цикл поднятия контекста (и аннотацию EventListener)
     7. Без объяснений показать шаблон взаимодействия с БД (для использования в ДЗ)
     8. TODO
	 */

	/*
	 * слои spring-приложения
	 *
	 * 1. controllers (api)
	 * 2. сервисный слой (services)
	 * 3. репозитории (repositories, dao (data access objects), ...)
	 * 4. jpa-сущности (entity, model, domain)
	 *
	 *
	 * Сервер, отвечающий за выдачу книг в библиотеке.
	 * Нужно напрограммировать ручку, которая либо выдает книгу читателями, либо отказывает в выдаче.
	 *
	 * /book/** - книга
	 * GET /book/25 - получить книгу с идентификатором 25
	 *
	 * /reader/** - читатель
	 * /issue/** - информация о выдаче
	 * POST /issue {"readerId": 25, "bookId": 57} - выдать читателю с идентификатором 25 книгу с идентификатором 57
	 *
	 *

	/*
			Tomcat - контейнер сервлетов (веб-сервер)

			/student/...
			spring-student.war -> tomcat
			/api/...
			spring-api.war -> tomcat

			spring-web.jar
	 */

	public static void main(String[] args) {
		UserRepository userRepository = SpringApplication.run(Application.class, args).getBean(UserRepository.class);
		saveUser(userRepository,"admin");
		saveUser(userRepository,"reader");
		saveUser(userRepository,"auth");
	}

	private static void saveUser(UserRepository userRepository, String login){
		User user = new User(login,login);
		Set<Role>roles = new HashSet<>();
		Role role = new Role(login);
		roles.add(role);
		user.setRole(login);
		userRepository.save(user);
//		Role role2 = (Role) user.getRoles().toArray()[0];
//		Set<Role> roles2 = user.getRoles();
//		//roles2.
//
//		System.out.println(((Role) user.getRoles().toArray()[0]).getName());
	}
}
