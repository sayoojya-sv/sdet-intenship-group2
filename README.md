**ICTAK-Institution Portal**

**Project Overview**

The Institution Portal enables partner institutions to access program details, execute MoUs, and submit nominations or registrations for credit-based programs, and this end-to-end test plan defines the strategy to validate complete workflows, security, and functional accuracy to ensure readiness for production release.

**Tools and Technologies Used**

* Programming Language: Java  
* Automation Tool: Selenium WebDriver  
* Testing Framework: TestNG  
* Build Tool: Maven  
* Design Pattern: Page Object Model (POM)  
* Version Control: Git & GitHub


**Framework Workflow-ICTAK\_Institution Portal**

├── src/test/java

│   ├── assertions

│   ├── base

│   ├── pages

│   └── utility

├── src/test/resources

│   ├── config.properties

│   └── intern.data

├── test-output

├── pom.xml

├── testng.xml

└── README.md

**Prerequisites** 

* Java JDK  
* Maven  
* Chrome & ChromeDriver  
* Git

**Setup Instructions**

1\. Clone the Repository

2\. Configure Browser & Environment

* Update application URL and browser details in config.properties

* ChromeDriver is managed automatically using WebDriverManager

3\. Install Dependencies

**Challenges Addressed**

* Dynamic element handling  
* Alert handling  
* Stale element issues  
* Click interception  
* Fast execution synchronization

**Conclusion**

This framework follows industry standards and is suitable for real-world automation testing.

