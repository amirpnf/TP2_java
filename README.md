## TP2 JAVA - Amirhossein Pouyanfar - 262575 Gr2 Mr Rémy Forax


# Exercise 1

1. With `var` we don't need to explicitly declare the type, the compiler 
will infer it by looking at what was used as the initializer of this
variable when declared.
And by knowing the type of this variable, the compiler can know if
there is a `length()` method defined in it's class.

Hence, here `s` is initialized with "toto" which is a string, so the 
compiler will know it's a String class object, on which there is a 
`length()` method defined.

2. In java, Object typed are handled by address.
`s1` is a litteral string, because it's value starts with quotes ("").
Litteral strings are stored in a dictionnary at runtime.

This `s1 == s2` is verifying if `s1` and `s2` have the same address in
memory. the "==" does not indicate the value equality of `s1` and `s2`.
And this `s1 == s2` returns true because as we said, Object typed are 
handled by address, and this line `var s2 = s1;` doesn't create a new
object, it just  copies the address of `s1` in `s2`.

so, `System.out.println(s1 == s2);` prints true, because as we said `s1`
and `s2` have the same address.

However, `System.out.println(s1 == s3);` prints false, because `s3` is a new
object as it's created using the keyword `new`, hence it has a different address
in memory, and `s1 == s3` results false as `s1` and `s2 don't have the same value.

3. The method `equals()` can be used to verify if two strings have the same value.
this method is a part of `java.lang.String` library, which is a part of Java Standard 
Library, and you don't need to import any specific package to use it.
