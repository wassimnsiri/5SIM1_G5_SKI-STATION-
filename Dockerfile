# Étape 1 : Utiliser une image Maven pour construire le projet
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src

# Télécharger les dépendances et construire le projet
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image JDK légère pour exécuter l'application
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré de l'étape précédente
COPY --from=build /app/target/gestion-station-ski-*.jar app.jar

# Exposer le port utilisé par l'application
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
