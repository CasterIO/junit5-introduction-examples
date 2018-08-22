![Caster.io logo](https://caster.io/assets/cio_logo-369287ed535e10c1e2291304f48ed0ccb9ed4d29ab2d0c6ba82634789f7d4c38.png)
# Caster.IO: Introduction to JUnit 5

These examples correlate with the course material for "Introduction to JUnit 5" on [Caster.IO](https://caster.io).
For each applicable lesson, there is a corresponding Git branch inside this repository.

## Installation

Clone the repository, check out a branch of your choice & open the project with IntelliJ IDEA or Android Studio.

## Multiplatform

There are three modules inside this repository, which make up the sample project under test:

* `android`: Android application for Blackjack
* `cli`: Console application for Blackjack
* `common`: Business logic & common code shared by both application modules

Because IntelliJ IDEA doesn't like Android and pure-Java modules living side by side in the same project, 
the `android` module is disabled by default, and the Console application is executable.
If you want to see the Android application in action, or run its tests,
please open the `settings.gradle` file and change the value of the `USE_ANDROID` flag.
