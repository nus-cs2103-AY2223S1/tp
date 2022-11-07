Use Case

System: Teaching Assistant Assistant (TAA)

UC1 - Add student
Actor: User
MSS:

1. User chooses to add a student record.
2. TAA displays the page for adding student.
3. User inputs the details of the student.
4. User confirms the details.
5. TAA add the record to the system.

Extensions
3a. TAA checks for duplicates and incorrect formating.
3a1. TAA requests for correct data and format.
3a2. User inputs the correct data.
Steps 3a1-3a2 are repeated until the data entered are correct.
Use case resumes from step 4.

UC2 - Edit student's record.
Actor: User
MSS:

1. User searches for his/her name or ID.
2. TAA displays all the students records with a matching signature.
3. User chooses the correct student.
4. User edit the student record.
5. User confirms the update.
6. TAA saves the new student record.

Extensions
2a. TAA cannot find any student record with a matching signature.
2b. TAA requests for re-enter.
2c. User re-enter the student name or ID
Use case resume from step 3.

UC3 - Tag a student who has not finished his/her homework
Actor: User
MSS:

1. User searches for his/her name or ID.
2. TAA displays all the students records with a matching signature.
3. User chooses the correct student.
4. User chooses the type of tag.
5. User confirms the tagging action.
6. TAA register the tag and update accordingly.

Extensions
2a. TAA cannot find any student record with a matching signature.
2b. TAA requests for re-enter.
2c. User re-enter the student name or ID
Use case resume from step 3.
