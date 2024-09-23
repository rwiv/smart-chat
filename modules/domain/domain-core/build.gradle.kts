val commonUtils: String by project

dependencies {
    implementation(project(commonUtils))

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
}
