# Substitution Cipher Encoder-Decoder

## Description

Substitution cipher Encoder-Decoder will perform various operations relating
encoding a message using a substitution cipher, and also tools for decoding 
such a message. A **simple substitution cipher** is a cipher, where each letter is 
substituted with different letter/symbol and the correspondence between 
letters and such symbols constitutes a key for the cipher. 


I chose to do this program as I find it interesting to learn about ciphers and ways of decoding them. 
I think that  such program could easily be exapnded to do more interesting things, like adding new 
ciphers to crack, and thus could constitute a good personal project. 

This program consists of two complementary functionalities: 
* _Encoding_ a message using a desired or generated key to obtain an encoded 
message. 
* Provide tools for _decoding_ a message that was encripted with a substitution cipher.

Encoding a message constitutes entering a text that is to be encoded and a key, that will be 
used to encode the text. 

Decoding a message will be possible through the following: 
* Gradual substitution of characters in the encoded text with key characters via addition of key-value pairs
* Keeping a workspace area with where keys can be saved. 


Notes: 
* Each key corresponds to one character and each individual value corersponds to one character,
thus a code cannot encode two characters with one key and decode two characters.
* special character '-' is used to indicate that there is not value for the key 

## User stories 

#### Story 1: 
As a user, I want to be able to encode a text with a substitution cipher of my choice.  

#### Story 2: 
As a user, I want to be able to substitute characters in an encoded text with for characters I think these 
represent. 

#### Story 3: 
As a user, I want to be able to save key-value maps for characters, and try a different map during decoding,
and be able to come back to the previous maps if I need to.

#### Story 4: 
As a user, I want to see the text that I am encoding/decoding on the console at all times, so that I am 
aware of the progress I am making.

## Additional user stories for phase 2: 

#### Story 5: 
As a user, I want to save the workspace containing the encoded/decoded text as well as keys that I am using 
and have tried to a file. 

#### Story 6: 
As a user, I want to be able to reload the workspace I saved the last time from a file. 

## Phase 4: Task 2

The task of implementing a type hierarchy was implemented in the ui package. Both InputTextGUI and 
OutputtextGUI extend TextGUI, each overrides syncWithText() method in a different way. 

Also, the Key class uses Map interface to store keys and values in the Key. 

##Phase 4: Task 3

* I would reduce the coupling, especially semantic coupling between the classes. For example, I would 
introduce more class variable that denote strings and commands. I would also make GUI classes more 
independent by making WorkSpaceGUI to pass only those fields to other classes that are necessary. 
* I would increase cohesion also think about splitting the Text class into two classes - one that 
deals with input text and another that deals with output text, and I would introduce a type hierarchy 
so that both of these classes would extend an abstract class Text. 
* I would go through all the code and reduce the number of duplication of code by introducing more helper 
methods. For example, there is code duplication for updating workspace and text areas in GUI classes
