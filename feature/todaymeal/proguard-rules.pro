# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ------------------------------
# Feature 모듈에서 Gson 모델 클래스 사용 시 보존
# ------------------------------
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.arambyeol.feature.**.model.** { *; }

# Compose 관련
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }

# Hilt
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
