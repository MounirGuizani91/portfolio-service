# Kubernetes notions

- **Pod** : L’unité de base de déploiement dans Kubernetes. Il encapsule un ou plusieurs conteneurs (souvent un seul), leur stockage, leur réseau, et les options de configuration.
- **Deployment** : Gère le déploiement et la réplication des pods (scalabilité, mises à jour).
- **Service** : Expose les pods pour qu’ils soient accessibles dans le cluster (découverte, load balancing).
- **Ingress** : Permet d’accéder aux services depuis l’extérieur du cluster (routage HTTP/HTTPS).
- **Namespace** : Isole et organise les ressources (pods, services, etc.) dans des groupes logiques.

---

## Définition d'un Pod simple

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: mon-pod
spec:
  containers:
    - name: mon-container
      image: nginx
```
# Kubectl
```
## Lister tous les pods dans le namespace courant
kubectl get pods

## Lister les services
kubectl get services

## Lister les déploiements
kubectl get deployments

## Appliquer un fichier de configuration
kubectl apply -f mon-fichier.yaml

## Supprimer une ressource
kubectl delete -f mon-fichier.yaml

## Afficher les logs d’un pod
kubectl logs nom-du-pod

## Accéder à un pod en shell
kubectl exec -it nom-du-pod -- /bin/sh

## Changer de namespace par défaut
kubectl config set-context --current --namespace=mon-namespace

## Décrire une ressource (détails)
kubectl describe pod nom-du-pod

## Lister les namespaces
kubectl get namespaces
```

## Commandes `minikube` utilisées

- `minikube start`  
  Démarre un cluster Kubernetes local.

- `minikube stop`  
  Arrête le cluster minikube en cours.

- `minikube delete`  
  Supprime complètement le cluster minikube.

- `minikube dashboard`  
  Ouvre le tableau de bord Kubernetes dans le navigateur.

- `minikube service nom-du-service`  
  Ouvre dans le navigateur le service exposé nommé `nom-du-service`.

- `minikube status`  
  Affiche l’état actuel du cluster minikube.

## Commandes `docker` utilisées

- `docker build -t mon-image .`  
  Construit une image Docker à partir du `Dockerfile` du répertoire courant.

- `docker images`  
  Liste toutes les images Docker présentes localement.

- `docker run -p 8081:8081 mon-image`  
  Démarre un conteneur à partir de l’image `mon-image` et mappe le port 8081.

- `docker ps`  
  Affiche les conteneurs en cours d’exécution.

- `docker stop <id-du-conteneur>`  
  Arrête un conteneur en cours d’exécution.

- `docker rm <id-du-conteneur>`  
  Supprime un conteneur arrêté.

- `docker rmi <id-de-l-image>`  
  Supprime une image Docker locale.
## Accès aux environnements

- Environnement de production [http://portfolio-app-prd/swagger-ui/index.html](http://portfolio-app-prd/swagger-ui/index.html)
- Environnement de développement: [http://portfolio-app-dev/swagger-ui/index.html](http://portfolio-app-dev/swagger-ui/index.html)