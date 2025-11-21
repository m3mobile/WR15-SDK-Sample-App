# M3 WR15 Android SDK Sample

This repository contains an **Android sample application** and **documentation** for integrating the **WR15 Scanner SDK** into your own Android apps.

The sample app demonstrates how to:

- Initialize the WR15 SDK
- Connect to a WR15 ring scanner
- Receive scanned barcode data
- Change basic scanner / firmware settings

---

## How to Open This Project
#### 1. Clone this repository
```bash
git clone https://github.com/m3mobile/WR15-SDK-Sample-App.git

cd WR15-SDK-Sample-App
```

#### 2. Open **Android Studio**
  - Select **'Open - WR15-SDK-Sample-App'**

#### 3. Let Gradle sync finish.

---

## Adding the WR15 SDK to Your Own Project
To use the WR15 SDK in your own app, add the Maven repository and dependency to your Gradle files.

#### 1. Add the Maven repository in your **settings.gradle.kts**
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        // WR15 SDK repository
        maven("https://m3mobile.github.io/wr15-sdk-maven")
    }
}
```
If you are using **Groovy**

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        // WR15 SDK repository
        maven { url "https://m3mobile.github.io/wr15-sdk-maven" }
    }
}
```

#### Add the SDK dependency
In your module-level build.gradle or build.gradle.kts
```kotlin
dependencies {
    implementation("com.m3:wr15-sdk:x.y.z") //Check the latest version in `https://github.com/m3mobile/wr15-sdk-maven/releases'
}
```


