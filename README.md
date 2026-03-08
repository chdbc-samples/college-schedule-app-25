# Приклад Java-програми ведення розкладу коледжу з використанням СКБД MongoDB
## Кроки для виконання
1. Завантажте і встановіть Java Development Kit 17 (або новішу версію) для Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.8.9/binaries/apache-maven-3.8.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну MAVEN_HOME=<шлях до папки з Maven>
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server вибрати варіант установки Complete -> "Run service as Network Service user"
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `college-db` і колекцію в ній `college-schedule`.
1. Зберіть програму використовуючи команду `mvn clean install`.
1. Запустіть програму за допомогою команди `mvn exec:java`.

## Результати виконання програми
```
C:\GitHub\college-schedule-app-25>mvn clean install
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.college:college-schedule >--------------------
[INFO] Building college-sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[WARNING] Parameter 'encoding' is unknown for plugin 'maven-checkstyle-plugin:3.3.1:check (checkstyle-validate)'
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ college-schedule ---
[INFO] Deleting C:\GitHub\college-schedule-app-25\target
[INFO] 
[INFO] --- checkstyle:3.3.1:check (checkstyle-validate) @ college-schedule ---
[INFO] Starting audit...
Audit done.
[INFO] You have 0 Checkstyle violations.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent (prepare-agent) @ college-schedule ---
[INFO] argLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\college-schedule-app-25\\target\\jacoco.exec
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ college-schedule ---
[INFO] Copying 2 resources from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ college-schedule ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 3 source files with javac [debug target 1.8] to target\classes
[WARNING] bootstrap class path is not set in conjunction with -source 8
  not setting the bootstrap class path may lead to class files that cannot run on JDK 8
    --release 8 is recommended instead of -source 8 -target 1.8 because it sets the bootstrap class path automatically
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[WARNING] To suppress warnings about obsolete options, use -Xlint:-options.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ college-schedule ---
[INFO] skip non existing resourceDirectory C:\GitHub\college-schedule-app-25\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ college-schedule ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug target 1.8] to target\test-classes
[WARNING] bootstrap class path is not set in conjunction with -source 8
  not setting the bootstrap class path may lead to class files that cannot run on JDK 8
    --release 8 is recommended instead of -source 8 -target 1.8 because it sets the bootstrap class path automatically
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[WARNING] To suppress warnings about obsolete options, use -Xlint:-options.
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ college-schedule ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit.JUnit3Provider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.college.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.119 s -- in com.college.AppTest
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jacoco:0.8.12:report (report) @ college-schedule ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco.exec
[INFO] Analyzed bundle 'college-sample' with 2 classes
[INFO] 
[INFO] --- jar:3.4.1:jar (default-jar) @ college-schedule ---
[INFO] Building jar: C:\GitHub\college-schedule-app-25\target\college-schedule-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- install:3.1.2:install (default-install) @ college-schedule ---
[INFO] Installing C:\GitHub\college-schedule-app-25\pom.xml to C:\Users\dmitr\.m2\repository\com\college\college-schedule\1.0-SNAPSHOT\college-schedule-1.0-SNAPSHOT.pom
[INFO] Installing C:\GitHub\college-schedule-app-25\target\college-schedule-1.0-SNAPSHOT.jar to C:\Users\dmitr\.m2\repository\com\college\college-schedule\1.0-SNAPSHOT\college-schedule-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  17.763 s
[INFO] Finished at: 2026-03-08T18:05:44+02:00
[INFO] ------------------------------------------------------------------------
```

```
C:\GitHub\college-schedule-app-25>chcp 65001
```

```
C:\GitHub\college-schedule-app-25>mvn exec:java
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.college:college-schedule >--------------------
[INFO] Building college-sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- exec:3.5.0:java (default-cli) @ college-schedule ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2026-03-08 18:07:31.502  INFO 13408 --- [lication.main()] com.college.CollegeApplication           : Starting CollegeApplication using Java 23 on DESKTOP-P20K4U1 with PID 13408 (C:\GitHub\college-schedule-app-25\target\classes started by dmitr in C:\GitHub\college-schedule-app-25)
2026-03-08 18:07:31.505  INFO 13408 --- [lication.main()] com.college.CollegeApplication           : No active profile set, falling back to default profiles: default
2026-03-08 18:07:32.207  INFO 13408 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT 
mode.
2026-03-08 18:07:32.344  INFO 13408 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 130 ms. Found 
1 MongoDB repository interfaces.
2026-03-08 18:07:33.342  INFO 13408 --- [lication.main()] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2026-03-08 18:07:33.462  INFO 13408 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:3}] to localhost:27017
2026-03-08 18:07:33.462  INFO 13408 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:4}] to localhost:27017
2026-03-08 18:07:33.465  INFO 13408 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=34980100}
2026-03-08 18:07:33.913  INFO 13408 --- [lication.main()] com.college.CollegeApplication           : Started CollegeApplication in 3.273 seconds (JVM running for 7.728)
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 1
2026-03-08 18:07:38.839  INFO 13408 --- [lication.main()] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:5}] to localhost:27017
5 документів з рядками з розкладу завантажено з CSV.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 2
Знайдено 5 документів розкладу:
Schedule { id="69ad9eca5a0d8c1ec8bdd57b"
 studentFirstName="Аліса"
 studentLastName="Мельник"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="69ad9eca5a0d8c1ec8bdd57c"
 studentFirstName="Катерина"
 studentLastName="Левченко"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="69ad9eca5a0d8c1ec8bdd57d"
 studentFirstName="Дмитро"
 studentLastName="Шевченко"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="69ad9eca5a0d8c1ec8bdd57e"
 studentFirstName="Богдан"
 studentLastName="Іванов"
 teacherFirstName="Оксана"
 teacherLastName="Коваль"
 courseName="Математичний аналіз I"
 departmentName="Математика"
 roomNumber="212"
 semester="Осінь"
 year="2024"
 startTime="11:00:00"
 endTime="12:30:00"
}
Schedule { id="69ad9eca5a0d8c1ec8bdd57f"
 studentFirstName="Олена"
 studentLastName="Петренко"
 teacherFirstName="Оксана"
 teacherLastName="Коваль"
 courseName="Математичний аналіз I"
 departmentName="Математика"
 roomNumber="212"
 semester="Осінь"
 year="2024"
 startTime="11:00:00"
 endTime="12:30:00"
}
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 3
Розклад видалено.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 4
2026-03-08 18:07:45.299  INFO 13408 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:3, serverValue:5}] to localhost:27017 because the pool has been closed.
```

## CI (GitHub Actions)

Файл workflow: `.github/workflows/build.yml`

CI-pipeline запускається автоматично для:
1. Pull request (`opened`, `synchronize`, `reopened`) у гілки `main` та `release/*`
2. Push у гілки `main` та `release/*`

Етапи pipeline:
1. `static-analysis`:
- Запускає Checkstyle (`mvn -B -DskipTests checkstyle:check`)
- Завершує CI з помилкою, якщо правила порушено
2. `build`:
- Запускає `mvn -B package` (включно з unit-тестами)
- Завершується з помилкою, якщо збірка або тести не пройшли

Артефакти та звіти:
1. Артефакт збірки: `target/*.jar`
2. Звіти тестів: `target/surefire-reports/**`
3. Звіти покриття: `target/site/jacoco/**`, `target/jacoco.exec`
4. Підсумок покриття виводиться в логах збірки та в GitHub Step Summary.