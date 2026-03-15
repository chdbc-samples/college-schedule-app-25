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
mvn exec:java
```

PowerShell:

```powershell
$env:MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/college-db?retryWrites=true&w=majority"
mvn exec:java
```

Linux/macOS:

```bash
export MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/college-db?retryWrites=true&w=majority"
mvn exec:java
```

Примітка: якщо пароль містить спеціальні символи, їх потрібно URL-кодувати в connection string. Наприклад, символ `@` потрібно замінити на `%40`.

## Результати виконання програми
```
C:\GitHub\college-schedule-app-25>mvn clean package
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.college:college-schedule >--------------------
[INFO] Building college-sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
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
[INFO] Compiling 3 source files with javac [debug target 17] to target\classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically       
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ college-schedule ---
[INFO] skip non existing resourceDirectory C:\GitHub\college-schedule-app-25\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ college-schedule ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 4 source files with javac [debug target 17] to target\test-classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically       
[INFO]
[INFO] --- surefire:3.5.1:test (default-test) @ college-schedule ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.college.CollegeApplicationTest
java.lang.RuntimeException: repository unavailable
        at com.college.CollegeApplication.addScheduleFromCsv(CollegeApplication.java:115)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at com.college.CollegeApplicationTest.invokePrivate(CollegeApplicationTest.java:145)
        at com.college.CollegeApplicationTest.addScheduleFromCsvPrintsFailureMessageWhenRepositoryThrows(CollegeApplicationTest.java:52)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:688)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:149)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:140)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:84)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:104)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:98)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:210)        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:206)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:131)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:65)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
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
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.113 s -- in com.college.CollegeApplicationTest
[INFO] Running com.college.ScheduleRepositoryTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.027 s -- in com.college.ScheduleRepositoryTest
[INFO] Running com.college.ScheduleTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.014 s -- in com.college.ScheduleTest
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] 
[INFO] --- jacoco:0.8.12:report (report) @ college-schedule ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco.exec
[INFO] Analyzed bundle 'college-sample' with 2 classes
[INFO] 
[INFO] --- jar:3.4.1:jar (default-jar) @ college-schedule ---
[INFO] Building jar: C:\GitHub\college-schedule-app-25\target\college-schedule-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- jacoco:0.8.12:check (check) @ college-schedule ---
[INFO] Loading execution data file C:\GitHub\college-schedule-app-25\target\jacoco.exec
[INFO] Analyzed bundle 'college-schedule' with 2 classes
[INFO] All coverage checks have been met.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  19.856 s
[INFO] Finished at: 2026-03-09T02:00:10+02:00
[INFO] ------------------------------------------------------------------------
```

```
C:\GitHub\college-schedule-app-25>chcp 65001
```

```
$ mvn exec:java
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

