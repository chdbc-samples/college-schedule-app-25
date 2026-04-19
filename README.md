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
1. Запустіть програму за допомогою команди `mvn spring-boot:run`.
1. Відкрийте веб-браузер і перейдіть за адресою `http://localhost:8081` для перегляду розкладу.

## Веб-інтерфейс

Після запуску програми веб-інтерфейс доступний у браузері за адресою `http://localhost:8081`.

Сторінки:
1. **Головна сторінка** (`http://localhost:8081`) — відображає розклад коледжу у вигляді таблиці з усіма колонками.
1. **Додати заняття** (`http://localhost:8081/add`) — форма для додавання нового рядка до розкладу.

## Налаштування `MONGO_URI`

За замовчуванням програма використовує адресу `mongodb://localhost:27017/college-db` для підключення до локальної бази даних.


### Підключення до MongoDB Atlas

Для підключення MongoDB Atlas потрібно записати connection string кластера в змінну середовища `MONGO_URI` наприклад:

```text
mongodb+srv://<db-username>:<db-password>@<cluster-url>/college-db?retryWrites=true&w=majority
```

Порядок налаштування:
1. Створіть кластер у MongoDB Atlas (тариф Free).
1. Створіть користувача бази даних у розділі Database Access і збережіть пароль.
1. Скопіюйте connection string з кнопки Connect -> Drivers і збережіть його.
1. Створіть базу даних `college-db` і колекцію `college-schedule` в розділі Data Explorer.
1. Підставте в URI свої `username`, `password` і назву бази даних `college-db` та збережіть у змінній середовища `MONGO_URI`.
1. Визначте зовнішню IP-адресу (`curl -s https://api.ipify.org && echo`) і додайте її в Network Access.

Приклад запуску з MongoDB Atlas.

Windows Command Prompt:

```cmd
set MONGO_URI=mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/college-db?retryWrites=true^&w=majority
mvn spring-boot:run
```

PowerShell:

```powershell
$env:MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/college-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

Linux/macOS:

```bash
export MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/college-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

Примітка: якщо пароль містить спеціальні символи, їх потрібно URL-кодувати в connection string. Наприклад, символ `@` потрібно замінити на `%40`.

## Запуск тестів

Локальні команди Maven:
1. Лише unit-тести:
`mvn test`
2. Лише integration-тести:
`mvn -DskipUnitTests=true verify`
3. Повна перевірка перед merge або release:
`mvn verify`

Примітка: integration-тести використовують **Testcontainers MongoDB**. Для локального запуску `mvn verify` має бути запущений Docker Desktop.

