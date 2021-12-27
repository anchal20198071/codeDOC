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

## Extra Feature Added
- Users can search and download questions from codeforces.

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
8. Codeforces API used.

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
- Login Window
<img src="https://user-images.githubusercontent.com/59894659/147474630-55752678-e36d-4ecb-908e-8378c7343017.png">

- CodeDoc Interface
<img src="https://user-images.githubusercontent.com/59894659/147474807-485d6508-5bc9-486b-8a0b-e3705238ebdf.png">

- Saving code
<img src="https://user-images.githubusercontent.com/59894659/147474994-7c911a15-2c3d-4ba7-adce-1a77f5c707db.png">

- Opening a Saved file
<img src="https://user-images.githubusercontent.com/59894659/147475095-a02d011c-9d0f-44b3-a648-1b869e19f7c0.png">

- Changing Font Settings
<img src="https://user-images.githubusercontent.com/59894659/147475172-663d94fa-cb0a-4147-8fcb-127436996d5c.png">
<img src="https://user-images.githubusercontent.com/59894659/147475269-50db7043-cbf3-416b-bf5f-fc29a77de09c.png">

- Switch to Dark Mode
<img src="https://user-images.githubusercontent.com/59894659/147475378-8cc71852-fdf6-4034-99c6-bba1b7edd402.png">

- Multiple Tab For each Session
<img src="https://user-images.githubusercontent.com/59894659/147475475-ed984600-4f7d-4123-a9f1-5d5403ecbe18.png">

- Search for Specific tag Questions of Codeforces
<img src="https://user-images.githubusercontent.com/59894659/147475556-1df32761-4e9a-4e04-9a45-49c3c991957c.png">

- Collaborate with other users via collaboration key
<img src="https://user-images.githubusercontent.com/59894659/147475670-cd916312-25a5-47db-8051-3e2e13544ab1.png">

- Use the Collaboration key and Join the collaboration corresponding to that collaboration key
<img src="https://user-images.githubusercontent.com/59894659/147475769-59da2870-1c67-4b46-a3c1-0daf22acf291.png">

- Autocomplete Feature
<img src="https://user-images.githubusercontent.com/59894659/147475872-874f718c-d6a5-4098-9f1f-9d913d6c612b.png">

- Search and Highlight Feature
<img src="https://user-images.githubusercontent.com/59894659/147475949-89560962-7c97-4e7d-a6cd-31a7c0de607f.png">

- Chat with users in that Collaboration
<img src="https://user-images.githubusercontent.com/59894659/147476040-748dd882-a4c8-4448-9c0a-4601421f2287.png">

- Chat Privately with other users by their UserID
<img src="https://user-images.githubusercontent.com/59894659/147476237-a187f511-2622-402f-bbe5-4c7ebf08aa7a.png">
<img src="https://user-images.githubusercontent.com/59894659/147476367-a7b0ff79-9a6e-422a-97bd-bc160ed2a901.png">
