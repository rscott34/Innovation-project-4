Single‑Scan Traceability Quest
=============================================================

In this first sprint we have created a working demo of our system so far, which allows the
user to lookup a product using it’s ProductID and then see a traceability timeline of the
product which includes visual flow from raw materials, processing, assembly, and
transport to the shelf. It also displays a claim card (e.g., "Organic" for olive oil) that allows
the user to click and view the linked evidence item (e.g., a PDF or certificate or a label on
the bottles that display the EU Organic logo). We have also implemented one mission
task for CW1 which allows to user to answer a question and will display diKerent
information depending on whether they are right or wrong. Finally, there is also an option
for a verifier to login to the system which allows them to then create/edit product stages,
attach evidence and justify confidence levels, all whilst logging any changes made.

Google docs:
https://docs.google.com/document/d/1JFQC1sF5lLrDW2v26Acps6JdZBTJdyL1oB0HNf6tVbo/edit?usp=sharing

Coursework brief:
https://ele.exeter.ac.uk/pluginfile.php/5542027/mod_resource/content/1/COMM2020%20Team%20project%20Coursework%20brief.pdf


**User Roles**
=============================================================

Project Lead: Rowan
- Plans, facilitates, keeps risks visible

Data/ML lead: Raahim
- Data pipline, modelling choices, evaluation design

Software Developer: Waj & Adam
- Build & maintain software

QA & testing: Mateo
- Test strategy, bug triage, definition of done

Technical lead: Jacob
- Architecture, integration decisions, code quality

UI/UX designer: George
- Creating user experience, Stakeholders, user stories, accpetance criteria

Documentation & comms: Kaylan
- Demo narrative, report, decisions log


**Steps for Setting up the Database locally**
=============================================================

PostgreSQL (with PGadmin) needs to be installed
https://www.postgresql.org/

Download the .sql file

Open PGadmin

Right click Servers

![alt text](image.png)

Register > Server

Name: Innovation-Group (can be anything)

Go to Connection tab

Hostname: localhost
Port: 5432
Username: postgres
Password: 1234

Save Server

Click Drop down on the Server

![alt text](image-1.png)

Right Click Databases

Create new database

Name: testDB
Owner: postgres

Save

right click testDB  

Restore

Format: Custom or tar
Filename: select the file
(may need to change file type searching for to .sql file)

Restore

Complete

