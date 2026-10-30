# DevOps Demo: Automated Java Build and Test with Jenkins

A Spring Boot app with a Jenkins pipeline that builds, tests and packages the code on every push to GitHub.

## What it does
Checkout -> Build -> Test -> Package. JUnit results are published and the JAR is archived.

## Tech
Java 17, Spring Boot, Maven, Git/GitHub, Jenkins (running in Docker on my laptop)

## Pipeline (see `Jenkinsfile`)
1. Checkout code from GitHub
2. `mvn clean compile`
3. `mvn test` (JUnit results published in Jenkins)
4. `mvn package` (JAR archived as a build artifact)

Jenkins polls GitHub every 2 minutes and starts a build automatically.

## Proof it works

**Build history and test trend: green, red (failing test), green again**

![Builds](docs/jenkins-builds.png)

- #1 failed: wrong branch configured (`*/master` instead of `*/main`)
- #2 passed: first successful build
- #3 failed: I broke a test on purpose, and the pipeline stopped before packaging
- #5 passed: test fixed, pipeline green again

**A failing test stops the pipeline (build #3 console)**

![Failed test](docs/failed-test.png)

**Archived JAR and test results (no failures)**

![Artifact](docs/artifact.png)

## What I learned
- Writing a declarative `Jenkinsfile`
- Configuring Maven as a Jenkins tool
- Using a failing test to verify the pipeline blocks bad code
- Debugging a Git branch configuration error from the console log

## Run locally
```
.\mvnw clean package
java -jar target\demo-0.0.1-SNAPSHOT.jar --server.port=8082
```
Then open http://localhost:8082/hello (port 8080 is used by Jenkins)
