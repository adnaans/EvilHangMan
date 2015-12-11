This is the Evil Hangman Nifty Project.

In it, I made a program that takes a user inputted word length, and then sort through the dictionary to find all words of that length. Then, when a user inputs a letter, the program finds all words with that letter and divides them into "families". These families contain words with the given letter in all possible positions. 

For example, if the word length given is 3, the computer first finds all the three letter words in the dictionary. If the user guesses the letter "e", the program finds all 3 letter words with "e" as the first letter, second letter, and third letter.

The final step in the program is to choose the largest family. This is then set to the "activelist" which is used as the new dictionary.

I also included extra bells and whistles such as an arraylist called "_guessed" which shows the player which letters they have guessed so they don't accidentally waste a guess. When the "_guessed" gets too long for the player to see, the letters the player has not guessed is shown instead.