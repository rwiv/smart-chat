tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release")
    }
}

val commonUtils: String by project
val domainCore: String by project
val infraCore: String by project

dependencies {
    implementation(project(commonUtils))
    implementation(project(domainCore))
    implementation(project(infraCore))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")

    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.graphql-java:graphql-java-extended-scalars:21.0")
}