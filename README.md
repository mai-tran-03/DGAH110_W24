# Overview
## DataCleanup
The DataCleanup program takes two text files: text-to-analyze and stopwords. It processes the text-to-analyze file by removing non-alphabetical characters, converting all words to lowercase, and adding them to a list. It then processes the stopwords, commonly used words in English, adds them to a set, and removes that set of words from the list of words. Finally, it writes the cleaned data to a new text file containing only words and no stopwords.

## DataAnalyze
The DataAnalyze program takes a cleaned text from the output of the DataCleanup program. It calculates the word frequency by putting all the words into a map with a count order. It then writes the top 10 most frequent and least frequent words to a new text file. The file contains the words and their counts.

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
