# Expense Reimbursement System
  The Expense Reimbursement System (ERS) manages the process of reimbursing employees for expenses incurred while on company time. All employees can log in and submit requests for reimbursement and view their past tickets and pending requests. Managers can then log in and view all reimbursement requests and past history for all employees. These managers are authorized to approve and deny requests for expense reimbursements.

## Technologies Used
Java, JavaScript, AJAX, Servlets, HTML/CSS, JDBC, SQL, Hibernate, JUnit, Mockito, Bootstrap, Tomcat, Git, Maven, AWS EC2, AWS RDS, Jenkins

## Features implemented
1. Authentication and Authorization based on session and role.
2. Image uploading and email sending by using AJAX, Servlet, AWS RDS, and Apache Commons-email. 
3. Performing CRUD operations by using Hibernate.
4. Creating a continuous delivery pipeline by using Jenkins, Github, Maven and deploying on AWS EC2.

## To-do list:
1. To build more efficient front end by using Angular framework.
2. To improve application security.
3. To add more functions to the system.

## Project set up
1. Download source code by: git clone	https://github.com/GoldWayBear/Expense-Reimbursement-System
2. Setup AWS RDS with Postgres Database 
3. Setup AWS EC2 instance
4. Set up Apache tomcat and Jenkins and Enviroment varibles in EC2
5. Run Apache tomcat and Jekins in EC2
6. Set up CI/CD in Jenkins by using Maven Integration,Slack Notification,Github Integration and	Deploy to Container
7. Build all project by tools in Jenkins
   
## How to connect Git and EC2 in windows
1. Using Git Bash to pull or upload source code in windows.
2. USing SSH to coonect AWS EC2 in windows.  

## Usage of this application
1. This application can used by Employee or Manager, base on the authorization.
2. In Employee page, user can submit requests with reciept image for reimbursement and view their past tickets and pending requests,
an Employee can reset their password.  
4. In Manage Page, user can view all reimbursement requests and past history for all employees, and approve and deny requests for 
expense reimbursements. A Manager can register an Employee, which sends the Employee an email with their username and temp password.

## start using it
URL:
http://3.86.155.49:8088/ProjectPipeline/index.html

## License
This project uses the following license: 

https://www.apache.org/licenses/

https://opensource.org/licenses/MIT
