# AchievementView for Android
A custom view for achievements pop-ups written in Kotlin.

The basic properties can be changed through XML like it's size, color, drawable, duration. If this isn't enough then
feel free to customize as you wish!

![gif](https://github.com/szaboa/AchievementView/blob/master/art/output.gif)


## Installation
You can import the ```achievementview``` module into your project or just grab it via Gradle,

```java
compile 'com.cdev.android:achievement-view:0.9'
```

## Usage

**1. Include into your layout**
```xml
<com.cdev.achievementview.AchievementView
        android:id="@+id/achievement_view"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        custom:collapseStartDelay="3500"
        custom:colorLeft="@color/green_light"
        custom:colorRight="@color/green_dark"
        custom:drawableLeft="@drawable/trophy"/>
```

**2. Get a reference and call ```show```**

*Java*

```java
AchievementView achievementView = (AchievementView) findViewById(R.id.achievement_view);

// show the achievement with a single line
achievementView.show("Single line text");

// show the achievement with two lines
achievementView.show("First line", "Second line");
```

*Kotlin*
```kotlin
val achievementView = findViewById(R.id.achievement_view) as AchievementView

// show the achievement with a single line
achievementView.show("Single line text")

// show the achievement with two lines
achievementView.show("First line", "Second line")
```

## Customize
The following attributes can be changed,
```xml
custom:rightPartWidth="250dp"                   // default 300 pixel
custom:revealDuration="300"                     // default 500 ms
custom:concealStartDelay="1000"                 // default 500 ms
custom:expandDuration="300"                     // default 500 ms
custom:collapseStartDelay="3500"                // default 1500 ms
custom:colorLeft="@color/green_light"           // default #607D8B
custom:colorRight="@color/green_dark"           // default #455A64
custom:drawableLeft="@drawable/trophy"          // default not set
custom:firstLine="First line"                   // default not set
custom:secondLine="Second line"                 // default not set
custom:textColorFirstLine="@color/colorPrimary" // default white
custom:textColorSecondLine="@color/colorAccent" // default white
custom:textSizeFirstLine="10sp"                 // default not set
custom:textSizeSecondLine="8sp"                 // default not set
  ```
 
If you doesn't specify an attribute, then a default value will be set.
  
## License
This project is licensed under the MIT License - see the [License.md](/License.md) file for details
