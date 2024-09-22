val commonUtils: String by project

dependencies {
    implementation(project(commonUtils))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