## Результати виконання програми
```
mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< com.college:college-schedule-25 >-------------------
[INFO] Building college-sample 0.2.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[WARNING] 1 problem was encountered while building the effective model for org.javassist:javassist:jar:3.20.0-GA during dependency collection step for project (use -X to see details)
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ college-schedule-25 ---
[INFO] Deleting C:\GitHub\college-schedule-app-25\target
[INFO]
[INFO] --- checkstyle:3.3.1:check (checkstyle-validate) @ college-schedule-25 ---
[INFO] Starting audit...
Audit done.
[INFO] You have 0 Checkstyle violations.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent (prepare-agent) @ college-schedule-25 ---
[INFO] argLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\college-schedule-app-25\\target\\jacoco.exec
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ college-schedule-25 ---
[INFO] Copying 4 resources from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ college-schedule-25 ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 4 source files with javac [debug target 17] to target\classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ college-schedule-25 ---
[INFO] skip non existing resourceDirectory C:\GitHub\college-schedule-app-25\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ college-schedule-25 ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 6 source files with javac [debug target 17] to target\test-classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically
[INFO]
[INFO] --- surefire:3.5.1:test (default-test) @ college-schedule-25 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.college.CollegeApplicationTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.664 s -- in com.college.CollegeApplicationTest
[INFO] Running com.college.ScheduleControllerTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.128 s -- in com.college.ScheduleControllerTest
[INFO] Running com.college.ScheduleRepositoryTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.009 s -- in com.college.ScheduleRepositoryTest
[INFO] Running com.college.ScheduleTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.025 s -- in com.college.ScheduleTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jacoco:0.8.12:report (report) @ college-schedule-25 ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco.exec
[INFO] Analyzed bundle 'college-sample' with 3 classes
[INFO]
[INFO] --- jar:3.4.1:jar (default-jar) @ college-schedule-25 ---
[INFO] Building jar: C:\GitHub\college-schedule-app-25\target\college-schedule-25-0.2.0-SNAPSHOT.jar
[INFO]
[INFO] --- jacoco:0.8.12:check (check) @ college-schedule-25 ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco.exec
[INFO] Analyzed bundle 'college-schedule-25' with 3 classes
[INFO] All coverage checks have been met.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent-integration (prepare-agent-integration) @ college-schedule-25 ---
[INFO] integrationTestsArgLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\college-schedule-app-25\\target\\jacoco-integration-tests.exec
[INFO]
[INFO] --- failsafe:3.5.1:integration-test (default) @ college-schedule-25 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
11:02:14.719 [main] INFO org.testcontainers.DockerClientFactory - Testcontainers version: 1.20.6
11:02:14.891 [main] DEBUG org.testcontainers.utility.TestcontainersConfiguration - Testcontainers configuration overrides will be loaded from file:/C:/Users/dmitr/.testcontainers.properties
11:02:16.313 [main] INFO org.testcontainers.dockerclient.DockerClientProviderStrategy - Loaded org.testcontainers.dockerclient.NpipeSocketClientProviderStrategy from ~/.testcontainers.properties, will try it first
11:02:16.334 [main] DEBUG org.testcontainers.dockerclient.DockerClientProviderStrategy - Trying out strategy: NpipeSocketClientProviderStrategy
11:02:17.070 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd:
11:02:17.237 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000001: preparing request execution
11:02:17.253 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:17.289 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:17.289 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000001: target auth state: UNCHALLENGED
11:02:17.292 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000001: proxy auth state: UNCHALLENGED
11:02:17.293 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000001: acquiring connection with route {}->npipe://localhost:2375
11:02:17.293 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000001: acquiring endpoint (3 MINUTES)
11:02:17.303 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000001: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 0; route allocated: 0 of 2147483647; total allocated: 0 of 2147483647]
11:02:17.335 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000001: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:17.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000001: acquired ep-00000000
11:02:17.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000001: acquired endpoint ep-00000000
11:02:17.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000001: opening connection {}->npipe://localhost:2375
11:02:17.390 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000000: connecting endpoint (3 MINUTES)
11:02:17.391 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: connecting endpoint to npipe://localhost:2375 (3 MINUTES)
11:02:17.418 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-0: connecting to localhost/127.0.0.1:2375
11:02:17.420 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-0: connection established
11:02:17.420 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: connected http-outgoing-0
11:02:17.422 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000000: endpoint connected
11:02:17.424 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000001: executing GET /v1.32/info HTTP/1.1
11:02:17.426 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000000: start execution ex-00000001
11:02:17.427 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: executing exchange ex-00000001 over http-outgoing-0
11:02:17.430 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/info HTTP/1.1
11:02:17.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:17.436 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:17.446 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:17.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:17.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:17.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/info HTTP/1.1[\r][\n]"
11:02:17.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:17.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:17.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:17.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:17.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:17.453 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:17.574 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:17.575 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:17.576 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:17.577 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:17 GMT[\r][\n]"
11:02:17.577 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:17.577 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:17.579 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:17.579 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:17.580 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:17.591 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:17.591 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:17.591 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:17.592 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:17 GMT
11:02:17.592 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:17.592 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:17.592 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:17.593 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:17.637 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000001: connection can be kept alive for 3 MINUTES
11:02:17.660 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "949[\r][\n]"
11:02:17.662 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"ID":"ca1e8893-0016-4da5-83d7-c55d2c041a81","Containers":2,"ContainersRunning":1,"ContainersPaused":0,"ContainersStopped":1,"Images":12,"Driver":"overlayfs","DriverStatus":[["driver-type","io.containerd.snapshotter.v1"]],"Plugins":{"Volume":["local"],"Network":["bridge","host","ipvlan","macvlan","null","overlay"],"Authorization":null,"Log":["awslogs","fluentd","gcplogs","gelf","journald","json-file","local","splunk","syslog"]},"MemoryLimit":true,"SwapLimit":true,"CpuCfsPeriod":true,"CpuCfsQuota":true,"CPUShares":true,"CPUSet":true,"PidsLimit":true,"IPv4Forwarding":true,"Debug":false,"NFd":75,"OomKillDisable":false,"NGoroutines":103,"SystemTime":"2026-04-12T08:02:17.488043313Z","LoggingDriver":"json-file","CgroupDriver":"cgroupfs","CgroupVersion":"2","NEventsListener":15,"KernelVersion":"6.6.87.2-microsoft-standard-WSL2","OperatingSystem":"Docker Desktop","OSVersion":"","OSType":"linux","Architecture":"x86_64","IndexServerAddress":"https://index.docker.io/v1/","RegistryConfig":{"IndexConfigs":{"docker.io":{"Mirrors":[],"Name":"docker.io","Official":true,"Secure":true},"hubproxy.docker.internal:5555":{"Mirrors":[],"Name":"hubproxy.docker.internal:5555","Official":false,"Secure":false}},"InsecureRegistryCIDRs":["::1/128","127.0.0.0/8"],"Mirrors":null},"NCPU":4,"MemTotal":6152237056,"GenericResources":null,"DockerRootDir":"/var/lib/docker","HttpProxy":"http.docker.internal:3128","HttpsProxy":"http.docker.internal:3128","NoProxy":"hubproxy.docker.internal","Name":"docker-desktop","Labels":["com.docker.desktop.address=npipe://\\\\.\\pipe\\docker_cli"],"ExperimentalBuild":false,"ServerVersion":"28.2.2","Runtimes":{"io.containerd.runc.v2":{"path":"runc"},"nvidia":{"path":"nvidia-container-runtime"},"runc":{"path":"runc"}},"DefaultRuntime":"runc","Swarm":{"NodeID":"","NodeAddr":"","LocalNodeState":"inactive","ControlAvailable":false,"Error":"","RemoteManagers":null},"LiveRestoreEnabled":false,"Isolation":"","InitBinary":"docker-init","ContainerdCommit":{"ID":"05044ec0a9a75232cad458027ca83437aae3f4da","Expected":"05044ec0a9a75232cad458027ca83437aae3f4da"},"RuncCommit":{"ID":"v1.2.5-0-g59923ef","Expected":"v1.2.5-0-g59923ef"},"InitCommit":{"ID":"de40ad0","Expected":"de40ad0"},"SecurityOptions":["name=seccomp,profile=builtin","name=cgroupns"],"CDISpecDirs":["/etc/cdi","/var/run/cdi"],"Warnings":["WARNING: DOCKER_INSECURE_NO_IPTABLES_RAW is set"]}[\r][\n]"
11:02:17.663 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:17.663 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:18.121 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000000: releasing valid endpoint
11:02:18.121 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: releasing endpoint
11:02:18.122 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:18.123 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000000: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.124 [main] INFO org.testcontainers.dockerclient.DockerClientProviderStrategy - Found Docker environment with local Npipe socket (npipe:////./pipe/docker_engine)
11:02:18.135 [main] DEBUG org.testcontainers.dockerclient.DockerClientProviderStrategy - Transport type: 'httpclient5', Docker host: 'npipe:////./pipe/docker_engine'
11:02:18.136 [main] DEBUG org.testcontainers.dockerclient.DockerClientProviderStrategy - Checking Docker OS type for local Npipe socket (npipe:////./pipe/docker_engine)
11:02:18.161 [main] INFO org.testcontainers.DockerClientFactory - Docker host IP address is localhost
11:02:18.162 [main] DEBUG org.testcontainers.DockerClientFactory - Docker info: {CDISpecDirs=[/etc/cdi, /var/run/cdi], NGoroutines=103, Name=docker-desktop, Swarm={NodeID=, NodeAddr=, LocalNodeState=inactive, ControlAvailable=false, Error=, RemoteManagers=null}, RuncCommit={ID=v1.2.5-0-g59923ef, Expected=v1.2.5-0-g59923ef}, OSVersion=, Runtimes={io.containerd.runc.v2={path=runc}, nvidia={path=nvidia-container-runtime}, runc={path=runc}}, ID=ca1e8893-0016-4da5-83d7-c55d2c041a81, DriverStatus=[[driver-type, io.containerd.snapshotter.v1]], LiveRestoreEnabled=false, SystemTime=2026-04-12T08:02:17.488043313Z, NoProxy=hubproxy.docker.internal, Architecture=x86_64, NEventsListener=15, HttpsProxy=http.docker.internal:3128, Images=12, ContainersRunning=1, Labels=[com.docker.desktop.address=npipe://\\.\pipe\docker_cli], InitCommit={ID=de40ad0, Expected=de40ad0}, NFd=75, KernelVersion=6.6.87.2-microsoft-standard-WSL2, LoggingDriver=json-file, OSType=linux, SecurityOptions=[name=seccomp,profile=builtin, name=cgroupns], ContainerdCommit={ID=05044ec0a9a75232cad458027ca83437aae3f4da, Expected=05044ec0a9a75232cad458027ca83437aae3f4da}, GenericResources=null, OperatingSystem=Docker Desktop, RegistryConfig={IndexConfigs={docker.io={Mirrors=[], Name=docker.io, Official=true, Secure=true}, hubproxy.docker.internal:5555={Mirrors=[], Name=hubproxy.docker.internal:5555, Official=false, Secure=false}}, InsecureRegistryCIDRs=[::1/128, 127.0.0.0/8], Mirrors=null}, ServerVersion=28.2.2, OomKillDisable=false, CpuCfsQuota=true, Containers=2, Driver=overlayfs, IPv4Forwarding=true, ExperimentalBuild=false, DockerRootDir=/var/lib/docker, CPUSet=true, CpuCfsPeriod=true, DefaultRuntime=runc, CPUShares=true, Debug=false, NCPU=4, Plugins={Volume=[local], Network=[bridge, host, ipvlan, macvlan, null, overlay], Authorization=null, Log=[awslogs, fluentd, gcplogs, gelf, journald, json-file, local, splunk, syslog]}, SwapLimit=true, CgroupDriver=cgroupfs, ContainersPaused=0, MemTotal=6152237056, IndexServerAddress=https://index.docker.io/v1/, MemoryLimit=true, HttpProxy=http.docker.internal:3128, InitBinary=docker-init, CgroupVersion=2, PidsLimit=true, Isolation=, Warnings=[WARNING: DOCKER_INSECURE_NO_IPTABLES_RAW is set], ContainersStopped=1}
11:02:18.172 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd:
11:02:18.181 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000002: preparing request execution
11:02:18.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:18.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:18.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000002: target auth state: UNCHALLENGED
11:02:18.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000002: proxy auth state: UNCHALLENGED
11:02:18.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000002: acquiring connection with route {}->npipe://localhost:2375
11:02:18.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000002: acquiring endpoint (3 MINUTES)
11:02:18.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000002: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000002: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000002: acquired ep-00000001
11:02:18.185 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000002: acquired endpoint ep-00000001
11:02:18.185 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000002: executing GET /v1.32/version HTTP/1.1
11:02:18.185 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000001: start execution ex-00000002
11:02:18.185 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000001: executing exchange ex-00000002 over http-outgoing-0
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/version HTTP/1.1
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:18.186 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:18.187 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:18.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/version HTTP/1.1[\r][\n]"
11:02:18.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:18.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:18.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:18.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:18.189 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:18.189 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:18.189 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:18.228 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:18.229 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:18.229 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:18.230 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:18 GMT[\r][\n]"
11:02:18.230 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:18.231 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:18.231 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:18.232 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:18.232 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:18.232 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:18.233 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:18.233 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:18.233 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:18 GMT
11:02:18.233 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:18.234 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:18.234 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:18.234 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:18.234 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000002: connection can be kept alive for 3 MINUTES
11:02:18.235 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "359[\r][\n]"
11:02:18.235 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"Platform":{"Name":"Docker Desktop 4.42.1 (196648)"},"Components":[{"Name":"Engine","Version":"28.2.2","Details":{"ApiVersion":"1.50","Arch":"amd64","BuildTime":"2025-05-30T12:07:26.000000000+00:00","Experimental":"false","GitCommit":"45873be","GoVersion":"go1.24.3","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","MinAPIVersion":"1.24","Os":"linux"}},{"Name":"containerd","Version":"1.7.27","Details":{"GitCommit":"05044ec0a9a75232cad458027ca83437aae3f4da"}},{"Name":"runc","Version":"1.2.5","Details":{"GitCommit":"v1.2.5-0-g59923ef"}},{"Name":"docker-init","Version":"0.19.0","Details":{"GitCommit":"de40ad0"}}],"Version":"28.2.2","ApiVersion":"1.50","MinAPIVersion":"1.24","GitCommit":"45873be","GoVersion":"go1.24.3","Os":"linux","Arch":"amd64","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","BuildTime":"2025-05-30T12:07:26.000000000+00:00"}[\n]"
11:02:18.236 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:18.236 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:18.236 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:18.270 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000001: releasing valid endpoint
11:02:18.272 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000001: releasing endpoint
11:02:18.273 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000001: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:18.274 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000001: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.275 [main] DEBUG org.testcontainers.DockerClientFactory - Docker version: {Components=[{Name=Engine, Version=28.2.2, Details={ApiVersion=1.50, Arch=amd64, BuildTime=2025-05-30T12:07:26.000000000+00:00, Experimental=false, GitCommit=45873be, GoVersion=go1.24.3, KernelVersion=6.6.87.2-microsoft-standard-WSL2, MinAPIVersion=1.24, Os=linux}}, {Name=containerd, Version=1.7.27, Details={GitCommit=05044ec0a9a75232cad458027ca83437aae3f4da}}, {Name=runc, Version=1.2.5, Details={GitCommit=v1.2.5-0-g59923ef}}, {Name=docker-init, Version=0.19.0, Details={GitCommit=de40ad0}}], KernelVersion=6.6.87.2-microsoft-standard-WSL2, ApiVersion=1.50, Platform={Name=Docker Desktop 4.42.1 (196648)}, GitCommit=45873be, Os=linux, Version=28.2.2, MinAPIVersion=1.24, Arch=amd64, GoVersion=go1.24.3, BuildTime=2025-05-30T12:07:26.000000000+00:00}
11:02:18.281 [main] INFO org.testcontainers.DockerClientFactory - Connected to docker:
  Server Version: 28.2.2
  API Version: 1.50
  Operating System: Docker Desktop
  Total Memory: 5867 MB
  Labels:
    com.docker.desktop.address=npipe://\\.\pipe\docker_cli
11:02:18.357 [main] INFO org.testcontainers.images.PullPolicy - Image pull policy will be performed by: DefaultPullPolicy()
11:02:18.387 [main] INFO org.testcontainers.utility.ImageNameSubstitutor - Image name substitution will be performed by: DefaultImageNameSubstitutor (composite of 'ConfigurationFileImageNameSubstitutor' and 'PrefixingImageNameSubstitutor')
11:02:18.429 [main] DEBUG org.testcontainers.utility.RyukResourceReaper - Ryuk is enabled
11:02:18.447 [main] DEBUG org.testcontainers.utility.PrefixingImageNameSubstitutor - No prefix is configured
11:02:18.447 [main] DEBUG org.testcontainers.utility.ImageNameSubstitutor - Did not find a substitute image for testcontainers/ryuk:0.11.0 (using image substitutor: DefaultImageNameSubstitutor (composite of 'ConfigurationFileImageNameSubstitutor' and 'PrefixingImageNameSubstitutor'))
11:02:18.456 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: ListImagesCmdImpl[filters=org.testcontainers.shaded.com.github.dockerjava.core.util.FiltersBuilder@0,imageNameFilter=<null>,showAll=false]
11:02:18.476 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000003: preparing request execution
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000003: target auth state: UNCHALLENGED
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000003: proxy auth state: UNCHALLENGED
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000003: acquiring connection with route {}->npipe://localhost:2375
11:02:18.477 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000003: acquiring endpoint (3 MINUTES)
11:02:18.478 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000003: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.481 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000003: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:18.481 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000003: acquired ep-00000002
11:02:18.481 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000003: acquired endpoint ep-00000002
11:02:18.482 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000003: executing GET /v1.32/images/json HTTP/1.1
11:02:18.482 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000002: start execution ex-00000003
11:02:18.482 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000002: executing exchange ex-00000003 over http-outgoing-0
11:02:18.483 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/images/json HTTP/1.1
11:02:18.483 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:18.484 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:18.484 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:18.484 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:18.485 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:18.485 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:18.485 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/images/json HTTP/1.1[\r][\n]"
11:02:18.485 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:18.486 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:18.486 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:18.486 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:18.487 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:18.487 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:18.487 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:19.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:19.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:19.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:19.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:19 GMT[\r][\n]"
11:02:19.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:19.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:19.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:19.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:19.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:19 GMT
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:19.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:19.348 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000003: connection can be kept alive for 3 MINUTES
11:02:19.349 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "114a[\r][\n]"
11:02:19.350 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[{"Containers":-1,"Created":1775074525,"Id":"sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe","Labels":{"org.opencontainers.image.version":"22.04"},"ParentId":"","RepoDigests":["mongo@sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe"],"RepoTags":["mongo:7.0"],"SharedSize":-1,"Size":1187732211,"VirtualSize":1187732211},{"Containers":-1,"Created":1750879333,"Id":"sha256:70f08a5f5369bd5fe577db1bf267055b113acea555b24a64f601c9718fa85836","Labels":null,"ParentId":"","RepoDigests":["cr.weaviate.io/semitechnologies/weaviate@sha256:70f08a5f5369bd5fe577db1bf267055b113acea555b24a64f601c9718fa85836"],"RepoTags":["cr.weaviate.io/semitechnologies/weaviate:1.31.3"],"SharedSize":-1,"Size":264718673,"VirtualSize":264718673},{"Containers":-1,"Created":1749823871,"Id":"sha256:321efffc83fc0f674776735dcd4b5b544868ae5c7c616271335b3afac6625814","Labels":null,"ParentId":"","RepoDigests":["cr.weaviate.io/semitechnologies/multi2vec-clip@sha256:321efffc83fc0f674776735dcd4b5b544868ae5c7c616271335b3afac6625814"],"RepoTags":["cr.weaviate.io/semitechnologies/multi2vec-clip:sentence-transformers-clip-ViT-B-32-multilingual-v1"],"SharedSize":-1,"Size":22152857153,"VirtualSize":22152857153},{"Containers":-1,"Created":1747874887,"Id":"sha256:f57a3bdbf044f0b213fdc99f35a0d21c401608bf41f063176ec00c51df9655f7","Labels":null,"ParentId":"","RepoDigests":["postgres@sha256:f57a3bdbf044f0b213fdc99f35a0d21c401608bf41f063176ec00c51df9655f7"],"RepoTags":["postgres:15"],"SharedSize":-1,"Size":608440246,"VirtualSize":608440246},{"Containers":-1,"Created":1747573137,"Id":"sha256:9e843aadb96841ddc829e356e50fafec83df662b7877faf9d32a8ecede58fea2","Labels":null,"ParentId":"","RepoDigests":["ghcr.io/chdbc-samples/college-schedule-app@sha256:9e843aadb96841ddc829e356e50fafec83df662b7877faf9d32a8ecede58fea2"],"RepoTags":["ghcr.io/chdbc-samples/college-schedule-app:0.3.0-SNAPSHOT"],"SharedSize":-1,"Size":830510353,"VirtualSize":830510353},{"Containers":-1,"Created":1747567294,"Id":"sha256:92b76ec8d5314bc1111b3409f758e565901acd528373ad35b7d5aa0e0f10191b","Labels":null,"ParentId":"","RepoDigests":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app@sha256:92b76ec8d5314bc1111b3409f758e565901acd528373ad35b7d5aa0e0f10191b"],"RepoTags":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app:1"],"SharedSize":-1,"Size":830512530,"VirtualSize":830512530},{"Containers":-1,"Created":1747567294,"Id":"sha256:eeafbf7e1345ee80f878442b6fbdc9ee8f75f1bce0471c3758c23a4e9002fe16","Labels":null,"ParentId":"","RepoDigests":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app@sha256:eeafbf7e1345ee80f878442b6fbdc9ee8f75f1bce0471c3758c23a4e9002fe16"],"RepoTags":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app:0.3.0-SNAPSHOT","ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app:latest"],"SharedSize":-1,"Size":830512544,"VirtualSize":830512544},{"Containers":-1,"Created":1747567294,"Id":"sha256:de924c4a596ab1d2f8a9c01c9670ca0dfb0f125a445eeb913fa63185a48b01d7","Labels":null,"ParentId":"","RepoDigests":["college-schedule-app@sha256:de924c4a596ab1d2f8a9c01c9670ca0dfb0f125a445eeb913fa63185a48b01d7"],"RepoTags":["college-schedule-app:0.3.0-SNAPSHOT"],"SharedSize":-1,"Size":830512500,"VirtualSize":830512500},{"Containers":-1,"Created":1747567294,"Id":"sha256:1805dd4a999e3c7b6fb7f39ca9a8a53ef29b20a90e0cda164d7c17fbbec0f97f","Labels":null,"ParentId":"","RepoDigests":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app@sha256:1805dd4a999e3c7b6fb7f39ca9a8a53ef29b20a90e0cda164d7c17fbbec0f97f"],"RepoTags":["ghcr.io/chdbc-samples/college-schedule-app/college-schedule-app:0.2.0-SNAPSHOT"],"SharedSize":-1,"Size":830512544,"VirtualSize":830512544},{"Containers":-1,"Created":1747561758,"Id":"sha256:9a8a11fef7572e224518fa88264d4d65f5c3144cfdc2452b5bde9a1902a85665","Labels":null,"ParentId":"","RepoDigests":["\u003cnone\u003e@\u003cnone\u003e"],"RepoTags":["\u003cnone\u003e:\u003cnone\u003e"],"SharedSize":-1,"Size":830512515,"VirtualSize":830512515},{"Containers":-1,"Created":1729693981,"Id":"sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5","Labels":{"org.testcontainers.ryuk":"true"},"ParentId":"","RepoDigests":["testcontainers/ryuk@sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5"],"RepoTags":["testcontainers/ryuk:0.11.0"],"SharedSize":-1,"Size":28330537,"VirtualSize":28330537}][\n]"
11:02:19.351 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:19.351 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:19.352 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:19.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000002: releasing valid endpoint
11:02:19.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000002: releasing endpoint
11:02:19.367 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000002: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:19.367 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000002: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:19.376 [main] DEBUG org.testcontainers.images.AbstractImagePullPolicy - Using locally available and not pulling image: testcontainers/ryuk:0.11.0
11:02:19.376 [main] DEBUG tc.testcontainers/ryuk:0.11.0 - Starting container: testcontainers/ryuk:0.11.0
11:02:19.380 [main] DEBUG tc.testcontainers/ryuk:0.11.0 - Trying to start container: testcontainers/ryuk:0.11.0 (attempt 1/1)
11:02:19.381 [main] DEBUG tc.testcontainers/ryuk:0.11.0 - Starting container: testcontainers/ryuk:0.11.0
11:02:19.381 [main] INFO tc.testcontainers/ryuk:0.11.0 - Creating container for image: testcontainers/ryuk:0.11.0
11:02:19.406 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - Looking up auth config for image: testcontainers/ryuk:0.11.0 at registry: https://index.docker.io/v1/
11:02:19.408 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - RegistryAuthLocator has configFile: C:\Users\dmitr\.docker\config.json (exists) configEnv: DOCKER_AUTH_CONFIG (does not exist) and commandPathPrefix:
11:02:19.408 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - RegistryAuthLocator reading from configFile: C:\Users\dmitr\.docker\config.json
11:02:19.409 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - registryName [https://index.docker.io/v1/] for dockerImageName [testcontainers/ryuk:0.11.0]
11:02:19.410 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - Executing docker credential provider: docker-credential-desktop to locate auth config for: https://index.docker.io/v1/
11:02:19.454 [main] DEBUG org.testcontainers.shaded.org.zeroturnaround.exec.ProcessExecutor - Executing [cmd, /c, docker-credential-desktop, get].
11:02:19.491 [main] DEBUG org.testcontainers.shaded.org.zeroturnaround.exec.ProcessExecutor - Started Process[pid=2568, exitValue="not exited"]
11:02:19.685 [WaitForProcess-Process[pid=2568, exitValue="not exited"]] DEBUG org.testcontainers.shaded.org.zeroturnaround.exec.WaitForProcess - Process[pid=2568, exitValue=1] stopped with exit code 1
11:02:19.690 [main] INFO org.testcontainers.utility.RegistryAuthLocator - Credential helper/store (docker-credential-desktop) does not have credentials for https://index.docker.io/v1/
11:02:19.691 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - No matching Auth Configs - falling back to defaultAuthConfig [null]
11:02:19.692 [main] DEBUG org.testcontainers.dockerclient.AuthDelegatingDockerClientConfig - Effective auth config [null]
11:02:19.742 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: org.testcontainers.shaded.com.github.dockerjava.core.command.CreateContainerCmdImpl@64fdcf99[aliases=<null>,argsEscaped=<null>,attachStderr=<null>,attachStdin=<null>,attachStdout=<null>,authConfig=<null>,cmd={},domainName=<null>,entrypoint=<null>,env={},exposedPorts=ExposedPorts(exposedPorts=[8080/tcp]),healthcheck=<null>,hostConfig=HostConfig(binds=[//var/run/docker.sock:/var/run/docker.sock:rw], blkioWeight=null, blkioWeightDevice=null, blkioDeviceReadBps=null, blkioDeviceWriteBps=null, blkioDeviceReadIOps=null, blkioDeviceWriteIOps=null, memorySwappiness=null, nanoCPUs=null, capAdd=null, capDrop=null, containerIDFile=null, cpuPeriod=null, cpuRealtimePeriod=null, cpuRealtimeRuntime=null, cpuShares=null, cpuQuota=null, cpusetCpus=null, cpusetMems=null, devices=null, deviceCgroupRules=null, deviceRequests=null, diskQuota=null, dns=null, dnsOptions=null, dnsSearch=null, extraHosts=[], groupAdd=null, ipcMode=null, cgroup=null, links=[], logConfig=LogConfig(type=null, config=null), lxcConf=null, memory=null, memorySwap=null, memoryReservation=null, kernelMemory=null, networkMode=null, oomKillDisable=null, init=null, autoRemove=true, oomScoreAdj=null, portBindings={8080/tcp=[Lcom.github.dockerjava.api.model.Ports$Binding;@3386c206}, privileged=true, publishAllPorts=null, readonlyRootfs=null, restartPolicy=null, ulimits=null, cpuCount=null, cpuPercent=null, ioMaximumIOps=null, ioMaximumBandwidth=null, volumesFrom=[], mounts=null, pidMode=null, isolation=null, securityOpts=null, storageOpt=null, cgroupParent=null, volumeDriver=null, shmSize=null, pidsLimit=null, runtime=null, tmpFs=null, utSMode=null, usernsMode=null, sysctls=null, consoleSize=null, cgroupnsMode=null),hostName=<null>,image=testcontainers/ryuk:0.11.0,ipv4Address=<null>,ipv6Address=<null>,labels={org.testcontainers=true, org.testcontainers.lang=java, org.testcontainers.version=1.20.6},macAddress=<null>,name=testcontainers-ryuk-44c8fccf-806a-49b6-800f-48b6a1a1d846,networkDisabled=<null>,networkingConfig=<null>,onBuild=<null>,platform=<null>,portSpecs=<null>,shell=<null>,stdInOnce=<null>,stdinOpen=<null>,stopSignal=<null>,stopTimeout=<null>,tty=<null>,user=<null>,volumes=Volumes(volumes=[]),workingDir=<null>]
11:02:20.071 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000004: preparing request execution
11:02:20.071 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:20.071 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:20.072 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000004: target auth state: UNCHALLENGED
11:02:20.072 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000004: proxy auth state: UNCHALLENGED
11:02:20.072 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000004: acquiring connection with route {}->npipe://localhost:2375
11:02:20.072 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000004: acquiring endpoint (3 MINUTES)
11:02:20.072 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000004: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000004: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000004: acquired ep-00000003
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000004: acquired endpoint ep-00000003
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000004: executing POST /v1.32/containers/create?name=testcontainers-ryuk-44c8fccf-806a-49b6-800f-48b6a1a1d846 HTTP/1.1
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000003: start execution ex-00000004
11:02:20.073 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000003: executing exchange ex-00000004 over http-outgoing-0
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> POST /v1.32/containers/create?name=testcontainers-ryuk-44c8fccf-806a-49b6-800f-48b6a1a1d846 HTTP/1.1
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> content-type: application/json
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:20.074 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Content-Length: 1956
11:02:20.075 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:20.075 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:20.075 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "POST /v1.32/containers/create?name=testcontainers-ryuk-44c8fccf-806a-49b6-800f-48b6a1a1d846 HTTP/1.1[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "content-type: application/json[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Content-Length: 1956[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:20.076 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:20.077 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "{"Hostname":null,"Domainname":null,"User":null,"AttachStdin":null,"AttachStdout":null,"AttachStderr":null,"PortSpecs":null,"Tty":null,"OpenStdin":null,"StdinOnce":null,"Env":[],"Cmd":[],"Healthcheck":null,"ArgsEscaped":null,"Entrypoint":null,"Image":"testcontainers/ryuk:0.11.0","Volumes":{},"WorkingDir":null,"MacAddress":null,"OnBuild":null,"NetworkDisabled":null,"ExposedPorts":{"8080/tcp":{}},"StopSignal":null,"StopTimeout":null,"HostConfig":{"Binds":["//var/run/docker.sock:/var/run/docker.sock:rw"],"BlkioWeight":null,"BlkioWeightDevice":null,"BlkioDeviceReadBps":null,"BlkioDeviceWriteBps":null,"BlkioDeviceReadIOps":null,"BlkioDeviceWriteIOps":null,"MemorySwappiness":null,"NanoCpus":null,"CapAdd":null,"CapDrop":null,"ContainerIDFile":null,"CpuPeriod":null,"CpuRealtimePeriod":null,"CpuRealtimeRuntime":null,"CpuShares":null,"CpuQuota":null,"CpusetCpus":null,"CpusetMems":null,"Devices":null,"DeviceCgroupRules":null,"DeviceRequests":null,"DiskQuota":null,"Dns":null,"DnsOptions":null,"DnsSearch":null,"ExtraHosts":[],"GroupAdd":null,"IpcMode":null,"Cgroup":null,"Links":[],"LogConfig":null,"LxcConf":null,"Memory":null,"MemorySwap":null,"MemoryReservation":null,"KernelMemory":null,"NetworkMode":null,"OomKillDisable":null,"Init":null,"AutoRemove":true,"OomScoreAdj":null,"PortBindings":{"8080/tcp":[{"HostIp":"","HostPort":""}]},"Privileged":true,"PublishAllPorts":null,"ReadonlyRootfs":null,"RestartPolicy":null,"Ulimits":null,"CpuCount":null,"CpuPercent":null,"IOMaximumIOps":null,"IOMaximumBandwidth":null,"VolumesFrom":[],"Mounts":null,"PidMode":null,"Isolation":null,"SecurityOpt":null,"StorageOpt":null,"CgroupParent":null,"VolumeDriver":null,"ShmSize":null,"PidsLimit":null,"Runtime":null,"Tmpfs":null,"UTSMode":null,"UsernsMode":null,"Sysctls":null,"ConsoleSize":null,"CgroupnsMode":null},"Labels":{"org.testcontainers":"true","org.testcontainers.lang":"java","org.testcontainers.version":"1.20.6"},"Shell":null,"NetworkingConfig":null}"
11:02:20.463 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 201 Created[\r][\n]"
11:02:20.463 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:20.463 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:20.464 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:20 GMT[\r][\n]"
11:02:20.464 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:20.464 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:20.464 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:20.465 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:20.465 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:20.465 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 201 Created
11:02:20.465 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:20 GMT
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:20.466 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:20.467 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000004: connection can be kept alive for 3 MINUTES
11:02:20.467 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "58[\r][\n]"
11:02:20.468 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"Id":"304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8","Warnings":[]}[\n]"
11:02:20.468 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:20.472 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:20.473 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:20.473 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000003: releasing valid endpoint
11:02:20.473 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000003: releasing endpoint
11:02:20.473 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000003: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:20.473 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000003: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:20.480 [main] INFO tc.testcontainers/ryuk:0.11.0 - Container testcontainers/ryuk:0.11.0 is starting: 304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8
11:02:20.488 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: 304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8
11:02:20.497 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000005: preparing request execution
11:02:20.497 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:20.497 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:20.498 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000005: target auth state: UNCHALLENGED
11:02:20.498 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000005: proxy auth state: UNCHALLENGED
11:02:20.498 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000005: acquiring connection with route {}->npipe://localhost:2375
11:02:20.498 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000005: acquiring endpoint (3 MINUTES)
11:02:20.499 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000005: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:20.499 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000005: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:20.499 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000005: acquired ep-00000004
11:02:20.499 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000005: acquired endpoint ep-00000004
11:02:20.499 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000005: executing POST /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/start HTTP/1.1
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000004: start execution ex-00000005
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000004: executing exchange ex-00000005 over http-outgoing-0
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> POST /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/start HTTP/1.1
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> content-type: application/json
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:20.500 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "POST /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/start HTTP/1.1[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "content-type: application/json[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:20.501 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:20.502 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:21.405 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 204 No Content[\r][\n]"
11:02:21.405 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:21.405 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:21 GMT[\r][\n]"
11:02:21.405 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:21.406 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:21.406 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:21.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:21.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 204 No Content
11:02:21.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:21.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:21 GMT
11:02:21.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:21.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:21.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:21.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000005: connection can be kept alive for 3 MINUTES
11:02:21.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000004: releasing valid endpoint
11:02:21.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000004: releasing endpoint
11:02:21.409 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000004: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:21.409 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000004: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.428 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: 304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8,false
11:02:21.430 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.exec.InspectContainerCmdExec - GET: DefaultWebTarget{path=[/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/json], queryParams={}}
11:02:21.431 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000006: preparing request execution
11:02:21.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:21.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:21.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000006: target auth state: UNCHALLENGED
11:02:21.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000006: proxy auth state: UNCHALLENGED
11:02:21.432 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000006: acquiring connection with route {}->npipe://localhost:2375
11:02:21.433 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000006: acquiring endpoint (3 MINUTES)
11:02:21.433 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000006: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.433 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000006: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000006: acquired ep-00000005
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000006: acquired endpoint ep-00000005
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000006: executing GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/json HTTP/1.1
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000005: start execution ex-00000006
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000005: executing exchange ex-00000006 over http-outgoing-0
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/json HTTP/1.1
11:02:21.434 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/json HTTP/1.1[\r][\n]"
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:21.435 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:21.436 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:21.436 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:21.436 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:21.436 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:21.448 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:21.448 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:21 GMT[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:21.449 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:21.450 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:21.450 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:21 GMT
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:21.451 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:21.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000006: connection can be kept alive for 3 MINUTES
11:02:21.452 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "10d4[\r][\n]"
11:02:21.454 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"Id":"304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8","Created":"2026-04-12T08:02:20.160111817Z","Path":"/bin/ryuk","Args":[],"State":{"Status":"running","Running":true,"Paused":false,"Restarting":false,"OOMKilled":false,"Dead":false,"Pid":1565,"ExitCode":0,"Error":"","StartedAt":"2026-04-12T08:02:20.7592952Z","FinishedAt":"0001-01-01T00:00:00Z"},"Image":"sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5","ResolvConfPath":"/var/lib/docker/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/resolv.conf","HostnamePath":"/var/lib/docker/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/hostname","HostsPath":"/var/lib/docker/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/hosts","LogPath":"/var/lib/docker/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8-json.log","Name":"/testcontainers-ryuk-44c8fccf-806a-49b6-800f-48b6a1a1d846","RestartCount":0,"Driver":"overlayfs","Platform":"linux","MountLabel":"","ProcessLabel":"","AppArmorProfile":"","ExecIDs":null,"HostConfig":{"Binds":["//var/run/docker.sock:/var/run/docker.sock:rw"],"ContainerIDFile":"","LogConfig":{"Type":"json-file","Config":{}},"NetworkMode":"bridge","PortBindings":{"8080/tcp":[{"HostIp":"","HostPort":""}]},"RestartPolicy":{"Name":"no","MaximumRetryCount":0},"AutoRemove":true,"VolumeDriver":"","VolumesFrom":[],"ConsoleSize":[0,0],"CapAdd":null,"CapDrop":null,"CgroupnsMode":"private","Dns":null,"DnsOptions":null,"DnsSearch":null,"ExtraHosts":[],"GroupAdd":null,"IpcMode":"shareable","Cgroup":"","Links":null,"OomScoreAdj":0,"PidMode":"","Privileged":true,"PublishAllPorts":false,"ReadonlyRootfs":false,"SecurityOpt":["label=disable"],"UTSMode":"","UsernsMode":"","ShmSize":67108864,"Runtime":"runc","Isolation":"","CpuShares":0,"Memory":0,"NanoCpus":0,"CgroupParent":"","BlkioWeight":0,"BlkioWeightDevice":null,"BlkioDeviceReadBps":null,"BlkioDeviceWriteBps":null,"BlkioDeviceReadIOps":null,"BlkioDeviceWriteIOps":null,"CpuPeriod":0,"CpuQuota":0,"CpuRealtimePeriod":0,"CpuRealtimeRuntime":0,"CpusetCpus":"","CpusetMems":"","Devices":null,"DeviceCgroupRules":null,"DeviceRequests":null,"MemoryReservation":0,"MemorySwap":0,"MemorySwappiness":null,"OomKillDisable":null,"PidsLimit":null,"Ulimits":null,"CpuCount":0,"CpuPercent":0,"IOMaximumIOps":0,"IOMaximumBandwidth":0,"MaskedPaths":null,"ReadonlyPaths":null},"GraphDriver":{"Data":null,"Name":"overlayfs"},"Mounts":[{"Type":"bind","Source":"//var/run/docker.sock","Destination":"/var/run/docker.sock","Mode":"rw","RW":true,"Propagation":"rprivate"}],"Config":{"Hostname":"304d2d8bfc26","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"ExposedPorts":{"8080/tcp":{}},"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"],"Cmd":["/bin/ryuk"],"Image":"testcontainers/ryuk:0.11.0","Volumes":null,"WorkingDir":"","Entrypoint":null,"OnBuild":null,"Labels":{"org.testcontainers":"true","org.testcontainers.lang":"java","org.testcontainers.ryuk":"true","org.testcontainers.version":"1.20.6"}},"NetworkSettings":{"Bridge":"","SandboxID":"f5cb07f87f6b094c119e8a652c38453863987f4f7dda8cfa5b655cd97ace8082","SandboxKey":"/var/run/docker/netns/f5cb07f87f6b","Ports":{"8080/tcp":[{"HostIp":"0.0.0.0","HostPort":"49254"},{"HostIp":"::","HostPort":"49254"}]},"HairpinMode":false,"LinkLocalIPv6Address":"","LinkLocalIPv6PrefixLen":0,"SecondaryIPAddresses":null,"SecondaryIPv6Addresses":null,"EndpointID":"5b96d25a094cd6bc3a6214aeab0c3378864c96501ece5ae9cfc7f7b41263f200","Gateway":"172.17.0.1","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","MacAddress":"56:37:54:a2:fa:5b","Networks":{"bridge":{"IPAMConfig":null,"Links":null,"Aliases":null,"MacAddress":"56:37:54:a2:fa:5b","DriverOpts":null,"GwPriority":0,"NetworkID":"0fc8823ed2c266ba3cf6f8e5a9740997040b3c754368741134586c0ce46d2d49","EndpointID":"5b96d25a094cd6bc3a6214aeab0c3378864c96501ece5ae9cfc7f7b41263f200","Gateway":"172.17.0.1","IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"DNSNames":null}}}}[\n]"
11:02:21.454 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:21.454 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:21.454 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:21.845 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000005: releasing valid endpoint
11:02:21.845 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000005: releasing endpoint
11:02:21.845 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000005: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:21.845 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000005: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.855 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5
11:02:21.860 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000007: preparing request execution
11:02:21.860 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000007: target auth state: UNCHALLENGED
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000007: proxy auth state: UNCHALLENGED
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000007: acquiring connection with route {}->npipe://localhost:2375
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000007: acquiring endpoint (3 MINUTES)
11:02:21.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000007: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.862 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000007: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:21.862 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000007: acquired ep-00000006
11:02:21.862 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000007: acquired endpoint ep-00000006
11:02:21.862 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000007: executing GET /v1.32/images/sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5/json HTTP/1.1
11:02:21.862 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000006: start execution ex-00000007
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000006: executing exchange ex-00000007 over http-outgoing-0
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/images/sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5/json HTTP/1.1
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:21.863 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/images/sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5/json HTTP/1.1[\r][\n]"
11:02:21.869 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:21.869 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:21.869 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:21.869 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:21.870 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:21.870 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:21.870 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:22.052 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:22.052 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:22.053 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:22.053 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:22 GMT[\r][\n]"
11:02:22.053 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:22.053 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:22.053 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:22.054 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:22.054 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:22 GMT
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:22.055 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:22.056 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:22.056 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:22.057 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000007: connection can be kept alive for 3 MINUTES
11:02:22.057 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "4a6[\r][\n]"
11:02:22.057 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"Id":"sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5","RepoTags":["testcontainers/ryuk:0.11.0"],"RepoDigests":["testcontainers/ryuk@sha256:2eaec4fc2cf1f67eb6624d4463467ea8aac999a01b2ff74b5973e9a563b7c2b5"],"Parent":"","Comment":"buildkit.dockerfile.v0","Created":"2024-10-23T14:33:01.88939101Z","DockerVersion":"","Author":"","Architecture":"amd64","Os":"linux","Size":11410956,"VirtualSize":11410956,"GraphDriver":{"Data":null,"Name":"overlayfs"},"RootFS":{"Type":"layers","Layers":["sha256:63ca1fbb43ae5034640e5e6cb3e083e05c290072c5366fcaa9d62435a4cced85","sha256:b16bb745c837b9b44d82089978628b7047d6916898287ab761b6efbf155ba390","sha256:274bded55a121c797c27d22f8f6961ef6e888f63d6354bace49d0d27fae6663d"]},"Metadata":{"LastTagTime":"2026-04-11T20:54:15.588938199Z"},"Config":{"Hostname":"","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"],"Cmd":["/bin/ryuk"],"ArgsEscaped":true,"Image":"","Volumes":null,"WorkingDir":"","Entrypoint":null,"OnBuild":null,"Labels":{"org.testcontainers.ryuk":"true"}}}[\n]"
11:02:22.058 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.058 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:22.058 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.089 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000006: releasing valid endpoint
11:02:22.090 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000006: releasing endpoint
11:02:22.091 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000006: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:22.091 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000006: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.091 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd:
11:02:22.092 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000008: preparing request execution
11:02:22.093 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:22.093 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:22.093 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000008: target auth state: UNCHALLENGED
11:02:22.093 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000008: proxy auth state: UNCHALLENGED
11:02:22.093 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000008: acquiring connection with route {}->npipe://localhost:2375
11:02:22.097 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000008: acquiring endpoint (3 MINUTES)
11:02:22.098 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000008: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.102 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000008: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.102 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000008: acquired ep-00000007
11:02:22.102 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000008: acquired endpoint ep-00000007
11:02:22.103 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000008: executing GET /v1.32/version HTTP/1.1
11:02:22.103 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000007: start execution ex-00000008
11:02:22.103 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000007: executing exchange ex-00000008 over http-outgoing-0
11:02:22.103 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/version HTTP/1.1
11:02:22.104 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> accept: application/json
11:02:22.104 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:22.104 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/version HTTP/1.1[\r][\n]"
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "accept: application/json[\r][\n]"
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:22.105 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:22.106 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:22.106 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/json[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:22 GMT[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:22.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:22.143 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:22.143 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:22.143 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.144 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:22.145 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:22.145 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/json
11:02:22.145 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:22 GMT
11:02:22.145 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:22.146 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:22.146 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:22.146 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:22.146 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000008: connection can be kept alive for 3 MINUTES
11:02:22.147 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "359[\r][\n]"
11:02:22.148 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "{"Platform":{"Name":"Docker Desktop 4.42.1 (196648)"},"Components":[{"Name":"Engine","Version":"28.2.2","Details":{"ApiVersion":"1.50","Arch":"amd64","BuildTime":"2025-05-30T12:07:26.000000000+00:00","Experimental":"false","GitCommit":"45873be","GoVersion":"go1.24.3","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","MinAPIVersion":"1.24","Os":"linux"}},{"Name":"containerd","Version":"1.7.27","Details":{"GitCommit":"05044ec0a9a75232cad458027ca83437aae3f4da"}},{"Name":"runc","Version":"1.2.5","Details":{"GitCommit":"v1.2.5-0-g59923ef"}},{"Name":"docker-init","Version":"0.19.0","Details":{"GitCommit":"de40ad0"}}],"Version":"28.2.2","ApiVersion":"1.50","MinAPIVersion":"1.24","GitCommit":"45873be","GoVersion":"go1.24.3","Os":"linux","Arch":"amd64","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","BuildTime":"2025-05-30T12:07:26.000000000+00:00"}[\n]"
11:02:22.148 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.148 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "0[\r][\n]"
11:02:22.148 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.152 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000007: releasing valid endpoint
11:02:22.152 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000007: releasing endpoint
11:02:22.152 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000007: connection http-outgoing-0 can be kept alive for 3 MINUTES
11:02:22.152 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000007: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.182 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000009: preparing request execution
11:02:22.183 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:22.183 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:22.183 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000009: target auth state: UNCHALLENGED
11:02:22.184 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000009: proxy auth state: UNCHALLENGED
11:02:22.184 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000009: acquiring connection with route {}->npipe://localhost:2375
11:02:22.184 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000009: acquiring endpoint (3 MINUTES)
11:02:22.184 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000009: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.185 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000009: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:22.185 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000009: acquired ep-00000008
11:02:22.185 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000009: acquired endpoint ep-00000008
11:02:22.185 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000009: executing GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1
11:02:22.186 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000008: start execution ex-00000009
11:02:22.186 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000008: executing exchange ex-00000009 over http-outgoing-0
11:02:22.187 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1
11:02:22.187 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:22.187 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> User-Agent: tc-java/1.20.6
11:02:22.187 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:22.187 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Host: localhost:2375
11:02:22.188 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 >> Connection: keep-alive
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "GET /v1.32/containers/304d2d8bfc26bb4c27ca4e82f2ac3b19666ea19875c6ea3a880fcad801d2e7a8/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Host: localhost:2375[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "Connection: keep-alive[\r][\n]"
11:02:22.189 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 >> "[\r][\n]"
11:02:22.196 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Api-Version: 1.50[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Content-Type: application/vnd.docker.raw-stream[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Date: Sun, 12 Apr 2026 08:02:22 GMT[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Docker-Experimental: false[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Ostype: linux[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "Transfer-Encoding: chunked[\r][\n]"
11:02:22.197 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.198 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << HTTP/1.1 200 OK
11:02:22.198 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Api-Version: 1.50
11:02:22.198 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Content-Type: application/vnd.docker.raw-stream
11:02:22.198 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Date: Sun, 12 Apr 2026 08:02:22 GMT
11:02:22.198 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Docker-Experimental: false
11:02:22.199 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Ostype: linux
11:02:22.199 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Server: Docker/28.2.2 (linux)
11:02:22.199 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-0 << Transfer-Encoding: chunked
11:02:22.199 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000009: connection can be kept alive for 3 MINUTES
11:02:22.201 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "18f[\r][\n]"
11:02:22.201 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffe7]time=2026-04-12T08:02:21.350Z level=INFO msg=starting connection_timeout=1m0s reconnection_timeout=10s request_timeout=10s shutdown_timeout=10m0s remove_retries=10 retry_offset=-1s changes_retry_interval=1s port=8080 verbose=false[\n]"
11:02:22.201 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0]Gtime=2026-04-12T08:02:21.352Z level=INFO msg=Started address=[::]:8081[\n]"
11:02:22.201 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0]Itime=2026-04-12T08:02:21.352Z level=INFO msg="client processing started"[\n]"
11:02:22.201 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[\r][\n]"
11:02:22.206 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: time=2026-04-12T08:02:21.350Z level=INFO msg=starting connection_timeout=1m0s reconnection_timeout=10s request_timeout=10s shutdown_timeout=10m0s remove_retries=10 retry_offset=-1s changes_retry_interval=1s port=8080 verbose=false
11:02:22.207 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: time=2026-04-12T08:02:21.352Z level=INFO msg="client processing started"
11:02:22.208 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: time=2026-04-12T08:02:21.352Z level=INFO msg=Started address=[::]:8081
11:02:22.210 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000008: cancel
11:02:22.212 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultManagedHttpClientConnection - http-outgoing-0: close connection IMMEDIATE
11:02:22.213 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000008: endpoint closed
11:02:22.213 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000008: discarding endpoint
11:02:22.213 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000008: releasing endpoint
11:02:22.213 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000008: connection is not kept alive
11:02:22.213 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "end of stream"
11:02:22.213 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000008: connection released [route: {}->npipe://localhost:2375][total available: 0; route allocated: 0 of 2147483647; total allocated: 0 of 2147483647]
11:02:22.217 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[read] I/O error: java.nio.channels.ClosedChannelException"
11:02:22.227 [main] DEBUG com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse - Failed to close the response
java.io.IOException: java.nio.channels.ClosedChannelException
        at java.base/java.nio.channels.Channels$1.read(Channels.java:166)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.LoggingInputStream.read(LoggingInputStream.java:81)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.fillBuffer(SessionInputBufferImpl.java:149)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.readLine(SessionInputBufferImpl.java:280)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.getChunkSize(ChunkedInputStream.java:261)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.nextChunk(ChunkedInputStream.java:222)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.read(ChunkedInputStream.java:147)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.close(ChunkedInputStream.java:314)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.IncomingHttpEntity.close(IncomingHttpEntity.java:111)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.HttpEntityWrapper.close(HttpEntityWrapper.java:120)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.message.BasicClassicHttpResponse.close(BasicClassicHttpResponse.java:93)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse.close(CloseableHttpResponse.java:200)
        at com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse.close(ApacheDockerHttpClientImpl.java:271)
        at org.testcontainers.shaded.com.github.dockerjava.core.DefaultInvocationBuilder.lambda$null$0(DefaultInvocationBuilder.java:272)
        at com.github.dockerjava.api.async.ResultCallbackTemplate.close(ResultCallbackTemplate.java:77)
        at org.testcontainers.containers.output.FrameConsumerResultCallback.close(FrameConsumerResultCallback.java:69)
        at org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy.waitUntilReady(LogMessageWaitStrategy.java:49)
        at org.testcontainers.containers.wait.strategy.AbstractWaitStrategy.waitUntilReady(AbstractWaitStrategy.java:52)
        at org.testcontainers.containers.GenericContainer.waitUntilContainerStarted(GenericContainer.java:909)
        at org.testcontainers.containers.GenericContainer.tryStart(GenericContainer.java:492)
        at org.testcontainers.containers.GenericContainer.lambda$doStart$0(GenericContainer.java:346)
        at org.rnorth.ducttape.unreliables.Unreliables.retryUntilSuccess(Unreliables.java:81)
        at org.testcontainers.containers.GenericContainer.doStart(GenericContainer.java:336)
        at org.testcontainers.containers.GenericContainer.start(GenericContainer.java:322)
        at org.testcontainers.utility.RyukResourceReaper.maybeStart(RyukResourceReaper.java:78)
        at org.testcontainers.utility.RyukResourceReaper.init(RyukResourceReaper.java:42)
        at org.testcontainers.DockerClientFactory.client(DockerClientFactory.java:245)
        at org.testcontainers.junit.jupiter.DockerAvailableDetector.isDockerAvailable(DockerAvailableDetector.java:9)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.isDockerAvailable(TestcontainersExtension.java:196)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.evaluate(TestcontainersExtension.java:187)
        at java.base/java.util.Optional.map(Optional.java:260)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.evaluateExecutionCondition(TestcontainersExtension.java:166)
        at org.junit.jupiter.engine.execution.ConditionEvaluator.evaluate(ConditionEvaluator.java:64)
        at org.junit.jupiter.engine.execution.ConditionEvaluator.lambda$evaluate$0(ConditionEvaluator.java:55)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:215)
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.stream.StreamSpliterators$WrappingSpliterator.tryAdvance(StreamSpliterators.java:305)
        at java.base/java.util.stream.Streams$ConcatSpliterator.tryAdvance(Streams.java:723)
        at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:147)
        at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:588)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:574)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        at java.base/java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        at java.base/java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:687)
        at org.junit.jupiter.engine.execution.ConditionEvaluator.evaluate(ConditionEvaluator.java:57)
        at org.junit.jupiter.engine.descriptor.JupiterTestDescriptor.shouldBeSkipped(JupiterTestDescriptor.java:202)
        at org.junit.jupiter.engine.descriptor.JupiterTestDescriptor.shouldBeSkipped(JupiterTestDescriptor.java:57)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$checkWhetherSkipped$2(NodeTestTask.java:119)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.checkWhetherSkipped(NodeTestTask.java:119)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:81)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:108)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:88)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:184)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:148)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:122)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.nio.channels.ClosedChannelException: null
        at java.base/sun.nio.ch.WindowsAsynchronousFileChannelImpl.implRead(WindowsAsynchronousFileChannelImpl.java:537)
        at java.base/sun.nio.ch.AsynchronousFileChannelImpl.read(AsynchronousFileChannelImpl.java:229)
        at com.github.dockerjava.transport.NamedPipeSocket$AsynchronousFileByteChannel.read(NamedPipeSocket.java:117)
        at java.base/java.nio.channels.Channels$1.read(Channels.java:164)
        ... 81 common frames omitted
11:02:22.230 [main] INFO tc.testcontainers/ryuk:0.11.0 - Container testcontainers/ryuk:0.11.0 started in PT2.8480536S
11:02:22.236 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-0 << "[read] I/O error: java.nio.channels.ClosedChannelException"
11:02:22.236 [docker-java-stream-837770498] DEBUG com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse - Failed to close the response
java.io.IOException: java.nio.channels.ClosedChannelException
        at java.base/java.nio.channels.Channels$1.read(Channels.java:166)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.LoggingInputStream.read(LoggingInputStream.java:81)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.fillBuffer(SessionInputBufferImpl.java:149)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.readLine(SessionInputBufferImpl.java:280)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.getChunkSize(ChunkedInputStream.java:261)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.nextChunk(ChunkedInputStream.java:222)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.read(ChunkedInputStream.java:147)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.close(ChunkedInputStream.java:314)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.IncomingHttpEntity.close(IncomingHttpEntity.java:111)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.HttpEntityWrapper.close(HttpEntityWrapper.java:120)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.message.BasicClassicHttpResponse.close(BasicClassicHttpResponse.java:93)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse.close(CloseableHttpResponse.java:200)
        at com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse.close(ApacheDockerHttpClientImpl.java:271)
        at org.testcontainers.shaded.com.github.dockerjava.core.DefaultInvocationBuilder.lambda$executeAndStream$1(DefaultInvocationBuilder.java:277)
        at java.base/java.lang.Thread.run(Thread.java:1575)
Caused by: java.nio.channels.ClosedChannelException: null
        at java.base/sun.nio.ch.WindowsAsynchronousFileChannelImpl.implRead(WindowsAsynchronousFileChannelImpl.java:537)
        at java.base/sun.nio.ch.AsynchronousFileChannelImpl.read(AsynchronousFileChannelImpl.java:229)
        at com.github.dockerjava.transport.NamedPipeSocket$AsynchronousFileByteChannel.read(NamedPipeSocket.java:117)
        at java.base/java.nio.channels.Channels$1.read(Channels.java:164)
        ... 16 common frames omitted
11:02:22.266 [testcontainers-ryuk] DEBUG org.testcontainers.utility.ResourceReaper - Sending 'label=org.testcontainers%3Dtrue&label=org.testcontainers.lang%3Djava&label=org.testcontainers.version%3D1.20.6&label=org.testcontainers.sessionId%3D44c8fccf-806a-49b6-800f-48b6a1a1d846' to Ryuk
11:02:22.271 [testcontainers-ryuk] DEBUG org.testcontainers.utility.RyukResourceReaper - Received 'ACK' from Ryuk
11:02:22.271 [main] INFO org.testcontainers.utility.RyukResourceReaper - Ryuk started - will monitor and terminate Testcontainers containers on JVM exit
11:02:22.272 [main] DEBUG org.testcontainers.DockerClientFactory - Checks are enabled
11:02:22.272 [main] INFO org.testcontainers.DockerClientFactory - Checking the system...
11:02:22.275 [main] INFO org.testcontainers.DockerClientFactory - ?? Docker server version should be at least 1.6.0
[INFO] Running com.college.ScheduleFlowIntegrationTests
11:02:22.391 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating CacheAwareContextLoaderDelegate from class [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
11:02:22.442 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating BootstrapContext using constructor [public org.springframework.test.context.support.DefaultBootstrapContext(java.lang.Class,org.springframework.test.context.CacheAwareContextLoaderDelegate)]
11:02:22.689 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating TestContextBootstrapper for test class [com.college.ScheduleFlowIntegrationTests] from class [org.springframework.boot.test.context.SpringBootTestContextBootstrapper]
11:02:22.743 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Neither @ContextConfiguration nor @ContextHierarchy found for test class [com.college.ScheduleFlowIntegrationTests], using SpringBootContextLoader
11:02:22.759 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [com.college.ScheduleFlowIntegrationTests]: class path resource [com/college/ScheduleFlowIntegrationTests-context.xml] does not exist
11:02:22.760 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [com.college.ScheduleFlowIntegrationTests]: class path resource [com/college/ScheduleFlowIntegrationTestsContext.groovy] does not exist
11:02:22.761 [main] INFO org.springframework.test.context.support.AbstractContextLoader - Could not detect default resource locations for test class [com.college.ScheduleFlowIntegrationTests]: no resource found for suffixes {-context.xml, Context.groovy}.
11:02:22.763 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils - Could not detect default configuration classes for test class [com.college.ScheduleFlowIntegrationTests]: ScheduleFlowIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
11:02:23.052 [main] DEBUG org.springframework.test.context.support.ActiveProfilesUtils - Could not find an 'annotation declaring class' for annotation type [org.springframework.test.context.ActiveProfiles] and class [com.college.ScheduleFlowIntegrationTests]
11:02:23.327 [main] DEBUG org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider - Identified candidate component class: URL [jar:file:/C:/GitHub/college-schedule-app-25/target/college-schedule-25-0.2.0-SNAPSHOT.jar!/com/college/CollegeApplication.class]
11:02:23.359 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Found @SpringBootConfiguration com.college.CollegeApplication for test class com.college.ScheduleFlowIntegrationTests
11:02:23.725 [main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper - @TestExecutionListeners is not present for class [com.college.ScheduleFlowIntegrationTests]: using defaults.
11:02:23.727 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]
11:02:23.789 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@69cc49ec, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@6b71e98f, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@6ed7b9c5, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@105dc246, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@1b556a88, org.springframework.test.context.support.DirtiesContextTestExecutionListener@7f8cd5fc, org.springframework.test.context.transaction.TransactionalTestExecutionListener@2c465259, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@52f7fa65, org.springframework.test.context.event.EventPublishingTestExecutionListener@62c6db99, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@7bfcc108, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@65b680b4, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@87f6ab5, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@5c316230, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@41581c3f, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@10ba9780]
11:02:23.810 [main] DEBUG org.springframework.test.context.support.AbstractDirtiesContextTestExecutionListener - Before test class: context [DefaultTestContext@1da32baf testClass = ScheduleFlowIntegrationTests, testInstance = [null], testMethod = [null], testException = [null], mergedContextConfiguration = [WebMergedContextConfiguration@4e4af370 testClass = ScheduleFlowIntegrationTests, locations = '{}', classes = '{class com.college.CollegeApplication}', contextInitializerClasses = '[]', activeProfiles = '{}', propertySourceLocations = '{}', propertySourceProperties = '{org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true}', contextCustomizers = set[[ImportsContextCustomizer@5cf33d25 key = [org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebDriverAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityConfiguration]], org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@1e95f584, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@189633f2, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@68454ed5, org.springframework.boot.test.autoconfigure.actuate.metrics.MetricsExportContextCustomizerFactory$DisableMetricExportContextCustomizer@68479e8b, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@4b3fa0b3, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@352c3d70, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@7f57938, org.springframework.boot.test.context.SpringBootTestArgs@1, org.springframework.boot.test.context.SpringBootTestWebEnvironment@47caedad], resourceBasePath = 'src/main/webapp', contextLoader = 'org.springframework.boot.test.context.SpringBootContextLoader', parent = [null]], attributes = map['org.springframework.test.context.web.ServletTestExecutionListener.activateListener' -> true]], class annotated with @DirtiesContext [false] with mode [null].
11:02:23.826 [main] DEBUG org.testcontainers.utility.PrefixingImageNameSubstitutor - No prefix is configured
11:02:23.827 [main] DEBUG org.testcontainers.utility.ImageNameSubstitutor - Did not find a substitute image for mongo:7.0 (using image substitutor: DefaultImageNameSubstitutor (composite of 'ConfigurationFileImageNameSubstitutor' and 'PrefixingImageNameSubstitutor'))
11:02:23.827 [main] DEBUG org.testcontainers.images.AbstractImagePullPolicy - Using locally available and not pulling image: mongo:7.0
11:02:23.827 [main] DEBUG tc.mongo:7.0 - Starting container: mongo:7.0
11:02:23.827 [main] DEBUG tc.mongo:7.0 - Trying to start container: mongo:7.0 (attempt 1/1)
11:02:23.827 [main] DEBUG tc.mongo:7.0 - Starting container: mongo:7.0
11:02:23.827 [main] INFO tc.mongo:7.0 - Creating container for image: mongo:7.0
11:02:23.827 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - Looking up auth config for image: mongo:7.0 at registry: https://index.docker.io/v1/
11:02:23.828 [main] DEBUG org.testcontainers.utility.RegistryAuthLocator - No matching Auth Configs - falling back to defaultAuthConfig [null]
11:02:23.828 [main] DEBUG org.testcontainers.dockerclient.AuthDelegatingDockerClientConfig - Effective auth config [null]
11:02:23.828 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: org.testcontainers.shaded.com.github.dockerjava.core.command.CreateContainerCmdImpl@71eafb64[aliases=<null>,argsEscaped=<null>,attachStderr=<null>,attachStdin=<null>,attachStdout=<null>,authConfig=<null>,cmd={--replSet,docker-rs},domainName=<null>,entrypoint=<null>,env={},exposedPorts=ExposedPorts(exposedPorts=[27017/tcp]),healthcheck=<null>,hostConfig=HostConfig(binds=[], blkioWeight=null, blkioWeightDevice=null, blkioDeviceReadBps=null, blkioDeviceWriteBps=null, blkioDeviceReadIOps=null, blkioDeviceWriteIOps=null, memorySwappiness=null, nanoCPUs=null, capAdd=null, capDrop=null, containerIDFile=null, cpuPeriod=null, cpuRealtimePeriod=null, cpuRealtimeRuntime=null, cpuShares=null, cpuQuota=null, cpusetCpus=null, cpusetMems=null, devices=null, deviceCgroupRules=null, deviceRequests=null, diskQuota=null, dns=null, dnsOptions=null, dnsSearch=null, extraHosts=[], groupAdd=null, ipcMode=null, cgroup=null, links=[], logConfig=LogConfig(type=null, config=null), lxcConf=null, memory=null, memorySwap=null, memoryReservation=null, kernelMemory=null, networkMode=null, oomKillDisable=null, init=null, autoRemove=null, oomScoreAdj=null, portBindings={27017/tcp=[Lcom.github.dockerjava.api.model.Ports$Binding;@746e534}, privileged=null, publishAllPorts=null, readonlyRootfs=null, restartPolicy=null, ulimits=null, cpuCount=null, cpuPercent=null, ioMaximumIOps=null, ioMaximumBandwidth=null, volumesFrom=[], mounts=null, pidMode=null, isolation=null, securityOpts=null, storageOpt=null, cgroupParent=null, volumeDriver=null, shmSize=null, pidsLimit=null, runtime=null, tmpFs=null, utSMode=null, usernsMode=null, sysctls=null, consoleSize=null, cgroupnsMode=null),hostName=<null>,image=mongo:7.0,ipv4Address=<null>,ipv6Address=<null>,labels={org.testcontainers=true, org.testcontainers.lang=java, org.testcontainers.version=1.20.6, org.testcontainers.sessionId=44c8fccf-806a-49b6-800f-48b6a1a1d846},macAddress=<null>,name=<null>,networkDisabled=<null>,networkingConfig=<null>,onBuild=<null>,platform=<null>,portSpecs=<null>,shell=<null>,stdInOnce=<null>,stdinOpen=<null>,stopSignal=<null>,stopTimeout=<null>,tty=<null>,user=<null>,volumes=Volumes(volumes=[]),workingDir=<null>]
11:02:23.832 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000A: preparing request execution
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000A: target auth state: UNCHALLENGED
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000A: proxy auth state: UNCHALLENGED
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000A: acquiring connection with route {}->npipe://localhost:2375
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000A: acquiring endpoint (3 MINUTES)
11:02:23.833 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000A: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 0; route allocated: 0 of 2147483647; total allocated: 0 of 2147483647]
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000A: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000A: acquired ep-00000009
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000A: acquired endpoint ep-00000009
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000A: opening connection {}->npipe://localhost:2375
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000009: connecting endpoint (3 MINUTES)
11:02:23.834 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: connecting endpoint to npipe://localhost:2375 (3 MINUTES)
11:02:23.835 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-1: connecting to localhost/127.0.0.1:2375
11:02:23.835 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-1: connection established
11:02:23.835 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: connected http-outgoing-1
11:02:23.836 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000009: endpoint connected
11:02:23.836 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000A: executing POST /v1.32/containers/create HTTP/1.1
11:02:23.836 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000009: start execution ex-0000000A
11:02:23.836 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: executing exchange ex-0000000A over http-outgoing-1
11:02:23.836 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> POST /v1.32/containers/create HTTP/1.1
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> accept: application/json
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> content-type: application/json
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Content-Length: 1987
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "POST /v1.32/containers/create HTTP/1.1[\r][\n]"
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "accept: application/json[\r][\n]"
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "content-type: application/json[\r][\n]"
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:23.837 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Content-Length: 1987[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:23.838 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "{"Hostname":null,"Domainname":null,"User":null,"AttachStdin":null,"AttachStdout":null,"AttachStderr":null,"PortSpecs":null,"Tty":null,"OpenStdin":null,"StdinOnce":null,"Env":[],"Cmd":["--replSet","docker-rs"],"Healthcheck":null,"ArgsEscaped":null,"Entrypoint":null,"Image":"mongo:7.0","Volumes":{},"WorkingDir":null,"MacAddress":null,"OnBuild":null,"NetworkDisabled":null,"ExposedPorts":{"27017/tcp":{}},"StopSignal":null,"StopTimeout":null,"HostConfig":{"Binds":[],"BlkioWeight":null,"BlkioWeightDevice":null,"BlkioDeviceReadBps":null,"BlkioDeviceWriteBps":null,"BlkioDeviceReadIOps":null,"BlkioDeviceWriteIOps":null,"MemorySwappiness":null,"NanoCpus":null,"CapAdd":null,"CapDrop":null,"ContainerIDFile":null,"CpuPeriod":null,"CpuRealtimePeriod":null,"CpuRealtimeRuntime":null,"CpuShares":null,"CpuQuota":null,"CpusetCpus":null,"CpusetMems":null,"Devices":null,"DeviceCgroupRules":null,"DeviceRequests":null,"DiskQuota":null,"Dns":null,"DnsOptions":null,"DnsSearch":null,"ExtraHosts":[],"GroupAdd":null,"IpcMode":null,"Cgroup":null,"Links":[],"LogConfig":null,"LxcConf":null,"Memory":null,"MemorySwap":null,"MemoryReservation":null,"KernelMemory":null,"NetworkMode":null,"OomKillDisable":null,"Init":null,"AutoRemove":null,"OomScoreAdj":null,"PortBindings":{"27017/tcp":[{"HostIp":"","HostPort":""}]},"Privileged":null,"PublishAllPorts":null,"ReadonlyRootfs":null,"RestartPolicy":null,"Ulimits":null,"CpuCount":null,"CpuPercent":null,"IOMaximumIOps":null,"IOMaximumBandwidth":null,"VolumesFrom":[],"Mounts":null,"PidMode":null,"Isolation":null,"SecurityOpt":null,"StorageOpt":null,"CgroupParent":null,"VolumeDriver":null,"ShmSize":null,"PidsLimit":null,"Runtime":null,"Tmpfs":null,"UTSMode":null,"UsernsMode":null,"Sysctls":null,"ConsoleSize":null,"CgroupnsMode":null},"Labels":{"org.testcontainers":"true","org.testcontainers.lang":"java","org.testcontainers.version":"1.20.6","org.testcontainers.sessionId":"44c8fccf-806a-49b6-800f-48b6a1a1d846"},"Shell":null,"NetworkingConfig":null}"
11:02:24.190 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 201 Created[\r][\n]"
11:02:24.191 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:24.191 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Content-Type: application/json[\r][\n]"
11:02:24.191 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:24 GMT[\r][\n]"
11:02:24.191 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:24.191 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:24.192 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:24.192 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Transfer-Encoding: chunked[\r][\n]"
11:02:24.192 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 201 Created
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Content-Type: application/json
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:24 GMT
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:24.193 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Transfer-Encoding: chunked
11:02:24.194 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000A: connection can be kept alive for 3 MINUTES
11:02:24.195 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "58[\r][\n]"
11:02:24.195 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "{"Id":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","Warnings":[]}[\n]"
11:02:24.195 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:24.195 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "0[\r][\n]"
11:02:24.196 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:24.197 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000009: releasing valid endpoint
11:02:24.197 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: releasing endpoint
11:02:24.197 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: connection http-outgoing-1 can be kept alive for 3 MINUTES
11:02:24.198 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000009: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:24.198 [main] INFO tc.mongo:7.0 - Container mongo:7.0 is starting: 93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4
11:02:24.198 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: 93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4
11:02:24.199 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000B: preparing request execution
11:02:24.199 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:24.199 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:24.199 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000B: target auth state: UNCHALLENGED
11:02:24.200 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000B: proxy auth state: UNCHALLENGED
11:02:24.200 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000B: acquiring connection with route {}->npipe://localhost:2375
11:02:24.200 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000B: acquiring endpoint (3 MINUTES)
11:02:24.201 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000B: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:24.202 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000B: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000B: acquired ep-0000000A
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000B: acquired endpoint ep-0000000A
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000B: executing POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/start HTTP/1.1
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000A: start execution ex-0000000B
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000A: executing exchange ex-0000000B over http-outgoing-1
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/start HTTP/1.1
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> accept: application/json
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> content-type: application/json
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:24.203 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/start HTTP/1.1[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "accept: application/json[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "content-type: application/json[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:24.204 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:24.205 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:25.319 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 204 No Content[\r][\n]"
11:02:25.319 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:25.319 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:25 GMT[\r][\n]"
11:02:25.319 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:25.319 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 204 No Content
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:25 GMT
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:25.320 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:25.321 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000B: connection can be kept alive for 3 MINUTES
11:02:25.321 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000A: releasing valid endpoint
11:02:25.321 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000A: releasing endpoint
11:02:25.322 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000A: connection http-outgoing-1 can be kept alive for 3 MINUTES
11:02:25.322 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000A: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.322 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: 93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4,false
11:02:25.323 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.exec.InspectContainerCmdExec - GET: DefaultWebTarget{path=[/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/json], queryParams={}}
11:02:25.323 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000C: preparing request execution
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000C: target auth state: UNCHALLENGED
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000C: proxy auth state: UNCHALLENGED
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000C: acquiring connection with route {}->npipe://localhost:2375
11:02:25.324 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000C: acquiring endpoint (3 MINUTES)
11:02:25.325 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000C: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.325 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000C: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.325 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000C: acquired ep-0000000B
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000C: acquired endpoint ep-0000000B
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000C: executing GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/json HTTP/1.1
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000B: start execution ex-0000000C
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000B: executing exchange ex-0000000C over http-outgoing-1
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/json HTTP/1.1
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> accept: application/json
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:25.326 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/json HTTP/1.1[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "accept: application/json[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:25.327 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 200 OK[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Content-Type: application/json[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:25 GMT[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:25.344 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Transfer-Encoding: chunked[\r][\n]"
11:02:25.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 200 OK
11:02:25.345 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Content-Type: application/json
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:25 GMT
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Transfer-Encoding: chunked
11:02:25.346 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000C: connection can be kept alive for 3 MINUTES
11:02:25.347 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "14ca[\r][\n]"
11:02:25.349 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "{"Id":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","Created":"2026-04-12T08:02:23.998994446Z","Path":"docker-entrypoint.sh","Args":["--replSet","docker-rs"],"State":{"Status":"running","Running":true,"Paused":false,"Restarting":false,"OOMKilled":false,"Dead":false,"Pid":1630,"ExitCode":0,"Error":"","StartedAt":"2026-04-12T08:02:24.458652932Z","FinishedAt":"0001-01-01T00:00:00Z"},"Image":"sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe","ResolvConfPath":"/var/lib/docker/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/resolv.conf","HostnamePath":"/var/lib/docker/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/hostname","HostsPath":"/var/lib/docker/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/hosts","LogPath":"/var/lib/docker/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4-json.log","Name":"/elastic_goodall","RestartCount":0,"Driver":"overlayfs","Platform":"linux","MountLabel":"","ProcessLabel":"","AppArmorProfile":"","ExecIDs":null,"HostConfig":{"Binds":[],"ContainerIDFile":"","LogConfig":{"Type":"json-file","Config":{}},"NetworkMode":"bridge","PortBindings":{"27017/tcp":[{"HostIp":"","HostPort":""}]},"RestartPolicy":{"Name":"no","MaximumRetryCount":0},"AutoRemove":false,"VolumeDriver":"","VolumesFrom":[],"ConsoleSize":[0,0],"CapAdd":null,"CapDrop":null,"CgroupnsMode":"private","Dns":null,"DnsOptions":null,"DnsSearch":null,"ExtraHosts":[],"GroupAdd":null,"IpcMode":"shareable","Cgroup":"","Links":null,"OomScoreAdj":0,"PidMode":"","Privileged":false,"PublishAllPorts":false,"ReadonlyRootfs":false,"SecurityOpt":null,"UTSMode":"","UsernsMode":"","ShmSize":67108864,"Runtime":"runc","Isolation":"","CpuShares":0,"Memory":0,"NanoCpus":0,"CgroupParent":"","BlkioWeight":0,"BlkioWeightDevice":null,"BlkioDeviceReadBps":null,"BlkioDeviceWriteBps":null,"BlkioDeviceReadIOps":null,"BlkioDeviceWriteIOps":null,"CpuPeriod":0,"CpuQuota":0,"CpuRealtimePeriod":0,"CpuRealtimeRuntime":0,"CpusetCpus":"","CpusetMems":"","Devices":null,"DeviceCgroupRules":null,"DeviceRequests":null,"MemoryReservation":0,"MemorySwap":0,"MemorySwappiness":null,"OomKillDisable":null,"PidsLimit":null,"Ulimits":null,"CpuCount":0,"CpuPercent":0,"IOMaximumIOps":0,"IOMaximumBandwidth":0,"MaskedPaths":["/proc/asound","/proc/acpi","/proc/interrupts","/proc/kcore","/proc/keys","/proc/latency_stats","/proc/timer_list","/proc/timer_stats","/proc/sched_debug","/proc/scsi","/sys/firmware","/sys/devices/virtual/powercap"],"ReadonlyPaths":["/proc/bus","/proc/fs","/proc/irq","/proc/sys","/proc/sysrq-trigger"]},"GraphDriver":{"Data":null,"Name":"overlayfs"},"Mounts":[{"Type":"volume","Name":"867bd85cb951fc979948a7a64c2800d48d7728bb2e92a8d3adf3991730b9200d","Source":"/var/lib/docker/volumes/867bd85cb951fc979948a7a64c2800d48d7728bb2e92a8d3adf3991730b9200d/_data","Destination":"/data/configdb","Driver":"local","Mode":"","RW":true,"Propagation":""},{"Type":"volume","Name":"16fdc834272c389745d5b78a65018f1d1f32f234cfde0b9d619d8382b8d730c5","Source":"/var/lib/docker/volumes/16fdc834272c389745d5b78a65018f1d1f32f234cfde0b9d619d8382b8d730c5/_data","Destination":"/data/db","Driver":"local","Mode":"","RW":true,"Propagation":""}],"Config":{"Hostname":"93df093346c7","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"ExposedPorts":{"27017/tcp":{}},"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","GOSU_VERSION=1.19","JSYAML_VERSION=3.13.1","JSYAML_CHECKSUM=662e32319bdd378e91f67578e56a34954b0a2e33aca11d70ab9f4826af24b941","MONGO_PACKAGE=mongodb-org","MONGO_REPO=repo.mongodb.org","MONGO_MAJOR=7.0","MONGO_VERSION=7.0.31","HOME=/data/db"],"Cmd":["--replSet","docker-rs"],"Image":"mongo:7.0","Volumes":{"/data/configdb":{},"/data/db":{}},"WorkingDir":"","Entrypoint":["docker-entrypoint.sh"],"OnBuild":null,"Labels":{"org.opencontainers.image.version":"22.04","org.testcontainers":"true","org.testcontainers.lang":"java","org.testcontainers.sessionId":"44c8fccf-806a-49b6-800f-48b6a1a1d846","org.testcontainers.version":"1.20.6"}},"NetworkSettings":{"Bridge":"","SandboxID":"2ef7013a3e480e39278c8bdb103ab900605b0c8346bc8ab68b6f3bd1c549a92b","SandboxKey":"/var/run/docker/netns/2ef7013a3e48","Ports":{"27017/tcp":[{"HostIp":"0.0.0.0","HostPort":"49259"},{"HostIp":"::","HostPort":"49259"}]},"HairpinMode":false,"LinkLocalIPv6Address":"","LinkLocalIPv6PrefixLen":0,"SecondaryIPAddresses":null,"SecondaryIPv6Addresses":null,"EndpointID":"64157dd5ac882d686407ceebdb3b5da8409579ce8ef74cd1925ba64e2755c2a7","Gateway":"172.17.0.1","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"IPAddress":"172.17.0.3","IPPrefixLen":16,"IPv6Gateway":"","MacAddress":"06:e5:5e:f3:3b:bb","Networks":{"bridge":{"IPAMConfig":null,"Links":null,"Aliases":null,"MacAddress":"06:e5:5e:f3:3b:bb","DriverOpts":null,"GwPriority":0,"NetworkID":"0fc8823ed2c266ba3cf6f8e5a9740997040b3c754368741134586c0ce46d2d49","EndpointID":"64157dd5ac882d686407ceebdb3b5da8409579ce8ef74cd1925ba64e2755c2a7","Gateway":"172.17.0.1","IPAddress":"172.17.0.3","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"DNSNames":null}}}}[\n]"
11:02:25.349 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.349 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "0[\r][\n]"
11:02:25.349 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000B: releasing valid endpoint
11:02:25.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000B: releasing endpoint
11:02:25.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000B: connection http-outgoing-1 can be kept alive for 3 MINUTES
11:02:25.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000B: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.357 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe
11:02:25.358 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000D: preparing request execution
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000D: target auth state: UNCHALLENGED
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000D: proxy auth state: UNCHALLENGED
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000D: acquiring connection with route {}->npipe://localhost:2375
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000D: acquiring endpoint (3 MINUTES)
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000D: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.359 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000D: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000D: acquired ep-0000000C
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000D: acquired endpoint ep-0000000C
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000D: executing GET /v1.32/images/sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe/json HTTP/1.1
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000C: start execution ex-0000000D
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000C: executing exchange ex-0000000D over http-outgoing-1
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> GET /v1.32/images/sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe/json HTTP/1.1
11:02:25.360 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> accept: application/json
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "GET /v1.32/images/sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe/json HTTP/1.1[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "accept: application/json[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:25.361 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:25.673 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 200 OK[\r][\n]"
11:02:25.673 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:25.673 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Content-Type: application/json[\r][\n]"
11:02:25.673 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:25 GMT[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Transfer-Encoding: chunked[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 200 OK
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Content-Type: application/json
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:25 GMT
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:25.674 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:25.675 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:25.675 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Transfer-Encoding: chunked
11:02:25.675 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000D: connection can be kept alive for 3 MINUTES
11:02:25.675 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "735[\r][\n]"
11:02:25.676 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "{"Id":"sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe","RepoTags":["mongo:7.0"],"RepoDigests":["mongo@sha256:45d9c9b48aa1b56b5e3a9f906763fe432f376abb3bc2832438022b6d2534e4fe"],"Parent":"","Comment":"buildkit.dockerfile.v0","Created":"2026-04-01T20:15:25.126030391Z","DockerVersion":"","Author":"","Architecture":"amd64","Os":"linux","Size":294111141,"VirtualSize":294111141,"GraphDriver":{"Data":null,"Name":"overlayfs"},"RootFS":{"Type":"layers","Layers":["sha256:351d919b2c06105efcb263b77e23bff2f166d23bcb487f5185d884bfada142f3","sha256:79df9318c799bdcf814fa6c11cc03fd8c80cace04922141e829816e742b0e617","sha256:71f45f958f736c5e4b0795644af7e8b885b3b7b87da42c23b503b67450d39f17","sha256:d891e8f697edacff48acdad718447d9ed2addc939aec46e5eecc1bf1f0e97b7d","sha256:dd0e621284775a7f433f9c03dca039a7c4c4e4f8f7ab5da8fd17ad5fb959a787","sha256:e98d88b9085f21020bf1ccdb62669deb4cadd334c4691988a843476db2bbe39c","sha256:e55f30220b94d1fa13081f3748811e23f5c6d83e2a93d985602548dbb2409c3b","sha256:11ebad3fc459ce781eb12363b39ba5e558dd8a907e998a7b0a879651315a8bfc"]},"Metadata":{"LastTagTime":"2026-04-11T20:57:24.928735311Z"},"Config":{"Hostname":"","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"ExposedPorts":{"27017/tcp":{}},"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","GOSU_VERSION=1.19","JSYAML_VERSION=3.13.1","JSYAML_CHECKSUM=662e32319bdd378e91f67578e56a34954b0a2e33aca11d70ab9f4826af24b941","MONGO_PACKAGE=mongodb-org","MONGO_REPO=repo.mongodb.org","MONGO_MAJOR=7.0","MONGO_VERSION=7.0.31","HOME=/data/db"],"Cmd":["mongod"],"Image":"","Volumes":{"/data/configdb":{},"/data/db":{}},"WorkingDir":"","Entrypoint":["docker-entrypoint.sh"],"OnBuild":null,"Labels":{"org.opencontainers.image.version":"22.04"}}}[\n]"
11:02:25.677 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.677 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "0[\r][\n]"
11:02:25.677 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.678 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000C: releasing valid endpoint
11:02:25.678 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000C: releasing endpoint
11:02:25.679 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000C: connection http-outgoing-1 can be kept alive for 3 MINUTES
11:02:25.679 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000C: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.679 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd:
11:02:25.679 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000E: preparing request execution
11:02:25.679 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000E: target auth state: UNCHALLENGED
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000E: proxy auth state: UNCHALLENGED
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000E: acquiring connection with route {}->npipe://localhost:2375
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000E: acquiring endpoint (3 MINUTES)
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000E: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000E: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000E: acquired ep-0000000D
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000E: acquired endpoint ep-0000000D
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000E: executing GET /v1.32/version HTTP/1.1
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000D: start execution ex-0000000E
11:02:25.680 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000D: executing exchange ex-0000000E over http-outgoing-1
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> GET /v1.32/version HTTP/1.1
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> accept: application/json
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "GET /v1.32/version HTTP/1.1[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "accept: application/json[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:25.681 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:25.711 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 200 OK[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Content-Type: application/json[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:25 GMT[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Transfer-Encoding: chunked[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 200 OK
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Content-Type: application/json
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:25 GMT
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:25.712 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Transfer-Encoding: chunked
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000E: connection can be kept alive for 3 MINUTES
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "359[\r][\n]"
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "{"Platform":{"Name":"Docker Desktop 4.42.1 (196648)"},"Components":[{"Name":"Engine","Version":"28.2.2","Details":{"ApiVersion":"1.50","Arch":"amd64","BuildTime":"2025-05-30T12:07:26.000000000+00:00","Experimental":"false","GitCommit":"45873be","GoVersion":"go1.24.3","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","MinAPIVersion":"1.24","Os":"linux"}},{"Name":"containerd","Version":"1.7.27","Details":{"GitCommit":"05044ec0a9a75232cad458027ca83437aae3f4da"}},{"Name":"runc","Version":"1.2.5","Details":{"GitCommit":"v1.2.5-0-g59923ef"}},{"Name":"docker-init","Version":"0.19.0","Details":{"GitCommit":"de40ad0"}}],"Version":"28.2.2","ApiVersion":"1.50","MinAPIVersion":"1.24","GitCommit":"45873be","GoVersion":"go1.24.3","Os":"linux","Arch":"amd64","KernelVersion":"6.6.87.2-microsoft-standard-WSL2","BuildTime":"2025-05-30T12:07:26.000000000+00:00"}[\n]"
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "0[\r][\n]"
11:02:25.713 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.716 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000D: releasing valid endpoint
11:02:25.716 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000D: releasing endpoint
11:02:25.716 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000D: connection http-outgoing-1 can be kept alive for 3 MINUTES
11:02:25.716 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000D: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.719 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000F: preparing request execution
11:02:25.722 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:25.723 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:25.723 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000F: target auth state: UNCHALLENGED
11:02:25.723 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-0000000F: proxy auth state: UNCHALLENGED
11:02:25.723 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-0000000F: acquiring connection with route {}->npipe://localhost:2375
11:02:25.724 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000F: acquiring endpoint (3 MINUTES)
11:02:25.726 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000F: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.727 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000F: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-0000000F: acquired ep-0000000E
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-0000000F: acquired endpoint ep-0000000E
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000F: executing GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000E: start execution ex-0000000F
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000E: executing exchange ex-0000000F over http-outgoing-1
11:02:25.728 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> User-Agent: tc-java/1.20.6
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Host: localhost:2375
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 >> Connection: keep-alive
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "GET /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/logs?stdout=true&stderr=true&follow=true&since=0 HTTP/1.1[\r][\n]"
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:25.729 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Host: localhost:2375[\r][\n]"
11:02:25.730 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "Connection: keep-alive[\r][\n]"
11:02:25.730 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 >> "[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "HTTP/1.1 200 OK[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Api-Version: 1.50[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Content-Type: application/vnd.docker.raw-stream[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Date: Sun, 12 Apr 2026 08:02:25 GMT[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Docker-Experimental: false[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Ostype: linux[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "Transfer-Encoding: chunked[\r][\n]"
11:02:25.750 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << HTTP/1.1 200 OK
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Api-Version: 1.50
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Content-Type: application/vnd.docker.raw-stream
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Date: Sun, 12 Apr 2026 08:02:25 GMT
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Docker-Experimental: false
11:02:25.751 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Ostype: linux
11:02:25.752 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Server: Docker/28.2.2 (linux)
11:02:25.752 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-1 << Transfer-Encoding: chunked
11:02:25.752 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-0000000F: connection can be kept alive for 3 MINUTES
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "d4[\r][\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffcc]{"t":{"$date":"2026-04-12T08:02:25.518+00:00"},"s":"I",  "c":"CONTROL",  "id":23285,   "ctx":"main","msg":"Automatically disabling TLS 1.0, to force-enable TLS 1.0 specify --sslDisabledProtocols 'none'"}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "f9f[\r][\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]n{"t":{"$date":"2026-04-12T08:02:25.518+00:00"},"s":"I",  "c":"NETWORK",  "id":4915701, "ctx":"main","msg":"Initialized wire specification","attr":{"spec":{"incomingExternalClient":{"minWireVersion":0,"maxWireVersion":21},"incomingInternalClient":{"minWireVersion":0,"maxWireVersion":21},"outgoing":{"minWireVersion":6,"maxWireVersion":21},"isInternalClient":true}}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xfffffff1]{"t":{"$date":"2026-04-12T08:02:25.535+00:00"},"s":"I",  "c":"NETWORK",  "id":4648601, "ctx":"main","msg":"Implicit TCP FastOpen unavailable. If TCP FastOpen is required, set tcpFastOpenServer, tcpFastOpenClient, and tcpFastOpenQueueSize."}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xfffffff4]{"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"REPL",     "id":5123008, "ctx":"main","msg":"Successfully registered PrimaryOnlyService","attr":{"service":"TenantMigrationDonorService","namespace":"config.tenantMigrationDonors"}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xfffffffc]{"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"REPL",     "id":5123008, "ctx":"main","msg":"Successfully registered PrimaryOnlyService","attr":{"service":"TenantMigrationRecipientService","namespace":"config.tenantMigrationRecipients"}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffd9]{"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"W",  "c":"NETWORK",  "id":11621101,"ctx":"main","msg":"Overriding max connections to honor `capMemoryConsumptionForPreAuthBuffers` settings","attr":{"limit":75097}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff89]{"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"CONTROL",  "id":5945603, "ctx":"main","msg":"Multi threading initialized"}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff9b]{"t":{"$date":"2026-04-12T08:02:25.550+00:00"},"s":"I",  "c":"TENANT_M", "id":7091600, "ctx":"main","msg":"Starting TenantMigrationAccessBlockerRegistry"}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffe7]{"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":4615611, "ctx":"initandlisten","msg":"MongoDB starting","attr":{"pid":1,"port":27017,"dbPath":"/data/db","architecture":"64-bit","host":"93df093346c7"}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff86]{"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":23403,   "ctx":"initandlisten","msg":"Build Info","attr":{"buildInfo":{"version":"7.0.31","gitVersion":"6a3bdfa2794c261d3ce011c74f818e970e563609","openSSLVersion":"OpenSSL 3.0.2 15 Mar 2022","modules":[],"allocator":"tcmalloc","environment":{"distmod":"ubuntu2204","distarch":"x86_64","target_arch":"x86_64"}}}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffb9]{"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":51765,   "ctx":"initandlisten","msg":"Operating System","attr":{"os":{"name":"Ubuntu","version":"22.04"}}}[\n]"
11:02:25.753 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffe2]{"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":21951,   "ctx":"initandlisten","msg":"Options set by command line","attr":{"options":{"net":{"bindIp":"*"},"replication":{"replSet":"docker-rs"}}}}[\n]"
11:02:25.755 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffe2]{"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"W",  "c":"NETWORK",  "id":11621101,"ctx":"initandlisten","msg":"Overriding max connections to honor `capMemoryConsumptionForPreAuthBuffers` settings","attr":{"limit":75097}}[\n]"
11:02:25.755 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0x1e]{"t":{"$date":"2026-04-12T08:02:25.567+00:00"},"s":"I",  "c":"STORAGE",  "id":22297,   "ctx":"initandlisten","msg":"Using the XFS filesystem is strongly recommended with the WiredTiger storage engine. See http://dochub.mongodb.org/core/prodnotes-filesystem","tags":["startupWarnings"]}[\n]"
11:02:25.756 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x2][0xffffffdb]{"t":{"$date":"2026-04-12T08:02:25.568+00:00"},"s":"I",  "c":"STORAGE",  "id":22315,   "ctx":"initandlisten","msg":"Opening WiredTiger","attr":{"config":"create,cache_size=2421M,session_max=33000,eviction=(threads_min=4,threads_max=4),config_base=false,statistics=(fast),log=(enabled=true,remove=true,path=journal,compressor=snappy),builtin_extension_config=(zstd=(compression_level=6)),file_manager=(close_idle_time=600,close_scan_interval=10,close_handle_minimum=2000),statistics_log=(wait=0),json_output=(error,message),verbose=[recovery_progress:1,checkpoint_progress:1,compact_progress:1,backup:0,checkpoint:0,compact:0,evict:0,history_store:0,recovery:0,rts:0,salvage:0,tiered:0,timestamp:0,transaction:0,verify:0,log:0],"}}[\n]"
11:02:25.756 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:25.756 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.518+00:00"},"s":"I",  "c":"CONTROL",  "id":23285,   "ctx":"main","msg":"Automatically disabling TLS 1.0, to force-enable TLS 1.0 specify --sslDisabledProtocols 'none'"}
11:02:25.756 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.535+00:00"},"s":"I",  "c":"NETWORK",  "id":4648601, "ctx":"main","msg":"Implicit TCP FastOpen unavailable. If TCP FastOpen is required, set tcpFastOpenServer, tcpFastOpenClient, and tcpFastOpenQueueSize."}
11:02:25.756 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"REPL",     "id":5123008, "ctx":"main","msg":"Successfully registered PrimaryOnlyService","attr":{"service":"TenantMigrationDonorService","namespace":"config.tenantMigrationDonors"}}
11:02:25.757 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.550+00:00"},"s":"I",  "c":"TENANT_M", "id":7091600, "ctx":"main","msg":"Starting TenantMigrationAccessBlockerRegistry"}
11:02:25.757 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":4615611, "ctx":"initandlisten","msg":"MongoDB starting","attr":{"pid":1,"port":27017,"dbPath":"/data/db","architecture":"64-bit","host":"93df093346c7"}}
11:02:25.757 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"CONTROL",  "id":5945603, "ctx":"main","msg":"Multi threading initialized"}
11:02:25.757 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"W",  "c":"NETWORK",  "id":11621101,"ctx":"main","msg":"Overriding max connections to honor `capMemoryConsumptionForPreAuthBuffers` settings","attr":{"limit":75097}}
11:02:25.757 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.549+00:00"},"s":"I",  "c":"REPL",     "id":5123008, "ctx":"main","msg":"Successfully registered PrimaryOnlyService","attr":{"service":"TenantMigrationRecipientService","namespace":"config.tenantMigrationRecipients"}}
11:02:25.758 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.518+00:00"},"s":"I",  "c":"NETWORK",  "id":4915701, "ctx":"main","msg":"Initialized wire specification","attr":{"spec":{"incomingExternalClient":{"minWireVersion":0,"maxWireVersion":21},"incomingInternalClient":{"minWireVersion":0,"maxWireVersion":21},"outgoing":{"minWireVersion":6,"maxWireVersion":21},"isInternalClient":true}}}
11:02:25.769 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.568+00:00"},"s":"I",  "c":"STORAGE",  "id":22315,   "ctx":"initandlisten","msg":"Opening WiredTiger","attr":{"config":"create,cache_size=2421M,session_max=33000,eviction=(threads_min=4,threads_max=4),config_base=false,statistics=(fast),log=(enabled=true,remove=true,path=journal,compressor=snappy),builtin_extension_config=(zstd=(compression_level=6)),file_manager=(close_idle_time=600,close_scan_interval=10,close_handle_minimum=2000),statistics_log=(wait=0),json_output=(error,message),verbose=[recovery_progress:1,checkpoint_progress:1,compact_progress:1,backup:0,checkpoint:0,compact:0,evict:0,history_store:0,recovery:0,rts:0,salvage:0,tiered:0,timestamp:0,transaction:0,verify:0,log:0],"}}
11:02:25.769 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.567+00:00"},"s":"I",  "c":"STORAGE",  "id":22297,   "ctx":"initandlisten","msg":"Using the XFS filesystem is strongly recommended with the WiredTiger storage engine. See http://dochub.mongodb.org/core/prodnotes-filesystem","tags":["startupWarnings"]}
11:02:25.769 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"W",  "c":"NETWORK",  "id":11621101,"ctx":"initandlisten","msg":"Overriding max connections to honor `capMemoryConsumptionForPreAuthBuffers` settings","attr":{"limit":75097}}
11:02:25.769 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":21951,   "ctx":"initandlisten","msg":"Options set by command line","attr":{"options":{"net":{"bindIp":"*"},"replication":{"replSet":"docker-rs"}}}}
11:02:25.770 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":51765,   "ctx":"initandlisten","msg":"Operating System","attr":{"os":{"name":"Ubuntu","version":"22.04"}}}
11:02:25.770 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:25.551+00:00"},"s":"I",  "c":"CONTROL",  "id":23403,   "ctx":"initandlisten","msg":"Build Info","attr":{"buildInfo":{"version":"7.0.31","gitVersion":"6a3bdfa2794c261d3ce011c74f818e970e563609","openSSLVersion":"OpenSSL 3.0.2 15 Mar 2022","modules":[],"allocator":"tcmalloc","environment":{"distmod":"ubuntu2204","distarch":"x86_64","target_arch":"x86_64"}}}}
11:02:26.731 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "6ef[\r][\n]"
11:02:26.731 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffffb4]{"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725439,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"recovery log replay has successfully finished and ran for 0 milliseconds"}}}[\n]"
11:02:26.731 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff91]{"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725638,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"Set global recovery timestamp: (0, 0)"}}}[\n]"
11:02:26.732 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff8f]{"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725710,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"Set global oldest timestamp: (0, 0)"}}}[\n]"
11:02:26.732 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xfffffffb]{"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725860,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"recovery was completed successfully and took 1ms, including 0ms for the log replay, 0ms for the rollback to stable, and 0ms for the checkpoint."}}}[\n]"
11:02:26.732 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.733 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725439,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"recovery log replay has successfully finished and ran for 0 milliseconds"}}}
11:02:26.733 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725710,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"Set global oldest timestamp: (0, 0)"}}}
11:02:26.733 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725860,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"recovery was completed successfully and took 1ms, including 0ms for the log replay, 0ms for the rollback to stable, and 0ms for the checkpoint."}}}
11:02:26.734 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.725+00:00"},"s":"I",  "c":"WTRECOV",  "id":22430,   "ctx":"initandlisten","msg":"WiredTiger message","attr":{"message":{"ts_sec":1775980946,"ts_usec":725638,"thread":"1:0x72a9d9857c80","session_name":"txn-recover","category":"WT_VERB_RECOVERY_PROGRESS","category_id":30,"verbose_level":"DEBUG_1","verbose_level_id":1,"msg":"Set global recovery timestamp: (0, 0)"}}}
11:02:26.763 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "184[\r][\n]"
11:02:26.763 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffa7]{"t":{"$date":"2026-04-12T08:02:26.762+00:00"},"s":"I",  "c":"STORAGE",  "id":4795906, "ctx":"initandlisten","msg":"WiredTiger opened","attr":{"durationMillis":1194}}[\n]"
11:02:26.763 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffcd]{"t":{"$date":"2026-04-12T08:02:26.762+00:00"},"s":"I",  "c":"RECOVERY", "id":23987,   "ctx":"initandlisten","msg":"WiredTiger recoveryTimestamp","attr":{"recoveryTimestamp":{"$timestamp":{"t":0,"i":0}}}}[\n]"
11:02:26.763 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.763 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.762+00:00"},"s":"I",  "c":"STORAGE",  "id":4795906, "ctx":"initandlisten","msg":"WiredTiger opened","attr":{"durationMillis":1194}}
11:02:26.763 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.762+00:00"},"s":"I",  "c":"RECOVERY", "id":23987,   "ctx":"initandlisten","msg":"WiredTiger recoveryTimestamp","attr":{"recoveryTimestamp":{"$timestamp":{"t":0,"i":0}}}}
11:02:26.818 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "333[\r][\n]"
11:02:26.819 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0x1]{"t":{"$date":"2026-04-12T08:02:26.812+00:00"},"s":"W",  "c":"CONTROL",  "id":22120,   "ctx":"initandlisten","msg":"Access control is not enabled for the database. Read and write access to data and configuration is unrestricted","tags":["startupWarnings"]}[\n]"
11:02:26.819 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffe3]{"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"REPL",     "id":5853300, "ctx":"initandlisten","msg":"current featureCompatibilityVersion value","attr":{"featureCompatibilityVersion":"unset","context":"startup"}}[\n]"
11:02:26.819 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff8e]{"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"STORAGE",  "id":5071100, "ctx":"initandlisten","msg":"Clearing temp directory"}[\n]"
11:02:26.819 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffa1]{"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"CONTROL",  "id":20536,   "ctx":"initandlisten","msg":"Flow Control is enabled on this deployment"}[\n]"
11:02:26.819 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.820 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"CONTROL",  "id":20536,   "ctx":"initandlisten","msg":"Flow Control is enabled on this deployment"}
11:02:26.820 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"STORAGE",  "id":5071100, "ctx":"initandlisten","msg":"Clearing temp directory"}
11:02:26.820 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.813+00:00"},"s":"I",  "c":"REPL",     "id":5853300, "ctx":"initandlisten","msg":"current featureCompatibilityVersion value","attr":{"featureCompatibilityVersion":"unset","context":"startup"}}
11:02:26.822 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.812+00:00"},"s":"W",  "c":"CONTROL",  "id":22120,   "ctx":"initandlisten","msg":"Access control is not enabled for the database. Read and write access to data and configuration is unrestricted","tags":["startupWarnings"]}
11:02:26.828 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "e1[\r][\n]"
11:02:26.829 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffd9]{"t":{"$date":"2026-04-12T08:02:26.825+00:00"},"s":"I",  "c":"FTDC",     "id":20625,   "ctx":"initandlisten","msg":"Initializing full-time diagnostic data capture","attr":{"dataDirectory":"/data/db/diagnostic.data"}}[\n]"
11:02:26.829 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.833 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.825+00:00"},"s":"I",  "c":"FTDC",     "id":20625,   "ctx":"initandlisten","msg":"Initializing full-time diagnostic data capture","attr":{"dataDirectory":"/data/db/diagnostic.data"}}
11:02:26.840 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "28c[\r][\n]"
11:02:26.841 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff9b]{"t":{"$date":"2026-04-12T08:02:26.836+00:00"},"s":"I",  "c":"REPL",     "id":40440,   "ctx":"initandlisten","msg":"Starting the TopologyVersionObserver"}[\n]"
11:02:26.841 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffa0]{"t":{"$date":"2026-04-12T08:02:26.837+00:00"},"s":"I",  "c":"REPL",     "id":40445,   "ctx":"TopologyVersionObserver","msg":"Started TopologyVersionObserver"}[\n]"
11:02:26.841 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]9{"t":{"$date":"2026-04-12T08:02:26.838+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.startup_log","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"0be49dd3-2773-4f5c-beeb-9f24df47b5f2"}},"options":{"capped":true,"size":10485760}}}[\n]"
11:02:26.841 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.853 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.838+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.startup_log","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"0be49dd3-2773-4f5c-beeb-9f24df47b5f2"}},"options":{"capped":true,"size":10485760}}}
11:02:26.853 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.837+00:00"},"s":"I",  "c":"REPL",     "id":40445,   "ctx":"TopologyVersionObserver","msg":"Started TopologyVersionObserver"}
11:02:26.853 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.836+00:00"},"s":"I",  "c":"REPL",     "id":40440,   "ctx":"initandlisten","msg":"Starting the TopologyVersionObserver"}
11:02:26.874 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "1a1[\r][\n]"
11:02:26.874 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff99]{"t":{"$date":"2026-04-12T08:02:26.872+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"0be49dd3-2773-4f5c-beeb-9f24df47b5f2"}},"namespace":"local.startup_log","index":"_id_","ident":"index-1--6578067038509229878","collectionIdent":"collection-0--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:26.874 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.876 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.872+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"0be49dd3-2773-4f5c-beeb-9f24df47b5f2"}},"namespace":"local.startup_log","index":"_id_","ident":"index-1--6578067038509229878","collectionIdent":"collection-0--6578067038509229878","commitTimestamp":null}}
11:02:26.877 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "20c[\r][\n]"
11:02:26.878 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]#{"t":{"$date":"2026-04-12T08:02:26.874+00:00"},"s":"I",  "c":"-",        "id":4939300, "ctx":"monitoring-keys-for-HMAC","msg":"Failed to refresh key cache","attr":{"error":"ReadConcernMajorityNotAvailableYet: Read concern majority reads are currently not possible.","nextWakeupMillis":200}}[\n]"
11:02:26.878 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffd9]{"t":{"$date":"2026-04-12T08:02:26.876+00:00"},"s":"I",  "c":"REPL",     "id":6015317, "ctx":"initandlisten","msg":"Setting new configuration state","attr":{"newState":"ConfigStartingUp","oldState":"ConfigPreStart"}}[\n]"
11:02:26.878 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.879 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "a5[\r][\n]"
11:02:26.880 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff9d]{"t":{"$date":"2026-04-12T08:02:26.878+00:00"},"s":"I",  "c":"REPL",     "id":6005300, "ctx":"initandlisten","msg":"Starting up replica set aware services"}[\n]"
11:02:26.880 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.882 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "1ec[\r][\n]"
11:02:26.883 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffac]{"t":{"$date":"2026-04-12T08:02:26.882+00:00"},"s":"I",  "c":"REPL",     "id":4280500, "ctx":"initandlisten","msg":"Attempting to create internal replication collections"}[\n]"
11:02:26.883 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]0{"t":{"$date":"2026-04-12T08:02:26.882+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.oplogTruncateAfterPoint","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"options":{}}}[\n]"
11:02:26.883 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.892 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.882+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.oplogTruncateAfterPoint","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"options":{}}}
11:02:26.892 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.882+00:00"},"s":"I",  "c":"REPL",     "id":4280500, "ctx":"initandlisten","msg":"Attempting to create internal replication collections"}
11:02:26.892 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.878+00:00"},"s":"I",  "c":"REPL",     "id":6005300, "ctx":"initandlisten","msg":"Starting up replica set aware services"}
11:02:26.892 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.876+00:00"},"s":"I",  "c":"REPL",     "id":6015317, "ctx":"initandlisten","msg":"Setting new configuration state","attr":{"newState":"ConfigStartingUp","oldState":"ConfigPreStart"}}
11:02:26.892 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.874+00:00"},"s":"I",  "c":"-",        "id":4939300, "ctx":"monitoring-keys-for-HMAC","msg":"Failed to refresh key cache","attr":{"error":"ReadConcernMajorityNotAvailableYet: Read concern majority reads are currently not possible.","nextWakeupMillis":200}}
11:02:26.909 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "451[\r][\n]"
11:02:26.909 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]k{"t":{"$date":"2026-04-12T08:02:26.907+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"object":{"create":"replset.oplogTruncateAfterPoint","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}[\n]"
11:02:26.910 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffffad]{"t":{"$date":"2026-04-12T08:02:26.908+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"namespace":"local.replset.oplogTruncateAfterPoint","index":"_id_","ident":"index-3--6578067038509229878","collectionIdent":"collection-2--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:26.910 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]!{"t":{"$date":"2026-04-12T08:02:26.908+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.minvalid","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"options":{}}}[\n]"
11:02:26.910 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.912 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.908+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.minvalid","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"options":{}}}
11:02:26.913 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.908+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"namespace":"local.replset.oplogTruncateAfterPoint","index":"_id_","ident":"index-3--6578067038509229878","collectionIdent":"collection-2--6578067038509229878","commitTimestamp":null}}
11:02:26.913 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.907+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"32cf9a7c-6df9-460c-a11f-b064334275c0"}},"object":{"create":"replset.oplogTruncateAfterPoint","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}
11:02:26.959 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "30a[\r][\n]"
11:02:26.960 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]\{"t":{"$date":"2026-04-12T08:02:26.955+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"object":{"create":"replset.minvalid","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}[\n]"
11:02:26.960 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff9e]{"t":{"$date":"2026-04-12T08:02:26.955+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"namespace":"local.replset.minvalid","index":"_id_","ident":"index-5--6578067038509229878","collectionIdent":"collection-4--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:26.960 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.961 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.955+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"namespace":"local.replset.minvalid","index":"_id_","ident":"index-5--6578067038509229878","collectionIdent":"collection-4--6578067038509229878","commitTimestamp":null}}
11:02:26.963 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.955+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"777d1a8a-edfb-4d02-9930-7d1bea3667f9"}},"object":{"create":"replset.minvalid","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}
11:02:26.964 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "129[\r][\n]"
11:02:26.965 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]!{"t":{"$date":"2026-04-12T08:02:26.956+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.election","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"options":{}}}[\n]"
11:02:26.965 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:26.982 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:26.956+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.replset.election","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"options":{}}}
11:02:27.006 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "98[\r][\n]"
11:02:27.006 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff90]{"t":{"$date":"2026-04-12T08:02:27.002+00:00"},"s":"W",  "c":"REPL",     "id":21533,   "ctx":"ftdc","msg":"Rollback ID is not initialized yet"}[\n]"
11:02:27.006 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.007 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.002+00:00"},"s":"W",  "c":"REPL",     "id":21533,   "ctx":"ftdc","msg":"Rollback ID is not initialized yet"}
11:02:27.009 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "1e2[\r][\n]"
11:02:27.009 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffffda]{"t":{"$date":"2026-04-12T08:02:27.005+00:00"},"s":"W",  "c":"QUERY",    "id":23799,   "ctx":"ftdc","msg":"Aggregate command executor error","attr":{"error":{"code":26,"codeName":"NamespaceNotFound","errmsg":"Unable to retrieve storageStats in $collStats stage :: caused by :: Collection [local.oplog.rs] not found."},"stats":{},"cmd":{"aggregate":"oplog.rs","cursor":{},"pipeline":[{"$collStats":{"storageStats":{"waitForLock":false,"numericOnly":true}}}],"$db":"local"}}}[\n]"
11:02:27.009 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.023 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.005+00:00"},"s":"W",  "c":"QUERY",    "id":23799,   "ctx":"ftdc","msg":"Aggregate command executor error","attr":{"error":{"code":26,"codeName":"NamespaceNotFound","errmsg":"Unable to retrieve storageStats in $collStats stage :: caused by :: Collection [local.oplog.rs] not found."},"stats":{},"cmd":{"aggregate":"oplog.rs","cursor":{},"pipeline":[{"$collStats":{"storageStats":{"waitForLock":false,"numericOnly":true}}}],"$db":"local"}}}
11:02:27.026 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "700[\r][\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]\{"t":{"$date":"2026-04-12T08:02:27.020+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"object":{"create":"replset.election","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}[\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff9e]{"t":{"$date":"2026-04-12T08:02:27.020+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"namespace":"local.replset.election","index":"_id_","ident":"index-7--6578067038509229878","collectionIdent":"collection-6--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffa2]{"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":4280501, "ctx":"initandlisten","msg":"Attempting to load local voted for document"}[\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffb3]{"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":21311,   "ctx":"initandlisten","msg":"Did not find local initialized voted for document at startup"}[\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff9f]{"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":4280502, "ctx":"initandlisten","msg":"Searching for local Rollback ID document"}[\n]"
11:02:27.027 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffb7]{"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":21312,   "ctx":"initandlisten","msg":"Did not find local Rollback ID document at startup. Creating one"}[\n]"
11:02:27.028 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]#{"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.system.rollback.id","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"options":{}}}[\n]"
11:02:27.028 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.041 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.system.rollback.id","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"options":{}}}
11:02:27.041 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":21312,   "ctx":"initandlisten","msg":"Did not find local Rollback ID document at startup. Creating one"}
11:02:27.042 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":4280502, "ctx":"initandlisten","msg":"Searching for local Rollback ID document"}
11:02:27.042 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":21311,   "ctx":"initandlisten","msg":"Did not find local initialized voted for document at startup"}
11:02:27.043 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.021+00:00"},"s":"I",  "c":"REPL",     "id":4280501, "ctx":"initandlisten","msg":"Attempting to load local voted for document"}
11:02:27.043 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.020+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"namespace":"local.replset.election","index":"_id_","ident":"index-7--6578067038509229878","collectionIdent":"collection-6--6578067038509229878","commitTimestamp":null}}
11:02:27.043 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.020+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"114ac1a3-cabc-4e6b-8c3e-17c2e3f7164d"}},"object":{"create":"replset.election","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}
11:02:27.080 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "714[\r][\n]"
11:02:27.081 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]^{"t":{"$date":"2026-04-12T08:02:27.073+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"object":{"create":"system.rollback.id","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}[\n]"
11:02:27.081 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffffa0]{"t":{"$date":"2026-04-12T08:02:27.074+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"namespace":"local.system.rollback.id","index":"_id_","ident":"index-9--6578067038509229878","collectionIdent":"collection-8--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:27.081 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffa4]{"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":21531,   "ctx":"initandlisten","msg":"Initialized the rollback ID","attr":{"rbid":1}}[\n]"
11:02:27.082 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]G{"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":21313,   "ctx":"initandlisten","msg":"Did not find local replica set configuration document at startup","attr":{"error":{"code":47,"codeName":"NoMatchingDocument","errmsg":"Did not find replica set configuration document in local.system.replset"}}}[\n]"
11:02:27.082 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffde]{"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":6015317, "ctx":"initandlisten","msg":"Setting new configuration state","attr":{"newState":"ConfigUninitialized","oldState":"ConfigStartingUp"}}[\n]"
11:02:27.082 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0x1d]{"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.system.views","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"options":{}}}[\n]"
11:02:27.082 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.082 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.073+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"object":{"create":"system.rollback.id","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}
11:02:27.082 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":21313,   "ctx":"initandlisten","msg":"Did not find local replica set configuration document at startup","attr":{"error":{"code":47,"codeName":"NoMatchingDocument","errmsg":"Did not find replica set configuration document in local.system.replset"}}}
11:02:27.082 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"initandlisten","msg":"createCollection","attr":{"namespace":"local.system.views","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"options":{}}}
11:02:27.082 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":6015317, "ctx":"initandlisten","msg":"Setting new configuration state","attr":{"newState":"ConfigUninitialized","oldState":"ConfigStartingUp"}}
11:02:27.083 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.075+00:00"},"s":"I",  "c":"REPL",     "id":21531,   "ctx":"initandlisten","msg":"Initialized the rollback ID","attr":{"rbid":1}}
11:02:27.083 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.074+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"11ea28bb-c00c-4016-8530-28775569f544"}},"namespace":"local.system.rollback.id","index":"_id_","ident":"index-9--6578067038509229878","collectionIdent":"collection-8--6578067038509229878","commitTimestamp":null}}
11:02:27.109 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "304[\r][\n]"
11:02:27.110 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]X{"t":{"$date":"2026-04-12T08:02:27.107+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"object":{"create":"system.views","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}[\n]"
11:02:27.110 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0xffffff9c]{"t":{"$date":"2026-04-12T08:02:27.107+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"namespace":"local.system.views","index":"_id_","ident":"index-11--6578067038509229878","collectionIdent":"collection-10--6578067038509229878","commitTimestamp":null}}[\n]"
11:02:27.110 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.111 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.107+00:00"},"s":"I",  "c":"REPL",     "id":7360102, "ctx":"initandlisten","msg":"Added oplog entry for create to transaction","attr":{"namespace":"local.$cmd","uuid":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"object":{"create":"system.views","idIndex":{"v":2,"key":{"_id":1},"name":"_id_"}}}}
11:02:27.111 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "1c4[\r][\n]"
11:02:27.111 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.107+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"initandlisten","msg":"Index build: done building","attr":{"buildUUID":null,"collectionUUID":{"uuid":{"$uuid":"a3b46edc-40e7-4819-a2f5-1c226d04e8e3"}},"namespace":"local.system.views","index":"_id_","ident":"index-11--6578067038509229878","collectionIdent":"collection-10--6578067038509229878","commitTimestamp":null}}
11:02:27.111 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff91]{"t":{"$date":"2026-04-12T08:02:27.108+00:00"},"s":"I",  "c":"STORAGE",  "id":22262,   "ctx":"initandlisten","msg":"Timestamp monitor starting"}[\n]"
11:02:27.111 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1]#{"t":{"$date":"2026-04-12T08:02:27.108+00:00"},"s":"I",  "c":"-",        "id":4939300, "ctx":"monitoring-keys-for-HMAC","msg":"Failed to refresh key cache","attr":{"error":"ReadConcernMajorityNotAvailableYet: Read concern majority reads are currently not possible.","nextWakeupMillis":400}}[\n]"
11:02:27.111 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.115 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "cd[\r][\n]"
11:02:27.116 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffc5]{"t":{"$date":"2026-04-12T08:02:27.113+00:00"},"s":"I",  "c":"QUERY",    "id":7080100, "ctx":"ChangeStreamExpiredPreImagesRemover","msg":"Starting Change Stream Expired Pre-images Remover thread"}[\n]"
11:02:27.116 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "126[\r][\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0x1e]{"t":{"$date":"2026-04-12T08:02:27.114+00:00"},"s":"I",  "c":"CONTROL",  "id":20710,   "ctx":"LogicalSessionCacheRefresh","msg":"Failed to refresh session cache, will try again at the next refresh interval","attr":{"error":"NotYetInitialized: Replication has not yet been configured"}}[\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "121[\r][\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x1][0x19]{"t":{"$date":"2026-04-12T08:02:27.116+00:00"},"s":"I",  "c":"CONTROL",  "id":20712,   "ctx":"LogicalSessionCacheReap","msg":"Sessions collection is not set up; waiting until next sessions reap interval","attr":{"error":"NamespaceNotFound: config.system.sessions does not exist"}}[\n]"
11:02:27.117 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.123 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.116+00:00"},"s":"I",  "c":"CONTROL",  "id":20712,   "ctx":"LogicalSessionCacheReap","msg":"Sessions collection is not set up; waiting until next sessions reap interval","attr":{"error":"NamespaceNotFound: config.system.sessions does not exist"}}
11:02:27.123 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.114+00:00"},"s":"I",  "c":"CONTROL",  "id":20710,   "ctx":"LogicalSessionCacheRefresh","msg":"Failed to refresh session cache, will try again at the next refresh interval","attr":{"error":"NotYetInitialized: Replication has not yet been configured"}}
11:02:27.123 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.113+00:00"},"s":"I",  "c":"QUERY",    "id":7080100, "ctx":"ChangeStreamExpiredPreImagesRemover","msg":"Starting Change Stream Expired Pre-images Remover thread"}
11:02:27.123 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.108+00:00"},"s":"I",  "c":"-",        "id":4939300, "ctx":"monitoring-keys-for-HMAC","msg":"Failed to refresh key cache","attr":{"error":"ReadConcernMajorityNotAvailableYet: Read concern majority reads are currently not possible.","nextWakeupMillis":400}}
11:02:27.123 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.108+00:00"},"s":"I",  "c":"STORAGE",  "id":22262,   "ctx":"initandlisten","msg":"Timestamp monitor starting"}
11:02:27.124 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "6c9[\r][\n]"
11:02:27.125 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffab]{"t":{"$date":"2026-04-12T08:02:27.121+00:00"},"s":"I",  "c":"NETWORK",  "id":23015,   "ctx":"listener","msg":"Listening on","attr":{"address":"/tmp/mongodb-27017.sock"}}[\n]"
11:02:27.125 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffff9b]{"t":{"$date":"2026-04-12T08:02:27.122+00:00"},"s":"I",  "c":"NETWORK",  "id":23015,   "ctx":"listener","msg":"Listening on","attr":{"address":"0.0.0.0"}}[\n]"
11:02:27.125 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0][0xffffffab]{"t":{"$date":"2026-04-12T08:02:27.122+00:00"},"s":"I",  "c":"NETWORK",  "id":23016,   "ctx":"listener","msg":"Waiting for connections","attr":{"port":27017,"ssl":"off"}}[\n]"
11:02:27.125 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x4][0xffffffb8]{"t":{"$date":"2026-04-12T08:02:27.122+00:00"},"s":"I",  "c":"CONTROL",  "id":8423403, "ctx":"initandlisten","msg":"mongod startup complete","attr":{"Summary of time elapsed":{"Startup from clean shutdown?":true,"Statistics":{"Transport layer setup":"0 ms","Run initial syncer crash recovery":"0 ms","Create storage engine lock file in the data directory":"0 ms","Get metadata describing storage engine":"0 ms","Create storage engine":"1226 ms","Write current PID to file":"0 ms","Write a new metadata for storage engine":"10 ms","Initialize FCV before rebuilding indexes":"0 ms","Drop abandoned idents and get back indexes that need to be rebuilt or builds that need to be restarted":"0 ms","Rebuild indexes for collections":"0 ms","Build user and roles graph":"0 ms","Set up the background thread pool responsible for waiting for opTimes to be majority committed":"1 ms","Initialize information needed to make a mongod instance shard aware":"0 ms","Start up cluster time keys manager with a local/direct keys client":"0 ms","Start up the replication coordinator":"201 ms","Create an oplog view for tenant migrations":"32 ms","Start transport layer":"1 ms","_initAndListen total elapsed time":"1571 ms"}}}}[\n]"
11:02:27.125 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[\r][\n]"
11:02:27.141 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.122+00:00"},"s":"I",  "c":"CONTROL",  "id":8423403, "ctx":"initandlisten","msg":"mongod startup complete","attr":{"Summary of time elapsed":{"Startup from clean shutdown?":true,"Statistics":{"Transport layer setup":"0 ms","Run initial syncer crash recovery":"0 ms","Create storage engine lock file in the data directory":"0 ms","Get metadata describing storage engine":"0 ms","Create storage engine":"1226 ms","Write current PID to file":"0 ms","Write a new metadata for storage engine":"10 ms","Initialize FCV before rebuilding indexes":"0 ms","Drop abandoned idents and get back indexes that need to be rebuilt or builds that need to be restarted":"0 ms","Rebuild indexes for collections":"0 ms","Build user and roles graph":"0 ms","Set up the background thread pool responsible for waiting for opTimes to be majority committed":"1 ms","Initialize information needed to make a mongod instance shard aware":"0 ms","Start up cluster time keys manager with a local/direct keys client":"0 ms","Start up the replication coordinator":"201 ms","Create an oplog view for tenant migrations":"32 ms","Start transport layer":"1 ms","_initAndListen total elapsed time":"1571 ms"}}}}
11:02:27.141 [main] DEBUG org.testcontainers.containers.output.WaitingConsumer - STDOUT: {"t":{"$date":"2026-04-12T08:02:27.122+00:00"},"s":"I",  "c":"NETWORK",  "id":23016,   "ctx":"listener","msg":"Waiting for connections","attr":{"port":27017,"ssl":"off"}}
11:02:27.141 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000E: cancel
11:02:27.141 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultManagedHttpClientConnection - http-outgoing-1: close connection IMMEDIATE
11:02:27.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000E: endpoint closed
11:02:27.142 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "end of stream"
11:02:27.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000E: discarding endpoint
11:02:27.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000E: releasing endpoint
11:02:27.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000E: connection is not kept alive
11:02:27.142 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000E: connection released [route: {}->npipe://localhost:2375][total available: 0; route allocated: 0 of 2147483647; total allocated: 0 of 2147483647]
11:02:27.143 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[read] I/O error: java.nio.channels.ClosedChannelException"
11:02:27.143 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-1 << "[read] I/O error: java.nio.channels.ClosedChannelException"
11:02:27.144 [docker-java-stream--804717290] DEBUG com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse - Failed to close the response
java.io.IOException: java.nio.channels.ClosedChannelException
        at java.base/java.nio.channels.Channels$1.read(Channels.java:166)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.LoggingInputStream.read(LoggingInputStream.java:81)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.fillBuffer(SessionInputBufferImpl.java:149)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.readLine(SessionInputBufferImpl.java:280)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.getChunkSize(ChunkedInputStream.java:261)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.nextChunk(ChunkedInputStream.java:222)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.read(ChunkedInputStream.java:147)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.close(ChunkedInputStream.java:314)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.IncomingHttpEntity.close(IncomingHttpEntity.java:111)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.HttpEntityWrapper.close(HttpEntityWrapper.java:120)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.message.BasicClassicHttpResponse.close(BasicClassicHttpResponse.java:93)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse.close(CloseableHttpResponse.java:200)
        at com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse.close(ApacheDockerHttpClientImpl.java:271)
        at org.testcontainers.shaded.com.github.dockerjava.core.DefaultInvocationBuilder.lambda$executeAndStream$1(DefaultInvocationBuilder.java:277)
        at java.base/java.lang.Thread.run(Thread.java:1575)
Caused by: java.nio.channels.ClosedChannelException: null
        at java.base/sun.nio.ch.WindowsAsynchronousFileChannelImpl.implRead(WindowsAsynchronousFileChannelImpl.java:537)
        at java.base/sun.nio.ch.AsynchronousFileChannelImpl.read(AsynchronousFileChannelImpl.java:229)
        at com.github.dockerjava.transport.NamedPipeSocket$AsynchronousFileByteChannel.read(NamedPipeSocket.java:117)
        at java.base/java.nio.channels.Channels$1.read(Channels.java:164)
        ... 16 common frames omitted
11:02:27.144 [main] DEBUG com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse - Failed to close the response
java.io.IOException: java.nio.channels.ClosedChannelException
        at java.base/java.nio.channels.Channels$1.read(Channels.java:166)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.LoggingInputStream.read(LoggingInputStream.java:81)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.fillBuffer(SessionInputBufferImpl.java:149)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.SessionInputBufferImpl.readLine(SessionInputBufferImpl.java:280)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.getChunkSize(ChunkedInputStream.java:261)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.nextChunk(ChunkedInputStream.java:222)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.read(ChunkedInputStream.java:147)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.ChunkedInputStream.close(ChunkedInputStream.java:314)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.impl.io.IncomingHttpEntity.close(IncomingHttpEntity.java:111)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.HttpEntityWrapper.close(HttpEntityWrapper.java:120)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.io.Closer.close(Closer.java:48)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.message.BasicClassicHttpResponse.close(BasicClassicHttpResponse.java:93)
        at com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse.close(CloseableHttpResponse.java:200)
        at com.github.dockerjava.zerodep.ApacheDockerHttpClientImpl$ApacheResponse.close(ApacheDockerHttpClientImpl.java:271)
        at org.testcontainers.shaded.com.github.dockerjava.core.DefaultInvocationBuilder.lambda$null$0(DefaultInvocationBuilder.java:272)
        at com.github.dockerjava.api.async.ResultCallbackTemplate.close(ResultCallbackTemplate.java:77)
        at org.testcontainers.containers.output.FrameConsumerResultCallback.close(FrameConsumerResultCallback.java:69)
        at org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy.waitUntilReady(LogMessageWaitStrategy.java:49)
        at org.testcontainers.containers.wait.strategy.AbstractWaitStrategy.waitUntilReady(AbstractWaitStrategy.java:52)
        at org.testcontainers.containers.GenericContainer.waitUntilContainerStarted(GenericContainer.java:909)
        at org.testcontainers.containers.GenericContainer.tryStart(GenericContainer.java:492)
        at org.testcontainers.containers.GenericContainer.lambda$doStart$0(GenericContainer.java:346)
        at org.rnorth.ducttape.unreliables.Unreliables.retryUntilSuccess(Unreliables.java:81)
        at org.testcontainers.containers.GenericContainer.doStart(GenericContainer.java:336)
        at org.testcontainers.containers.GenericContainer.start(GenericContainer.java:322)
        at org.testcontainers.junit.jupiter.TestcontainersExtension$StoreAdapter.start(TestcontainersExtension.java:276)
        at org.testcontainers.junit.jupiter.TestcontainersExtension$StoreAdapter.access$200(TestcontainersExtension.java:263)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.lambda$startContainers$4(TestcontainersExtension.java:83)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore.lambda$getOrComputeIfAbsent$4(ExtensionValuesStore.java:86)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore$MemoizingSupplier.computeValue(ExtensionValuesStore.java:223)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore$MemoizingSupplier.get(ExtensionValuesStore.java:211)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore$StoredValue.evaluate(ExtensionValuesStore.java:191)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore$StoredValue.access$100(ExtensionValuesStore.java:171)
        at org.junit.jupiter.engine.execution.ExtensionValuesStore.getOrComputeIfAbsent(ExtensionValuesStore.java:89)
        at org.junit.jupiter.engine.execution.NamespaceAwareStore.getOrComputeIfAbsent(NamespaceAwareStore.java:53)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.lambda$startContainers$5(TestcontainersExtension.java:83)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.startContainers(TestcontainersExtension.java:83)
        at org.testcontainers.junit.jupiter.TestcontainersExtension.beforeAll(TestcontainersExtension.java:57)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeBeforeAllCallbacks$8(ClassBasedTestDescriptor.java:368)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeBeforeAllCallbacks(ClassBasedTestDescriptor.java:368)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:192)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:78)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:136)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:108)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:88)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:184)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:148)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:122)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.nio.channels.ClosedChannelException: null
        at java.base/sun.nio.ch.WindowsAsynchronousFileChannelImpl.implRead(WindowsAsynchronousFileChannelImpl.java:537)
        at java.base/sun.nio.ch.AsynchronousFileChannelImpl.read(AsynchronousFileChannelImpl.java:229)
        at com.github.dockerjava.transport.NamedPipeSocket$AsynchronousFileByteChannel.read(NamedPipeSocket.java:117)
        at java.base/java.nio.channels.Channels$1.read(Channels.java:164)
        ... 80 common frames omitted
11:02:27.145 [main] INFO tc.mongo:7.0 - Container mongo:7.0 started in PT3.3178824S
11:02:27.146 [main] DEBUG org.testcontainers.containers.MongoDBContainer - Initializing a single node node replica set...
11:02:27.154 [main] DEBUG org.testcontainers.containers.ExecInContainerPattern - /elastic_goodall: Running "exec" command: sh -c mongosh mongo --eval "rs.initiate();"  || mongo --eval "rs.initiate();"
11:02:27.157 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: true,<null>,true,{sh,-c,mongosh mongo --eval "rs.initiate();"  || mongo --eval "rs.initiate();"},93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4,<null>,<null>,<null>,<null>,<null>
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000010: preparing request execution
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000010: target auth state: UNCHALLENGED
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000010: proxy auth state: UNCHALLENGED
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000010: acquiring connection with route {}->npipe://localhost:2375
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000010: acquiring endpoint (3 MINUTES)
11:02:27.167 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000010: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 0; route allocated: 0 of 2147483647; total allocated: 0 of 2147483647]
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000010: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000010: acquired ep-0000000F
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000010: acquired endpoint ep-0000000F
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000010: opening connection {}->npipe://localhost:2375
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000F: connecting endpoint (3 MINUTES)
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: connecting endpoint to npipe://localhost:2375 (3 MINUTES)
11:02:27.168 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-2: connecting to localhost/127.0.0.1:2375
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator - http-outgoing-2: connection established
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: connected http-outgoing-2
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000F: endpoint connected
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000010: executing POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000F: start execution ex-00000010
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: executing exchange ex-00000010 over http-outgoing-2
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> content-type: application/json
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Content-Length: 307
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "content-type: application/json[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Content-Length: 307[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:27.169 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "{"containerId":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","AttachStdin":null,"AttachStdout":true,"AttachStderr":true,"Tty":null,"Privileged":null,"User":null,"Cmd":["sh","-c","mongosh mongo --eval \"rs.initiate();\"  || mongo --eval \"rs.initiate();\""],"Env":null,"WorkingDir":null}"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 201 Created[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/json[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:27 GMT[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:27.182 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 201 Created
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/json
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:27 GMT
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:27.183 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000010: connection can be kept alive for 3 MINUTES
11:02:27.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "4a[\r][\n]"
11:02:27.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "{"Id":"c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152"}[\n]"
11:02:27.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:27.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:27.184 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:27.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-0000000F: releasing valid endpoint
11:02:27.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: releasing endpoint
11:02:27.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:27.188 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-0000000F: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:27.195 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000011: preparing request execution
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000011: target auth state: UNCHALLENGED
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000011: proxy auth state: UNCHALLENGED
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000011: acquiring connection with route {}->npipe://localhost:2375
11:02:27.196 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000011: acquiring endpoint (3 MINUTES)
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000011: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000011: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000011: acquired ep-00000010
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000011: acquired endpoint ep-00000010
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000011: executing POST /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/start HTTP/1.1
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000010: start execution ex-00000011
11:02:27.197 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000010: executing exchange ex-00000011 over http-outgoing-2
11:02:27.198 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> POST /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/start HTTP/1.1
11:02:27.198 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:27.198 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> content-type: application/json
11:02:27.198 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:27.199 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Content-Length: 26
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "POST /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/start HTTP/1.1[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "content-type: application/json[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Content-Length: 26[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:27.200 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "{"Detach":null,"Tty":null}"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 200 OK[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/vnd.docker.raw-stream[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:27 GMT[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 200 OK
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/vnd.docker.raw-stream
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:27 GMT
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:27.217 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:27.219 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000011: connection can be kept alive for 3 MINUTES
11:02:29.299 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "82[\r][\n]"
11:02:29.299 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[0x1][0x0][0x0][0x0][0x0][0x0][0x0]z{[\n]"
11:02:29.299 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "  info2: 'no configuration specified. Using a default configuration for the set',[\n]"
11:02:29.300 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "  me: '93df093346c7:27017',[\n]"
11:02:29.300 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "  ok: 1[\n]"
11:02:29.300 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "}[\n]"
11:02:29.300 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.347 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:29.347 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.348 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000010: releasing valid endpoint
11:02:29.348 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000010: releasing endpoint
11:02:29.348 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000010: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:29.348 [docker-java-stream-2090596466] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000010: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.352 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152
11:02:29.352 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.exec.InspectExecCmdExec - GET: DefaultWebTarget{path=[/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/json], queryParams={}}
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000012: preparing request execution
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000012: target auth state: UNCHALLENGED
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000012: proxy auth state: UNCHALLENGED
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000012: acquiring connection with route {}->npipe://localhost:2375
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000012: acquiring endpoint (3 MINUTES)
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000012: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.356 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000012: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000012: acquired ep-00000011
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000012: acquired endpoint ep-00000011
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000012: executing GET /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/json HTTP/1.1
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000011: start execution ex-00000012
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000011: executing exchange ex-00000012 over http-outgoing-2
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> GET /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/json HTTP/1.1
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "GET /v1.32/exec/c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152/json HTTP/1.1[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:29.357 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 200 OK[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/json[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:29 GMT[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:29.364 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 200 OK
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/json
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:29 GMT
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:29.365 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000012: connection can be kept alive for 3 MINUTES
11:02:29.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "1bf[\r][\n]"
11:02:29.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "{"ID":"c6477e693550d7cab79b7e01f0ed0bde41ce72107b86546516bd29b0e384c152","Running":false,"ExitCode":0,"ProcessConfig":{"tty":false,"entrypoint":"sh","arguments":["-c","mongosh mongo --eval \"rs.initiate();\"  || mongo --eval \"rs.initiate();\""],"privileged":false},"OpenStdin":false,"OpenStderr":true,"OpenStdout":true,"CanRemove":false,"ContainerID":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","DetachKeys":"","Pid":1742}[\n]"
11:02:29.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:29.366 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.382 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000011: releasing valid endpoint
11:02:29.383 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000011: releasing endpoint
11:02:29.383 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000011: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:29.383 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000011: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.384 [main] DEBUG org.testcontainers.containers.MongoDBContainer - {
  info2: 'no configuration specified. Using a default configuration for the set',
  me: '93df093346c7:27017',
  ok: 1
}

11:02:29.384 [main] DEBUG org.testcontainers.containers.MongoDBContainer - Awaiting for a single node replica set initialization up to 60 attempts
11:02:29.384 [main] DEBUG org.testcontainers.containers.ExecInContainerPattern - /elastic_goodall: Running "exec" command: sh -c mongosh mongo --eval "var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }"  || mongo --eval "var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }"
11:02:29.384 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: true,<null>,true,{sh,-c,mongosh mongo --eval "var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }"  || mongo --eval "var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }"},93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4,<null>,<null>,<null>,<null>,<null>
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000013: preparing request execution
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000013: target auth state: UNCHALLENGED
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000013: proxy auth state: UNCHALLENGED
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000013: acquiring connection with route {}->npipe://localhost:2375
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000013: acquiring endpoint (3 MINUTES)
11:02:29.386 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000013: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000013: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000013: acquired ep-00000012
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000013: acquired endpoint ep-00000012
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000013: executing POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000012: start execution ex-00000013
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000012: executing exchange ex-00000013 over http-outgoing-2
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> content-type: application/json
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Content-Length: 713
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:29.387 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "POST /v1.32/containers/93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4/exec HTTP/1.1[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "content-type: application/json[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Content-Length: 713[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:29.388 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "{"containerId":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","AttachStdin":null,"AttachStdout":true,"AttachStderr":true,"Tty":null,"Privileged":null,"User":null,"Cmd":["sh","-c","mongosh mongo --eval \"var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }\"  || mongo --eval \"var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }\""],"Env":null,"WorkingDir":null}"
11:02:29.406 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 201 Created[\r][\n]"
11:02:29.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:29.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/json[\r][\n]"
11:02:29.407 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:29 GMT[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 201 Created
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/json
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:29 GMT
11:02:29.408 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:29.409 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:29.409 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:29.409 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:29.411 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000013: connection can be kept alive for 3 MINUTES
11:02:29.411 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "4a[\r][\n]"
11:02:29.411 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "{"Id":"5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448"}[\n]"
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000012: releasing valid endpoint
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000012: releasing endpoint
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000012: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:29.412 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000012: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.418 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000014: preparing request execution
11:02:29.418 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:29.419 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:29.419 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000014: target auth state: UNCHALLENGED
11:02:29.419 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000014: proxy auth state: UNCHALLENGED
11:02:29.420 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000014: acquiring connection with route {}->npipe://localhost:2375
11:02:29.420 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000014: acquiring endpoint (3 MINUTES)
11:02:29.420 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000014: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.421 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000014: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:29.421 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000014: acquired ep-00000013
11:02:29.421 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000014: acquired endpoint ep-00000013
11:02:29.421 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000014: executing POST /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/start HTTP/1.1
11:02:29.422 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000013: start execution ex-00000014
11:02:29.423 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000013: executing exchange ex-00000014 over http-outgoing-2
11:02:29.424 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> POST /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/start HTTP/1.1
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> content-type: application/json
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Content-Length: 26
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "POST /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/start HTTP/1.1[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "content-type: application/json[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Content-Length: 26[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:29.426 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "{"Detach":null,"Tty":null}"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 200 OK[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/vnd.docker.raw-stream[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:29 GMT[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:29.446 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:29.448 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 200 OK
11:02:29.448 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:29.448 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/vnd.docker.raw-stream
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:29 GMT
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:29.450 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000014: connection can be kept alive for 3 MINUTES
11:02:30.845 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:30.845 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:30.845 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000013: releasing valid endpoint
11:02:30.845 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000013: releasing endpoint
11:02:30.846 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000013: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:30.846 [docker-java-stream--34808032] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000013: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:30.847 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd: 5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448
11:02:30.847 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.exec.InspectExecCmdExec - GET: DefaultWebTarget{path=[/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/json], queryParams={}}
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000015: preparing request execution
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000015: target auth state: UNCHALLENGED
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000015: proxy auth state: UNCHALLENGED
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ConnectExec - ex-00000015: acquiring connection with route {}->npipe://localhost:2375
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000015: acquiring endpoint (3 MINUTES)
11:02:30.848 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000015: endpoint lease request (3 MINUTES) [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000015: endpoint leased [route: {}->npipe://localhost:2375][total available: 0; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ex-00000015: acquired ep-00000014
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000015: acquired endpoint ep-00000014
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000015: executing GET /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/json HTTP/1.1
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000014: start execution ex-00000015
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000014: executing exchange ex-00000015 over http-outgoing-2
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> GET /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/json HTTP/1.1
11:02:30.849 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> accept: application/json
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> User-Agent: tc-java/1.20.6
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Accept-Encoding: gzip, x-gzip, deflate
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Host: localhost:2375
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 >> Connection: keep-alive
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "GET /v1.32/exec/5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448/json HTTP/1.1[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "accept: application/json[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "x-tc-sid: 44c8fccf-806a-49b6-800f-48b6a1a1d846[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "User-Agent: tc-java/1.20.6[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Accept-Encoding: gzip, x-gzip, deflate[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Host: localhost:2375[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "Connection: keep-alive[\r][\n]"
11:02:30.850 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 >> "[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "HTTP/1.1 200 OK[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Api-Version: 1.50[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Content-Type: application/json[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Date: Sun, 12 Apr 2026 08:02:30 GMT[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Docker-Experimental: false[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Ostype: linux[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Server: Docker/28.2.2 (linux)[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "Transfer-Encoding: chunked[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << HTTP/1.1 200 OK
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Api-Version: 1.50
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Content-Type: application/json
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Date: Sun, 12 Apr 2026 08:02:30 GMT
11:02:30.857 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Docker-Experimental: false
11:02:30.858 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Ostype: linux
11:02:30.858 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Server: Docker/28.2.2 (linux)
11:02:30.858 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.headers - http-outgoing-2 << Transfer-Encoding: chunked
11:02:30.858 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.MainClientExec - ex-00000015: connection can be kept alive for 3 MINUTES
11:02:30.858 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "355[\r][\n]"
11:02:30.859 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "{"ID":"5e91b300c28aa191c9e176b8e373cd1b709fc39ad77e67748192f7329b945448","Running":false,"ExitCode":0,"ProcessConfig":{"tty":false,"entrypoint":"sh","arguments":["-c","mongosh mongo --eval \"var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }\"  || mongo --eval \"var attempt = 0; while(db.runCommand( { isMaster: 1 } ).ismaster==false) { if (attempt > 60) {quit(1);} print('An attempt to await for a single node replica set initialization: ' + attempt); sleep(100);  attempt++;  }\""],"privileged":false},"OpenStdin":false,"OpenStderr":true,"OpenStdout":true,"CanRemove":false,"ContainerID":"93df093346c74d29daf492e00b82e60b3be9ff5ff4b528e9f44a0920a2651cf4","DetachKeys":"","Pid":1791}[\n]"
11:02:30.859 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:30.860 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "0[\r][\n]"
11:02:30.860 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.wire - http-outgoing-2 << "[\r][\n]"
11:02:30.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ep-00000014: releasing valid endpoint
11:02:30.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000014: releasing endpoint
11:02:30.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000014: connection http-outgoing-2 can be kept alive for 3 MINUTES
11:02:30.861 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager - ep-00000014: connection released [route: {}->npipe://localhost:2375][total available: 1; route allocated: 1 of 2147483647; total allocated: 1 of 2147483647]
11:02:30.861 [main] DEBUG org.testcontainers.containers.MongoDBContainer -
11:02:30.959 [main] DEBUG org.springframework.test.context.support.TestPropertySourceUtils - Adding inlined properties to environment: {spring.jmx.enabled=false, org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true}

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2026-04-12 11:02:31.822  INFO 6760 --- [           main] c.college.ScheduleFlowIntegrationTests   : Starting ScheduleFlowIntegrationTests using Java 23 on DESKTOP-P20K4U1 with PID 6760 (started by dmitr in C:\GitHub\college-schedule-app-25)
2026-04-12 11:02:31.829  INFO 6760 --- [           main] c.college.ScheduleFlowIntegrationTests   : No active profile set, falling back to default profiles: default
2026-04-12 11:02:33.889  INFO 6760 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2026-04-12 11:02:34.028  INFO 6760 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 126 ms. Found 1 MongoDB repository interfaces.
2026-04-12 11:02:35.589  INFO 6760 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:49259], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2026-04-12 11:02:36.014  INFO 6760 --- [localhost:49259] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:13}] to localhost:49259
2026-04-12 11:02:36.021  INFO 6760 --- [localhost:49259] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:12}] to localhost:49259
2026-04-12 11:02:36.026  INFO 6760 --- [localhost:49259] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:49259, type=REPLICA_SET_PRIMARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=21, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=181773900, setName='docker-rs', canonicalAddress=93df093346c7:27017, hosts=[93df093346c7:27017], passives=[], arbiters=[], primary='93df093346c7:27017', tagSet=TagSet{[]}, electionId=7fffffff0000000000000001, setVersion=1, topologyVersion=TopologyVersion{processId=69db51917867fb21be277d57, counter=6}, lastWriteDate=Sun Apr 12 11:02:30 EEST 2026, lastUpdateTimeNanos=261648155110200}
2026-04-12 11:02:39.785  INFO 6760 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-04-12 11:02:39.786  INFO 6760 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-04-12 11:02:39.788  INFO 6760 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2026-04-12 11:02:39.838  INFO 6760 --- [           main] c.college.ScheduleFlowIntegrationTests   : Started ScheduleFlowIntegrationTests in 8.86 seconds (JVM running for 28.901)
2026-04-12 11:02:41.023  INFO 6760 --- [           main] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:14}] to localhost:49259
2026-04-12 11:02:42.915  INFO 6760 --- [localhost:49259] org.mongodb.driver.cluster               : Exception in monitor thread while connecting to server localhost:49259

com.mongodb.MongoSocketReadException: Prematurely reached end of stream
        at com.mongodb.internal.connection.SocketStream.read(SocketStream.java:112) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.SocketStream.read(SocketStream.java:131) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.InternalStreamConnection.receiveResponseBuffers(InternalStreamConnection.java:647) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.InternalStreamConnection.receiveMessageWithAdditionalTimeout(InternalStreamConnection.java:512) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.InternalStreamConnection.receiveCommandMessageResponse(InternalStreamConnection.java:355) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.InternalStreamConnection.receive(InternalStreamConnection.java:315) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.DefaultServerMonitor$ServerMonitorRunnable.lookupServerDescription(DefaultServerMonitor.java:215) ~[mongodb-driver-core-4.2.3.jar:na]
        at com.mongodb.internal.connection.DefaultServerMonitor$ServerMonitorRunnable.run(DefaultServerMonitor.java:144) ~[mongodb-driver-core-4.2.3.jar:na]
        at java.base/java.lang.Thread.run(Thread.java:1575) ~[na:na]

[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 21.18 s -- in com.college.ScheduleFlowIntegrationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- failsafe:3.5.1:verify (default) @ college-schedule-25 ---
[INFO]
[INFO] --- jacoco:0.8.12:report-integration (report-integration) @ college-schedule-25 ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco-integration-tests.exec
[INFO] Analyzed bundle 'college-sample' with 3 classes
[INFO]
[INFO] --- jacoco:0.8.12:check (check-integration) @ college-schedule-25 ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco-integration-tests.exec
[INFO] Analyzed bundle 'college-schedule-25' with 3 classes
[INFO] All coverage checks have been met.
[INFO]
[INFO] --- install:3.1.2:install (default-install) @ college-schedule-25 ---
[INFO] Installing C:\GitHub\college-schedule-app-25\pom.xml to C:\Users\dmitr\.m2\repository\com\college\college-schedule-25\0.2.0-SNAPSHOT\college-schedule-25-0.2.0-SNAPSHOT.pom
[INFO] Installing C:\GitHub\college-schedule-app-25\target\college-schedule-25-0.2.0-SNAPSHOT.jar to C:\Users\dmitr\.m2\repository\com\college\college-schedule-25\0.2.0-SNAPSHOT\college-schedule-25-0.2.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  59.085 s
[INFO] Finished at: 2026-04-12T11:02:45+03:00
[INFO] ------------------------------------------------------------------------
```

