Campus Course & Records Manager (CCRM)
Project Overview
This is a console-based Java SE application for managing academic records, including students, courses, enrollment, and grades. The application is built using a layered architecture to demonstrate advanced Java features.

How to Run
Prerequisites: Ensure you have Java Development Kit (JDK) [version used] installed.

Clone Repository:

Bash

git clone (https://github.com/rohansaini28-sudo/COURSE-management-system)
cd [project-folder-name]
Run (from command line, after compiling):

Bash

java -jar ccmr.jar
# OR (from IDE)
Run the main() method in the [YourMainClass] class.
Technical Documentation (Mandatory Requirements)
1. Java Platform and Setup
Topic	Description	Screenshot Reference
Evolution of Java		
[Short timeline bullet points for Java's evolution] 

N/A
Java ME vs SE vs EE		


N/A
Java Architecture		
[Explanation of JDK, JRE, and JVM and how they interact] 

N/A
Install & Configure Java		
[Detailed steps to install Java on Windows] 

screenshots/java-install-verify.png 

Eclipse IDE Setup		
[Steps for new project creation and run configurations in Eclipse] 

screenshots/eclipse-setup.png 


Export to Sheets
2. Core Java Concepts Demonstrated
Concept	File/Class/Method Where Demonstrated
Packages		
edu.ccrm.domain, edu.ccrm.service, edu.ccrm.io, edu.ccrm.util, edu.ccrm.cli 

Operators & Precedence		
[Specify location of a small documented example] 

Decision/Loops/Jumps		
[Specify location of if-else, switch menu, break/continue/labeled jump] 


Arrays & Utilities		
Arrays.sort() or Arrays.binarySearch() in [ServiceClass] 

Date/Time API		
Student class (for date fields) and BackupService (for timestamps) 


NIO.2 (Path & Files)		
BackupService, ImportExportService (for check/delete/copy/move) 

Streams API		
CourseService (for search/filter), TranscriptService (for aggregation) 



Export to Sheets
3. OOP & Advanced Concepts
Concept	File/Class Where Demonstrated
Encapsulation		
Student, Course (private fields + getters/setters) 

Inheritance		
Person (abstract) → Student and Instructor 

Abstraction		
Person abstract class with abstract methods 

Polymorphism		
TranscriptService (using base-class references) 

Immutability		
CourseCode (final fields, defensive copying) 

Interfaces		
Persistable (or similar), Searchable<T> 

Functional Interfaces/Lambdas		
Comparator in [UtilClass] for sorting/filtering 

Enums with Fields		
Semester, Grade 

Design Pattern: Singleton		
AppConfig or DataStore 

Design Pattern: Builder		
Course.Builder or Transcript.Builder 

Custom Exceptions		
DuplicateEnrollmentException, MaxCreditLimitExceededException 


Export to Sheets
4. Exceptions and Assertions
Topic	Description
Errors vs Exceptions		
[Clarify the difference between Java Errors and Exceptions] 

Assertions		
[Note on enabling assertions (e.g., -ea VM argument) and sample commands] 


Exception Handling		
[Location of try/catch/multi-catch/finally/throw/throws] 


Export to Sheets
USAGE.md (or Usage Section in README)

Sample Data: [Brief description of the structure of the CSV-like data files in the test-data folder].


Menu Workflow: [Show a simple run-through of the main CLI menu options].


Example Operations:

Add Student: 1 -> Add Student -> [details]

Enroll Student: 3 -> Enroll -> [student ID], [course code]


Export/Backup: 5 -> Export Data -> 6 -> Backup Data 

Screenshots Folder Reference
The repository includes a screenshots/ folder with the following mandatory files:


java-install-verify.png: Screenshot of java -version output.


eclipse-project-setup.png: Screenshot of Eclipse project setup/run configuration.


program-menu.png: Screenshot of the main CCRM menu.


program-operation.png: Screenshot of a sample operation (e.g., printing a transcript).


exports-backup-structure.png: Screenshot showing the generated timestamped backup folder structure.