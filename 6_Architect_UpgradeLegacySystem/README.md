# 6. Task: Scalable and Agile Web Systems

## Task Description

Scenario: Imagine that you have been appointed as the architect for a project to upgrade an existing e-commerce web system. The current system is over 5 years old and needs significant improvements to meet growing customer demands. The development team follows the Agile Scrum methodology.

## Responses

### Current System Analysis

**Briefly describe the main challenges and problems identified in the current web system that justifies an upgrade.**
**List the main features and functionalities that the system must maintain or improve during the upgrade.**

Due to the rapid advances of technology, the ways in which companies and consumers expect to interact with an e-commerce may have changed significantly in 5+ years.
Due to a growing customer base, the current system may need to be converted to a more scalable architecture in order to cope with performance demands.
The way customers access the service may have changed, for example, the application should be compatible with mobile devices as well as computers.
In the interests of staying competitive, the system may need to handle new payment methods and related security requirements. For example, inclusion of PayPal or contactless payments.
The technology should be kept up to date in order to be ahead of any security vulnerabilities.
After 5+ years, the system may be difficult to maintain and this is causing significant overhead when trying to adapt to changes or fix bugs. In the case the architecture may need to be assessed.
The customer data and any repositories must be protected and maintained during any changes to the system.

### System Architecture

**Design a high-level architecture for the updated web system. Consider scalability, security, performance and the ability to handle a large volume of customer traffic.**
**Specify the technologies and frameworks you would recommend to implement the architecture.**

The high-level architecture could consist of a front-end for providing the web application to the customer via their browser or mobile; a back-end API gateway for orchestrating requests to a set of microservices.
A separated front-end application that communicates with the back-end via a well-defined, and versioned, API means that changes to the web application shown to the consumer can be released independently of changes to the rest of the system.
A gateway can handle the requests from the client and deal with sending them to the appropriate load-balanced services when there is higher demand.
Spring includes a typical framework that provides a lot this functionality.
The microservices are usually designed to fulfil specific purposes and can be deployed, for example, inside Docker containers such that multiple instances can be span up if demand requires it. E.g. using Kubernetes. Deploying to Amazon Web Services is a solution for creating and destroying VMs as required by real-time demand.
By separating the services in this way not only improves the scalability and performance of the system by allowing multiple instances to be ran simultaneously, but also improves maintainability of the system as a whole because fixes can be made more rapidly on smaller sections of the system. With clear APIs between the different services, the security is also improved as it provides fewer opportunities for hackers to exploit vulnerabilities.

### Security

**Describe the security practices and measures you would implement to protect the system against common threats.**

Quality gate controls integrated within the development environments to highlight any code vulnerabilities at the time of writing.
Maintain all system dependencies up to date in order to ensure any security fixes are patched as soon as possible.
Keep key infrastructure behind firewalls in order to limit the number of potentially vulnerable access points to the system and servers. Access to any URLs should be done via SSL in order to ensure connections are encrypted and to avoid snooping of transferred data.
Only minimal user details should be stored, preferably encrypted, and if it is necessary to store passwords they must be strongly encrypted.
Any web applications should follow secure code practices to avoid such common issues as cross-site request forgery or injections.

### Project Scope

**Propose a set of priority features for the first iteration of the Scrum project, highlighting those that will bring the greatest value to customers.**
**Describe how you would deal with the constantly changing requirements that are common in Agile Scrum projects.**

Assuming that performance may be a key issue affecting customers as their demands grow, initial steps would be to assess the main bottlenecks and determine if functionality could be broken down into simpler services that could then benefit from parallelisation or load-balancing.
By gradually converting the project into micro-services (if not already the case) that can be released independently, the team will be able to maintain or implement new functionality as the requirements change without being blocked by having to release the entire system, thus alleviating the time that would have been required for integration testing a much large system.

### Continuous Integration and Continuous Delivery (CI/CD)

**Explain how you would implement a CI/CD pipeline to automate the building, testing and deployment of the updated web system.**

