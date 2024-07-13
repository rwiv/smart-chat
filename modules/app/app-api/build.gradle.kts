tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

val domainCore: String by project
val infraCore: String by project

dependencies {
    implementation(project(domainCore))
    implementation(project(infraCore))

    implementation("org.springframework.boot:spring-boot-starter-web")
}