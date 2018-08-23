# Description
- upload file from web browser
- launch srping-batch job to process file:
  - Read csv file
  - Insert into a table 

The goal is to have the fields declared only once, in the pojo (Student.java). No sql file (the table should be create by the application if not exists)

# Version
- V1: generic write with introspection, sql file, no read, no jpa
- V2: use jpa to create data in the table (in memory)
- V3: insert in postgres
- V4: use lib 'opencsv' to read csv into pojo
- V5: vaadin add upload page

# Launching
Import the project to the IDE of your choosing as a Maven project. 

Run application using
`mvn spring-boot:run`

Open http://localhost:8080/ in browser


### Legacy: How the pojo was created the first time
##### get column name from csv & replace 'tab' with '\n'
##### create column name in notepadd++, then apply regex
    search: (é)|(\/)|(è)|(ô)|(à)|(d')|(\))|(^\w)|[^\w\n]+(\w)
    replaceby: (?1e)(?2)(?3e)(?4o)(?5a)(?6)(?6)(?8\L$8)(?9\U$9)
##### some manual cleanup
