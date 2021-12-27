# codeDOC - A Code Collaboration Platform, where multiple users can code simultaneously while experiencing some extra features too.

#### Github repo URL : https://github.com/anchal20198071/codeDOC
#### Demo Video: 

## Features: 
### Basic Features:
1. Users can signup/login/logout.
2. User should be able to fetch previous records
3. Users can create/edit/save CodeDoc.
4. Users can allow other users to edit/see in their CodeDoc and after giving permission can block the user from further updating the code.
5. When any user updates in the CodeDoc the same change must be reflected in every other user's window, working on the same CodeDoc.
6. Coding area should have highlighting/autocomplete/recommendation according to language. (Library)
7. Should allow writing/compilation in at least 4 languages.
8. Coding area should show line numbers.
9. Users can run programs by providing input and can have output.

### Advance Features:
1. Recommendation feature implemented by own(used Trie).
2. Separate Chat section where users can send/receive messages both privately and to everyone.
3. Coding area features in point 6 of basic implemented from scratch.
4. Write cursors for users should display their respective names (like Google Docs).
5. Audio-video communication between users (Can use external libraries for this, e.g. WebRTC)
6. Multiple tabs for each session.

## Tech Stack
- Frontend Desing : Java Swing
- Backend : Java Socket Programming, Java OOP Concepts
- Database : MySQL
- Jars Used : 
1. JCanlender Jar
2. MySQL connector jar
3. Gson Library (Api Parsing)
4. JSoup Library (HTML Scraping)
5. bridj jar (Audio Communication)
6. slf4j-api jar (Audio Communication)
7. webcam-capture jar (Audio Communication)

### TEAM: Untangled in Threads
#### Team Members:
- Arya Pandey (20193043, Mechanical)
- Asmita Yadav (20195007, ECE)
- Anchal Yadav (20198071, IT)

## Instructions to Run:
- Project Installation and Running it
1. Firstly Install Java(any version) , Netbeans IDE and MySQL database.
2. Clone the project via https://github.com/anchal20198071/codeDOC and open both the project(ServercodeDoc, codeDOC) in netbeans.
3. Add all the required Jars - JCanlender Jar, MySQL connector jar, Gson Library, Json Library.
4. Configure your Mysql database username and password in file Mysqlconnect.java in ServercodeDoc Project.
5. The IP of the server in the client side can be set in RegisterWindow.java, LoginWindow.java, CodeDocTab.java of codeDOC package.
6. Now first run ServercodeDoc Project then run codeDOC project by clicking on Run Project icon on the top.

## ScreenShots
<img src="https://user-images.githubusercontent.com/59894659/147441808-1c54faa2-f27f-4721-a306-c87b037a279d.png">
