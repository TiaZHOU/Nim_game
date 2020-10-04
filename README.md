# Nim_game
Nim game
It is a two player game, and the rules of the version used
here are as follows:

The game begins with a number of objects (e.g., stones placed on a table).
Each player takes turns removing stones from the table.

On each turn, a player must remove at least one stone. In addition, there is an upper bound on
the number of stones that can be removed in a single turn. For example, if this upper bound is 3,
a player has the choice of removing 1, 2 or 3 stones on each turn.

The game ends when there are no more stones remaining. The player who removes the last stone,
loses. The other player is, of course, the winner.

Both the initial number of stones, and the upper bound on the number that can be removed, can
be varied from game to game, and must be chosen before a game commences.


The features added are:
Sort the players with more specific rules

Handling of invalid input via Exceptions

Write (read) the game statistics into (from) a file, i.e., one which is stored on the hard disk between
executions of Nimsys

A new type of player - an AI (Artificial Intelligence) player, whose moves are automatically deter-
mined by the computer rather than a game user

A victory guaranteed strategy for the AI player

An advanced Nim game and a victory guaranteed strategy for the AI player in this new game (for
bonus marks)
