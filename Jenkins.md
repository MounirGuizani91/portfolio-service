# Documentation Jenkins pour portfolio-service

## Objectif
Ce projet utilise Jenkins pour l'intégration continue (CI) et le déploiement continu (CD). Jenkins automatise la compilation, les tests et l'archivage du projet Java/Maven.

## Prérequis
- Java (OpenJDK 11 ou supérieur)
- Maven installé et accessible dans le PATH
- Jenkins installé (service Windows ou via jenkins.war)

## Lancement de Jenkins

### 1. Via le service Windows
- Ouvre `services.msc` et démarre le service "Jenkins".
- Accède à Jenkins sur [http://localhost:8082](http://localhost:8082)

### 2. Via le fichier jenkins.war
- Ouvre une invite de commandes (cmd)
- Place-toi dans le dossier où se trouve `jenkins.war`
- Lance Jenkins sur un port libre (exemple 8082 si 8081 est occupé) :
  ```
  java -jar jenkins.war --httpPort=8082
  ```
- Accède à Jenkins sur [http://localhost:8082](http://localhost:8081)

## Configuration du pipeline

Le pipeline Jenkins est défini dans le fichier `Jenkinsfile` à la racine du projet. Il contient les étapes suivantes :
- **Build** : Compile et package le projet avec Maven
- **Test** : Exécute les tests unitaires
- **Archive** : Archive le JAR généré

Extrait du Jenkinsfile :
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
```

## Problèmes courants
- **Port déjà utilisé** : Si Jenkins ne démarre pas, vérifie que le port n'est pas occupé (`netstat -ano | findstr :8081`).
- **Maven non reconnu** : Ajoute Maven au PATH système.
- **Accès refusé** : Lance le terminal en mode administrateur.

## Ressources
- [Documentation officielle Jenkins](https://www.jenkins.io/doc/)
- [Documentation Maven](https://maven.apache.org/)

---

N'hésite pas à adapter ce fichier selon l'évolution de ton pipeline ou de ton infrastructure.

