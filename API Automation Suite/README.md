#  API Automation Framework

This project is an **end-to-end API automation framework** for **PetStore User CRUD operations**, built using:

**REST Assured + Cucumber BDD + TestNG + Maven + Jenkins**

It supports *positive and negative testing**, **centralized configuration**, **HTML reporting**, and **CI/CD execution** with Jenkins.

---

## 📂 Project Structure


API Automation Suite
│
├── src/main/java
│ ├── config # ConfigReader
│ ├── constants #Endpoints
│ ├── payloads #UserPayload
│ ├── specs #RequestSpecUtil and ResponseSpecUtil
│ ├── utils #ExceptionUtil and LoggerUtil
│
├── src/main/resources
│ ├── features # BDD Feature files (.feature)
│ ├── schemas # JSON schemas for validation
│ ├── config.properties
│ └── log4j2.xml
│
├── Jenkinsfile # CI/CD pipeline configuration
├── pom.xml # Maven dependencies & plugins
└── README.md # Project documentation


---

## ⚙️ Setup Instructions

1. **Clone the repository**

```bash
-- git clone https://github.com/your-username/api-automation-framework.git
-- cd api-automation-framework


2. **Install dependencies**
 -- mvn clean install


3. **Run API tests**
 -- mvn clean test


4. **View reports**
 --/reports/cucumber-report.html


5. **Jenkins CI execution**
-- Configure a Jenkins job to run mvn clean test automatically on every commit.
