[![CircleCI](https://circleci.com/gh/joklek/PointExplorerTask.svg?style=svg)](https://circleci.com/gh/joklek/PointExplorerTask)

# Intro
This is an application made for a job application and fun.
The goal of this program is to interactively read shape definitions from the user 
and then determine if any provided points reside within provided shapes. 
If there are, the program will print those shapes and their areas, also the total sum of found areas.

## Implemented Commands
* circle \<x> \<y> \<radius> : Creates a circle with center at given x and y, with given radius
* ellipse \<x> \<y> \<radius1> \<radius2> : Creates a circle with center at given x and y, with given radii
* donut \<x> \<y> \<hole radius> \<outer ring radius> : Creates a donut with center at given x and y, with given hole and outer radii
* triangle \<x1> \<y1> \<x2> \<y2> \<x3> \<y3> : Creates a triangle with given points
* help  : Provides usage information 
* exit : exits from program
* \<x> \<y> : outputs all shapes that have these provided points inside, gives their area, and provides the area sum of those shapes

## Example
The example input could be
```
circle 0 0 10
triangle 0 10 10 -1 -1
donut 0 0 5 10
ellipse 0 0 10 11
0 0
```