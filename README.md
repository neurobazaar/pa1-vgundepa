## CSC435 Programming Assignment 1 (Winter 2024)
**Jarvis College of Computing and Digital Media - DePaul University**

**Student**: TO-DO write-student-name (TO-DO write-email-address)

**Solution programming language**: TO-DO write-solution-programming-language (C++ or Java)

### Requirements

If you are implementing your solution in C++ you will need to have GCC 12.x and CMake 3.22.x installed on your system. On Ubuntu 22.04 you can install GCC and set it as default compiler using the following commands:

```
sudo apt install g++-12 gcc-12 cmake
sudo update-alternatives --remove-all gcc
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 110
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-12 120
sudo update-alternatives --remove-all g++
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-11 110
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-12 120
```

If you are implementing your solution in Java you will need to have Java 1.7.x and Maven 3.6.x installed on your systems. On Ubuntu 22.04 you can install Java and Maven using the following commands:

```
sudo apt install openjdk-17-jdk maven

```

### Setup

There are 5 datasets (Dataset1, Dataset2, Dataset3, Dataset4, Dataset5) that you need to use to evaluate your solution. Before you can evaluate your solution you need to download the datasets. You can download the datasets from the following link:

https://depauledu-my.sharepoint.com/:f:/g/personal/aorhean_depaul_edu/EgmxmSiWjpVMi8r6QHovyYIB-XWjqOmQwuINCd9N_Ppnug?e=TLBF4V

After you finished downloading the datasets copy them to the dataset directory (create the directory if it does not exist). Here is an example on how you can copy Dataset1 to the remote machine and how to unzip the dataset:

```
remote-computer$ cd <path-to-repo>
remote-computer$ mkdir datasets
local-computer$ scp Dataset1.zip cc@<remote-ip>:<path-to-repo>/datasets/.
remote-computer$ cd <path-to-repo>/datasets
remote-computer$ unzip Dataset1.zip
```

### C++ solution
#### How to build/compile  

To build the C++ solution use the following commands:
```
cd app-cpp
mkdir build
cmake -S . -B build
cmake --build build
```

#### How to run application

To run the C++ clean dataset program (after you build the project) use the following command:
```
./build/clean_dataset <input directory> <output directory>
```

To run the C++ word count program (after you build the project) use the following command:
```
./build/count_words <input directory> <output directory>
```

To run the C++ sort words program (after you build the project) use the following command:
```
./build/sort_words <input directory> <output directory>
```

### Java solution
#### How to build/compile

To build the Java solution use the following commands:
```
cd app-java
mvn compile
mvn package
```

#### How to run application

To run the Java clean dataset program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.CleanDataset <input directory> <output directory>
```

To run the Java word count program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.CountWords <input directory> <output directory>
```

To run the Java sort_words program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.SortWords <input directory> <output directory>
```

# Java Text Processing Programs

This repository contains a set of Java programs designed for processing text data within `.txt` files. The programs are intended to be used in sequence to clean text data, count word occurrences, and sort words by their frequencies.

## Programs Overview

1. **CleanDataset.java**
   - **Purpose:** Cleans text data by removing non-alphanumeric characters and normalizing whitespace.
   - **Usage:** `java CleanDataset <inputDir> <outputDir>`
     - `inputDir`: Directory with `.txt` files to clean.
     - `outputDir`: Directory to save cleaned files, preserving the input directory structure.

2. **CountWords.java**
   - **Purpose:** Counts occurrences of each word in text files.
   - **Usage:** `java CountWords <inputDir> <outputDir>`
     - `inputDir`: Directory with `.txt` files for word counting.
     - `outputDir`: Directory to save files with word counts, preserving the input directory structure.

3. **SortWords.java**
   - **Purpose:** Sorts words by frequency in descending order based on word counts in text files.
   - **Usage:** `java SortWords <inputDir> <outputDir>`
     - `inputDir`: Directory with `.txt` files containing word counts.
     - `outputDir`: Directory to save files with sorted word counts, preserving the input directory structure.

## Usage Instructions

- Ensure Java is installed and properly set up on your system.
- Use the programs in the order listed to process your text data effectively.
- Specify the correct paths for `inputDir` and `outputDir` when running the programs.

## Requirements

- Java Runtime Environment (JRE) or Java Development Kit (JDK).

For more detailed information on each program, please refer to the comments within the source code.
