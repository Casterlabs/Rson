# Rson

Our very own Json library. (Previously known as Rakurai-Json).

## Adding to your project

### Maven
```xml
    <repositories>
        <repository>
            <id>casterlabs-maven</id>
            <url>https://repo.casterlabs.co/maven</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>co.casterlabs</groupId>
            <artifactId>rson</artifactId>
            <version>VERSION_OR_HASH</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```

### Gradle
```gradle
    allprojects {
        repositories {
            maven { url 'https://repo.casterlabs.co/maven' }
        }
    }

    dependencies {
        implementation 'co.casterlabs:rson:VERSION'
    }
```

## Example Code

### Creating Json directly.
```java
// Has convenience constructors
JsonArray array = new JsonArray("Lorem", "ipsum", "dolor", "sit", "amet");

// Supports a builder-like syntax
JsonObject obj = new JsonObject()
    .put("test_num", 1234.5678)
    .put("test_str", "The quick brown fox jumped over the lazy dog.")
    .put("test_arr", array)
    .put("test_obj", JsonObject.singleton("java", "rocks!"))
    .putNull("test_null")
    .putNull("0__non_json5_friendly_field_name");

System.out.println(
    new Rson.Builder()
        .setJson5FeaturesEnabled(true)
        .setPrettyPrintingEnabled(true)
        .build()
        .toJsonString(obj)
);

// Output:
/*
{
    test_num: 1234.5678,
    test_str: "The quick brown fox jumped over the lazy dog.",
    test_arr: [
        "Lorem",
        "ipsum",
        "dolor",
        "sit",
        "amet"
    ],
    test_obj: {
        java: "rocks!"
    },
    test_null: null,
    "0__non_json5_friendly_field_name": null
}
*/

// System.out.println(obj); Also works.

```

### Demos
[Demo File](https://github.com/Casterlabs/Rson/blob/main/Json/JsonDemo.java)  
[Validation Demo File](https://github.com/Casterlabs/Rson/blob/main/Json/JsonValidationDemo.java)  
[Methods Demo File](https://github.com/Casterlabs/Rson/blob/main/Json/JsonMethodDemo.java)  