2026-03-15 13:23:01.753  INFO 23921 --- [lication.main()] com.college.CollegeApplication           : Starting CollegeApplication using Java 25.0.2 on codespaces-a54bda with PID 23921 (/workspaces/college-schedule-app-25/target/classes started by vscode in /workspaces/college-schedule-app-25)
2026-03-15 13:23:01.755  INFO 23921 --- [lication.main()] com.college.CollegeApplication           : No active profile set, falling back to default profiles: default
2026-03-15 13:23:02.079  INFO 23921 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2026-03-15 13:23:02.123  INFO 23921 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 40 ms. Found 1 MongoDB repository interfaces.
2026-03-15 13:23:02.437  INFO 23921 --- [lication.main()] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[127.0.0.1:27017], srvHost=cluster0.lnmgkes.mongodb.net, mode=MULTIPLE, requiredClusterType=REPLICA_SET, serverSelectionTimeout='30000 ms', requiredReplicaSetName='atlas-thm4an-shard-0'}
2026-03-15 13:23:02.495  INFO 23921 --- [kes.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017 to client view of cluster
2026-03-15 13:23:02.536  INFO 23921 --- [kes.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017 to client view of cluster
2026-03-15 13:23:02.538  INFO 23921 --- [kes.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017 to client view of cluster
2026-03-15 13:23:02.895  INFO 23921 --- [lication.main()] com.college.CollegeApplication           : Started CollegeApplication in 1.664 seconds (JVM running for 4.478)
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 2026-03-15 13:23:03.372  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:162144}] to ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.373  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:166773}] to ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.373  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:172900}] to ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.374  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:4, serverValue:162144}] to ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.375  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:6, serverValue:166773}] to ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.372  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:5, serverValue:172900}] to ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.393  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017, type=REPLICA_SET_SECONDARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=449721409, setName='atlas-thm4an-shard-0', canonicalAddress=ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017, hosts=[ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017], passives=[], arbiters=[], primary='ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='eun1-az2'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='AWS'}, Tag{name='region', value='EU_NORTH_1'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=null, setVersion=16, topologyVersion=TopologyVersion{processId=69b2dd3939cbe272cd48a228, counter=3}, lastWriteDate=Sun Mar 15 13:23:02 UTC 2026, lastUpdateTimeNanos=4153764175640}
2026-03-15 13:23:03.393  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017, type=REPLICA_SET_PRIMARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=445969756, setName='atlas-thm4an-shard-0', canonicalAddress=ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017, hosts=[ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017], passives=[], arbiters=[], primary='ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='eun1-az3'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='AWS'}, Tag{name='region', value='EU_NORTH_1'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=7fffffff00000000000000c1, setVersion=16, topologyVersion=TopologyVersion{processId=69b2db529d278ac4be012462, counter=6}, lastWriteDate=Sun Mar 15 13:23:02 UTC 2026, lastUpdateTimeNanos=4153760811353}
2026-03-15 13:23:03.393  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017, type=REPLICA_SET_SECONDARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=448986000, setName='atlas-thm4an-shard-0', canonicalAddress=ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017, hosts=[ac-wwkixgl-shard-00-00.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-01.lnmgkes.mongodb.net:27017, ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017], passives=[], arbiters=[], primary='ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='eun1-az1'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='AWS'}, Tag{name='region', value='EU_NORTH_1'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=null, setVersion=16, topologyVersion=TopologyVersion{processId=69b2d8870c6d3c42b8889b1f, counter=4}, lastWriteDate=Sun Mar 15 13:23:02 UTC 2026, lastUpdateTimeNanos=4153765342702}
2026-03-15 13:23:03.396  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Setting max election id to 7fffffff00000000000000c1 from replica set primary ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.396  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Setting max set version to 16 from replica set primary ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
2026-03-15 13:23:03.396  INFO 23921 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Discovered replica set primary ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
1
2026-03-15 13:23:08.553  INFO 23921 --- [lication.main()] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:7, serverValue:172835}] to ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017
5 документів з рядками з розкладу завантажено з CSV.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 4
2026-03-15 13:23:12.328  INFO 23921 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:7, serverValue:172835}] to ac-wwkixgl-shard-00-02.lnmgkes.mongodb.net:27017 because the pool has been closed.
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
2. `build`:
- Запускає `mvn -B package` (включно з unit-тестами)
- Завершується з помилкою, якщо збірка або тести не пройшли

Артефакти та звіти:
1. Артефакт збірки: `target/*.jar`
2. Звіти тестів: `target/surefire-reports/**`
3. Звіти покриття: `target/site/jacoco/**`, `target/jacoco.exec`
4. Підсумок покриття виводиться в логах збірки та в GitHub Step Summary.

## Тестування та покриття

Поточний стан:
1. Unit-тести написані на **JUnit 5**.
2. Для покриття використовується **JaCoCo**.
3. У `pom.xml` налаштовано мінімальне покриття **75% line coverage** (перевірка на етапі `package`).

Локальний запуск:
1. Тільки unit-тести:
`mvn test`
2. Повна перевірка (включно з JaCoCo check):
`mvn package`

Звіти:
1. JUnit/Surefire: `target/surefire-reports/`
2. JaCoCo HTML: `target/site/jacoco/index.html`
3. JaCoCo CSV: `target/site/jacoco/jacoco.csv`
