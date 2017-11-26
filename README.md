# BOJUG-JCStressTest
JCStress tests for Bojug meetup

## Resources:
1. Alexey Shipilev's [blog](https://shipilev.net/blog/2016/close-encounters-of-jmm-kind/#horror-primitives) on JMM and JCstress tests for visualizing those constructs.
2. More sample tests - [Oracle repo](http://hg.openjdk.java.net/code-tools/jcstress) 

## How to run:
Add the vmoptions, if declared in the file
```
mvn verify && java -jar target/jcstress.jar -t ".*UnsafePublication.*" -v 
```

## Samples in the repo:
1. [Word tearing](https://github.com/bangarharshit/BOJUG-JCStressTest/blob/master/src/main/java/org/sample/BitSetTest.java)
2. [Non atomic increment](https://github.com/bangarharshit/BOJUG-JCStressTest/blob/master/src/main/java/org/sample/AtomicityAtomic.java)
3. [Compare access sweep](https://github.com/bangarharshit/BOJUG-JCStressTest/blob/master/src/main/java/org/sample/CASTest.java)
4. [Unsafe publication](https://github.com/bangarharshit/BOJUG-JCStressTest/blob/master/src/main/java/org/sample/UnsafePublication.java) - Run with vm option -XX:-UseCompressedOops
5. [Visibility test](https://github.com/bangarharshit/BOJUG-JCStressTest/blob/master/src/main/java/org/sample/VolatilteTest.java)

## How to contribute:
Write a new test or add documentation/explanation to the existing tests, and send the PR :).

## License
```
MIT License

Copyright (c) 2017 bangarharshit

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
