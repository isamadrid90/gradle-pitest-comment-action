# Introduction

This is a POC to make it easier to include mutation testing as a part of a regular pipeline,
it has a lot of room to improvement

What it does ❓
============

The purpose of this action is to execute pitest, upload the report as an artifact and
add a comment with the summary of the execution

Prerequisites 📝
============
* Gradle
* PITest plugin
```kotlin
plugins {
    id("info.solidsoft.pitest") version "1.7.0"
}
```
* PITest configuration working in local, here an example:
```kotlin
pitest {
    setProperty("junit5PluginVersion", "0.12")
    setProperty("targetClasses", listOf("package-to-check"))
    setProperty("outputFormats", listOf("HTML")) //this is important because it will update it as an artifact
}
```

How to use it 🛠️
=============

Add action as step

```yaml
uses: isamadrid90/gradle-pitest-comment-action@v0
with:
  repo-token: ${{ secrets.GITHUB_TOKEN }}
  reports-retention: 1
  reports-path: "build/reports/pitest"
  delete-old-comments: "true"
```

Screenshots 📸
=============
![img.png](img.png)
