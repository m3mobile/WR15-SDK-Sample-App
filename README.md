> For more details, please click [here](https://github.com/m3mobile/ring-scanemul-sdk-maven/releases) to visit the Ring ScanEmul SDK repo and refer to the SDK manual.

# M3 Ring ScanEmul Android SDK Sample

This repository contains an **Android sample application** and **documentation** for integrating the **Ring ScanEmul Scanner SDK** into your own Android apps.

The sample app demonstrates how to:

- Initialize the Ring_ScanEmul SDK
- Connect to a Ring_ScanEmul ring scanner
- Receive scanned barcode data
- Change basic scanner / firmware settings

---

## How to Open This Project
#### 1. Clone this repository
```bash
git clone https://github.com/m3mobile/Ring_ScanEmul-SDK-Sample-App.git
```

#### 2. Open **Android Studio**
  - Select **'Open - Ring_ScanEmul-SDK-Sample-App'**

#### 3. Let Gradle sync finish.
---

## Adding the Ring_ScanEmul SDK to Your Own Project
To use the Ring ScanEmul SDK in your own app, add the Maven repository and dependency to your Gradle files.

#### 1. Add the Maven repository in your **settings.gradle.kts**
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        // Ring ScanEmul SDK repository
        maven("https://m3mobile.github.io/ring-scanemul-sdk-maven")
    }
}
```
If you are using **Groovy**

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        // Ring ScanEmul SDK repository
        maven { url "https://m3mobile.github.io/ring-scanemul-sdk-maven" }
    }
}
```

#### Add the SDK dependency
In your module-level build.gradle or build.gradle.kts
```kotlin
dependencies {
    implementation("com.m3:ring-scanemul-sdk:x.y.z") //Check the latest version in `https://github.com/m3mobile/ring-scanemul-sdk-maven'
}
```
> For more details, please click [here](https://github.com/m3mobile/ring-scanemul-sdk-maven/releases) to visit the Ring ScanEmul SDK repo and refer to the SDK manual.

