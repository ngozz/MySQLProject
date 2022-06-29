# XAMPP
I used XAMPP to create a MySQL database. Here’s the information.

Database name is “mysql_db”

![image](https://user-images.githubusercontent.com/68413509/176473082-f751df38-54a6-46b4-974a-529a0ad94185.png)

Table name is “oopproject” with 7 columns

![image](https://user-images.githubusercontent.com/68413509/176473661-dfd5aa7a-bb2f-4926-94e0-199d1b20513b.png)

Here’s the detail of each element.

![image](https://user-images.githubusercontent.com/68413509/176473301-6d722d56-771e-453a-aef2-9aee5a34e80f.png)
 
Set username to “root” and password to “hieu.”

The MySQL library (JAR file) is included inside the source code. Open my project and associate the file with your IDE to and it will work.

# Demonstration
![image](https://user-images.githubusercontent.com/68413509/176474140-eb759041-190b-425e-8870-63ec62972834.png)

Next, I'll demonstrate each feature. I’ll begin with the first one, “Add user.”
We need to create a form that can accept input from users. Here’s 2 hours of my life. Yeah, I coded everything. JavaSwing sucks.

![image](https://user-images.githubusercontent.com/68413509/176474340-a539c3ea-4f31-480d-ba7d-520b38c4ee9b.png)
 
The “Reset” button will clear all text fields. 

To make it simple, the “edit” feature will use the same form as the “add” feature. Instead of reusing the form, I added both to the original form.

The “delete” feature is much easier. I used JOptionPane to create a single input form.

![image](https://user-images.githubusercontent.com/68413509/176474594-bd68a499-2b35-4d84-af90-e04b2ddac88e.png)

Next is the search feature

This is the before table.

![image](https://user-images.githubusercontent.com/68413509/176474752-f7f0c92a-8ec7-4058-b34e-9278c815a5c6.png)

This is after doing the search.

![image](https://user-images.githubusercontent.com/68413509/176474828-f8abf008-e740-4a6d-bb62-9d63e473278d.png)

Thanks to javax.swing.table.TableRowSorter, it’s a search feature inside the table, not the code itself.
 
Let's move on to the last two features
Again, we need to create a form for the user input

![image](https://user-images.githubusercontent.com/68413509/176475063-bc3e0244-73f5-4d73-94e8-606a2953fe7d.png)

Pressing “Thống kê” will print out a table that includes information of all contracts which have ‘start dates’ between the given period of time.

![image](https://user-images.githubusercontent.com/68413509/176475129-6d072c25-5c16-4468-a022-aeaed6a52e43.png)

Here’s what the last feature output looks like. All credit goes to JOptionPane.

![image](https://user-images.githubusercontent.com/68413509/176475260-193e5030-e8f2-4f2d-913c-2ba81653e461.png)

 
 
