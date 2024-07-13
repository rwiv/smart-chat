tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

val domainCore: String by project

dependencies {
    implementation(project(domainCore))

    implementation("org.springframework.boot:spring-boot-starter-web")
}