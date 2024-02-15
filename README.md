# Java Program for File Retrieval Engine 

This program is divided into 3 layers which are AppInterface, ProcessingEngine and IndexStore. 

The **AppInterface**  will provides a command-line interface for a file retrieval engine. It prompts users to input commands such as indexing files, searching for specific queries, and quitting the program. It utilizes a ProcessingEngine instance to perform these operations. Users can input commands like "index <path>" to index files, "search <query>" to search for files, and "quit" to exit the program.

The **ProcessingEngine** will be responsible for indexing and searching files. It utilizes an IndexStore instance for storing indexed files and their contents. The class utilizes multithreading via an ExecutorService to concurrently index files in a specified directory. The indexFiles method walks through the directory, filters out non-text files, and submits indexing tasks to the thread pool. The searchFiles method performs a case-insensitive search for a given query within the indexed files. Additionally, it provides functionality to gracefully shut down the thread pool via the shutdown method.

The **IndexStore** will manage the global index of words and their occurrences in various files. It uses a ConcurrentHashMap to store word occurrences per file. The updateIndex method updates the global index with the word counts from a local index of a specific file. The searchANDQuery method performs a search for files containing all terms in a given query using a logical AND operation. It calculates the scores of each file based on the occurrence of terms and returns the top 10 files with the highest scores. Additionally, it measures the duration of the search operation and prints it out.


# Execution commands


**command to compile** : javac -d target src/main/java/org/vishal/appinterface/*.java src/main/java/org/vishal/indexstore/*.java src/main/java/org/vishal/processengine/*.java

**command to execute**
java -cp target org.vishal.appinterface.AppInterface

**options**
index <path>  - Enter relative path with the index
search <query> - Enter the valid query
quit  - Exit the program
