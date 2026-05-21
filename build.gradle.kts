plugins {
    java
    `maven-publish`
}

version = "2.1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("com.google.guava:guava:33.6.0-android")
    implementation("androidx.collection:collection:1.6.0")

    testImplementation("junit:junit:4.13.2")
}

afterEvaluate {
    publishing {
        publications {
            register(project.name, MavenPublication::class.java) {
                groupId = "com.aliucord"
                artifactId = "binary-resources"

                from(components["java"])
            }
        }

        repositories {
            val username = System.getenv("MAVEN_USERNAME")
            val password = System.getenv("MAVEN_PASSWORD")

            if (username != null && password != null) {
                maven {
                    credentials {
                        this.username = username
                        this.password = password
                    }
                    setUrl("https://mvn.janisslsm.id.lv/#/canny")
                }
            } else {
                mavenLocal()
            }
        }
    }
}
