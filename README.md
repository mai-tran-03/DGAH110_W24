# Overview
## DataCleanup
The DataCleanup program takes two text files of a text-to-analyze file and a stopwords file. It processes the text-to-analyze file by removing any non-alphabetical characters and converting all words to lowercase and adding them to a list. It then proccesses the stopwords, commonly used words in English, and add them to a set and removes that set of words from the list of words. Finally, the cleaned data is written to a new text file containing only words and no stopwords.

## DataAnalyze
The DataAnalyze program takes a cleaned text file, from the output of the DataCleanup program. It calculates the word frequency by putting all the words into a map with a count order. It then writes the top 10 most frequent words and least frequent words to a new text file. The file contains the words and their counts.

# Usage
## DataCleanup
To use the program, run the following:
  ```
  $ javac *.java
  $ java DataCleanup fileName stopWordsFile
  ```
The program will write a new CleanedFile.txt to the current directory.

## DataAnalyze
To use the program, run the following:
  ```
  $ javac *.java
  $ java DataAnalyze fileName
  ```
The program will write a new WordFrequency.txt to the current directory.
