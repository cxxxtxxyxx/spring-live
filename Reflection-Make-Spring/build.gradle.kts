plugins {
    id("java")
}

group = "shop.mtcoding"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.9.0")

}

tasks.test {
    useJUnitPlatform()
}


tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE // 중복 시 실패 전략 설정
    }
}

sourceSets {
    getByName("main") {
        resources {
            srcDirs("src/main/resources")
        }
    }
}