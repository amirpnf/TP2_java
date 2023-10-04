## TP2 JAVA - Amirhossein Pouyanfar - 262575 Gr2 Mr Rémy Forax


# Exercise 1

1. Using `var` we don't need to explicitly declare the type, the compiler 
will infer it by looking at what is used as the initializer of this
variable when declared.
By knowing the type of this variable, the compiler can verify if
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

3. The method `equals()` can be used to compare two strings, first by their have
length and then by their characters.
This method is a part of `java.lang.String` library, which is a part of Java Standard 
Library, and you don't need to import any specific package to use it.

4. `s6` and `s7` are litteral strings. As previously mentionned, litteral strings are stored in a
dictionnary/cache at runtime. This implies that `s6` and `s7` have the same address in memory
(they reference the same object `"toto"` in memory), therefore `System.out.println(s6 == s7)`
will ultimately print true as `==` compares addresses when used with objects.

5. First of all, Java saves a lot of heap space because different string variables can refer
to the same thing in memory.
Secondly, if String class wasn't immutable it would cause several security threats to the
application. 
In addition, since a string is immutable, its hashcode is generated at the time of creation
and therefore it doesn't need to be recalculated again.
At last, the fact that the String Class is immutable, makes if safe for multi-
threading. A single instance can be shared across multiple threads.

6. As mentionned before, String class is immutable. That means, once created,
their value can't be modified. The method `toUpperCase()` doesn't 
change the object, but it returns a new instance of the String class, containing
the characters of `s8`, in capitals. Therefore, a call to `System.out.println(s8);`
will print "hello" and not "HELLO".
In order to print "HELLO", we can write :

```java
var s8 = "hello";
var s9 = s8.toUpperCase();
System.out.println(s9);
```

Here `s9` is a new instance of String class, storing the return value of `s8.toUpperCase()`.


# Exercise 2


1. Here's a class `Morse`, printing the strings given as arguments, separated by `"Stop."` :

```java
public class Morse {
    public static void main (String[] args) {
        String res = join(args, " Stop. ");
        System.out.println(res);
    }

    public static String join(String[] array, String separator) {
        var res = array[0] + separator;
        for (int i = 1; i < array.length; i++) {
            res = res + array[i] + separator;
        }
        return res;
    }
}
```
The `join` method uses the `+` operator to concatenate the strings.

2. The `java.lang.StringBuilder` object is an extensible buffer of characters,
allowing you to build a string without having too many intermediate string
allocation. 
The `append` method of this class (StringBuilder) returns a StringBuilder object
in order to allow for method chaining. This means it's possible to call multiple
`append` methods one after another. You might say that it would be possible to do
so if append returned a string instead of a StringBuilder object, but note that 
returning a StringBuilder object avoids creating a new string each time, because
the StringBuilder class is mutable, unlike String class. So when you write
`build.append(separator)`, the current StringBuilder object is modified, there's no
new object created.

3. Here's the `Morse` class, now using `StringBuilder`:

```java
public class Morse {
    public static void main (String[] args) {
        String res = join(args, " Stop. ");
        System.out.println(res);
    }

    public static String join(String[] array, String separator) {
        var build = new StringBuilder();
        var sep = "";
        for(var i : array) {
            build.append(sep).append(i);
            sep = separator;
        }
        build.append(sep);
        return build.toString();
    }
}    
```

The upside of this, compared to the previous version of `Morse` class, is 
probably it's performance. Using `StringBuilder`, you avoid creating useless
temporary string objects every time you iterate through the loop, this can
considerably increase the performance et reduce the memory use, when you are handling
massive quantities of data.  