```
C:\GitHub\college-schedule-app-25>chcp 65001
```

```
mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< com.college:college-schedule-25 >-------------------
[INFO] Building college-sample 0.2.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:2.5.4:run (default-cli) > test-compile @ college-schedule-25 >>>
[WARNING] 1 problem was encountered while building the effective model for org.javassist:javassist:jar:3.20.0-GA during dependency collection step for project (use -X to see details)
[INFO]
[INFO] --- checkstyle:3.3.1:check (checkstyle-validate) @ college-schedule-25 ---
[INFO] Starting audit...
Audit done.
[INFO] You have 0 Checkstyle violations.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent (prepare-agent) @ college-schedule-25 ---
[INFO] argLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\college-schedule-app-25\\target\\jacoco.exec
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ college-schedule-25 ---
[INFO] Copying 4 resources from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ college-schedule-25 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ college-schedule-25 ---
[INFO] skip non existing resourceDirectory C:\GitHub\college-schedule-app-25\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ college-schedule-25 ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< spring-boot:2.5.4:run (default-cli) < test-compile @ college-schedule-25 <<<
[INFO]
[INFO]
[INFO] --- spring-boot:2.5.4:run (default-cli) @ college-schedule-25 ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2026-04-12 11:23:03.718  INFO 13732 --- [           main] com.college.CollegeApplication           : Starting CollegeApplication using Java 23 on DESKTOP-P20K4U1 with PID 13732 (C:\GitHub\college-schedule-app-25\target\classes started by dmitr in C:\GitHub\college-schedule-app-25)
2026-04-12 11:23:03.726  INFO 13732 --- [           main] com.college.CollegeApplication           : No active profile set, falling back to default profiles: default
2026-04-12 11:23:04.654  INFO 13732 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2026-04-12 11:23:04.722  INFO 13732 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 59 ms. Found 1 MongoDB repository interfaces.
2026-04-12 11:23:05.436  INFO 13732 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
2026-04-12 11:23:05.463  INFO 13732 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-04-12 11:23:05.464  INFO 13732 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.52]
2026-04-12 11:23:05.613  INFO 13732 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2026-04-12 11:23:05.613  INFO 13732 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1759 ms
2026-04-12 11:23:05.837  INFO 13732 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2026-04-12 11:23:05.950  INFO 13732 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:7}] to localhost:27017
2026-04-12 11:23:05.950  INFO 13732 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:8}] to localhost:27017
2026-04-12 11:23:05.956  INFO 13732 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=42085300}
2026-04-12 11:23:06.768  INFO 13732 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2026-04-12 11:23:06.780  INFO 13732 --- [           main] com.college.CollegeApplication           : Started CollegeApplication in 3.934 seconds (JVM running for 4.661)
2026-04-12 11:24:03.335  INFO 13732 --- [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-04-12 11:24:03.337  INFO 13732 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-04-12 11:24:03.346  INFO 13732 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2026-04-12 11:24:03.443  INFO 13732 --- [nio-8081-exec-1] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:9}] to localhost:27017
```