The code repository would be structured to provide the following branches:
• Master – the latest stable release that has been deployed
• Develop – the current main development branch of work towards the next main release
• Feature-x – feature branches that allow developers to work on new features without blocking the develop branch until ready
• Release-x – a release branch created from the develop that “freezes” the current code as it is validated before merging with the master branch to be deployed.
• Hotfix-x – a specific branch for fixing bugs found on the currently deployed release that must be fixed in a patch release
The CI/CD pipeline would involve hooking a CI tool (such as Jenkins or Drone) into the code repository such that on each pushed commit a pre-defined set of build steps and tests will be ran. These steps will ensure that and alert if the latest code integration is valid and has not created any regression errors (to be tested against a set of pre-defined unit and integration tests).
During a build the code can be validated against a Quality tool (such as Sonar) and can be configured to fail if the code does not achieve the minimum quality.
Successful builds will produce artifacts that can be deployed in validation or operational environments depending on the originating branch.
When closing a release or hotfix branch into master, the CI/CD tool can be configured to automatically deploy the validated release to the production environment.

### Data Management

**Suggest a data management strategy, including storage and access to system data.**

1. Identify the data that is needed in order to provide the functionality of the e-commerce web system.
2. Identify from where the data will come and how to collect it.
3. Design a data model and identify how the data needs to be combined. Assess whether it is better to store the data in a structured or unstructured format. E.g. relational database or NoSQL.
4. Design the system to ingest and curate the data.
5. Design the API(s) to access the data securely.
   In the case of customer data, ensure that data can be collected securely and legally and a data disposal policy is clearly defined.

### Test and Quality

**Describe how you would ensure code quality and test coverage throughout the development of the project.**

By integrating a Quality Gate tool into the continuous integration pipeline will ensure that any committed code is assessed for its level of code quality and test coverage. The CI tool can be configured to fail a build if it does not achieve the minimum quality or test coverage.
By having developers include a Quality Gate plugin (e.g. Sonar) in their IDEs, any code quality issues can be highlighted in real-time as the developer is writing code.
Given the system in question is 5+ years old, if quality control had not been previously applied to the project then it can be very difficult to achieve sufficient quality across the entire code base. In this case, it could be recommended to apply the quality controls only to new code.

### Team Management

**Discuss how you would lead the development team in the context of Agile Scrum, including roles and responsibilities.**

The purpose of Agile Scrum is to provide a project management structure for the team to be able to adapt rapidly to changing requirements through frequent feedback iterations that allow functional products to be released incrementally on a regular basis.
I would ensure that the roles and responsibilities are clearly identified within the team, and as a key principle of Agile is the self-organisation of the team, facilitate that through the daily scrum meetings and sprint retrospectives any issues or improvements can be identified and tackled rapidly.
It must be clear who is/are the Product Owner(s) as they define and assign priorities to the work that must be carried out, in particular identifying the sprint goals. The Product Owner is usually responsible for communicating with the Stakeholders and interpreting their requirements.
It is useful to have a Scrum Master who will be responsible for managing the sprint, such as organising the sprint review, planning and retrospective meetings. During a sprint, the Scrum Master will help the team identify blockers and try to prevent scope creep.
The developers in the team are responsible for developing the product. Through the continuous feedback loop of sprints, it can be quickly identified whether any aspects of scrum should be modified in order for the team to be more comfortable in their work. For example, a good definition of “ready” for when tickets can move from the product backlog to the sprint backlog and similarly an agreed definition of “done” or “validated” for completed tickets. Also, the sprint duration may need to be adjusted depending on the project – for example, a 2 week sprint may be considered too fast a turn-around for some large teams.

### Documentation

**Explain the importance of system documentation and how you would ensure that the team keeps the documentation up to date.**

System documentation is key to ensure an efficient maintainability of the software and system integration which in turn helps to mitigate issues, accidents or regressions. Good documentation also helps reduce the learning curve for incorporating new members into the team.
It is useful for team members to understand many parts of the system as a whole in order to avoid silos in the team. By having team members work on different parts of the system, they would be encouraged to keep documentation up to date in order to facilitate their understanding when switching between areas.
Use of tools, such as swagger, can make it easier to quickly generate good documentation which in turn encourages developers to maintain the documentation as it does not imply such an overhead to their time.
