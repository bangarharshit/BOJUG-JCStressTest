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