## CI (GitHub Actions)

Файл workflow: `.github/workflows/ci.yml`

CI-pipeline запускається автоматично для:
1. Pull request (`opened`, `synchronize`, `reopened`) у гілки `main` та `release/*`
2. Push у гілки `main` та `release/*`

Етапи pipeline:
1. `static-analysis`:
- Запускає Checkstyle (`mvn -B -DskipTests checkstyle:check`)
- Завершує CI з помилкою, якщо правила порушено
2. `unit-tests`:
- Запускає `mvn -B -Dcheckstyle.skip=true -DskipIntegrationTests=true test`
- Публікує `Surefire`-звіти та артефакти покриття unit-тестів
- Додає в `GITHUB_STEP_SUMMARY` підсумок line coverage для unit-тестів
3. `integration-tests`:
- Запускає `mvn -B -Dcheckstyle.skip=true -DskipUnitTests=true verify`
- Піднімає ізольований MongoDB через Testcontainers
- Публікує `Failsafe`-звіти та артефакти покриття integration-тестів
- Додає в `GITHUB_STEP_SUMMARY` окремий підсумок line coverage для `com.college.Schedule`
- Додає в `GITHUB_STEP_SUMMARY` окремий підсумок line coverage для `com.college.ScheduleController`
4. `build`:
- Запускає `mvn -B -DskipUnitTests=true -DskipIntegrationTests=true package`
- Створює JAR після успішного проходження unit та integration тестів
5. `publish`:
- Використовує reusable workflow `.github/workflows/maven-publish.yml`
- Запускається лише після успішного завершення етапу `build`

