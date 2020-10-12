# Substitution Cipher Encoder-Decoder

## Description

Substitution cipher Encoder-Decoder will perform various operations relating
encoding a message using a substitution cipher, and also tools for decoding 
such a message. A **simple substitution cipher** is a cipher, where each letter is 
substituted with different letter/symbol and the correspondence between 
letters and such symbols constitutes a key for the cipher. There are different
kinds of substitution ciphers, for example, in **homophonic substitution**, the cypher can be made 
slighly more complex, if a single letter corresponds to several symbols. 

I chose to do this program as I am very interested in various ciphers and ways of decoding them. I think that 
such program could easily be exapnded to do more interesting things, like adding new ciphers to crack, and thus 
could constitute a good personal project. 

This program consists of two complementary functionalities: 
* _Encoding_ a message using a desired or generated key to obtain an encoded 
message. 
* Provide tools for _decoding_ a message that was encripted with a substitution cipher. (See note)

NOTE: In this program, in the decoding part the tools provided are mainly useful in 
deciphering a simple substitution cipher or a homophonic substitution ciphers. Tools for more 
complex ciphers will not be provided. 

Encoding a message constitutes uploading a text that is to be encoded and a key, that will be 
used to encode the text. Alternatively, a random key can be generated as well.

Decoding a message will be possible through the following: 
* Frequency analysis of symbols or groups of symbols
* Determining and counting frequencies of 2-, 3-, 4- letter words/digraphs
* Keeping a workspace area
* Design a genetic algorithm that solves a simple substitution cipher (for later). 
* substititute tuples of keys with most common words using the existing key set (for later).

## User stories 

#### Story 1: 
I want to be able to upload a text file with my text and encode it with a cipher of my choice. Then, I want to 
download the encoded text. Also, I would like to be able to change the cipher and obtain a new encoded text. 

#### Story 2: 
I found this ancient in the attic writtein using simple substitution cipher (i do not know how, but i know it). 
I want to try a couple of things and decidpher the message. First, I suspect that the meaining of a couple of symbols,
so I want to see how the text is going to turn out first. Then I would like to count frequencies each symbols appears in
the text,and try incrimentally to decipher every symbol until I decipher the entire text. 

#### Story 3: 
A friend of mine created an encrypted message and I would first assume that it is a simple substitution cipher. I would 
want to systematically solve the cipher, either by trial-and-error, or using some tools, like frequency analysis. I 
have not done it before, so I would want to have a program that would keep my progress into deciphering, keeping my 
earlier trials, and also marking which letter-symbol pairs I am sure about and which I am still unsure and which are 
unknown. Thus, I would need to keep track of previous key sets that I have tried. 

#### Story 4: 
I am trying to decode a cipher that I found in a mysterious cave, and I have good reasons to suspect that the original
text is written in modern English. I think that it would be easier, in addition to a frequency analysis, to have 
lists of most common two-, three-, four-letter words/letter combination in English, so that I could find the most 
common two-, three-, and four-lettered strings and see what happens to the cipher text when I substitute one or some 
of such combinations with the most common words from the list. 

## Structure of this program 

NOTES: 
* Each key corresponds to one character and each individual value corersponds to one character,
thus a code cannot encode two characters with one key and decode two characters.
* one key can correspond to 0, 1 or many values.
* special character '-' is used to indicate that there is not value for the key 