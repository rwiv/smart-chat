tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
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
}