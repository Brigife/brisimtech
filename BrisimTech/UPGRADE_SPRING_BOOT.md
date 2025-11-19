Step-by-step Spring Boot 3.5 upgrade checklist

1. Branching
   git checkout -b upgrade/spring-boot-3.5

2. Build and tests
   cd BrisimTech
   # Windows PowerShell
   .\mvnw.cmd -U -DskipTests=true clean package
   .\mvnw.cmd -DskipTests=false test

   If wrapper fails: install system maven and run `mvn -U -DskipTests=true clean package`.

3. Run OpenRewrite locally (optional, automates some mechanical transformations)
   - Add the Rewrite Maven plugin to `pom.xml` (snipped there as commented stanza).
   - Run recipes locally with the rewrite plugin, e.g.:

   # Example: list recipes
   mvn -q org.openrewrite:rewrite-maven-plugin:5.20.0:list

   # Example: run recipes
   mvn -Drewrite.activeRecipes=org.openrewrite.java.migrate.MigrateJavaxToJakarta -Drewrite.skip=false org.openrewrite:rewrite-maven-plugin:5.20.0:run

   Recipes you may want to try (validate each recipe before running):
   - org.openrewrite.java.migrate.MigrateJavaxToJakarta
   - org.openrewrite.java.spring.boot.UpgradeSpringBoot_3_5 (may not exist: check available recipes)
   - org.openrewrite.java.spring.security.UpgradeSpringSecurity_6

   If recipes fail, revert the branch and try a subset of recipes.

4. Manual checks to run after OpenRewrite or after your test run
   - Search for `javax.` and replace with `jakarta.` where appropriate.
   - Check `application.properties` for deprecated properties.
   - Update `jjwt` usage: ensure secret key is long (256-bit) and set via env var.
   - Run integration tests for Twilio, email, and database connectivity.

5. Update and push
   git add .
   git commit -m "chore: apply OpenRewrite migrations and manual fixes for Spring Boot 3.5"
   git push origin upgrade/spring-boot-3.5

6. Open a PR and run CI to ensure compilation and tests pass.

Notes:
- Some OpenRewrite recipes require additional configuration or the correct rewrite library version; list available recipes first.
- If you want, I can craft a specific `rewrite.yml` recipe file for your repo; say so and I’ll create it and commit it to this branch.
