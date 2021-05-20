import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
public object Libs {
    /**
     * https://github.com/bumptech/glide
     */
    public const val com_github_bumptech_glide_compiler: String =
            "com.github.bumptech.glide:compiler:" + Versions.com_github_bumptech_glide

    /**
     * https://github.com/bumptech/glide
     */
    public const val glide: String = "com.github.bumptech.glide:glide:" +
            Versions.com_github_bumptech_glide

    /**
     * https://github.com/square/retrofit
     */
    public const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
            Versions.com_squareup_retrofit2

    /**
     * https://github.com/square/retrofit
     */
    public const val retrofit: String = "com.squareup.retrofit2:retrofit:" +
            Versions.com_squareup_retrofit2

    public const val databinding_adapters: String = "androidx.databinding:databinding-adapters:" +
            Versions.androidx_databinding

    /**
     * https://developer.android.com/studio
     */
    public const val databinding_common: String = "androidx.databinding:databinding-common:" +
            Versions.androidx_databinding

    /**
     * https://developer.android.com/studio
     */
    public const val databinding_compiler: String = "androidx.databinding:databinding-compiler:" +
            Versions.androidx_databinding

    public const val databinding_runtime: String = "androidx.databinding:databinding-runtime:" +
            Versions.androidx_databinding

    public const val viewbinding: String = "androidx.databinding:viewbinding:" +
            Versions.androidx_databinding

    /**
     * https://kotlinlang.org/
     */
    public const val kotlin_annotation_processing_gradle: String =
            "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    public const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    public const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    public const val navigation_fragment_ktx: String =
            "androidx.navigation:navigation-fragment-ktx:" + Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    public const val navigation_safe_args_gradle_plugin: String =
            "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    public const val navigation_ui_ktx: String = "androidx.navigation:navigation-ui-ktx:" +
            Versions.androidx_navigation

    /**
     * https://github.com/google/dagger
     */
    public const val dagger_android_processor: String =
            "com.google.dagger:dagger-android-processor:" + Versions.com_google_dagger

    /**
     * https://github.com/google/dagger
     */
    public const val dagger_android_support: String = "com.google.dagger:dagger-android-support:" +
            Versions.com_google_dagger

    /**
     * https://github.com/google/dagger
     */
    public const val dagger_compiler: String = "com.google.dagger:dagger-compiler:" +
            Versions.com_google_dagger

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    public const val room_compiler: String = "androidx.room:room-compiler:" + Versions.androidx_room

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    public const val room_runtime: String = "androidx.room:room-runtime:" + Versions.androidx_room

    /**
     * https://developer.android.com/testing
     */
    public const val com_android_support_test_runner: String = "com.android.support.test:runner:" +
            Versions.com_android_support_test_runner

    /**
     * https://developer.android.com/studio
     */
    public const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
            Versions.com_android_tools_build_gradle

    public const val de_fayard_buildsrcversions_gradle_plugin: String =
            "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    /**
     * https://square.github.io/okhttp/
     */
    public const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
            Versions.logging_interceptor

    /**
     * http://developer.android.com/tools/extras/support-library.html
     */
    public const val paging_runtime_ktx: String = "androidx.paging:paging-runtime-ktx:" +
            Versions.paging_runtime_ktx

    /**
     * http://tools.android.com
     */
    public const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintlayout

    /**
     * https://developer.android.com/testing
     */
    public const val espresso_core: String = "com.android.support.test.espresso:espresso-core:" +
            Versions.espresso_core

    /**
     * https://developer.android.com/jetpack/androidx
     */
    public const val recyclerview: String = "androidx.recyclerview:recyclerview:" +
            Versions.recyclerview

    /**
     * https://developer.android.com/studio
     */
    public const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" +
            Versions.lint_gradle

    /**
     * https://developer.android.com/jetpack/androidx
     */
    public const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

    /**
     * https://github.com/material-components/material-components-android
     */
    public const val material: String = "com.google.android.material:material:" + Versions.material

    /**
     * https://developer.android.com/studio
     */
    public const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

    /**
     * http://junit.org
     */
    public const val junit: String = "junit:junit:" + Versions.junit
}