Артефакти та звіти:
1. Артефакт збірки: `target/*.jar`
2. Звіти тестів: `target/surefire-reports/**`
3. Звіти integration-тестів: `target/failsafe-reports/**`
4. Звіти unit test coverage: `target/site/jacoco/**`, `target/jacoco.exec`
5. Звіти integration test coverage: `target/site/jacoco-integration-tests/**`, `target/jacoco-integration-tests.exec`
6. При падінні static analysis workflow завантажує логи: `target/ci-logs/**`
7. При падінні unit-тестів workflow завантажує логи: `target/ci-logs/**`
8. При падінні integration-тестів workflow завантажує логи `target/ci-logs/**` і додатково звіти `target/failsafe-reports/**` та `target/site/jacoco-integration-tests/**`

## Тестування та покриття

Поточний стан:
1. Unit-тести написані на **JUnit 5**.
2. Integration-тести запускаються через **Failsafe** і використовують **Testcontainers MongoDB**.
3. Для покриття використовується **JaCoCo**.
4. У `pom.xml` налаштовано мінімальне покриття **75% line coverage** для unit-тестів (перевірка на етапі `package`) і **100% line coverage** для інтеграційних тестів для класів `com.college.Schedule` та `com.college.ScheduleController` (перевірка на етапі `verify`).

Локальний запуск:
1. Тільки unit-тести:
`mvn test`
2. Тільки integration-тести:
`mvn -DskipUnitTests=true verify`
3. Повна перевірка (unit + integration + JaCoCo):
`mvn verify`

Звіти:
1. JUnit/Surefire: `target/surefire-reports/`
2. JUnit/Failsafe: `target/failsafe-reports/`
3. Unit JaCoCo HTML: `target/site/jacoco/index.html`
4. Integration JaCoCo HTML: `target/site/jacoco-integration-tests/index.html`
5. Unit JaCoCo CSV: `target/site/jacoco/jacoco.csv`
