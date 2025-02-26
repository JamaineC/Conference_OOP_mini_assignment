# Conference Management App – Java & OOP  
## Project Overview  
This project is a **mini-assignment** from **Aberystwyth University's Object-Oriented Programming course**, focusing on **Java-based event management**. The application allows users to manage a **conference schedule**, supporting **event creation, search functionality, venue management, and file I/O for saving and loading data**. The system applies **OOP principles** like **inheritance and polymorphism**, using a superclass for events with specialized subclasses for **Talks and Social Events**. The project also includes **UML diagrams** for system design.  

## Prerequisites  
Java 17 or later, an IDE such as IntelliJ IDEA or Eclipse.  

## Installation & Setup  
Clone the repository: `git clone https://github.com/your-repo/conference-app-java.git && cd conference-app-java`  
Compile all Java files: `javac *.java`  
Run the application: `java ConferenceApp`  

## System Features  
Users can **add, search, and remove events**, while managing **venues** with data projector requirements. The **Event superclass** enables the creation of **Talks (with speakers) and Social Events (with catering and invitation options)**. The **Conference class** stores and manages all events, while the **ConferenceApp class** provides a **command-line interface** with a menu system for user interactions. Events are **saved and loaded from files** using file I/O operations.  

## Core Components  
The **Conference** class manages **event storage and retrieval**. The **Event** superclass defines **common event properties**, with **Talk and SocialEvents subclasses** handling **specific details**. The **Venue** class ensures compatibility with **data projector requirements**. The **ConferenceApp** class provides a **menu-driven interface** for user interactions.  

## Troubleshooting  
If the **program does not compile**, ensure **all Java files are present and correctly referenced**. If **file loading fails**, check **file paths and formatting issues in saved data**. If **events do not display correctly**, verify **obtainAllEvents() in the Conference class**.  

## Future Improvements  
Enhancements could include **a graphical user interface (GUI) for better usability**, **data validation to prevent incorrect input**, and **improved file handling for structured data storage**.  

## License  
This project is released for **educational and research purposes**. Contributions and improvements are welcome!  
