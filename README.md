[![Build Status](https://travis-ci.org/olegosipenko/mapperverifier.svg?branch=master)](https://travis-ci.org/olegosipenko/mapperverifier)
<a href="http://www.methodscount.com/?lib=com.github.olegosipenko%3Amapperverifier%3A0.0.1-SNAPSHOT"><img src="https://img.shields.io/badge/Methods count-636-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.olegosipenko%3Amapperverifier%3A0.0.1-SNAPSHOT"><img src="https://img.shields.io/badge/Size-80 KB-e91e63.svg"/></a>
[![](https://jitpack.io/v/olegosipenko/mapperverifier.svg)](https://jitpack.io/#olegosipenko/mapperverifier)
[![Apache License 2.0](https://img.shields.io/:license-Apache%20License%202.0-blue.svg?style=plastic)](https://github.com/olegosipenko/mapperverifier/blob/master/LICENSE.md)
[![Open issues](https://img.shields.io/github/issues/olegosipenko/mapperverifier.svg?style=plastic)](https://github.com/olegosipenko/mapperverifier/issues)

**MapperVerifier** intended to use in unit tests to validate correctness of model class mappers

Have you ever written a mapper class from one entity to another with dozen or two number of fields? Boring. Especially boring seems the unit-testing of such mapper: imagine, you need to create instance of the class you want to map from, fill it with some data, and then traverse all fields and check that they equal to expected values.

**MapperVerifier** helps you. Testing mapper could be as simple as:

```java
   @Test
   void testMapper() {
       MapperVerifier.forClass(MySuperMapper.class).verify()
   }
```

And **MapperVerifier** does everything for you.

Installations
---

Current Limitations
---
* Mapper class should contain one public mapping method with one argument and return type different from Void.
* If you have more public methods in your mapper class with one argument and return type different from Void, you can implicitly specify mapping method using method `forMethod(Method method)`:

```java
   @Test
   void testSpecificMethodMapper() {
       MapperVerifier.forClass(MySuperMapper.class)
           .forMethod(MySuperMapper.class.getMethod("mySuperMappingMethod"))
           .verify()
   }
```
* There's no support for mappers taking two or more arguments to produce output
* Number of getters in input and output classes should match and also should match their ordering
* For now fields of only primitive types and Strings supported
