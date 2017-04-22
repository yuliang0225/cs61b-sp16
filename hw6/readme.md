## Description



This homework consists of four files.

<u>BoggleBoard.java</u> : represents Boggle boards;

<u>Trie.java</u> : implements the trie data structure;

<u>BoggleSolver.java</u> : finds all valid words in a given Boggle board;

<u>Boggle.java</u> : main program.



An input command to boggle should look like:

```
$ java Boggle (-k [number of words])
              (-n [width])
              (-m [height])
              (-d [path to dictionary])
              (-r) < [input board file]
```

Example

```
$ java Boggle -k 7 -r testBoggle
thumbtacks
thumbtack
setbacks
setback
ascent
humane
smacks
```

