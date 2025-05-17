# 🚀 My Android App

[![Android Build](https://github.com/prasadaraoteela/learn-jetpack-compose/actions/workflows/android_build.yml/badge.svg)](https://github.com/prasadaraoteela/learn-jetpack-compose/actions/workflows/android_build.yml)
[![Security Checks](https://github.com/prasadaraoteela/learn-jetpack-compose/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/prasadaraoteela/learn-jetpack-compose/actions/workflows/github-code-scanning/codeql)

![APK Size](https://img.shields.io/badge/apk--size-9.99MB-blue)
![Kotlin](https://img.shields.io/badge/kotlin-1.9.0-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.7.4-blue)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![GitHub last commit](https://img.shields.io/github/last-commit/prasadaraoteela/learn-jetpack-compose)

An Android app built with **Jetpack Compose**, **Kotlin**, and **MVVM**, featuring automated checks using **GitHub Actions**.

---

## 🧰 Features

- Jetpack Compose UI
- MVVM Architecture
- Hilt for Dependency Injection
- Detekt for static code analysis
- Ktlint or Spotless for formatting
- GitHub Actions for CI/CD

---

## 📸 Screenshots

| Home Screen |
|-------------|
| ![Home](screenshots/home.png) |

---

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **AndroidX**
- **Hilt**
- **Retrofit**
- **Room**
- **GitHub Actions** for CI
- **Detekt** for linting
- **Gradle Kotlin DSL**

---

## ✅ GitHub Actions

This project uses GitHub Actions for automated checks:

### ➤ `android_build.yml`
Runs on every push/pull request:
- Lint check
- Unit tests
- Assemble debug build
- Detekt check

---

## 🧪 Running Locally

```bash

# Clone the repo
git clone https://github.com/prasadaraoteela/learn-jetpack-compose.git

# Navigate to project
cd learn-jetpack-compose

# Android Clean Build
./gradlew clean assembleDebug

```

## 🧹 Static Code Analysis

```bash
# Run Detekt
./gradlew detekt

# Run Format Check (if using ktlint or spotless)
./gradlew ktlintCheck

```
