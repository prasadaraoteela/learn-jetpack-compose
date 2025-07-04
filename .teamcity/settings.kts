import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.03"

project {

    buildType(AndroidLintCheck)
    buildType(AndroidBuild)
    buildType(AndroidUnitTests)
    buildType(AndroidCompile)
    buildType(AndroidUiTests)
}

object AndroidBuild : BuildType({
    name = "Android Build"

    artifactRules = "app/build/outputs/apk/debug/app-debug.apk => apk"
    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "🧹 Lint"
            id = "Lint"
            tasks = "lint"
        }
        gradle {
            name = "🧹 Detekt"
            id = "Detekt"
            enabled = false
            tasks = "detekt"
        }
        gradle {
            name = "🧪 Unit Tests"
            id = "Unit_Tests"
            tasks = "testDebugUnitTest"
        }
        gradle {
            name = "🏗️ Build Debug APK"
            id = "Build_Debug_APK"
            tasks = ":app:assembleDebug"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})

object AndroidCompile : BuildType({
    name = "android-compile"

    artifactRules = "app/build/outputs/apk/debug/app-debug.apk => apk"
    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "🏗️ Build Debug APK"
            id = "Build_Debug_APK"
            tasks = ":app:clean :app:assembleDebug"
        }
    }

    dependencies {
        snapshot(AndroidUnitTests) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})

object AndroidLintCheck : BuildType({
    name = "android-lint-check"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "🧹 Lint"
            id = "Lint"
            tasks = "lint"
        }
    }
})

object AndroidUiTests : BuildType({
    name = "android-ui-tests"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Start Android Emulator"
            id = "Start_Android_Emulator"
            scriptContent = """
                #!/bin/bash
                echo "Starting Android Emulator..."
                ${'$'}ANDROID_HOME/emulator/emulator -avd Medium_Phone_API_35 -no-window
                adb wait-for-device
                adb shell input keyevent 82
            """.trimIndent()
        }
        gradle {
            name = "🧪 UI Tests"
            id = "UI_Tests"
            tasks = "connectedDebugAndroidTest"
        }
    }
})

object AndroidUnitTests : BuildType({
    name = "android-unit-tests"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "🧪 Unit Tests"
            id = "Unit_Tests"
            tasks = "testDebugUnitTest"
        }
    }

    dependencies {
        snapshot(AndroidLintCheck) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
